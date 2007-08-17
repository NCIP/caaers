<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<tags:noform>
    <rd:oneReportDeliveryDefinition index="${index}" originalIndex="${originalIndex}" rdd="${command.reportDefinition.deliveryDefinitions[originalIndex]}" style="display: none" />
</tags:noform>