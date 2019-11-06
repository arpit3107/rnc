<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Suppliers</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Material</th>
                        <th>Contacts</th>
                        <th>Site Id</th>
       
                    </tr>
                    <c:forEach var="suppliers" items="${list}">
                        <tr>
                            <td>${suppliers.supplier_id}  </td>
                            <td>${suppliers.name}</td>
                            <td>${suppliers.material}</td>
                            <td><a href="showsuppcont/${suppliers.supplier_id} ">Show Contacts</a></td>
                        	<td><a href="siteshow${suppliers.site_id}">Site</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>