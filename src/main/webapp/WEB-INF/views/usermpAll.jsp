<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>M.P</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>City</th>
                        <th>Name</th>
                        <th>P.A</th>
                        <th>Show Contact</th>
       
                    </tr>
                    <c:forEach var="mp" items="${list}">
                        <tr>
                            <td>${mp.city}  </td>
                            <td>${mp.name}</td>
                            <td>${mp.p_a}</td>
                            <td><a href="usershowmpcont/${mp.city} ">Show Contacts</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>