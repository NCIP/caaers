<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.extremecomponents.org" prefix="ec" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
<head>

    <link rel="stylesheet" type="text/css"
          href="<c:url value="/css/extremecomponents.css"/>">

    <script type="text/javascript">

        Spring.addDecoration(new Spring.ElementDecoration({
            elementId : "participantDetails",
            widgetType : "dijit.TitlePane"  ,widgetAttrs : {
                title : "Participant Details "
            }


        }));

        Spring.addDecoration(new Spring.ElementDecoration({
            elementId : "studyDetails",
            widgetType : "dijit.TitlePane",
            widgetAttrs : {
                title : "Study Details "
            }


        }));
        Spring.addDecoration(new Spring.ElementDecoration({
            elementId : "medicalHistory",
            widgetType : "dijit.TitlePane",
            widgetAttrs : {
                title : "Medical History"
            }


        }));
    </script>

</head>
<body class="tundra">
<!-- TOP LOGOS END HERE -->
<!-- TOP NAVIGATION STARTS HERE -->
<chrome:box autopad="true" title="Assign Participant on a Study">
<div id="studyDetails" width="">

    <c:if test="${studySite ne null }">
        <c:if test="${studySite.id ne null }">

            <table class="tablecontent">
                <tr>
                    <th scope="col">Study Short Title</th>
                    <th scope="col">Site</th>
                </tr>
                <tr class="results">
                    <td>${studySite.study.shortTitle}</td>
                    <td>${studySite.organization.name}</td>
                </tr>
            </table>
            <br>

        </c:if>

    </c:if>
    <a id="chooseStudy" href="${flowExecutionUrl}&_eventId=chooseStudy">
        Choose Study</a>
    <script type="text/javascript">
        Spring.addDecoration(new Spring.AjaxEventDecoration({
            elementId: "chooseStudy",
            event: "onclick" ,
            params: {
                subview : "test1"}

        }));
    </script>
</div>


<%--<a href="${flowExecutionUrl}&_eventId=chooseStudy">Choose Study</a>--%>
<%--</div>--%>

<div id="participantDetails" width="">

    <c:if test="${participant ne null}">

        <table id="test2" class="single-fields">
            <tr>
                <td>
                    <div class="leftpane">
                        <div class="row">
                            <div class="label">First name:</div>
                            <div class="value">${participant.firstName}</div>
                        </div>
                        <div class="row">
                            <div class="label">Last name:</div>
                            <div class="value">${participant.lastName}</div>
                        </div>

                        <div class="row">
                            <div class="label">Maiden name:</div>
                            <div class="value">${participant.maidenName}</div>
                        </div>

                        <div class="row">
                            <div class="label">Middle name:</div>
                            <div class="value">${participant.middleName}</div>
                        </div>
                    </div>
                </td>

                <td>
                    <div class="row">
                        <div class="label">Date of birth:</div>
                        <div class="value">${participant.dateOfBirth}</div>
                    </div>

                    <div class="row">
                        <div class="label">Ethnicity:</div>
                        <div class="value">${participant.ethnicity}</div>
                    </div>
                    <div class="row">
                        <div class="label">Race:</div>
                        <div class="value">${participant.race}</div>
                    </div>
                    <div class="row">
                        <div class="label">Gender:</div>
                        <div class="value">${participant.gender}</div>
                    </div>
                </td>
            </tr>
        </table>


    </c:if>

    <a href="${flowExecutionUrl}&_eventId=chooseParticipant">Choose Participant</a>

</div>
<div id="medicalHistory" width="">

        <%--<c:if test="${participant ne null}">--%>

    <!--<table id="test2" class="single-fields">-->
    <!--<tr>-->
    <!--<td>-->
    <!--<div class="leftpane">-->
    <!--<div class="row">-->
    <!--<div class="label">First name:</div>-->
    <!--<div class="value">${participant.firstName}</div>-->
    <!--</div>-->
    <!--<div class="row">-->
    <!--<div class="label">Last name:</div>-->
    <!--<div class="value">${participant.lastName}</div>-->
    <!--</div>-->

    <!--<div class="row">-->
    <!--<div class="label">Maiden name:</div>-->
    <!--<div class="value">${participant.maidenName}</div>-->
    <!--</div>-->

    <!--<div class="row">-->
    <!--<div class="label">Middle name:</div>-->
    <!--<div class="value">${participant.middleName}</div>-->
    <!--</div>-->
    <!--</div>-->
    <!--</td>-->

    <!--<td>-->
    <!--<div class="row">-->
    <!--<div class="label">Date of birth:</div>-->
    <!--<div class="value">${participant.dateOfBirth}</div>-->
    <!--</div>-->

    <!--<div class="row">-->
    <!--<div class="label">Ethnicity:</div>-->
    <!--<div class="value">${participant.ethnicity}</div>-->
    <!--</div>-->
    <!--<div class="row">-->
    <!--<div class="label">Race:</div>-->
    <!--<div class="value">${participant.race}</div>-->
    <!--</div>-->
    <!--<div class="row">-->
    <!--<div class="label">Gender:</div>-->
    <!--<div class="value">${participant.gender}</div>-->
    <!--</div>-->
    <!--</td>-->
    <!--</tr>-->
    <!--</table>-->


        <%--</c:if>--%>

    <a href="${flowExecutionUrl}&_eventId=medicalHistory">Add/Edit Medical History</a>

</div>

</chrome:box>
</div>
</body>
</html>