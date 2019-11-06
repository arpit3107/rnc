<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Loan list</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Loan Id</th>
                        <th>Amount</th>
                        <th>Given By</th>
                        <th>Given Date</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="loan" items="${list}">
                        <tr>
                            <td>${loan.loan_id}  </td>
                            <td>${loan.amount}</td>
                            <td><a href="sourshow${loan.source_id} ">Source</a></td>
                            <td>${loan.given_date}</td>
                            <td><a href="deleteloan${loan.loan_id}">Delete Loan</a></td>
                        </tr>
                    </c:forEach>
                </table>
               
    </body>
</html>