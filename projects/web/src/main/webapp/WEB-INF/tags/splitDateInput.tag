<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="path" %>
<%@attribute name="required" type="java.lang.Boolean" description="Will tell the field itself is required" %>
<%@attribute name="yearRequired" type="java.lang.Boolean"
             description="Will tell whether date field as whole is required" %>
<%@attribute name="dayRequired" type="java.lang.Boolean" description="If true, the day is mandatory" %>
<%@attribute name="monthRequired" type="java.lang.Boolean" description="If true, the month is mandatory" %>
<%@attribute name="cssClass" %>
<table cellspacing="0" cellpadding="0" border="0" class="split-date-wrap">
    <tr>
        <td>
            <form:input path="${path}.monthString"
                        cssClass=" ${required and monthRequired ? cssClass :'validate-NUMERIC' }"
                        maxlength="2" size="2"/>/
            <label for="${path}.month">
                <c:if
                        test="${monthRequired}">
                    <tags:requiredIndicator/>
                </c:if>MM
            </label>
        </td>
        <td>
            <form:input path="${path}.dayString"
                        cssClass="${required and dayRequired ? cssClass :'validate-NUMERIC' }"
                        maxlength="2" size="2"/>/
            <label for="${path}.day">
                <c:if
                        test="${dayRequired}">
                    <tags:requiredIndicator/>
                </c:if>DD
            </label></td>
        <td>
            <form:input path="${path}.yearString"
                        cssClass="split-date ${required and yearRequired ? cssClass:'validate-NUMERIC'}"
                        maxlength="4" size="4"/>
            <label for="${path}.yearString">
                <c:if test="${yearRequired}">
                    <tags:requiredIndicator/>
                </c:if>YYYY
            </label>
        </td>
        <td valign="top">&nbsp;<a href="#" id="${path}-calbutton"><img src="<chrome:imageUrl name="b-calendar.gif"/>" alt="Calendar" width="17" height="16" border="0" align="absmiddle"/></a></td>
    </tr>
</table>
