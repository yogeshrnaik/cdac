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
public class StampChamp_Old
{
  int d;
  int[] D;
  int noc;
  Customer[] customer;

  StampChamp_Old (SimpleInput sin) throws IOException
  {
    d = sin.readInt();
    D = new int[d];
    for (int i=d-1; i>=0; i--)
      D[i] = sin.readInt();

    noc = sin.readInt();
    customer = new Customer[noc];

    for (int i=0; i<noc; i++)
      customer[i] = new Customer(sin);

    int flag = 0;
    int total_stamps = 0;
    for (int i=0; i<noc; i++)
    {
      flag = getStamps(customer[i], customer[i].value, 0, 0);
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
    StampChamp_Old champ = new StampChamp_Old (new SimpleInput());
  }

  public int getStamps (Customer customer, int val, int stamp, int count)
  {
    if (count == 20) return 2;
    if (val == 0)
      return 1;
    for (int i=0; i<d; i++)
    {
      if (val >= D[i])
      {
        int flag = getStamps(customer, val-D[i], stamp+1, count+1);
        if (flag == 1)
        {
          if (customer.stamps == 0 || stamp+1 < customer.stamps)
            customer.stamps = stamp+1;
        }
        else if (flag == 2)
          return 2;
      }
    }
    return 0;
  }
} // StampChamp
