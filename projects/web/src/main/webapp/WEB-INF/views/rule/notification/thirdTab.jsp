<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <title>Not implemented</title>
  
    <script language="javascript">
    	function deleteEntity(iots, entity){
    		var frm = $('command');
			frm.elements['delete'].value = iots;
			frm.elements['entity'].value = entity;
			var target = $('_target');
			target.name = '_target3';
			var finish = $('markFinish');
			finish.name='xyz';
			frm.submit();
    	}
    	 
    </script>
</head>
<body>
    
    <chrome:division title="Review Notification(s)">
    <tags:tabForm tab="${tab}" flow="${flow}" >
    <jsp:attribute name="instructions">
    	Please review the details furnished below, then press save to persist the modifications.
    </jsp:attribute>
	<jsp:attribute name="singleFields">
		  
		  <tags:errors path="*"/>
		  
		<!-- Basic Details -->
		<chrome:division title="Basic Details">
		  <c:forEach items="${FIELDS['Basic Details']}" var="pair">
		    <rd:renderPair pair="${pair}" />
		  </c:forEach>
		</chrome:division>
		
		<!-- Delivery Details -->
		<chrome:division title="Report Delivery Definition(s)">
		  <c:forEach items="${FIELDS.rddKeySet}" var="rddKey" varStatus="status">
			<chrome:division title="Delivery Definition : ${status.index + 1}">
		    	<c:forEach items="${FIELDS['Report Delivery Definition'][rddKey]}" var="pair" >
		    		<rd:renderPair pair="${pair}" />
			    </c:forEach>
			     <div class="content buttons autoclear">
    				<div class="local-buttons">
			    		<input type="button" name="delRdd" onClick="javascript:deleteEntity(${status.index},'reportdeliverydefinition');" value="Delete Delivery Definition : ${status.index + 1}" />
			    	</div>
			    </div>
		    </chrome:division>
		  </c:forEach>
		</chrome:division>
		 
		<!-- Notification Details -->	
		<chrome:division title="Notification(s)">
		  <c:forEach items="${FIELDS.pnfKeySet}" var="pnfKey" varStatus="pnfStatus">
			<chrome:division title="Notification for ${command.reportDefinition.timeScaleUnitType.displayName} : ${command.reportDefinition.plannedNotifications[pnfStatus.index].indexOnTimeScale}">
		    	<c:forEach items="${FIELDS['Planned Notification'][pnfKey]}" var="pair" >
		    		<rd:renderPair pair="${pair}" preformatedValue="${pair.key eq 'Message'}"/>
			    </c:forEach>
			    <div class="content buttons autoclear">
    				<div class="local-buttons">
			    		<input type="button" name="del" onClick="javascript:deleteEntity(${command.reportDefinition.plannedNotifications[pnfStatus.index].indexOnTimeScale},'notification');" value="Delete Notification of ${command.reportDefinition.timeScaleUnitType.displayName} : ${command.reportDefinition.plannedNotifications[pnfStatus.index].indexOnTimeScale}" />
			    	</div>
			    </div>
		    </chrome:division>
		  </c:forEach>
		</chrome:division>
        <input id="markFinish" type="hidden" name="_finish"/>
        <input type="hidden" name="lastPointOnScale" value="" />
        <input type="hidden" name="delete" value="" /> 
        <input type="hidden" name="entity" value="" />
	</jsp:attribute>
</tags:tabForm> 
</chrome:division>
</body>
</html>