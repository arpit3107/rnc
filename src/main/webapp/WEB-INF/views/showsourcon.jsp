<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Sources Contact</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Contacts</th>
                        <th>Update it</th>
                        <th>Delete it</th>
                    </tr>
                    <c:forEach var="source_contacts" items="${list}">
                        <tr>
                            <td>${source_contacts.contact}  </td>
                            <td><a href="updatesourcont${source_contacts.source_id}/${source_contacts.contact}">Update it</a></td>
                           	<td><a href="delsourcont${source_contacts.source_id}/${source_contacts.contact}">Delete it</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>