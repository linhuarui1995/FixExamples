package atmoerror;

public class Main {
    static Object o = new Object();

    public static void main(String[] args) {

        BankAccount account = new BankAccount();
        synchronized (account) {
            Thread t1 = new Thread(new Customer(5, account));
            Thread t2 = new Thread(new Customer(5, account));

            t1.start();
            t2.start();
        }

//	      for(int i = 0; i < 2; i++) {
//	          new Thread(new Customer(100, account)).start();
//	      }
	      /*try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      if(account.getTotal() != 500)
	    	  throw new RuntimeException();*/
    }
}
