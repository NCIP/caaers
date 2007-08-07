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
		
		#tsu:hover{
			color:#002EE2;
            background-image: url(/caaers/images/rule/timescale_back.gif);
            cursor: pointer;
		}
		#tsu_current{
			background-color: #6E81A6;
		}
		.divider{
  			border-left-color: #ADC9EF;
  			border-left-style: solid;
  			border-left-width: 1px
		}
	</style>
		
	
   
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
			var insertLoc = $('recipientHolder_'+ rType);
			insertLoc.parentNode.insertBefore(newDiv,insertLoc);
		}
		//removes a recipient
		function removeRecipient(aDiv){
			var frm = $('command');
			aDiv.parentNode.removeChild(aDiv);
		}
		
		function selectTimeScaleUnit(tsu){
			var frm = $('command');
			frm.elements['pointOnScale'].value = tsu;
			//frm.elements['_page'].value = frm.elements['_page'].value - 1;
			var target = $('_target');
			target.name = '_noname';
			frm.submit();
		}
		
		//This is the function that will insert the substitutions
		var FREE_MARKER_PREFIX = '$' + '{';
		var FREE_MARKER_SUFFIX = '}';
		
		
		var lastElement; //will store the last element on focus
		function insertReplacement(selSub){
			i = selSub.selectedIndex;
			if(i < 0) return;
			if(!lastElement){
			  selSub.selectedIndex = 0;
			  return;
			}
			
			txtToInsert = FREE_MARKER_PREFIX + selSub.options[i].value 
				  + FREE_MARKER_SUFFIX;
			
			var msg = lastElement.value;
			if(lastElement.type == 'text'){
				//text (subject)
				msg = msg + txtToInsert;
				lastElement.value = msg;
			}else if(lastElement.type == 'textarea'){
			   //textarea (message)
				st = lastElement.selectionStart;
				end = lastElement.selectionEnd;
				len = lastElement.textLength;
				msg = msg.substring(0,st) + txtToInsert + msg.substring(end, len);
				lastElement.value = msg;
				lastElement.selectionStart = st + txtToInsert.length;
				lastElement.selectionEnd = lastElement.selectionStart;
			}
			selSub.selectedIndex = 0;
			lastElement.focus();
		}
		
	</script> 

</head>
<body>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
  	 <tr>
    	<td>
    	  	<!-- This box contains the Scale -->
    		<chrome:box title="Time Scale" id="timescale" style="width:100%" autopad="true">
   				<p>Choose the ${command.timeScaleType},and configure the notification(s)<br /></p>
   				
   				<table  border="0" cellpadding="0" cellspacing="0" bordercolor="#0066CC" width="98%">
   					<tr align="center" valign="middle"> 
   						<td align="left" width="80"> ${command.timeScaleType}</td>
   						<c:forTokens delims="," var="tsUnit" 
   						             items="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28">
   						    <td width="40" class="divider">
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
			  
			  <c:forEach var="role" begin="0" items="${command.roles}">
			  	<option value="${role.key}">${role.value}</option>
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
		         	  	<table width="100%" border="0" align="left" valign="top" cellspacing="0" cellpadding="0">
 						 <tr>
   							<td width="15%"><div class="row"><div class="label"><label for="fromAddress">From Address</label></div></div></td>
  							<td width="85%"><form:input path="fromAddress" size="40"/></td>
 						 </tr>
 						 <tr>
   							<td width="15%"><div class="row"><div class="label"><label for="recipients">Recipients</label></div></div></td>
   							<td>
   							<table border="0" cellspacing="2" cellpadding="0" width="80%">
   							<tr>
   								<td width="49%">
   									<div cssStyle="width:100%">
   										  Click to add an email <a href="javascript:{insertRecipient('direct')}" >
   										<img align="add-role-image" style="border: 0px none ;" src="/caaers/images/rule/add_condition.gif" />
   										</a>
   									</div>
   								</td>
							   <td width="1" class="divider" rowspan="2"><div><img src="/caaers/images/chrome/spacer.gif" height="100%" width="1px"/></div></td>								<td width="49%">
   								<div cssStyle="width:100%">
   									Click to add a role <a href="javascript:{insertRecipient('role')}" >
   									<img align="add-role-image" style="border: 0px none ;" src="/caaers/images/rule/add_condition.gif" />
   									</a> 
   								</div>
   								</td>
   							</tr>
   							 <tr>
   								<td width="49%">
   									<div id="div_recipient">
   									<c:forEach var="email" items="${command.directRecipient}">
   								    	<div id="div_recipient_direct_x" >
  											<input type="text" name="directRecipient" id="email" value="${email}" size="40"/>
											<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" 
                							id="remove-action-image" style="border: 0px none ;" 
                       					 	onClick="javascript:{removeRecipient(this.parentNode)}"/>
    									</div> 
   									</c:forEach>    
   									<span id="recipientHolder_direct"></span>
   									</div>
   								</td>
   								
   								<td width="49%">
   									<c:forEach var="selectedRole" items="${command.roleRecipient}">
   										<div id="div_recipient_role_x" >
			  							<select name="roleRecipient" id="recipient_role">
			  								<option value="">Select a Role</option>
			  								<c:forEach var="role" begin="0" items="${command.roles}">
			  									<option value="${role.key}" ${selectedRole == role.key ? 'SELECTED' : ''}>${role.value}</option>
			  								</c:forEach>
      		  							</select>
	      								<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" 
            								id="remove-action-image" style="border: 0px none ;" 
                   						 	onClick="javascript:{removeRecipient(this.parentNode)}"/>
    									</div>
   									</c:forEach>
   									<span id="recipientHolder_role"></span>
   							    </td>
   							 </tr>
   						    </table>
   						    </td>
 						 </tr>
 						 <tr>
 						 <td></td>
 						 <td>
 						 	<div id="div_my_select" style="padding-top: 2px">
   								Select a substitution to insert it in the editor <select name="substitutions" id="substitutions" onchange="insertReplacement(this)">
   										<option value="">Substitution....</option>
   										<option value="nCIProtocolNumber">NCI Protocol Number</option>
   										<option value="ticketNumber">Ticket Number</option>
   										<option value="patientId">Patient ID</option>
   										<option value="amendmentNumber">Amendment Number</option>
   										<option value="reportId">Report ID</option>
   										<option value="reportURL">URL To Report</option>
   										<option value="study.shortTitle">Study Short Title</option>
   										<option value="study.primaryIdentifier.value">Study Primary Identifier</option>
   									</select>
   							</div>
 						 </tr>
 						 </tr>
 						 <tr>
   							<td width="15%"><div class="row"><div class="label"><label for="subject">Subject Line</label></div></div></td>
   							<td width="85%"><form:input path="subjectLine" size="100" cssStyle="width:96%;" onfocus="lastElement = this;" /></td>
 						 </tr>
 						 <tr>
   							<td width="15%"  valign="top">
   							<div class="row"><div class="label"><label for="message">Message</label></div></div>
   							</td> 
   							<td width="85%">
   								<div id="div_my_editor">
   									<form:textarea cssStyle="width:96%; height:300px"  path="message" onfocus="lastElement = this;"/>
   								</div>
   							</td>
   						 </tr>
   						 <tr><td><img src="/caaers/images/chrome/spacer.gif" height="100%" width="1px"/></td></tr>
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
       		
</body>

</html>
