<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 25.11.2019
  Time: 4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="itg" uri="/WEB-INF/tld/InfoDayTag.tld" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="content"/>
<html lang="${language}">
<head>
    <base href="http://localhost:8080/web_war_exploded/"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Bad+Script&display=swap" rel="stylesheet">
    <title><fmt:message key="label.profileAdmin.profile"/></title>
    <link rel="stylesheet" type="text/css" href=css/admin/profile.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <script src="js/menu.js"></script>
    <script src="js/adminProfile.js"></script>
</head>
<body class="image">
<input id="customValidity" type="hidden" value="<fmt:message key="label.start.menu"/>"/>
<div id="sidebar">
    <div class="toggle-btn" onclick="openMenu()">
        <span></span>
        <span></span>
        <span></span>
    </div>
    <ul>
        <li><fmt:message key="label.start.menu"/></li>
        <li><a href=""><fmt:message key="label.start.home"/></a></li>
        <li><a href="jsp/admin/profile.jsp"><fmt:message key="label.profileAdmin.profile"/></a></li>
        <li>
            <form name="viewData" method="GET" action="controller">
                <input type="hidden" name="command" value="view-data"/>
                <input type="submit" value="<fmt:message key="label.profileAdmin.aboutMe"/>"/>
            </form>
        </li>
        <li>
            <form name="showUsers" method="GET" action="controller">
                <input type="hidden" name="command" value="view"/>
                <input type="submit" value="<fmt:message key="label.profileAdmin.viewUsers"/>"/>
            </form>
        </li>
        <li>
            <input type="submit" onclick="displayBlock()" value="<fmt:message key="label.profileAdmin.changeUser"/>"/>
        </li>
    </ul>
</div>
<div id="id01" class="modalSelectType">
    <span onclick=displayNone(); class="closeSelect"
          title="<fmt:message key="label.start.cancelBtn"/>">×</span>
    <form class="modal-contentSelect animate" name="confirmForm" method="GET" action="controller">
        <div class="contentSelectText"><fmt:message key="label.profileAdmin.select"/></div>
        <br/>
        <button type="submit" class="selectbtn" name="command" value="change-role"><fmt:message key="label.profileAdmin.selectRole"/></button>
        <br/>
        <button type="submit" class="selectbtn" name="command" value="block"><fmt:message key="label.profileAdmin.selectBlock"/></button>
        <br/>
        <button type="submit" class="selectbtn" name="command" value="unblock"><fmt:message key="label.profileAdmin.selectUnblock"/></button>
        <br/>
    </form>
</div>
<h1><fmt:message key="label.admin.welcome"/></h1>
<div class="appreciateText">
    <fmt:message key="label.profile.appreciate.part1"/> <itg:info-day name="${login}"/>
    <fmt:message key="label.profile.appreciate.part2"/><br/> <fmt:message key="label.profile.appreciate.part3"/><br/>
    <div style="color: red">&#10084;</div>
</div>
<div id="language">
    <div class="icon-bar" onclick="openLanguageMenu()">
        <i class="fa fa-globe"></i>
    </div>
    <ul>
        <li>
            <form name="englishForm" method="GET" action="controller">
                <input type="hidden" name="command" value="en"/>
                <input type="hidden" name="page" value="/jsp/admin/profile.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/admin/profile.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>
<div id="logout">
<form name="LogoutForm" method="GET" action="controller">
    <button type="submit" name="command" value="logout"><fmt:message key="label.profile.logout"/></button>
</form>
</div>
</body>
</html>
