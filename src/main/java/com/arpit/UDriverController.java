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

import com.arpit.model.*;
import com.arpit.dao.*;


@Controller
public class UDriverController {


	@Autowired
	public DriversDao driverdao;
	

	
	@RequestMapping("useralldriver")
	public String Showall(Model model) throws SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		List<Drivers> list=driverdao.loadAll(conn);
		conn.close();
		model.addAttribute("list",list);
		return "driverAll";
	}
	@RequestMapping("userdrivershow{driver_id}")
	public String EmplSho(@PathVariable(value="driver_id") int driver_id,Model model) throws NotFoundException, SQLException
	{
		Connection conn =null;
		try {conn= DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/WBiPzPYnnv","WBiPzPYnnv","lqSJLks6bJ");
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		Drivers x=driverdao.getObject(conn, driver_id);
		List<Drivers> list=driverdao.searchMatching(conn, x);
		conn.close();
		model.addAttribute("list", list);
		return "driverAll";
	}
}
