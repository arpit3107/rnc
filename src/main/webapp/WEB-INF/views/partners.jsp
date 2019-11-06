<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Partners list</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Name</th>
                        <th>Contact</th>
                        <th>Total_share</th>
                        <th>Representative</th>
                    </tr>
                    <c:forEach var="partners" items="${list}">
                        <tr>
                            <td>${partners.name}  </td>
                            <td>${partners.contact}</td>
                            <td>${partners.total_share}</td>
                            <td>${partners.representative}</td>
                        </tr>
                    </c:forEach>
                </table>
                
	<button><a href="updpart">Update Partners Contact</a></button>
    </body>
</html>