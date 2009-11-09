<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Enter basic AE information</title>
    <style type="text/css">
        /* This is intended to apply to the grade longselect only */
        .longselect {
            width: 40em;
            white-space: nowrap;
        }
        .longselect label {
            padding-left: 3.0em;
            text-indent: -2.5em;
        }
    </style>
    <tags:dwrJavascriptLink objects="createAE"/>
	<%--  <tags:slider renderComments="${command.associatedToWorkflow }" renderAlerts="${command.associatedToLabAlerts}" 
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
    </tags:slider> --%>
    
     <script type="text/javascript">
     	var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
		var aesEditor;
        var grades = ['NORMAL','MILD', 'MODERATE', 'SEVERE', 'LIFE_THREATENING', 'DEATH'];

        Element.observe(window, "load", function() {
            
            aesEditor = new ListEditor("ae-section", createAE, "AdverseEventMeddra", {
                addParameters: [aeReportId],
                reorderable: false,
                deletable: false,
                minimizeable: false,
                addCallback: function(nextIndex) {
                },
                reorderCallback : function(original, target){
                    $$('span.primary-indicator').each(function(el, indx){
                        if(indx == 0) el.innerHTML='[Primary]';
                        else el.innerHTML = '';
                    });
                 }
             }, "aeReport.adverseEvents")    
			
			 //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
        }) 
      //==================================================================================================
       function addAdverseEvents(selectedTerms){
            var termId = selectedTerms.keys()[0];

           var tID = lookupByTermId(termId);
           if (tID != -1) {
               openDivisionById(tID);
               document.location = document.location.toString().split("#")[0] + "#adverseEventTerm-" + termId;
               return;
           }
           
           // var newIndex = $$(".ae-section").length;
			var externalFunction = createAE['addAdverseEventWithTermsMeddra'];
            var externalArgs = [termId];
			aesEditor.add('', externalFunction,externalArgs);
       }
     //==================================================================================================

         // ----------------------------------------------------------------------------------------------------------------

          function lookupByTermId(_id) {
             var list = $$('div.aeID-' + _id);
             for (i=0; i<list.length; i++) {
                 return (list[i].id);
             }
             return -1;
          }

         // ----------------------------------------------------------------------------------------------------------------

          function closeDivisionById(_id) {
                  panelDiv = $("contentOf-" + _id);
                  imageId= 'image-' + _id;
                  imageSource = $(imageId).src;
                  CloseDown(panelDiv, arguments[1] || {});
                  document.getElementById(imageId).src = imageSource.replace('down','right');
          }

          // ----------------------------------------------------------------------------------------------------------------

          function openDivisionById(_id) {
                  panelDiv = $("contentOf-" + _id);
                  imageId= 'image-' + _id;
                  imageSource = $(imageId).src;
                  OpenUp(panelDiv, arguments[1] || {});
                  document.getElementById(imageId).src = imageSource.replace('right','down');
          }
         
    </script>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="ae_captureRoutine">
        <jsp:attribute name="instructions">
           <tags:instructions code="instruction_ae_enterBasics" />
        </jsp:attribute>
        <jsp:attribute name="repeatingFields">
        <%--
        <tags:aeTermQuery isMeddra="true"
  						hideAddMultiple="true"
                       	noBackground="true"
                       	callbackFunctionName="addAdverseEvents"
                       	ignoreOtherSpecify="false"
                       	isAjaxable="true"
                       	version="${command.assignment.studySite.study.aeTerminology.meddraVersion.id}"
                       	title="Select New Adverse Event Terms">
    	</tags:aeTermQuery>
    	--%>
    	 <div style="margin-left:20px; margin-bottom:10px;">
    		<tags:button color="blue" size="small" value="Add Adverse Event" icon="+"  markupWithTag="a"
    			href="captureRoutine?study=${command.study.id}&participant=${command.participant.id}&adverseEventReportingPeriod=${command.aeReport.reportingPeriod.id}&_page=0&_target1=1&displayReportingPeriod=true&addReportingPeriodBinder=true" />
   		</div>
            <c:forEach items="${command.aeReport.adverseEvents}" varStatus="status" var="ae">
                <ae:oneAdverseEventMeddra index="${status.index}" collapsed="${status.index gt 0}" adverseEvent="${ae}"/>
            </c:forEach>
			<ae:reportingContext allReportDefinitions="${command.applicableReportDefinitions}" selectedReportDefinitions="${command.selectedReportDefinitions}" />
        </jsp:attribute>

    </tags:tabForm>
</body>
</html>
