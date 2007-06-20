<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ruletags" tagdir="/WEB-INF/tags/rule"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <title>Not implemented</title>
     <style>
    #name {width:300px}
    .test {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	height: auto;
	width: auto;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: solid;
	border-bottom-style: none;
	border-left-style: solid;
	border-top-color: #80A5E7;
	border-right-color: #003399;
	border-bottom-color: #80A5E7;
	border-left-color: #80A5E7;
	margin: 0px;
	padding: 0px;
}
 .test0{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	height: auto;
	width: auto;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: solid;
	border-bottom-style: none;
	border-left-style: solid;
	border-top-color: #80A5E7;
	border-right-color: #80A5E7;
	border-bottom-color: #80A5E7;
	border-left-color: #003399;
	padding-left: 3px;
}
  .test2 {
	width: 300px;
	padding-top: 1px;
	padding-right: 1px;
	padding-bottom: 1px;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #000099;
	border-right-color: #000099;
	border-bottom-color: #000099;
	border-left-color: #000099;
	background-color: #B9CDFB;
}
 .test3 {
	width: auto;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #000099;
	border-right-color: #000099;
	border-bottom-color: #000099;
	border-left-color: #000099;
	padding: 3px;
}
    .test4 {
	width: auto;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: solid;
	border-bottom-style: none;
	border-left-style: none;
	border-top-color: #000099;
	border-right-color: #000099;
	border-bottom-color: #000099;
	border-left-color: #000099;
	
}
    .testfrom {
	width: auto;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #7B99E1;
	border-right-color: #0033CC;
	border-bottom-color: #7B99E1;
	border-left-color: #7B99E1;
	padding: 3px;
	overflow:auto; width:100%; padding: 20px 0; 
}
    </style>
    <script language="javascript">
    	function deleteNF(iots){
    		var frm = $('command');
			frm.elements['delete'].value = iots;
			var target = $('_target');
			target.name = '_target2';
			var finish = $('markFinish');
			finish.name='xyz';
			frm.submit();
    	}
    </script>
</head>
<body>
    
    <chrome:division title="Review Notification(s)">
    <tags:tabForm tab="${tab}" flow="${flow}" >
	<jsp:attribute name="singleFields">
            <tags:errors path="*"/>
            
           <table width="100%" border="0" cellpadding="4" cellspacing="4">
  			<tr>
    		<td width="25%"><div align="right" class="label">Name</div></td>
    		<td width="75%">${command.reportDefinition.name}</td>
  			</tr>
  			<tr>
    		<td><div align="right" class="label">Description</div></td>
    		<td>${command.reportDefinition.description}</td>
  			</tr>
  			<tr>
    		<td><div align="right" class="label">Time Scale UOM</div></td>
    		<td>${command.reportDefinition.timeScaleUnitType}</td>
  			</tr>
  			<tr>
    		<td><div align="right" class="label">Time Till Report Due</div></td>
    		<td>${command.reportDefinition.duration}</td>
  			</tr>
  			<tr>
    		<td><div align="right"></div></td>
    		<td>&nbsp;</td>
  			</tr>
		   </table>
        	<c:forEach var="pen" items="${command.reportDefinition.plannedNotifications}">
        	<div class="test0">
        	<table border="0" bordercolor="#0033ff" width="100%"  cellpadding="4" cellspacing="0">
 				 <tr><td width="20%"> <div align="left" class="test2">Notification for ${command.reportDefinition.timeScaleUnitType.displayName} : ${pen.indexOnTimeScale}</div></td>
                 <td><div class="test3">&nbsp;</div></td>
                 </tr>
                 <tr>
   				<td width="20%"><div class="label">From Address</div></td>
  				<td width="80%"><div class="testfrom">${pen.fromAddress}</div></td>
 				</tr>
 				<tr>
   				<td><div  class="label">Recipients</div></td>
   					<td>
   				  	 <div class="testfrom">
   				  	 <c:forEach var="r" items="${pen.recipients}">
   				  	 	${r}<br />
   				  	 </c:forEach>
   				 	 </div>
   				</td>
 				</tr>
 				<tr>
   				<td><div  class="label" style="height:100%;">Subject Line</div></td>
   				<td><div class="testfrom">${pen.subjectLine}</div></td>
 				</tr>
 				<tr align="left" valign="top">
 				<td> 
                <div  class="label" style="height:100%;">
					Message
                    </div>
   				</td>
   				<td> 
   				<div class="testfrom" >
   					<pre>${pen.notificationBodyContent.bodyAsString}</pre>
   				</div></td>
   				</tr>
   				<tr>
   				<td colspan="2">
   				  <div class="buttons" align="right"><input type="button" name="del" onClick="javascript:deleteNF(${pen.indexOnTimeScale});" value="Delete Notification of ${command.reportDefinition.timeScaleUnitType.displayName} : ${pen.indexOnTimeScale}" /></div>
   				</td>
   				</tr>
 				</table>
           </div>
           </c:forEach>
            <input id="markFinish" type="hidden" name="_finish"/>
            <input type="hidden" name="lastPointOnScale" value="" />
            <input type="hidden" name="delete" value="" />
		</jsp:attribute>
	</tags:tabForm> 
	</chrome:division>
    
</body>


</html>