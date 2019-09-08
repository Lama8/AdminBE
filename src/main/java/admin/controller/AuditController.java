package admin.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import admin.db.AuditService;
import models.Audit;


@RestController
@RequestMapping("/audit")
public class AuditController {

	@Autowired
	private AuditService auditService;
	
	@GetMapping("")
	public @ResponseBody List<Audit> all() throws SQLException{
		return auditService.findAll();
	}
	


}
