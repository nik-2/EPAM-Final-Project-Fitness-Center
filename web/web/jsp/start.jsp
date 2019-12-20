<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 22.10.2019
  Time: 23:19
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
    <title><fmt:message key="label.start.title"/></title>
    <link rel="stylesheet" type="text/css" href=css/login.css>
    <link rel="stylesheet" type="text/css" href=css/registration.css>
    <link rel="stylesheet" type="text/css" href=css/start.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <link rel="stylesheet" type="text/css" href=css/regconfirm.css>
    <script src="js/menu.js"></script>
    <script src="js/regandlog.js"></script>
</head>
<body class="image">
<c:set var="role" scope="session"> ${role} </c:set>
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
                <input type="hidden" name="page" value="/jsp/start.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/start.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>

<c:if test="${role.equalsIgnoreCase('GUEST')}">

    <div id="reg">
        <button onclick=displayBlock()><fmt:message key="label.start.sign_up"/></button>
    </div>

    <div id="id01" class="modal">
        <span onclick=displayNone(); class="close" title="<fmt:message key="label.start.closeSignIn.title"/>">×</span>
        <form class="modal-content" name="regForm" method="POST" action="controller">
            <div class="container">
                <h1><fmt:message key="label.start.sign_up"/></h1>
                <p><fmt:message key="label.start.container.p"/></p>
                <hr>
                <b><fmt:message key="label.start.email"/></b>
                <input type="text" placeholder="<fmt:message key="label.start.placeholder.email"/>"
                       onfocus="checkEmail(this)" oninput="checkEmail(this)" name="mail"
                       pattern="[a-z\d_\.]+@[a-z]+\.[a-z]{2,4}$" maxlength="30" required>

                <b><fmt:message key="label.start.name"/></b>
                <input type="text" onfocus="checkName(this)" oninput="checkName(this)"
                       placeholder="<fmt:message key="label.start.placeholder.name"/>" name="name"
                       pattern="[A-Z][a-z]+(-[A-Z][a-z]+)?" maxlength="20" required>

                <b><fmt:message key="label.start.surname"/></b>
                <input type="text" onfocus="checkSurname(this)" oninput="checkSurname(this)"
                       placeholder="<fmt:message key="label.start.placeholder.surname"/>" name="surname"
                       pattern="[A-Z][a-z]+(-[A-Z][a-z]+)?" maxlength="30" required>

                <b><fmt:message key="label.start.password"/></b>
                <input id="password1" type="password"
                       placeholder="<fmt:message key="label.start.placeholder.password"/>" name="password1"
                       onfocus="checkPassword(this)" oninput="checkPassword(this)" pattern="[A-Za-z_\d]{4,15}"
                       minlength="4" maxlength="15" required>

                <b><fmt:message key="label.start.repeatPassword"/></b>
                <input type="password" placeholder="<fmt:message key="label.start.placeholder.repeatPassword"/>"
                       name="password2" onfocus="checkRepeatPassword(document.getElementById('password1'), this)"
                       oninput="checkRepeatPassword(document.getElementById('password1'), this)"
                       pattern="[A-Za-z_\d]{4,15}" minlength="4" maxlength="15" required>

                <br/>
                <div id="textReg"> ${param.errorRegistrationMessage}</div>
                <br/><br/>

                <div class="clearfix">
                    <button type="button" onclick=displayNone(); class="cancelbtnSignUp"><fmt:message
                            key="label.start.cancelBtn"/></button>
                    <button type="submit" class="signupbtn" name="command" value="registration"><fmt:message
                            key="label.start.sign_up"/></button>
                </div>
            </div>
        </form>
    </div>

    <div id="log">
        <button onclick=displayBlockLogin()><fmt:message key="label.start.login"/></button>
    </div>

    <div id="id02" class="modalSignIn">
        <form class="modal-contentSignIn animate" name="logForm" method="POST" action="controller">
            <input type="hidden" id="checkBlock" name="state" value="${param.state}"/>
            <div class="imgcontainer">
                <span onclick=displayNoneLogin(); class="closeSignIn"
                      title="<fmt:message key="label.start.closeSignIn.title"/>">×</span>
                <img src="image/avatar.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="containerSignIn">
                <b><fmt:message key="label.start.email"/></b>
                <input id="email" type="text" onfocus="checkEmail(this)" oninput="checkEmail(this)"
                       placeholder="<fmt:message key="label.start.placeholder.email"/>" name="mail"
                       pattern="[a-z\d_\.]+@[a-z]+\.[a-z]{2,4}$" maxlength="30" required>

                <b><fmt:message key="label.start.password"/></b>
                <input type="password" onfocus="checkPassword(this)" oninput="checkPassword(this)"
                       placeholder="<fmt:message key="label.start.placeholder.password"/>" name="password"
                       pattern="[A-Za-z_\d]{4,15}" minlength="4" maxlength="15" required>

                <br/>
                <div id="textLogin"> ${param.errorLoginPassMessage} </div>
                <br/><br/>

                <button type="submit" class="signinbtn" name="command" value="login"><fmt:message
                        key="label.start.login"/></button>
                <label>
                    <input type="checkbox" checked="checked" name="remember"> <fmt:message
                        key="label.start.rememberMe"/>
                </label>
            </div>

            <div class="containerSignInBtn">
                <button type="button" onclick=displayNoneLogin(); class="cancelbtnSignIn"><fmt:message
                        key="label.start.cancelBtn"/></button>
            </div>
        </form>
    </div>

    <div id="id03" class="modalConfirm">
        <input type="hidden" id="checkConfirm" name="state" value="${param.stateCheck}"/>
        <span onclick=displayConfirmNone(); class="closeConfirm"
              title="<fmt:message key="label.start.cancelBtn"/>">×</span>
        <form class="modal-contentConfirm animate" name="confirmForm" method="POST" action="controller">
            <div class="containerConfirm">
                <div><fmt:message key="label.start.confirmtext"/></div>
                <br/>
                <input id="confirmCode" type="text" placeholder="<fmt:message key="label.start.placeholder.confirm"/>"
                       name="confirm" maxlength="4" minlength="4" required>

                <br/>
                <div id="textConfirm"> ${param.errorConfirmMessage} </div>
                <br/><br/>

                <button type="submit" class="confirmbtn" name="command" value="confirm"><fmt:message
                        key="label.start.confirm"/></button>
            </div>
        </form>
    </div>
</c:if>
<c:if test="${!role.equalsIgnoreCase('GUEST')}">
    <form name="profileForm" method="GET" action="controller">
        <button class="icon-profile" type="submit" name="command" value="profile"><i class="fa fa-home"></i></button>
    </form>
</c:if>
</body>
</html>
