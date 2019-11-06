<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Employees Contact</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Contacts</th>
                        <th>Update it</th>
                        <th>Delete it</th>
                    </tr>
                    <c:forEach var="employee_contacts" items="${list}">
                        <tr>
                            <td>${employee_contacts.contact}  </td>
                            <td><a href="updateempcont${employee_contacts.employee_id}/${employee_contacts.contact}">Update it</a></td>
                           	<td><a href="delempcont${employee_contacts.employee_id}/${employee_contacts.contact}">Delete it</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>