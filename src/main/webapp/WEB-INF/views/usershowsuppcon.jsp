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
                    </tr>
                    <c:forEach var="supplier_contact" items="${list}">
                        <tr>
                            <td>${supplier_contact.contact}  </td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>