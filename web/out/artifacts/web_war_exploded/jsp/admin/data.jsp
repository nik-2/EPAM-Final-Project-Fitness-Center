<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 26.11.2019
  Time: 10:16
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
    <title><fmt:message key="label.data.data"/></title>
    <link rel="stylesheet" type="text/css" href=css/admin/data.css>
    <link rel="stylesheet" type="text/css" href=css/admin/profile.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <script src="js/menu.js"></script>
    <script src="js/adminProfile.js"></script>
    <script src="js/changeData.js"></script>
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
<div id="language">
    <div class="icon-bar" onclick="openLanguageMenu()">
        <i class="fa fa-globe"></i>
    </div>
    <ul>
        <li>
            <form name="englishForm" method="GET" action="controller">
                <input type="hidden" name="command" value="en"/>
                <input type="hidden" name="page" value="/jsp/admin/data.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/admin/data.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>
<h2><fmt:message key="label.data.aboutMe"/></h2>
<div id="changeBtn">
    <button type="submit" onclick="changeData()" class="editData"><b>&#128393;</b><b class="edit"> <fmt:message key="label.data.editData"/></b></button>
    <button type="submit" onclick="changePassword()" class="editPassword"><b>&#128393;</b><b class="edit"> <fmt:message key="label.data.editPassword"/></b></button>
</div>
<div class="dataContent">
    <div class="inp">
        <b><fmt:message key="label.start.email"/>:</b>
        <input type="text" class="dataContentInput" value="${mailData}" name="mail" readonly/>
    </div>

    <div class="inp">
        <b><fmt:message key="label.start.name"/>:</b>
        <input type="text" class="dataContentInput" onchange="setNewName()" onfocus="checkName(this)"
               oninput="checkName(this)"
               value="${nameData}" id="name" pattern="[A-Z][a-z]+(-[A-Z][a-z]+)?" maxlength="20" readonly
               required>
    </div>

    <div class="inp">
        <b><fmt:message key="label.start.surname"/>:</b>
        <input type="text" id="surname" class="dataContentInput" onchange="setNewSurname()" onfocus="checkSurname(this)"
               oninput="checkSurname(this)"
               value="${surnameData}" pattern="[A-Z][a-z]+(-[A-Z][a-z]+)?" maxlength="30" readonly required>
    </div>

    <div class="inp">
        <b><fmt:message key="label.data.role"/>:</b>
        <input type="text" value="${roleData}" class="dataContentInput" name="role" readonly>
    </div>

    <div class="inp">
        <b><fmt:message key="label.table.dateOfReg"/>:</b>
        <input type="text" value="${dateOfReg}" class="dataContentInput" name="data-reg" readonly>
    </div>

    <div class="errorText"><b>${param.errorChangeData}</b></div>
</div>

<div id="responseBtn">
    <form class="responseBtn" name="cancel" method="GET" action="controller">
        <button type="submit" class="cancelbtn" name="command" value="view-data"><fmt:message key="label.start.cancelBtn"/></button>
    </form>
    <form class="responseBtn" name="confirm" method="POST" action="controller">
        <input type="hidden" id="newName" name="name" value="${nameData}">
        <input type="hidden" id="newSurname" name="surname" value="${surnameData}">
        <input type="hidden" id="newBankCard" name="bankCard" value="${bankCardData}">
        <button type="submit" class="confirmbtn" name="command" value="change-user-data"><fmt:message
                key="label.start.confirm"/></button>
    </form>
</div>

<div id="id03" class="changePassword">
    <input type="hidden" id="checkConfirm" name="state" value="${param.stateCheck}"/>
    <span onclick=changePasswordStop(); class="closeConfirm"
          title="<fmt:message key="label.start.cancelBtn"/>">×</span>
    <form class="modal-content animate" name="changePassForm" method="POST" action="controller">
        <div class="containerChangePassword">
            <div class="center">
                <b><fmt:message key="label.data.inpPass"/></b>
                <br/>
                <input type="password"
                       placeholder="<fmt:message key="label.start.placeholder.password"/>" name="password"
                       onfocus="checkPassword(this)" oninput="checkPassword(this)" pattern="[A-Za-z_\d]{4,15}"
                       minlength="4" maxlength="15" required>
                <br/>
                <b><fmt:message key="label.data.inpNewPass"/></b>
                <br/>
                <input id="password1" type="password"
                       placeholder="<fmt:message key="label.start.placeholder.password"/>" name="password1"
                       onfocus="checkPassword(this)" oninput="checkPassword(this)" pattern="[A-Za-z_\d]{4,15}"
                       minlength="4" maxlength="15" required>
                <br/>
                <b><fmt:message key="label.data.repNewPass"/></b>
                <br/>
                <input type="password" placeholder="<fmt:message key="label.start.placeholder.repeatPassword"/>"
                       name="password2" onfocus="checkRepeatPassword(document.getElementById('password1'), this)"
                       oninput="checkRepeatPassword(document.getElementById('password1'), this)"
                       pattern="[A-Za-z_\d]{4,15}" minlength="4" maxlength="15" required>

                <br/>
                <div id="textChangePassword">${param.errorChangePassword}</div>
                <br/>

                <button type="submit" class="confirmChangeBtn" name="command" value="change-password"><fmt:message
                        key="label.start.confirm"/></button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
