<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.extremecomponents.org" prefix="ec"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Search for a Study</title>
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<tags:dwrJavascriptLink objects="searchStudy"/>
<script type="text/javascript">
	  function buildTable(form) {
		var parameterMap = getParameterMap(form);   
		//alert(parameterMap);
		//searchStudy.getTable(parameterMap, showTable);
		var type = document.getElementById('searchType').value;
		var text = document.getElementById('searchText').value;
		searchStudy.getTable(parameterMap, type, text, showTable);
		//searchStudy.getTable(null, type, text, showTable);		
      }

	  function buildTable1(form) {		
		var r = document.getElementsByName("inputs");	
		var type = "";
		var text = "";
		for(var x=0; x < r.length; x++) {
			if(x==0)
			{
				type = document.getElementById("searchCommand[" + x + "].searchType").value;
				text = document.getElementById("searchCommand[" + x + "].searchText").value;			
			}
			else 
			{
				type = type + "," + document.getElementById("searchCommand[" + x + "].searchType").value;
				text = text + "," + document.getElementById("searchCommand[" + x + "].searchText").value;			
			}
			// alert(document.getElementById("searchCommand[" + x + "].searchType").value);
			// alert(document.getElementById("searchCommand[" + x + "].searchText").value);			
		}

		// alert(type);
		// alert(text);
		
		//var parameterMap = getParameterMap(form);   
		//alert(parameterMap);
		//searchStudy.getTable(parameterMap, showTable);
		//var type = document.getElementById('searchType').value;
		//var text = document.getElementById('searchText').value;
		//searchStudy.getTable(parameterMap, type, text, showTable);
		//alert(type);
		//searchStudy.getTable(null,null, null, showTable);
		searchStudy.getTable(null, type, text, showTable);		
      }

      function showTable(table) {
		document.getElementById('tableDiv').innerHTML=table;
	  }
	  
	  function fireAction(selected){	
		document.searchForm._selected.value=selected;		
		document.searchForm.submit();	
	  }
	
	  
</script>
</head>
<body>

<%-- <chrome:search title="">	--%>
    <br>
   <%-- <form:form id="searchForm">
        <table border="0" cellspacing="0" cellpadding="0" class="search">
            <tr>
                <td class="searchType">
                    Search Study&nbsp;&nbsp;
                </td>                
                <td><form:select path="searchType">
						<form:options items="${studySearchType}" itemLabel="desc" itemValue="code" />
					</form:select></td>
                <td><form:input path="searchText" size="25"/></td>								
				<td>
				  <input class='ibutton' type='button' onclick="buildTable1('assembler');" value='Go'  title='Calls searchStudy.a(). View source for details.'/>
  <script type='text/javascript'>
    var reply1 = function(data)
    {
	alert("hi1");
      if (data != null && typeof data == 'object') alert(dwr.util.toDescriptiveString(data, 2));
	  document.getElementById('tableDiv').innerHTML=data;
      //else dwr.util.setValue('tableDiv', dwr.util.toDescriptiveString(data, 1));
    }
  </script>

				</td>
            </tr>
            <tr>
                <td> </td>				
                <td> </td>
				<td class="notation"> Search Criteria </td>
            </tr>
        </table>
    </form:form> --%>
        
    <form:form id="searchForm" name="searchForm">
	    <div>			
			<input type="hidden" name="_selected" value="">
		</div>
        <table border="0" cellspacing="0" cellpadding="0" class="search">
			<tr>
			 <td class="searchType">
                    Search Study&nbsp;&nbsp;
                </td>     
			</tr> 

        	<c:forEach items="${command.searchCommand}" varStatus="status">
	
            <tr name="inputs">
                           
                <td><form:select path="searchCommand[${status.index}].searchType">
						<form:options items="${studySearchType}" itemLabel="desc" itemValue="code" />
					</form:select></td>
                <td width="25%"><form:input path="searchCommand[${status.index}].searchText" size="25"/></td>								
                <td>
					<a href="javascript:fireAction(${status.index});"><img
					src="/caaers/images/checkno.gif" border="0" alt="remove"></a>										
				</td>
			</tr>
            </c:forEach>

			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
            <tr align="center">
				<td width="20%">										
					<b><a href="javascript:fireAction('-1');"><img
						src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></b> 
				</td>
				<td>
				  <input class='ibutton' type='button' onclick="buildTable1('searchForm');" value='Go'  title='Calls searchStudy.a(). View source for details.'/>
  <script type='text/javascript'>
    var reply1 = function(data)
    {
	alert("hi1");
      if (data != null && typeof data == 'object') alert(dwr.util.toDescriptiveString(data, 2));
	  document.getElementById('tableDiv').innerHTML=data;
      //else dwr.util.setValue('tableDiv', dwr.util.toDescriptiveString(data, 1));
    }
  </script>

				</td>
			</tr>	
        </table>
    </form:form> 
<%-- </chrome:search> --%>

<div id="tableDiv">
   <c:out value="${assembler}" escapeXml="false"/> 
</div>

<!-- MAIN BODY ENDS HERE -->
</body>
</html>
