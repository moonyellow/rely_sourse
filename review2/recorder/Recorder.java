package recorder;

import java.util.ArrayList;

import microbench.BenchSharedObject;
import microbench.BenchThread;
import scheduler.Scheduler;
import test.TestThread;

public class Recorder {
	private final static int NumberOfThreads = 2;
	private final static int TotalOperation= 500000;
	private final static int SPENumber = 5;

	
	public static void main(String[] args) throws InterruptedException{
		Thread t;
		Thread threadNow = Thread.currentThread();
		RecordTable retable = new RecordTable();
		RandomTable rantable = new RandomTable();
		
		//recorder
		char c;
		Scheduler sd = new Scheduler();
		
//		TestThread[] T = new TestThread[NumberOfThreads];
		
		
		//create working threads
		BenchThread[] T = new BenchThread[NumberOfThreads];
		BenchSharedObject s;
        for (int k=0;k<NumberOfThreads;k++) 
        {
        	s = new BenchSharedObject(SPENumber,retable,rantable);
        	T[k] = new BenchThread(s,TotalOperation/NumberOfThreads);
			sd.addThread(T[k]);
        }
        
        
		long st,et;
		st=System.currentTimeMillis();
  		
		
		//use sd to run scheduler
		for (int k=0;k<NumberOfThreads;k++)
		{
			T[k].start();
		}
		
//		sd.run();
		
		for (int k=0;k<NumberOfThreads;k++)
		{
			T[k].join();
		}
		
        et=System.currentTimeMillis();
		System.out.println(et-st);	
		BenchSharedObject.printTop5();
		
//write object
		RecordTable.writeObject();
		RandomTable.writeObject();
		System.out.println("=========================");

	}
	
}

