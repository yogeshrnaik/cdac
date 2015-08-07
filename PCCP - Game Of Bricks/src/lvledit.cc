# include <curses.h>
# include "header.h"
# include <stdio.h>
# define STARTY 5
# define STARTX 2
int main()
{
	int N=0,i;
	int x,y, hits;
	char filename[20];
	FILE *fp;
	
	printf("Enter the name of the file:");
	scanf("%s",&filename);

	fp = fopen(filename,"w");
	
	N = 5*((80-4)/(BRK_WIDTH+1));
	printf("\nEnter the number of bricks (Max = %d) : ",N);
	scanf("%d",&i);
	if (i<=N) N = i;
	i=0;
	fprintf(fp,"%d ",N);

	for (int x=1; x<=15; x++)
	{
		int option;
		printf("\nDo you want to place Bricks in Col = %d :(0/1): ", x);
		scanf ("%d", &option);
		if (option)
		{
			int nob;	// no. of bricks
			int r[5], c[5];
			printf("Enter the no. of bricks you want to place ");
			printf("in column %d : ", x);
			scanf ("%d", &nob);

			printf ("Enter the %d row nos. : ",nob);
			for (i=0; i<nob; i++)
				scanf ("%d", &r[i]);

			printf("Enter Colors of %d bricks : ", nob);
			for (i=0; i<nob; i++)
				scanf ("%d", &c[i]);

			for (i=0; i<nob; i++)
			{
				switch (c[i])
				{
					case 1 : hits = 1; break;
					case 2 : hits = 2; break;
					case 3 : hits = -1; break;
				}
				int ax = STARTX + ((BRK_WIDTH+1) * (x-1));
				int ay = STARTY + ((BRK_HEIGHT+1) * (r[i]-1));
				
			//	printf("x = %d ", ax);
			//	printf("y = %d\n", ay);

				fprintf(fp,"%d ", ax);
				fprintf(fp,"%d ", ay);
				fprintf(fp,"%d ",c[i]);
				fprintf(fp,"%d\n",hits);
			}
		}
	}
	fclose(fp);	
	return 1;
}


/*
	for (i=1; i<=N; i++)
	{
		printf("\nBrick # %d\n",i);
		printf("Cell Co-ordinates : (y (row), x (col)) : ");
		scanf("%d %d",&y,&x);
		printf("Color : ");
		scanf("%d",&color);
		printf("Hits to break the brick : ");
		scanf("%d",&hits);

		x = STARTX + ((BRK_WIDTH+1) * (x-1));
		y = STARTY + ((BRK_HEIGHT+1) * (y-1));

		//drawBrick(y, x);

		fprintf(fp,"%d ", x);
		fprintf(fp,"%d ", y);
		fprintf(fp,"%d ",color);
		fprintf(fp,"%d\n",hits);
		
	}
*/
