<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
    <head>
        <title>${tab.longTitle}</title>
        <tags:dwrJavascriptLink objects="createAE"/>
    </head>
    <body>
        <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section11reporterinformation" saveButtonLabel="Submit">
        	
			
            <jsp:attribute name="singleFields">
                The <b>${command.report.reportDefinition.name} </b>
                will be sent to the following preconfigured recipients:
                <ul>
                <c:forEach items="${command.reportDeliveries}" varStatus="status" var="delivery">
                    
                            <li>
                                ${delivery.reportDeliveryDefinition.entityType eq 1 ? delivery.reportDeliveryDefinition.entityName : delivery.endPoint }
                                <c:if test="${delivery.reportDeliveryDefinition.entityType ne 1}">
                                    (${delivery.reportDeliveryDefinition.entityName})
                                </c:if>
                            </li>
                    
                </c:forEach>
				</ul>
                <chrome:division title="CC Details">
                    <p>
                        To send this report to others, enter the email addresses in the field below.
                        <br/>
                        Multiple email addresses can be entered separated by a comma.
                    </p>
                    <c:forEach items="${fieldGroups['ccReport'].fields}" var="field">
                        <tags:renderRow field="${field}"/>
                    </c:forEach>
                </chrome:division>
				
            </jsp:attribute>
			
			
            <jsp:attribute name="tabControls">
                <div class="content buttons autoclear">
                    <div class="local-buttons">
                    </div>
                    <div class="flow-buttons">
                        <span class="prev"><tags:button type="submit" color="blue" id="flow-prev" icon="back" value="Back" cssClass="tab${tab.number -1 }"/></span><span class="next"><tags:button type="submit" color="orange" id="flow-next" icon="Save & Continue" value="Submit"/></span>
                    </div>
                </div>
            </jsp:attribute>
        </tags:tabForm>
    </body>
</html>