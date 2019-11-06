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
import com.arpit.dao.*;
import com.arpit.model.*;

@Controller
public class LawyerController {

	@Autowired
	public LawyerDao lawdao;
	@Autowired
	public Lawyer_contactDao lawcondao;
	
	
	
	
	@RequestMapping("admin/law")
	public String Allemp(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Lawyer> list=lawdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "lawAll";
	}
	
	@RequestMapping("admin/newlaw")
	public String Newempl(Model model)
	{
		Lawyer x=new Lawyer();
		model.addAttribute("lawyer",x);
		return "lawNew";
	}
	@RequestMapping(value = "admin/addlaw", method = RequestMethod.POST)
	public String Addemp(@ModelAttribute("lawer") Lawyer lawyer,Model model, HttpServletRequest request) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		String contact1=request.getParameter("contact1");
		String contact2=request.getParameter("contact2");
		Lawyer_contact x= new Lawyer_contact();
		x.setLawyer_id(lawyer.getLawyer_id());
		x.setContact(contact1);

		lawdao.create(conn, lawyer);
		lawcondao.create(conn, x);
		if(contact2!="")
		{
			x.setContact(contact2);
			lawcondao.create(conn, x);
		}
		conn.close();
		return "success";
	}

	
	
	
	@RequestMapping("admin/showlawcont/{id}")
	public String EmplCont(@PathVariable(value="id") int id,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Lawyer_contact x=new Lawyer_contact();
		x.setLawyer_id(id);
		
		List<Lawyer_contact> list=lawcondao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "showlawcon";
	}

	@RequestMapping("admin/showlawcont/dellawcont{id}/{contact}")
	public String DelEmplCont(@PathVariable(value="id") int id,@PathVariable(value="contact") String contact,Model model) throws SQLException, NotFoundException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Lawyer_contact x=new Lawyer_contact();
		x.setLawyer_id(id);
		x.setContact(contact);
		lawcondao.delete(conn, x);
		conn.close();
		return "redirect:/success";
	}
	
	@RequestMapping("admin/showlawcont/updatelawcont{id}/{contact}")
	public String UpdEmplCont(@PathVariable(value="id") int id, Model model,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
	{

		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Lawyer_contact x=new Lawyer_contact();
		model.addAttribute("lawyer_contact", x);
		model.addAttribute("id", id);
		model.addAttribute("contact", contact);
		return "lawcontupd";
	}
		@RequestMapping(value = "admin/showlawcont/updatelawcont{id}/updalawcont{id}/{contact}", method = RequestMethod.POST)
		public String UpEmplShow(@ModelAttribute("lawyer_contact") Lawyer_contact lawyer_contact,Model model,@PathVariable(value="id") int id,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
		{
			Connection conn =null;
			try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
			Lawyer_contact x=new Lawyer_contact();
			x.setLawyer_id(id);
			x.setContact(contact);
			lawcondao.delete(conn, x);
			Lawyer_contact y=new Lawyer_contact();
			y.setAll(id,lawyer_contact.getContact());
			//source_contacts.setSource_id(id);
			//System.out.println(employee_contacts.getEmployee_id());
		//	empcondao.delete(conn, employee_contacts);
			lawcondao.create(conn, y);
			conn.close();
			return "redirect:/success";
		}
	
}
