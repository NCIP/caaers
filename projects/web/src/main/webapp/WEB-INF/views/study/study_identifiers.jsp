<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<script language="JavaScript" type="text/JavaScript">

function fireAction(action, selected){
	document.getElementById('command')._target.name='_noname';
	document.studyIdentifiersForm._action.value=action;
	document.studyIdentifiersForm._selected.value=selected;		
	document.studyIdentifiersForm.submit();
}
function clearField(field){
field.value="";
}

</script>
<script language="javascript">
  function toggleDiv(divid){
    if(document.getElementById(divid).style.display == 'none'){
      document.getElementById(divid).style.display = 'block';
    }else{
      document.getElementById(divid).style.display = 'none';
    }
  }
</script> </head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" formName="studyIdentifiersForm">
    <jsp:attribute name="singleFields">
		<div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>
		<p id="instructions">
			Add Identifiers associated with the Study
			<a href="javascript:fireAction('addIdentifier','0');"><img
				src="<c:url value="/images/checkyes.gif"/>" border="0" alt="Add" title="Add"></a><br>
		</p>
        
        
        
		<table class="tablecontent">
				<tr><td colspan="5" style="border-width: 0px; padding: 0px;">
                <div id="Identdiv" style="display:none"><h3>Enter Official name of the protocol provided by the study principal investigator or sponsor. Required Field. </h3></div>
                <div id="IdentTypediv" style="display:none"><h3>Select the category of the study specific identifier. Required field.</h3></div>
                <div id="AssignAuthdiv" style="display:none"><h3>Select institution assigning the study identifier. Required field.</h3></div>
                <div id="PrimInddiv" style="display:none"><h3>Is this the primary identifier for this study protocol ? Only one identifier can be selected as the primary indicator.</h3></div>
                </td></tr>
		
                <tr align="center">
			<th scope="col"> Identifier<span class="red">*</span> <a href="javascript:;" onMouseDown="toggleDiv('Identdiv');"> <img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></th>														
		  <th scope="col"> Identifier Type<span class="red">*</span> </b> <a href="javascript:;" onMouseDown="toggleDiv('IdentTypediv');"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></th>						
			<th scope="col"> Assigning Authority<span class="red">*</span> </b><a href="javascript:;" onMouseDown="toggleDiv('AssignAuthdiv');"> <img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></th>					
			<th scope="col"> Primary&nbsp;Indicator </b><a href="javascript:;" onMouseDown="toggleDiv('PrimInddiv');"> <img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></th>
			<th scope="col"></th>
		</tr>																		
		<c:forEach items="${command.identifiers}" varStatus="status">
		<tr align="center" class="results">
			<td class="alt"> <form:input path="identifiers[${status.index}].value" onclick="javascript:clearField(this)();"/> </td>
			<td class="alt"><form:select path="identifiers[${status.index}].type">
				<option value="">--please select --</option>								
				<form:options items="${identifiersTypeRefData}" itemLabel="desc"
					itemValue="code" /></form:select></td>	
			<td class="alt">
			    <form:select path="identifiers[${status.index}].source">
				<option value="">--please select --</option>					
				<form:options items="${identifiersSourceRefData}" itemLabel="name"
					itemValue="name" /></form:select></td>
			<td class="alt"><form:radiobutton path="identifiers[${status.index}].primaryIndicator" value="true"/></td> 
			<td class="alt"><a href="javascript:fireAction('removeIdentifier',${status.index});"><img
				src="/caaers/images/checkno.gif" border="0" alt="remove" title="Remove"></a></td>
		</tr> 
		</c:forEach> 
	</table>
  </jsp:attribute>
</tags:tabForm>
</body>
</html>
