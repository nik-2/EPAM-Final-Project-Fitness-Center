<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 22.10.2019
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="content"/>
<html lang="${language}">
<head>
    <base href="http://localhost:8080/web_war_exploded/"/>
    <link href="https://fonts.googleapis.com/css?family=Bad+Script&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href=css/errorPage.css>
    <title><fmt:message key="label.error.title"/></title>
</head>
<body class="background">
<div class="errorText">
            Request from ${pageContext.errorData.requestURI} is failed
            <br/>
            Servlet name or type: ${pageContext.errorData.servletName}
            <br/>
            Status code: ${pageContext.errorData.statusCode}
            <br/>
            Exception: ${pageContext.errorData.throwable}
            <br/>
            Error: ${param.message}
</div>
<div id="btn">
    <a href="jsp/start.jsp"><fmt:message key="label.error.goHome"/></a>
</div>
</body>
</html>