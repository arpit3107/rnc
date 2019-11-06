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
import com.arpit.dao.SuppliersDao;
import com.arpit.dao.Supplier_contactDao;
import com.arpit.model.Suppliers;
import com.arpit.model.Employee;
import com.arpit.model.Supplier_contact;

@Controller
public class USupplierController {

	@Autowired
	public SuppliersDao suppdao;
	@Autowired
	public Supplier_contactDao suppcondao;
	
	@RequestMapping("usersupp")
	public String empdash()
	{
		return "usersuppdash";
	}
	
	
	@RequestMapping("userallsupp")
	public String Allemp(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Suppliers> list=suppdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "usersuppAll";
	}
	

	
	@RequestMapping("usersuppsearch")
	public String Emplid(Model model)
	{	
		Suppliers x=new Suppliers();
		model.addAttribute("suppliers",x);
		return "usersuppfind";
	}
	@RequestMapping(value = "usersuppshow", method = RequestMethod.POST)
	public String EmplShow(@ModelAttribute("suppliers") Suppliers suppliers,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Suppliers> list=suppdao.searchMatching(conn, suppliers);
		conn.close();
		model.addAttribute("list", list);
		//model.addAttribute("id", employee.getId());
		return "usersuppAll";
	}

	
	@RequestMapping("usershowsuppcont/{id}")
	public String EmplCont(@PathVariable(value="id") int id,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Supplier_contact x=new Supplier_contact();
		x.setSupplier_id(id);
		
		List<Supplier_contact> list=suppcondao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "usershowsuppcon";
	}


	
}
