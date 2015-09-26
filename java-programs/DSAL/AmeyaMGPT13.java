import ncst.pgdst.*;

public class AmeyaMGPT13
{
	static int count =0;
	static int[] fst;
	static int[] sec;
	static int n;
	static int total;
	static int alter=0;
	static int amt;

	public static void main(String [] args) throws IOException
	{
           	SimpleInput sin = new SimpleInput();
            n = sin.readInt();
	      fst = new int[n];
            for(int i=0; i<n; i++)
			fst[i] = sin.readInt();
            for(int i=0; i<n-1; i++)
     	        for(int j=i+1; j<n; j++)
                 if(fst[i]>fst[j])
                 {
                    int temp=fst[i];
            	  fst[i] = fst[j];
		        fst[j] = fst[i];
                 }
		total = n*(n-1)/2 +n;
		sec = new int[total];
		int tot=0;
		for(int i=0; i<n; i++)
			for(int j=i; j<n; j++)
				sec[tot++]= fst[i] + fst[j];
		for(int i=0; i<total-1; i++)
			for(int j=i+1; j<total; j++)
				if(sec[i]>sec[j])
				{ 	
					int temp =sec[i]; 
					sec[i] = sec[j]; 
					sec[j] = temp;
				}
		int no = sin.readInt();
		for(int i=0; i<no; i++)
	      { 
			amt = sin.readInt();
		      stamp();
	        	if(amt !=0 ) count = alter;
	           	else 
	       	    	if((count > alter)&&(alter >0)) 
					count = alter;
		}
	      System.out.println(count);
	} // main

	public static void stamp()
	{
	                if(amt > fst[n-1])
	                {
               		         for(int k=0;k<n;k++)
                               		 if(amt%fst[k] == 0) alter = count + amt/fst[k];
		         int temp = (amt/fst[n-1]) -1;
	                        amt -= temp * fst[n-1];
	                        count += temp;
	                }
                if(amt != 0)
                        for(int i=0; i<n; i++)
	                if(amt == fst[i])
		{
			count++; 
			amt =0; 
			i=n; 
		}

                if(amt != 0)
                        for(int j=total-1; j>=0; j--)
                        if(sec[j] <= amt)
                        {
                        for(int k=0;k<n;k++)
                                if(amt%fst[k] == 0)
                                {
                                if (alter !=0)
                                {
                                if ((count + amt/fst[k]) <=alter)
                                {alter = 0;
                                alter = count + amt/fst[k];}
                               
                                }
                                else alter = count +amt/fst[k];
                                }
                                amt -= sec[j];
                                count += 2;
                                j =-1;
                                stamp();
                                }
                
        }
}                  
  
                
