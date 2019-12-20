<%--
  Created by IntelliJ IDEA.
  User: Nikita
  Date: 01.12.2019
  Time: 13:54
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
    <title><fmt:message key="label.data.subscription"/></title>
    <link rel="stylesheet" type="text/css" href=css/user/subscription.css>
    <link rel="stylesheet" type="text/css" href=css/user/profile.css>
    <link rel="stylesheet" type="text/css" href=css/mainMenu.css>
    <link rel="stylesheet" type="text/css" href=css/languageMenu.css>
    <script src="js/subscription.js"></script>
    <script src="js/menu.js"></script>
</head>
<body class="image">
<jsp:useBean id="listResults" class="java.util.ArrayList" scope="session"/>
<c:set var="CARDID" scope="session"> ${cardId} </c:set>
<c:set var="SUBSCRIPTION" scope="session"> ${subscription} </c:set>
<c:set var="COACH" scope="session"> ${coach} </c:set>
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
                <input type="hidden" name="page" value="/jsp/user/subscription.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.en"/>"/>
            </form>
        </li>
        <li>
            <form name="russianForm" method="GET" action="controller">
                <input type="hidden" name="command" value="ru"/>
                <input type="hidden" name="page" value="/jsp/user/subscription.jsp"/>
                <input type="submit" value="<fmt:message key="label.start.ru"/>"/>
            </form>
        </li>
    </ul>
</div>
<div class="inp">
    <b><fmt:message key="label.subscription.balance"/>:</b>
    <input type="text" value="${clubBalance}" class="clubBalance" name="Club balance" readonly>
    <input type="submit" onclick="displayBlock()" class="replenishBalance" value="+"/>
</div>
<div class="errorText"><b>${param.moneyValueError}</b></div>

<div id="id01" class="modalSelectType">
    <span onclick=displayNone(); class="closeSelect"
          title="">×</span>
    <c:if test="${CARDID.equalsIgnoreCase('')}">
        <form class="modal-contentSelect animate" name="confirmForm" method="GET" action="controller">
            <div class="contentSelectText"><fmt:message key="label.subscription.noCreditCard"/></div>
            <br/>
            <button type="submit" class="selectbtn" name="command" value="link-card"><fmt:message key="label.subscription.linkCard"/></button>
            <br/>
        </form>
    </c:if>
    <c:if test="${!CARDID.equalsIgnoreCase('')}">
        <form class="modal-contentSelect animate" name="confirmForm" method="POST" action="controller">
            <div class="contentSelectText"><fmt:message key="label.subscription.enterBalance"/></div>
            <br/>
            <input id="confirmValue" class="confirmInp" type="text" placeholder="<fmt:message key="label.subscription.inputAmount"/>"
                   onfocus="checkValue(this)" oninput="checkValue(this)" pattern="[1-9]\d{0,4}" name="value"
                   maxlength="5" minlength="1" required>
            <br/>
            <button type="submit" class="selectbtn" name="command" value="replenish"><fmt:message key="label.subscription.relenish"/></button>
            <br/>
        </form>
    </c:if>
</div>
<c:if test="${SUBSCRIPTION.equalsIgnoreCase('NONE')}">
    <div id="headline"> <fmt:message key="label.subscription.selectSub"/></div>

    <div id="service1" onclick="change1()">
        <ul>
            <li id="11" class="line1"><fmt:message key="label.services.service1.line1"/></li>
            <li id="12" class="line2"><fmt:message key="label.services.service1.line2"/></li>
            <li id="13" class="line3"><fmt:message key="label.services.service.line3"/></li>
            <li id="14" class="line4"><fmt:message key="label.services.service.line4"/></li>
        </ul>
    </div>
    <div id="service2" onclick="change2()">
        <ul>
            <li id="21" class="line1"><fmt:message key="label.services.service2.line1"/></li>
            <li id="22" class="line2"><fmt:message key="label.services.service2.line2"/></li>
            <li id="23" class="line3"><fmt:message key="label.services.service.line3"/></li>
            <li id="24" class="line4"><fmt:message key="label.services.service.line4"/></li>
        </ul>
    </div>
    <div id="service3" onclick="change3()">
        <ul>
            <li id="31" class="line1"><fmt:message key="label.services.service3.line1"/></li>
            <li id="32" class="line2"><fmt:message key="label.services.service3.line2"/></li>
            <li id="33" class="line3"><fmt:message key="label.services.service.line3"/></li>
            <li id="34" class="line4"><fmt:message key="label.services.service.line4"/></li>
        </ul>
    </div>
    <div id="service4" onclick="change4()">
        <ul>
            <li id="41" class="line1"><fmt:message key="label.services.service4.line1"/></li>
            <li id="42" class="line2"><fmt:message key="label.services.service4.line2"/></li>
            <li id="43" class="line3"><fmt:message key="label.services.service.line3"/></li>
            <li id="44" class="line4"><fmt:message key="label.services.service.line4"/></li>
        </ul>
    </div>
    <div id="service5" onclick="change5()">
        <ul>
            <li id="51" class="line1"><fmt:message key="label.services.service5.line1"/></li>
            <li id="52" class="line2"><fmt:message key="label.services.service5.line2"/></li>
            <li id="55" class="line5"><fmt:message key="label.services.service.line5"/></li>
            <li id="53" class="line3"><fmt:message key="label.services.service.line3"/></li>
            <li id="54" class="line4"><fmt:message key="label.services.service.line4"/></li>
        </ul>
    </div>
    <div id="service6" onclick="change6()">
        <ul>
            <li id="61" class="line1"><fmt:message key="label.services.service6.line1"/></li>
            <li id="62" class="line2"><fmt:message key="label.services.service6.line2"/></li>
            <li id="65" class="line5"><fmt:message key="label.services.service.line5"/></li>
            <li id="63" class="line3"><fmt:message key="label.services.service.line3"/></li>
            <li id="64" class="line4"><fmt:message key="label.services.service.line4"/></li>
        </ul>
    </div>
    <div id="id02" class="modalConfirmSubscription">
    <span onclick=displayConfirmNone(); class="closeConfirm"
          title="Close">×</span>
        <form class="modal-confirmSubscription animate" name="confirmForm" method="POST" action="controller">
            <div class="contentSelectText"><fmt:message key="label.subscription.confirmSub"/></div>
            <br/>
            <input type="hidden" id="duration" name="duration" value=""/>
            <input type="hidden" id="cost" name="cost" value=""/>
            <input type="hidden" id="selectSub" name="selectSub" value=""/>
            <button type="submit" class="confirmSubscriptionBtn" name="command" value="change-subscription"><fmt:message key="label.start.confirm"/>
            </button>
            <br/>
        </form>
    </div>
    </div>
    <div id="takeTable">
        <span id="cancel" onclick=tableNone(); class="closeTable"
              title="">×</span>
        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="<fmt:message key="label.changeUsers.placeholder"/>" title="<fmt:message key="label.changeUsers.titleMail"/>">
        <div class="scroll-table">
            <table id="myTable">
                <tr class="header">
                    <th class="column1"><fmt:message key="label.table.select"/></th>
                    <th class="column2"><fmt:message key="label.table.mail"/></th>
                    <th class="column3"><fmt:message key="label.table.name"/></th>
                    <th class="column4"><fmt:message key="label.table.surname"/></th>
                    <th class="column5"><fmt:message key="label.table.role"/></th>
                </tr>
                <c:forEach items="${listResults}" var="cell">
                    <tr class="" onclick="setSelectMail(this)">
                        <td>
                            <button type="button" class="checkMark" onclick="displayTableBlock(); changeStyle(this)">
                                &#10004;
                            </button>
                        </td>
                        <td>${cell.mail}</td>
                        <td>${cell.name}</td>
                        <td>${cell.surname}</td>
                        <td>${cell.role}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="change" class="modalConfirm">
            <form name="confirmForm" method="POST" action="controller">
                <input type="hidden" id="durationCoach" name="duration" value=""/>
                <input type="hidden" id="btn" name="selectMail" value=""/>
                <input type="hidden" id="costCoach" name="cost" value=""/>
                <input type="hidden" id="selectSubCoach" name="selectSub" value=""/>
                <button type="submit" class="confirmbtn" name="command" value="change-subscription-coach"><fmt:message
                        key="label.start.confirm"/></button>
            </form>
            <button type="submit" class="cancelbtn" onclick="tableNone(); displayTableNone(); removeChangeStyle()">
                Cancel
            </button>
        </div>
    </div>
</c:if>
<c:if test="${((!SUBSCRIPTION.equalsIgnoreCase('NONE') && !SUBSCRIPTION.equalsIgnoreCase('MONTH_WITH_COACH') && !SUBSCRIPTION.equalsIgnoreCase('MONTH3_WITH_COACH') ) && COACH.equalsIgnoreCase(''))
                || ((SUBSCRIPTION.equalsIgnoreCase('MONTH_WITH_COACH') || SUBSCRIPTION.equalsIgnoreCase('MONTH3_WITH_COACH')) && !COACH.equalsIgnoreCase(''))}">
    <h2><fmt:message key="label.subscription.aboutSub"/></h2>
    <div class="dataContent">
        <div class="inpData">
            <b><fmt:message key="label.data.subscription"/>:</b>
            <input type="text" value="${subscription}" class="dataContentInput" name="subscription" readonly>
        </div>

        <div class="inpData">
            <b><fmt:message key="label.data.subscriptionEnd"/>:</b>
            <input type="text" value="${subscriptionEndData}" class="dataContentInput" name="subscriptionEnd" readonly>
        </div>
    <c:if test="${(SUBSCRIPTION.equalsIgnoreCase('MONTH_WITH_COACH') || SUBSCRIPTION.equalsIgnoreCase('MONTH3_WITH_COACH')) && !COACH.equalsIgnoreCase('')}">
        <div class="inpData">
            <b><fmt:message key="label.subscription.yourCoach"/>:</b>
            <input type="text" value="${coachSurname} ${coachName} ${coach}" class="dataContentInput" name="coach" readonly>
        </div>
    </c:if>
    </div>
</c:if>

<c:if test="${(SUBSCRIPTION.equalsIgnoreCase('MONTH_WITH_COACH') || SUBSCRIPTION.equalsIgnoreCase('MONTH3_WITH_COACH')) && COACH.equalsIgnoreCase('')}">
    <div class="headline"><fmt:message key="label.subscription.selectYourCoach"/></div>
        <input type="text" id="myInput2" onkeyup="myFunction2()" placeholder="<fmt:message key="label.changeUsers.placeholder"/>" title="<fmt:message key="label.changeUsers.titleMail"/>">
        <div class="scroll-table">
            <table id="myTable2">
                <tr class="header">
                    <th class="column1"><fmt:message key="label.table.select"/></th>
                    <th class="column2"><fmt:message key="label.table.mail"/></th>
                    <th class="column3"><fmt:message key="label.table.name"/></th>
                    <th class="column4"><fmt:message key="label.table.surname"/></th>
                    <th class="column5"><fmt:message key="label.table.role"/></th>
                </tr>
                <c:forEach items="${listResults}" var="cell">
                    <tr class="" onclick="setSelectMail2(this)">
                        <td>
                            <button type="button" class="checkMark" onclick="displayTableBlock2(); changeStyle2(this)">
                                &#10004;
                            </button>
                        </td>
                        <td>${cell.mail}</td>
                        <td>${cell.name}</td>
                        <td>${cell.surname}</td>
                        <td>${cell.role}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="change2" class="modalConfirm">
            <form name="confirmForm" method="POST" action="controller">
                <input type="hidden" id="btn2" name="selectCoach" value=""/>
                <button type="submit" class="confirmbtn" name="command" value="change-subscription-coach"><fmt:message
                        key="label.start.confirm"/></button>
            </form>
            <button type="submit" class="cancelbtn" onclick="displayTableNone2(); removeChangeStyle2()">
                <fmt:message key="label.start.cancelBtn"/>
            </button>
        </div>

</c:if>
</body>
</html>
