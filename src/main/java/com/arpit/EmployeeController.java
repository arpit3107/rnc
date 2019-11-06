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
public class EmployeeController {
	
	@Autowired
	public EmployeeDao empdao;
	@Autowired
	public Employee_contactsDao empcondao;
	@RequestMapping("admin/empl")
	public String empdash()
	{
		return "empdash";
	}
	@RequestMapping(value = "success", method = { RequestMethod.GET, RequestMethod.POST })
	public String succ()
	{
		return "success";
	}
	
	@RequestMapping("admin/allempl")
	public String Allemp(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Employee> list=empdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "emplAll";
	}
	
	@RequestMapping("admin/newempl")
	public String Newempl(Model model)
	{
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "emplNew";
	}
	@RequestMapping(value = "admin/addemp", method = RequestMethod.POST)
	public String Addemp(@ModelAttribute("employee") Employee employee,Model model, HttpServletRequest request) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		String contact1=request.getParameter("contact1");
		String contact2=request.getParameter("contact2");
		Employee_contacts x= new Employee_contacts();
		x.setEmployee_id(employee.getId());
		x.setContact(contact1);

		empdao.create(conn, employee);
		empcondao.create(conn, x);
		if(contact2!="")
		{
			x.setContact(contact2);
			empcondao.create(conn, x);
		}
		conn.close();
		return "success";
	}
	@RequestMapping("admin/emplsearch")
	public String Emplid(Model model)
	{
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "emplfind";
	}
	@RequestMapping(value = "admin/emplshow", method = RequestMethod.POST)
	public String EmplShow(@ModelAttribute("employee") Employee employee,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Employee> list=empdao.searchMatching(conn, employee);
		conn.close();
		model.addAttribute("list", list);
		//model.addAttribute("id", employee.getId());
		return "emplAll";
	}
	@RequestMapping("admin/deleteempl{id}")
	public String Empldel(@PathVariable(value="id") int id) throws NotFoundException, SQLException
	{
		
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}

		Employee_contacts y=new Employee_contacts();
		y.setEmployee_id(id);
		empcondao.remove(conn, y);
		Employee x=new Employee();
		x.setId(id);
		empdao.delete(conn, x);
		conn.close();
		return "success";
	}
	
	///important
	@RequestMapping("admin/empshow{id}")
	public String EmplSho(@PathVariable(value="id") int id,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Employee x=new Employee();
		x=empdao.getObject(conn, id);

		List<Employee> list=empdao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "emplAll";
	}
	
	@RequestMapping("admin/showemplcont/{id}")
	public String EmplCont(@PathVariable(value="id") int id,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Employee_contacts x=new Employee_contacts();
		x.setEmployee_id(id);
		
		List<Employee_contacts> list=empcondao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "showemplcon";
	}

	@RequestMapping("admin/showemplcont/delempcont{id}/{contact}")
	public String DelEmplCont(@PathVariable(value="id") int id,@PathVariable(value="contact") String contact,Model model) throws SQLException, NotFoundException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Employee_contacts x=new Employee_contacts();
		x.setEmployee_id(id);
		x.setContact(contact);
		empcondao.delete(conn, x);
		conn.close();
		return "redirect:/success";
	}
	
	@RequestMapping("admin/showemplcont/updateempcont{id}/{contact}")
	public String UpdEmplCont(@PathVariable(value="id") int id, Model model,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
	{

		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Employee_contacts x=new Employee_contacts();
		model.addAttribute("employee_contacts", x);
		model.addAttribute("id", id);
		model.addAttribute("contact", contact);
		return "empcontupd";
	}
		@RequestMapping(value = "admin/showemplcont/updateempcont{id}/updaempcont{id}/{contact}", method = RequestMethod.POST)
		public String UpEmplShow(@ModelAttribute("employee_contacts") Employee_contacts employee_contacts,Model model,@PathVariable(value="id") int id,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
		{
			Connection conn =null;
			try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
			Employee_contacts x=new Employee_contacts();
			x.setEmployee_id(id);
			x.setContact(contact);
			empcondao.delete(conn, x);
			
			Employee_contacts y=new Employee_contacts();
			y.setAll(id,employee_contacts.getContact());
			employee_contacts.setEmployee_id(id);
			//System.out.println(employee_contacts.getEmployee_id());
		//	empcondao.delete(conn, employee_contacts);
			empcondao.create(conn, y);
			conn.close();
			return "redirect:/success";
		}
	
}