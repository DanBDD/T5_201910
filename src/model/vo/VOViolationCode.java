package model.vo;

public class VOViolationCode  implements Comparable<VOViolationCode> {
	private String code;
	private double amt;
	
	
	public VOViolationCode(String code, double amt){
		this.code=code;
		this.amt=amt;
	}

	public String getViolationCode() {
		return code;
	}
	
	public double getAvgFineAmt() {
		return amt;
	}
	@Override
	public int compareTo(VOViolationCode o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
