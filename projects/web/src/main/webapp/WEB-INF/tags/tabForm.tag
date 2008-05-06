<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="tab" required="true" type="gov.nih.nci.cabig.ctms.web.tabs.Tab" %>
<%@attribute name="flow" required="true" type="gov.nih.nci.cabig.ctms.web.tabs.Flow" %>
<%@attribute name="willSave"%>
<%@attribute name="title"%>
<%@attribute name="formName"%>
<%@attribute name="enctype"%>
<%@attribute name="boxId"%>
<%@attribute name="boxClass" %>
<%@attribute name="pageHelpAnchor"%>
<%@attribute name="instructions" fragment="true" %>
<%@attribute name="singleFields" fragment="true" %>
<%@attribute name="repeatingFields" fragment="true" %>
<%@attribute name="localButtons" fragment="true" %>
<%@attribute name="saveButtonLabel" %>
<%@attribute name="hideErrorDetails" type="java.lang.Boolean" %>
<c:if test="${empty willSave}"><c:set var="willSave" value="${true}"/></c:if>
<c:if test="${not empty pageHelpAnchor}"><c:set var="pageHelp"><tags:pageHelp anchor="${pageHelpAnchor}"/></c:set></c:if>
<chrome:box title="${pageHelp}${empty title ? tab.shortTitle : title}" id="${boxId}" cssClass="${boxClass}">
    <chrome:flashMessage/>
    <form:form name="${formName}" enctype="${enctype}">
        <tags:tabFields tab="${tab}"/>
        <chrome:division id="single-fields">
            <c:if test="${not empty instructions}"><p class="instructions"><jsp:invoke fragment="instructions"/></p></c:if>
            <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/>
            <jsp:invoke fragment="singleFields"/>
        </chrome:division>
        <jsp:invoke fragment="repeatingFields"/>
        <tags:tabControls tab="${tab}" flow="${flow}" localButtons="${localButtons}" willSave="${willSave}" saveButtonLabel="${saveButtonLabel}"/>
    </form:form>
</chrome:box>
