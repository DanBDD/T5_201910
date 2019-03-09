package model.vo;

public class LocationVO implements Comparable<LocationVO>{

	private int adressID;
	
	private String location;
	
	private int numberOfRegisters;
	
	@Override
	public int compareTo(LocationVO o) {
		
		if((numberOfRegisters - o.numberOfRegisters) > 0){
			return 1;
		}
		else if((numberOfRegisters - o.numberOfRegisters) < 0){
			return -1;
		}
		else{
			if((location.com))
		}
	}

}
