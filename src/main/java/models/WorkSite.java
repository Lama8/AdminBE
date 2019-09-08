package models;

public class WorkSite {
	private Integer id;
	private String name;
	private Integer countryId;
	
	public WorkSite(Integer id,String name) {
		this.id=id;
		this.name=name;
	}
	public WorkSite(Integer id,String name,Integer countryId) {
		this(id,name);
		this.countryId=countryId;
	}

	@Override
	public String toString() {
		return "WorkSite [id=" + id + ", name=" + name + ", countryId=" + countryId + "]";
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
