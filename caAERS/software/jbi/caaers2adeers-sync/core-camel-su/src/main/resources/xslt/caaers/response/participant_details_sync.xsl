<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
                xmlns:par="http://schema.integration.caaers.cabig.nci.nih.gov/participant"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
                
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    
    <xsl:template match="/">
    	<xsl:text disable-output-escaping="yes">errMessage=</xsl:text>  
    	
    	<xsl:if test="soapenv:Envelope/soapenv:Body/soapenv:Fault">
    		<Response>
           	   <xsl:attribute name="IsTransactionSuccessful">0</xsl:attribute>
	           <xsl:attribute name="ReasonCode">
				    <xsl:value-of select="soapenv:Envelope/soapenv:Body/soapenv:Fault/faultcode" />
				</xsl:attribute>
				<xsl:attribute name="ErrorClientResponseMessage">
				    <xsl:value-of select="soapenv:Envelope/soapenv:Body/soapenv:Fault/faultstring" />
				</xsl:attribute>
       	 	</Response>
       	</xsl:if>
       	<xsl:if test="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse">
	        <xsl:choose>
				<xsl:when test="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse/wsError">
					<Response>
		           	   <xsl:attribute name="IsTransactionSuccessful">0</xsl:attribute>
			           <xsl:attribute name="ReasonCode">
						    <xsl:value-of select="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse/wsError/errorCode" />
						</xsl:attribute>
						<xsl:attribute name="ErrorClientResponseMessage">
						    <xsl:value-of select="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse/wsError/errorDesc" />
						</xsl:attribute>
	            	</Response>
				</xsl:when>
				<xsl:otherwise>
					<Response>
		           	   <xsl:attribute name="IsTransactionSuccessful">1</xsl:attribute>
			           <xsl:attribute name="ReasonCode">WS_PMS_006</xsl:attribute>
						<xsl:attribute name="ErrorClientResponseMessage">
						    <xsl:value-of select="soapenv:Envelope/soapenv:Body/par:createParticipantResponse/com:CaaersServiceResponse/com:ServiceResponse/message" />
						</xsl:attribute>
	            	</Response>
				</xsl:otherwise>
			</xsl:choose> 
		</xsl:if>
		
    </xsl:template>
            
 
                

</xsl:stylesheet>