import ncst.pgdst.*;

public class testwhile
{

public static void main(String[] args)
{
        int i=0,count=0;
        while(++i<5){i++;count++;}
        System.out.println("I = "+i+'\n'+ "Count= "+count);
        for(i=0;((i<10)&&(i<=20));i++){}
         System.out.println("I = "+i);
        if(i<=20)   System.out.println("I = "+(i=20));
        char a =0;
        for(int j=-10;j<0;j++)System.out.println((a++) + " ");


        }}
