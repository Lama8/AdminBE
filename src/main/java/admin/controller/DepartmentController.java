package admin.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import admin.db.DepartmentService;
import admin.db.IdException;
import models.Department;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	//Add department
  	@PostMapping("")
	public String newDepartment(@RequestBody Department department) throws SQLException, IdException {
 		Department isOk=departmentService.add(department);
  		return isOk.toString();
  		}
}
