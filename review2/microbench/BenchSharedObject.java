package microbench;

import java.util.Random;

import recorder.RandomTable;
import recorder.RecordTable;

public class BenchSharedObject {
	public static RecordTable Retable = null;
	public static RandomTable Rantable = null;
	public boolean record = true;

	public static void printTop5()
	{
		System.out.println("total sum=" + (data_0+data_1+data_2+data_3+data_4));
		System.out.println(data_0);
		System.out.println(data_1);
		System.out.println(data_2);
		System.out.println(data_3);
		System.out.println(data_4);
	}

	static int data_0=0;
	static int data_1=0;
	static int data_2=0;
	static int data_3=0;
	static int data_4=0;

	Random r;

	int size;

	public BenchSharedObject(int size,RecordTable rt, RandomTable rant)
	{
		this.r = new Random();
		this.size = size;
		if(Retable==null)
			Retable = rt;
		if(Rantable==null)
			Rantable = rant;
	}
	public void updateData() {

		int index=0;

		if(record==true)
		{
			index= r.nextInt()%size;
			if(index<0)index=-index;
			Rantable.record(Thread.currentThread().getName(), index);
			Retable.record(index+"", Thread.currentThread().getName());
		}

		if(record==false)
		{
			index = Rantable.get(Thread.currentThread().getName());
			Retable.check(index+"", Thread.currentThread().getName());
//			System.out.println(Thread.currentThread().getName()+"="+index);
		}

		switch(index){
			case 0:
				data_0++;break;
			case 1:
				data_1++;break;
			case 2:
				data_2++;break;
			case 3:
				data_3++;break;
			case 4:
				data_4++;break;
			default:
				break;
		}

	}

}
