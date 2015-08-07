import ncst.pgdst.*;
import java.io.File;

/*	
	startx and starty are the co-ordinates of the first left top brick
	i.e. co-ordinates of brick present in the first row and first column
	startx = 40							starty = 145
	total no. of columns = 12		total no. of rows = 5
*/
public class LevelEditor
{
	final static int brkWidth = 50;		//50;
	final static int brkHeight = 30;		//25;
	final static int HGAP = 30;
	final static int VGAP = 25;
	String filename;
	final static int startx = 40, starty = 145;
	final static String path = "D:\\Yogesh\\APGDST_OOAD\\Game Of Bricks\\Source Code\\Current\\";
	int no_of_bricks;

	public LevelEditor(SimpleInput sin) throws IOException
	{
		System.out.print("Enter level file name : e.g. level01.txt : ");
		filename = sin.readWord();

		// initialise a file object to write output to a file
		File file = new File(path + filename);
		SimpleOutput sout = new SimpleOutput(file);

		System.out.print("Enter no. of bricks : ");
		no_of_bricks = sin.readInt();
		sout.writelnInt(no_of_bricks);

		// outer for
		for (int i=1; i<=12; i++)
		{
			System.out.print("Do you want to place brick/s in column : " + i + " : y/n : ");
			sin.skipWhite();
			char ch = sin.readChar();
			if (ch == 'y' || ch == 'Y')
			{
				int no_of_bricks;
				int row[], color[];
				System.out.print("Enter the no. of bricks you want to place in column " + i + " : ");
				no_of_bricks = sin.readInt();
				row = new int[no_of_bricks];
				System.out.print("Enter the row nos. : ");
				for (int j=0; j<no_of_bricks; j++)
				{
					row[j] = sin.readInt();
				}
				// 1 : red, 2 : pink, 3 : yellow 4 : blue
				System.out.print("Enter the colors for " + no_of_bricks + " bricks : ");
				color = new int[no_of_bricks];
				for (int j=0; j<no_of_bricks; j++)
				{
					color[j] = sin.readInt();
				}

				// inner for
				for (int j=0; j<no_of_bricks; j++)
				{
					int hits = -1;
					switch(color[j])
					{
						case 1 : hits = 1; break;
						case 2 : hits = 2; break;
						case 3 : hits = 3; break;
						case 4 : hits = -1; break;
						default : hits = -1;
					}
					int x = startx + ((i-1) * (brkWidth+HGAP));
					int y = starty + ((row[j]-1) * (brkHeight+VGAP));
					System.out.print("writing to file : " + j + "\t");
					System.out.println("x="+x+" y="+y+" color="+color[j]+" hits="+hits);

					sout.writelnString("");
					sout.writeString(x + " " + y + " ");
					sout.writeString(color[j] + " " + hits);
					sout.flush();
				} // inner for
			} // if 
		} // outer for
	} // constructor

	public static void main(String s[]) throws IOException
	{
        LevelEditor le = new LevelEditor(new SimpleInput());
    }
}

