import ncst.pgdst.*;
public class StrCheck
{       public static void main(String[] args) throws IOException
        {
                SimpleInput sin = new SimpleInput();
                String s = "";
                for (int d=1; d<=2; d++)
                        for (int i=0; i<26; i++)
                                s = s + (char)(i+97);

                System.out.println(s);

                int index1st = s.indexOf("bc");
                System.out.println (index1st);

                int indexlast = s.lastIndexOf("bc");
                System.out.println(indexlast);

        }
}


