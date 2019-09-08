package models;

import java.util.Date;

public class Audit {
	private Integer id;
	private Integer employeeNumber;
	private Date dateTime;
	private Integer userId;
	private String activity;

	
	public Audit(Integer id,Integer employeeNumber, Date dateTime,Integer userId,String activity) {
		this.id=id;
		this.employeeNumber=employeeNumber;
		this.dateTime=dateTime;
		this.userId=userId;
		this.activity=activity;
	}
	public Audit(Integer id,Integer employeeNumber, Date dateTime,String activity) {
		this.id=id;
		this.employeeNumber=employeeNumber;
		this.dateTime=dateTime;
		this.activity=activity;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
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
