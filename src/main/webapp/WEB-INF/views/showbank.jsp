<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Bank Details</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Account Number</th>
                        <th>Name</th>
                        <th>Branch</th>
                        <th>Balance</th>
                        <th>Managing Person's Id</th>
                    </tr>
                    <c:forEach var="bank" items="${list}">
                        <tr>
                            <td><a href="accountupd/${bank.account_number}">${bank.account_number}</a></td>
                            <td>${bank.name}</td>
                            <td>${bank.branch}</td>
                            <td>${bank.balance}</td>
                            <td><a href="empshow${bank.employee_id}">${bank.employee_id}</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
	<button><a href="newaccount">Add New Account</a></button>
    </body>
</html>