<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
 <head>
 	<title>Create IND#</title>
 	<tags:dwrJavascriptLink objects="createIND"/>
	<script>
		 Event.observe(window, "load", function() {
		 	
		 	Event.observe('holderType',"change", function(event){
   	    		 //clear the sponsor. 
   	    		 $('strSponsorId').value = '';
   	    		 $('strSponsorId-input').value = '';
	 		})
		 	
		 	AE.createStandardAutocompleter('strSponsorId', function(autocompleter, text){
		 			holderType = $F('holderType')
		 			 if('org' == holderType){
		 			    //call the method to feth organization
		 			    createIND.matchOrganization(text, function(values) {
         					autocompleter.setChoices(values)
         				})
		 			 }else if('inv' == holderType){
		 			 	//call the method to fetch investigators
		 			 	createIND.matchInvestigators(text, function(values) {
         					autocompleter.setChoices(values)
         				})
		 			 }else {
		 			 	alert("Please select appropriate value in 'IND Held by?'");
		 			 }
		 		}, function(obj){
		 		     return obj.fullName;
		 	});
		 });	
	</script>
 </head>
 <body>
 <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug:READ" var="_hasRead"/>

 <div class="workflow-tabs2">
     <ul id="" class="tabs autoclear">
         <c:if test="${_hasRead}"><li id="thirdlevelnav" class="tab selected"><div><a href="#">Create IND#</a></div></li></c:if>
         <li id="thirdlevelnav" class="tab"><div><a href="searchIND">Search IND#</a></div></li>
     </ul>
 </div>

 <div class="content">
	<form:form>
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
        <chrome:box title="Investigational New Drug Details">
		<chrome:division id="single-fields">
             <tags:instructions code="inddetails" />
			<tags:hasErrorsMessage hideErrorDetails="true"/>
            <tags:jsErrorsMessage/>
			<!--  JSP body here -->
			<div id="indfields">
			 <c:forEach items="${fieldGroups.main.fields}" var="field">
                    <tags:renderRow field="${field}"/>
             </c:forEach>
			</div>
		</chrome:division>
        </chrome:box>
        <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug:UPDATE || gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug:READ">
         <div class="content buttons autoclear">
          <div class="flow-buttons">
           <span class="next">
              <!--  reset and save buttons -->
               <tags:button type="submit" value="Save" color="green" icon="save" />
           </span>
          </div>
        </div>
        </csmauthz:accesscontrol>

	</form:form>

  </div>

 </body>
</html>
