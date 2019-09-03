package admin.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import admin.db.RoleService;
import models.Role;

@RestController
@RequestMapping("/roles")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
	public @ResponseBody List<Role> all() throws SQLException{
		return roleService.findAll();
	}
	

	private String getCurrentLocalDateTimeStamp(Date date) {
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
	}

	@GetMapping("/test")
	public String testing() throws SQLException {
		return getCurrentLocalDateTimeStamp(roleService.testTimezone());
	}
	
	

}
