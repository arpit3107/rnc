<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Lawyers</title>
    </head>
    <body>
    	<button><a href="newlaw">Add New Lawyer</a></button>
            <table border="1px solid black">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>City</th>
                        <th>Contact</th>
                        <th>Office Name</th>
       
                    </tr>
                    <c:forEach var="lawyer" items="${list}">
                        <tr>
                            <td>${lawyer.lawyer_id}  </td>
                            <td>${lawyer.name}</td>
                            <td>${lawyer.city}</td>
                            <td><a href="showlawcont/${lawyer.lawyer_id} ">Show Contacts</a></td>
                        	<td><a href="officeshow${lawyer.office_name}">Site</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>