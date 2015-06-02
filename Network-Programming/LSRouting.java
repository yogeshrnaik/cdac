// change Link working
// delay optimisation achieved

// adding a new router in progress

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
	Neighbour next;
	Neighbour()
	{
		id = "";
		next = null;
	}
	Neighbour(String a_id)
	{
		id = a_id;
		next = null;
	}
}

class Packet
{
	String id;
	int seqno;
	int age;
	NeighbourInfo[] nbrInfo;

	Packet (int no)
	{
		id = "-";
		seqno = 0;
		age = no + 1;
	}
	Packet(String a_id, int a_seq_no, int a_age)
	{
		id = a_id;
		seqno = a_seq_no;
		age = a_age;
	}
}

 class NeighbourInfo 
{
	String nbr_id;
	int delay, bandwidth;

	public NeighbourInfo()
	{
		nbr_id = "";
		delay = 100;
		bandwidth = 0;
	}
	public NeighbourInfo(String a_nbrid, int a_delay, int a_bandwidth)
	{
		nbr_id = a_nbrid;
		delay = a_delay;
		bandwidth = a_bandwidth;
	}
}

class RoutingInfo
{
	String dest_id,interface_id;
	int delay;

	RoutingInfo(String a_dest_id, String a_interface_id, int a_cost)
	{
		dest_id = a_dest_id;
		interface_id = a_interface_id;
		delay = a_cost;
	}
}

class Router
{
	String id;
	int last_seqno;
	RoutingInfo[] rtable;
//	RoutingInfo[] rtable_bandwidth;
	Packet[] pack;
	Neighbour nbrHead;
	Router next;

	Router (String a_id)
	{
		id = a_id;
		last_seqno = 9;
		nbrHead = null;
		rtable = null;
		//rtable_bandwidth = null;
		pack = null;
		next = null;
	}
}

public class LSRouting
{
	int no_of_routers;
	Mapper map[];
	int matdelay[][];
	int matbandwidth[][];

	Router rtrHead;
	SimpleOutput soutput;
	String path, ipfile, opfile;

	String visited[];
	int size;
	int cost;

	LSRouting () throws IOException
	{
		no_of_routers = 0;
		rtrHead = null;
		//path = "C:\\j2sdk1.4.0_02\\bin\\";
		path = "C:\\java\\bin\\";
		ipfile = "lsrip.txt";
		opfile = "lsrop.txt";

		cost = 10000;

		size = 0;
		visited = new String[100];
		for (int i=0; i<100; i++)
		{
			visited[i] = new String("");
		}

/*		// initialise a file object to write output to a file
		File foutput = new File(path + opfile);
		soutput = new SimpleOutput(foutput);*/
	}
	public static void main(String[] args) throws IOException
	{
		LSRouting routing = new LSRouting();
		char choice;
		int option;
		SimpleInput sin = new SimpleInput();

		System.out.println("\t\t\tLINK STATE ROUTING");
		System.out.println("\t\t\t==================\n");

		first:
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
				routing.createRouters("lsrip.txt");
			}
			else if (option == 2)
			{
				System.out.print("\n\n\t\t\tEnter file name : ");
				String filename = sin.readWord();
				routing.createRouters(filename);
			}					
			else if (option >= 3)
				break;
			
			// initialise a file object to write output to a file
			File foutput = new File(routing.path + routing.opfile);
			routing.soutput = new SimpleOutput(foutput);
	
			/*System.out.println("-----------------------------------------------");
			System.out.println("Printing routers after creating.");
			routing.printRouters();
			System.out.println("-----------------------------------------------");*/

			// starting simulation
			int itno = 1;
			while (true)
			{
				// 1) every router will generate its packet
				routing.generatePackets();

				/*System.out.println("---------------------------------------");
				routing.printGeneratedPackets();
				System.out.println("---------------------------------------");*/

				/* 2) send packets of all routers to all other routers
						a) each router sends its packet to its neighbours and 
						b) receives packets of other routers from its neighbours*/
				routing.exchangePackets();
			
				// print info of all packets in each router
				// routing.printAllPackets();

				// update the routing tables of all routers
				routing.updateRoutingTables();

				// print the routing tables in the lsrop.txt file
				routing.printRoutingTables (routing.rtrHead, itno);

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
						case 2 :	System.out.print("\n\t\t\tEnter source id, Dest id and Delay : "); 
									routing.changeLink(sin.readWord(), sin.readWord(), sin.readInt());break;

						// make a link down
						case 3 :	System.out.print("\n\t\t\tEnter source id, Dest id : ");
									routing.changeLink(sin.readWord(), sin.readWord(), 100);break;
						
						// make a link up
						case 4 :	System.out.print("\n\t\t\tEnter source id, Dest id and Delay : ");
									routing.changeLink(sin.readWord(), sin.readWord(), sin.readInt());break;
					}
				}
				else if (option >= 3)
					continue first;
				itno++;
				// reduce age of all packets present in each router before going to next interation
				routing.reduceAge();

			} // inner while
		} // outer while	
	} // main

	public void changeLink (String src_id, String dest_id, int delay) throws IOException
	{
		int ind1, ind2;

		soutput.writelnString(src_id + "-" + dest_id + " link changed to " + delay);

		ind1 = getIndex(src_id);
		ind2 = getIndex(dest_id);

		matdelay[ind1][ind2] = delay;
		matdelay[ind2][ind1] = delay;
	
		//System.out.println("Printing matrices after changing link : "+src_id+"-"+dest_id);
		//printAdjMat();

		modifyTopologyFile();

	} // end of changeLink

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
				if (matdelay[i][j] != 0 && matdelay[i][j] != 100)
				{
					sout.writelnString("");
					String id1="", id2="";
					id1 = getId(i);
					id2 = getId(j);

					sout.writeString(id1 + " ");
					sout.writeString(id2 + " ");
					sout.writeString(matdelay[i][j] + " ");
					sout.writeInt(matbandwidth[i][j]);
					sout.flush();
				}
			}
		}
	} // end of modifyTopologyFile


	public void updateRoutingTables() throws IOException
	{
		Router rtr = rtrHead;
		while (rtr != null)
		{
			for (int i=0; i<no_of_routers; i++)
			{
				cost = 10000;
				size = 1;
				for (int k=0; k<100; k++)
				{
					visited[k] = "";
				}
				visited[0] = rtr.id;

				if (!rtr.id.equals(getId(i)))
				{
					//System.out.println("====================================================");
					//System.out.println("calling getMinimumCost for start="+rtr.id+ " and dest="+getId(i));
					getMinimumCost(rtr.id, rtr.id, getId(i), 0);
				}
				else
				{
					rtr.rtable[getIndex(rtr.id)].delay = 0;
					rtr.rtable[getIndex(rtr.id)].interface_id = "-";
				}
			}
			rtr = rtr.next;
		}
		/*cost = 10000; size = 1; visited[0] = "c";
		getMinimumCost("c", "c", "a", 0);
		printRoutingTables(rtrHead, 1);*/
	}

	// get the cost between immediate neighboours from packet of source
	public int getCost (String source, String dest)
	{
		Router src = getRouterRef(source);
		int ind_src = getIndex(source);
		if (src.pack[ind_src].age > 0)
		{
			int len = src.pack[ind_src].nbrInfo.length;
			for (int i=0; i<len; i++)
			{
				if (src.pack[ind_src].nbrInfo[i].nbr_id.equals(dest))
				{
					return src.pack[ind_src].nbrInfo[i].delay;
				}
			}
		}
//		else
//			return 100;

		return 0;
	}

	public void getMinimumCost (String start, String source, String dest, int c) throws IOException
	{
		Neighbour nbr = getRouterRef(source).nbrHead;
		/*System.out.println("------------------------------------");
		System.out.println("Entering getminimum cost with : ");
		System.out.println("start : "+start+" source : "+ source+" dest : "+dest+" cost : "+c);
		SimpleInput sin = new SimpleInput();*/

		while (nbr != null)
		{
			/*System.out.println(nbr.id + " isVisited = "+ isVisited(nbr.id));
			System.out.println("is start.equals(nbr.id) = " + start.equals(nbr.id));

			//sin.skipWhite();
			//sin.readChar();*/

			if (!isVisited(nbr.id) && !start.equals(nbr.id))
			{
				visited[size++] = nbr.id;
				/*System.out.print("Size of visited array : "+size+" Array = ");
				for (int k=0; k<size; k++)
				{
					System.out.print(visited[k] + " ");
				}
				System.out.println();*/

				// getCost returns the cost between source and dest 
				// assuming that they are immediate neighbours
				int temp_cost = 0;
				//System.out.println("nbrid : " + nbr.id + " dest : " + dest);
				if(nbr.id.equals(dest))
				{
					temp_cost = getCost(source, dest);
					//System.out.println("nbr.id.equals(dest)");
					//System.exit(1);
				}
				else	
				{
					//System.out.println("Before recursive call, source="+source+" and nbr.id="+nbr.id+ " cost between them = "+getCost(source, nbr.id));
					//System.exit(1);
					getMinimumCost (start, nbr.id, dest, c + getCost(source, nbr.id));
				}

				Router startRef = getRouterRef(start);
				if (temp_cost >= 100 && cost >= temp_cost)
				{
					//System.out.println("temp_cost >= 100 thus interface id for "+start+"-"+dest+" = -");
					startRef.rtable[getIndex(dest)].interface_id = "-";
				}
//				else
//					c = c + temp_cost;

				// this means they are immediate neighbours
				//if (temp_cost != 0 && temp_cost != 100)
				if (temp_cost > 0 && temp_cost < 100)
				{
					//System.out.println("temp_cost > 0 && temp_cost < 100");
					//System.out.println("c < cost and cost = "+cost+" and c = "+ (c+temp_cost));
					if (c + temp_cost < cost)
					{
						cost = c + temp_cost;
						// store interface id and cost in the starting router's 
						// routing table for current dest
						if (size == 1)
						{
							//System.out.println("size == 1 and dest = " + dest);
							//System.exit(1);
							//System.out.println("interface id for "+start+"-"+dest+" = "+dest);
							startRef.rtable[getIndex(dest)].interface_id = dest;
						}
						else
						{
							//System.out.println("size != 1 and visited[1] = " + visited[1]);
							//System.out.println("interface id for "+start+"-"+dest+" = "+visited[1]);
							startRef.rtable[getIndex(dest)].interface_id = visited[1];
						}
						//startRef.rtable[getIndex(dest)].delay = cost;
					}
				}
				startRef.rtable[getIndex(dest)].delay = cost;

				if (size != 0)
				{	visited[--size] = "";
				}
				else
					visited[size] = "";
			}
			if (nbr.next != null)
			{	
				//System.out.println("Moving to next neighbour of "+getRouterRef(source).id);
				//System.out.println("------------------------------------");
			}
			else
			{	
				//System.out.println("All neighbours of "+getRouterRef(source).id+" checked.");
				//System.out.println("------------------------------------");
			}
			nbr = nbr.next;
		}
	} // end of getMinimumCost

	public boolean isVisited (String id)
	{
		for (int i=0; i<size; i++)
		{
			if (visited[i].equals(id))
				return true;
		}
		return false;
	}

	public void generatePackets() throws IOException
	{
		Router curr = rtrHead;
		SimpleInput sin = new SimpleInput();

		while (curr != null)
		{
			int index = getIndex(curr.id);
			curr.pack[index] = new Packet(curr.id, ++curr.last_seqno, no_of_routers+1);

			// count no of neighbours
			Neighbour nbr = curr.nbrHead;
			int nbrCount = 0;
			while (nbr != null)
			{
				nbrCount++;
				nbr = nbr.next;
			}
			//System.out.println("Nbr count for router " + curr.id + " = " + nbrCount);
			
			curr.pack[index].nbrInfo = new NeighbourInfo[nbrCount];
			int cnt = 0;
			for (int i=0; i<no_of_routers; i++)
			{
				// set delay even if the link is down between two neighbours
				//if (matdelay[index][i] != 0 && matdelay[index][i] != 100)
				if (isNeighbour(curr.id, getId(i)))
				{
					curr.pack[index].nbrInfo[cnt++] = new NeighbourInfo (getId(i), matdelay[index][i], matbandwidth[index][i]);
				}
			}		
			curr = curr.next;
		}
	} // end of generatePackets

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

	// print packets of each router
	public void printGeneratedPackets() throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Router curr = rtrHead;
		while (curr != null)
		{
			int index = getIndex(curr.id);
			System.out.println("-----------------------------------");
			System.out.println("Packet of router " + curr.id);
			System.out.println("Seq no = " + curr.pack[index].seqno + " Age = " + curr.pack[index].age);
			System.out.println("Neighbour Id\tDelay\tBandwidth");
			for (int i=0; i<curr.pack[index].nbrInfo.length; i++)
			{   
				System.out.println("    " + curr.pack[index].nbrInfo[i].nbr_id + "\t\t" + curr.pack[index].nbrInfo[i].delay +"\t" + curr.pack[index].nbrInfo[i].bandwidth);
			}
			curr = curr.next;
			//sin.skipWhite();
			//sin.readChar();
		}
	} // end of printGeneratedPackets

	public void printAllPackets() throws IOException
	{
		SimpleInput sin = new SimpleInput();
		Router curr = rtrHead;
		while (curr != null)
		{
			int index = getIndex(curr.id);
			System.out.println("--------------------------------------");
			System.out.println("Packets stored in router " + curr.id);
			for (int i=0; i<no_of_routers; i++)
			{	
				System.out.println("Packet no. : " + (i+1));
				System.out.println("Seq no = " + curr.pack[i].seqno + " Age = " + curr.pack[i].age);
				System.out.println("Neighbour Id\tDelay\tBandwidth");
				for (int j=0; j<curr.pack[i].nbrInfo.length; j++)
				{   
					System.out.println("    " + curr.pack[i].nbrInfo[j].nbr_id + "\t\t" + curr.pack[i].nbrInfo[j].delay +"\t" + curr.pack[i].nbrInfo[j].bandwidth);
				}
				System.out.println("---------------------------------------");
				//sin.skipWhite();
				//sin.readChar();
			}
			curr = curr.next;
		}
	} // end of printAllPackets


	public void reduceAge()
	{
		Router rtr = rtrHead;
		while (rtr != null)
		{
			for (int i=0; i<no_of_routers; i++)
			{
				rtr.pack[i].age--;
			}
			rtr = rtr.next;
		}
	} // end of reduceAge

	public void exchangePackets() throws IOException
	{
		Router rtr = rtrHead;
		while (rtr != null)
		{
			Neighbour nbr = rtr.nbrHead;
			while (nbr != null)
			{
				// send packet from router to its neighbours if path exists
				int rtrIndex = getIndex(rtr.id);
				int nbrIndex = getIndex(nbr.id);
				if (matdelay[rtrIndex][nbrIndex] != 0 && matdelay[rtrIndex][nbrIndex] != 100)
				{
					sendPacketFromTo (getRouterRef(rtr.id), getRouterRef(nbr.id), rtr.id, 0);
				}
				nbr = nbr.next;
			}
			rtr = rtr.next;
		}
	} // end of exchangePackets

	public void sendPacketFromTo (Router source, Router dest, String id, int depth) throws IOException
	{
		if (depth > no_of_routers)
		{
			return;
		}

		SimpleInput sin = new SimpleInput();

		// compare info about new and old packet
		int ind_src = getIndex(source.id);	// index of new packet
		int ind_dest = getIndex(dest.id);
		
		// copy all latest packets from source to dest
		for (int no=0; no<no_of_routers; no++)
		{
			if (source.pack[no].seqno > dest.pack[no].seqno || dest.pack[no].age <= 0)
			{
				// copy packet only if
				if (no != ind_dest && !source.pack[no].id.equals(dest.id))
				{
					/*System.out.println("copying packet no " + no +" from "+source.id+" to "+dest.id);
					System.out.println("Packet no : " + no);
					System.out.println("Packet id : "+source.pack[no].id+" seqno = "+ source.pack[no].seqno + " age = " + source.pack[no].age);
					System.out.println("Neighbour info : ");
					System.out.println("Neighbour Id\tDelay\tBandwidth");
					for (int i=0; i<source.pack[no].nbrInfo.length; i++)
					{   
						System.out.println("    " + source.pack[no].nbrInfo[i].nbr_id + "\t\t" + source.pack[no].nbrInfo[i].delay +"\t" + source.pack[no].nbrInfo[i].bandwidth);
					}*/

					//sin.skipWhite();
					//sin.readChar();

					dest.pack[no] = new Packet (source.pack[no].id, source.pack[no].seqno, source.pack[no].age);
					dest.pack[no].nbrInfo = new NeighbourInfo[source.pack[no].nbrInfo.length];
					for (int i=0; i<source.pack[no].nbrInfo.length; i++)
					{
						dest.pack[no].nbrInfo[i] = new NeighbourInfo (source.pack[no].nbrInfo[i].nbr_id, source.pack[no].nbrInfo[i].delay, source.pack[no].nbrInfo[i].bandwidth);
					}
					// reduce the age of received packet
					dest.pack[no].age--;
				}
			}
		}

		// send the packets to the nbrs of dest
		Neighbour nbr = dest.nbrHead;
		while (nbr != null)
		{
			// if path exits
			int rtrIndex = getIndex(dest.id);
			int nbrIndex = getIndex(nbr.id);
			if (matdelay[rtrIndex][nbrIndex] != 0 && matdelay[rtrIndex][nbrIndex] != 100)
			{
				if (!id.equals(nbr.id))
				{
					sendPacketFromTo (getRouterRef(dest.id), getRouterRef(nbr.id), dest.id, depth+1);
				}
			}
			nbr = nbr.next;
		}
	} // sendPacketFromTo

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
			System.out.print("\n\t\t\tEnter id of router " + (i+1) + " : ");
			sout.writelnString(sin.readWord());
		}	
		
		System.out.println("Enter network topology in the following form and 0 0 0 0 to terminate.");
		System.out.println("Router id 1     Router id 2      Delay		Bandwidth");
		while (true)
		{	
			String x,y;
			int delay, bandwidth;
			String choice;
			x = sin.readWord();
			y = sin.readWord();
			delay = sin.readInt();
			bandwidth = sin.readInt();

			if (x.equals("0") && y.equals("0") && delay == 0 && bandwidth == 0)
				break;

			sout.writelnString("");
			sout.writeString(x + " ");
			sout.writeString(y + " ");
			sout.writeString(delay + " ");
			sout.writeInt(bandwidth);
			sout.flush();
		}
	} // end of generateTopologyFile


	// load network topology from file and create routers
	public void createRouters(String filename) throws IOException
	{
		String id1 = "",id2 = "";
		int delay, bandwidth;
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
		
		// create adjacency matrix for delay and bandwidth
		matdelay = new int[no_of_routers][no_of_routers];
		matbandwidth = new int[no_of_routers][no_of_routers];
		for (int i=0; i<no_of_routers; i++)
		{
			matdelay[i][i] = 0;
			matbandwidth[i][i] = 0;
		}

		while (!sin.eof())
		{
			int ind1,ind2;
			id1 = sin.readWord();
			id2 = sin.readWord();
			delay = sin.readInt();
			bandwidth = sin.readInt();

			ind1 = getIndex(id1);	// get index corresponding to router id1
			ind2 = getIndex(id2);	// get index corresponding to router id2

			//System.out.println(ind1 +" " +ind2 +" "+cost);

			matdelay[ind1][ind2] = delay;
			matdelay[ind2][ind1] = delay;

			matbandwidth[ind1][ind2] = bandwidth;
			matbandwidth[ind2][ind1] = bandwidth;

		}
		for (int i=0; i<no_of_routers; i++)
		{
			for (int j=0; j<no_of_routers; j++)
			{
				if (i != j && matdelay[i][j] == 0)
				{
					matdelay[i][j] = 100;
					matbandwidth[i][j] = -100;
				}
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
			//router.rtable_bandwidth = new RoutingInfo[no_of_routers];
			
			// initialise the array of packets for each router
			router.pack = new Packet[no_of_routers];

			for (int i=0;i<no_of_routers;i++)
			{
				id2 = getId(i);
				router.rtable[i] = new RoutingInfo(id2,"-",matdelay[ind_source][i]);
				//router.rtable_bandwidth[i] = new RoutingInfo(id2,"-",matbandwidth[ind_source][i]);
				router.pack[i] = new Packet(no_of_routers);
			}
		}

		// create linked list of neighbours for each router
		for (router=rtrHead; router!=null; router=router.next)
		{
			int temp_index;
			temp_index = getIndex(router.id);

			for (int i=0; i<no_of_routers; i++)
			{
				if (matdelay[temp_index][i] != 0 && matdelay[temp_index][i] != 100)
				{
					Neighbour temp_nb = new Neighbour(getId(i));
					if (router.nbrHead == null)
						router.nbrHead = temp_nb;
					else
						addNeighbour(router.nbrHead, temp_nb);
				}
			}
		}
		//printNeighbours();
	} // end of createRouters

	public void printNeighbours()
	{
		Router rtr = rtrHead;
		while (rtr != null)
		{
			System.out.print("Neighbours of router : " + rtr.id + " are : ");
			Neighbour nbr = rtr.nbrHead;
			while (nbr != null)
			{
				System.out.print(nbr.id + " ");
				nbr = nbr.next;
			}
			System.out.println();
			rtr = rtr.next;
		}
	} // end of printNeighbours

	public void addRouter(Router head, Router temp)
	{
		while (head.next != null)
			head = head.next;
		head.next = temp;
	}
	public void addNeighbour(Neighbour head, Neighbour temp)
	{
		while (head.next!=null)
			head = head.next;
		head.next = temp;
	}

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
		System.out.println("Adjacency Matrix for Delay:");
		for (int i=0;i<no_of_routers;i++)
		{
			for (int j=0;j<no_of_routers;j++)
			{
				System.out.print("\t" + matdelay[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("Adjacency Matrix for Bandwidth:");
		for (int i=0;i<no_of_routers;i++)
		{
			for (int j=0;j<no_of_routers;j++)
			{
				System.out.print("\t" + matbandwidth[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------");
	}

	public void printRouters()
	{
		Router curr = rtrHead;
		System.out.print("Routers are : ");
		while (curr != null)
		{
			System.out.print(curr.id + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	// prints the routing tables of all routers to lsrop.txt after each iteration
	public void printRoutingTables(Router head, int itno) throws IOException
	{
		int i;
		soutput.writelnString("------------------------------Iteration " + itno + "-----------------------------------");
		while (head!=null)
		{
			soutput.writelnString(head.id);
			soutput.writelnString("Destination ID\tInterface ID\tDelay");
			for (i=0;i<no_of_routers;i++)
			{
//				soutput.writelnString("       "+head.rtable[i].dest_id +"\t     "+head.rtable[i].interface_id+"\t\t"+head.rtable[i].cost);
				soutput.writeString("       "+head.rtable[i].dest_id +"\t     "+head.rtable[i].interface_id);
				if (head.rtable[i].delay >= 100)
				{
					soutput.writelnString("\t\tINFINITY");
					//soutput.writelnString("\t\t"+head.rtable[i].delay);
				}
				else
				{
					soutput.writelnString("\t\t"+head.rtable[i].delay);
				}
			}
			soutput.writelnString("");
			head = head.next;
		}
	} // end of printRoutingTables


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

		/* 4) Re-construct the Adjacency Matrix for both delay and bandwidth.
			  a) Increase the size of the Adjacency Matrix.
			  b) Copy the original matrix into the new matrix.
			  c) Assign cost = 100 to the row and column representing new Router.*/
		int[][] temp_matdelay = new int[no_of_routers][no_of_routers];
		int[][] temp_matbandwidth = new int[no_of_routers][no_of_routers];

		for (int i=0; i<no_of_routers-1; i++)
		{
			for (int j=0; j<no_of_routers-1; j++)
			{
				temp_matdelay[i][j] = matdelay[i][j];
				temp_matbandwidth[i][j] = matbandwidth[i][j];
			}
		}
		for (int i=0; i<no_of_routers; i++)
		{	
			temp_matdelay[i][no_of_routers-1] = 100;
			temp_matdelay[no_of_routers-1][i] = 100;
			
			temp_matbandwidth[i][no_of_routers-1] = -1;
			temp_matbandwidth[no_of_routers-1][i] = -1;

		}
		// for dest == router itself
		temp_matdelay[no_of_routers-1][no_of_routers-1] = 0;
		temp_matbandwidth[no_of_routers-1][no_of_routers-1] = 0;
		
		/* 5) Enter the IDs of its neighbours and the cost of the links
			  between the neighbours and the new router. */
		System.out.println("Enter Links between new Router and its neighbours in following format.");
		System.out.println("Enter 0 0 0 to terminate.");
		System.out.println("Neighbour ID	Delay	Bandwidth");
		String nbrId = "";
		int delay, bandwidth;
		Router newRouter = getRouterRef(rtrId);
		while (true)
		{
			nbrId = sin.readWord();
			delay = sin.readInt();
			bandwidth = sin.readInt();
			if (nbrId.equals("0") && delay == 0 && bandwidth == 0)
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
			int rtrIndex = getIndex(rtrId);
			int nbrIndex = getIndex(nbrId);
			temp_matdelay[rtrIndex][nbrIndex] = delay;
			temp_matdelay[nbrIndex][rtrIndex] = delay;
			temp_matbandwidth[rtrIndex][nbrIndex] = bandwidth;
  			temp_matbandwidth[nbrIndex][rtrIndex] = bandwidth;

		}
		// assign the new adjacency matrix to old matrix
		matdelay = new int[no_of_routers][no_of_routers];
		matbandwidth = new int[no_of_routers][no_of_routers];
		matdelay = temp_matdelay;
		matbandwidth = temp_matbandwidth;

		// 8) Update routing tables of all the routers including the new router
		Router currRouter = rtrHead;
		while (currRouter.next != null)
		{
			// a) Increase the size of routing table by 1
			// b) Copy the original routing table into new table
			RoutingInfo newTable[] = new RoutingInfo[no_of_routers];
			for (int i=0; i<no_of_routers-1; i++)
				newTable[i] = new RoutingInfo(currRouter.rtable[i].dest_id, currRouter.rtable[i].interface_id, currRouter.rtable[i].delay);
			/* c) Set the dest_id = rtrId, interface_id = "-" and delay = 100
			      in the last entry of new routing tables. */

			// set cost to reach new router in the neighbouring tables
			// also interface id should be dest_id when there is a direct link
			delay = matdelay[getIndex(currRouter.id)][no_of_routers-1];
			if (delay < 100)
	   			newTable[no_of_routers-1] = new RoutingInfo(rtrId, rtrId, delay);
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
			if (matdelay[ind_source][i] < 100)
				currRouter.rtable[i] = new RoutingInfo(dest_id, getId(i), matdelay[ind_source][i]);
			else
				currRouter.rtable[i] = new RoutingInfo(dest_id,"-",matdelay[ind_source][i]);
		}

		// 9) create new array pack in each router to hold packet from new router
			// copy old info from old pack to new array of packets for each router
		Router rtr = rtrHead;
		while (rtr.next != null)
		{	
			Packet[] newPack = new Packet[no_of_routers];
			for (int i=0;i<no_of_routers;i++)
			{
				if (i != no_of_routers-1)
				{	
					// copy info from old pack
					newPack[i] = new Packet(rtr.pack[i].id, rtr.pack[i].seqno, rtr.pack[i].age);
					// copy neighbour info from old pack
					int nbrCount = rtr.pack[i].nbrInfo.length;
					newPack[i].nbrInfo = new NeighbourInfo[nbrCount];
					for (int j=0; j<nbrCount; j++)
					{
						newPack[i].nbrInfo[j] = new NeighbourInfo(rtr.pack[i].nbrInfo[j].nbr_id, rtr.pack[i].nbrInfo[j].delay, rtr.pack[i].nbrInfo[j].bandwidth);
					}
				}
				else
				{
					newPack[i] = new Packet(rtrId, 9, no_of_routers);
					// count no of neighbours of new router
					int nbrCount = getNbrCount(rtrId);
					newPack[i].nbrInfo = new NeighbourInfo[nbrCount];
				}
			}
			// assign new pack to old pack ref
			rtr.pack = new Packet[no_of_routers];
			rtr.pack = newPack;

			rtr = rtr.next;
		} // end of while loop

		// create pack for new router
		rtr.pack = new Packet[no_of_routers];
		for (int i=0;i<no_of_routers;i++)
		{
			String dest_id = getId(i);
			rtr.pack[i] = new Packet(no_of_routers);
			rtr.pack[i].nbrInfo = new NeighbourInfo[getNbrCount(dest_id)];
		}


		// 10) Modify the topology file
		modifyTopologyFile();

	} // end of addNewRouter

	public int getNbrCount(String id)
	{
		Router rtr = getRouterRef(id);
		Neighbour nbr = rtr.nbrHead;
		int nbrCount = 0;
		while (nbr != null)
		{
			nbrCount++;
			nbr = nbr.next;
		}
		return nbrCount;
	}

} // end of LSRouting