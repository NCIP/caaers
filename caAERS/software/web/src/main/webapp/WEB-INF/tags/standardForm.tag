<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" required="true"%>
<%@attribute name="formName"%>
<%@attribute name="enctype"%>
<%@attribute name="boxId"%>
<%@attribute name="boxClass" %>
<%@attribute name="pageHelpAnchor"%>
<%@attribute name="instructions" fragment="true" %>
<%@attribute name="singleFields" fragment="true" %>
<%@attribute name="repeatingFields" fragment="true" %>
<%@attribute name="navButtons" fragment="true" description="The stylesheet that is to be applied for the navigation button area" %>
<%@attribute name="hideErrorDetails" type="java.lang.Boolean" %>
<%@attribute name="flashMessage" fragment="true" description="Will show this message in the flash screen"%>
<chrome:box title="${title}" id="${boxId}" cssClass="${boxClass}">
	<c:set var="flashMessage" scope="request"><jsp:invoke fragment="flashMessage"/></c:set>
    <chrome:flashMessage/>
    <form:form name="${formName}" enctype="${enctype}">
        <chrome:division id="single-fields">
            <c:if test="${not empty instructions}"><p class="instructions"><jsp:invoke fragment="instructions"/></p></c:if>
            <tags:jsErrorsMessage/>
            <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/>
            <jsp:invoke fragment="singleFields"/>
        </chrome:division>
        <jsp:invoke fragment="repeatingFields"/>
        <div class="content standard-buttons autoclear">
    		<div class="standard-nav-buttons">
				<jsp:invoke fragment="navButtons" />
			</div>
   		 </div>
    </form:form>
</chrome:box>
