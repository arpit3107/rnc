<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Site Details</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>City</th>
                        <th>Starting Date</th>
                        <th>Ending Date</th>
                        <th>Workers</th>
                        <th>Manager</th>
                    </tr>
                    <c:forEach var="site" items="${list}">
                        <tr>
                        	<td>${site.site_id}</td>
                            <td>${site.name}</td>
                            <td><a href="mpshow${site.city}">${site.city}</a></td>
                            <td>${site.starting_date}</td>
                            <td>${site.ending_date}</td>
                            <td>${site.number_of_labours}</td>
                            <td><a href="empshow${site.manager_id}">${site.manager_id}</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>