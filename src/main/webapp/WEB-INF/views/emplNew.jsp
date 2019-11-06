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
	<form:form method="post" modelAttribute="employee" action="addemp" class="form-inline">
	<div class="form-group">
            <label for="">Id</label>
            <br>
            <form:input type="number" class="form-control" path="id" placeholder="id" value="2019" />
            <form:errors path="id" />
        </div>
			<br>
			<br>
			<div class="form-group">
			<label for="">name</label>
			<br>
				<form:input path="name" type="text" class="form-control" placeholder="name" value="aaa"/>
				<!-- bind to user.name-->
				<form:errors path="name" />
			</div>
			<br>
			<br>
			<div class="form-group">

				<label for="">Address</label>
				<br>
				<form:input path="address" type="text" class="form-control" placeholder="address" value="gorakhpur" />
				<!-- bind to user.name-->
				<form:errors path="address" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">EMAIL</label>
			<br>
				<form:input path="email_id" type="text" class="form-control" placeholder="email_id" value="aaa"/>
				<!-- bind to user.name-->
				<form:errors path="email_id" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">Contact</label>
			<br>
				<input name="contact1" type="text" class="form-control" placeholder="contact1"/>
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">Alternate Contact</label>
			<br>
				<input name="contact2" type="text" class="form-control" placeholder="contact2" />
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