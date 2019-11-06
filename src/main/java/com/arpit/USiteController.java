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

import com.arpit.model.Employee;
import com.arpit.model.Site;
import com.arpit.dao.SiteDao;

@Controller
public class USiteController {
	
	@Autowired
	public SiteDao sitedao;
	
	
	@GetMapping("usersiteAll")
	public String showsite(Model model) throws SQLException
	{
		Connection conn =null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Site> list=sitedao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "usershowsite";
	}
	
	
	//important
	@RequestMapping("usersiteshow{site_id}")
	public String EmplSho(@PathVariable(value="site_id") int id,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {
			conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","arpit~0201");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Site x=new Site();
		x=sitedao.getObject(conn, id);
		List<Site> list=sitedao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "usershowsite";
	}
}
