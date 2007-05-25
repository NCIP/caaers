<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ruletags" tagdir="/WEB-INF/tags/rule"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <title>Not implemented</title>
	<style>
		#message {width:80%; height:200px}
		#subject,#to,#from {width : 81%}
		#roles{width:30%}
		#tsu:hover{
			background-color: #6E81A6;
            color: white;
		}
		#tsu_current{
			background-color: #6E81A6;
		}
	</style>
		
	<script type="text/javascript" src="/caaers/js/tiny_mce/tiny_mce_src.js"></script>
	 <script language="javascript" type="text/javascript">
			tinyMCE.baseURL="<c:url value="/js/tiny_mce" />";
			tinyMCE.srcMode="_src";
			tinyMCE.init({
				mode : "textareas",
				theme : "advanced",
				theme_advanced_buttons1 : "bold,italic,underline,separator,strikethrough,justifyleft,justifycenter,justifyright, justifyfull,bullist,numlist,undo,redo,link,unlink",
				theme_advanced_buttons2 : "",
				theme_advanced_buttons3 : "",
				theme_advanced_toolbar_location : "top",
				theme_advanced_toolbar_align : "left",
				extended_valid_elements : "a[name|href|target|title|onclick],img[class|src|border=0|alt|title|hspace|vspace|width|height|align|onmouseover|onmouseout|name],hr[class|width|size|noshade],font[face|size|color|style],span[class|align|style]"
			});				
				
		 function initTinyMCE(){
		 	tinyMCE.idCounter=0;
			tinyMCE.execCommand('mceAddControl', true, 'div_my_editor');
		 }
	</script>
   
	<script language="javascript" type="text/javascript">
		//to add new recipients
		function insertRecipient(rType, rVal){
			var newDiv = $('div_recipient_'+rType).cloneNode(true);
			newDiv.id = newDiv.id + '_1'; //change the id to avoid confilict
			if(rVal){
			    if(rType = 'direct'){
				var element = $A(newDiv.getElementsByTagName('input')).first();
				   element.value=rVal;
				}else{
					var element = $A(newDiv.getElementsByTagName('select')).first();
					element.selected=rVal;				
				}
			}
			newDiv.show();
			var insertLoc = $('recipientHolder');
			insertLoc.parentNode.insertBefore(newDiv,insertLoc);
		}
		//removes a recipient
		function removeRecipient(aDiv){
			aDiv.parentNode.removeChild(aDiv);
		}
		
		function selectTimeScaleUnit(tsu){
			//alert('hello');
			var frm = $('command');
			frm.elements['pointOnScale'].value = tsu;
			//frm.elements['_page'].value = frm.elements['_page'].value - 1;
			var target = $('_target');
			target.name = '_target1';
			frm.submit();
		}
		
	
		
	</script> 

</head>
<body>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
  	 <tr>
    	<td>
    	  	<!-- This box contains the Scale -->
    		<chrome:box title="Time Scale"  style="width:98%" id="timescale" cssClass="paired" autopad="true">
   				<p>Choose the ${command.timeScaleType},and configure the notification<br /></p>
   				<c:set var="loopEnd">
   				   <c:if test="${command.timeScaleType == 'DAY'}">
   				    27
   				   </c:if>
   				   <c:if test="${command.timeScaleType == 'WEEK'}">
   				    6
   				   </c:if>
   				    <c:if test="${command.timeScaleType == 'HOUR'}">
   				    23
   				   </c:if>
   				    <c:if test="${command.timeScaleType == 'SECOND'}">
   				    60
   				   </c:if>
   				   <c:if test="${command.timeScaleType == 'MINUTE'}">
   				    60
   				   </c:if>
   				    <c:if test="${command.timeScaleType == 'MONTH'}">
   				    11
   				   </c:if>
   				</c:set>
   				<!--
   				 loopEnd : ${loopEnd}
   				-->
   				<table  border="1" cellpadding="0" cellspacing="0" bordercolor="#0066CC" width="98%">
   					<tr align="center" valign="middle"> 
   						<td align="left" width="150"> Scale Unit (${command.timeScaleType})</td>
   						<c:forTokens delims="," var="tsUnit" 
   						             items="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28">
   						    <td width="40">
   						    	<c:if test="${tsUnit eq command.pointOnScale}">
   						    	<div id="tsu_current">${tsUnit}</div>
   						    	</c:if>
   						    	<c:if test="${tsUnit != command.pointOnScale}">
   						    	<div id="tsu" onClick="javascript:{selectTimeScaleUnit(${tsUnit});}" >${tsUnit}</div>
   						    	</c:if>
   							</td>
   						</c:forTokens>
					</tr>
				</table>
    		</chrome:box>
    	</td>
			
  	</tr>
  	<tr>
    	<td>
    	<!-- hidden role recipient div -->
			<div id="div_recipient_role" style="display: none">
			  <select name="roleRecipient" id="recipient_role">
			   <option value="">Select a Role</option>
			  <c:forEach var="role" begin="0" items="${command.allRoles}">
			  	<option value="${role}">${role}</option>
			  </c:forEach>
      		  </select>
	      	<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" 
            		id="remove-action-image" style="border: 0px none ;" 
                    onClick="javascript:{removeRecipient(this.parentNode)}"/>
    	</div>
		<!-- hidden direct recipient div -->
		<div id="div_recipient_direct" style="display: none">
  			<input type="text" name="directRecipient" id="email" size="40"/>
			<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" 
                		id="remove-action-image" style="border: 0px none ;" 
                        onClick="javascript:{removeRecipient(this.parentNode)}"/>
    	</div> 
    	<div id="div_matter">
	   			<!-- This box contains the body-->
    	
    			<tags:tabForm tab="${tab}" flow="${flow}" willSave="false" 
    				title="Configure Notification for ${command.timeScaleType} : ${command.pointOnScale}">
				  <jsp:attribute name="singleFields">
			 		<tags:errors path="*"/>   
			
		          	<div id="div_nf_box">
		         	  	<table width="100%" border="1" >
 						 <tr>
   							<td width="15%">From Address</td>
  							<td width="85%"><form:input path="fromAddress" size="40"/></td>
 						 </tr>
 						 <tr>
   							<td width="15%">Recipients
   							<a href="javascript:{insertRecipient('role')}" >
   							<img align="add-role-image" style="border: 0px none ;" src="/caaers/images/rule/add_role.gif" />
   							</a>
   							<a href="javascript:{insertRecipient('direct')}" >
   							<img align="add-role-image" style="border: 0px none ;" src="/caaers/images/rule/add_condition.gif" />
   							</a> 
   							</td>
   							<td width="85%">
   								<c:forEach var="email" items="${command.directRecipient}">
   								<div id="div_recipient">
   								    <div id="div_recipient_direct_x" >
  									<input type="text" name="directRecipient" id="email" value="${email}" size="40"/>
									<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" 
                						id="remove-action-image" style="border: 0px none ;" 
                       					 onClick="javascript:{removeRecipient(this.parentNode)}"/>
    							</div> 
   								</c:forEach>    
   								<c:forEach var="selectedRole" items="${command.roleRecipient}">
   									<div id="div_recipient_role_x" >
			  						<select name="roleRecipient" id="recipient_role">
			  							<option value="">Select a Role</option>
			  							<c:forEach var="role" begin="0" items="${command.allRoles}">
			  							<option value="${role}" ${selectedRole == role}>${role}</option>
			  						</c:forEach>
      		  						</select>
	      							<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" 
            							id="remove-action-image" style="border: 0px none ;" 
                   						 onClick="javascript:{removeRecipient(this.parentNode)}"/>
    							</div>
   								</c:forEach>
   									<span id="recipientHolder"></span>
   								</div>
   							</td>
 						 </tr>
 						 <tr>
   							<td width="15%">Subject Line</td>
   							<td width="85%"><form:input path="subjectLine" size="100" /></td>
 						 </tr>
 						 <tr>
   							<td colspan="2" align="center"> 
   								<div id="div_my_editor">
   									<form:textarea cssStyle="display:none;  width:96%" path="message" />
   								</div>
   							</td>
   						 </tr>
						</table>
						<form:hidden path="pointOnScale" />
						<form:hidden path="lastPointOnScale" />
						
		    		</div>
				  </jsp:attribute>
				</tags:tabForm> 
  
    	</div> <!-- div_matter -->
    	</td>
  	 </tr>
	</table>
       		
 <!--
	TestOut :
	   ${command.name}, ${command.notificationType}, ${command.timeScaleType}
-->
	
</body>

</html>
