<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Routing & Review</title>
    <style type="text/css">
        input.autocomplete {
            width: 75%;
            font-style: normal;
            background-color: #CCE6FF;
        }

        input.pending-search {
            width: 75%;
            color: gray;
            font-style: italic;
            background-color: #CCE6FF;
        }
        
        #criteria-div{
          width: 70%;
          margin-left: 5em;
        }

    </style>
    <tags:dwrJavascriptLink objects="createAE,routingAndReview"/>
    
        </head>
<body>
<chrome:flashMessage/>
<c:if test="${!command.workflowEnabled}">
	<p>
    	<tags:message key="routing_and_review_disabled"/>
   	</p>
</c:if>
<c:if test="${command.workflowEnabled}">
	<tags:instructions code="instruction_selectRoutingAndReview"/>
	<form:form method="post" cssClass="standard autoclear">
        <tags:jsErrorsMessage/>
        <tags:hasErrorsMessage />
    	<tags:tabFields tab="${tab}"/>
			<div id="criteria-div">
				<chrome:box title="Search Criteria" id="search-criteria" autopad="true" >
				<ui:row path="Study">
					<jsp:attribute name="label">Study
					</jsp:attribute>
					<jsp:attribute name="value">
						<ui:autocompleter path="study"  initialDisplayValue="${empty command.study  ? 'Begin typing here...' : command.study.shortTitle}" enableClearButton="true" size="50">
								<jsp:attribute name="populatorJS">
									function(autocompleter, text) {
       	         					createAE.matchStudies(text, $('participant').value, ${command.ignoreCompletedStudy}, function(values) {
       	             					autocompleter.setChoices(values)
       	         					})
       	     					}
								</jsp:attribute>
								<jsp:attribute name="selectorJS">
									function(obj) {
       	        						 return obj.displayName;
       	     					}
								</jsp:attribute>
						</ui:autocompleter>	
					</jsp:attribute>
				</ui:row>
				<ui:row path="participant">
					<jsp:attribute name="label">Subject
					</jsp:attribute>
					<jsp:attribute name="value">
						<ui:autocompleter path="participant"  initialDisplayValue="${empty command.participant  ? 'Begin typing here...' : command.participant.fullName}" 
							enableClearButton="true" size="50">
							<jsp:attribute name="populatorJS">
								function(autocompleter, text) {
                					createAE.matchParticipants(text, $('study').value, function(values) {
                    					autocompleter.setChoices(values)
                					})
           						}
							</jsp:attribute>
							<jsp:attribute name="selectorJS">
								function(obj) {
                					return obj.displayName
            					}
							</jsp:attribute>
						</ui:autocompleter>
					</jsp:attribute>
				</ui:row>
				<ui:row path="Study site">
					<jsp:attribute name="label">Study site
					</jsp:attribute>
					<jsp:attribute name="value">
						<ui:autocompleter path="organization"  initialDisplayValue="${empty command.organization  ? 'Begin typing here...' : command.organization.fullName}" enableClearButton="true" size="50">
							<jsp:attribute name="populatorJS">
								function(autocompleter, text){
        							routingAndReview.matchSites(text, $('study').value, function(values) {
        								autocompleter.setChoices(values)
        							})
        						}
							</jsp:attribute>
							<jsp:attribute name="selectorJS">
								function(obj) {
        							return obj.displayName;
        						}
							</jsp:attribute>
						</ui:autocompleter>			
					</jsp:attribute>
				</ui:row>
				<ui:row path="Review status">
					<jsp:attribute name="label">Review status
					</jsp:attribute>
					<jsp:attribute name="value">
						<ui:select options="${command.reviewStatusOptionsMap}" path="reviewStatus"></ui:select>
					</jsp:attribute>
				</ui:row>
				<ui:row path="Report status">
					<jsp:attribute name="label">Report status
					</jsp:attribute>
					<jsp:attribute name="value">
						<ui:select options="${command.reportStatusOptionsMap}" path="reportStatus"></ui:select>
					</jsp:attribute>
				</ui:row>
				</chrome:box>
			</div>
			<c:choose>
        		<c:when test="${empty tab}">
            		<tags:tabControls tabNumber="${0}" isLast="${false}" willSave="${false}"/>
       			</c:when>
        		<c:otherwise>
            		<tags:tabControls tab="${tab}" flow="${flow}" willSave="${false}"/>
        		</c:otherwise>
    		</c:choose>
			<input type="hidden" name="paginationAction" value="firstPage"/>
			<input type="hidden" name="numberOfResultsPerPage" value="15"/>
	</form:form>	
</c:if>

</body>
</html>