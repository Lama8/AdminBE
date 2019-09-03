package models;

public class Employees {

	   private Integer id;
	   private Integer number;
	   private String name;//Concatenate firstName+lastName
	   private String worksite; // concatenated with country
	   private String department;
	
	   
	   public Employees(Integer id, Integer number, String name, String worksite, String department) {
		this.id = id;
		this.number = number;
		this.name = name;
		this.worksite = worksite;
		this.department = department;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorksite() {
		return worksite;
	}
	public void setWorksite(String worksite) {
		this.worksite = worksite;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

}
