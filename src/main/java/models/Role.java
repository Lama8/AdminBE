package models;

import java.util.ArrayList;
import java.util.List;

public class Role {
	private Integer id;
	private String name;
	private String description;
	private List<Permission> permissions;
	
	public Role(String name) {
		this.name=name;
	}
	public Role(Integer id,String name) {
		this.id=id;
		this.name=name;
	}
	public Role(Integer id,String name, List<Permission> arrayList) {
		this(id, name);
		this.permissions = arrayList;
	}
	
	public Role(Integer id,String name,String description, ArrayList<Permission> arrayList) {
		this(id, name);
		this.description=description;
		this.permissions = arrayList;
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}