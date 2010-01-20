<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Mandatory Fields</title>
    <style type="text/css">
	   	div.row div.label { width: 13em; } 
   		div.row div.value { margin-left: 14em; }
   		.half {
   			float:left;
			width:46%;
			margin:0 10px;
   		}
   		
   		.updated {
     		border: #494 solid;
     		border-width: 1px 0;
     		background-color: #8C8;
     		padding: 1em 2em;
     		text-align: center;
     		margin: 1em 30%;
     		color: #fff;
     		font-weight: bold;
     		font-size: 1.1em;
    	}
	</style>  
</head>
<body>
	<div class="tabpane">
	    <div class="workflow-tabs2">
    	    <ul id="" class="tabs autoclear">
        	    <li id="thirdlevelnav" class="tab">
           	    	<div>
                    	<a href="configure"><caaers:message code="configure.menu.general"/></a>
                	</div>
            	</li>
            	<li id="thirdlevelnav" class="tab">
                	<div>
                   		<a href="passwordPolicyConfigure"><caaers:message code="configure.menu.passwordPolicy"/></a>
                	</div>
            	</li>
            	<li id="thirdlevelnav" class="tab selected">
                	<div>
                   		<a href="mandatoryFields"><caaers:message code="configure.menu.mandatoryFields"/></a>
                	</div>
            	</li>
        	</ul>
    	</div>
    	<div class="content">
			<form:form>
			<caaers:message code="configure.menu.aefields.title" var="detailsSectionTitle"/>
        		<chrome:box title="${detailsSectionTitle}">
             			<tags:instructions code="admin.mandatory.fields.instruction" />
						<tags:hasErrorsMessage hideErrorDetails="true"/>
            			<tags:jsErrorsMessage/>
						<!--  JSP body here -->
						<div id="captureAdverseEventsFields-id">
			 				<rd:renderMandatoryFields key="CAPTURE_ADVERSE_EVENTS_TAB~ADVERSE_EVENT_SECTION~Adverse events"/>
			 				<%-- <c:forEach var="field" items='${fieldGroups["CAPTURE_ADVERSE_EVENTS_TAB~ADVERSE_EVENT_SECTION~Adverse events"].fields}'>
			 					${field }
								<br><tags:renderLabel field="${field}" /><br>
							</c:forEach>  --%>
						</div>
						<c:if test="${updated}"><p class="updated">Settings saved</p></c:if>
    			</chrome:box>
        		<div class="content buttons autoclear">
          			<div class="flow-buttons">
           				<span class="next">
              				<!--  reset and save buttons -->
               				<tags:button type="submit" value="Save" color="green" icon="save" />
           				</span>
          			</div>
        		</div>
			</form:form>
		</div>
    </div>
</body>
</html>