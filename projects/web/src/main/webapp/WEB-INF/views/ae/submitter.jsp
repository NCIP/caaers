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
    	var reportIndex = ${empty command.reportIndex ? 'null' : command.reportIndex}
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
                $("aeReport.reports[" + reportIndex + "]" + person + "." + field).value = ''
            })
        }

        function isPhysicianSame() {
            return $('reporter_same_as_submitter').checked
        }

		function isSubmitterSameAsPhysician() {
            return $('physician_same_as_submitter').checked
        }

		
        function updatePhysicianSame() {
            if (isPhysicianSame()) {
                updateSubmitterFromReporter()
                $('physician_same_as_submitter').checked=""
            } else {
                clear("submitter")
            }
        }
        
        function updateSubmitterWithPhysician() {
            if (isSubmitterSameAsPhysician()) {
                updateSubmitterFromPhysician()
                 $('reporter_same_as_submitter').checked=""
            } else {
                clear("submitter")
            }
        }

        function updatePhysicianFromReporterIfSame() {
            if (isPhysicianSame()) updateSubmitterFromReporter();
        }

        function updateSubmitterFromReporter() {
            PERSON_FIELDS.each(function(field) {
                $("aeReport.reports[" + reportIndex + "].lastVersion.submitter." + field).value = $('aeReport.Reporter.' + field).innerHTML
            })
        }
        
        function updateSubmitterFromPhysician() {
            PERSON_FIELDS.each(function(field) {
                $("aeReport.reports[" + reportIndex + "].lastVersion.submitter." + field).value = $('aeReport.Physician.' + field).innerHTML
            })
        }

        Event.observe(window, "load", function() {
            //$('staff').observe("change", chooseStaff)
            $('reporter_same_as_submitter').observe("click", updatePhysicianSame)
            $('physician_same_as_submitter').observe("click", updateSubmitterWithPhysician)
        })

    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11reporterinformation">
    <jsp:attribute name="instructions">
           		<p>
                    Complete the submitter information below.
                </p>

    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
    	
    	
    	<chrome:division title="Physician sign-off">
    		
    		<p>
    			
    			Certify the physician has signed-off on the report and then enter your information as the submitter.
    		
    		</p>

    		
    		<c:forEach items="${fieldGroups['physicianSignoff'].fields}" var="field">
                
    			<tags:renderRow field="${field}"/>
    		</c:forEach>
    		
    	
    	</chrome:division>
    	
        <chrome:division title="Reporter & Physician details">
            <c:forEach begin="0" end="1" varStatus="status">
            	<c:if test="${status.first}">
            		<c:set var="reporter" value="${command.aeReport.reporter}"/>
					<c:set var="reporterType" value="Reporter"/>
   				</c:if>
   				<c:if test="${status.last}">
            		<c:set var="reporter" value="${command.aeReport.physician}"/>
					<c:set var="reporterType" value="Physician"/>
   				</c:if>
            	<div class="row">
					<div class="label">${reporterType}</div>
			
					<div class="value">
            			<span id="aeReport.${reporterType}.firstName"><c:out value="${reporter.firstName}"/></span>
            			<span id="aeReport.${reporterType}.middleName"><c:out value="${reporter.middleName}"/></span>
            			<span id="aeReport.${reporterType}.lastName"><c:out value="${reporter.lastName}"/> </span>,
            			<span id="aeReport.${reporterType}.contactMechanisms[e-mail]"><c:out value="${reporter.contactMechanisms['e-mail']}"/></span>
            			<span id="aeReport.${reporterType}.contactMechanisms[phone]"><c:out value="${reporter.contactMechanisms['phone']}"/></span>
            			<span id="aeReport.${reporterType}.contactMechanisms[fax]"><c:out value="${reporter.contactMechanisms['fax']}"/></span>
            		</div>
            	</div>
            </c:forEach>	
        </chrome:division>

        <chrome:division title="Submitter details">
            <div class="row">
                <div class="value">
                    <label>
                        <input type="checkbox" id="reporter_same_as_submitter" name="reporter_same_as_submitter" />&nbsp;
                        	If the Submitter same as Reporter
                    </label>
                </div>
                <div class="value">
                    <label>
                        <input type="checkbox" id="physician_same_as_submitter" name="physician_same_as_submitter" />&nbsp;
                        	If the Submitter same as Physician
                    </label>
                </div>
            </div>

            <c:forEach items="${fieldGroups['submitter'].fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </chrome:division>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
