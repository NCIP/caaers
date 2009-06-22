<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="labs" type="java.util.List" required="true" %>

<script language="JavaScript1.2">
    var vis = 0;
</script>

<c:set var="vis" value="0" />
<c:forEach items="${labs}" var="alab" varStatus="status">
	<c:if test="${alab.dismissed == false}">
		<script language="JavaScript1.2">vis++;</script>
		<c:set var="vis" value="1" />
	</c:if>
</c:forEach>

<div>
	<c:if test="${vis > 0}">
		<table width="290px" cellspacing="0" cellpadding="1" style="font-size:10px;" border="0" id="alertTable">
			<tr>
				<td><b>Lab</b></td>
				<td><b>Date</b></td>
				<td><b>Value</b></td>
				<td><b>Unit</b></td>
				<td></td>
			</tr>
			<div id="alertTableContents">
				<c:forEach items="${labs}" var="alab" varStatus="status">
					<c:if test="${!alab.dismissed}">
						<tr id="LAB_${alab.id}" onMouseOver="bgColor='yellow'" onMouseOut="bgColor='#eeeeee'">
							<td>${alab.name}</td>
   		                 	<td>${fn:substring(alab.labDate, 0, 10)}</td>
   	   		              	<td>${alab.result}</td>
   	   		              	<td>${alab.units}</td>
           		         	<td align="center"><a onClick="dismissLab(${alab.id})" style="cursor:pointer;"><img src="<c:url value="/images/checkno.gif" />" border="0"></a></td>
                		</tr>                           
					</c:if>
				</c:forEach>
			</div>	
		</table>
	</c:if>
	<c:if test="${vis == 0}">
		<tags:message key="message_no_labs_data" />
	</c:if>
</div>
<div id="noData" style='display:none'>
	<tags:message key="message_no_labs_data" />
</div>

<script language="JavaScript1.2">
	function dismissLab(labId) {
		if (confirm("Are you sure ?")) {
			createAE.dismissLab(labId);
			$('LAB_' + labId).style.display = "none";
			vis--;
			if(vis == 0) {
				document.getElementById('alertTable').hide();
				document.getElementById('noData').style.display = '';
			}
		}
	}
</script>       