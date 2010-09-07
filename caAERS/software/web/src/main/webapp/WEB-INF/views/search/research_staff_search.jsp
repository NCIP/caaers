<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:CREATE" var="hasRSCreate"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:UPDATE" var="hasRSUpdate"/>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.ResearchStaff:READ" var="hasRSRead"/>

<html>
<head>

<style>
.yui-pg-page { padding: 5pt; }
.yui-dt-label .yui-dt-sortable { color: white; }
.yui-dt table { width: 100%; }
div.yui-dt-liner a {color : black;}
tr.yui-dt-even { background-color: #FFF; border-bottom: 1px gray solid;}
tr.yui-dt-odd { background-color: #EDF5FF; border-bottom: 2px blue dotted; padding: 2px; }
</style>

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
	showCoppaSearchDisclaimer();
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
	search.getResearchStaffTable(parameterMap, type, text, test);
    $('bigSearch').show();

}

function test(jsonResult) {
    $('indicator').className = 'indicator';
    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
    hideCoppaSearchDisclaimer();
}

var linkFormatter = function(elCell, oRecord, oColumn, oData) {
        var _id = oRecord.getData("id");
        elCell.innerHTML = "<a href='editResearchStaff?researchStaffId=" + _id + "'>" + oData + "</a>";
};

var myColumnDefs = [
    {key:"firstName",        label:"First Name",    sortable:true,      resizeable:true, formatter: linkFormatter},
    {key:"middleName",       label:"Middle Name",   sortable:true,      resizeable:true},
    {key:"lastName",         label:"Last Name",     sortable:true,      resizeable:true, formatter: linkFormatter},
    {key:"organization",     label:"Organization",  sortable:true,      resizeable:true}
];

var myFields = [
    {key:'id',                         parser:"integer"},
    {key:'firstName',    parser:"string"},
    {key:'middleName',   parser:"string"},
    {key:'lastName',     parser:"string"},
    {key:'organization',               parser:"string"}
];

</script>
</head>
<body>
<div class="tabpane">
    <div class="workflow-tabs2">
        <ul id="" class="tabs autoclear">
            <c:if test="${hasRSCreate and hasRSUpdate}"><li id="thirdlevelnav" class="tab"><div><a href="createResearchStaff"><caaers:message code="researchstaff.menu.createEditResearchStaff"/></a></div></li></c:if>
            <c:if test="${hasRSRead}"><li id="thirdlevelnav" class="tab selected"><div><a href="searchResearchStaff"><caaers:message code="researchstaff.menu.searchResearchStaff"/></a></div></li></c:if>
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
							                  image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>&nbsp';
							        } else {
							                  image = '';
							        }
					    			return image + organization.name + " (" + organization.nciInstituteCode + ")";
								}
							</jsp:attribute>
						</ui:autocompleter>
					</div>
            </div>

		<div class="row">
			<div class="value" style="float:left;">
		   		<tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('assembler'); $('bigSearch').show();"/>
				<tags:indicator id="indicator"/>
				<span id="coppa-search-disclaimer" class="coppa-search-disclaimer" style="display:none;"><caaers:message code="coppa.search.message" /></span>
			</div>
		</div>
        
        <div class="endpanes"></div>
    </chrome:box>

   </form:form>
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