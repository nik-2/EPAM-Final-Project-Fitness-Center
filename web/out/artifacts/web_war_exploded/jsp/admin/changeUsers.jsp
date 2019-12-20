<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 26.11.2019
  Time: 10:20
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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Bad+Script&display=swap" rel="stylesheet">
    <title><fmt:message key="label.changeUsers.title"/></title>
    <link rel="stylesheet" type="text/css" href=css/admin/profile.css>
    <link rel="stylesheet" type="text/css" href=css/admin/showUsers.css>
    <link rel="stylesheet" type="text/css" href=css/admin/changeUser.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <script src="js/menu.js"></script>
    <script src="js/changeUsers.js"></script>
    <script src="js/adminProfile.js"></script>
</head>
<body class="image">
<c:set var="CHANGE" scope="session"> ${CHANGE} </c:set>
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
<div id="language">
    <div class="icon-bar" onclick="openLanguageMenu()">
        <i class="fa fa-globe"></i>
    </div>
    <ul>
        <li>
            <form name="englishForm" method="GET" action="controller">
                <input type="hidden" name="command" value="en"/>
                <input type="hidden" name="page" value="/jsp/admin/changeUsers.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/admin/changeUsers.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>

<h2><fmt:message key="label.changeUsers.users"/></h2>

<input type="text" id="myInput" onkeyup="myFunction()" placeholder="<fmt:message key="label.changeUsers.placeholder"/>" title="<fmt:message key="label.changeUsers.titleMail"/>">
<div class="scroll-table">
    <table id="myTable">
        <tr class="header">
            <th><fmt:message key="label.table.select"/></th>
            <th><fmt:message key="label.table.mail"/></th>
            <th><fmt:message key="label.table.name"/></th>
            <th><fmt:message key="label.table.surname"/></th>
            <th><fmt:message key="label.table.bankCard"/></th>
            <th><fmt:message key="label.table.clubBalance"/></th>
            <th><fmt:message key="label.table.block2"/></th>
            <th><fmt:message key="label.table.role"/></th>
            <th><fmt:message key="label.table.dateOfReg"/></th>
            <th><fmt:message key="label.table.subscription"/></th>
            <th><fmt:message key="label.table.subscriptionDate"/></th>
        </tr>
        <c:if test="${CHANGE.equalsIgnoreCase('ROLE')}">
            <c:forEach items="${listResults}" var="cell">
                <tr class="" onclick="setSelectMail(this)">
                    <td>
                        <button id="checkMark" type="button" class="checkMark" onclick="displayTableBlockRole(); changeStyle(this)">&#10004;</button>
                    </td>
                    <td>${cell.mail}</td>
                    <td>${cell.name}</td>
                    <td>${cell.surname}</td>
                    <td>${cell.bankCardId}</td>
                    <td>${cell.clubBalance}</td>
                    <td>${cell.block}</td>
                    <td>${cell.role}</td>
                    <td>${cell.dateReg}</td>
                    <td>${cell.subscription}</td>
                    <td>${cell.dateSub}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${!CHANGE.equalsIgnoreCase('ROLE')}">
            <c:forEach items="${listResults}" var="cell">
                <tr class="" onclick="setSelectMail(this)">
                    <td>
                        <button type="button" class="checkMark" onclick="displayTableBlock(); changeStyle(this)">&#10004;</button>
                    </td>
                    <td>${cell.mail}</td>
                    <td>${cell.name}</td>
                    <td>${cell.surname}</td>
                    <td>${cell.bankCardId}</td>
                    <td>${cell.clubBalance}</td>
                    <td>${cell.block}</td>
                    <td>${cell.role}</td>
                    <td>${cell.dateReg}</td>
                    <td>${cell.subscription}</td>
                    <td>${cell.dateSub}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<div id="change" class="modalConfirm">
    <form name="confirmForm" method="POST" action="controller">
        <input type="hidden" id="btn" name="selectMail" value=""/>
        <input type="hidden" id="btnRole" name="selectRole" value=""/>
        <button type="submit" class="confirmbtn" name="command" value="change-user"><fmt:message
                key="label.start.confirm"/></button>
    </form>
    <button type="submit" class="cancelbtn" onclick="displayTableNone(); removeChangeStyle()"><fmt:message key="label.start.cancelBtn"/></button>
</div>
<div id="role" class="modalSelectType">
    <span onclick="displayNoneRole(); removeChangeStyle()" class="closeSelect"
          title="<fmt:message key="label.start.cancelBtn"/>">×</span>
    <div class="modal-contentSelect animate">
        <div class="contentSelectText"><fmt:message key="label.changeUser.selectNewRole"/></div>
        <br/>
        <button type="submit" class="selectbtn" onclick="setRoleAdmin()"><fmt:message key="label.changeUser.admin"/></button>
        <br/>
        <button type="submit" class="selectbtn" onclick="setRoleUser()"><fmt:message key="label.changeUser.user"/></button>
        <br/>
        <button type="submit" class="selectbtn" onclick="setRoleCoach()"><fmt:message key="label.changeUser.coach"/></button>
        <br/>
    </div>
</div>
</body>
</html>
