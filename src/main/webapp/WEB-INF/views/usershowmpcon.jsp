<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>M.P Contact</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Contacts</th>
                    </tr>
                    <c:forEach var="mp_contact" items="${list}">
                        <tr>
                            <td>${mp_contact.contact}  </td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>