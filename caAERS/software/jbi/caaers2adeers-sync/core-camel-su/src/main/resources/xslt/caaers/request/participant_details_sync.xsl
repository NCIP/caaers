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
							     
							     <xsl:variable name="vMonth">
							     <xsl:choose>
							     	<xsl:when test="$vMonthName = 'Jan'">01</xsl:when>
							     	<xsl:when test="$vMonthName = 'Feb'">02</xsl:when>
							     	<xsl:when test="$vMonthName = 'Mar'">03</xsl:when>
							     	<xsl:when test="$vMonthName = 'Apr'">04</xsl:when>
							     	<xsl:when test="$vMonthName = 'May'">05</xsl:when>
							     	<xsl:when test="$vMonthName = 'Jun'">06</xsl:when>
							     	<xsl:when test="$vMonthName = 'Jul'">07</xsl:when>
							     	<xsl:when test="$vMonthName = 'Aug'">08</xsl:when>
							     	<xsl:when test="$vMonthName = 'Sep'">09</xsl:when>
							     	<xsl:when test="$vMonthName = 'Oct'">10</xsl:when>
							     	<xsl:when test="$vMonthName = 'Nov'">11</xsl:when>
							     	<xsl:when test="$vMonthName = 'Dec'">12</xsl:when>
							     </xsl:choose>
							     </xsl:variable>
							
							     <xsl:value-of select=
							      "concat($vYear,'-',$vMonth,'-',$vDay)"/>
								
							</birthDate>
							<gender>
								 <xsl:variable name="odmGender" select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="PERSON_GENDER"]/@Value'/>

							     <xsl:variable name="caAERSGender">
							     <xsl:choose>
							     	<xsl:when test="$odmGender = 'Male Gender'">Male</xsl:when>
							     	<xsl:when test="$odmGender = 'Female Gender'">Female</xsl:when>
							     	<xsl:otherwise>$odmGender</xsl:otherwise>
							     </xsl:choose>
							     </xsl:variable>
							
							     <xsl:value-of select="$caAERSGender"/>
							     
							</gender>
							<race>
								<xsl:value-of
									select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="RACE_CAT_TXT"]/@Value' />
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
	
</xsl:stylesheet>
