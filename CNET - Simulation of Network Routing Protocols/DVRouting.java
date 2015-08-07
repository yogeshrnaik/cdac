// up/down link working
// change Link working
// count to infinity problem appearing
// split horion hack mechanism working
// adding a new Router completed
// menu driven

// new link addition working 
// a new link a-b which was not there in the initial topology when made up will get added

import ncst.pgdst.*;
import java.io.File;

// class used to map the id of router and its index in the adjacency matrix
class Mapper
{
	int index;
	String id;

	Mapper(int a_index, String a_id)
	{
		index = a_index;
		id = a_id;
	}
}

class Neighbour
{
	String id;
	RoutingInfo rtable[];
	Neighbour next;

	Neighbour(String a_id)
	{
		id = a_id;
		next = null;
	}
}

class RoutingInfo
{
	String dest_id,interface_id;
	int cost;

	RoutingInfo(String a_dest_id, String a_interface_id, int a_cost)
	{
		dest_id = a_dest_id;
		interface_id = a_interface_id;
		cost = a_cost;
	}
}

class Router
{
	String id;
	Router next;
	boolean status;
	RoutingInfo rtable[];
	Neighbour nbrHead;
	
	Router(String a_id)
	{
		id = a_id;
		next = null;
		status = true;
		nbrHead = null;
		rtable = null;
	}

	public void changeLinkCost (int index, int cost)
	{
		rtable[index].cost = cost;
	}
	
	// to update the routing table of a router without using Split Horizon Hack
	public void update(int a_index, int no_of_routers)
	{
		Neighbour curr_nb;
		int min_cost;
		String min_interface_id = "-";

		//System.out.println(a_index + " is updating");
		for (int i=0; i<no_of_routers; i++)
		{
			//System.out.println("Updating for " + i);
			curr_nb = nbrHead;
			
			min_cost = 9999;
			//min_cost = 100;

			if (i == a_index)
			{
				rtable[i].interface_id = "-";
				rtable[i].cost = 0;
			}
			else
			{
				// updating the tabel based on the tables of neighbours
				curr_nb = nbrHead;
				while (curr_nb != null)
				{
					//System.out.println("i = " + i);
					//System.out.println(temp_nb.rtable[i].cost + temp_nb.rtable[a_index].cost + " " + min_cost);
					if (curr_nb.rtable != null)
					{
						if (curr_nb.rtable[i].cost + curr_nb.rtable[a_index].cost < min_cost)
						{
							min_cost = curr_nb.rtable[i].cost + curr_nb.rtable[a_index].cost;
							min_interface_id = curr_nb.id;
							//System.out.println("min cost updated:" + min_cost);
							//System.out.println("interface updated:" + min_interface_id);
						}
					}
					curr_nb = curr_nb.next;
				}
				/*
				rtable[i].interface_id = min_interface_id;
				rtable[i].cost = min_cost;
				*/
				if (min_cost >= 100)
				{
					rtable[i].interface_id = "-";
					rtable[i].cost = 100;
				}
				else
				{
					rtable[i].interface_id = min_interface_id;
					rtable[i].cost = min_cost;
				}
			}
		}
	} // end of update method
} // end of Router class

public class DVRouting
{	
    int no_of_routers;
	Mapper map[];
	int adjmat[][];
	Router rtrHead;
	SimpleOutput soutput;
	String path, ipfile, opfile;

	DVRouting () throws IOException
	{
		no_of_routers = 0;
		rtrHead = null;
		//path = "C:\\j2sdk1.4.0_02\\bin\\";
		path = "C:\\java\\bin\\";
		ipfile = "dvrip.txt";
		opfile = "dvrop.txt";
/*
		// initialise a file object to write output to a file
		File foutput = new File(path + opfile);
		soutput = new SimpleOutput(foutput);
*/
	}
	public static void main(String[] args) throws IOException
	{
		DVRouting routing = new DVRouting();
		char choice;
		int option;
		SimpleInput sin = new SimpleInput();

		first:
		while (true)
		{		
			System.out.println("\n\n\t\t\tDISTANCE VECTOR ROUTING SIMULATION");
			System.out.println("\t\t\t==================================\n\n");

			boolean isWithSHH = false;
			System.out.println("\t\t\t1 : With Split Horizon Hack");
			System.out.println("\n\t\t\t2 : Without Split Horizon Hack");
			System.out.println("\n\t\t\t3 : Exit Simulation");
			System.out.print("\n\n\t\t\tEnter option : ");
			option = sin.readInt();

			if (option == 1)
				isWithSHH = true;
			else if (option == 2)
				isWithSHH = false;
			else if (option >= 3)
				return;

			second:
			while (true)
			{
				System.out.println("\n\t\t\t1 : Specify Network Topology");
				System.out.println("\n\t\t\t2 : Load Network Topology from a File");
				System.out.println("\n\t\t\t3 : Go back to main menu");
				System.out.print("\n\n\t\t\tEnter option : ");
				option = sin.readInt();

				if (option == 1)
				{
					routing.generateTopologyFile();
					routing.createRouters("dvrip.txt");
				}
				else if (option == 2)
				{
					System.out.print("\n\n\t\t\tEnter file name : ");
					String filename = sin.readWord();
					routing.createRouters(filename);
				}					
				else if (option >= 3)
					continue first;
				
				int itno = 1;

				// starting simulation
				// initialise a file object to write output to a file
				File foutput = new File(routing.path + routing.opfile);
				routing.soutput = new SimpleOutput(foutput);
				
				third:
				while (true)
				{
					// iteration started
					if (isWithSHH)
						routing.sendRoutingTables_withSHH();
					else
						routing.sendRoutingTables_withoutSHH();

					/*System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println("Printing information of all routers before updating.");
					routing.printRoutersInfo();
					System.out.println("-----------------------------------------------------");*/

					routing.updateRoutingTables();

					/*System.out.println();
					System.out.println("-----------------------------------------------------");
					System.out.println("Printing information of all routers after updating.");
					routing.printRoutersInfo();
					System.out.println("-----------------------------------------------------");*/

					// print the routing tables of all routers to output.txt
					routing.printRouteringTables(routing.rtrHead, itno);

					System.out.println("\n\t\t\t1 : Modify Network Topology");
					System.out.println("\n\t\t\t2 : Proceed to next interation");
					System.out.println("\n\t\t\t3 : Go to previous menu");
					System.out.print("\n\n\t\t\tEnter option : ");
					option = sin.readInt();

					if (option == 1)
					{
						System.out.println("\n\n\t\t\t1 : Add a router");
						System.out.println("\t\t\t2 : Change the cost of a link");
						System.out.println("\t\t\t3 : Make a link down");
						System.out.println("\t\t\t4 : Make a link up");
						System.out.println("\t\t\t5 : Exit\n");
						System.out.print("\n\t\t\tEnter your option : ");
						option = sin.readInt();
						switch(option)
						{
							case 1 :	routing.addNewRouter(); break;

							// change the cost of a link
							case 2 :	System.out.print("\n\t\tEnter source id, Dest id and Cost : "); 
										routing.changeLink(sin.readWord(), sin.readWord(), sin.readInt());break;

							// make a link down
							case 3 :	System.out.print("\n\t\t\tEnter source id, Dest id : ");
										routing.changeLink(sin.readWord(), sin.readWord(), 100);break;
										
							// make a link up
							case 4 :	System.out.print("\n\t\tEnter source id, Dest id and Cost : ");
										routing.changeLink(sin.readWord(), sin.readWord(), sin.readInt());break;
						}
					}
					else if (option >= 3)
						continue second;

					itno++;
				} // end of third while loop
			} // end of second while loop
		} // end of first while loop
	} // end of main

	// load network topology from config.txt file and create routers
	public void createRouters(String filename) throws IOException
	{
		String id1 = "",id2 = "";
		int cost;
		Router router;

		// initialise a file object to read network topology from filename
		//File fin = new File("/pgdst/d0214017/Routing/config.txt");
		//File fin = new File("c:\\java\\bin\\config.txt");
		String filepath = path + filename;
		File fin = new File(filepath);

		SimpleInput sin = new SimpleInput(fin);

		no_of_routers = sin.readInt();
		map = new Mapper[no_of_routers];
		//System.out.println("no_of_routers = " + no_of_routers);

		// create the linked list of routers
		rtrHead = null;
		for (int i=0; i<no_of_routers; i++ )
		{
			id1 = sin.readWord();
			// map the router id with its index in the adjacency matrix
			map[i] = new Mapper(i, id1);
			router = new Router(id1);
			if (rtrHead == null)
				rtrHead = router;
			else
				addRouter(rtrHead, router);
		}
	
		// create adjacency matrix
		adjmat = new int[no_of_routers][no_of_routers];
		for (int i=0; i<no_of_routers; i++)
			adjmat[i][i] = 0;

		while (!sin.eof())
		{
			int ind1,ind2;
			id1 = sin.readWord();
			id2 = sin.readWord();
			cost = sin.readInt();

			ind1 = getIndex(id1);	// get index corresponding to router id1
			ind2 = getIndex(id2);	// get index corresponding to router id2

			//System.out.println(ind1 +" " +ind2 +" "+cost);

			adjmat[ind1][ind2] = cost;
			adjmat[ind2][ind1] = cost;
		}
		for (int i=0; i<no_of_routers; i++)
		{
			for (int j=0; j<no_of_routers; j++)
			{
				if (i != j && adjmat[i][j] == 0)
					adjmat[i][j] = 100;
			}
		}
		//printAdjMat();

		// create routing table for each router
		for (router=rtrHead; router!=null; router=router.next)
		{
			int ind_source;
			id1 = router.id;
			ind_source = getIndex(id1);
			router.rtable = new RoutingInfo[no_of_routers];
			for (int i=0;i<no_of_routers;i++)
			{
				id2 = getId(i);
				router.rtable[i] = new RoutingInfo(id2,"-",adjmat[ind_source][i]);
			}
		}

		// create linked list of neighbours for each router
		for (router=rtrHead; router!=null; router=router.next)
		{
			int temp_index;
			temp_index = getIndex(router.id);

			for (int i=0; i<no_of_routers; i++)
			{
				if (adjmat[temp_index][i] != 0 && adjmat[temp_index][i] != 100)
				{
					Neighbour temp_nb = new Neighbour(getId(i));
					temp_nb.rtable = new RoutingInfo[no_of_routers];
					
					if (router.nbrHead == null)
						router.nbrHead = temp_nb;
					else
						addNeighbour(router.nbrHead, temp_nb);
				}
			}
		}
	} // end of createRouters

	public void addRouter(Router head, Router temp)
	{
		while (head.next != null)
			head = head.next;
		head.next = temp;
	}

	public void addNeighbour(Neighbour head,Neighbour temp)
	{
		while (head.next!=null)
			head = head.next;
		head.next = temp;
	}

	// prints the routing tables of all routers to output.txt after each iteration
	public void printRouteringTables(Router head, int itno) throws IOException
	{
		int i;
		soutput.writelnString("------------------------------Iteration " + itno + "-----------------------------------");
		while (head!=null)
		{
			soutput.writelnString(head.id);
			// soutput.writelnString(head.id + " " + head.status);
			soutput.writelnString("Destination ID\tInterface ID\tCost");
			for (i=0;i<no_of_routers;i++)
			{
//				soutput.writelnString("       "+head.rtable[i].dest_id +"\t     "+head.rtable[i].interface_id+"\t\t"+head.rtable[i].cost);
				soutput.writeString("       "+head.rtable[i].dest_id +"\t     "+head.rtable[i].interface_id);
				if (head.rtable[i].cost >= 100)
					soutput.writelnString("\t\tINFINITY");
				else
					soutput.writelnString("\t\t"+head.rtable[i].cost);
			}
			soutput.writelnString("");
			head = head.next;
		}
	}

	// this method is used to print the tables of each neighbour of each router
	// after all routers send their tables to their neighbours
	public void printNbrTables(Router router)
	{
		while (router != null)
		{
			Neighbour neighbour;
			neighbour = router.nbrHead;
			System.out.println("Router ID : " + router.id);
			
			while (neighbour != null)
			{
				if (neighbour.rtable != null)
				{
					System.out.println("Neighbour ID : " + neighbour.id);
					System.out.println("Destination id\tInterface Id\tcost");
				
					for (int i=0; i<no_of_routers; i++)
					{
						System.out.println("       "+neighbour.rtable[i].dest_id + "\t     " + neighbour.rtable[i].interface_id + "\t\t" + neighbour.rtable[i].cost);
					}
				}
				else
					System.out.println(neighbour.id + "'s table NULL");
				neighbour = neighbour.next;
			}
			router = router.next;
		}
		System.out.println();
	} // printNbrTables

	public int getIndex(String a_id)
	{
		for (int i=0;i<no_of_routers;i++)
		{
			if (map[i].id.equals(a_id))
			{
				return map[i].index;
			}
		}
		return -1;
	}

	public String getId(int a_index)
	{
		for (int i=0;i<no_of_routers;i++)
		{
			if (map[i].index == a_index)
			{
				return map[i].id;
			}
		}
		return "";
	}

	public void printAdjMat()
	{
		System.out.println();
		System.out.println("-----------------------------------------------------");
		System.out.println("Adjacency Matrix:");
		for (int i=0;i<no_of_routers;i++)
		{
			for (int j=0;j<no_of_routers;j++)
			{
				System.out.print("\t" + adjmat[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------");
	}

	// ask the user to enter the network topology and write it in the config.txt file
	public void generateTopologyFile() throws IOException
	{
		// initialise a file object for writing network topology
       	// File fout = new File("/pgdst/d0214017/Routing/config.txt");
		File fout = new File(path + ipfile);
		SimpleOutput sout = new SimpleOutput(fout);

		SimpleInput sin = new SimpleInput();

		System.out.print("\n\t\t\tEnter the number of routers : ");
		no_of_routers = sin.readInt();
		sout.writelnInt(no_of_routers);

		for (int i=0; i < no_of_routers; i++ )
		{
			System.out.print("\t\t\tEnter id of router " + (i+1) + " : ");
			sout.writelnString(sin.readWord());
		}	
		
		System.out.println("Enter network topology in the following form and 0 0 0 to terminate.");
		System.out.println("Router id 1     Router id 2      Cost");
		while (true)
		{	
			String x,y;
			int cost;
			String choice;
			x = sin.readWord();
			y = sin.readWord();
			cost = sin.readInt();

			if (x.equals("0") && y.equals("0") && cost == 0)
				break;

			sout.writelnString("");
			sout.writeString(x + " ");
			sout.writeString(y + " ");
			sout.writeInt(cost);
			sout.flush();
		}
	}

	// used to get the reference of a neighbour of a destination router
	Neighbour getDestTableNeighbourRef(String a_src_id,String a_dst_id)
	{
		Router temp;
		Neighbour temp_nb;
		temp = rtrHead;

		while (!temp.id.equals(a_dst_id))
		{
			temp = temp.next;
		}

		temp_nb = temp.nbrHead;
		while (!temp_nb.id.equals(a_src_id))
		{
			temp_nb = temp_nb.next;
		}
		
		return temp_nb;
	}

	Router getRouterRef(String a_id)
	{
		Router temp;
		temp = rtrHead;
		while (!temp.id.equals(a_id))
		{
			temp = temp.next;
		}
		return temp;
	}

	// sending updates to each neighbour without using Split Horizon Hack mechanism
	public void sendRoutingTables_withoutSHH()
	{
		Router router = rtrHead;
		//System.out.println("-----------------------------------------------------");
		while (router != null)
		{	
			Neighbour neighbour;
			neighbour = router.nbrHead;
			//System.out.println(router.id + " is sending updates");
			while (neighbour != null)
			{
				/* send table to the neighbour only if the link 
				between the router and its neighbour is valid. */
				if (adjmat[getIndex(router.id)][getIndex(neighbour.id)] < 100)
				{				
					//System.out.println("TO " + neighbour.id);
					//Send updates to neighbours.
					Neighbour dest_nb = getDestTableNeighbourRef(router.id, neighbour.id);
					dest_nb.rtable = new RoutingInfo[no_of_routers];
					for (int i=0; i<no_of_routers; i++)
					{
						dest_nb.rtable[i] = new RoutingInfo(router.rtable[i].dest_id,router.rtable[i].interface_id,router.rtable[i].cost);
					}
				}
				neighbour = neighbour.next;
			}
			router = router.next;
		}
		//System.out.println("Updates sent");
		//System.out.println("-----------------------------------------------------");
	} //sendRoutingTables_withoutSHH

	// sending updates to each neighbour using the Split Horizon Hack mechanism
	public void sendRoutingTables_withSHH()
	{
		Router router = rtrHead;
		//System.out.println("-----------------------------------------------------");
		while (router != null)
		{	
			Neighbour neighbour;
			neighbour = router.nbrHead;
			//System.out.println(router.id + " is sending updates");
			while (neighbour != null)
			{
				/* send table to the neighbour only if the link 
				between the router and its neighbour is valid. */
				if (adjmat[getIndex(router.id)][getIndex(neighbour.id)] < 100)
				{				
					//System.out.println("TO " + neighbour.id);

					/* Send updates to neighbours but before doing that 
					   change the entry of cost present in the source table 
					   for which the interface id == neighbour id to INFINITY.
					   i.e. if (router.rtable[i].interface_id == neighbour.id)
								set cost = INFINITY.
					*/
					Neighbour dest_nb = getDestTableNeighbourRef(router.id, neighbour.id);
					dest_nb.rtable = new RoutingInfo[no_of_routers];
					for (int i=0; i<no_of_routers; i++)
					{
						// whether to include this condition (&& getIndex(neighbour.id) != i)) or not?
						if (router.rtable[i].interface_id.equals(neighbour.id) && getIndex(neighbour.id) != i)
							dest_nb.rtable[i] = new RoutingInfo(router.rtable[i].dest_id,"-", 100);
						else
							dest_nb.rtable[i] = new RoutingInfo(router.rtable[i].dest_id,router.rtable[i].interface_id,router.rtable[i].cost);
					}
				}
				neighbour = neighbour.next;
			}
			router = router.next;
		}
		
		//System.out.println("Updates sent.");
		//System.out.println("-----------------------------------------------------");

	} // sendRoutingTables_withSHH

	public void updateRoutingTables()
	{
		Router router = rtrHead;
		while (router != null)
		{
			router.update(getIndex(router.id), no_of_routers);

			/* after a router updates its table, it removes 
			all the tables of its neighbours so that in the
			next interation new tables can be stored */
			Neighbour nbr = router.nbrHead;
			while (nbr != null)
			{
				nbr.rtable = null;
				nbr = nbr.next;
			}
			router = router.next;
		}
	}

	// write back the changes made in the topology to the config.txt file
	public void modifyTopologyFile() throws IOException
	{
		File fout = new File(path + ipfile);
		//File fout = new File("/pgdst/d0214017/Routing/config.txt");
		SimpleOutput sout = new SimpleOutput(fout);
		sout.writelnInt(no_of_routers);

		Router router = rtrHead;

		while (router != null)
		{
			sout.writelnString(router.id);
			router = router.next;
		}
			
		for (int i=0; i<no_of_routers-1; i++)
		{
			for (int j=i+1; j<no_of_routers; j++)
			{
				if (adjmat[i][j] != 0 && adjmat[i][j] != 100)
				{
					sout.writelnString("");
					String id1="", id2="";
					id1 = getId(i);
					id2 = getId(j);

					sout.writeString(id1 + " ");
					sout.writeString(id2 + " ");
					sout.writeInt(adjmat[i][j]);

					sout.flush();
				}
			}
		}
	} // end of modifyTopologyFile

	// used to change the cost of a link in the Network at run-time
	public void changeLink(String src_id, String dest_id, int cost) throws IOException
	{
		int ind1, ind2;
		if (cost == 100)
		{
			soutput.writelnString(src_id + "-" + dest_id + " link down.");
		}
		else
			soutput.writelnString(src_id + "-" + dest_id + " link changed to " + cost);

		ind1 = getIndex(src_id);
		ind2 = getIndex(dest_id);

		adjmat[ind1][ind2] = cost;
		adjmat[ind2][ind1] = cost;
	
		modifyTopologyFile();
		
		
		if (!isNeighbour(src_id, dest_id))
		{
			// add src as neighbour of dest and vice versa
			Router srcRef = getRouterRef(src_id);
			Router destRef = getRouterRef(dest_id);

			// add dest as neighbour of src
			Neighbour nbr = new Neighbour(dest_id);
			nbr.rtable = new RoutingInfo[no_of_routers];
			if (srcRef.nbrHead == null)
				srcRef.nbrHead = nbr;
			else
				addNeighbour(srcRef.nbrHead, nbr);

			// add src as neighbour of dest			
			nbr = new Neighbour(src_id);
			nbr.rtable = new RoutingInfo[no_of_routers];
			if (destRef.nbrHead == null)
				destRef.nbrHead = nbr;
			else
				addNeighbour(destRef.nbrHead, nbr);
		}


//		if (cost < 100)
//		{
			Router src = getRouterRef(src_id);
			Router dest = getRouterRef(dest_id);

			src.changeLinkCost(ind2, cost);
			dest.changeLinkCost(ind1, cost);
//		}
	} // end of changeLink

	public boolean isNeighbour(String id1, String id2)
	{
		Router r1 = getRouterRef(id1);
		Neighbour nbr = r1.nbrHead;
		while (nbr != null)
		{
			if (nbr.id.equals(id2))
			{
				return true;
			}
			nbr = nbr.next;
		}
		return false;
	} // end of isNeighbour

	// prints the tables of router's neighbours and 
	// table of the router before update and after update
	public void printRoutersInfo()
	{
		Router router = rtrHead;
		while (router != null)
		{
			System.out.println();
			System.out.println("Table of Router : " + router.id);
			System.out.println("Destination id\tInterface Id\tcost");
			for (int i=0; i<no_of_routers; i++)
			{
				System.out.println("       "+router.rtable[i].dest_id + "\t     " + router.rtable[i].interface_id + "\t\t" + router.rtable[i].cost);
			}
			Neighbour nbr = router.nbrHead;
			while (nbr != null)
			{
				System.out.println("Table of Neighbour : " + nbr.id);
				if (nbr.rtable != null)
				{
					System.out.println("Destination id\tInterface Id\tcost");
					for (int i=0; i<no_of_routers; i++)
						System.out.println("       "+nbr.rtable[i].dest_id + "\t     " + nbr.rtable[i].interface_id + "\t\t" + nbr.rtable[i].cost);
				}
				else
					System.out.println("No table found.");
				nbr = nbr.next;
			}
			router = router.next;
		}
	} // printRouterInfo

/*	public void changeTopology() throws IOException
	{
		String id1="",id2="",choice="";
		int cost;
		int ind1,ind2;
		boolean flag = true;
		SimpleInput sin = new SimpleInput();

		while (flag)
		{
			System.out.println("Enter source id, Dest id and cost");
			id1 = sin.readWord();
			id2 = sin.readWord();
			cost = sin.readInt();

			soutput.writelnString(id1 + "-" + id2 + " link changed to " + cost);

			ind1 = getIndex(id1);
			ind2 = getIndex(id2);

			adjmat[ind1][ind2] = cost;
			adjmat[ind2][ind1] = cost;
		
			System.out.println("Enter more? (y/n)");
			choice = sin.readWord();

			if (choice.equals("y") || choice.equals("Y"))
				flag = true;
			else
				flag = false;
		}

		modifyTopologyFile();
	}
*/
	// used to add a new router in the Network at the run-time
	public void addNewRouter() throws IOException
	{
		SimpleInput sin = new SimpleInput();

		no_of_routers++;
		
		// Steps involved....
		// 1) Enter the Router ID
		System.out.print("\n\t\t\tEnter the new Router's ID : ");
		String rtrId = sin.readWord();

		soutput.writelnString("New Router " +rtrId+ " added to the Network.");

		// 2) Add the new Router in the Linked list of Routers
		addRouter(rtrHead, new Router(rtrId));

		// 3) Give index to the new Router and map it using array of Mapper class
		Mapper temp_map[] = new Mapper[no_of_routers];
		for (int i=0; i<no_of_routers; i++)
		{
			// copy previous mapping
			if (i != no_of_routers-1)
				temp_map[i] = new Mapper(map[i].index, map[i].id);
			else // create map for new router
				temp_map[i] =  new Mapper(i, rtrId);
		}
		// assign new map to old map
		map = new Mapper[no_of_routers];
		map = temp_map;

	
		/* 4) Re-construct the Adjacency Matrix.
			  a) Increase the size of the Adjacency Matrix.
			  b) Copy the original matrix into the new matrix.
			  c) Assign cost = 100 to the row and column representing new Router.*/
		int[][] temp_mat = new int[no_of_routers][no_of_routers];
		for (int i=0; i<no_of_routers-1; i++)
			for (int j=0; j<no_of_routers-1; j++)
				temp_mat[i][j] = adjmat[i][j];
		for (int i=0; i<no_of_routers; i++)
		{	
			temp_mat[i][no_of_routers-1] = 100;
			temp_mat[no_of_routers-1][i] = 100;
		}
		// for dest == router itself
		temp_mat[no_of_routers-1][no_of_routers-1] = 0;


		/* 5) Enter the IDs of its neighbours and the cost of the links
			  between the neighbours and the new router. */
		System.out.println("Enter Links between new Router and its neighbours in following format.");
		System.out.println("Enter 0 0 to terminate.");
		System.out.println("Neighbour ID	Cost");
		String nbrId = "";
		int cost = 0;
		Router newRouter = getRouterRef(rtrId);
		while (true)
		{
			nbrId = sin.readWord();
			cost = sin.readInt();
			if (nbrId.equals("0") && cost == 0)
				break;
			// a) Create the linked list of neighbours in the new router
			if (newRouter.nbrHead == null)
				newRouter.nbrHead = new Neighbour(nbrId);
			else
				addNeighbour(newRouter.nbrHead, new Neighbour(nbrId));

			// b) Add the new router in the neighbour's list of its neighbours
			Router nbrRouter = getRouterRef(nbrId);
			if (nbrRouter.nbrHead == null)
				nbrRouter.nbrHead = new Neighbour(rtrId);
			else
				addNeighbour(nbrRouter.nbrHead, new Neighbour(rtrId));

			/* c) Update the new matrix with the new links between the new router
				  and its neighbours. */
			  temp_mat[getIndex(rtrId)][getIndex(nbrId)] = cost;
			  temp_mat[getIndex(nbrId)][getIndex(rtrId)] = cost;
		}
		// assign the new adjacency matrix to old matrix
		adjmat = new int[no_of_routers][no_of_routers];
		adjmat = temp_mat;

		// 8) Update routing tables of all the routers including the new router
		Router currRouter = rtrHead;
		while (currRouter.next != null)
		{
			// a) Increase the size of routing table by 1
			// b) Copy the original routing table into new table
			RoutingInfo newTable[] = new RoutingInfo[no_of_routers];
			for (int i=0; i<no_of_routers-1; i++)
				newTable[i] = new RoutingInfo(currRouter.rtable[i].dest_id, currRouter.rtable[i].interface_id, currRouter.rtable[i].cost);
			/* c) Set the dest_id = rtrId, interface_id = "-" and cost = 100
			      in the last entry of new routing tables. */

			// set cost to reach new router in the neighbouring tables
			// also interface id should be dest_id when there is a direct link
			cost = adjmat[getIndex(currRouter.id)][no_of_routers-1];
			if (cost < 100)
	   			newTable[no_of_routers-1] = new RoutingInfo(rtrId, rtrId, cost);
			else
	   			newTable[no_of_routers-1] = new RoutingInfo(rtrId, "-", 100);
			// replace the old table with new table
			currRouter.rtable = new RoutingInfo[no_of_routers];
			currRouter.rtable = newTable;

			currRouter = currRouter.next;
		}
		// d) For the last router create its table from new matrix.
		currRouter.rtable = new RoutingInfo[no_of_routers];
		int ind_source = getIndex(rtrId);
		for (int i=0; i<no_of_routers; i++)
		{
			String dest_id = getId(i);
			if (adjmat[ind_source][i] < 100)
				currRouter.rtable[i] = new RoutingInfo(dest_id, getId(i), adjmat[ind_source][i]);
			else
				currRouter.rtable[i] = new RoutingInfo(dest_id,"-",adjmat[ind_source][i]);
		}


		// 9) Modify the topology file
		modifyTopologyFile();

	} // end of addNewRouter

} // end of DVRouting class