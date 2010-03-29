<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@attribute name="path" %>
<%@attribute name="required" type="java.lang.Boolean" description="Will tell the field itself is required" %>
<%@attribute name="yearRequired" type="java.lang.Boolean" description="Will tell whether date field as whole is required" %>
<%@attribute name="dayRequired" type="java.lang.Boolean" description="If true, the day is mandatory" %>
<%@attribute name="monthRequired" type="java.lang.Boolean" description="If true, the month is mandatory" %>

<%@attribute name="yearMandatory" type="java.lang.Boolean" description="If true, the year is mandatory" %>
<%@attribute name="monthMandatory" type="java.lang.Boolean" description="If true, the month is mandatory" %>
<%@attribute name="dayMandatory" type="java.lang.Boolean" description="If true, the day is mandatory" %>

<%@attribute name="cssClass" %>
<%@attribute name="hideDay" type="java.lang.Boolean" %>

<c:set var="yearValue"><jsp:attribute name="value"><caaers:value path="${path}.year" /></jsp:attribute></c:set>
<c:set var="monthValue"><jsp:attribute name="value"><caaers:value path="${path}.month" /></jsp:attribute></c:set>
<c:set var="dateValue"><jsp:attribute name="value"><caaers:value path="${path}.day" /></jsp:attribute></c:set>

<c:set var="monthCSSValue">
    <jsp:attribute name="value">${empty monthValue and required and monthRequired ? 'required' : ''} ${empty monthValue and monthMandatory ? 'mandatory' : ''} ${not empty monthValue ? 'valueOK' : ''}</jsp:attribute>
</c:set>
<c:set var="dayCSSValue">
    <jsp:attribute name="value">${empty dateValue and required and dayRequired ? 'required' : ''} ${empty dateValue and dayMandatory ? 'mandatory' : ''} ${not empty dateValue ? 'valueOK' : ''}</jsp:attribute>
</c:set>
<c:set var="yearCSSValue">
    <jsp:attribute name="value">${empty yearValue and required and yearRequired ? 'required' : ''} ${empty yearValue and yearMandatory ? 'mandatory' : ''} ${not empty yearValue ? 'valueOK' : ''}</jsp:attribute>
</c:set>

<table cellspacing="0" cellpadding="0" border="0" class="split-date-wrap" width="200px">
    <tr>
        <td valign="top">
            <form:input title="Month" path="${path}.monthString" cssClass="${monthCSSValue} ${required and monthRequired ? cssClass :'validate-NUMERIC' }" maxlength="2" size="2"/>
            <label for="${path}.month"><c:if test="${fn:contains(monthCSSValue, 'mandatory')}"><tags:requiredIndicator/></c:if>MM</label>
        </td>
        <td valign="top" align="center">/</td>

        <td valign="top" <c:if test="${hideDay}">style="display:none;"</c:if>>
            <form:input title="Date" path="${path}.dayString" cssClass="${dayCSSValue} ${required and dayRequired ? cssClass :'validate-NUMERIC' }" maxlength="2" size="2"/>
            <label for="${path}.day"><c:if test="${fn:contains(dayCSSValue, 'mandatory')}"><tags:requiredIndicator/></c:if>DD</label>
        </td>
        <td valign="top" align="center" <c:if test="${hideDay}">style="display:none;"</c:if>>/</td>

        <td valign="top">
            <form:input title="Year" path="${path}.yearString" cssClass="${yearCSSValue} split-date ${required and yearRequired ? cssClass:'validate-NUMERIC'}" maxlength="4" size="4"/>
            <label for="${path}.yearString"><c:if test="${fn:contains(yearCSSValue, 'mandatory')}"><tags:requiredIndicator/></c:if>YYYY</label>
        </td>
        <td valign="top">&nbsp;<a href="#" id="${path}-calbutton"><img src="<chrome:imageUrl name="b-calendar.gif"/>" alt="Calendar" width="17" height="16" border="0" align="absmiddle"/></a></td>
    </tr>
</table>
