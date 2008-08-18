<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="blue" tagdir="/WEB-INF/tags/chrome" %>
<html>
  <head>
 	<tags:dwrJavascriptLink objects="createAE"/>
	<script type="text/javascript">
		var mHistory = null;
 		var mHistoryClass = Class.create();
 		Object.extend(mHistoryClass.prototype, {
 	 		initialize: function(){ 
 	 		},
 	 		addDetails : function(src, val, loc){
 	 	 		src.disable();
 	 	 		alert('ajax ' + val + ' : ' + loc);
 	 	 		src.enable();
 	 		},
 	 		removeDetails :function(){
 	 		}
	 		
 		});

 		Event.observe(window, "load",setupPage);
		
		function setupPage(){
			mHistory = new mHistoryClass();//create a new mHistory object
			Element.observe('metastatic-diseases-btn', 'click', function(e){
				//call the ajax function
				var inField = $('metastaticDiseaseSite');
			 	this.addDetails(e.element(), inField.value, 'anchorMetastaticDiseases');
			 	
			 	//clear the fields
				inField.clear();
				$('metastaticDiseaseSite-input').value('Begin typing here...');
							 	
		 	}.bind(mHistory));
		}
		
	</script>
  </head>
  <body>
   <form:form id="medicalHistoryForm">	
	<blue:box id="assignment.general" title="General" collapsable="true">
	</blue:box>
	<blue:box id="assignment.diseaseHistory" title="Disease Information" collapsable="true">
	</blue:box>
	<blue:box id="assignment.diseaseHistory.metastaticDiseaseSites" title="Metastatic Disease Site" collapsable="true">
		<table class="tablecontent">
			<tr>
				<td width="90%">
					<ui:autocompleter path="metastaticDiseaseSite" initialDisplayValue="Begin typing here..." enableClearButton="true">
						<jsp:attribute name="populatorJS">
							function(autocompleter, text) {
                				createAE.matchAnatomicSite(text, function(values) {
                    				autocompleter.setChoices(values)
                				})
							}	
						</jsp:attribute>
						<jsp:attribute name="selectorJS">
							function (obj) {   
								return obj.name;  
							}
						</jsp:attribute>
					</ui:autocompleter>
				</td>
				<td><input id="metastatic-diseases-btn" type="button" value="Add"/>
			</tr>
			<tr>
				<td colspan="2">
					<span id="anchorMetastaticDiseases" />
				</td>
			</tr>
		</table>
	</blue:box>
	<blue:box id="assignment.preExistingConditions" title="Pre-existing Conditions" collapsable="true">
	</blue:box>
	<blue:box id="assignment.concomitantMedications" title="ConMeds" collapsable="true">
	</blue:box>
	<blue:box id="assignment.priorTherapies" title="Prior Therapies" collapsable="true">
	</blue:box>
   </form:form>
  </body>
</html>