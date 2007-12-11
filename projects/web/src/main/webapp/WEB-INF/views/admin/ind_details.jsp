<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
 <head>
 	
 	<tags:stylesheetLink name="tabbedflow"/>
 	 <style type="text/css">
        div.content {
            padding: 5px 15px;
        }
    </style>
 	<tags:includeScriptaculous/>
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
		 			 }
		 		}, function(obj){
		 		     return obj.fullName;
		 	});
		 });	
	</script>
 </head>
 <body>
 <div class="tabpane">
  <ul id="workflow-tabs" class="tabs autoclear">
    <li class="tab selected"><div>
        <a href="#">Create IND#</a>
    </div></li>
    <li class="tab"><div>
        <a href="searchIND">Search IND#</a>
    </div></li>
  </ul>
  <br />
  <div class="content">
    <chrome:box title="Investigational New Drug Details">
	<form:form>
		<chrome:division id="single-fields">
			<div class="instructions">You can add the details of an Investigational New Drug(IND)here.
			<br /><br />
			</div>
			<tags:hasErrorsMessage hideErrorDetails="true"/>
			<!--  JSP body here -->
			<div id="indfields">
			 <c:forEach items="${fieldGroups.main.fields}" var="field">
                    <tags:renderRow field="${field}"/>
             </c:forEach>
			</div>
			<div class="content buttons autoclear">
			  <div class="flow-buttons">
			   <span class="next">
			  	<!--  reset and save buttons -->
			  	<input type="reset" value="Reset" />
			  	<input type="submit" value="Save" />
			   </span>	
			  </div>
			</div>
		</chrome:division>
	</form:form>
  </chrome:box>
  
  </div>
  
  </div>
 </body>
</html>