<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Machinery list</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Tag</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Site id</th>
                        <th>Driver</th>
                    </tr>
                    <c:forEach var="machinery" items="${list}">
                        <tr>
                            <td>${machinery.tag}  </td>
                            <td>${machinery.name}</td>
                            <td>${machinery.price}</td>
                            <td><a href="usersiteshow${machinery.site_id} ">Show Site</a></td>
                            <td><a href="userdrivershow${machinery.driver_id}">Driver</a></td>
                        </tr>
                    </c:forEach>
                </table>
               
    </body>
</html>