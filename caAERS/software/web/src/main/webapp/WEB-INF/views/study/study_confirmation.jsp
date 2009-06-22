<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Confirmation</title>
<style type="text/css">
  /* TODO: all these are temporary */
  #confirmbox {  width: 98%;}
</style>
 
</head>
<body>
<chrome:box title="Confirmation"  id="confirmbox">
<chrome:division id="single-fields" style="width:100%">
      <div class="content">
           <div class="row">
               <div>The following study is saved successfully</div>
           </div>
            <div class="row">
              <div class="label">Short Title:</div>
              <div class="value">${command.study.shortTitle}</div>
           </div>
           <div class="row">
              <div class="label">Primary Identifier:</div>
              <div class="value">${command.study.primaryIdentifier}</div>
           </div>
           <div class="row">
              <div class="label">Coordinating center:</div>
              <div class="value">${command.study.studyCoordinatingCenter.organization.name}</div>
           </div>
           <div class="row">
              <div class="label">Funding sponsor:</div>
              <div class="value">${command.study.primaryFundingSponsorOrganization.name}</div>
           </div>
           <div class="row">
              <div class="label">Phase:</div>
              <div class="value">${command.study.phaseCode}</div>
           </div>
           <div>
 			<img src="/caaers/images/chrome/spacer.gif" width="900" height="1" />
 		  </div>
       </div>
</chrome:division>
</chrome:box>
</body>
</html>
