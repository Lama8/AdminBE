package models;

import java.sql.Time;
import java.util.Date;

public class Audit {
	private Integer id;
	//delete the time ...just datetime
	
	private Date date;
	private Time time;
	private Integer userId;
	private String activity;

	
	public Audit(Integer id, Date date,Time time,Integer userId,String activity) {
		this.id=id;
		this.date=date;
		this.time=time;
		this.userId=userId;
		this.activity=activity;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
}
