package test;

import java.util.ArrayList;

import scheduler.Scheduler;

public class MainTest {
	private final static int NumberOfThreads = 5;
	public static void main(String[] args) throws InterruptedException{
		Thread t;
		Thread threadNow = Thread.currentThread();

		//Order list
		ArrayList<String> threadOrderlist = new ArrayList<String>();
		
		//recorder
		char c;
		Scheduler sd = new Scheduler();
		Scheduler sd2 = new Scheduler();
		for(int i=0;i<NumberOfThreads;i++)
		{
			c = (char) ('A' + i);
			t = new TestThread(c+"");
//insert	--lock--
			threadOrderlist.add(threadNow.getName());
			sd.addThread(t);
		}
		
		sd.run();
		
		System.out.println("gaga");
		for(int i=0;i<NumberOfThreads;i++)
		{

			c = (char) ('A' + i);
			t = new TestThread(c+"");
//insert	--lock--
			while(!threadNow.getName().equals(threadOrderlist.get(0)))
				Thread.sleep(100);
			sd2.addThread(t);
			threadOrderlist.remove(0);
		}
		
		sd2.run();
	}
}