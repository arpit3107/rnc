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

import com.arpit.dao.EmployeeDao;
import com.arpit.model.Employee;
import com.arpit.model.Employee_contacts;
import com.arpit.dao.Employee_contactsDao;

@Controller
public class UEmployeeController {
	
	@Autowired
	public EmployeeDao empdao;
	@Autowired
	public Employee_contactsDao empcondao;
	@RequestMapping("userempl")
	public String empdash()
	{
		return "userempdash";
	}
	@RequestMapping(value = "usersuccess", method = { RequestMethod.GET, RequestMethod.POST })
	public String succ()
	{
		return "usersuccess";
	}
	
	@RequestMapping("userallempl")
	public String Allemp(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Employee> list=empdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "useremplAll";
	}
	
	
	@RequestMapping("useremplsearch")
	public String Emplid(Model model)
	{
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "useremplfind";
	}
	@RequestMapping(value = "useremplshow", method = RequestMethod.POST)
	public String EmplShow(@ModelAttribute("employee") Employee employee,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Employee> list=empdao.searchMatching(conn, employee);
		conn.close();
		model.addAttribute("list", list);
		//model.addAttribute("id", employee.getId());
		return "useremplAll";
	}

	///important
	@RequestMapping("userempshow{id}")
	public String EmplSho(@PathVariable(value="id") int id,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Employee x=new Employee();
		x=empdao.getObject(conn, id);

		List<Employee> list=empdao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "useremplAll";
	}
	
	@RequestMapping("usershowemplcont/{id}")
	public String EmplCont(@PathVariable(value="id") int id,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Employee_contacts x=new Employee_contacts();
		x.setEmployee_id(id);
		
		List<Employee_contacts> list=empcondao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "usershowemplcon";
	}

	
}