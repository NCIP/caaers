<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="path" %>
<%@attribute name="title" %>
<%@attribute name="cssClass" %>
<form:input path="${path}" cssClass="date ${cssClass}" title="${title}"/>
<a href="#" id="${path}-calbutton">
    <img src="<chrome:imageUrl name="b-calendar.gif"/>" alt="Calendar" width="17" height="16" border="0"
         align="absmiddle"/>
</a>
<i>(mm/dd/yyyy)</i>