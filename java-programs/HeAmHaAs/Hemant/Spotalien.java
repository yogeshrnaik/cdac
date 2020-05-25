import ncst.pgdst.*;

class Dna
{
String id;
char [] dnas;
boolean flags [];
Dna(int n1){count = 0;dnas = new char[n1]; flags = new boolean[n1]; pair = new boolean[n1-1];
		for(int i=0; i<n1-1; i++) pair[i] = true;	}
int count ;
boolean pair[];


public void settrue()
{	for(int i=0; i<flags.length; i++)
	flags[i] =true;
	for(int i=0; i<pair.length; i++)
	pair[i] = true;
}
}

public class Spotalien
{

	static int nos,dnanos;
	static Dna dna[];
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		nos = sin.readInt();
		dnanos = sin.readInt();

		 dna = new Dna[nos];

		for(int i=0; i<nos; i++)

		{	
			sin.skipWhite();
			dna[i] = new Dna(dnanos);
			dna[i].id = sin.readWord();
			for(int j=0; j<dnanos; j++)
			{	sin.skipWhite();  dna[i].dnas[j] = sin.readChar(); dna[i].flags[j] = true;
				
				
			}

		}
		
		for(int i = 0; i<nos; i++)
		{	Dna temp = dna[i];   dna[i] = dna[0];  dna[0] = temp;
		{	for(int j=1; j<nos; j++)

				{		dna[0].settrue(); dna[j].settrue();
					search(j);
					serch(j);
					serpair(j);
				System.out.println(dna[0].count);
				}
		}
		}	
	
		
	for(int i=0; i<nos-1; i++)
		for(int j=i+1; j<nos; j++)
				{if(dna[i].count > dna[j].count) { Dna t = dna[i]; dna[i] = dna[j]; dna[j] = t; }}
		System.out.println(dna[0].id);

				}
	
	

	public static void search(int a)
	{
		for(int i=0; i<dnanos; i++)
		if(dna[0].dnas[i] == dna[a].dnas[i]) { dna[0].count +=3;  dna[0].flags[i] = false; dna[a].flags[i] = false; 
				
	/*						for(int k=0; k<dnanos; k++) { if(dna[0].dnas[i] == dna[0].dnas[k]) dna[0].flags[k] = false;	  if (dna[a].dnas[i] == dna[a].dnas[k]) dna[a].flags[k] = false;}*/
	}
		}
	

	public static void serch(int a)
	{
		for(int i=0; i<dnanos; i++)
		{ for(int j=0; j<dnanos; j++)
			{ if(dna[0].flags[i] && dna[a].flags[j]){ if (dna[0].dnas[i]==dna[a].dnas[j]) { dna[0].count +=1; 
													dna[0].flags[i] = false;
													dna[a].flags[j] = false;
													}
								}
			}
		}
	}

	public static void serpair(int a)
	{
		for(int i=0; i<(dnanos-1); i++)
			{ for (int j=0; j<(dnanos-1); j++)
				{
					if(dna[0].pair[i] && dna[a].pair[j])
					{	if((dna[0].dnas[i] == dna[a].dnas[j]) && (dna[0].dnas[i+1] == dna[a].dnas[j+1]))
						{	dna[0].count +=2; dna[0].pair[i] = false;
										dna[a].pair[j] = false;
						}
					}
				}	
			}
	}  




						

}	

