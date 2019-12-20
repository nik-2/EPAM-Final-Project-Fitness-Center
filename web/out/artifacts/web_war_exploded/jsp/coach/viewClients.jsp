<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 12.12.2019
  Time: 22:57
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
    <title><fmt:message key="label.profileAdmin.profile"/></title>
    <link rel="stylesheet" type="text/css" href=css/user/profile.css>
    <link rel="stylesheet" type="text/css" href=css/admin/showUsers.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <script src="js/viewUsers.js"></script>
    <script src="js/menu.js"></script>
</head>
<body class="image">
<jsp:useBean id="listResults" class="java.util.ArrayList" scope="session"/>
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
        <li><a href="jsp/coach/profile.jsp"><fmt:message key="label.profileAdmin.profile"/></a></li>
        <li>
            <form name="viewData" method="GET" action="controller">
                <input type="hidden" name="command" value="view-data"/>
                <input type="submit" value="<fmt:message key="label.profileAdmin.aboutMe"/>"/>
            </form>
        </li>
        <li>
            <form name="showUsers" method="GET" action="controller">
                <input type="hidden" name="change" value="viewCustomers"/>
                <input type="hidden" name="command" value="view-customers"/>
                <input type="submit" value="<fmt:message key="label.dataCoach.viewCust"/>"/>
            </form>
        </li>
        <li>
            <form name="showUsers" method="GET" action="controller">
                <input type="hidden" name="change" value="prescribeDiet"/>
                <input type="hidden" name="command" value="view-customers"/>
                <input type="submit" value="<fmt:message key="label.dataCoach.prescribeDiet"/>"/>
            </form>
        </li>
        <li>
            <form name="showUsers" method="GET" action="controller">
                <input type="hidden" name="change" value="prescribeExercise"/>
                <input type="hidden" name="command" value="view-customers"/>
                <input type="submit" value="<fmt:message key="label.dataCoach.prescribeExercise"/>"/>
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
                <input type="hidden" name="page" value="/jsp/coach/viewClients.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/coach/viewClients.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>
<h2>Customers</h2>

<input type="text" id="myInput" onkeyup="myFunction()" placeholder="<fmt:message key="label.changeUsers.placeholder"/>" title="<fmt:message key="label.changeUsers.titleMail"/>">
<div class="scroll-table">
    <table id="myTable">
        <tr class="header">
            <th style="width: 24%"><fmt:message key="label.table.mail"/></th>
            <th style="width: 18%"><fmt:message key="label.table.name"/></th>
            <th style="width: 18%"><fmt:message key="label.table.surname"/></th>
            <th style="width: 10%"><fmt:message key="label.table.subscription"/></th>
            <th style="width: 25%"><fmt:message key="label.table.subscriptionDate"/></th>
        </tr>
        <c:forEach items="${listResults}" var="cell">
                <td>${cell.mail}</td>
                <td>${cell.name}</td>
                <td>${cell.surname}</td>
                <td>${cell.subscription}</td>
                <td>${cell.dateSub}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
