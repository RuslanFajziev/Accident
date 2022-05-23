<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<c:forEach items="${accidents}" var="accident">
    <c:out value="${accident.name}"/></br>
</c:forEach>
</body>
</html>