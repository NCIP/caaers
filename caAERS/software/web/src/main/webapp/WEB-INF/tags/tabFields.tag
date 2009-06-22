<%@attribute name="tab" required="true" type="gov.nih.nci.cabig.ctms.web.tabs.Tab" %>
<input type="hidden" name="_target${tab.targetNumber}" id="_target"/>
<input type="hidden" name="_page" value="${tab.number}" id="_page"/>
