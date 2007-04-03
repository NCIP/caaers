<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%--<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>--%>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <style type="text/css">
        #attribution {
            border-spacing: 0;
            border-collapse: collapse;
        }
        #attribution th {
            text-align: right;
            padding: 2px;
            font-weight: normal;
        }
        #attribution tr.top th.ae, #attribution tr.fields td {
            border: solid #6E81A6;
            border-width: 0 1px;
        }
        #attribution tr.top th {
            text-align: center;
            vertical-align: bottom;
        }
        #attribution tr.top th .index {
            font-weight: normal;
            border-bottom: 1px solid #6E81A6;
        }
        #attribution tr.top th .grade {
        }
        #attribution tr.top th .term {
            font-weight: bold;
            font-size: 120%;
        }
        #attribution tr.subhead th {
            text-align: left;
            font-weight: bold;
            font-size: 120%;
            background-color: #D7D9E3;
            color: black;
            border-top: 1px solid #6E81A6;
        }
        #attribution td {
            text-align: center;
            padding: 2px;
        }
        #attribution tr.fields:hover td, #attribution tr.fields:hover th {
            background-color: #6E81A6;
            color: white;
        }
        #attribution col:hover {
            background-color: #eee;
        }
        #attribution tr.fields td, #attribution tr.fields th {
            border-bottom: 1px solid #6E81A6;
        }
        #attribution col { border: 1px solid #6E81A6; }
    </style>
</head>
<body>
<form:form cssClass="standard">
    <tags:tabFields tab="${tab}"/>
    <chrome:division>
        <p id="instructions">
            You are attributing adverse events to causes for
            ${command.assignment.participant.fullName} on
            ${command.assignment.studySite.study.shortTitle}.
        </p>
        <tags:hasErrorsMessage/>                                                                        

        <c:set var="cols" value="${fn:length(command.aeReport.adverseEvents) + 1}"/>
        <table id="attribution">
            <col class="right" />
            <colgroup>
                <c:forEach begin="0" end="${cols - 2}">
                <col class="ae"/>
                </c:forEach>
            </colgroup>
            <tr class="top">
                <th>Possible cause</th>
                <c:forEach items="${command.aeReport.adverseEvents}" var="ae" varStatus="status">
                    <th class="ae">
                        <div class="index">AE ${status.count}</div>
                        <div class="grade">${ae.grade}</div>
                        <div class="term">${ae.ctcTerm.fullName}</div>
                    </th>
                </c:forEach>
            </tr>
        <c:forEach items="${blocks}" var="block">
            <tr class="subhead">
                <th colspan="${cols}">${block.displayName}</th>
            </tr>
            <c:if test="${empty block.rows}">
            <td colspan="${cols}">No ${block.displayName} for this report.</td>
            </c:if>
            <c:forEach items="${block.rows}" var="row">
            <tr class="fields">
                <th>${row.displayName}</th>
                <c:forEach items="${row.fields}" var="field">
                <td><tags:renderInputs field="${field}"/></td>
                </c:forEach>
            </tr>
            </c:forEach>
        </c:forEach>
        </table>
    </chrome:division>
</form:form>
</body>
</html>