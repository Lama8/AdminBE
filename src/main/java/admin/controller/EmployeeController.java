package admin.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import admin.db.EmployeeDataService;
import admin.db.IdException;
import models.Department;
import models.EmployeeData;
import models.Role;
import models.WorkSite;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeDataService employeeDataService;
//Employee
	@GetMapping("/{id}")
	public @ResponseBody EmployeeData findEmployeeById(@PathVariable int id) throws SQLException {
		return employeeDataService.find(id);
	}
//Select Work Sites
	@GetMapping("/WorkSites")
	public @ResponseBody List<WorkSite> allSites() throws SQLException {
		return employeeDataService.findAllSites();
	}
//Select Roles
	@GetMapping("/roles")
    public @ResponseBody List<Role> allRoles() throws SQLException {
        return employeeDataService.findAllRoles();
    }
//Select Departments
    @GetMapping("/departments")
    public @ResponseBody List<Department> allDepartments() throws SQLException {
        return employeeDataService.findAllDepartments();
    }
//Select Managers	
    @GetMapping("/Managers")
    public @ResponseBody List<EmployeeData> allManagers() throws SQLException {
    	return employeeDataService.findAllManagers();
    }
//Add Employee
    @PostMapping("")
    public String newEmployee(@RequestBody EmployeeData employee) throws SQLException ,IdException{
    	EmployeeData isOK = employeeDataService.add(employee);
    	return isOK.toString();
    }
//Update Employee
    @PutMapping("/{id}")
    public @ResponseBody EmployeeData updateEmployeeInfo(@PathVariable int id, @RequestBody EmployeeData employee) throws SQLException {
    	EmployeeData isOK = employeeDataService.update(employee);
    	return isOK;
    }
	

//Delete Employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Integer id) throws SQLException {
    	EmployeeData isOK = employeeDataService.delete(id);
    	return isOK.toString();
    }
	
//Unlock Employee
    @PutMapping("/unlock/{id}")
    public String  unlockEmployee(@PathVariable Integer id) throws SQLException {
    	EmployeeData isOK = employeeDataService.unlock(id);
    	return isOK.toString();
    }
}