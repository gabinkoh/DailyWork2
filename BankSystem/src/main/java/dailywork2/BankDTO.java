package dailywork2;

public class BankDTO {

	private String userid;
	private String accountnum;
	private int balance;
	private boolean result;
	
	public BankDTO() {
	}
	
	

	public BankDTO(String userid, String accountnum, int balance) {
		this.userid = userid;
		this.accountnum = accountnum;
		this.balance = balance;
	}



	public boolean isResult() {
		return result;
	}



	public void setResult(boolean result) {
		this.result = result;
	}



	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAccountnum() {
		return accountnum;
	}
	public void setAccountnum(String accountnum) {
		this.accountnum = accountnum;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}
