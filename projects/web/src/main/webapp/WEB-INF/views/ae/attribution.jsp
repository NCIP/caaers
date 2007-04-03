<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae"%>

<c:set var="MAX_COLS" value="${4}"/>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <style type="text/css">
        hr.attrib-divider {
            border: 2px solid #6E81A6;
            margin: 0.4em 2em;
            width: ${13 + MAX_COLS * 16}em;
        }
        .attribution {
            border-spacing: 0;
            border-collapse: collapse;
            margin: 1em 2em;
        }
        .attribution col { border: 1px solid #6E81A6; }
        .attribution col.cause {
            width: 13em;
        }
        .attribution col.ae-attrib {
            width: 16em;
        }
        .attribution th {
            text-align: right;
            padding: 2px 5px;
            font-weight: normal;
        }
        .attribution tr.top th.ae, .attribution tr.fields td {
            border: solid #6E81A6;
            border-width: 0 1px;
        }
        .attribution tr.top th {
            text-align: center;
            vertical-align: top;
        }
        .attribution tr.top th.cause {
            vertical-align: bottom;
        }
        .attribution tr.top th .index {
            font-weight: normal;
            border-bottom: 1px solid #6E81A6;
        }
        .attribution tr.top th .grade {
        }
        .attribution tr.top th .term {
            font-weight: bold;
            font-size: 120%;
        }
        .attribution tr.subhead th {
            text-align: left;
            font-weight: bold;
            font-size: 120%;
            background-color: #D7D9E3;
            color: black;
            border-top: 1px solid #6E81A6;
        }
        .attribution td {
            text-align: center;
            padding: 2px;
        }
        .attribution tr.fields:hover td, .attribution tr.fields:hover th {
            background-color: #6E81A6;
            color: white;
        }
        .attribution tr.fields td, .attribution tr.fields th {
            border-bottom: 1px solid #6E81A6;
        }
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

        <c:forEach var="offset" begin="0" end="${fn:length(command.aeReport.adverseEvents) - 1}" step="${MAX_COLS}">
            <ae:attributionTable adverseEvents="${command.aeReport.adverseEvents}" blocks="${blocks}"
                                 maxAEs="${MAX_COLS}" offset="${offset}"/>
        </c:forEach>
    </chrome:division>
</form:form>
</body>
</html>