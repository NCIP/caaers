<%--
    This is the decorator for workflow page that doesn't use a WizardForm.
--%>
<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="standard" tagdir="/WEB-INF/tags/standard"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>caAERS || Assign A Participant to a Study || <decorator:title/></title>
    <standard:head/>
    <tags:stylesheetLink name="tabbedflow"/>
    <tags:javascriptLink name="tabbedflow"/>
    <decorator:head/>
</head>
<body>
<standard:header/>
<div class="tabpane">
    <ul id="level2" class="tabs autoclear">
        <c:set var="current">${atab.number == tab.number}</c:set>
        <li class="tab ${current ? 'current' : ''}">
            <img src="<chrome:imageUrl name="tab2${current ? '_h' : ''}_L.gif"/>" width="1" height="16" align="absmiddle"><a href="#" class="tab${atab.number}">1. Select Study</a><img src="<chrome:imageUrl name="tab2${current ? '_h' : ''}_R.gif"/>" width="6" height="16" align="absmiddle">
        </li>
        <li class="tab ${current ? 'current' : ''}">
            <img src="<chrome:imageUrl name="tab2${current ? '_h' : ''}_L.gif"/>" width="1" height="16" align="absmiddle"><a href="#" class="tab${atab.number}">2. Select Participant</a><img src="<chrome:imageUrl name="tab2${current ? '_h' : ''}_R.gif"/>" width="6" height="16" align="absmiddle">
        </li>
        <li class="tab ${current ? 'current' : ''}">
            <img src="<chrome:imageUrl name="tab2${current ? '_h' : ''}_L.gif"/>" width="1" height="16" align="absmiddle"><a href="#" class="tab${atab.number}">3. Review and Submit</a><img src="<chrome:imageUrl name="tab2${current ? '_h' : ''}_R.gif"/>" width="6" height="16" align="absmiddle">
        </li>
    </ul>
    <div id="level2-spacer"></div>

    <div class="tabcontent workArea">
        <div class="body">
            <decorator:body/>
        </div>
        <div class="tabcontrols autoclear">
        </div>
    </div>
</div>
<standard:footer/>
</body>
</html>
