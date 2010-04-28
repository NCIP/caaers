<%@attribute name="selected" type="java.lang.String" %>

<div class="workflow-tabs2">
    <ul id="" class="tabs autoclear">
        <li id="thirdlevelnav" class="tab ${selected eq 'search' ? 'selected' : ''}"><div><a href="asaelSearch">Search Agents</a></div></li>
        <li id="thirdlevelnav" class="tab ${selected eq 'create' ? 'selected' : ''}"><div><a href="asaelCreate">Enter / Edit Agent</a></div></li>
        <%--<li id="thirdlevelnav" class="tab ${selected eq 'import' ? 'selected' : ''}"><div><a href="asaelImport">Import Agents</a></div></li>--%>
    </ul>
    <tags:pageHelp propertyKey="searchIND"/>
</div>
