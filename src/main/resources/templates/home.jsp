<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View API</title>
</head>
<body>

<h1>Welcome to thymeleaf</h1>
<ul>
    <li>[[${msg}]]</li>
    <li>[(${msg})]</li>
</ul>
<table>
    <tbody>
    <c:forEach items="${links}" var="link">
        <tr>
            <td><a href="${url}api/v1/${link}/">${link}-api</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td><a href="${url}swagger-ui/index.html">swagger-ui-api</a></td>
    </tr>
    </tbody>
</table>

</body>
</html>