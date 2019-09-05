
package models;

import java.util.List;

public class EmployeeData {
	private Integer id;
	private Integer number;
	private String firstName;
	private String lastName;
	private String email;
	private Integer managerId;
	private String managerName;
	private String department;
	private String workSite;
	private Integer workSiteId;
	private String country;
	private String phone;
	private Boolean loginStatus;
	private Boolean locked;
	private Boolean deactivated;
	private String password;
	private List<Role> roles;

	public EmployeeData() {

	}

	public EmployeeData(Integer id, String firstName) {
		this.id = id;
		this.firstName = firstName;
	}

	public EmployeeData(Integer id, Integer number, String firstName, String lastName, String email, String managerName,
			Integer managerId, String department, String workSite, Integer workSiteId, String country, String phone,
			Boolean loginStatus, Boolean locked, Boolean deactivated,String password) {
		this.id = id;
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.managerName = managerName;
		this.managerId = managerId;
		this.department = department;
		this.workSite = workSite;
		this.workSiteId = workSiteId;
		this.country = country;
		this.phone = phone;
		this.loginStatus = loginStatus;
		this.locked = locked;
		this.deactivated = deactivated;
		this.password=password;
	}

	public EmployeeData(Integer id, Integer number, String firstName, String lastName, String email, Integer managerId,
			String department, Integer workSiteId, String country, String phone, Boolean loginStatus, Boolean locked,
			Boolean deactivated,String password) {
		this.id = id;
		this.number = number;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.managerId = managerId;
		this.department = department;
		this.workSiteId = workSiteId;
		this.country = country;
		this.phone = phone;
		this.loginStatus = loginStatus;
		this.locked = locked;
		this.deactivated = deactivated;
		this.password=password;
	}

	public EmployeeData(Integer id, Integer number, String firstName, String lastName, String email, String managerName,
			Integer managerId, String department, String workSite, Integer workSiteId, String country, String phone,
			Boolean loginStatus, Boolean locked, Boolean deactivated,String password, List<Role> roles) {
		this(id, number, firstName, lastName, email, managerName, managerId, department, workSite, workSiteId, country,
				phone, loginStatus, locked, deactivated,password);
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "EmployeeData [id=" + id + ", number=" + number + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", managerId=" + managerId + ", managerName=" + managerName + ", department="
				+ department + ", workSite=" + workSite + ", workSiteId=" + workSiteId + ", country=" + country
				+ ", phone=" + phone + ", loginStatus=" + loginStatus + ", locked=" + locked + ", deactivated="
				+ deactivated + ", password=" + password + ", roles=" + roles + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getWorkSiteId() {
		return workSiteId;
	}

	public void setWorkSiteId(Integer workSiteId) {
		this.workSiteId = workSiteId;
	}

	public String getWorkSite() {
		return workSite;
	}

	public void setWorkSite(String workSite) {
		this.workSite = workSite;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getnumber() {
		return number;
	}

	public void setnumber(Integer number) {
		this.number = number;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getDeactivated() {
		return deactivated;
	}

	public void setDeactivated(Boolean deactivated) {
		this.deactivated = deactivated;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}