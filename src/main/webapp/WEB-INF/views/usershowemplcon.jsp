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
                    </tr>
                    <c:forEach var="employee_contacts" items="${list}">
                        <tr>
                            <td>${employee_contacts.contact}  </td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>