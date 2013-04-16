<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
                xmlns:par="http://schema.integration.caaers.cabig.nci.nih.gov/participant"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                exclude-result-prefixes="com par soapenv">
                
    <xsl:output method="text/xml" indent="yes" omit-xml-declaration="yes"/>
    <xsl:param name="c2a_correlation_id" />
    <xsl:template match="/">
    	<errMessage>
    		<Response>
    			<xsl:attribute name="ReferenceNumber"><xsl:value-of select="$c2a_correlation_id" /></xsl:attribute>
    			<xsl:if test="soapenv:Envelope/soapenv:Body/soapenv:Fault">
	           	   <xsl:attribute name="IsTransactionSuccessful">0</xsl:attribute>
		           <xsl:attribute name="ReasonCode">
					    <xsl:value-of select="soapenv:Envelope/soapenv:Body/soapenv:Fault/faultcode" />
					</xsl:attribute>
					<xsl:attribute name="ErrorClientResponseMessage">
					    <xsl:value-of select="soapenv:Envelope/soapenv:Body/soapenv:Fault/faultstring" />
					</xsl:attribute>
	       		</xsl:if>
		       	<xsl:if test="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse">
			        <xsl:choose>
						<xsl:when test="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse/wsError">
				           	   <xsl:attribute name="IsTransactionSuccessful">0</xsl:attribute>
					           <xsl:attribute name="ReasonCode">
								    <xsl:value-of select="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse/wsError/errorCode" />
								</xsl:attribute>
								<xsl:attribute name="ErrorClientResponseMessage">
								    <xsl:value-of select="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse/wsError/errorDesc" />
								</xsl:attribute>
						</xsl:when>
						<xsl:otherwise>
				           	   <xsl:attribute name="IsTransactionSuccessful">1</xsl:attribute>
					           <xsl:attribute name="ReasonCode">WS_PMS_006</xsl:attribute>
								<xsl:attribute name="SuccessStatistics">
								    <xsl:value-of select="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse/message" />
								</xsl:attribute>
						</xsl:otherwise>
					</xsl:choose> 
				</xsl:if>
			</Response>
		</errMessage>
    </xsl:template>
            
 
                

</xsl:stylesheet>