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

import com.arpit.model.Machinery;
import com.arpit.dao.MachineryDao;

@Controller
public class MachineryController{
	
	@Autowired
	public MachineryDao machinedao;
	
	@RequestMapping("admin/machine")
	public String Show()
	{
		return "machinedash";
	}
	@RequestMapping("admin/allmachine")
	public String Showall(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Machinery> list=machinedao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "machineAll";
	}
	@RequestMapping("admin/newmachine")
	public String Newtender(Model model)
	{
		Machinery machinery=new Machinery();
		model.addAttribute("machinery",machinery);
		return "machineNew";
	}
	@RequestMapping(value = "admin/addmachine", method = RequestMethod.POST)
	public String Addtender(@ModelAttribute("machinery") Machinery machinery,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		machinedao.create(conn, machinery);
		conn.close();
		return "success";
	}
}