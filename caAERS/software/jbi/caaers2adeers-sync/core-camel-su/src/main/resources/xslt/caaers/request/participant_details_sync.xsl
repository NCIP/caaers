<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:par="http://schema.integration.caaers.cabig.nci.nih.gov/participant"
	version='2.0'>
	
	<xsl:output method="xml" indent="yes"/>
	<xsl:variable name="map" select="document('lookup.xml')"/>
	
	<xsl:template match="/">
		<soapenv:Envelope>
			<soapenv:Body>
				<par:createParticipant>
					<par:participants>
						<par:participant>
							 <xsl:variable name="sub" select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="PT_INITIALS_NAME"]/@Value'/>
							 <xsl:if test="substring($sub,1,1) !='-' ">
								<firstName>
									<xsl:value-of
										select='substring($sub,1,1)' />
								</firstName>
							</xsl:if>
							<xsl:if test="substring($sub,2,1) !='-' ">
								<lastName>
									<xsl:value-of
										select='substring($sub,2,1)' />
								</lastName>
							</xsl:if>
							<xsl:if test="substring($sub,3,1) !='-' ">
								<middleName>
									<xsl:value-of
											select='substring($sub,3,1)' />
								</middleName>
							</xsl:if>
							<birthDate>
								 <xsl:variable name="birthDate" select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="PER_BIR_DT"]/@Value'/>
								 <xsl:variable name="vDay" select="substring-before($birthDate, ' ')"/>

							     <xsl:variable name="vMonthName" select=
							     "substring-before(substring-after($birthDate, ' '), ' ')"/>
							
							     <xsl:variable name="vYear" select=
							     "substring-after(substring-after($birthDate, ' '), ' ')"/>
							     
							     <xsl:variable name="vMonth"><xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//months" /><xsl:with-param name="_code" select="$vMonthName" /></xsl:call-template></xsl:variable>
							
							     <xsl:value-of select=
							      "concat($vYear,'-',$vMonth,'-',$vDay)"/>
								
							</birthDate>
							<gender>
								<xsl:call-template name="lookup">
									<xsl:with-param name="_map" select="$map//genders" />
									<xsl:with-param name="_code" select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="PERSON_GENDER"]/@Value' />
								</xsl:call-template>
							</gender>
							<race>
								<xsl:call-template name="lookup">
									<xsl:with-param name="_map" select="$map//races" />
									<xsl:with-param name="_code" select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="RACE_CAT_TXT"]/@Value' />
								</xsl:call-template>
							</race>
							<ethnicity>
								<xsl:value-of
									select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="ETHN_GRP_CAT_TXT"]/@Value' />
							</ethnicity>
							<identifiers>
								<par:organizationAssignedIdentifier>
									<type>MRN</type>
									<value>
										<xsl:value-of
											select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="SUBJECT"]/FormData[@FormOID="SUBJECT_ENROLLMENT"]/ItemGroupData[@ItemGroupOID="SUBJECT_ENROLLMENT"]/ItemData [@ItemOID="PT_ID"]/@Value' />
									</value>
									<primaryIndicator>true</primaryIndicator>
									<par:organization>
										<name>
											<xsl:value-of
												select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="SUBJECT"]/FormData[@FormOID="SUBJECT_ENROLLMENT"]/ItemGroupData[@ItemGroupOID="SUBJECT_ENROLLMENT"]/ItemData [@ItemOID="CURRENT_SITE_ID"]/@Value' />
										</name>
										<nciInstituteCode>
											<xsl:value-of
												select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="SUBJECT"]/FormData[@FormOID="SUBJECT_ENROLLMENT"]/ItemGroupData[@ItemGroupOID="SUBJECT_ENROLLMENT"]/ItemData [@ItemOID="CURRENT_SITE_ID"]/@Value' />
										</nciInstituteCode>
									</par:organization>
								</par:organizationAssignedIdentifier>
							</identifiers>
							<assignments>
								<par:assignment>
									<studySubjectIdentifier>
										<xsl:value-of select='ODM/ClinicalData/SubjectData/@SubjectKey' />
									</studySubjectIdentifier>
									<par:studySite>
										<par:study>
											<identifiers>
												<identifier>
													<type>Protocol Authority Identifier</type>
													<value>
														<xsl:value-of select='ODM/ClinicalData/@StudyOID' />
													</value>
												</identifier>
											</identifiers>
										</par:study>
										<par:organization>
											<name><xsl:value-of
													select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="SUBJECT"]/FormData[@FormOID="SUBJECT_ENROLLMENT"]/ItemGroupData[@ItemGroupOID="SUBJECT_ENROLLMENT"]/ItemData [@ItemOID="CURRENT_SITE_ID"]/@Value' />
											</name>
											<nciInstituteCode>
												<xsl:value-of
													select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="SUBJECT"]/FormData[@FormOID="SUBJECT_ENROLLMENT"]/ItemGroupData[@ItemGroupOID="SUBJECT_ENROLLMENT"]/ItemData [@ItemOID="CURRENT_SITE_ID"]/@Value' />
											</nciInstituteCode>
										</par:organization>
									</par:studySite>
								</par:assignment>
							</assignments>
						</par:participant>
					</par:participants>
				</par:createParticipant>
			</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>
	
	<xsl:template name="lookup">
        <xsl:param name="_map" />
        <xsl:param name="_code" />        
        <xsl:value-of select="$_map//code[text() = $_code]/parent::node()/value"/>
    </xsl:template>
    
</xsl:stylesheet>
