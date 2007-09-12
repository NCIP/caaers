
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="investigator" tagdir="/WEB-INF/tags/investigator"%>

<html>
<head>
<tags:stylesheetLink name="participant" />
<tags:includeScriptaculous />
<tags:stylesheetLink name="tabbedflow"/>
 	 <style type="text/css">
        div.content {
            padding: 5px 15px;
        }
    </style>

<tags:dwrJavascriptLink objects="createInvestigator" />

<script language="JavaScript" type="text/JavaScript">


var si = [];
var addInvestigatorEditor;
var jsInvestigator = Class.create();
Object.extend(jsInvestigator.prototype, {initialize: function(index) {}});
    
function fireAction(action, selected){
		if(action == 'addInvestigator'){
			addInvestigatorEditor.add.bind(addInvestigatorEditor)();
		}else{
			document.getElementById('command')._target.name='_noname';
			document.createInvestigatorForm._action.value=action;
			document.createInvestigatorForm._selected.value=selected;
			document.createInvestigatorForm._finish.name='xyz';		
			document.createInvestigatorForm.submit();
		}
	};
	
function clearField(field){
		field.value="";
	};
	
	
	  
Event.observe(window, "load", function() {
            	<c:forEach varStatus="status" items="${command.siteInvestigators}" var="si">
					new jsInvestigator(${status.index});
			
      		</c:forEach>
      		
      		//This is added for Add Site Investigator button
		            new ListEditor("site-investigator-row", createInvestigator, "SiteInvestigator", {
		            	addFirstAfter: "site-investigator",
		                addCallback: function(nextIndex) {new jsInvestigator(nextIndex);}
		            });
    });
	

	</script>

</head>
<body>

<div class="tabpane">
  <ul id="workflow-tabs" class="tabs autoclear">
    <li class="tab selected"><div>
        <a href="createInvestigator">Add/Edit Investigator</a>
    </div></li>
    <li class="tab"><div>
        <a href="searchInvestigator">Search Investigator</a>
    </div></li>
  </ul>
  <br />
 

<tags:tabForm tab="${tab}" flow="${flow}" formName="createInvestigatorForm"
	hideErrorDetails="true" willSave="false">

	<jsp:attribute name="singleFields">
	<div><input type="hidden" name="_action" id="_action" value="">
	<input type="hidden" name="_selected" id="_selected" value="-1">
	    <c:if test="${(empty id) or ( id le 0) }"><input type="hidden" name="_finish" value="true"/></c:if>
	</div>

             </jsp:attribute>
	<jsp:attribute name="repeatingFields">
    
    
    	<chrome:division title="Investigator Details" id="investigator">
		
<table id="test2" class="single-fields" class="tablecontent">
        	<tr>
    				<td> 
    				<c:forEach begin="0" end="3"
						items="${fieldGroups.investigator.fields}" var="field">
                    <tags:renderRow field="${field}"  />
                	</c:forEach>
    				</td>
    				<td><c:forEach begin="4" end="6"
						items="${fieldGroups.investigator.fields}" var="field">
                    <tags:renderRow field="${field}" />
                	</c:forEach>
                	<div id="test-row" class="row"></div>
    				</td>
    			</tr>
    			
    		</table> 
    
	</chrome:division>
	<chrome:division title="Associate Organizations">
	  
	  <table class="tablecontent">
    			<tr id="site-investigator">
    				<th class="tableHeader"><tags:requiredIndicator />Organization</th>
    				<th class="tableHeader"><tags:requiredIndicator />Status</th>
    			</tr>
    			
            	<c:forEach varStatus="status" items="${command.siteInvestigators}">
					<investigator:siteInvestigator 	title="Associated Sites ${status.index + 1}" enableDelete="${status.index > 0}"
						sectionClass="site-investigator-row"
						removeButtonAction="removeInvestigator" index="${status.index}" />
				</c:forEach>
            	
            	</table>
	
	</chrome:division>
         
     </jsp:attribute>

	<jsp:attribute name="localButtons"> 
	      	<chrome:division title="">          	
	      		<tags:listEditorAddButton divisionClass="site-investigator-row" label="Add Organization" />   
            </chrome:division>                                                                                                                                                                                                                                                             
	</jsp:attribute>

</tags:tabForm>

</body>
</html>
