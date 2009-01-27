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
    <tags:dwrJavascriptLink objects="createAE,routingAndReview"/>
    <link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <tags:slider renderComments="${command.workflowEnabled}" renderAlerts="false" display="">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments domainObjectType="aeReport"/>
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>
    
    <script type="text/javascript">
        var NAME_FIELDS = [
            'firstName', 'middleName', 'lastName','title', 'address.street', 'address.city', 'address.state', 'address.zip'
        ]
        var PERSON_FIELDS = NAME_FIELDS.concat([
            'contactMechanisms[e-mail]', 'contactMechanisms[phone]', 'contactMechanisms[fax]'
        ])

        function chooseStaff() {
            var id = document.getElementById("staff").value;
            if (id == '') {
                clear('reporter');
               
            } else {
                createAE.getResearchStaff(id, updateReporterFromStaff)
            }
        }

        function choosePhysician(){
			var id = document.getElementById("physician").value;
			if (id == '') {
                clear('physician');
               
            } else {
                createAE.getInvestigator(id, updatePhysicianFromInvestigator)
            }
        }
		/* IE7 fix:- null text is displayed when staff[field] is empty or null*/
        function updateReporterFromStaff(staff) {
			updateUserData('aeReport.reporter', staff);			
        }
        
        function updatePhysicianFromInvestigator(investigator) {
            updateUserData('aeReport.physician', investigator);
        }
        
        function updateUserData(prefix, user) {
            NAME_FIELDS.each(function(field) {
            	if(user[field] != null) updateFieldValue(prefix + '.' + field, user[field]);
            })
			if(user['emailAddress'] != null) updateFieldValue(prefix + '.' + 'contactMechanisms[e-mail]',user['emailAddress']);
			if(user['phoneNumber'] != null) updateFieldValue(prefix + '.' + 'contactMechanisms[phone]',user['phoneNumber']);
			if(user['faxNumber'] != null) updateFieldValue(prefix + '.' + 'contactMechanisms[fax]',user['faxNumber']);
			
        }

        function clear(person) {
            PERSON_FIELDS.each(function (field) {
            	updateFieldValue('aeReport.' + person + '.' + field, '');
            })
        }



        function updateFieldValue(uiField, value){
			var f = $(uiField);
			if(f){
				f.value = value;
			}
        }

        Event.observe(window, "load", function() {
            $('staff').observe("change", chooseStaff)
            $('physician').observe("change", choosePhysician)
            
            createAE.retrieveReportReviewComments(
					function(ajaxOutput){
						document.getElementById('scrollbar_content').innerHTML = "";
						document.getElementById('scrollbar_content').innerHTML = ajaxOutput;
						document.getElementById('enter-comment-text').value = "";
					}) ;
        })

    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section3reporter">
    <jsp:attribute name="instructions">
        <c:choose>
            <c:when test="${oneOrMoreSevere}">
               <tags:instructions  code="instruction_ae_reporterAE"/>
            </c:when>
            <c:otherwise>
            	<tags:instructions code="instruction_ae_reporterNoAE" />
                <tags:instructions code="instruction_ae_reporterNote" heading="Note: " />
            </c:otherwise>
        </c:choose>
    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
        <chrome:division title="Reporter Details">
            <div class="row">
                <div class="label">Research staff</div>
                <div class="value">
                    <select id="staff" name="aeReport.reporter.user">
                        <option value="">please select--</option>
                        <c:forEach var="person" items="${command.assignment.studySite.organization.researchStaffs}">
                            <option value="${person.id}">${person.firstName} ${person.lastName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <c:forEach items="${fieldGroups['reporter'].fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </chrome:division>

        <chrome:division title="Treating Physician Details">
        <div class="row">
                <div class="label">Physician</div>
                <div class="value">
                    <select id="physician" name="aeReport.physician.user">
                        <option value="">please select--</option>
                        <c:forEach var="siteInv" items="${command.assignment.studySite.organization.siteInvestigators}">
                            <option value="${siteInv.investigator.id}">${siteInv.investigator.firstName} ${siteInv.investigator.lastName}</option>
                        </c:forEach>
                    </select>
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
