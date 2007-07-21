<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var NAME_FIELDS = [
            'firstName', 'middleName', 'lastName'
        ]
        var PERSON_FIELDS = NAME_FIELDS.concat([
            'contactMechanisms[e-mail]', 'contactMechanisms[phone]', 'contactMechanisms[fax]'
        ])

        function chooseStaff() {
            var id = document.getElementById("staff").value;
            if (id == -1) {
                clear();
            } else {
                createAE.getResearchStaff(id, updateReporterFromStaff)
            }
        }

        function updateReporterFromStaff(staff) {
            NAME_FIELDS.each(function(field) {
                $('aeReport.reporter.' + field).value = staff[field]
            })

            updatePhysicianFromReporterIfSame()
        }

        function clear(person) {
            PERSON_FIELDS.each(function (field) {
                $('aeReport.' + person + '.' + field).value = ''
            })
        }

        function isPhysicianSame() {
            return $('physician-same').checked
        }

        function updatePhysicianSame() {
            if (isPhysicianSame()) {
                updatePhysicianFromReporter()
            } else {
                clear('physician')
            }
        }

        function updatePhysicianFromReporterIfSame() {
            if (isPhysicianSame()) updatePhysicianFromReporter();
        }

        function updatePhysicianFromReporter() {
            PERSON_FIELDS.each(function(field) {
                $('aeReport.physician.' + field).value = $('aeReport.reporter.' + field).value
            })
        }

        Event.observe(window, "load", function() {
            $('staff').observe("change", chooseStaff)
            $('physician-same').observe("click", updatePhysicianSame)
        })

    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11reporterinformation">
    <jsp:attribute name="instructions">
        <c:choose>
            <c:when test="${oneOrMoreSevere || aeReport.isExpeditedReportingRequired}">
                <p>
                    One or more of the adverse events you entered <strong>requires expedited
                    reporting</strong> under the rules in efect for this study and participant.
                    Please enter your contact information and that for the treating physician.
                    Then proceed to the next page for details about the required expedited
                    report(s).
                </p>
            </c:when>
            <c:otherwise>
                <p>
                    None of the adverse events you entered requires expedited reporting under the
                    rules in effect for this study and participant.  If you would like to prepare
                    and submit one or more expedited reports anyway, please start by entering your
                    contact information and that for the treating physician.
                </p>
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
        <chrome:division title="Reporter Details">
            <div class="row">
                <div class="label">Research staff</div>
                <div class="value">
                    <select id="staff" name="staff">
                        <option value=-1>please select--</option>
                        <c:forEach var="person" items="${command.assignment.studySite.organization.researchStaffs}">
                            <option value="${person.id}">
                                ${person.firstName} ${person.lastName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <c:forEach items="${fieldGroups['reporter'].fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </chrome:division>

        <chrome:division title="Physician details">
            <div class="row">
                <div class="value">
                    <label>
                        <input type="checkbox" id="physician-same" name="physician-same" />&nbsp;Physician
                        is same as reporter
                    </label>
                </div>
            </div>

            <c:forEach items="${fieldGroups['physician'].fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </chrome:division>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
