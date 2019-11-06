<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Drivers list</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Driver Id</th>
                        <th>License Number</th>
                        <th>Expertise</th>
                    </tr>
                    <c:forEach var="drivers" items="${list}">
                        <tr>
                            <td>${drivers.driver_id}  </td>
                            <td>${drivers.license_number}</td>
                            <td>${drivers.expertise}</td>
                        </tr>
                    </c:forEach>
                </table>
               
    </body>
</html>