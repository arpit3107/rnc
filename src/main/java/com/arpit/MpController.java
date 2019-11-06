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

import com.arpit.model.Bank;
import com.arpit.model.Employee;
import com.arpit.model.Employee_contacts;
import com.arpit.model.Mp;
import com.arpit.dao.MpDao;
import com.arpit.model.Mp_contact;
import com.arpit.dao.Mp_contactDao;

@Controller
public class MpController {

	@Autowired
	public MpDao mpdao;
	@Autowired
	public Mp_contactDao mpcondao;
	@RequestMapping("admin/allmp")
	public String Allmp(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Mp> list=mpdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "mpAll";
	}
	@RequestMapping("admin/showmpcont/{city}")
	public String EmplCont(@PathVariable(value="city") String city,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Mp_contact x=new Mp_contact();
		x.setCity(city);
		
		List<Mp_contact> list=mpcondao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "showmpcon";
	}

	@RequestMapping(value = "admin/mpupd/{city}")
	public String UpdateAccount(@PathVariable(value="city") String city,Model model)
	{
		Mp mp =new Mp();
		model.addAttribute("mp", mp);
		model.addAttribute("city", city);
		return "updmp";
	}
	
	@RequestMapping(value = "admin/mpupd/updatemp/{city}", method = RequestMethod.POST)
	public String AccUpd(@ModelAttribute("mp") Mp mp,Model model,@PathVariable(value="city") String city) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Mp x=mpdao.getObject(conn, city);
		x.setName(mp.getName());
		x.setP_a(mp.getP_a());
		mpdao.save(conn, x);
		conn.close();
		return "redirect:/success";
	}
	
	@RequestMapping("admin/showmpcont/delmpcont{city}/{contact}")
	public String DelEmplCont(@PathVariable(value="city") String city,@PathVariable(value="contact") String contact,Model model) throws SQLException, NotFoundException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Mp_contact x=new Mp_contact();
		x.setCity(city);
		x.setContact(contact);
		mpcondao.delete(conn, x);
		conn.close();
		return "redirect:/success";
	}
	@RequestMapping("admin/showmpcont/updatempcont{city}/{contact}")
	public String UpdEmplCont(@PathVariable(value="city") String city, Model model,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
	{

		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}

		Mp_contact x=new Mp_contact();
		model.addAttribute("employee_contacts", x);
		model.addAttribute("city", city);
		model.addAttribute("contact", contact);
		return "mpcontupd";
	}
		@RequestMapping(value = "admin/showmpcont/updatempcont{city}/updampcont{city}/{contact}", method = RequestMethod.POST)
		public String UpEmplShow(@ModelAttribute("mp_contact") Mp_contact mp_contact,Model model,@PathVariable(value="city") String city,@PathVariable(value="contact") String contact) throws SQLException, NotFoundException
		{
			Connection conn =null;
			try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			Mp_contact x=new Mp_contact();
			x.setCity(city);;
			x.setContact(contact);

			Mp_contact y=new Mp_contact();
			y.setAll(mp_contact.getContact(),city);
			mpcondao.delete(conn, x);
			//mp_contact.setCity(city);
			//System.out.println(employee_contacts.getEmployee_id());
		//	empcondao.delete(conn, employee_contacts);
			mpcondao.create(conn, y);
			conn.close();
			return "redirect:/success";
		}
	
	@RequestMapping("admin/mpshow{city}")
	public String mpShow(@PathVariable(value="city") String city,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Mp x=new Mp();
		x=mpdao.getObject(conn, city);

		List<Mp> list=mpdao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "mpAll";
	}
	
}