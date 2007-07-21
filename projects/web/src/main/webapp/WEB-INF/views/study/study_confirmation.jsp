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
</head>
<body>
<chrome:box title="Confirmation">
<form:form>

<chrome:division id="single-fields">
      <div class="content">
           <div class="row">
               <div>The following study is saved successfully</div>
           </div>
            <div class="row">
              <div class="label">Short Title:</div>
              <div class="value">${command.shortTitle}</div>
           </div>
           <div class="row">
              <div class="label">Primary Identifier:</div>
              <div class="value">${command.primaryIdentifier}</div>
           </div>
           <div class="row">
              <div class="label">Sponsor:</div>
              <div class="value">${command.primarySponsorCode}</div>
           </div>
           <div class="row">
              <div class="label">Phase:</div>
              <div class="value">${command.phaseCode}</div>
           </div>
           <div>
 			<img src="/caaers/images/chrome/spacer.gif" width="400" height="1" />
 		  </div>
       </div>
</chrome:division>
</form:form>
</chrome:box>
</body>
</html>
