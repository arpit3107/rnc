<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Employee</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Email_id</th>
                        <th>Contacts</th>
       
                    </tr>
                    <c:forEach var="employee" items="${list}">
                        <tr>
                            <td>${employee.id}  </td>
                            <td>${employee.name}</td>
                            <td>${employee.address}</td>
                            <td>${employee.email_id}</td>
                            <td><a href="usershowemplcont/${employee.id} ">Show Contacts</a></td>
                        	
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>