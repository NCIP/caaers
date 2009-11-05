<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    	
    	Event.observe(window, "load", function() {
    		//remove the query string from form url
	    	removeQueryStringFromForm('command');	
    	});
    	 
    </script>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" willSave="false" >
    <jsp:attribute name="instructions">
    	<tags:instructions code="rulereview" />
    </jsp:attribute>
	<jsp:attribute name="singleFields">
		  
		  <tags:errors path="*"/>
		  
		<!-- Basic Details -->
		<chrome:division title="Rule Set Details">
		  
		  <div class="content">
			<div class="row " >
   				 <div class="label">Rule Set Level</div>
   				 <div class="value" >
					${command.levelDescription}	
    			</div>
			</div>
 			<div class="row " >
   				 <div class="label">Rule Set Name</div>
   				 <div class="value" >
					${command.ruleSetName}		
    			</div>
			</div>
			<div class="row " >
   				 <div class="label">Organization Name</div>
   				 <div class="value" >
					${command.sponsorName}	
					${command.institutionName}
    			</div>
			</div>
			<div class="row " >
   				 <div class="label">Organization Role</div>
   				 <div class="value" >
   				 	<c:if test="${not empty command.sponsorName}">
   				 		Sponsor
   				 	</c:if>
   				 	<c:if test="${not empty command.institutionName}">
   				 		Institution
   				 	</c:if>						
    			</div>
			</div>
			
			<c:if test="${not empty command.categoryIdentifier}">
				<div class="row " >
	   				 <div class="label">Study</div>
	   				 <div class="value" >
	   				 			${command.categoryIdentifier}
	    			</div>
				</div>	
			</c:if>		

 	</div>

		</chrome:division>
			<c:forEach var="rule" items="${command.ruleSet.rule}">
	        		<chrome:division title="${rule.metaData.name}">
	        			<c:forEach var="line" items="${rule.readableRule.line}" varStatus="lineStatus">
	        				<c:choose>
								<c:when test="${lineStatus.index % 2 == 0}">
									<div class="row">
									<div class="label">${line}</div>
								</c:when>
								<c:otherwise>
									<div class="value">${line}</div>
									</div>
								</c:otherwise>
							</c:choose>
	        			</c:forEach>	
	        		
	        		<div class="row">
	        			<div class="label">
	        				Actions
	        			</div>
                        <div class="value">
                            <c:forEach var="ruleac" items="${rule.readableAction}">
                                <c:choose>
                                    <c:when test="${ruleac == 'IGNORE'}">
                                        No Report Required (Study Level Exception Rule) 
                                        <br/>
                                    </c:when>
                                    <c:otherwise>
                                        ${ruleac}
                                        <br>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
	        		</div>
					</chrome:division>
				</c:forEach>
		
        <input id="markFinish" type="hidden" name="_finish"/>

	</jsp:attribute>
	<jsp:attribute name="tabControls">
		<div class="content buttons autoclear">
          <div class="flow-buttons">
              <span class="prev">
              	<tags:button type="submit" color="blue" icon="Back" id="flow-prev" cssClass="tab1" value="Back"/>
			  </span>
			  <c:if test="${command.mode == 'create'}">
				  <span class="next">
			  	  	<tags:button type="submit" color="green" icon="save" value="Save & Enable"></tags:button>
			  	  </span>
			  </c:if>
			  <c:if test="${command.mode == 'edit'}">
			  	  <span class="next">
			  	  	<tags:button type="submit" color="green" icon="save" value="Save"></tags:button>
			  	  </span>
			  </c:if>
          </div>
      </div>
	</jsp:attribute>
</tags:tabForm> 

</body>
</html>