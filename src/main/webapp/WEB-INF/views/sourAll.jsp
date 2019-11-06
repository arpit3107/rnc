<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Sources</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Contact</th>
                        <th>Employee Id</th>
       
                    </tr>
                    <c:forEach var="sources" items="${list}">
                        <tr>
                            <td>${sources.source_id}  </td>
                            <td>${sources.name}</td>
                            <td><a href="showsourcont/${sources.source_id} ">Show Contacts</a></td>
                        	<td><button><a href="empshow${sources.employee_id}">Employee</a></button></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>