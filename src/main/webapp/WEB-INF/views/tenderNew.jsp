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
	<form:form method="post" modelAttribute="tenders" action="addtender" class="form-inline">
	<div class="form-group">
            <label for="">Id</label>
            <br>
            <form:input type="number" class="form-control" path="tender_id" placeholder="tender_id" />
            <form:errors path="tender_id" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">Department Name</label>
            <br>
            <form:input type="text" class="form-control" path="department" placeholder="department" />
            <form:errors path="department" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">City</label>
            <br>
            <form:input type="text" class="form-control" path="city" placeholder="city" />
            <form:errors path="city" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">Cost</label>
            <br>
            <form:input type="text" class="form-control" path="cost" placeholder="cost" />
            <form:errors path="cost" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">Managed By</label>
            <br>
            <form:input type="number" class="form-control" path="managed_by" placeholder="managed_by" />
            <form:errors path="managed_by" />
        </div>
			<br>
			<br>
			<div class="form-group">
            <label for="">Last Date</label>
            <br>
            <form:input type="date" class="form-control" path="last_date" placeholder="last_date" />
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