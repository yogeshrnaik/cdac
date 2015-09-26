import ncst.pgdst.*;
import java.lang.*;

public class OrDigitalSignals{
	public static void main(String [] args) throws IOException
	{
		SimpleInput sin = new SimpleInput();
		int size = sin.readInt();
		Signal s1 = new Signal(size * 2);
		s1.readSignal(sin);
//		s1.printSignal();
		sin.skipWhite();
		size = sin.readInt();
		Signal s2 = new Signal(size * 2);
		s2.readSignal(sin);
//		s2.printSignal();

		Signal finalSignal = Signal.addSignals(s1,s2);
		finalSignal.sort();
		finalSignal.printSignal();	
	}
	/*
	public static Signal fuseSignal(Signal s1, Signal s2)
        {
                        int lengthMax = s1.length + s2.length;
                        int[][] sig = new int[lengthMax][2];
                        int countSecond = 0;
                        int countSig = 0;
			boolean firstFinish = false;
			boolean secondFinish = false;
                        for(int i = 0 ; i < s1.length ; i++)
                        {
                                for(int j = countSecond ; j < s2.length ; j++)
                                {

			System.out.println(s1.signal[i][0] + " " + s2.signal[j][0] + " " + "i = " + i + " j = " + j);
//					System.out.println("i = " + i + " j = " + j);
                                        if((s1.signal[i][0] < s2.signal[j][0] && firstFinish == false) || (secondFinish == true && firstFinish != true))
                                        {
                                                sig[countSig][0] = s1.signal[i][0];
                                                sig[countSig][1] = s1.signal[i][1];
						//if(i >= s1.length)i--;
						countSecond = j;
                                                countSig++;
						if(i < s1.length - 1)break;
						else {firstFinish = true;j--;}
                                        }
                                        else
                                        {
                                                sig[countSig][0] = s2.signal[j][0];
                                                sig[countSig][1] = s2.signal[j][1];
						countSecond = j;
                                                countSig++;
						if(j >= s2.length - 1){secondFinish = true;}
                                        }
                                }
                        }
                        Signal newSig = new Signal(countSig);
                        for(int i = 0 ; i < countSig ; i++)
                        {
                                newSig.signal[i][0] = sig[i][0];
                                newSig.signal[i][1] = sig[i][1];
                        }
                        return newSig;
        }
	*/
}	
	class Signal{
		Signal(int length)
		{
			signal = new int[length][2];
			this.length = length;
		}
		public void readSignal(SimpleInput sin) throws IOException
		{
			final int ONE = 1;
			final int ZERO = 0;
			int flag = ONE;
			for(int i = 0 ; i < length ; i++)
			{
				sin.skipWhite();
				signal[i][0] = sin.readInt();
				signal[i][1] = flag;
				flag = flag == ZERO ? ONE : ZERO;
//				System.out.println(signal[i][0] + " signal " + i );
			}

		}
		public void printSignal()
		{
			int raise = 0;
			int size = 0;
			String s = "";
			for(int i = 0 ; i < length ; i++)
			{
				if(raise == 0)
				{
					s = s + " " + signal[i][0];
					if(signal[i][1] == 1)raise++;
					else if(signal[i][1] == 0 && raise != 0)raise--;
					size++;
				}
				else
				{
					if(signal[i][1] == 1)raise++;
					else if(signal[i][1] == 0 && raise != 0)raise--;
					if(raise == 0){s = s + " " + signal[i][0];size++;}
				}
			}
			System.out.println((size / 2) + s);
		}
		public void sort()
		{
			int min;
			int pos ;
			for(int i = length - 1 ; i >= 0 ; i--)
			{
				min = signal[i][0];
				pos = i;
				for(int j = i ; j >= 0 ; j--)
				{
					if(min < signal[j][0])
					{
						min = signal[j][0];
						pos = j;
					}
				}
				signal[pos][0] = signal[i][0];
				signal[i][0] = min;
				min = signal[pos][1];
				signal[pos][1] = signal[i][1];
				signal[i][1] = min;
			}
		}
		public static Signal addSignals(Signal s1,Signal s2)
		{
			Signal newSig = new Signal(s1.length + s2.length);
			for(int i = 0 ; i < s1.length ; i++)
			{
				newSig.signal[i][0] = s1.signal[i][0];
				newSig.signal[i][1] = s1.signal[i][1];
			}
			for(int i = 0 ; i < s2.length ; i++)
			{
				newSig.signal[i + s1.length][0] = s2.signal[i][0];
				newSig.signal[i + s1.length][1] = s2.signal[i][1];
			}
			return newSig;

		}
		int[][] signal;
		int length;
	}

