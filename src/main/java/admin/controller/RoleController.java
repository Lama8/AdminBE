package admin.controller;

import java.sql.SQLException;
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
}