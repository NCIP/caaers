<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@taglib prefix="report" tagdir="/WEB-INF/tags/report"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:dwrJavascriptLink objects="reportDef"/>
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
		
	
   
	<script language="javascript" type="text/javascript"><!--
		Event.observe(window, "load", function() {
			//to add new notification section
			Event.observe($('add-nf-section-button'), "click", function(evnt){
				index = $$('.nf-section').length;
				scaleIndex = $F('pointOnScale');
				reportDef.addNotification(index ,scaleIndex, function (html){
				 new Insertion.Before('nfbookmark', html);
				 AE.slideAndShow('nf-' + index);
				});
			})
		});
		
		//to add new recipients
		function insertRecipient(rType, index){
			var newDiv = $('div_recipient_'+rType).cloneNode(true);
			
			newDiv.id = newDiv.id + '_1'; //change the id to avoid confilict
			var inputNode = newDiv.getElementsByClassName('rInput')[0];
			if(rType == 'role'){
				inputNode.name = 'emailNotifications[' + index + '].roleBasedRecipients';
				var insertLoc = $('rbookmark'+ index);
				insertLoc.parentNode.insertBefore(newDiv,insertLoc);
				newDiv.show();
			}else{
			   	inputNode.name = 'emailNotifications[' + index + '].contactMechanismBasedRecipients';
			   	var insertLoc = $('ebookmark'+ index);
				insertLoc.parentNode.insertBefore(newDiv,insertLoc);
				newDiv.show();
			}
			
		}
		
		//removes a recipient
		function removeRecipient(aDiv){
			var frm = $('command');
			aDiv.parentNode.removeChild(aDiv);
		}
		
		function selectTimeScaleUnit(tsu){
			var frm = $('command');
			frm.elements['pointOnScale'].value = tsu;
			var target = $('_target');
			target.name = '_noname';
			frm.submit();
		}
		
		//This is the function that will insert the substitutions
		var FREE_MARKER_PREFIX = '$' + '{';
		var FREE_MARKER_SUFFIX = '}';
		
		
		var lastElement; //will store the last element on focus
		function insertReplacement(selSub, nfIndex){
			i = selSub.selectedIndex;
			if(i < 0) return;
			if(!lastElement){
			  selSub.selectedIndex = 0;
			  return;
			}
			
			txtToInsert = FREE_MARKER_PREFIX + selSub.options[i].value 
				  + FREE_MARKER_SUFFIX;
			
			var msg = lastElement.value;
			//check if the last element belongs to the correct box.
			if(lastElement.name.indexOf('emailNotifications[' + nfIndex +']') < 0){
			   selSub.selectedIndex = 0;
			   return;
			}
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
		function deleteNotification(index){
			var frm = $('command');
			frm.elements['indexToDelete'].value = index;
			frm.elements['_action'].value = 'delete';
			var target = $('_target');
			target.name = '_noname';
			frm.submit();
		}
		
	--></script> 
<link type="image/x-icon" href="../../../images/caaers.ico" rel="shortcut icon"/>
</head>
<body>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
  	 <tr>
    	<td>
    		<caaers:message code="header.reportdefinition.notifications" arguments="${command.reportDefinition.name},${command.reportDefinition.organization.name}" />
    	  	<!-- This box contains the Scale -->
    		<chrome:box title="Time Scale" id="timescale" style="width:100%" autopad="true">
   				<p>
                <tags:instructions code="rulenotifications" />
                </p>
   				
   				<table  border="0" cellpadding="0" cellspacing="0" bordercolor="#0066CC" width="98%">
   					<tr align="center" valign="middle"> 
   						<td align="left" width="80"> ${command.reportDefinition.timeScaleUnitType}</td>
   						<c:forTokens delims="," var="tsUnit" 
   						             items="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28">
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
			  <select name="roleRecipient" id="recipient_role" class="rInput">
			  <c:forEach var="role" begin="0" items="${command.roles}">
			  	<option value="${role.key}">${role.value}</option>
			  </c:forEach>
      		  </select>
	      	<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" id="remove-action-image" style="border: 0px none ;" onClick="javascript:{removeRecipient(this.parentNode)}"/>
    	</div>
		<!-- hidden direct recipient div -->
		<div id="div_recipient_direct" style="display: none">
  			<input type="text" name="directRecipient" id="email" size="40" class="rInput"/>
			<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" id="remove-action-image" style="border: 0px none ;" onClick="javascript:{removeRecipient(this.parentNode)}"/>
    	</div> 
    	<div id="div_matter">
	   			<!-- This box contains the body-->
    	
    			<tags:tabForm tab="${tab}" flow="${flow}" title="Configure Notification for ${command.reportDefinition.timeScaleUnitType} : ${command.pointOnScale}">
    			  
				  <jsp:attribute name="singleFields">
		          	<c:forEach var="curNF" items="${command.emailNotifications}"  varStatus="status">
		          	 <report:oneNotification index="${status.index}" pnf="${curNF}"/>
		          	</c:forEach>
		          	<span id="nfbookmark" />
		         	<form:hidden path="pointOnScale" />
					<form:hidden path="lastPointOnScale" />
					<form:hidden path="indexToDelete" />
					<input type="hidden" name="hideErrors"  value="false"/>
					<input type="hidden" name="_action" value=""/>

                      <br>
                      <tags:listEditorAddButton divisionClass="nf-section" label="Add Notification" />
                  </jsp:attribute>
				  <jsp:attribute name="localButtons"> 

				  </jsp:attribute>
				</tags:tabForm> 
  
    	</div> <!-- div_matter -->
    	</td>
  	 </tr>
	</table>
       		
</body>

</html>
