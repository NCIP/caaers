<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<tags:stylesheetLink name="tabbedflow"/>
<tags:stylesheetLink name="participant"/>
<tags:includeScriptaculous />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<style type="text/css">
        /* Override default lable length */
         div.row div.label { width: 9em; } 
        div.row div.value { margin-left: 10em; }
        div.content {
            padding: 5px 15px;
        }        
</style>

<title>${tab.longTitle}</title>
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<tags:dwrJavascriptLink objects="search"/>

<script>

function buildTable(form) {
	$('indicator').className=''
	var type = "";
	var text = "";

	for(var x=0; x < 3; x++) {
	
		if ( $('prop'+x).value.length > 0 ){
			text = text +  $('prop'+x).value + ",";
			type = type +  $('prop'+x).name +',';
		}
	}
	
	$('prop').value=type
	$('value').value=text
	
	var parameterMap = getParameterMap(form);		
	search.getInvestigatorTable(parameterMap,type,text,showTable);
}


</script>
</head>
<body>

<div class="tabpane">
    <div class="workflow-tabs2">

  <ul id="" class="tabs autoclear">
    <li id="thirdlevelnav" class="tab"><div>
        <a href="createInvestigator">Create/Edit Investigator</a>
    </div></li>
    <li id="thirdlevelnav" class="tab selected"><div>
        <a href="searchInvestigator">Search Investigator</a>
    </div></li>
  </ul>
	<tags:pageHelp propertyKey="searchInvestigator" />
  </div>
  <br />

 <div class="content">
  <form:form name="searchForm" id="searchForm" method="post">
   
   <chrome:box title="Investigator Criteria" cssClass="mpaired" autopad="false">
		    <div class="row">
		    	<div class="label"> First Name&nbsp; </div>
		    	<div class="value"><input id="prop0" name="firstName" type="text"/></div>
		    </div>
		    <div class="row">
		    	<div class="label"> Last Name&nbsp; </div>
		    	<div class="value"><input id="prop1" name="lastName" type="text"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label"> Investigator number&nbsp; </div>
		    	<div class="value"><input id="prop2" type="text" name="nciInstituteCode"/></div>
		    </div>

	<div class="endpanes" />
	<div class="row" style="float:right;">
	<input class='ibutton' type='button' onclick="buildTable('assembler');" value='Search'  title='Search'/>
	<tags:indicator id="indicator" />
	</div>
	<div class="endpanes" />
   </chrome:box>


   </form:form>
  <br>
  <form:form id="assembler" >
	<div>			
	<input type="hidden" name="_prop" id="prop" >
	<input type="hidden" name="_value" id="value"  >
	</div>
	<chrome:box title="Search Results">
     <chrome:division id="single-fields">
        <div id="tableDiv">
   			<c:out value="${assembler}" escapeXml="false"/> 
		</div>
	</chrome:division>
	</chrome:box>
   </form:form>
 </div>
 
</div>
</body>
</html>