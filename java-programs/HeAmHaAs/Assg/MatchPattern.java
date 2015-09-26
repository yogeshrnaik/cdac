import ncst.pgdst.*;
import java.lang.*;

public class MatchPattern{
	public static void main(String [] args) throws IOException
	{
	
		final String DOT = ".";
		final String ASTERIK = "*";
		SimpleInput sin = new SimpleInput();
		String master = sin.readLine();
		sin.skipWhite();
		String scan = sin.readLine();
		StringComponent sc = new StringComponent();

		
		sc.setDotAndAsterikPosition(scan,DOT);
		sc.setDotAndAsterikPosition(scan,ASTERIK);
		sc.setComponents(scan);	
		sc.printComponents();


		sc.setMatchString(master , sc.component[0] , 1 , 2);
		System.out.println(sc.matchFinal);
		String printSc = "";
	}
}
class StringComponent{

	public void setMatchString(String master ,String first, int middle ,int second )
	{
		String match = "";
		
		if(component[second].compareTo(ASTERIK) != 0 && component[second].compareTo(DOT) != 0)
		{
			if(component[middle].compareTo(DOT) == 0)
			{
				match = getMatchDot(master,first,component[second],1);
				//System.out.println(" match dot ="+match+" master="+master+" first="+first+" second="+component[second]);
				if(match.length() > matchFinal.length())matchFinal = match;
				if(second < component.length - 2)
				setMatchString( master , match , second + 1 , second + 2 );
				
			}
			if(component[middle].compareTo(ASTERIK) == 0)
			{
				match = getMatchAsterik( master , first , component[second] , String.valueOf(first.charAt(first.length() - 1)));	
				//System.out.println(" match  asterik="+match+" master="+master+" first="+first+" second="+component[second]);
				if(match.length() > matchFinal.length())matchFinal = match;
				if(second < component.length - 2)
				setMatchString( master , match , second + 1 , second + 2 );
				
			}
		}
		else
		{
			if(component[middle].compareTo(DOT) == 0 && component[second].compareTo(ASTERIK) == 0)
			{
				if(second < component.length - 1)
				match = getMatchDotAsterik(master , first , component[second + 1]);
				System.out.println(" match  asterikdot="+match+" master="+master+" first="+first+" second="+component[second+1]);
				if(match.length() > matchFinal.length())matchFinal = match;
				if(second < component.length - 2)
				setMatchString( master , match , second + 1 , second + 2 );
				
			}
		}
	}
			
	public String getMatchDot(String master,String first,String second,int number)
	{
		String match = "";
		String temp = "";
		String modified = "";
		int start = initialiseStartIngetMatchDot;
		System.out.println("start ="+start);
		int end = 0;
		int finish = 0;
		do
		{
/*			start = master.indexOf(first,end);
			if(start == -1)
				if(first != "") break;
				else start = 0;
			end = master.indexOf(second,start + 1);
			if(end == -1)
				if(second != "" ) break;
				else end = master.length();
			if(second == "") end = master.length();
			finish = end + second.length();
			
*/			
				
			start = master.indexOf(first,start);
			if(start == -1)
				if(first != "") break;
				else start = 0;
			
			//match = master.substring(start,finish);
			
			
			temp = first +getString(".",number);
			int modifiedLength = temp.length();
			temp = temp + second;
			modified = first + getString(".",number) + master.substring(start + modifiedLength,start + temp.length());
			match = master.substring(start,start + temp.length());
			if(modified.compareTo(temp) == 0)break;	

//		System.out.println("2"+first + " " + second + " " + temp + " " +match+" "+modified +" " +start+" "+ end );
			start++;
			
		}while(true);
		initialiseStartIngetMatchDot = start + 1;
		if(start != -1)return match;
		else return "";
		
	}
	public String getMatchAsterik(String master,String first,String second,String character)
	{
		String match = "";
		String maxMatch = match;
		String temp = "";
		int start = 0;
		int end = 0;
		int finish = 0;
		do
		{
			start = master.indexOf(first,end);
			if(start == -1)
				if(first != "") break;
				else start = 0;
			end = master.indexOf(second,start + 1);
			if(end == -1)
				if(second != "" ) break;
				else end = master.length();
			if(second == "") end = master.length();
			finish = end + second.length();
			
			match = master.substring(start,finish);
			int number = match.length() - first.length() - second.length();
			
			temp = first +getString(character,number)+ second;
			//System.out.println(first + " " + second + " " + temp + " " +match+ " " +start+" "+ end );
			if(match.compareTo(temp) == 0)
			if(maxMatch.length() < match.length())maxMatch = match;	
			start = end;
			
		}while(true);
		return maxMatch;
	}
	public String getString(String character ,int number)
	{
		String charArray = "";
		for(int i = 0 ; i < number ; i++) charArray = charArray + character;
		return charArray;
	}
	public String getMatchDotAsterik(String master,String first,String second)
	{
		String match = "";
		int start;
		int end;
		int finish;
		
		start = master.indexOf(first);
		if(start == -1)
			if(first != "") return match;
			else start = 0;
		end = master.lastIndexOf(second);
		if(end == -1)
			if(second != "" ) return match;
			else end = master.length();
		if(second == "")end = master.length();
		finish = end + second.length();
		match = master.substring(start,finish);
			
		return match;
	}
	public void setComponents(String scan)
	{
		int[][] totalWild = new int[dot.length + asterik.length][2];// 0 - dot ; 1 - asterik	
		
		// forming totalWild
		
		for(int i = 0 ; i < dot.length + asterik.length ; i++)
		{
			if(i<dot.length){totalWild[i][0] = dot[i];totalWild[i][1] = 0;}
			else {totalWild[i][0] = asterik[i - dot.length];totalWild[i][1] = 1;}
		}
		 
		// sorting totalWild
		int min = 0;
		int pos = 0;
		for(int i = 0 ; i < totalWild.length ; i++)
		{
			min = totalWild[i][0];
			pos = i;
			for(int j = i ; j < totalWild.length ; j++)
			{
				if(min > totalWild[j][0])
				{
					min = totalWild[j][0];	
					pos = j;
				}
			}
			totalWild[pos][0] = totalWild[i][0];
			totalWild[i][0] = min;
			min = totalWild[pos][1];
			totalWild[pos][1] = totalWild[i][1];
			totalWild[i][1] = min;
			
		}

		// setting components
		component = new String[2 * totalWild.length + 1];
		int begin = 0;
		int end = 0;
		int j = 0;
		for(int i = 0 ; i < component.length ; i = i + 2)
		{
			if(j < totalWild.length)end = totalWild[j][0];
			else end = scan.length();
			
			component[i] = scan.substring(begin,end);
			if(j < totalWild.length)
			{
				if(totalWild[j][1] == 0)component[i + 1] = ".";
				else component[i + 1] = "*";
				begin = totalWild[j][0] + 1;
			}
			j++;
		}
	}
	public void printComponents()
	{
		for(int i = 0 ; i < component.length ; i++)
		{
			System.out.println("component["+i+"]="+component[i]);
		}
	}
	public void setDotAndAsterikPosition(String scan,String DOT)
	{
		int[] dotPos = new int[scan.length()];
		int pos = -1;
		int counter = 0;
		do
		{
			pos = scan.indexOf(DOT,pos + 1);
			dotPos[counter] = pos;
			counter++;
		}while(pos != -1);
		counter--;
		if(DOT.compareTo(this.DOT) == 0)
		{
			dot = new int[counter];
			for(int i = 0 ; i < counter ; i++)
			{
				dot[i] = dotPos[i];
			}
		}
		else if(DOT.compareTo(this.ASTERIK) == 0)
		{
			asterik = new int[counter];
			for(int i = 0 ; i < counter ; i++)
			{
				asterik[i] = dotPos[i];
			}
		}
	}
	String[] component;
	int[] dot;
	int[] asterik;
	final String DOT = ".";
	final String ASTERIK = "*";

	int initialiseStartIngetMatchDot = 0;
	String matchFinal = "";
	

}
