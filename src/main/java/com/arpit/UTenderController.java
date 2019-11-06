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

import com.arpit.model.Employee;
import com.arpit.model.Employee_contacts;
import com.arpit.model.Tenders;
import com.arpit.dao.TendersDao;

@Controller
public class UTenderController{
	
	@Autowired
	public TendersDao tenderdao;
	
	@RequestMapping("usertender")
	public String Show()
	{
		return "usertenderdash";
	}
	@RequestMapping("useralltender")
	public String Showall(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Tenders> list=tenderdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "usertenderAll";
	}
	@RequestMapping("usertendersearch")
	public String Tender(Model model)
	{
		Tenders tenders=new Tenders();
		model.addAttribute("tenders",tenders);
		return "usertenderfind";
	}
	@RequestMapping(value = "usertendershow", method = RequestMethod.POST)
	public String EmplShow(@ModelAttribute("tenders") Tenders tenders,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Tenders> list=tenderdao.searchMatching(conn, tenders);
		conn.close();
		model.addAttribute("list", list);
		//model.addAttribute("id", employee.getId());
		return "usertenderAll";
	}
	
	@RequestMapping("usershowtender{id}")
	public String Empldel1(@PathVariable(value="id") int id) throws NotFoundException, SQLException
	{
		
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Tenders x=new Tenders();
		x.setTender_id(id);
		List<Tenders> list=tenderdao.searchMatching(conn, x);
		conn.close();
		return "usertenderAll";
	}
	
}