package com.arpit;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arpit.dao.PartnersDao;
import com.arpit.model.Employee;
import com.arpit.model.Partners;

@Controller
public class PartnersController {

	@Autowired
	public PartnersDao partnersdao;
	@RequestMapping("admin/allpartners")
	public String part(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Partners> list=partnersdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "partners";
	}
	@RequestMapping("admin/updpart")
	public String updpa(Model model)
	{
		Partners partners=new Partners();
		model.addAttribute("partners", partners);
		return "updpartner";
	}
	@RequestMapping(value="admin/partupd",method=RequestMethod.POST)
	public String uppart(@ModelAttribute("partners") Partners partners,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Partners x= new Partners();
		x=partnersdao.getObject(conn, partners.getName());
		partners.setRepresentative(x.getRepresentative());
		partners.setTotal_share(x.getTotal_share());
		partnersdao.save(conn, partners);
		conn.close();
		return "success";
	}
	@RequestMapping("admin/partners{name}")
	public String partSho(@PathVariable(value="name") String name,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Partners x=new Partners();
		x.setName(name);

		List<Partners> list=partnersdao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "partners";
	}
}
