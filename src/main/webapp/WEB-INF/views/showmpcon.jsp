<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Mp Contact</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Contacts</th>
                        <th>Update it</th>
                        <th>Delete it</th>
                    </tr>
                    <c:forEach var="mp_contact" items="${list}">
                        <tr>
                            <td>${mp_contact.contact}  </td>
                            <td><a href="updatempcont${mp_contact.city}/${mp_contact.contact}">Update it</a></td>
                           	<td><a href="delmpcont${mp_contact.city}/${mp_contact.contact}">Delete it</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>