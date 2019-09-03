package admin.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import admin.db.EmployeesService;
import models.Employees;

public class EmployeesController {
	@RestController
	@RequestMapping("/users")
	public class EmployeeController {
		@Autowired
		private EmployeesService employeesService;
//All Employees
	@GetMapping("")
	public @ResponseBody List<Employees> allEmployees() throws SQLException {
		return employeesService.findAll();
		}
	}
}
