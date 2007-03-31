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
    <p id="instructions">
        You are entering labs for an AE for ${command.assignment.participant.fullName} on
        ${command.assignment.studySite.study.shortTitle}.
    </p>
    <p>
        TODO: this page is temporarily broken.
    </p>

    <form:form cssClass="standard">
        <tags:errors path="*"/> <%-- TODO: this will need to be moved --%>
        <tags:tabFields tab="${tab}"/>
        <c:forEach items="${fieldGroups}" var="labGroupEntry">
            <c:set var="fields" value="${labGroupEntry.value.fields}"/>
            <chrome:division title="${labGroupEntry.value.displayName}">
                <div class="repeatedGroup first" id="${labGroupEntry.value.name}">
                    <tags:renderRow field="${fields[0]}"/>
                    <tags:renderRow field="${fields[1]}"/>
                    <c:forEach begin="2" end="7" step="2" var="i">
                        <div class="row">
                            <div class="label"><tags:renderLabel field="${fields[i]}"/></div>
                            <div class="value">
                                <tags:renderInputs field="${fields[i]}"/>
                                <form:label path="${fields[i+1].propertyName}">date</form:label>
                                <tags:renderInputs field="${fields[i+1]}"/>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </chrome:division>
        </c:forEach>
    </form:form>
</body>
</html>