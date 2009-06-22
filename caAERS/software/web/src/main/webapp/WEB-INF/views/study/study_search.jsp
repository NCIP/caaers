<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>Search Studies</title>
<tags:dwrJavascriptLink objects="searchStudy"/>
<style type="text/css">
        /* Override default lable length */
		div.row {margin-top:0px; margin-bottom:0px;}
       	div.row div.label { width: 9em; }
        div.row div.value { margin-left: 10em; }
        .endpanes {	clear: both; }
</style>
<script>

function buildTable(form) {

	var type = $F('searchCriteria[0].searchType')
	var text = $F('searchCriteria[0].searchText')

	if(text == ''){
		$('error').innerHTML="<font color='#FF0000'>Provide at least one character in the search field</font>"
	}else{
		$('error').innerHTML=""

//		//showing indicator and hiding pervious results. (#10826)
		$('indicator').className='';
	//	$('assembler_table').hide();  //do not hide the results..becz filter string get disappear
        var parameterMap = getParameterMap(form);

		searchStudy.getTable(parameterMap, type, text, showTable);
	}
}



function fireAction(action, selected){
	document.getElementById("_action").value=action;
	document.getElementById("_selected").value=selected;
	document.searchForm.submit();
}

function onKey(e) {
    var keynum = getKeyNum(e);

    if (keynum == 13) {
        Event.stop(e);
        buildTable('searchForm');
        $('bigSearch').show();
    } else return;
}


</script>
</head>
<body>

<chrome:box title="Search Criteria" autopad="true">
<p><tags:instructions code="study.search.top" /></p>
    <form:form name="searchForm" id="searchForm" method="post">
       <div>
			<input type="hidden" name="_selected" id="_selected" value="">
			<input type="hidden" name="_action" id="_action" value="">
		</div>
        <div class="content">

        	<c:forEach items="${command.searchCriteria}" varStatus="status">
            <div class="row" name="inputs" style="float:left;">
            	<div class="label"> Search By: </div>
                <div class="value">
                	<form:select path="searchCriteria[${status.index}].searchType">
						<form:options items="${studySearchType}" itemLabel="desc" itemValue="code" />
					</form:select>
					<form:input path="searchCriteria[${status.index}].searchText" size="25" onkeydown="onKey(event);"/>
					<div id="error"></div>
				</div>
			</div>
		 	</c:forEach>

            <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('searchForm'); $('bigSearch').show();"/>
            <tags:indicator id="indicator" />
        </div>
    </form:form>
</chrome:box>

<div class="endpanes" />
<div class="row" style="float:right;">
</div>

<div id="bigSearch" style="display:none;">
<div class="endpanes" />
<chrome:box title="Results">
<p><tags:instructions code="study.search.results" /></p>
<form:form id="assembler">
     <chrome:division id="single-fields">
        <div id="tableDiv">
   			<c:out value="${assembler}" escapeXml="false"/>
		</div>
	</chrome:division>
</form:form>
</chrome:box>
</div>

</body>
</html>