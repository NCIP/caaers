<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="pnf"  type="gov.nih.nci.cabig.caaers.domain.report.PlannedNotification" %>
<%@attribute name="style"%>
<chrome:minimizableBox title="Email Notification ${index + 1} " 
id="nf-${index}" cssClass="nf-section" autopad="true">
<div class="row">
 <div class="label"><label for="recipients">Recipients</label></div>
  <div class="value">
    <table border="0" cellspacing="2" cellpadding="0" width="90%">
      <tr align="middle">
         <td width="49%"><input type="button" value="Add eMail" onClick="javascript:insertRecipient('direct', ${index})" /> </td>
         <td rowspan="2" width="2%" class="divider">&nbsp;</td>
         <td><input type="button" value="Add Role" onClick="javascript:insertRecipient('role',${index})" /></td>
      </tr>
      <tr>
         <td width="49%" >
          <c:forEach var="email" items="${pnf.contactMechanismBasedRecipients}">
   			<div>
  			<input name="emailNotifications[${index}].contactMechanismBasedRecipients" id="email" value="${email.contact}" size="40"/>
			<input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" 
        		 style="border: 0px none ;" onClick="javascript:{removeRecipient(this.parentNode)}"/>
    		</div> 
   		 </c:forEach>
          <span id="ebookmark${index}" />
         </td>
         <td>
          <c:forEach var="selectedRole" items="${pnf.roleBasedRecipients}">
   		  <div>
		   <select name="emailNotifications[${index}].roleBasedRecipients" id="recipient_role">
			 <option value="">Select a Role</option>
			  <c:forEach var="role" begin="0" items="${command.roles}">
			  	<option value="${role.key}" ${selectedRole.contact == role.key ? 'SELECTED' : ''}>${role.value}</option>
			  </c:forEach>
      	   </select>
	       <input type="image" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" 
           		style="border: 0px none ;" 	onClick="javascript:{removeRecipient(this.parentNode)}"/>
    	  </div>
   	      </c:forEach>
   	      <span id="rbookmark${index}" /> 
         </td>
      </tr>
    </table>
  </div>
</div>
<div class="row">
 <div class="label">Insert a substitution variable</div>
 <div class="value">
  <select name="substitutions" id="substitutions" onchange="insertReplacement(this, ${index})">
   <option value="">Substitution....</option>
   <%--
    <option value="nCIProtocolNumber">NCI Protocol Number</option> 
    <option value="ticketNumber">Ticket Number</option>
    <option value="amendmentNumber">Amendment Number</option>
    <option value="reportId">Report ID</option>
   --%>
   
   <option value="patientId">Patient ID</option>
   <option value="reportURL">URL To Report</option>
   <option value="study.shortTitle">Study Short Title</option>
   <option value="study.primaryIdentifier.value">Study Primary Identifier</option>
  </select>
 </div>
</div>
<div class="row">
 <div class="label"><tags:requiredIndicator/><label for="subject">Subject Line</label></div>
 <div class="value"><form:input path="emailNotifications[${index}].subjectLine" size="100" cssStyle="width:96%;" onfocus="lastElement = this;" /></div>
</div>
<div class="row">
 <div class="label"><tags:requiredIndicator/><label for="message">Message</label></div>
 <div class="value"><form:textarea cssStyle="width:96%; height:300px"  path="emailNotifications[${index}].notificationBodyContent.body" onfocus="lastElement = this;"/></div>
</div>
<div class="content buttons autoclear">
	<div class="local-buttons">
		<input type="button" value="Delete" onClick="javascript:deleteNotification(${index});"/>
	</div> 
</div>
</chrome:minimizableBox>