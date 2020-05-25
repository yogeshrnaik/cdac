import ncst.pgdst.*;

public class ZeroRC{
	public static void main(String[] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int row = sin.readInt();
		int column = sin.readInt();
		int[][] matrixOld = new int[row][column];
		int[][] matrixNew = new int[row][column];
		for(int i = 0 ; i < row ; i++)
			for(int j = 0 ; j < column ; j++)
			{
				sin.skipWhite();
				matrixOld[i][j] = sin.readInt();
			//	System.out.println(" "+matrixOld[i][j]+ " ");
			}
		int a = 0, b = 0;
		boolean allZeros = true;
		for(int i = 0 ; i < row ; i++)
		{
			allZeros = true;
			for(int j = 0 ; j < column ; j++)
			{
				if(matrixOld[i][j] != 0)allZeros = false;
				b = j;
				matrixNew[a][b] = matrixOld[i][j];
		//		System.out.println("matrixOld["+a+"]["+b+"]="+matrixOld[i][j]+ " "+matrixNew[a][b]+ " "+  allZeros + " "+ a);	
			}
			a++;
			if(allZeros == true)a--;
		}
		matrixOld = matrixNew;
		int maxA,maxB;
		maxA = a;
		a = 0; b = 0;
		for(int j = 0 ; j < column ; j++)
		{
			allZeros = true;
			for(int i = 0 ; i < maxA ; i++)
			{
				if(matrixOld[i][j] != 0)allZeros = false;
				a = i;
				matrixNew[a][b] = matrixOld[i][j];
				
			}
			b++;
			if(allZeros == true)b--;
		}
		maxB = b;
		for(int i = 0 ; i < maxA ; i++)
		{
			for(int j = 0 ; j < maxB ; j++)
			{
				System.out.print(matrixNew[i][j] + " ");
			}
			System.out.println();
		}
	}
	
}
