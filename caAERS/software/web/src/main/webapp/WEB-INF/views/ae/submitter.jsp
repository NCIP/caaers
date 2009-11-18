<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
    	var reportIndex = ${empty command.reportIndex ? 'null' : command.reportIndex}
        var NAME_FIELDS = [
            'firstName', 'middleName', 'lastName'
        ]
        var PERSON_FIELDS = NAME_FIELDS.concat([
            'contactMechanisms[e-mail]', 'contactMechanisms[phone]', 'contactMechanisms[fax]'
        ])

      

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

        function isSubmitterSameAsReporter() {
            return $('reporter_same_as_submitter').checked
        }

		function isSubmitterSameAsPhysician() {
            return $('physician_same_as_submitter').checked
        }

		
        function updateSubmitterWithReporter() {
            if (isSubmitterSameAsReporter()) {
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
            if (isSubmitterSameAsReporter()) updateSubmitterFromReporter();
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
            $('reporter_same_as_submitter').observe("click", updateSubmitterWithReporter)
            $('physician_same_as_submitter').observe("click", updateSubmitterWithPhysician)

            //default to reporter, if entering first time. 
            var fNField = $("aeReport.reports[" + reportIndex + "].lastVersion.submitter.firstName");
            if(fNField){
                if(fNField.value == ''){
                	$('reporter_same_as_submitter').click();
                }
            }
        })

    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11reporterinformation">
    <jsp:attribute name="instructions">
    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
    	
    <div class="row"> 
    <c:forEach begin="0" end="1" varStatus="status">
        <c:if test="${status.first}">
            <c:set var="reporter" value="${command.aeReport.reporter}"/>
			<c:set var="reporterType" value="Reporter"/>
			<c:set var="side" value="leftpanel"/>
        </c:if>
        <c:if test="${status.last}">
            <c:set var="reporter" value="${command.aeReport.physician}"/>
			<c:set var="reporterType" value="Physician"/>
			<c:set var="side" value="rightpanel"/>
        </c:if>
		<div class="${side}">
        <chrome:division title="${reporterType}">
            <div class="row">
                <div class="label">
                    Name
                </div>
                <div class="value">
                    <span id="aeReport.${reporterType}.firstName"><c:out value="${reporter.firstName}"/></span>
					<span id="aeReport.${reporterType}.middleName"><c:out value="${reporter.middleName}"/></span>
					<span id="aeReport.${reporterType}.lastName"><c:out value="${reporter.lastName}"/></span>
                </div>
            </div>

			<div class="row">
                <div class="label">
                    E-mail
                </div>
                <div class="value">
					<span id="aeReport.${reporterType}.contactMechanisms[e-mail]"><c:out value="${reporter.contactMechanisms['e-mail']}"/></span>
                </div>
            </div>
			<div class="row">
                <div class="label">
                    Phone
                </div>
                <div class="value">
					<span id="aeReport.${reporterType}.contactMechanisms[phone]"><c:out value="${reporter.contactMechanisms['phone']}"/></span>
                </div>
            </div>
			<div class="row">
                <div class="label">
                    Fax
                </div>
                <div class="value">
					<span id="aeReport.${reporterType}.contactMechanisms[fax]"><c:out value="${reporter.contactMechanisms['fax']}"/></span>
                </div>
            </div>
        </chrome:division>
		</div>
    </c:forEach>
</div>

        <chrome:division title="Submitter details">
            <div class="row">
                <div class="value">
                    <label>
                        <input type="checkbox" id="reporter_same_as_submitter" name="reporter_same_as_submitter" />&nbsp;
                        	If the submitter is the same as the reporter
                    </label>
                </div>
                <div class="value">
                    <label>
                        <input type="checkbox" id="physician_same_as_submitter" name="physician_same_as_submitter" />&nbsp;
                        	If the submitter is the same as the physician
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
