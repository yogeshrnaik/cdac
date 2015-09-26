import ncst.pgdst.*;
class Women
{
   int id;
   boolean paired;
   int with;
   boolean clue;
   int cannot[];
   int cn;
   Women (int i)
   {
        id = i+1;
        paired = false;
        with = 0;
        cannot = new int[10];
        cn=0;
   }
} // women
class Men
{
  int id;
  boolean paired;
  int with;
  int[] cannot;
  int cn;
  Men(int i)
  {
        id = i+1;
        paired = false;
        with=0;
        cannot = new int[10];
        cn=0;
  }
} // men
class Clues
{
   char ch;
   int w,m;
   Clues (SimpleInput in) throws IOException
   {
        in.skipWhite();
        ch = in.readChar();
        w = in.readInt();
        m = in.readInt();
   } // constructor
} // clues
public class Pairing
{
   public static void main(String[] args) throws IOException
   {
        SimpleInput sin = new SimpleInput();
        int nop = sin.readInt();
        int noc = sin.readInt();
        Men[] M = new Men[nop];
        Women[] W = new Women[nop];
        for (int i=0; i<nop; i++)
        {
           M[i] = new Men(i);
           W[i] = new Women(i);
        }
        Clues[] C = new Clues[noc];
        for (int i=0; i<noc; i++)
                C[i] = new Clues(sin);
        for (int i=0; i<noc; i++)
        {
            if (C[i].ch == '=')
            {
                W[C[i].w-1].paired = true;
                M[C[i].m-1].paired = true;
                W[C[i].w-1].with = C[i].m;
                M[C[i].m-1].with = C[i].w;
            }
            else
            {
                W[C[i].w-1].cannot[W[C[i].w-1].cn] = C[i].m;
                W[C[i].w-1].cn++;
                M[C[i].m-1].cannot[M[C[i].m-1].cn] = C[i].w;
                M[C[i].m-1].cn++;
            } // else
        } // for

        for (int i=0; i<nop; i++)
        {
            if (!W[i].paired)
            {
                for (int j=0; j<noc; j++)
                {
                   if (C[j].ch=='=')
                   {
                      W[C[j].w-1].cannot[W[C[j].w-1].cn] = C[j].m;
                      W[C[j].w-1].cn++;
                      M[C[j].m-1].cannot[M[C[j].m-1].cn] = C[j].w;
                      M[C[j].m-1].cn++;
                   }
                }
            } //if
        }

        for (int i=0; i<nop; i++)
        {
            if (!W[i].paired)
            {
                if (W[i].cn==0)
                {
                     boolean found = false;
                     int j = 0;
                     while (!found && j<nop)
                     {
                        if (!M[j].paired)
                        {  M[j].paired = true;
                           W[i].paired = true;
                           W[i].with = M[j].id;
                           M[j].with = W[i].id;
                           found = true;
                        }
                        else j++;
                     }
                }
                else if (W[i].cn <0 )// cn>0
                {
                    boolean found = true;
                    int z=0;
                    while (M[z].paired)
                        z++;
                    W[i].paired = true;
                    M[z].paired = true;
                    W[i].with = M[z].id;
                    M[z].with = W[i].id;
                }
            }
        }

     for (int i=0; i<nop; i++)
     {
        if (i!=nop-1)
          System.out.print(W[i].with + " ");
        else
          System.out.println(W[i].with);
     }
   } // main
} // Pairing
