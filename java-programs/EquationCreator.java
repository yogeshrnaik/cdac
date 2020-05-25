import ncst.pgdst.*;
class Equation
{
  int no;
  char ch;
  Equation (int no)
  {
    this.no = no; ch = ' ';
  }
  Equation (char ch)
  {
    this.ch = ch;
    no = -1;
  }
}
public class EquationCreator
{
  int[] nos, column;
  int size, operators;
  int ROW;
  int[][] matrix;
  Equation[] equation;
  int eqSize;

  EquationCreator (SimpleInput sin) throws IOException
  {
    size = sin.readInt();
    nos = new int[size];
    for (int i=0; i<size; i++)
      nos[i] = sin.readInt();
    operators = size - 2;
    eqSize = operators + size - 1;
    equation = new Equation[eqSize];

    int temp = raiseTo(3, operators);
    column = new int[operators];
    matrix = new int[temp][operators];
    ROW = 0;
    generate (0);
    String op = "";
    for (int i=0; i<ROW; i++)
    {
      op = "";
      for (int j=0; j<operators; j++)
      {
        switch (matrix[i][j])
        {
          case 1 : op += "+"; break;
          case 2 : op += "-"; break;
          case 3 : op += "*"; break;
        }
      }
      formArray (op);
      boolean flag = evaluateArray();
      if (flag)
        System.out.println(op);
    }
  } // constructor

  public static void main (String[] args) throws IOException
  {
     EquationCreator abc = new EquationCreator (new SimpleInput());
  } // main

  public int raiseTo(int n, int r)
  {
    int ans=1;
    for (int i=1; i<=r; i++)
       ans = ans * n;
    return (ans);
  }

  public void generate(int k)
  {
    column[k] = 0;
    while (column[k] < 3)
    {
      column[k]++;
      if (k<operators-1)
         generate(k+1);
      else
      {
        for (int j=0; j<operators; j++)
           matrix[ROW][j] = column[j];
        ROW++;
      } // else
    } // while
  } // generate

  public void formArray (String op)
  {
    int nos_count=0, op_count=0;
    for (int i=0; i<eqSize; i++)
    {
      if (i%2 == 0)
        equation[i] = new Equation(nos[nos_count++]);
      else
        equation[i] = new Equation (op.charAt(op_count++));
    }
  } // formArray

  public boolean evaluateArray ()
  {
     int Size = eqSize;
     while (Size != 1)
     {
       int i = 0;
       for (; i<Size; i++)
         if (equation[i].ch == '*') break;
       if (i!=Size)
         equation[i-1] = new Equation (equation[i-1].no*equation[i+1].no);
       else     // no * symbol, so evaluate expression from left to right
       {
          switch (equation[1].ch)
          {
            case '+' : equation[0] = new Equation(equation[0].no+equation[2].no);
                       break;
            case '-' : equation[0] = new Equation(equation[0].no-equation[2].no);
                       break;
          }
       }
       int j = 0;
       if (i != Size) j = i; else j = 1;
       for (; j<Size-2; j++)
         equation[j] = equation[j+2];
       Size -= 2;
     } // while
     if (equation[0].no == nos[size-1])
        return true;
     else return false;
  }

} // EquationCreator
