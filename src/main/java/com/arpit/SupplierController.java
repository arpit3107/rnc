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
public class SupplierController {

	@Autowired
	public SuppliersDao suppdao;
	@Autowired
	public Supplier_contactDao suppcondao;
	
	@RequestMapping("admin/supp")
	public String empdash()
	{
		return "suppdash";
	}
	
	
	@RequestMapping("admin/allsupp")
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
		return "suppAll";
	}
	
	@RequestMapping("admin/newsupp")
	public String Newempl(Model model)
	{
		Suppliers x=new Suppliers();
		model.addAttribute("suppliers",x);
		return "suppNew";
	}
	@RequestMapping(value = "admin/addsupp", method = RequestMethod.POST)
	public String Addemp(@ModelAttribute("suppliers") Suppliers suppliers,Model model, HttpServletRequest request) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		String contact1=request.getParameter("contact1");
		String contact2=request.getParameter("contact2");
		Supplier_contact x= new Supplier_contact();
		x.setSupplier_id(suppliers.getSupplier_id());
		x.setContact(contact1);

		suppdao.create(conn, suppliers);
		suppcondao.create(conn, x);
		if(contact2!="")
		{
			x.setContact(contact2);
			suppcondao.create(conn, x);
		}
		conn.close();
		return "success";
	}

	
	@RequestMapping("admin/suppsearch")
	public String Emplid(Model model)
	{	
		Suppliers x=new Suppliers();
		model.addAttribute("suppliers",x);
		return "suppfind";
	}
	@RequestMapping(value = "admin/suppshow", method = RequestMethod.POST)
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
		return "suppAll";
	}

	
	@RequestMapping("admin/showsuppcont/{id}")
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
		return "showsuppcon";
	}

	@RequestMapping("admin/showsuppcont/delsuppcont{id}/{contact}")
	public String DelEmplCont(@PathVariable(value="id") int id,@PathVariable(value="contact") String contact,Model model) throws SQLException, NotFoundException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Supplier_contact x=new Supplier_contact();
		x.setSupplier_id(id);
		x.setContact(contact);
		suppcondao.delete(conn, x);
		conn.close();
		return "redirect:/success";
	}
	
	@RequestMapping("admin/showsuppcont/updatesuppcont{id}/{contact}")
	public String UpdEmplCont(@PathVariable(value="id") int id, Model model,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
	{

		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Supplier_contact x=new Supplier_contact();
		model.addAttribute("supplier_contact", x);
		model.addAttribute("id", id);
		model.addAttribute("contact", contact);
		return "suppcontupd";
	}
		@RequestMapping(value = "admin/showsuppcont/updatesuppcont{id}/updasuppcont{id}/{contact}", method = RequestMethod.POST)
		public String UpEmplShow(@ModelAttribute("supplier_contact") Supplier_contact supplier_contact,Model model,@PathVariable(value="id") int id,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
		{
			Connection conn =null;
			try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
			Supplier_contact x=new Supplier_contact();
			x.setSupplier_id(id);
			x.setContact(contact);
			suppcondao.delete(conn, x);
			Supplier_contact y=new Supplier_contact();
			y.setAll(supplier_contact.getContact(),id);
			//source_contacts.setSource_id(id);
			//System.out.println(employee_contacts.getEmployee_id());
		//	empcondao.delete(conn, employee_contacts);
			suppcondao.create(conn, y);
			conn.close();
			return "redirect:/success";
		}
	
}
