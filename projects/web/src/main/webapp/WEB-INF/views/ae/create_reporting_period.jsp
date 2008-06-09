<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
 <head>
 <tags:stylesheetLink name="tabbedflow"/>
 	 <style type="text/css">
        div.content {
            padding: 5px 15px;
        }
    </style>
 <tags:includeScriptaculous/>
  </head>
 <body>
 <br>
  <div class="content">
    <chrome:box title="Reporting Period Details">
	<form:form>
		<div class="instructions">You can add the details of the Repoting Period here.
		<br /><br />
		</div>
			<tags:hasErrorsMessage hideErrorDetails="true"/>
			<!--  JSP body here -->
			<div id="rpfields">
				<c:forEach items="${fieldGroups.ReportingPeriod.fields}" var="field">
                    <tags:renderRow field="${field}"/>
            	</c:forEach>
			</div>
			<div class="content buttons autoclear">
			  <div class="flow-buttons">
			   <span class="next">
			  	<!--  reset and save buttons -->
			  	<input type="submit" value="Save" />
			   </span>	
			  </div>
			</div>
	</form:form>
  </chrome:box>
  
  </div>
  
  </div>
 </body>
</html>