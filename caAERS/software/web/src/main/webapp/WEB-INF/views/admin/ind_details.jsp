<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
 <head>
 	<title>Create IND#</title>
 	 <style type="text/css">
        div.content {
            padding: 5px 15px;
        }
    </style>
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
 <div class="tabpane">
     <div class="workflow-tabs2">
  <ul id="" class="tabs autoclear">
    <li id="thirdlevelnav" class="tab selected"><div>
        <a href="#">Create IND#</a>
    </div></li>
    <li id="thirdlevelnav" class="tab"><div>
        <a href="searchIND">Search IND#</a>
    </div></li>
  </ul>
         </div>
  <br />
  <div class="content">
	<form:form>
        <chrome:box title="Investigational New Drug Details">
		<chrome:division id="single-fields">
			<p>
             <tags:instructions code="inddetails" />
            </p>
			<tags:hasErrorsMessage hideErrorDetails="true"/>
			<!--  JSP body here -->
			<div id="indfields">
			 <c:forEach items="${fieldGroups.main.fields}" var="field">
                    <tags:renderRow field="${field}"/>
             </c:forEach>
			</div>
		</chrome:division>
    </chrome:box>
        <div class="content buttons autoclear">
          <div class="flow-buttons">
           <span class="next">
              <!--  reset and save buttons -->
               <tags:button type="submit" value="Save" color="green" icon="save" />&nbsp;&nbsp;&nbsp;
               <tags:button type="reset" value="Reset" color="blue" icon="x" />
           </span>
          </div>
        </div>
	</form:form>

  </div>
  
  </div>
 </body>
</html>
