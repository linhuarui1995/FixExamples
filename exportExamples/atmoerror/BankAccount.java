package atmoerror;

public class BankAccount {
	 private int total = 0;

	  public  void  add(int n) {
synchronized (){ 	      total += n;
}	  }

	  public  int getTotal() {
	      return total;
	  }
}
