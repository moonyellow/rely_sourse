package test;

public class TestThread extends Thread {

	public String  threadName = "";
	public TestThread(String threadName){
		this.threadName = threadName;
	}
	@Override
	public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 1000; i++)
				System.out.print(threadName + i + " ");
		System.out.print(threadName);
	}

}