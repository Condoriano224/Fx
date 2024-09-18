package ch30;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class AccountWithSemaphore {
	private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        
        // Create multiple deposit and withdraw tasks
        for (int i = 0; i < 50; i++) {
            executor.execute(new AddAPennyTask());
            executor.execute(new WithdrawTask());
        }
        
        executor.shutdown();
        while (!executor.isTerminated()) {}

        System.out.println("Final balance: " + account.getBalance());
    }

    public static class AddAPennyTask implements Runnable {
        public void run() {
            account.deposit(5);
        }
    }

    public static class WithdrawTask implements Runnable {
        public void run() {
            account.withdraw(3);
        }
    }

    public static class Account {
        private static Semaphore semaphore = new Semaphore(2);
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            try {
                semaphore.acquire();
                int newBalance = balance + amount;
                Thread.sleep(5); // Simulate delay
                balance = newBalance;
                
                System.out.println("Deposited " + amount + ", Balance: " + balance);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

        public void withdraw(int amount) {
            try {
                semaphore.acquire();
                if (balance >= amount) {
                    int newBalance = balance - amount;
                    Thread.sleep(5); // Simulate delay
                    balance = newBalance;
                    balance = newBalance;
                    System.out.println("Withdrawed " + amount + ", Balance: " + balance);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally 
            {
                semaphore.release();
            }
        }
    }

}
