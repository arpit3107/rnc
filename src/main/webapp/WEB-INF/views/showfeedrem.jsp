<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Remarks</title>
    </head>
    <body>
            <table border="1px solid black">
                    <tr>
                        <th>Remarks</th>
                        <th>Delete it</th>
                    </tr>
                    <c:forEach var="feedback_remarks" items="${list}">
                        <tr>
                            <td>${feedback_remarks.remarks}  </td>
                            <td><a href="delfeedrem${feedback_remarks.id}/${feedback_remarks.remarks}">Delete it</a></td>
                        </tr>
                    </c:forEach>
                </table>
                
    </body>
</html>