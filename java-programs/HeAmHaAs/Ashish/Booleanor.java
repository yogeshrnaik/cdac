import ncst.pgdst.*;
public class Booleanor

  {   var[] x = new var[30];
      int hno;
      Booleanor(SimpleInput sin) throws IOException  
        {
               for(int i=0;i<30;i++)
               x[i] = new var();
           hno = sin.readInt();
           int j=0;
           for(int i =0;i<hno;i++)
               {
                 x[i].a  = sin.readInt();
                 x[i].b = sin.readInt();
                 
               }
         }
         Booleanor()
         {
               int hno =0;
               for(int i=0;i<30;i++)
                x[i] = new var();
         }
       void  OR(Booleanor s2)
       {
        Booleanor ans = new Booleanor();
            int i = 0; int j=0;int k=0;
            boolean endi=false,endj = false;
         while(!endi && !endj)
            {
              if((k>0)&&(ans.x[k-1].b>x[i].a)) { i++;}
              else                       
              if((k>0)&&(ans.x[k-1].b>s2.x[k].a)) { j++;}
              else
             {
              if(x[i].b<s2.x[j].a)           
               { ans.x[k] = x[i];i++; }
              else                           
              if(s2.x[j].b<x[i].a)            
                  {ans.x[k] = s2.x[j];j++;}
              else                             
                {     ans.x[k].a = Math.min(x[i].a,s2.x[j].a);
                      ans.x[k].b = Math.max(x[i].b,s2.x[j].b);
                      j++;
                      i++;
                  
                }
               k++;
              }
              if(i>hno)  endi = true;
              if(j>s2.hno) endj =true;

           } 
            if(endi)
                { for(int m =j;m<=s2.hno;m++)
                    {  ans.x[k] = s2.x[m]; k++;}  }
            if(endj)
                { for(int m =i;m<=hno;m++)
                    {  ans.x[k] = x[m]; k++;}
                } 
                    ans.hno = k-1;
                    System.out.print(ans.hno +  " ");
                    for(i=0;i<ans.hno;i++)
                        System.out.print(ans.x[i].a + " "+ans.x[i].b+ " ");
                        System.out.println();

       }
       public static void main (String[] args) throws IOException
                {
                 SimpleInput sin = new SimpleInput();
                 Booleanor s1 = new Booleanor(sin);
                 Booleanor s2 = new Booleanor(sin);
                 s1.OR(s2);  
                }
 }
 class var
 {  int a,b;
  var()
  {int a=b=0;}
 }
