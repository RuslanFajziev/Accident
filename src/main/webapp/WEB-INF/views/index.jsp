<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <div class="btn-group btn-group-toggle" data-toggle="buttons">
            <a class="btn btn-secondary active" href="<c:url value='/create'/>" role="button">Добавить инцидент</a>
            <a class="btn btn-secondary" href="<c:url value='/logout'/>" role="button">Logout ${user.username}</a>
        </div>
        <div class="card" style="width: 100%">
            <div class="card-header">
                Список авто-нарушений
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">name</th>
                        <th scope="col">text</th>
                        <th scope="col">address</th>
                        <th scope="col">Accident Type</th>
                        <th scope="col">Rules</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accidents}" var="accident">
                        <tr>
                            <td>
                                <a href=/accident/update?id=<c:out value="${accident.id}"/>&name=<c:out value="${accident.name}"/>&text=<c:out value="${accident.text}"/>&address=<c:out value="${accident.address}"/>>
                                    <c:out value="${accident.id}"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${accident.name}"/>
                            </td>
                            <td>
                                <c:out value="${accident.text}"/>
                            </td>
                            <td>
                                <c:out value="${accident.address}"/>
                            </td>
                            <td>
                                <c:out value="${accident.type.name}"/>
                            </td>
                            <td>
                                <c:forEach items="${accident.rules}" var="rule">
                                    <c:out value="${rule.name} "/>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>