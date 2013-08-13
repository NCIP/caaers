<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<%@ page contentType="text/html;charset=iso-8859-1" language="java" %>

<html>
<head>
    <title>Summary of Rules</title>
  
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

    <tags:tabForm tab="${tab}" flow="${flow}"  saveButtonLabel="${command.createMode  ? 'Save &amp; Enable' : 'Save'}"  willSave="false" hideTabControls="${not command.ruleManager}">
    <jsp:attribute name="instructions">
    	<tags:instructions code="rulereview" />
    </jsp:attribute>
	<jsp:attribute name="singleFields">
		  
		  <tags:errors path="*"/>
		  
		<!-- Basic Details -->
		<chrome:division title="Rule Set Details">
		  
		  <div class="content">
            <div class="row " >
   				 <div class="label">Rule Set Name</div>
   				 <div class="value" >
					${command.caaersRuleSet.ruleType.name}
    			</div>
			</div>
            <c:if test="${not empty command.caaersRuleSet.ruleLevel}">
             <div class="row " >
   				 <div class="label">Rule Set Level</div>
   				 <div class="value" >
					${command.caaersRuleSet.ruleLevel.description}
    			</div>
			</div>
            <div class="row " >
                    <div class="label">Organization Name</div>
                    <div class="value" >
                    ${command.caaersRuleSet.organization.fullName}
                </div>
            </div>
            <div class="row " >
                    <div class="label">Organization Role</div>
                    <div class="value" >
                        <c:if test="${command.caaersRuleSet.ruleLevel.sponsorBased}">
                            Sponsor
                        </c:if>
                        <c:if test="${command.caaersRuleSet.ruleLevel.institutionBased}">
                            Institution
                        </c:if>
                </div>
            </div>

            </c:if>


              <c:if test="${not empty command.caaersRuleSet.study}">
                  <div class="row " >
                      <div class="label">Study</div>
                      <div class="value" >
                          (${command.caaersRuleSet.study.primaryIdentifierValue}) ${command.caaersRuleSet.study.shortTitle}
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
	
</tags:tabForm> 

</body>
</html>
