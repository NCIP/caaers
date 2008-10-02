<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="commons" uri="http://bioinformatics.northwestern.edu/taglibs/commons"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
<head>
    <title>Configure caAERS</title>
    <style type="text/css">
        div.row {
            padding: 5px 3px;
        }
        .row .value {
            margin-left: 22%;
        }
        .row .label {
            width: 20%;
            margin-left: 1em;
            text-align: right;
        }
        p.description {
            margin: 0.25em 0 0 1em;
        }
        div.submit {
            text-align: right;
        }
        .value input[type=text] {
            width: 80%;
        }

        form {
            margin-top: 1em;
        }

        .updated {
            border: #494 solid;
            border-width: 1px 0;
            background-color: #8C8;
            padding: 1em 2em;
            text-align: center;
            margin: 1em 30%;
            color: #fff;
            font-weight: bold;
            font-size: 1.1em;
        }
    </style>
</head>
<body>
    <chrome:box autopad="true">
    <c:url value="/pages/admin/configure" var="action"/>
    <form:form action="${action}" cssClass="standard">
        <c:forEach items="${command.conf}" var="entry" varStatus="status">
            <div class="row">
                <div class="label">
                    <form:label path="conf[${entry.key}].value">${entry.value.property.name}</form:label>
                </div>
                <div class="value">
                    <c:set var="beanPath">conf[${entry.key}].value</c:set>
                    <c:choose>
                        <c:when test="${entry.value.property.controlType == 'boolean'}">
                            <div>
                                <label><form:radiobutton path="${beanPath}" value="true"/> Yes</label>
                                <label><form:radiobutton path="${beanPath}" value="false"/> No</label>
                            </div>
                        </c:when>
                        <c:when test="${entry.value.property.controlType == 'text'}">
                            <div><form:input path="${beanPath}"/></div>
                        </c:when>
                        <c:otherwise>
                            <div>Unimplemented control type ${entry.value.controlType} for ${beanPath}</div>
                        </c:otherwise>
                    </c:choose>
                    <p class="description">${entry.value.property.description}</p>
                    <c:if test="${not empty entry.value.default}"><p class="description">(Default: ${entry.value.default})</p></c:if>
                </div>
            </div>
        </c:forEach>
        <div class="row submit">
            <input type="submit" value="Save"/>
        </div>

        <c:if test="${param.updated}">
            <p class="updated">Settings saved</p>
        </c:if>
    </form:form>
    </chrome:box>
</body>
</html>