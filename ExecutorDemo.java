package ch30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create a fixed thread pool
		ExecutorService executor=Executors.newFixedThreadPool(3);
		
		//Submit runnable tasks to the executor
		executor.execute(new PrintChar('a',100));
		executor.execute(new PrintChar('b',100));
		executor.execute(new PrintNum(100));
		
		//Shutdown the executor
		executor.shutdown();

	}

}
