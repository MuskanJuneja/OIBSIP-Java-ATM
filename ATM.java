import java.util.Scanner;
import java.util.ArrayList;
public class ATM {
	int amount=100000;
	ArrayList<String> hist=new ArrayList<String>();
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
	    int pass= 123;
	    int attempt=3;
	    System.out.println("-----WELCOME TO MUSKAN ATM--------");
	    System.out.println("Please swipe your Card");
	    while(attempt>0) {  //PIN AUTHENTICATION....
	    System.out.println("Please Enter Your Secret Number: ");
		int pin=scan.nextInt();
	    if(pin==pass) {
	    	System.out.println("Correct PIN..");
	    	ATM a=new ATM();
	    	a.Start();
	    	break;
	    } 
	    else {
	    	attempt=attempt-1;
	    	System.out.println("Incorrect PIN...0"+attempt+" Attempts left..");
	    }
	    }
	    if(attempt==0) {
	    System.out.println("Login session FAILED! due to 3 wrong attempts..");
	    System.exit(0);
	    }
	}
	public void Start() {
		boolean cont=true;
		Scanner scan=new Scanner(System.in);
		do {
			System.out.println(" ");
			System.out.println("Your current balance is "+amount);
			System.out.println("1 for Withdraw");
			System.out.println("2 for Deposit");
			System.out.println("3 for Transfer");
			System.out.println("4 for Transaction History");
			System.out.println("5 for Exit");
			System.out.println("Please enter the number for the operation");
			int ch=scan.nextInt();
			switch(ch) 
			{
			   case 1: System.out.println("Enter the amount you wish to withdraw: ");
			           int with=scan.nextInt();
			           Withdraw w=new Withdraw();
			           amount=w.deduct(amount,with,hist);
			           break;
			   case 2: System.out.println("Enter the amount you wish to Deposit: ");
	                   int dep=scan.nextInt();
	                   Deposit d=new Deposit();
	                   amount=d.add(amount,dep,hist);
	                   break;
			   case 3: System.out.println("Enter the amount you wish to Transfer: ");
	                   int trans=scan.nextInt();
			           Transfer t=new Transfer();
			           amount=t.transfer(amount,trans,hist);
			           break;
			   case 4: 
			           History h=new History();
			           h.show(hist);
			           break;
			   case 5: System.out.println("Thankyou for using our Services..! Do visit again!");
			           cont=false;
			           break;
			   default: System.out.println("Please enter correct number");
			            break;
			}
			
		}while(cont);
	}
}
class Withdraw{
	public int deduct(int amt,int n,ArrayList<String> hist) {
		if(amt>=n) {
			amt=amt-n;
			System.out.println("Amount of Rs "+n+" is successfully Withdrawn!!...");
			String s="*[Withdrawn Rs "+n+" ]";
			hist.add(s);
			return amt;
		}
		else {
			System.out.println("Insufficient balance!!");
			return amt;
		}
	}
}
class Deposit{
	public int add(int amt,int n,ArrayList<String> hist){
		amt=amt+n;
		System.out.println("Amount of Rs "+n+" successfully deposited!!...");
		System.out.println("Your new updated balance is: "+amt+" !!....");
		String s="*[Deposited Rs "+n+" ]";
		hist.add(s);
		return amt;
	}
}
class Transfer{
	public int transfer(int amt,int n,ArrayList<String> hist) {
		if(amt>=n) {
			Scanner scan=new Scanner(System.in);
			System.out.println("Enter the person you wish to transfer: ");
			String name=scan.nextLine();
			amt=amt-n;
			System.out.println("Successful Transaction of Rs "+n+" to "+name+" !...");
			String s="*[Transferred Rs "+n+" to "+name+" ]";
			hist.add(s);
			return amt;
		}
		else {
			System.out.println("Insufficient Balance in the Account!...");
			return amt;
		}
	}
}
class History{
	public void show(ArrayList<String> hist) {
		if(hist.size()>0) {
		System.out.println("Your Transaction history is: ");
		for(int i=0;i<hist.size();i++) {
			System.out.println(hist.get(i));
		}
		}
		else {
			System.out.println("No Transaction History!!..");
		}
	}
}