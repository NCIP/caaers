<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <tags:stylesheetLink name="extremecomponents"/>
    <title>Not implemented</title>
  	<style type="text/css">
  		.label {
  			float:left;
			font-weight:bold;
			margin-left:0.5em;
			text-align:right;
  		}
  		.value {
  			padding-left:15px;
  		}
  		div.row div.label { width: 11em; } 
  	</style>
    <script language="javascript">
    	
    	 
    </script>
    <link type="image/x-icon" href="../../../images/caaers.ico" rel="shortcut icon"/>
</head>
<body>

    <tags:tabForm tab="${tab}" flow="${flow}" >
    <jsp:attribute name="instructions">
    	<tags:instructions code="createrulereview" />
    </jsp:attribute>
	<jsp:attribute name="repeatingFields">
		  <tags:errors path="*"/>
		  
		<!-- Basic Details -->
		<chrome:division title="Basic Details">
		 
		  <c:forEach items="${FIELDS['Basic Details']}" var="pair">
		    <rd:renderPair pair="${pair}" />
		  </c:forEach>
		</chrome:division>
		
		<!-- Delivery Details -->
		<chrome:division title="Report Delivery Definition(s)">
		  <table width="100%" class="tablecontent">
			<tr>
			<th width="5%" class="tableHeader">Recipient type</th>
 			 <th width="30%" class="tableHeader">Name</th>
			 <th width="55%" class="tableHeader">Address/Role</th>
			 <th width="10%" class="tableHeader">Report format</th>
			</tr>
		    <c:forEach items="${FIELDS['rdd']}" var="pair" varStatus="status">
		      <tr class="${status.index % 2 ne 0 ? 'even' : 'odd'}">
		    	<td>${pair.key}</td><td>${pair.value}</td><td>${pair.attribute1}</td><td>${pair.attribute2}</td>
			  </tr>
		    </c:forEach>
		  </table>
		  <br>
		</chrome:division>
		<!--  Mandatory Fields  -->
		<chrome:division title="Report Mandatory Fields">
		   <table width="100%" class="tablecontent">
			<tr>
				<th width="35%">Section</th>
				<th width="65%">Field</th>
			</tr>
			
			<c:forEach items="${FIELDS.mandatoryFields}" var="section">
				<tr>
					<td><div class="label">${section.key}</div></td>
					
					<td>
						<table class="tablecontent" width="100%">
							<c:forEach items="${section.value}" var="x">
								<tr>
									<td width="60%">${x.key}</td>
									<td width="40%">
									<c:if test="${x.value eq '0: Optional'}">Optional</c:if>
									<c:if test="${x.value eq '1: Mandatory'}">Mandatory</c:if>
									<c:if test="${x.value eq '-1: Not Applicable'}">Not Applicable</c:if>
									</td>

								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</c:forEach>
			
		   </table>
		</chrome:division> 
		<!-- Notification Details -->	
		<chrome:division title="Notification(s)">
		 <c:forEach items="${FIELDS.PENF}" var="entry">
		 	<chrome:division title="${entry.key}" >
		 		<c:forEach items="${entry.value}" var="pair">
		 			<rd:renderPair pair="${pair}" preformatedValue="${pair.key eq 'Message'}" />
		 		</c:forEach>
		 	</chrome:division>
		 </c:forEach>
		
		</chrome:division>
        <input id="markFinish" type="hidden" name="_finish"/>
	</jsp:attribute>
</tags:tabForm> 

</body>
</html>