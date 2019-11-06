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
	<form:form method="post" modelAttribute="loan" action="addloan" class="form-inline">
	<div class="form-group">
            <label for="">Id</label>
            <br>
            <form:input type="number" class="form-control" path="loan_id" placeholder="loan_id" />
            <form:errors path="loan_id" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">Amount</label>
            <br>
            <form:input type="text" class="form-control" path="amount" placeholder="amount" />
            <form:errors path="amount" />
        </div>
			<br>
			<br>
<div class="form-group">
            <label for="">Given BY</label>
            <br>
            <form:input type="number" class="form-control" path="source_id" placeholder="source_id" />
            <form:errors path="source_id" />
        </div>
			<br>
			<br>
			<div class="form-group">
            <label for="">Given Date</label>
            <br>
            <form:input type="date" class="form-control" path="given_date" placeholder="given_date" />
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