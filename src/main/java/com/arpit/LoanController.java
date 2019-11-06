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
import com.arpit.dao.LoanDao;
import com.arpit.dao.TendersDao;
import com.arpit.model.Employee;
import com.arpit.model.Employee_contacts;
import com.arpit.model.Loan;
import com.arpit.model.Tenders;

@Controller
public class LoanController{
	
	@Autowired
	public LoanDao loandao;
	
	@RequestMapping("admin/loan")
	public String Show()
	{
		return "loandash";
	}
	@RequestMapping("admin/allloan")
	public String Showall(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Loan> list=loandao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "loanAll";
	}
	
	
	@RequestMapping("admin/newloan")
	public String Newtender(Model model)
	{
		Loan loan=new Loan();
		model.addAttribute("loan",loan);
		return "loanNew";
	}
	@RequestMapping(value = "admin/addloan", method = RequestMethod.POST)
	public String Addtender(@ModelAttribute("loan") Loan loan,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		loandao.create(conn, loan);
		conn.close();
		return "success";
	}
	@RequestMapping("admin/deleteloan{id}")
	public String Empldel(@PathVariable(value="id") int id) throws NotFoundException, SQLException
	{
		
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Loan x=new Loan();
		x.setLoan_id(id);
		loandao.delete(conn, x);
		conn.close();
		return "success";
	}
	
}