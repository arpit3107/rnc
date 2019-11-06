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
                    <c:forEach var="supplier_contact" items="${list}">
                        <tr>
                            <td>${supplier_contact.contact}  </td>
                            <td><a href="updatesuppcont${supplier_contact.supplier_id}/${supplier_contact.contact}">Update it</a></td>
                           	<td><a href="delsuppcont${supplier_contact.supplier_id}/${supplier_contact.contact}">Delete it</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>