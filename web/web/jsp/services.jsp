<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 10.11.2019
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="content"/>
<html lang="${language}">
<head>
    <base href="http://localhost:8080/web_war_exploded/"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="js/menu.js"></script>
    <link rel="stylesheet" type="text/css" href=css/services.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <link href="https://fonts.googleapis.com/css?family=Bad+Script&display=swap" rel="stylesheet">
    <title><fmt:message key="label.services.title"/></title>
</head>
<body class="background">
<div id="sidebar">
    <div class="toggle-btn" onclick="openMenu()">
        <span></span>
        <span></span>
        <span></span>
    </div>
    <ul>
        <li><fmt:message key="label.start.menu"/></li>
        <li><a href=""><fmt:message key="label.start.home"/></a></li>
        <li><a href="jsp/services.jsp"><fmt:message key="label.start.services"/></a></li>
        <li><a href="jsp/schedule.jsp"><fmt:message key="label.start.schedule"/></a></li>
        <li><a href="jsp/coaches.jsp"><fmt:message key="label.start.coaches"/></a></li>
        <li><a href="jsp/contacts.jsp"><fmt:message key="label.start.contacts"/></a></li>
        <li><a href="jsp/infoAboutClub.jsp"><fmt:message key="label.start.info"/></a></li>
    </ul>
</div>
<div id="language">
    <div class="icon-bar" onclick="openLanguageMenu()">
        <i class="fa fa-globe"></i>
    </div>
    <ul>
        <li>
            <form name="englishForm" method="GET" action="controller">
                <input type="hidden" name="command" value="en"/>
                <input type="hidden" name="page" value="/jsp/services.jsp"/>
                <input type="submit" value=" <fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/services.jsp"/>
                <input type="submit" value=" <fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>
<div id="headline"> <fmt:message key="label.services.headline"/></div>
<div id="service1">
    <ul>
        <li class="line1"><fmt:message key="label.services.service1.line1"/></li>
        <li class="line2"><fmt:message key="label.services.service1.line2"/></li>
        <li class="line3"><fmt:message key="label.services.service.line3"/></li>
        <li class="line4"><fmt:message key="label.services.service.line4"/></li>
    </ul>
</div>
<div id="service2">
    <ul>
        <li class="line1"><fmt:message key="label.services.service2.line1"/></li>
        <li class="line2"><fmt:message key="label.services.service2.line2"/></li>
        <li class="line3"><fmt:message key="label.services.service.line3"/></li>
        <li class="line4"><fmt:message key="label.services.service.line4"/></li>
    </ul>
</div>
<div id="service3">
    <ul>
        <li class="line1"><fmt:message key="label.services.service3.line1"/></li>
        <li class="line2"><fmt:message key="label.services.service3.line2"/></li>
        <li class="line3"><fmt:message key="label.services.service.line3"/></li>
        <li class="line4"><fmt:message key="label.services.service.line4"/></li>
    </ul>
</div>
<div id="service4">
    <ul>
        <li class="line1"><fmt:message key="label.services.service4.line1"/></li>
        <li class="line2"><fmt:message key="label.services.service4.line2"/></li>
        <li class="line3"><fmt:message key="label.services.service.line3"/></li>
        <li class="line4"><fmt:message key="label.services.service.line4"/></li>
    </ul>
</div>
<div id="service5">
    <ul>
        <li class="line1"><fmt:message key="label.services.service5.line1"/></li>
        <li class="line2"><fmt:message key="label.services.service5.line2"/></li>
        <li class="line5"><fmt:message key="label.services.service.line5"/></li>
        <li class="line3"><fmt:message key="label.services.service.line3"/></li>
        <li class="line4"><fmt:message key="label.services.service.line4"/></li>
    </ul>
</div>
<div id="service6">
    <ul>
        <li class="line1"><fmt:message key="label.services.service6.line1"/></li>
        <li class="line2"><fmt:message key="label.services.service6.line2"/></li>
        <li class="line5"><fmt:message key="label.services.service.line5"/></li>
        <li class="line3"><fmt:message key="label.services.service.line3"/></li>
        <li class="line4"><fmt:message key="label.services.service.line4"/></li>
    </ul>
</div>
</body>
</html>
