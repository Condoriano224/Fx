package ch30;

import java.util.concurrent.*;

public class ConsumerProducerUsingBlockingQueue {
	private static ArrayBlockingQueue<Integer> buffer=new ArrayBlockingQueue<>(2);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exe=Executors.newFixedThreadPool(2);
		exe.execute(new ProducerTask());
		exe.execute(new ConsumerTask());
		exe.shutdown();

	}
	
	private static class ProducerTask implements Runnable{
		public void run(){
			try
			{
				int i=1;
				while (true){
					System.out.println("Producer writes "+i);
					buffer.put(i++);
					Thread.sleep((int)(Math.random()*10000));
				}
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
	}
	
	private static class ConsumerTask implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try
			{
				while(true){
					System.out.println("\t\t\tConsumer read "+buffer.take());
					Thread.sleep((int)(Math.random()*10000));
				}
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}

}
