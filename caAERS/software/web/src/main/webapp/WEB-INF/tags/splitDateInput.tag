<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>

<%@attribute name="path" %>
<%@attribute name="required" type="java.lang.Boolean" description="Will tell the field itself is required" %>
<%@attribute name="yearRequired" type="java.lang.Boolean" description="Will tell whether date field as whole is required" %>
<%@attribute name="dayRequired" type="java.lang.Boolean" description="If true, the day is mandatory" %>
<%@attribute name="monthRequired" type="java.lang.Boolean" description="If true, the month is mandatory" %>
<%@attribute name="cssClass" %>

<c:set var="yearValue"><jsp:attribute name="value"><caaers:value path="${path}.year" /></jsp:attribute></c:set>
<c:set var="monthValue"><jsp:attribute name="value"><caaers:value path="${path}.month" /></jsp:attribute></c:set>
<c:set var="dateValue"><jsp:attribute name="value"><caaers:value path="${path}.day" /></jsp:attribute></c:set>

<table cellspacing="0" cellpadding="0" border="0" class="split-date-wrap">
    <tr>
        <td>
            <form:input path="${path}.monthString" cssClass="${empty monthValue and required and monthRequired ? 'required' : not empty monthValue ? 'valueOK' : ''} ${required and monthRequired ? cssClass :'validate-NUMERIC' }" maxlength="2" size="2"/>/
            <label for="${path}.month"><c:if test="${monthRequired}"><tags:requiredIndicator/></c:if>MM</label>
        </td>
        <td>
            <form:input path="${path}.dayString" cssClass="${empty dateValue and required and dayRequired ? 'required' : not empty dateValue ? 'valueOK' : ''} ${cssValue} ${required and dayRequired ? cssClass :'validate-NUMERIC' }" maxlength="2" size="2"/>/
            <label for="${path}.day"><c:if test="${dayRequired}"><tags:requiredIndicator/></c:if>DD</label>
        </td>
        <td>
            <form:input path="${path}.yearString" cssClass="${empty yearValue and required and yearRequired ? 'required' : not empty yearValue ? 'valueOK' : ''} ${cssValue} split-date ${required and yearRequired ? cssClass:'validate-NUMERIC'}" maxlength="4" size="4"/>
            <label for="${path}.yearString"><c:if test="${yearRequired}"><tags:requiredIndicator/></c:if>YYYY</label>
        </td>
        <td valign="top">&nbsp;<a href="#" id="${path}-calbutton"><img src="<chrome:imageUrl name="b-calendar.gif"/>" alt="Calendar" width="17" height="16" border="0" align="absmiddle"/></a></td>
    </tr>
</table>
