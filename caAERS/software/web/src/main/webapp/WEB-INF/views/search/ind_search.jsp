<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Search IND#</title>

<title>${tab.longTitle}</title>
<tags:dwrJavascriptLink objects="search"/>

<script>

function buildTable(form) {
	$('indicator').className=''
	var type = "";
	var text = "";

	for(var x=0; x < 2; x++) {
	
		if ( $('prop'+x).value.length > 0 ){
			text = text +  $('prop'+x).value + ",";
			type = type +  $('prop'+x).name +',';
		}
	}
	
	$('prop').value=type
	$('value').value=text
	
	var parameterMap = getParameterMap(form);		
	search.getINDTable(parameterMap,type,text,showTable);
    $('bigSearch').show();
}


</script>
</head>
<body>
<div class="tabpane">
    <div class="workflow-tabs2">
        <ul id="" class="tabs autoclear">
            <li id="thirdlevelnav" class="tab"><div><a href="createIND">Create IND#</a></div></li>
            <li id="thirdlevelnav" class="tab selected"><div><a href="#">Search IND#</a></div></li>
        </ul>
        <tags:pageHelp propertyKey="searchIND"/>
    </div>

    <div class="content">
        <form:form name="searchForm" id="searchForm" method="post">
            
            <chrome:box title="Investigational New Drug Criteria" cssClass="mpaired" autopad="false">
            	<tags:instructions code="indsearch"/>
                <div class="row">
                    <div class="label"> IND #</div>
                    <div class="value"><input id="prop0" name="strINDNumber" type="text"/></div>
                </div>

                <div class="row">
                    <div class="label"> IND holder</div>
                    <div class="value"><input id="prop1" type="text" name="sponsorName"/></div>
                </div>

                <div class="endpanes"></div>
                <div class="row">
					<div class="value">
	                    <tags:button type="button" value="Search" color="blue" icon="search" onclick="buildTable('assembler');" size="small"/>
	                    <tags:indicator id="indicator"/>
					</div>
                </div>
                <div class="endpanes"></div>
            </chrome:box>


        </form:form>
		<div style="margin-left:20px; margin-bottom:10px;"><tags:button markupWithTag="a" value="Add IND" color="blue" icon="add" href="createIND" /></div>
        <div id="bigSearch" style="display:none;">
            <form:form id="assembler">
                <div>
                    <input type="hidden" name="_prop" id="prop">
                    <input type="hidden" name="_value" id="value">
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

</div>
</body>
</html>