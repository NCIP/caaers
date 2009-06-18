<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE,routingAndReview"/>
	<tags:slider renderComments="${command.associatedToWorkflow }" renderAlerts="${command.associatedToLabAlerts}" 
		display="${(command.associatedToWorkflow or command.associatedToLabAlerts) ? '' : 'none'}">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments />
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>
    
    
    <script type="text/javascript">
    	var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');
    
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
            	if(user[field] != null) {
                    updateFieldValue(prefix + '.' + field, user[field]);
                    ValidationManager.setNormalState($(prefix + '.' + field));
                }
            })
            
            if(user['emailAddress'] != null) {
                updateFieldValue(prefix + '.' + 'contactMechanisms[e-mail]',user['emailAddress']);
                ValidationManager.setNormalState($(prefix + '.contactMechanisms[e-mail]'));
            }


            if(user['phoneNumber'] != null) {
                updateFieldValue(prefix + '.' + 'contactMechanisms[phone]',user['phoneNumber']);
                ValidationManager.setNormalState($(prefix + '.contactMechanisms[phone]'));
            }


            if(user['faxNumber'] != null) {
                updateFieldValue(prefix + '.' + 'contactMechanisms[fax]',user['faxNumber']);
                ValidationManager.setNormalState($(prefix + '.contactMechanisms[fax]'));
            }

        }

        function clear(person) {
            PERSON_FIELDS.each(function (field) {
            	updateFieldValue('aeReport.' + person + '.' + field, '');
                ValidationManager.doFieldValidation($('aeReport.' + person + '.' + field));
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
            
            //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
        })

    </script>
	<!--[if lte IE 6]>
<style>
		#reporter-summary {
			margin-top:80px;
			margin-bottom:0;
		}
	</style>
<![endif]-->
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
        <chrome:division title="Reporter Details" id="reporter">
        		<!--[if lte IE 6]>
					<br/>
					<br/>
					<br/>
				<![endif]-->
            <div class="row">
                <div class="label">Reporter</div>
                <div class="value">
                    <select id="staff" name="aeReport.reporter.user">
                        <option value="">please select--</option>
                        <optgroup label="Reporter">
                        <c:forEach var="person" items="${command.assignment.studySite.organization.researchStaffs}">
                        	<c:if test="person.active">
                            	<option value="${person.id}" ${person.id eq command.aeReport.reporter.user.id ? 'SELECTED' : '' }>${person.firstName} ${person.lastName}</option>
                            </c:if>	
                        </c:forEach>
                        </optgroup>
                        <optgroup label="Investigators">
                        <c:forEach var="siteInv" items="${command.assignment.studySite.organization.siteInvestigators}">
                            <option value="${siteInv.investigator.id}" ${siteInv.investigator.id eq command.aeReport.reporter.user.id ? 'SELECTED' : '' }>${siteInv.investigator.firstName} ${siteInv.investigator.lastName}</option>
                        </c:forEach>
                        </optgroup>
                    </select>
                </div>
            </div>

            <c:forEach items="${fieldGroups['reporter'].fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </chrome:division>

        <chrome:division title="Treating Physician Details" id="treating">
        	<!--[if lte IE 6]>
					<br/>
					<br/>
					<br/>
				<![endif]-->
        <div class="row">
                <div class="label">Physician</div>
                <div class="value">
                    <select id="physician" name="aeReport.physician.user">
                        <option value="">please select--</option>
                        <c:forEach var="siteInv" items="${command.assignment.studySite.organization.siteInvestigators}">
                            <option value="${siteInv.investigator.id}" ${siteInv.investigator.id eq command.aeReport.physician.user.id ? 'SELECTED' : '' }>${siteInv.investigator.firstName} ${siteInv.investigator.lastName}</option>
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
