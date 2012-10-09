package woni.FireFighter;

public class Mission{

	String image;
	String date;
	String time;
	String kind;
	String command;
	String station;
	String count;
	String state;
	
	public Mission(String[] fields){
		this.image = fields[0];
		this.date = fields[1];
		this.time = fields[2];
		this.kind = fields[3];
		this.command = fields[4];
		this.station = fields[5];
		this.count = fields[6];
		this.state = fields[7];
	}
	

	public String getImage(){
		return image;
	}
	
	public String getDate(){
		return date;
	}
	
	public String getTime(){
		return time;
	}
	
	public String getKind(){
		return kind;
	}
	
	public String getCommand(){
		return command;
	}
	
	public String getStation(){
		return station;
	}
	
	public String getCount(){
		return count;
	}
	
	public String getState(){
		return state;
	}

	
}