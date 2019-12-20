<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 10.11.2019
  Time: 9:56
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
    <script src="js/coaches.js"></script>
    <link rel="stylesheet" type="text/css" href=css/coaches.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <link href="https://fonts.googleapis.com/css?family=Bad+Script&display=swap" rel="stylesheet">
    <title><fmt:message key="label.coaches.title"/></title>
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
                <input type="hidden" name="page" value="/jsp/coaches.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/coaches.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>
<div id="btn-left" class="btn-arrow-left" onclick="leftCoach()"></div>
<div id="btn-right" class="btn-arrow-right" onclick="rightCoach()"></div>
<div id="coach1">
    <ul>
        <li><fmt:message key="label.coaches.header"/></li>
        <li><fmt:message key="label.coaches.coach1"/></li>
    </ul>
</div>
<div id="coach2">
    <ul>
        <li><fmt:message key="label.coaches.header"/></li>
        <li><fmt:message key="label.coaches.coach2"/></li>
    </ul>
</div>
<div id="coach3">
    <ul>
        <li><fmt:message key="label.coaches.header"/></li>
        <li><fmt:message key="label.coaches.coach3"/></li>
    </ul>
</div>
<div id="coach4">
    <ul>
        <li><fmt:message key="label.coaches.header"/></li>
        <li><fmt:message key="label.coaches.coach4"/></li>
    </ul>
</div>
<div id="coach5">
    <ul>
        <li><fmt:message key="label.coaches.header"/></li>
        <li><fmt:message key="label.coaches.coach5"/></li>
    </ul>
</div>
<div id="coach6">
    <ul>
        <li><fmt:message key="label.coaches.header"/></li>
        <li><fmt:message key="label.coaches.coach6"/></li>
    </ul>
</div>
<img src="image/coach/image1.jpg" id="image1"/>
<img src="image/coach/image2.jpg" id="image2"/>
<img src="image/coach/image3.jpg" id="image3"/>
<img src="image/coach/image4.jpg" id="image4"/>
<img src="image/coach/image5.jpg" id="image5"/>
<img src="image/coach/image6.jpg" id="image6"/>
</body>
</html>
