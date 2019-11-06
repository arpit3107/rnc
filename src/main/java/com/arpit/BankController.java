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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.arpit.dao.BankDao;
import com.arpit.model.Bank;
import com.arpit.model.Employee;

@Controller
public class BankController {
	
	@Autowired
	public BankDao bankdao;
	
	@RequestMapping("house")
	public String fun()
	{
		return "redirect:/admin";
	}
	@GetMapping("admin/bankall")
	public String showbank(Model model) throws SQLException
	{
		Connection conn =null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Employee> list=bankdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "showbank";
	}
	@RequestMapping("admin/newaccount")
	public String Addaccount(Model model)
	{
		Bank bank=new Bank();
		model.addAttribute("bank", bank);
		return "addaccount";
	}
	@RequestMapping(value = "admin/addacc", method = RequestMethod.POST)
	public String Addemp(@ModelAttribute("bank") Bank bank,Model model) throws SQLException
	{
		Connection conn =null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		bankdao.create(conn, bank);
		conn.close();
		return "success";
	}
	@RequestMapping(value = "admin/accountupd/{account_number}")
	public String UpdateAccount(@PathVariable(value="account_number") String account_number,Model model)
	{
		Bank bank=new Bank();
		model.addAttribute("bank", bank);
		model.addAttribute("account_number", account_number);
		return "updacc";
	}
	@RequestMapping(value = "admin/accountupd/updateacc/{account_number}", method = RequestMethod.POST)
	public String AccUpd(@ModelAttribute("bank") Bank bank,Model model,@PathVariable(value="account_number") String account_number) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Bank x=bankdao.getObject(conn, account_number);
		x.setBalance(bank.getBalance());
		bankdao.save(conn, x);
		conn.close();
		return "redirect:/success";
	}@RequestMapping("admin/house")
	public String fun1()
	{
		return "redirect:/admin";
	}
}