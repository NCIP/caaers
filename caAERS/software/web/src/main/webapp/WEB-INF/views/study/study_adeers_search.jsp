<%@include file="/WEB-INF/views/taglibs.jsp" %>

<title>Search AdEERS Studies</title>

<chrome:box title="Search AdEERS Studies" autopad="true">

    <p><tags:instructions code="study.adEERSsearch.top"/></p>

    <form:form name="searchForm" id="searchForm" method="post">
        <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="alert('Searching...')"/>
    </form:form>
</chrome:box>