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
public class UMpController {

	@Autowired
	public MpDao mpdao;
	@Autowired
	public Mp_contactDao mpcondao;
	@RequestMapping("userallmp")
	public String Allmp(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Mp> list=mpdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "usermpAll";
	}
	@RequestMapping("usershowmpcont/{city}")
	public String EmplCont(@PathVariable(value="city") String city,Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		Mp_contact x=new Mp_contact();
		x.setCity(city);
		
		List<Mp_contact> list=mpcondao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "usershowmpcon";
	}


	
	@RequestMapping("usermpshow{city}")
	public String mpShow(@PathVariable(value="city") String city,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","arpit","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Mp x=new Mp();
		x=mpdao.getObject(conn, city);

		List<Mp> list=mpdao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "usermpAll";
	}
	
}