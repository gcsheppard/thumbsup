
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Test</title>
    </head>
    <body>
        <h1>images</h1> 
        <c:forEach var="image" items="${images}">
            <div>
                <img width="100" src="/thumbsup/image?id=${image.id}" />
            </div>

        </c:forEach>

        <a href="/thumbsup/upload">New Image</a>

    </body>
</html>