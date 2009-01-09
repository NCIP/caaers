<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Search IND#</title>
<tags:stylesheetLink name="tabbedflow"/>
<tags:stylesheetLink name="participant"/>

<style type="text/css">
    div.row div.label { width: 9em; }
    div.row div.value { margin-left: 10em; }
    div.content { padding: 5px 15px; }        
</style>

<title>${tab.longTitle}</title>
<tags:javascriptLink name="extremecomponents"/>
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
            <p><tags:instructions code="indsearch"/></p>
            <chrome:box title="Investigational New Drug Criteria" cssClass="mpaired" autopad="false">
                <div class="row">
                    <div class="label"> IND # :&nbsp; </div>
                    <div class="value"><input id="prop0" name="strINDNumber" type="text"/></div>
                </div>

                <div class="row">
                    <div class="label"> IND holder :&nbsp; </div>
                    <div class="value"><input id="prop1" type="text" name="sponsorName"/></div>
                </div>

                <div class="endpanes"></div>
                <div class="row" style="float:right;">
                    <input class='ibutton' type='button' onClick="buildTable('assembler');" value='Search'
                           title='Search'/>
                    <tags:indicator id="indicator"/>
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