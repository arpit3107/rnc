<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@page session="true"%>
<html>
<head>
<title>User</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body style="background: #ffe6e6">
<nav class="navbar navbar-inverse navbar-fixed-top" style="background: #ffcccc !important">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">M/s. Maa Bindhyavashini Constructions</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>
						LOGOUT</a></li>
						</c:if>
						
			</ul>
		</div>
	</nav>
	<br>
	<br>
	<br>
	<div class="container"> 
	<c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            Welcome : ${user}
        </h2>
    </c:if>
    <br>
    <br>
    <h3> <a href="userallpartners">Partners</a></h3>
    <h3> <a href="userempl">Employee</a>  </h3>
     <h3> <a href="usersiteAll">Site</a>  </h3>
    <h3> <a href="useroffice">Offices</a></h3>
	 <h3> <a href="usertender">Tenders</a></h3> 
	 <h3> <a href="userallmp">M.P</a></h3> 
	 <h3><a href="userallmachine">Machines</a></h3>
	 <h3><a href="useralldriver">Drivers</a></h3>
	 <h3><a href="usersupp">Suppliers</a></h3>
   
    </div>
    <script src="webjars/jquery/1.11.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>