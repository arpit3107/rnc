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
import com.arpit.model.Office;
import com.arpit.dao.OfficeDao;

@Controller
public class UOfficeController{
	
	@Autowired
	public OfficeDao officedao;
	
	@RequestMapping("useroffice")
	public String Office(Model model) throws SQLException {
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Office> list=officedao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "usershowoffice";
	}
	
	@RequestMapping("userofficeshow{name}")
	public String OffSho(@PathVariable(value="name") String name,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Office x=new Office();
		x=officedao.getObject(conn, name);

		List<Office> list=officedao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "usershowoffice";
	}
}