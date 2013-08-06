<xsl:stylesheet version="1.0"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ctep="http://types.ws.adeers.ctep.nci.nih.gov"
                xmlns:ns1="http://localhost:8080/AdEERSWSMap/services/AEReportXMLService">

    <!-- Assuming that the following Parameters are derived runtime from header -->
    <xsl:param name="c2r_msg_id" /> <!-- a UUID -->
    <xsl:param name="c2r_today_204" />
    <xsl:param name="c2r_report_received_on_102" />
    <xsl:param name="c2r_rave_icsrmessagenumb" />

    <xsl:template match="/">
		<xsl:variable name="c2r_correlation_id" select="//payload/@correlationId"/>
        <ichicsrack lang="en">
            <ichicsrmessageheader>
                <messagetype>ichicsrack</messagetype>
                <!-- always ichicsrack for ach-->
                <messageformatversion>1.1</messageformatversion>
                <!-- ich-icsrack-v1.1.dtd is used for icsr 2.1-->
                <messageformatrelease>1.0</messageformatrelease>
                <!-- release of dtd is 1.0 -->
                <messagenumb><xsl:value-of select="$c2r_msg_id" /></messagenumb>
                <!-- caAERS's unique message number, for this ACK message -->
                <messagesenderidentifier>caAERS</messagesenderidentifier>
                <messagereceiveridentifier>RAVE</messagereceiveridentifier>
                <messagedateformat>204</messagedateformat>
                <messagedate><xsl:value-of select="$c2r_today_204" /></messagedate>
                <!-- Date on which ack got created : in CCYYMMDDHHMMSS format-->
            </ichicsrmessageheader>
            <xsl:variable name="reportStatus" select="//ctep:submitAEDataXMLAsAttachmentResponse/ns1:AEReportJobInfo/reportStatus"/>
            <acknowledgment>
                <messageacknowledgment>
                    <icsrmessagenumb><xsl:value-of select="c2r_rave_icsrmessagenumb" /></icsrmessagenumb>
                    <!-- obtained as part of the input safety report message -->
                    <localmessagenumb><xsl:value-of select="//ctep:submitAEDataXMLAsAttachmentResponse/ns1:AEReportJobInfo/jobID" /></localmessagenumb>
                    <!-- optional, might not be present if there was processing error. This number was assigned to the input safety Message by caAERS -->
                    <icsrmessagesenderidentifier>Rave</icsrmessagesenderidentifier>
                    <icsrmessagereceiveridentifier>caAERS</icsrmessagereceiveridentifier>
                    <icsrmessagedateformat>204</icsrmessagedateformat>
                    <icsrmessagedate>20130530110000</icsrmessagedate>
                    <!-- Date in CCYYMMDDHHMMSS -->
                    <!--
                    01= All Reports loaded into database
                    02 = ICSR Error, not all reports loaded into the database, check section B
                    03= SGML parsing error, no data extracted
                    -->					
                    <xsl:choose>
	                    <xsl:when test="$reportStatus='SUCCESS'">
	                        <transmissionacknowledgmentcode>01</transmissionacknowledgmentcode>	
	                    </xsl:when>
	                    <xsl:otherwise>
	                        <transmissionacknowledgmentcode>02</transmissionacknowledgmentcode>
	                        <!--Optional:-->
	                        <!--<parsingerrormessage><xsl:value-of select="//ctep:submitAEDataXMLAsAttachmentResponse/ns1:AEReportJobInfo/comments"/></parsingerrormessage> -->
							<parsingerrormessage><xsl:apply-templates select="//ctep:submitAEDataXMLAsAttachmentResponse/ns1:AEReportJobInfo/jobExceptions" mode="ERR"/></parsingerrormessage>
	                    </xsl:otherwise>
                    </xsl:choose>
                </messageacknowledgment>
                <xsl:if test="$reportStatus='SUCCESS'">

                    <!--Zero or more repetitions:-->
                    <reportacknowledgment>
                        <safetyreportid><xsl:value-of select="$c2r_correlation_id" /></safetyreportid>
                        <!-- obtained from (safetyreport/safetyreportid) of the input safety report message-->
                        <!--Optional:-->
                        <safetyreportversion>2.1</safetyreportversion>
                        <!-- ICSR input message version -->
                        <!--Optional:-->
                        <localreportnumb><xsl:value-of select="//ctep:submitAEDataXMLAsAttachmentResponse/ns1:AEReportJobInfo/ctep:REPORT_ID" /></localreportnumb>
                        <!-- can be Database ID of DC or Report (if multiple reports possible per DC)-->
                        <!-- ****** *******************************************
                        authoritynumb or companynumb     only one of them is allowed in the incoming message and should follow a specific pattern.
                        Note :- AdEERS ticket number do not follow the country-company-message-id pattern.
                        ****** *********************************** -->
                        <!--Optional:-->
                        <!--<authoritynumb>authority number</authoritynumb>-->
                        <!-- Identifier assigned by other regulatory authority for this Report-->
                        <!--Optional:-->
                        <companynumb><xsl:value-of select="//ctep:submitAEDataXMLAsAttachmentResponse/ns1:AEReportJobInfo/ticketNumber" /></companynumb>
                        <!--(Refer section B.1.5) Not sure, either this can be Case number or AdEERS ticket number. -->
                        <!--Optional:-->
                        <receiptdateformat>102</receiptdateformat>
                        <!--Optional:-->
                        <receiptdate><xsl:value-of select="$c2r_report_received_on_102" /></receiptdate>
                        <!-- Date on which input safety reprot message was received  : in CCYYMMDD format-->
                        <!--
                        01 = Report Loaded Successfully
                        02 = Report Not Loaded
                        -->
                        <reportacknowledgmentcode>01</reportacknowledgmentcode>
                        <!--Optional:-->
                        <!--<errormessagecomment>No comments or error</errormessagecomment>-->
                    </reportacknowledgment>
                </xsl:if>

            </acknowledgment>
        </ichicsrack>
    </xsl:template>
	
	<xsl:template match="//ctep:submitAEDataXMLAsAttachmentResponse/ns1:AEReportJobInfo/jobExceptions" mode="ERR">
		<xsl:value-of select="concat(code,' -' ,description, ', ')"/>
	</xsl:template>

</xsl:stylesheet>