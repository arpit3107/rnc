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
import com.arpit.dao.SourcesDao;
import com.arpit.model.Sources;
import com.arpit.dao.EmployeeDao;
import com.arpit.dao.Employee_contactsDao;
import com.arpit.dao.Source_contactsDao;
import com.arpit.model.Employee;
import com.arpit.model.Employee_contacts;
import com.arpit.model.Source_contacts;

@Controller
public class SourceController {

	@Autowired
	public SourcesDao srcdao;
	@Autowired
	public Source_contactsDao srccondao;
	
	@RequestMapping("admin/sour")
	public String empdash()
	{
		return "sourdash";
	}
	
	
	@RequestMapping("admin/allsour")
	public String Allemp(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Sources> list=srcdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "sourAll";
	}
	
	@RequestMapping("admin/newsour")
	public String Newempl(Model model)
	{

		Sources x=new Sources();
		model.addAttribute("sources",x);
		return "sourNew";
	}
	@RequestMapping(value = "admin/addsour", method = RequestMethod.POST)
	public String Addemp(@ModelAttribute("sources") Sources sources,Model model, HttpServletRequest request) throws SQLException
	{
		Connection conn =null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		String contact1=request.getParameter("contact1");
		String contact2=request.getParameter("contact2");
		Source_contacts x= new Source_contacts();
		x.setSource_id(sources.getSource_id());
		x.setContact(contact1);

		srcdao.create(conn, sources);
		srccondao.create(conn, x);
		if(contact2!="")
		{
			x.setContact(contact2);
			srccondao.create(conn, x);
		}
		conn.close();
		return "success";
	}

	

	///important
	@RequestMapping("admin/sourshow{id}")
	public String EmplSho(@PathVariable(value="id") int id,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}

		Sources x=new Sources();
		x=srcdao.getObject(conn, id);

		List<Sources> list=srcdao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "sourAll";
	}
	
	@RequestMapping("admin/showsourcont/{id}")
	public String EmplCont(@PathVariable(value="id") int id,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Source_contacts x=new Source_contacts();
		x.setSource_id(id);
		
		List<Source_contacts> list=srccondao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "showsourcon";
	}

	@RequestMapping("admin/showsourcont/delsourcont{id}/{contact}")
	public String DelEmplCont(@PathVariable(value="id") int id,@PathVariable(value="contact") String contact,Model model) throws SQLException, NotFoundException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}

		Source_contacts x=new Source_contacts();
		x.setSource_id(id);
		x.setContact(contact);
		srccondao.delete(conn, x);
		conn.close();
		return "redirect:/success";
	}
	
	@RequestMapping("admin/showsourcont/updatesourcont{id}/{contact}")
	public String UpdEmplCont(@PathVariable(value="id") int id, Model model,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
	{

		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Source_contacts x=new Source_contacts();
		model.addAttribute("source_contacts", x);
		model.addAttribute("id", id);
		model.addAttribute("contact", contact);
		return "sourcontupd";
	}
		@RequestMapping(value = "admin/showsourcont/updatesourcont{id}/updasourcont{id}/{contact}", method = RequestMethod.POST)
		public String UpEmplShow(@ModelAttribute("source_contacts") Source_contacts source_contacts,Model model,@PathVariable(value="id") int id,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
		{
			Connection conn =null;
			try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
			Source_contacts x=new Source_contacts();
			x.setSource_id(id);
			x.setContact(contact);
			srccondao.delete(conn, x);

			Source_contacts y=new Source_contacts();
			y.setAll(id,source_contacts.getContact());
			source_contacts.setSource_id(id);
			//System.out.println(employee_contacts.getEmployee_id());
		//	empcondao.delete(conn, employee_contacts);
			srccondao.create(conn, y);
			conn.close();
			return "redirect:/success";
		}
	
}
