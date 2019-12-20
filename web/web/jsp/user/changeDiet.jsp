<%--
  Created byIntelliJ  IDEA.
  User: Nikita
  Date: 09.12.2019
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Bad+Script&display=swap" rel="stylesheet">
    <title><fmt:message key="label.user.changeDiet"/></title>
    <link rel="stylesheet" type="text/css" href=css/user/changeDietAndExer.css>
    <link rel="stylesheet" type="text/css" href=css/user/profile.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <script src="js/menu.js"></script>
    <script src="js/changeDietAndExer.js"></script>
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
        <li><a href="jsp/user/profile.jsp"><fmt:message key="label.profileAdmin.profile"/></a></li>
        <li>
            <form name="viewData" method="GET" action="controller">
                <input type="hidden" name="command" value="view-data"/>
                <input type="submit" value="<fmt:message key="label.profileAdmin.aboutMe"/>"/>
            </form>
        </li>
        <li>
            <form name="showUsers" method="GET" action="controller">
                <input type="hidden" name="command" value="view-diet"/>
                <input type="submit" value="<fmt:message key="label.profileUser.diet"/>"/>
            </form>
        </li>
        <li>
            <form name="showUsers" method="GET" action="controller">
                <input type="hidden" name="command" value="view-exercise"/>
                <input type="submit" value="<fmt:message key="label.profileUser.exercise"/>"/>
            </form>
        </li>
        <li>
            <form name="showUsers" method="GET" action="controller">
                <input type="hidden" name="command" value="view-subscription"/>
                <input type="submit" value="<fmt:message key="label.profileUser.subscription"/>"/>
            </form>
        </li>
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
                <input type="hidden" name="page" value="/jsp/user/changeDiet.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/user/changeDiet.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>
<h2><fmt:message key="label.user.myDiet"/></h2>
<div class="errorMess">${param.errorMess}</div>
<div id="changeBtn">
    <button type="submit" onclick="changeDiet()" class="editData"><b>&#128393;</b><b class="edit"> <fmt:message key="label.user.CHANGEDIET"/> </b></button>
</div>
<div class="dataContent">
    <div id="responseBtn">
        <form class="responseBtn" name="cancel" method="GET" action="controller">
            <button type="submit" class="cancelbtn" name="command" value="view-diet"><fmt:message key="label.start.cancelBtn"/></button>
        </form>
        <form onsubmit="return checkValidation(document.getElementById('diet'))" class="responseBtn" name="confirm" method="POST" action="controller">
            <input type="hidden" id="newDiet" name="diet" value="${diet}">
            <button type="submit" class="confirmbtn" name="command" value="change-user-diet"><fmt:message key="label.start.confirm"/></button>
        </form>
    </div>

    <div class="inp">
        <textarea type="text" class="dataContentInput" onchange="setNewDiet()" oninput="checkDietAndExer(this)" onfocus="checkDietAndExer(this)"
                  id="diet" cols="100" rows="6" maxlength="600" readonly>${diet}</textarea>
    </div>
</div>
</body>
</html>
