package recorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class RandomTable implements Serializable {
	public static LinkedHashMap<String,ArrayList<Integer>> table = new LinkedHashMap<String,ArrayList<Integer>>();	
	private static final String ObjectsFilePath = "/Users/yuehuang/Desktop/objfile/randomTable_obj";
	
	RandomTable()
	{;}
	
	public RandomTable(String fileName)
	{
		table = null;
	    try
	    {
	     // FileInputStream inputFileStream = new FileInputStream(ObjectsFilePath);
	      FileInputStream inputFileStream = new FileInputStream(ObjectsFilePath);
	      ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
	      table = (LinkedHashMap<String,ArrayList<Integer>>)objectInputStream.readObject();
	      objectInputStream.close();
	      inputFileStream.close();
	    }
	    catch(ClassNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch(IOException i)
	    {
	      i.printStackTrace();
	    }
	}


	public void record(String threadName, int randomValue)
	{
		synchronized(this)
		{
			if(!table.containsKey(threadName))
				table.put(threadName, new ArrayList<Integer>());
			table.get(threadName).add(randomValue);
		}
	}

	public int get(String threadName)
	{
		int randomValue =0;
		synchronized(this)
		{
			randomValue = table.get(threadName).get(0);
			table.get(threadName).remove(0);
		}
		return randomValue;
	}

	public static void writeObject()
	{
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File(ObjectsFilePath)));
			// do the magic  
			oos.writeObject(table);
			// close the writing.
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}