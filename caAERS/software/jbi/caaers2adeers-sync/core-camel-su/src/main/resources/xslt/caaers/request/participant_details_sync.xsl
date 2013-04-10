<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:par="http://schema.integration.caaers.cabig.nci.nih.gov/participant"
	version='2.0'>
	
	<xsl:output method="xml" indent="yes"/>
	
	<xsl:template match="/">
		<soapenv:Envelope>
			<soapenv:Body>
				<par:createParticipant>
					<par:participants>
						<par:participant>
							 <xsl:variable name="sub" select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="PT_INITIALS_NAME"]/@Value'/>
							<firstName>
								<xsl:value-of
									select='substring($sub,1,1)' />
							</firstName>
							<lastName>
								<xsl:value-of
									select='substring($sub,2,2)' />
							</lastName>
							<middleName>
								<xsl:value-of
										select='substring($sub,3,3)' />
							</middleName>
							<birthMonth></birthMonth>
							<birthDay></birthDay>
							<birthYear></birthYear>
							<birthDate>
								 <xsl:variable name="birthDate" select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="PER_BIR_DT"]/@Value'/>
								 <xsl:variable name="vMonth" select="substring-before($birthDate, '/')"/>

							     <xsl:variable name="vDay" select=
							     "substring-before(substring-after($birthDate, '/'), '/')"/>
							
							     <xsl:variable name="vYear" select=
							     "substring-before(substring-after(substring-after($birthDate, '/'), '/'), ' ')"/>
							
							     <xsl:value-of select=
							      "concat($vYear,'-',$vMonth,'-',$vDay)"/>
								
							</birthDate>
							<gender>
								<xsl:value-of
									select='ODM/ClinicalData/SubjectData/StudyEventData[@StudyEventOID="ENROLLMENT_FORMS"]/FormData[@FormOID="DEMOGRAPHY"]/ItemGroupData[@ItemGroupOID="DEMOGRAPHY"]/ItemData [@ItemOID="PERSON_GENDER"]/@Value' />
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
													<type>CTEP-ESYS-IDENTIFIERr</type>
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
