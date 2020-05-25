import ncst.pgdst.*;

class var
 { int c;
   int p;
   var()
   {c=p=0;}
  void addvar(var a ,var b)
  {   c = a.c+ b.c;
      p = a.p;
  }
 }
 public class pol
 { var[] x = new var[40];
   int terms;
   pol(SimpleInput sin) throws IOException
   {
   var[] a =new var[40];
   for(int i=0;i<40;i++)
    {
    a[i] = new var();
    }
    boolean end = false;
    int temp1,temp2;
    int j=0;
    while(!end)    //read
     {
     temp1 = sin.readInt();
     temp2 = sin.readInt();
     if((temp1==-1)&&(temp2==-1)) end =true;
     else {
           a[j].c = temp1;
           a[j].p = temp2;
           j++;
          }
    }
    terms = j;
    for(int i=0;i<terms;i++)
    { for(int k=i;k<terms;k++)
      {
       if(a[i].p <a[k].p)
       { var temp =a[i];
         a[i] = a[k];
         a[k] = temp;
       }
      }
    }
    x=a;
   }
   pol()          //default constructor
   {
    for(int i=0;i<40;i++)
       x[i] = new var();
       terms = 0;
   }
   pol addpol (pol p2)                   //addition
    {  pol ans = new pol();
       int i,j,k;
       i = j = k = 0;
       boolean endi = false;
       boolean endj = false;
     while(!endi && !endj)
       {  if( x[i].p == p2.x[j].p)
             { ans.x[k].addvar(x[i],p2.x[j]);
               i++;    
               j++;
             }
           else if( x[i].p > p2.x[j].p)
             {
              ans.x[k] = x[i];
              i++;
             }
           else if( x[i].p < p2.x[j].p)
             { ans.x[k] = p2.x[j];
               j++;
             }
            if(i>terms) endi = true;
            if(j>p2.terms) endj = true;
            k++;
       }
            if(endi)
            { for(int a =j;a<=p2.terms;a++) { ans.x[k] = p2.x[a]; k++;} }
            if(endj)
            { for(int a =i;a<=terms;a++) { ans.x[k] =x[a]; k++;} }
            ans.terms = k-1;
            return ans;
        
    }
    public static void main (String[] args) throws IOException
    {
      SimpleInput sin = new SimpleInput();
      pol p1 = new pol(sin);
      pol p2 = new pol(sin);
      pol p = new pol();
      p = p1.addpol(p2);
      for(int i = 0;i<p.terms-1;i++)
      { if(p.x[i].c!=0) System.out.print(p.x[i].c + " "+ p.x[i].p+" "); }
      if(p.x[p.terms].c!=0)  System.out.println(p.x[p.terms].c + " " + p.x[p.terms].p); 
    }
 }
