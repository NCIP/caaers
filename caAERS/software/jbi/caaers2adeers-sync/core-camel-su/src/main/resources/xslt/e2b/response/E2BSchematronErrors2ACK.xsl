<xsl:stylesheet version="1.0"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

    <!-- Assuming that the following Parameters are derived runtime from header -->
    <xsl:param name="c2r_msg_id" /> <!-- a UUID -->
    <xsl:param name="c2r_today_204" />
    <xsl:param name="c2r_report_received_on_102" />
    <xsl:param name="c2r_msg_number" />
    <xsl:param name="c2r_msg_date" />
    <xsl:param name="c2r_msg_sender_id" />
    <xsl:param name="c2r_msg_receiver_id" />
    <xsl:param name="report_id" />
    
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes"/>

    <xsl:template match="/">
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
                <messagesenderidentifier><xsl:value-of select="$c2r_msg_receiver_id" /></messagesenderidentifier>
                <messagereceiveridentifier><xsl:value-of select="$c2r_msg_sender_id" /></messagereceiveridentifier>
                <messagedateformat>204</messagedateformat>
                <messagedate><xsl:value-of select="$c2r_today_204" /></messagedate>
                <!-- Date on which ack got created : in CCYYMMDDHHMMSS format-->
            </ichicsrmessageheader>
            <acknowledgment>
                <messageacknowledgment>
                    <icsrmessagenumb><xsl:value-of select="$c2r_msg_number" /></icsrmessagenumb>
                    <!-- obtained as part of the input safety report message -->
                    <localmessagenumb></localmessagenumb>
                    <!-- optional, might not be present if there was processing error. This number was assigned to the input safety Message by caAERS -->
                    <icsrmessagesenderidentifier><xsl:value-of select="$c2r_msg_sender_id" /></icsrmessagesenderidentifier>
                    <icsrmessagereceiveridentifier><xsl:value-of select="$c2r_msg_receiver_id" /></icsrmessagereceiveridentifier>
                    <icsrmessagedateformat>204</icsrmessagedateformat>
                    <icsrmessagedate><xsl:value-of select="$c2r_msg_date" /></icsrmessagedate>
                    <transmissionacknowledgmentcode>02</transmissionacknowledgmentcode>
                    <parsingerrormessage><xsl:value-of select="//payload" /></parsingerrormessage> 
                </messageacknowledgment>
            </acknowledgment>
        </ichicsrack>
    </xsl:template>
	
</xsl:stylesheet>