<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@attribute name="title"%>
<chrome:box title="${title}">
    <div class="content">
        <jsp:doBody/>
    </div>
</chrome:box>
