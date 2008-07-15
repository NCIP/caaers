<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="standard" tagdir="/WEB-INF/tags/standard"%>

<html>
<head>
 <standard:head/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Confirmation</title>
<style type="text/css">
  #confirmbox {  width: 98%;}
</style>
<script>

	function callParentWindow(){
			window.parent.addedReportingPeriod(${command.reportingPeriod.id}, '${command.reportingPeriod.name}');
	}
</script>
</head>
<body>
<chrome:box title="Confirmation"  id="confirmbox">
<chrome:division id="single-fields" style="width:100%">
      <div class="content">
      <div><b>Reporting period details saved successfully</b></div>
      <table>
            <div class="row">
            <tr>
            <td>
              <div class="label"><b>Start date:</b></div>
            </td>
            <td>
              <div class="value">${command.reportingPeriod.startDate}</div>
            </td>
            </tr>  
           </div>
           <div class="row">
           <tr>
           <td>
              <div class="label"><b>End date:</b></div>
           </td>
           <td>
              <div class="value">${command.reportingPeriod.endDate}</div>
			</td>
			</tr>
           </div>
           <div class="row">
           <tr>
           <td>
              <div class="label"><b>Reporting Period Type:</b></div>
            </td>
            <td>
              <div class="value">${command.reportingPeriod.epoch.name}</div>
            </td>
            </tr>  
           </div>
           <div class="row">
           <tr>
           <td>
              <div class="label"><b>Description:</b></div>
           </td>
           <td>
              <div class="value">${command.reportingPeriod.description}</div>
           </td>
           </tr>
           </div>
           <div class="row">
           <tr>
           <td>
              <div class="label"><b>Cycle number:</b></div>
           </td>
           <td>
              <div class="value">${command.reportingPeriod.cycleNumber}</div>
           </td>
           </tr>
           </div>
           <div class="row">
           <tr>
           <td>
              <div class="label"><b>Treatment Assignment:</b></div>
           </td>
           <td>
              <div class="value">${command.reportingPeriod.treatmentAssignment.code}</div>
           </td>
           </tr>
           </div>
           <div class="row">
           <tr>
           <td>
              <div class="label"><b>Treatment Description:</b></div>
           </td>
           <td>
              <div class="value">${command.reportingPeriod.treatmentAssignment.description}</div>
           </td>
           </tr>
           </div>
       </table>
           <div class="content buttons autoclear">
			  <div class="flow-buttons" id="confirm_ok">
			   <span class="next">
			  	<!--  reset and save buttons -->
			  	<input type="submit" value="OK" onclick="callParentWindow()"/>
			   </span>	
			  </div>
			</div>
           <div>
 			<img src="/caaers/images/chrome/spacer.gif" width="900" height="1" />
 		  </div>
       </div>      
</chrome:division>
</chrome:box>
</body>
</html>
