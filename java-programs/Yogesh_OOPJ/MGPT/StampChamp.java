import ncst.pgdst.*;
class Customer
{
  int value;
  int stamps;
  Customer(SimpleInput sin) throws IOException
  {
    value = sin.readInt();
    stamps = 0;
  }
}
public class StampChamp
{
  int d;
  int[] D;
  int noc;
  Customer[] customer;
  StampChamp (SimpleInput sin) throws IOException
  {
    d = sin.readInt();
    D = new int[d];
    for (int i=d-1; i>=0; i--)
      D[i] = sin.readInt();
    noc = sin.readInt();
    customer = new Customer[noc];
    for (int i=0; i<noc; i++)
      customer[i] = new Customer(sin);
    boolean flag;
    int total_stamps = 0;
/*************************************************************/
    for (int i=0; i<noc; i++)
    {
      flag = getStamps(customer[i], customer[i].value, 0);
      total_stamps += customer[i].stamps;
      if (i != noc-1)
        System.out.print(customer[i].stamps+" + ");
      else
        System.out.print(customer[i].stamps+" = ");
    }
    System.out.println(total_stamps);
  }
  public static void main (String[] args) throws IOException
  {
    StampChamp champ = new StampChamp (new SimpleInput());
  }
  public boolean getStamps (Customer customer, int val, int stamp)
  {
    boolean flag = false;
    if (val == 0)
      return true;
    for (int i=0; i<d; i++)
    {
      if (val >= D[i])
      {
        flag = getStamps(customer, val-D[i], stamp+1);
        if (flag)
         if (customer.stamps == 0 || stamp+1 < customer.stamps)
            customer.stamps = stamp+1;
      }
    }
    return false;
  }
} // StampChamp
/*************************************************************/
