package ch30;

/**
 * @author asus
 *
 */
public class TaskThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create tasks
		Runnable printA=new PrintChar('a',100);
		Runnable printB=new PrintChar('b',100);
		Runnable print100=new PrintNum(100);
		
		//Create threads
		Thread thread1=new Thread(printA);
		Thread thread2=new Thread(printB);
		Thread thread3=new Thread(print100);
		
		//Start threads
		thread1.start();
		thread2.start();
		thread3.start();

	}

}

//Task for printing a char a special number of times
class PrintChar implements Runnable{
	private char charToPrint;
	private int times;
	public PrintChar(char c,int times){
		charToPrint=c;
		this.times=times;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<times;i++){
			System.out.print(charToPrint);
		}
		
	}
}

//Task for printing a number in a specific times
class PrintNum implements Runnable{
	private int lastNum;
	public PrintNum(int num){
		lastNum=num;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<lastNum;i++){
			System.out.print(" "+i);
		}
		
	}
	
}
