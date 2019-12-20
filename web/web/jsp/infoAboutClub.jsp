<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 09.11.2019
  Time: 18:15
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
    <script src="js/infoClub.js"></script>
    <link rel="stylesheet" type="text/css" href=css/infoAboutClub.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <link href="https://fonts.googleapis.com/css?family=Bad+Script&display=swap" rel="stylesheet">
    <title><fmt:message key="label.info.title"/></title>
</head>
<body class="background">
<div id="headline"><fmt:message key="label.info.header"/></div>
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
                <input type="hidden" name="page" value="/jsp/infoAboutClub.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/infoAboutClub.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>
<div id="info">
    <ul>
        <li><fmt:message key="label.info.line1"/></li>
        <li><fmt:message key="label.info.line2"/></li>
        <li><fmt:message key="label.info.line3"/></li>
        <li><fmt:message key="label.info.line4"/></li>
        <li><fmt:message key="label.info.line5"/></li>
        <li><fmt:message key="label.info.line6"/></li>
        <li><fmt:message key="label.info.line7"/></li>
        <li><fmt:message key="label.info.line8"/></li>
        <li><fmt:message key="label.info.line9"/></li>
    </ul>
</div>
<div>
    <img src="image/aboutclub/slide1.jpg" id="image1"/>
    <img src="image/aboutclub/slide2.jpg" id="image2" class="parameter"/>
    <img src="image/aboutclub/slide3.jpg" id="image3" class="parameter"/>
    <img src="image/aboutclub/slide4.jpg" id="image4" class="parameter"/>
    <img src="image/aboutclub/slide5.jpg" id="image5" class="parameter"/>
    <img src="image/aboutclub/slide6.jpg" id="image6" class="parameter"/>
    <img src="image/aboutclub/slide7.jpg" id="image7" class="parameter"/>
    <img src="image/aboutclub/slide8.jpg" id="image8" class="parameter"/>
</div>
</body>
</html>

