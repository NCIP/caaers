<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<tags:noform>
    <rd:oneReportDeliveryDefinition index="${index}" rdd="${command.reportDefinition.deliveryDefinitions[index]}" style="display: none" />
</tags:noform>