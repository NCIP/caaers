<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="tab" type="gov.nih.nci.cabig.ctms.web.tabs.Tab" required="true" %>
<%@attribute name="flow" type="gov.nih.nci.cabig.ctms.web.tabs.Flow" required="true" %>
<ul id="level2" class="tabs autoclear">
<c:forEach items="${flow.tabs}" var="atab">
    <c:set var="current" value="${atab.number == tab.number}"/>
    <li class="tab ${current ? 'current' : ''}">
        <img src="<chrome:imageUrl name="tab2${current ? '_h' : ''}_L.gif"/>" width="1" height="16" align="absmiddle"><a href="#" class="tab${atab.number}">${atab.number + 1}. ${atab.shortTitle}</a><img src="<chrome:imageUrl name="tab2${current ? '_h' : ''}_R.gif"/>" width="6" height="16" align="absmiddle">
    </li>
</c:forEach>
</ul>
<div id="level2-spacer"></div>
