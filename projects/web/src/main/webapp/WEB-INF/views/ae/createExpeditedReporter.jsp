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
    <tags:javascriptLink name="routing_and_review" />
	<tags:stylesheetLink name="slider" />
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
    	var routingHelper = new RoutingAndReviewHelper(createAE);
    
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
            
            //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }


    		if($('manualselect2')){
    			 
    		
          		 Event.observe('manualselect2', "click", function() {
          	 		var answer = confirm('Are you sure you want to bypass the caAERS-based report selection above and instead manually select from the list of all reports defined for this study?');
          	 	 	if(answer){
          	 	 		$('manualselect2').disabled=true
          	 	  	 	$('report-list').hide();
          		   		$('report-list').innerHTML = $('report-list-full').innerHTML;
          		   		$('report-list-full').innerHTML='';
     			   		AE.slideAndShow($('report-list'));  
     			  		// setUpEventObserving();	
          	 	 	}	
          	 	 });
    		}
               
        })
        
        

    </script>
</head>
<body>
<div id="report-list-full" style="display:none; padding-bottom:5px;" align="center">
  <tags:noform>
    <table class="tablecontent">
      <tr>
        <th>Required</th>
        <th>Report</th>
        <th>Status</th>
      </tr>
      <c:forEach items="${rpdAllTable}"  var="rdTable" varStatus="rdStatus">
        <tr>
          <td align="center">${rdTable.value.required ? 'Yes' : 'No' }</td>
          <td align="left">
          <ui:checkbox path="${rdTable.value.field.propertyName}" cssClass="rpdChk" onclick="javascript:enableReportsInPopup();"></ui:checkbox>
            <tags:renderLabel field="${rdTable.value.field}"/></td>
          <td>${rdTable.value.status}</td>
        </tr>
      </c:forEach>
    </table>
  </tags:noform>
</div>
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
    
        <%--  =================== Rules Engine Results  ===================================== --%>
  
      <c:choose>
        <c:when test="${not empty rpdSelectedTable}">
         
        <chrome:box id="box-report-by-caaers" title="Reports Identified by caAERS" collapsable="true" autopad="true">
    <div style="border:1px solid #f00; height:100px; padding:9px; margin-bottom:10px;">
		<img src="<chrome:imageUrl name="stop_sign.png" />" alt="Stop!" style="float:left; margin-right:30px; margin-left:80px;" />
		<div style="font-size:20px; margin-bottom:5px;">Report Required!</div>
		<div><tags:instructions code="instruction_ae_require_reporting" /></div>
	</div>
           <div align="center">
            <div id="report-list" align="center" style="padding-bottom:5px;">
              <!-- required reports -->
              <table class="tablecontent">
                <tr>
                  <th>Required</th>
                  <th>Report</th>
                  <th>Status</th>
                </tr>
                <c:forEach items="${rpdSelectedTable}"  var="rdTable" varStatus="rdStatus">
                  <tr>
                    <td align="center"> ${rdTable.value.required ? 'Yes' : 'No' }</td>
                    <td align="left"><tags:renderInputs field="${rdTable.value.field}" cssClass="rpdChk"/>
                      <tags:renderLabel field="${rdTable.value.field}"/></td>
                    <td>${rdTable.value.status}</td>
                  </tr>
                </c:forEach>
              </table>
            </div>
          </div>
          <c:if test='${displaySeriousTable || displayObservedTable || displaySolicitedTable}'>
              <%--<tags:instructions code="instruction_ae_require_reporting" />--%>
              <p>Click <a id="manualselect2" style='cursor:pointer' class="link">here</a> to manually select from the list of all reports available for this study.</p>
      	  </c:if>
        </chrome:box>
        </c:when>
        <c:otherwise>
         <chrome:box id="box-report-by-caaers" title="Reports Identified by caAERS" collapsable="true" autopad="true">

             <tags:instructions code="instruction_ae_not_require_reporting" />
             
          <div align="center" style="padding-bottom:5px;" id="report-list">
            <!-- optional reports -->
            <table class="tablecontent" width="80%">
              <tr>
                <th>Required</th>
                <th>Report</th>
                <th>Status</th>
              </tr>
              <tr>
                <td align="left" colspan="3">No reports required.</td>
              </tr>
            </table>
          </div>
          <c:if test='${displaySeriousTable || displayObservedTable || displaySolicitedTable}'>
      	  </c:if>
             <%--<tags:instructions code="instruction_ae_require_reporting" />--%>
             <p>Click <a id="manualselect2" style='cursor:pointer' class="link">here</a> to manually select from the list of all reports available for this study.</p>
            </chrome:box>
        </c:otherwise>
      </c:choose>
       
        
        <%-- ================================================================================  --%>
        <chrome:box title="Reporter Details">
            <div class="row">
                <div class="label">Reporter</div>
                <div class="value">
                    <select id="staff" name="aeReport.reporter.user">
                        <option value="">please select--</option>
                        <optgroup label="Research staff">
                        <c:forEach var="person" items="${command.assignment.studySite.organization.researchStaffs}">
                            <option value="${person.id}" ${person.id eq command.aeReport.reporter.user.id ? 'SELECTED' : '' }>${person.firstName} ${person.lastName}</option>
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
        </chrome:box>

        <chrome:box title="Treating Physician Details">
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
        </chrome:box>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
