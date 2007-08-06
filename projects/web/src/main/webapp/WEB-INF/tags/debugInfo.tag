<%@tag%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="commons" uri="http://bioinformatics.northwestern.edu/taglibs/commons" %>
<div id="debug">
    <h1>Submitted info</h1>
    <h2>Request parameters</h2>
    <dl>
    <c:forEach items="<%= new java.util.TreeMap(request.getParameterMap()) %>" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(fn:join(item.value, ', '))}</dd>
    </c:forEach>
    </dl>
    <h2>Request headers</h2>
    <dl>
    <c:forEach items="<%= edu.nwu.bioinformatics.commons.WebUtils.headersToMap(request) %>" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(fn:join(item.value, ', '))}</dd>
    </c:forEach>
    </dl>
    <h2>Cookies</h2>
    <dl>
    <c:forEach items="${cookie}" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(item.value.value)}</dd>
    </c:forEach>
    </dl>

    <h1>Context info</h1>
    <h2>Request attributes</h2>
    <dl>
    <c:forEach items="<%= edu.nwu.bioinformatics.commons.WebUtils.requestAttributesToMap(request) %>" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(item.value)} <em class="clazz">${commons:classname(item.value)}</em></dd>
    </c:forEach>
    </dl>
    <h2>Request properties</h2>
    <dl>
    <c:forEach items="<%= edu.nwu.bioinformatics.commons.WebUtils.requestPropertiesToMap(request) %>" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(item.value)} <em class="clazz">${commons:classname(item.value)}</em></dd>
    </c:forEach>
    </dl>
    <h2>Session attributes</h2>
    <dl>
    <c:forEach items="<%= edu.nwu.bioinformatics.commons.WebUtils.sessionAttributesToMap(request.getSession(false)) %>" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(item.value)} <em class="clazz">${commons:classname(item.value)}</em></dd>
    </c:forEach>
    </dl>
    <h2>Context initialization parameters</h2>
    <dl>
    <c:forEach items="${initParam}" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(item.value)} <em class="clazz">${commons:classname(item.value)}</em></dd>
    </c:forEach>
    </dl>
    <%-- Since the "page" attributes are scoped within this tag only, there's not much point in displaying them.
    <h2>Page attributes</h2>
    <dl>
    <c:forEach items="${pageScope}" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(item.value)} <em class="clazz">${commons:classname(item.value)}</em></dd>
    </c:forEach>
    </dl>
    --%>
    <h2>Application attributes</h2>
    <dl>
    <c:forEach items="<%= edu.nwu.bioinformatics.commons.WebUtils.servletContextAttributesToMap(application) %>" var="item">
        <dt class="var">${item.key}</dt><dd>${commons:nl2br(item.value)} <em class="clazz">${commons:classname(item.value)}</em></dd>
    </c:forEach>
    </dl>

    <%--
    <h1>Queries</h1>
    <dl>
    <c:forEach items="<%= edu.nwu.genome.mousedb.web.logging.QueryLoggingAppender.getQueryLogs() %>" var="item">
        <dt class="sql">
            <c:forEach items="${item.sqlClauses}" var="clause">
                <p>${clause}</p>
            </c:forEach>
        </dt>
        <dd class="sql">
            <ul>
            <c:forEach items="${item.boundParameters}" var="binding">
                <li>${binding}</li>
            </c:forEach>
            </ul>
        </dd>
        <p>${item.duration} ms</p>
    </c:forEach>
    </dl>
    --%>
</div>
