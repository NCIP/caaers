<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>

<style type="text/css">
    div.row div.label { width: 9em; }
    div.row div.value { margin-left: 10em; }
</style>

<title><caaers:message code="researchStaff.search.pageTitle"/></title>
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<tags:dwrJavascriptLink objects="search,createInvestigator"/>
<script language="JavaScript">

function buildTable(form) {
	$('indicator').className=''
	var type = "";
	var text = "";

	for (var x=0; x < 2; x++) {
	
		if ($('prop'+x).value.length > 0 ) {
			text = text +  $('prop'+x).value + ",";
			type = type +  $('prop'+x).name +',';
		}
	}

	text = text + $('organization').value + ",";
    type = type + "organization" + ',';
	
	$('prop').value=type
	$('value').value=text
	
	var parameterMap = getParameterMap(form);		
	search.getResearchStaffTable(parameterMap,type,text,showTable);
    $('bigSearch').show();
    
}

</script>
</head>
<body>
<div class="tabpane">
    <div class="workflow-tabs2">
        <ul id="" class="tabs autoclear">
            <li id="thirdlevelnav" class="tab">
                <div>
                    <a href="createResearchStaff"><caaers:message code="researchstaff.menu.createEditResearchStaff"/></a>
                </div>
            </li>
            <li id="thirdlevelnav" class="tab selected">
                <div>
                    <a href="searchResearchStaff"><caaers:message code="researchstaff.menu.searchResearchStaff"/></a>
                </div>
            </li>
        </ul>
		<tags:pageHelp propertyKey="searchResearchStaff" />
    </div>
 
 <div class="content">
    <form:form name="searchForm" id="searchForm" method="post">
		<caaers:message code="researchstaff.search.criteriaSection" var="criteriaSectionTitle"/>
        <chrome:box title="Search Research Staff" cssClass="mpaired" autopad="false">
        	<tags:instructions code="researchstaffsearch" />
            <div class="row">
                <div class="label"><caaers:message code="LBL_firstName"/>&nbsp; </div>
                <div class="value"><input id="prop0" name="firstName" type="text"/></div>
            </div>

            <div class="row">
                <div class="label"><caaers:message code="LBL_lastName"/>&nbsp; </div>
                <div class="value"><input id="prop1" name="lastName" type="text"/></div>
            </div>

            <div class="row">
				<div class="label"><caaers:message code="LBL_organization"/>&nbsp; </div>
					<div class="value">
						<ui:autocompleter path="organization"
							initialDisplayValue="Begin typing here..." enableClearButton="true">
							<jsp:attribute name="populatorJS">
								function(autocompleter, text) {
				         				createInvestigator.restrictOrganization(text, function(values) {
				    					autocompleter.setChoices(values)
				  					})
				     			}
							</jsp:attribute>
							<jsp:attribute name="selectorJS">
								function(organization){
					        		var image;            	
							    	if(organization.externalId != null){
							                  image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
							        } else {
							                  image = '';
							        }
					    			return organization.name + " (" + organization.nciInstituteCode + ")";
								}
							</jsp:attribute>
						</ui:autocompleter>
					</div>
            </div>

		<div class="row">
			<div class="value" style="float:left;">
		   		<tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('assembler'); $('bigSearch').show();"/>
				<tags:indicator id="indicator"/>
			</div>
		</div>
        
        <div class="endpanes"></div>
    </chrome:box>

   </form:form>
	<div style="margin-left:20px"><tags:button markupWithTag="a" color="blue" icon="add" value="Add Research Staff" href="createResearchStaff" /></div>
     <div id="bigSearch" style="display:none;">
         <br>
         <form:form id="assembler">
             <div>
                 <input type="hidden" name="_prop" id="prop">
                 <input type="hidden" name="_value" id="value">
             </div>
             <caaers:message code="researchStaff.search.resultsSection" var="resultsSectionTitle"/>
             <chrome:box title="${resultsSectionTitle}">
                 <chrome:division id="single-fields">
                     <div id="tableDiv">
                         <c:out value="${assembler}" escapeXml="false"/>
                     </div>
                 </chrome:division>
             </chrome:box>
         </form:form>
     </div>


 </div>
</div>

</body>
</html>