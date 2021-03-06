package woni.FireFighter.Common;

import java.util.ArrayList;
import java.util.HashMap;

public class Mission{

	String image;
	String date;
	String time;
	String kind;
	String alarm;
	String station;
	String count;
	String state;
	Boolean onDuty = false;
	ArrayList<String> units;
	
	
	public Mission(String[] fields){
		this.image = fields[0];
		this.date = fields[1];
		this.time = fields[2];
		this.kind = fields[3];
		this.alarm = fields[4];
		this.station = fields[5];
		this.count = fields[6];
		this.units = new ArrayList<String>();
		
		String currentState = fields[7];
		
		if(currentState.equals("<img src='LLZ/9.png'>")){
			this.state = "Im Einsatz";
			this.onDuty = true;
		}else if (currentState.equals("<img src='LLZ/99.png'>"))
			this.state = "Einsatz beendet";
		else
			this.state = "";
	}
	
	public Mission(String date, String time, String alarm, String station) {
		this.date = date;
		this.time = time;
		this.alarm = alarm;
		this.station = station;
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
	
	public String getAlarm(){
		return alarm;
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
	
	public Boolean getOnDuty(){
		return onDuty;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(!(o instanceof Mission))
			return false;
		if(o == this)
			return true;
		
		Mission other = (Mission)o;
		
		return station.equals(other.station) && date.equals(other.date) && time.equals(other.time);
	}
	
	@Override
	public int hashCode() {
	      return 17 * station.hashCode() * date.hashCode() * time.hashCode();
	}

	public void setUnits(ArrayList<String> units) {
		this.units = units;
		
	}
	
	public ArrayList<String> getUnits(){
		return this.units;
	}
}