<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
<title>ADMIN</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />
</head>
<script type="text/javascript">
 var tenders = [];
<c:forEach var="tenders" items="${list}">
tenders.push("${tenders.tender_id}");
</c:forEach>
var mess = "Tenders  "
for(var i=0;i<tenders.length;i++)
{
mess=mess+tenders[i];
mess=mess+",";
}
mess=mess+" have last date today to apply!";
if(tenders.length != 0)
{
alert(mess);
}
</script>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">M/s. Maa Bindhyavashini Constructions</a>
			</div>
		</div>
	</nav>
<div class="container">
<div class="jumbotron">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            Welcome to the admin page :<mark>${user}</mark>  | <a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>
						Logout</a>
        </h2>
    </c:if>
    </div>
    <h3> <a href="admin/allpartners">Partners</a></h3>
    <h3> <a href="admin/empl">Employee</a>  </h3>
     <h3> <a href="admin/site">Site</a>  </h3>
    <h3> <a href="admin/bankall">Bank Details </a></h3>
    <h3> <a href="admin/office">Offices</a></h3>
	 <h3> <a href="admin/tender">Tenders</a></h3> 
	 <h3> <a href="admin/allmp">M.P</a></h3> 
	 <h3><a href="admin/machine">Machines</a></h3>
	 <h3><a href="admin/driver">Drivers</a></h3>
	 <h3><a href="admin/sour">Sources</a></h3>
	 <h3><a href="admin/supp">Suppliers</a></h3>
	 <h3><a href="admin/loan">Loans</a></h3>
	  <h3><a href="admin/law">Lawyers</a></h3>
    </div>
</body>
</html>
