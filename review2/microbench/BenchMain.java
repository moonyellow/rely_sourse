package microbench;

public class BenchMain {
	
	static int NumOfThread=10;
	static int TotalOperation=10000000;
	static int SPENumber = 10;

	public static void main( String[] args)
	{
		
		try
		{
			BenchThread[] T = new BenchThread[NumOfThread];
			BenchSharedObject s;
	        for (int k=0;k<NumOfThread;k++) 
	        {
	        	s = new BenchSharedObject(SPENumber);
	        	T[k] = new BenchThread(s,TotalOperation/NumOfThread);
	        }
	        
			long st,et;
			st=System.currentTimeMillis();
	  		
			for (int k=0;k<NumOfThread;k++)
			{
				T[k].start();
			}
			
			for (int k=0;k<NumOfThread;k++)
			{
				T[k].join();
			}
			
	        et=System.currentTimeMillis();
			System.out.println(et-st);	
			BenchSharedObject.printTop5();
		}catch(Exception e){}
	}
}