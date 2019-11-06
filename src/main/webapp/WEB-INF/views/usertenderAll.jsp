<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Tenders list</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th> Tender Id</th>
                        <th>Department</th>
                        <th>City</th>
                        <th>Last Date</th>
                        <th>Cost</th>
                        <th>Managed by</th>
                    </tr>
                    <c:forEach var="tenders" items="${list}">
                        <tr>
                            <td>${tenders.tender_id}  </td>
                            <td>${tenders.department}</td>
                            <td>${tenders.city}</td>
                            <td>${tenders.last_date}</td>
                            <td>${tenders.cost}</td>
                            <td><a href="userempshow${tenders.managed_by} ">Show Employee</a></td>
                        </tr>
                    </c:forEach>
                </table>
               
    </body>
</html>