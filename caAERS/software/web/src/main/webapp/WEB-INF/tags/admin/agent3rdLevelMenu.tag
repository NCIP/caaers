<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="selected" type="java.lang.String" %>

<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Agent:CREATE" var="hasAgentCreate"/>

<div class="workflow-tabs2">
    <ul id="" class="tabs autoclear">
        <li id="thirdlevelnav" class="tab ${selected eq 'search' ? 'selected' : ''}"><div><a href="asaelSearch">Search Agents</a></div></li>
        <c:if test="${hasAgentCreate}">
            <li id="thirdlevelnav" class="tab ${selected eq 'create' ? 'selected' : ''}"><div><a href="asaelCreate">Enter / Edit Agent</a></div></li>
        </c:if>
        <%--<li id="thirdlevelnav" class="tab ${selected eq 'import' ? 'selected' : ''}"><div><a href="asaelImport">Import Agents</a></div></li>--%>
    </ul>
    <tags:pageHelp propertyKey="searchIND"/>
</div>
