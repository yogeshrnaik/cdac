
import java.awt.*;

/**
  * GraphAlgorithm.java
  *
  * @author: Carla Laffra
  * @date:   March, 1996
  *
  * Applet for explaining graph algorithms. 
  * It currently only explains Dijkstra's shortest path algorithm, 
  * but it can easily be extended to handle more algorithms.
  *
  * Copyright 1996, Carla Laffra, all rights reserved
  */


public class GraphAlgorithm extends java.applet.Applet {

    GraphCanvas graphcanvas = new GraphCanvas(this);
    Options options = new Options(this);   
    Documentation documentation = new Documentation();

    public void init() {
	setLayout(new BorderLayout(10, 10));
	add("Center", graphcanvas);
	add("North", documentation);
	add("East", options);
    }

    public Insets insets() {
	return new Insets(10, 10, 10, 10);
    }

    public void lock() {
	graphcanvas.lock();
	options.lock();
    } 

    public void unlock() {
	graphcanvas.unlock();
	options.unlock();
    } 

    public static void main(String arg[]){
        GraphAlgorithm g = new GraphAlgorithm();
	Frame frame = new Frame("Dijkstra");
        g.init();
        frame.add("Center", g);
        frame.resize(800,600);
        frame.show();
        frame.resize(800,600);
    }
}


class Options extends Panel {
// set of options at the right of the screen
    Button b1 = new Button("clear");
    Button b2 = new Button("run");
    Button b3 = new Button("step");
    Button b4 = new Button("reset");
    Button b5 = new Button("example");
    Button b6 = new Button("exit");
    GraphAlgorithm parent;   
    boolean Locked=false;
  
    Options(GraphAlgorithm myparent) {
	parent = myparent;
	setLayout(new GridLayout(6, 1, 0, 10));
	add(b1);
	add(b2);
	add(b3);
	add(b4);
	add(b5); 
	add(b6);
    }

    public boolean action(Event evt, Object arg) {
	if (evt.target instanceof Button) {
	  if (((String)arg).equals("step")) {
	     if (!Locked) {
	        b3.setLabel("next step");
	        parent.graphcanvas.stepalg();
	     }
	     else parent.documentation.doctext.showline("locked");
	  }
	  if (((String)arg).equals("next step")) 
	     parent.graphcanvas.nextstep();
	  if (((String)arg).equals("reset")) { 
	     parent.graphcanvas.reset();
	     b3.setLabel("step");
	     parent.documentation.doctext.showline("all items");
	  }
	  if (((String)arg).equals("clear")) { 
	     parent.graphcanvas.clear();
	     b3.setLabel("step");
	     parent.documentation.doctext.showline("all items");
	  }
	  if (((String)arg).equals("run")) {
	     if (!Locked)  
	        parent.graphcanvas.runalg();
	     else parent.documentation.doctext.showline("locked");
	  }
	  if (((String)arg).equals("example")) {
	     if (!Locked)   
	        parent.graphcanvas.showexample();
	     else parent.documentation.doctext.showline("locked");
	  } 
	  if (((String)arg).equals("exit")) { 
	     System.exit(0);
	  } 
	}                   
	return true; 
    }
    
    public void lock() {
	Locked=true;
    }

    public void unlock() {
	Locked=false;
	b3.setLabel("step"); 
    } 
}    


class Documentation extends Panel {
// Documentation on top of the screen
    DocOptions docopt = new DocOptions(this);
    DocText doctext = new DocText();

    Documentation() {
	setLayout(new BorderLayout(10, 10));
	add("West", docopt);
	add("Center", doctext);
    }
}


class DocOptions extends Panel {
    Choice doc = new Choice();
    Documentation parent;   
   
    DocOptions(Documentation myparent) {
	setLayout(new GridLayout(2, 1, 5, 0));
	parent = myparent;
	add(new Label("DOCUMENTATION:"));
	doc.addItem("draw nodes");
	doc.addItem("remove nodes"); 
	doc.addItem("move nodes");
	doc.addItem("the startnode");
	doc.addItem("draw arrows"); 
	doc.addItem("change weights");
	doc.addItem("remove arrows");
	doc.addItem("clear / reset"); 
	doc.addItem("run algorithm");
	doc.addItem("step through");
	doc.addItem("example"); 
	doc.addItem("exit"); 
	doc.addItem("all items");
	add(doc);
    }
  
    public boolean action(Event evt, Object arg) {
        if (evt.target instanceof Choice) {
	    String str=new String(doc.getSelectedItem());
	    parent.doctext.showline(str);  
        }             
       	return true;
    }
}


class DocText extends TextArea {
    final String drawnodes = new String("DRAWING NODES:\n"+
	  "Draw a node by clicking the mouse.\n\n");
    final String rmvnodes = new String("REMOVE NODES:\n"+
	  "To remove a node press <ctrl> and click on the node.\n"+
	  "You can not remove the startnode.\n"+
	  "Select another startnode, then you can remove the node.\n\n");
    final String mvnodes = new String("MOVING NODES\n"+
	  "To move a node press <Shift>, click on the node,\nand drag it to"+
	  " its new position.\n\n");
    final String startnode = new String("STARTNODE:\n"+
	  "The startnode is blue, other nodes are grey."+ 
	  "The first node you draw on the screen will be the startnode.\n"+
	  "To select another startnode press <ctrl>, click on the startnode, "+
	  "and drag the mouse to another node.\n"+
	  "To delete the startnode, first select another startnode, and then"+
	  "remove the node the usual way.\n\n"); 
    final String drawarrows = new String("DRAWING ARROWS:\n"+
	  "To draw an arrow click mouse in a node,"+
	  "and drag it to another node.\n\n");
    final String weight = new String("CHANGING WEIGHTS:\n"+
	  "To change the weight of an arrow, click on the arrowhead and drag\n"+
	  "it along the arrow.\n\n");
    final String rmvarrows = new String("REMOVE ARROWS:\n"+
	  "To remove an arrow, change its weight to 0.\n\n");
    final String clrreset = new String("<CLEAR> BUTTON: "+
	  "Remove the current graph from the screen.\n"+
	  "<RESET> BUTTON: "+
	  "Remove the results of the algorithm from the graph,\n"+
	  " and unlock screen.\n\n");
    final String runalg = new String("<RUN> BUTTON: "+
	  "Run the algorithm on the graph, there will be a time\n" +
	  "delay of +/- 1 second between steps.\n"+
	  "To run the algorithm slower, use <STEP>.\n");
    final String step = new String("<STEP> BUTTON: " +
	  "An opportunity to step through the algorithm.\n");
    final String example = new String("<EXAMPLE> BUTTON: "+
	  "Displays a graph on the screen for you.\n"+
	  "If wanted, the graph can be edited before you "+
	  "<STEP> or <RUN> through the animation of the algorithm.\n");
    final String exitbutton = new String("<EXIT> BUTTON: " +
	  "Only works if applet is run with appletviewer.\n");
    final String toclose = new String("ERROR: "+
	  "This position is too close to another node/arrow.\n");
    final String done = new String("Algorithm has finished, " +
	  "follow orange arrows from startnode to any node "+
	  "to get\nthe shortest path to " +
	  "the node. The length of the path is written in the node.\n" +
          "press <RESET> to reset the graph, and unlock the screen.");
    final String some = new String("Algorithm has finished, " +
	  "follow orange arrows from startnode to any node "+
	  "to get\nthe shortest path to " +
	  "the node. The length of the path is written in the node.\n" +
          "There are no paths from the startnode to any gray node.\n" +
          "press <RESET> to reset the graph, and unlock the screen.");
    final String none = new String("Algorithm has finished, " +
	  "there are no nodes reachable from the start node.\n"+
	  "press <RESET> to reset the graph, and unlock the screen.");
  
    final String maxnodes = new String("ERROR: "+ 
	  "Maximum number of nodes reached!\n\n");
    final String info = new String("DOCUMENTATION:\n"+
	  "You can scroll through the documentation or get documentation\n"+
	  "on a specific "+
	  "item by selecting the item on the left.\nSelecting <All items> "+
	  "brings you back "+
	  " to the scrolling text.\n\n"); 
    final String locked = new String("ERROR: "+
	  "Keyboard/mouse locked for this action.\n"+
	  "Either press <NEXT STEP> or <RESET>.\n"); 

    final String doc = info + drawnodes + rmvnodes + mvnodes + 
		       startnode + drawarrows + weight + rmvarrows + 
		       clrreset + runalg + step + example + exitbutton;

    DocText() {
	super(5, 2);
	setText(doc);
    }
    
    public void showline(String str) {
	if (str.equals("draw nodes"))              setText(drawnodes);
	else if (str.equals("remove nodes"))       setText(rmvnodes);
	else if (str.equals("move nodes"))         setText(mvnodes);
	else if (str.equals("the startnode"))      setText(startnode);
	else if (str.equals("draw arrows"))        setText(drawarrows);
	else if (str.equals("change weights"))     setText(weight);
	else if (str.equals("remove arrows"))      setText(rmvarrows);
	else if (str.equals("clear / reset"))      setText(clrreset);
	else if (str.equals("run algorithm"))      setText(runalg);
	else if (str.equals("step through"))       setText(step);
	else if (str.equals("example"))            setText(example); 
        else if (str.equals("exit"))               setText(exitbutton);
	else if (str.equals("all items"))          setText(doc);
	else if (str.equals("toclose"))            setText(toclose); 
	else if (str.equals("done"))               setText(done);   
	else if (str.equals("locked"))             setText(locked);
	else if (str.equals("maxnodes"))           setText(maxnodes);       
        else if (str.equals("none"))               setText(none);   
        else if (str.equals("some"))               setText(some);   
	else setText(str);
    }
}


class GraphCanvas extends Canvas implements Runnable {
// drawing area for the graph
    
    final int MAXNODES = 20;
    final int MAX = MAXNODES+1;
    final int NODESIZE = 26;
    final int NODERADIX = 13;
    final int DIJKSTRA = 1;
 
    // basic graph information
    Point node[] = new Point[MAX];          // node
    int weight[][] = new int[MAX][MAX];     // weight of arrow
    Point arrow[][] = new Point[MAX][MAX];  // current position of arrowhead
    Point startp[][] = new Point[MAX][MAX]; // start and
    Point endp[][] = new Point[MAX][MAX];   // endpoint of arrow
    float dir_x[][] = new float[MAX][MAX];  // direction of arrow
    float dir_y[][] = new float[MAX][MAX];  // direction of arrow
   
    // graph information while running algorithm
    boolean algedge[][] = new boolean[MAX][MAX];
    int dist[] = new int[MAX];
    int finaldist[] = new int[MAX];
    Color colornode[] = new Color[MAX];
    boolean changed[] = new boolean[MAX];   // indicates distance change during algorithm   
    int numchanged =0; 
    int neighbours=0;
    
    int step=0;
    
    // information used by the algorithm to find the next 
    // node with minimum distance
    int mindist, minnode, minstart, minend;

    int numnodes=0;      // number of nodes
    int emptyspots=0;    // empty spots in array node[] (due to node deletion)
    int startgraph=0;    // start of graph
    int hitnode;         // mouse clicked on or close to this node
    int node1, node2;    // numbers of nodes involved in current action

    Point thispoint=new Point(0,0); // current mouseposition
    Point oldpoint=new Point(0, 0); // previous position of node being moved

    // current action
    boolean newarrow = false;
    boolean movearrow = false;
    boolean movestart = false;
    boolean deletenode = false;
    boolean movenode = false;
    boolean performalg = false;
    boolean clicked = false;

    // fonts
    Font roman= new Font("TimesRoman", Font.BOLD, 12);
    Font helvetica= new Font("Helvetica", Font.BOLD, 15);
    FontMetrics fmetrics = getFontMetrics(roman);
    int h = (int)fmetrics.getHeight()/3;

    // for double buffering
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private Dimension offScreenSize;


    // for run option
    Thread algrthm;

    // current algorithm, (in case more algorithms are added)
    int algorithm;

    // algorithm information to be displayed in documetation panel
    String showstring = new String("");

    boolean stepthrough=false;

    // locking the screen while running the algorithm
    boolean Locked = false;

    GraphAlgorithm parent;

    GraphCanvas(GraphAlgorithm myparent) {
	parent = myparent;
	init();
	algorithm=DIJKSTRA;
	setBackground(Color.white);
    }

    public void lock() {
    // lock screen while running an algorithm
	Locked=true;
    }

    public void unlock() {
	Locked=false;
    }

    public void start() {
	if (algrthm != null) algrthm.resume();
    }

    public void init() {
	for (int i=0;i<MAXNODES;i++) {
	  colornode[i]=Color.gray;
	  for (int j=0; j<MAXNODES;j++)
	      algedge[i][j]=false;
	}
	colornode[startgraph]=Color.blue;
	performalg = false;
    }

    public void clear() {
    // removes graph from screen
	startgraph=0;
	numnodes=0;
	emptyspots=0;
	init();
	for(int i=0; i<MAXNODES; i++) {
	  node[i]=new Point(0, 0);
	  for (int j=0; j<MAXNODES;j++)
	      weight[i][j]=0;
	}
	if (algrthm != null) algrthm.stop();
	parent.unlock();
	repaint();
    }

    public void reset() {
    // resets a graph after running an algorithm
	init();
	if (algrthm != null) algrthm.stop();
	parent.unlock();
	repaint();
    }

    public void runalg() {
    // gives an animation of the algorithm
	parent.lock();
	initalg();
	performalg = true;
	algrthm = new Thread(this);
	algrthm.start();
    }

    public void stepalg() {
    // lets you step through the algorithm
	parent.lock();
	initalg();
	performalg = true;
	nextstep();
    }

    public void initalg() {
	init();
	for(int i=0; i<MAXNODES; i++) {
	  dist[i]=-1;
	  finaldist[i]=-1;
	  for (int j=0; j<MAXNODES;j++)
              algedge[i][j]=false;
	}
        dist[startgraph]=0;
	finaldist[startgraph]=0;
	step=0;
    }

    public void nextstep() {
    // calculates a step in the algorithm (finds a shortest 
    // path to a next node).
        finaldist[minend]=mindist;
	algedge[minstart][minend]=true;
	colornode[minend]=Color.orange;
        // build more information to display on documentation panel
	step++;
	repaint();
    }

    public void stop() {
	if (algrthm != null) algrthm.suspend();
    }

    public void run() {
	for(int i=0; i<(numnodes-emptyspots); i++){
	  nextstep();
	  try { algrthm.sleep(2000); }
	  catch (InterruptedException e) {}
	}
	algrthm = null;
    }

    public void showexample() {
    // draws a graph on the screen
	int w, h;
	clear();
	init();
	numnodes=10;
	emptyspots=0;
	for(int i=0; i<MAXNODES; i++) {
	  node[i]=new Point(0, 0);
	  for (int j=0; j<MAXNODES;j++)
	      weight[i][j]=0;
	}
	w=this.size().width/8;
	h=this.size().height/8;
	node[0]=new Point(w, h);     node[1]=new Point(3*w, h);   
	node[2]=new Point(5*w, h);   node[3]=new Point(w, 4*h); 
	node[4]=new Point(3*w, 4*h); node[5]=new Point(5*w, 4*h);
	node[6]=new Point(w, 7*h);   node[7]=new Point(3*w, 7*h); 
	node[8]=new Point(5*w, 7*h); node[9]=new Point(7*w, 4*h);

	weight[0][1]=4;  weight[0][3]=85;
	weight[1][0]=74; weight[1][2]=18; weight[1][4]=12;
	weight[2][5]=74; weight[2][1]=12; weight[2][9]=12;
	weight[3][4]=32; weight[3][6]=38;
	weight[4][3]=66; weight[4][5]=76; weight[4][7]=33;
	weight[5][8]=11; weight[5][9]=21;
	weight[6][7]=10; weight[6][3]=12;
	weight[7][6]=2;  weight[7][8]=72;
	weight[8][5]=31; weight[8][9]=78; weight[8][7]=18;
	weight[9][5]=8;

	for (int i=0;i<numnodes;i++)
	   for (int j=0;j<numnodes;j++)
	      if (weight[i][j]>0)
	         arrowupdate(i, j, weight[i][j]);

	repaint();
    }

    public boolean mouseDown(Event evt, int x, int y) {
	
	if (Locked)
	    parent.documentation.doctext.showline("locked");
	else {
	  clicked = true;
	  if (evt.shiftDown()) {
	  // move a node
	     if (nodehit(x, y, NODESIZE)) {
	        oldpoint = node[hitnode];
	        node1 = hitnode;
	        movenode=true;
	     }
	  }
	  else if (evt.controlDown()) {
	  // delete a node
	     if (nodehit(x, y, NODESIZE)) {
	        node1 = hitnode;
	        if (startgraph==node1) {
	           movestart=true;
	           thispoint = new Point(x,y);
                   colornode[startgraph]=Color.gray;
	        }
	        else
	           deletenode= true;
	     }
	  }
	  else if (arrowhit(x, y, 5)) {
	  // change weihgt of an edge
	     movearrow = true;
	     repaint();
	  }
	  else if (nodehit(x, y, NODESIZE)) {
	  // draw a new arrow
	     if (!newarrow) {
	        newarrow = true;
	        thispoint = new Point(x, y);
	        node1 = hitnode;
	     }
	   }
	   else if ( !nodehit(x, y, 50) && !arrowhit(x, y, 50) )  {
	   // draw new node
	      if (emptyspots==0) {
	      // take the next available spot in the array
	         if (numnodes < MAXNODES)
	            node[numnodes++]=new Point(x, y);
	         else  parent.documentation.doctext.showline("maxnodes");
	      }
	      else {
	      // take an empty spot in the array (from previously deleted node)
	         int i;
	         for (i=0;i<numnodes;i++)
	            if (node[i].x==-100) break;
	         node[i]=new Point(x, y);
	         emptyspots--;
	      }
	   }
	   else
	   // mouseclick too close to a point or arrowhead, so probably an error
	      parent.documentation.doctext.showline("toclose");
	   repaint();
	}
	return true;
    }

    public boolean mouseDrag(Event evt, int x, int y) {
	if ( (!Locked) && clicked ) {
	   if (movenode) {
	   // move node and adjust arrows coming into/outof the node
	      node[node1]=new Point(x, y);
	      for (int i=0;i<numnodes;i++) {
	         if (weight[i][node1]>0) {
	            arrowupdate(i, node1, weight[i][node1]);
	         }
	         if (weight[node1][i]>0) {
	            arrowupdate(node1, i, weight[node1][i]);
	         }
	      }
	      repaint();
	   }
	   else if (movestart || newarrow) {
	      thispoint = new Point(x, y);
	      repaint();
	   }
	   else if (movearrow) {
	      changeweight(x, y);
	      repaint();
	   }
	}
	return true;
    }


    public boolean mouseUp(Event evt, int x, int y) {
	if ( (!Locked) && clicked ) {
	   if (movenode) {
	   // move the node if the new position is not too close to 
	   // another node or outside of the panel
	      node[node1]=new Point(0, 0);
	      if ( nodehit(x, y, 50) || (x<0) || (x>this.size().width) || 
					  (y<0) || (y>this.size().height) ) {
	         node[node1]=oldpoint;
	         parent.documentation.doctext.showline("toclose");
	      }
	      else node[node1]=new Point(x, y);
	      for (int i=0;i<numnodes;i++) {
	         if (weight[i][node1]>0)
	            arrowupdate(i, node1, weight[i][node1]);
	         if (weight[node1][i]>0) 
	            arrowupdate(node1, i, weight[node1][i]);
	      }
	      movenode=false;
	   }
	   else if (deletenode) {
	      nodedelete();
	      deletenode=false;
	   }
	   else if (newarrow) {
	      newarrow = false;
	      if (nodehit(x, y, NODESIZE)) {
	         node2=hitnode;
	         if (node1!=node2) {
	            arrowupdate(node1, node2, 50);
	            if (weight[node2][node1]>0) {
	               arrowupdate(node2, node1, weight[node2][node1]);
	            }
	            parent.documentation.doctext.showline("change weights");
	         }
	         else System.out.println("zelfde");
	      }
	   }
	   else if (movearrow) {
	      movearrow = false;
	      if (weight[node1][node2]>0)
	         changeweight(x, y);
	   }
	   else if (movestart) {
	   // if new position is a node, this node becomes the startnode
	      if (nodehit(x, y, NODESIZE))
	         startgraph=hitnode;
	      colornode[startgraph]=Color.blue;
	      movestart=false;
	   }
	   repaint();
	}
	return true;
    }

    public boolean nodehit(int x, int y, int dist) {
    // checks if you hit a node with your mouseclick
	for (int i=0; i<numnodes; i++)
	  if ( (x-node[i].x)*(x-node[i].x) + 
				(y-node[i].y)*(y-node[i].y) < dist*dist ) {
	     hitnode = i;
	     return true;
	  }
	return false;
    }

    public boolean arrowhit(int x, int y, int dist) {
    // checks if you hit an arrow with your mouseclick
	for (int i=0; i<numnodes; i++)
	  for (int j=0; j<numnodes; j++) {
	     if ( ( weight[i][j]>0 ) && 
			(Math.pow(x-arrow[i][j].x, 2) + 
			 Math.pow(y-arrow[i][j].y, 2) < Math.pow(dist, 2) ) ) {
	        node1 = i;
	        node2 = j;
	        return true;
	     }
	  }
	return false;
    }

    public void nodedelete() {
    // delete a node and the arrows coming into/outof the node
	node[node1]=new Point(-100, -100);
	for (int j=0;j<numnodes;j++) {
	   weight[node1][j]=0;
	   weight[j][node1]=0;
	}
	emptyspots++;
    }

    public void changeweight(int x, int y) {
    // changes the weight of an arrow (edge in the graph), when a user drags 
    // the arrowhead over the edge connecting node node1 and node2.

	// direction of the arrow
	int diff_x = (int)(20*dir_x[node1][node2]);
	int diff_y = (int)(20*dir_y[node1][node2]);

	// depending on the arrow direction, follow the x, or the y
	// position of the mouse while the arrowhead is being dragged
	boolean follow_x=false;
	   if (Math.abs(endp[node1][node2].x-startp[node1][node2].x) >
		     Math.abs(endp[node1][node2].y-startp[node1][node2].y) ) {
	   follow_x = true;
	}

	// find the new position of the arrowhead, and calculate 
	// the corresponding weight
	if (follow_x) {
	    int hbound = Math.max(startp[node1][node2].x, 
				  endp[node1][node2].x)-Math.abs(diff_x);
	    int lbound = Math.min(startp[node1][node2].x, 
				  endp[node1][node2].x)+Math.abs(diff_x);

	    // arrow must stay between the nodes
	    if (x<lbound) { arrow[node1][node2].x=lbound; }
	    else if (x>hbound) { arrow[node1][node2].x=hbound; }
	    else arrow[node1][node2].x=x;

	    arrow[node1][node2].y=
		(arrow[node1][node2].x-startp[node1][node2].x) *
		    (endp[node1][node2].y-startp[node1][node2].y)/
		        (endp[node1][node2].x-startp[node1][node2].x) + 
		startp[node1][node2].y;

	    // new weight
	    weight[node1][node2]=
		Math.abs(arrow[node1][node2].x-startp[node1][node2].x-diff_x)*
			100/(hbound-lbound);
	}
	// do the same if you follow y
	else {
	    int hbound = Math.max(startp[node1][node2].y, 
			 	  endp[node1][node2].y)-Math.abs(diff_y);
	    int lbound = Math.min(startp[node1][node2].y, 
			 	  endp[node1][node2].y)+Math.abs(diff_y);

	    if (y<lbound) { arrow[node1][node2].y=lbound; }
	    else if (y>hbound) { arrow[node1][node2].y=hbound; }
	    else arrow[node1][node2].y=y;
	    arrow[node1][node2].x=
		(arrow[node1][node2].y-startp[node1][node2].y) *
		    (endp[node1][node2].x-startp[node1][node2].x)/
			(endp[node1][node2].y-startp[node1][node2].y) + 
		startp[node1][node2].x;

	    weight[node1][node2]=
		Math.abs(arrow[node1][node2].y-startp[node1][node2].y-diff_y)*
			100/(hbound-lbound);
	}
    }

    public void arrowupdate(int p1, int p2, int w) {
    // make a new arrow from node p1 to p2 with weight w, or change
    // the weight of the existing arrow to w, calculate the resulting 
    // position of the arrowhead
	int dx, dy;
	float l;
	weight[p1][p2]=w;

	// direction line between p1 and p2
	dx = node[p2].x-node[p1].x;
	dy = node[p2].y-node[p1].y;

	// distance between p1 and p2
	l = (float)( Math.sqrt((float)(dx*dx + dy*dy)));
	dir_x[p1][p2]=dx/l;
	dir_y[p1][p2]=dy/l;

	// calculate the start and endpoints of the arrow,
	// adjust startpoints if there also is an arrow from p2 to p1
	if (weight[p2][p1]>0) {
	   startp[p1][p2] = new Point((int)(node[p1].x-5*dir_y[p1][p2]), 
				      (int)(node[p1].y+5*dir_x[p1][p2]));
	   endp[p1][p2] = new Point((int)(node[p2].x-5*dir_y[p1][p2]), 
				    (int)(node[p2].y+5*dir_x[p1][p2]));
	}
	else {
	   startp[p1][p2] = new Point(node[p1].x, node[p1].y);
	   endp[p1][p2] = new Point(node[p2].x, node[p2].y);
	}

	// range for arrowhead is not all the way to the start/endpoints
	int diff_x = (int)(Math.abs(20*dir_x[p1][p2]));
	int diff_y = (int)(Math.abs(20*dir_y[p1][p2]));

	// calculate new x-position arrowhead
	if (startp[p1][p2].x>endp[p1][p2].x) {
	   arrow[p1][p2] = new Point(endp[p1][p2].x + diff_x +
		(Math.abs(endp[p1][p2].x-startp[p1][p2].x) - 2*diff_x )*
			(100-w)/100 , 0);
	}
	else {
	   arrow[p1][p2] = new Point(startp[p1][p2].x + diff_x +
		(Math.abs(endp[p1][p2].x-startp[p1][p2].x) - 2*diff_x )*
			w/100, 0);
	}

	// calculate new y-position arrowhead
	if (startp[p1][p2].y>endp[p1][p2].y) {
	   arrow[p1][p2].y=endp[p1][p2].y + diff_y +
		(Math.abs(endp[p1][p2].y-startp[p1][p2].y) - 2*diff_y )*
			(100-w)/100;
	}
	else {
	   arrow[p1][p2].y=startp[p1][p2].y + diff_y +
		(Math.abs(endp[p1][p2].y-startp[p1][p2].y) - 2*diff_y )*
			w/100;
	}
    }


    public String intToString(int i) {
	char c=(char)((int)'a'+i);
	return ""+c;
    }

    public final synchronized void update(Graphics g) {
    // prepare new image offscreen
	Dimension d=size();
	if ((offScreenImage == null) || (d.width != offScreenSize.width) ||
			(d.height != offScreenSize.height)) {
	    offScreenImage = createImage(d.width, d.height);
	    offScreenSize = d;
	    offScreenGraphics = offScreenImage.getGraphics();
	}
	offScreenGraphics.setColor(Color.white);
	offScreenGraphics.fillRect(0, 0, d.width, d.height);
	paint(offScreenGraphics);
	g.drawImage(offScreenImage, 0, 0, null);
    }

    public void drawarrow(Graphics g, int i, int j) {
    // draw arrow between node i and node j
	int x1, x2, x3, y1, y2, y3;

	// calculate arrowhead
	x1= (int)(arrow[i][j].x - 3*dir_x[i][j] + 6*dir_y[i][j]);
	x2= (int)(arrow[i][j].x - 3*dir_x[i][j] - 6*dir_y[i][j]);
	x3= (int)(arrow[i][j].x + 6*dir_x[i][j]);

	y1= (int)(arrow[i][j].y - 3*dir_y[i][j] - 6*dir_x[i][j]);
	y2= (int)(arrow[i][j].y - 3*dir_y[i][j] + 6*dir_x[i][j]);
	y3= (int)(arrow[i][j].y + 6*dir_y[i][j]);

	int arrowhead_x[] = { x1, x2, x3, x1 };
	int arrowhead_y[] = { y1, y2, y3, y1 };

	// if edge already chosen by algorithm change color
	if (algedge[i][j]) g.setColor(Color.orange);
	// draw arrow
	g.drawLine(startp[i][j].x, startp[i][j].y, endp[i][j].x, endp[i][j].y);
	g.fillPolygon(arrowhead_x, arrowhead_y, 4);

	// write weight of arrow at an appropriate position
	int dx = (int)(Math.abs(7*dir_y[i][j]));
	int dy = (int)(Math.abs(7*dir_x[i][j]));
	String str = new String("" + weight[i][j]);
	g.setColor(Color.black);
	if ((startp[i][j].x>endp[i][j].x) && (startp[i][j].y>=endp[i][j].y))
	  g.drawString( str, arrow[i][j].x + dx, arrow[i][j].y - dy);
	if ((startp[i][j].x>=endp[i][j].x) && (startp[i][j].y<endp[i][j].y))
	  g.drawString( str, arrow[i][j].x - fmetrics.stringWidth(str) - dx , 
			     arrow[i][j].y - dy);
	if ((startp[i][j].x<endp[i][j].x) && (startp[i][j].y<=endp[i][j].y))
	  g.drawString( str, arrow[i][j].x - fmetrics.stringWidth(str) , 
			     arrow[i][j].y + fmetrics.getHeight());
	if ((startp[i][j].x<=endp[i][j].x) && (startp[i][j].y>endp[i][j].y))
	    g.drawString( str, arrow[i][j].x + dx, 
			       arrow[i][j].y + fmetrics.getHeight() );
    }


    public void detailsDijkstra(Graphics g, int i, int j) {
    // check arrow between node i and node j is amongst the arrows to
    // choose from during this step of the algorithm
    // check if node j has the next minimal distance to the startnode
	if ( (finaldist[i]!=-1) && (finaldist[j]==-1) ) {
	  g.setColor(Color.red);
	  if ( (dist[j]==-1) || (dist[j]>=(dist[i]+weight[i][j])) ) {
             if ( (dist[i]+weight[i][j])<dist[j] ) {
                 changed[j]=true;
                 numchanged++;
             }
	     dist[j] = dist[i]+weight[i][j];
	     colornode[j]=Color.red;             
	     if ( (mindist==0) || (dist[j]<mindist) ) {
	        mindist=dist[j];
	        minstart=i;
	        minend=j;
	     }
	  }
	}
	else g.setColor(Color.gray);
    }

    public void endstepDijkstra(Graphics g) {
    // displays current and final distances of nodes, sets the final distance
    // for the node that had minimal distance in this step
    // explains algorithm on documentation panel
        for (int i=0; i<numnodes; i++)
	  if ( (node[i].x>0) && (dist[i]!=-1) ) {
	     String str = new String(""+dist[i]);
	     g.drawString(str, node[i].x - (int)fmetrics.stringWidth(str)/2 -1, 
			       node[i].y + h);
	     // string to distance to nodes on the documentation panel
	     if (finaldist[i]==-1) {
	        neighbours++;  
	        if (neighbours!=1)
	           showstring = showstring + ", ";
	        showstring = showstring + intToString(i) +"=" + dist[i];
	     } 
	  }
        showstring = showstring + ". ";
       
        if ( (step>1) && (numchanged>0) ) {
           if (numchanged>1)
              showstring = showstring + "Notice that the distances to ";
           else showstring = showstring + "Notice that the distance to ";
           for (int i=0; i<numnodes; i++)
              if ( changed[i] )
                 showstring = showstring + intToString(i) +", ";
           if (numchanged>1)
              showstring = showstring + "have changed!\n";
           else showstring = showstring + "has changed!\n";
        }
        else showstring = showstring + " "; 
	if (neighbours>1) { 
	// if there where more canditates explain why this node is taken  
 	  showstring = showstring + "Node " + intToString(minend) + 
				    " has the minimum distance.\n";
	  //check if there are other paths to minend. 
	  int newpaths=0;
	  for (int i=0; i<numnodes; i++) 
	     if ( (node[i].x>0) && (weight[i][minend]>0) && ( finaldist[i] == -1 ) )
	        newpaths++; 
	  if (newpaths>0) 
	     showstring = showstring + "Any other path to " + intToString(minend) + 
                   " visits another red node, and will be longer than " +  mindist + ".\n";
          else showstring = showstring + 
			     "There are no other arrows coming in to "+
			     intToString(minend) + ".\n";
	}
	else {
           boolean morenodes=false;
           for (int i=0; i<numnodes; i++) 
	     if ( ( node[i].x>0 ) && ( finaldist[i] == -1 ) && ( weight[i][minend]>0 ) )
	        morenodes=true; 
           boolean bridge=false;
           for (int i=0; i<numnodes; i++) 
	     if ( ( node[i].x>0 ) && ( finaldist[i] == -1 ) && ( weight[minend][i]>0 ) )
	        bridge=true; 
           if ( morenodes && bridge ) 
              showstring = showstring + "Since this node forms a 'bridge' to "+
               "the remaining nodes,\nall other paths to this node will be longer.\n";     
           else if ( morenodes && (!bridge) ) 
              showstring = showstring + "Remaining gray nodes are not reachable.\n";
           else showstring = showstring + "There are no other arrows coming in to "+
	       intToString(minend) + ".\n"; 
        }
	showstring = showstring + "Node " + intToString(minend) + 
	   " will be colored orange to indicate " + mindist + 
	   " is the length of the shortest path to " + intToString(minend) +"."; 
	parent.documentation.doctext.showline(showstring);       
    }

    public void detailsalg(Graphics g, int i, int j) {
    // more algorithms can be added later
	if (algorithm==DIJKSTRA)
	    detailsDijkstra(g, i, j);
    }

    public void endstepalg(Graphics g) {
    // more algorithms can be added later
	if (algorithm==DIJKSTRA)
	    endstepDijkstra(g);
	if ( ( performalg ) && (mindist==0) ) {
	   if (algrthm != null) algrthm.stop();
	   int nreachable = 0;
	   for (int i=0; i<numnodes; i++)
	      if (finaldist[i] > 0)
		 nreachable++;
	   if (nreachable == 0)
	      parent.documentation.doctext.showline("none");
           else if (nreachable< (numnodes-emptyspots-1))
	      parent.documentation.doctext.showline("some");
	   else
	      parent.documentation.doctext.showline("done");
	} 
    }

    public void paint(Graphics g) {
        mindist=0;
	minnode=MAXNODES;
	minstart=MAXNODES;
	minend=MAXNODES;
        for(int i=0; i<MAXNODES; i++) 
           changed[i]=false;
        numchanged=0;
        neighbours=0;
	g.setFont(roman);
	g.setColor(Color.black);
        if (step==1)
           showstring="Algorithm running: red arrows point to nodes reachable from " +
                   " the startnode.\nThe distance to: ";
        else 
           showstring="Step " + step + ": Red arrows point to nodes reachable from " +
                   "nodes that already have a final distance." +
                   "\nThe distance to: ";

	// draw a new arrow upto current mouse position
	if (newarrow)
	  g.drawLine(node[node1].x, node[node1].y, thispoint.x, thispoint.y);

	// draw all arrows
	for (int i=0; i<numnodes; i++)
	  for (int j=0; j<numnodes; j++)
	     if (weight [i][j]>0) {
	        // if algorithm is running then perform next step for this arrow
	        if (performalg)
	           detailsalg(g, i, j);
	        drawarrow(g, i, j);
	     }

	// if arrowhead has been dragged to 0, draw it anyway, so the user
	// will have the option to make it positive again
	if (movearrow && weight[node1][node2]==0) {
	  drawarrow(g, node1, node2);
	  g.drawLine(startp[node1][node2].x, startp[node1][node2].y, 
		     endp[node1][node2].x, endp[node1][node2].y);
	}

	// draw the nodes
	for (int i=0; i<numnodes; i++)
	  if (node[i].x>0) {
	     g.setColor(colornode[i]);
	     g.fillOval(node[i].x-NODERADIX, node[i].y-NODERADIX, 
			NODESIZE, NODESIZE);
	  }

	// reflect the startnode being moved
	g.setColor(Color.blue);
	if (movestart)
	  g.fillOval(thispoint.x-NODERADIX, thispoint.y-NODERADIX, 
			NODESIZE, NODESIZE);


	g.setColor(Color.black);
	// finish this step of the algorithm
	if (performalg) endstepalg(g);

	// draw black circles around nodes, write their names to the screen
	g.setFont(helvetica);
	for (int i=0; i<numnodes; i++)
	  if (node[i].x>0) {
	     g.setColor(Color.black);
	     g.drawOval(node[i].x-NODERADIX, node[i].y-NODERADIX, 
			NODESIZE, NODESIZE);
	     g.setColor(Color.blue);
	     g.drawString(intToString(i), node[i].x-14, node[i].y-14);
	  }
    }
}





