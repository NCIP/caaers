<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <script type="text/javascript">
        Element.observe(window, "load", function() {
            Element.observe('add-lab-button', "click", function() {
                alert("TODO")
            })
        })
    </script>
</head>
<body>
<chrome:body title="${flow.name}: ${tab.longTitle}">
    <p id="instructions">
        You are entering labs for an AE for ${command.assignment.participant.fullName} on
        ${command.assignment.studySite.study.shortTitle}.
    </p>

    <chrome:division title="Labs">
        <form:form cssClass="standard">
            <tags:errors path="*"/>
            <tags:tabFields tab="${tab}"/>

            <c:forEach items="${fieldGroups}" var="labGroupEntry">
                <div class="repeatedGroup first" id="${labGroupEntry.value.name}">
                    <h2>${labGroupEntry.value.displayName}</h2>
                    <c:forEach items="${labGroupEntry.value.fields}" var="field">
                        <tags:renderRow field="${field}"/>
                    </c:forEach>
                </div>
            </c:forEach>

        </form:form>
        <div class="row">
            <div class="value"><input type="button" id="add-lab-button" value="Add another lab"/></div>
        </div>
    </chrome:division>
</chrome:body>
</body>
</html>