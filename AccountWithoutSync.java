package ch30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountWithoutSync {//30.4
	private static Account account=new Account();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor=Executors.newCachedThreadPool();
		
		for(int i=0;i<100;i++){
			executor.execute(new AddAPennyTask());
		}
		executor.shutdown();
		
		while(!executor.isTerminated()){
			
		}
		System.out.println("What is balance? "+account.getBalance());

	}
	
	//A thread for adding a penny to the account
	private static class AddAPennyTask implements Runnable{
		
		public void run(){
			account.deposit(2);
		}
	}
	
	//An inner class for account
	private static class Account{
		private static Lock lock=new ReentrantLock();
		private int balance=0;
		public int getBalance(){
			return balance;
		}
		public void deposit(int amount){
			//int newBalance=balance+amount;
			//Using lock
			lock.lock();
			try
			{
				//Using lock
				int newBalance=balance+amount;
				Thread.sleep(5);
				//Using lock
				balance=newBalance;
			}
			catch (InterruptedException e) {
				// TODO: handle exception
			}
			//balance=newBalance;
			//Using lock
			finally{
				lock.unlock();
			}
		}
	}

}
