import ncst.pgdst.*;
class Reference
{
        int id;
        Reference next;
        Reference (int id)
        {
                this.id = id;
                next = null;
        }
}
class Document
{
        int id;
        int words;
        String key[];
        int refs;
        Reference head;
        Document (int id, SimpleInput sin) throws IOException
        {
                this.id = id;
                words = sin.readInt();
                key = new String[words];
                for (int i=0; i<words; i++)
                        key[i] = new String (sin.readWord());
                refs = sin.readInt();
                head = null;
                for (int i=0; i<refs; i++)
                {
                        int tempref = sin.readInt();
                        if (head == null)
                                head = new Reference (tempref);
                        else if (head.id > tempref)
                        {
                           Reference newRef = new Reference(tempref);
                           newRef.next = head;
                           head = newRef;
                        }
                        else
                        {
                           Reference newRef = new Reference (tempref);
                           Reference curr = head, prev = null;
                           for (; curr!=null && tempref > curr.id; )
                           {
                                prev = curr;
                                curr = curr.next;
                           }
                           newRef.next = prev.next;
                           prev.next = newRef;
                        }
                } // for
        } // constructor
} // Document

public class SearchEngine
{
        int nodoc;
        Document doc[];
        int visited[];
        int size;
        int found[];
        int fsize;

        // query
        int stno, nokws;
        String[] kws;

        SearchEngine (SimpleInput sin) throws IOException
        {
                nodoc = sin.readInt();
                doc = new Document[nodoc];
                for (int i=0; i<nodoc; i++)
                {
                        int tempid = sin.readInt();
                        doc[tempid-1] = new Document (tempid, sin);
                }
                stno = sin.readInt();
                nokws = sin.readInt();
                kws = new String[nokws];
                for (int i=0; i<nokws; i++)
                        kws[i] = new String(sin.readWord());

                visited = new int[50];
                size = 0;
                found = new int[50];
                fsize = 0;
                search (doc[stno-1]);
                if (fsize == 0)
                        System.out.println ("nil");
                else
                {
                        for (int i=0; i<fsize; i++)
                                System.out.print (found[i] + " ");
                        System.out.println();
                }
        } // constructor
        public static void main (String[] args) throws IOException
        {
                SearchEngine se = new SearchEngine (new SimpleInput());
        } // main

        boolean isVisited (int id)
        {
                for (int i=0; i<size; i++)
                        if (visited[i] == id)
                                return true;
                return false;
        }

        void search (Document curr)
        {
                if (!isVisited (curr.id))
                {
                        // add to visited
                        visited[size++] = curr.id;
                        // search in current document
                        int count = 0;
                        for (int i=0; i<nokws; i++)
                        {
                           for (int j=0; j<curr.words; j++)
                           {
                                if (kws[i].equals(curr.key[j]))
                                        count++;
                           }
                        }
                        if (count == nokws)
                                found[fsize++] = curr.id;
                        Reference ref = curr.head;
                        for (; ref!=null; ref=ref.next)
                                search (doc[ref.id-1]);
                } // if
        } // search

} // SearchEngine