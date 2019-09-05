package admin.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import admin.db.EmployeesService;
import admin.db.RoleService;
import models.EmployeeData;
import models.Employees;
import models.Role;

@RestController
@RequestMapping("/users")
public class EmployeesController {
	@Autowired
	private EmployeesService employeesService;

	//All Employees
	@GetMapping("")
	public @ResponseBody List<Employees> all() throws SQLException {
		return employeesService.findAll();
		}
//	@GetMapping("/name")
//	public @ResponseBody Employees findEmployeeByName(@PathVariable String name) throws SQLException {
//		return employeesService.findByName(name);
//	}
	
}

