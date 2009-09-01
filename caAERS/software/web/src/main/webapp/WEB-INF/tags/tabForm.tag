<!-- BEGIN tags\tabForm.tag -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="tab" required="true" type="gov.nih.nci.cabig.ctms.web.tabs.Tab" %>
<%@attribute name="flow" required="true" type="gov.nih.nci.cabig.ctms.web.tabs.Flow" %>
<%@attribute name="willSave" %>
<%@attribute name="title" %>
<%@attribute name="formName" %>
<%@attribute name="formId" %>
<%@attribute name="enctype" %>
<%@attribute name="boxId" %>
<%@attribute name="boxClass" %>
<%@attribute name="pageHelpAnchor" %>
<%@attribute name="instructions" fragment="true" %>
<%@attribute name="singleFields" fragment="true" %>
<%@attribute name="repeatingFields" fragment="true" %>
<%@attribute name="localButtons" fragment="true" %>
<%@attribute name="tabControls" fragment="true" %>
<%@attribute name="header" fragment="true" description="Will display the content within the form, but above chrome box" %>
<%@attribute name="saveButtonLabel" %>
<%@attribute name="noBackground" required="false" %>
<%@attribute name="hideErrorDetails" type="java.lang.Boolean" %>
<%@attribute name="hideBox" type="java.lang.Boolean" %>
<%@attribute name="additionalTitle" required="false" fragment="false" %>
<c:if test="${empty willSave}">
    <c:set var="willSave" value="${true}"/>
</c:if>
<c:if test="${not hideBox}">
    <form:form name="${formName}" enctype="${enctype}" id="${formId}">
    	<jsp:invoke fragment="header" />
    	<chrome:warningMessage/>
        <chrome:box title="${empty title ? tab.shortTitle : title}" id="${boxId}" cssClass="${boxClass}" noBackground="${noBackground}" additionalTitle="${additionalTitle}">
            <chrome:flashMessage/><tags:tabFields tab="${tab}"/>
            <chrome:division id="single-fields">
                <c:if test="${not empty instructions}">
                    <p class="instructions">
                        <jsp:invoke fragment="instructions"/>
                    </p>
                </c:if>
                <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/><jsp:invoke fragment="singleFields"/>
                <tags:jsErrorsMessage/>
            </chrome:division><jsp:invoke fragment="repeatingFields"/>
        </chrome:box>
        <c:if test="${not empty tabControls}">
                <jsp:invoke fragment="tabControls" />
            </c:if>
        <c:if test="${empty tabControls}">
            <tags:tabControls tab="${tab}" flow="${flow}" localButtons="${localButtons}" willSave="${willSave}" saveButtonLabel="${saveButtonLabel}"/>
        </c:if>
    </form:form>
</c:if>
<c:if test="${hideBox}">
	<chrome:warningMessage/>
    <form:form name="${formName}" enctype="${enctype}" id="${formId}">
    	<jsp:invoke fragment="header" />
    	<chrome:flashMessage/>
        <tags:tabFields tab="${tab}"/>
        <chrome:division id="single-fields">
            <c:if test="${not empty instructions}">
                <p class="instructions">
                    <jsp:invoke fragment="instructions"/>
                </p>
            </c:if>
            <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/><jsp:invoke fragment="singleFields"/>
            <tags:jsErrorsMessage/>
        </chrome:division><jsp:invoke fragment="repeatingFields"/>
        <c:if test="${not empty tabControls}">
            <jsp:invoke fragment="tabControls" />
        </c:if>
        <c:if test="${empty tabControls}">
            <tags:tabControls tab="${tab}" flow="${flow}" localButtons="${localButtons}" willSave="${willSave}" saveButtonLabel="${saveButtonLabel}"/>
        </c:if>
    </form:form>
</c:if><!-- END tags\tabForm.tag -->
