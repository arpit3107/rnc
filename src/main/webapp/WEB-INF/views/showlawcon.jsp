<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Lawyer Contact</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Contacts</th>
                        <th>Update it</th>
                        <th>Delete it</th>
                    </tr>
                    <c:forEach var="lawyer_contact" items="${list}">
                        <tr>
                            <td>${lawyer_contact.contact}  </td>
                            <td><a href="updatelawcont${lawyer_contact.lawyer_id}/${lawyer_contact.contact}">Update it</a></td>
                           	<td><a href="dellawcont${lawyer_contact.lawyer_id}/${lawyer_contact.contact}">Delete it</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>