<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>register</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body style="background: #ffe6e6">

<div class="container">
	<form:form method="post" modelAttribute="suppliers" action="usersuppshow" class="form-inline">
	<div class="form-group">
            <label for="">Id</label>
            <br>
            <form:input type="number" class="form-control" path="supplier_id" placeholder="supplier_id" />
            <form:errors path="supplier_id" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">Name</label>
            <br>
            <form:input type="text" class="form-control" path="name" placeholder="name" />
            <form:errors path="name" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">Material</label>
            <br>
            <form:input type="text" class="form-control" path="material" placeholder="material" />
            <form:errors path="material" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">Site Id</label>
            <br>
            <form:input type="number" class="form-control" path="site_id" placeholder="site_id" />
            <form:errors path="site_id" />
        </div>
			<br>
			<br>
	

			<button type="submit" class="btn btn-primary">Submit</button>
				
				<td>${error}</td>
			
	</form:form>
	</div>
	<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>