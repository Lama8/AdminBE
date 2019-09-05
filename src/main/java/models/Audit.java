package models;

import java.util.Date;

public class Audit {
	private Integer id;
	
	private Date dateTime;
	private Integer userId;
	private String activity;

	
	public Audit(Integer id, Date dateTime,Integer userId,String activity) {
		this.id=id;
		this.dateTime=dateTime;
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
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
}
