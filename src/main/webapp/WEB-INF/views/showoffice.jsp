<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Offices</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Name</th>
                        <th>Partner Name</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach var="office" items="${list}">
                        <tr>
                            <td>${office.name}</td>
                            <td><a href="partners${office.partner_name}">${office.partner_name}</a></td>
                            
                            <td><a href="deloffice${office.name}">Delete it</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
	<button><a href="newoffice">Add New Office</a></button>
    </body>
</html>