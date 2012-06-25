<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:stud="http://schema.integration.caaers.cabig.nci.nih.gov/study"
        version='1.0'>
    <xsl:include href="caaers_request_common.xsl" />
    <xsl:param name="c2a_operation"  />
    <xsl:output method="xml" indent="yes" />
    <xsl:variable name="map" select="document('lookup.xml')"/>

    <xsl:template match="data">
        <xsl:if test="$c2a_operation = 'createStudy'">
            <stud:createStudy>
                <xsl:apply-templates select="StudyDetails" />
            </stud:createStudy>
        </xsl:if>
        <xsl:if test="$c2a_operation != 'createStudy'">
            <stud:updateStudy>
                <xsl:apply-templates select="StudyDetails" />
            </stud:updateStudy>
        </xsl:if>

    </xsl:template>

    <xsl:template match="StudyDetails">
        <stud:studies>
            <stud:study>
               <shortTitle><xsl:value-of select="title" /></shortTitle>
                <xsl:variable name="_phase" select="phase"></xsl:variable>
                <phaseCode>
                    <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//phases" /><xsl:with-param name="_code" select="$_phase" /></xsl:call-template>
                </phaseCode>
                <verbatimFirst>true</verbatimFirst>
                <adeersReporting>true</adeersReporting>
                <studyPurpose>
                    <xsl:variable name="_purpose" select="primaryStudyType"></xsl:variable>
                    <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//studypurposes" /><xsl:with-param name="_code" select="$_purpose" /></xsl:call-template>
                </studyPurpose>
                <participationType><xsl:value-of select="participationType" /></participationType>
                <aeTermUnique>true</aeTermUnique>
                <stud:aeTerminology>
                    <stud:ctcVersion><name><xsl:value-of select="ctcVersion" /></name></stud:ctcVersion>
                </stud:aeTerminology>
                <stud:diseaseTerminology>
                    <diseaseCodeTerm>CTEP</diseaseCodeTerm>
                </stud:diseaseTerminology>
                <fundingSponsor>
                    <organizationAssignedIdentifier>
                        <value><xsl:value-of select="nciDocumentNum" /></value>
                    </organizationAssignedIdentifier>
                    <stud:studyFundingSponsor>
                        <stud:organization>
                            <name><xsl:value-of select="sponsorOrganization" /></name>
                            <nciInstituteCode><xsl:value-of select="sponsorOrganization" /></nciInstituteCode>
                        </stud:organization>
                    </stud:studyFundingSponsor>
                </fundingSponsor>
                <xsl:apply-templates select="studyOrganizations/studyOrganization[@role = 'Document to Lead Organization']" mode="cc" />
                <xsl:apply-templates select="nciDocumentNum" mode="sys" />
                <xsl:apply-templates select="studyTreatmentAssignments" />
                <xsl:apply-templates select="studyAgents" />
                <xsl:apply-templates select="studyDevices" />
                <xsl:apply-templates select="studyTherapies" />
                <xsl:apply-templates select="studyDiseases" />

            </stud:study>
        </stud:studies>
    </xsl:template>
    <xsl:template match="studyOrganization" mode="cc">
        <xsl:if test="status/text() = 'Active'">
            <coordinatingCenter>
                <organizationAssignedIdentifier>
                    <xsl:variable name="ccIdVal" select="localDocumentNumber" />
                    <xsl:if test="$ccIdVal != ''">
                        <value><xsl:value-of select="$ccIdVal"/></value>
                    </xsl:if>
                    <xsl:if test="$ccIdVal = ''">
                        <value><xsl:value-of select="parent::*/parent::*/nciDocumentNum"/></value>
                    </xsl:if>
                </organizationAssignedIdentifier>
                <stud:studyCoordinatingCenter>
                    <xsl:apply-templates select="organization" />
                </stud:studyCoordinatingCenter>
            </coordinatingCenter>
        </xsl:if>
    </xsl:template>
    <xsl:template match="organization">
        <stud:organization>
            <xsl:if test="address/street">
                <descriptionText><xsl:value-of select="address/street" /></descriptionText>
            </xsl:if>
            <name><xsl:value-of select="organizationName" /></name>
            <nciInstituteCode><xsl:value-of select="ctepId" /></nciInstituteCode>
            <xsl:if test="address/city">
                <city><xsl:value-of select="address/city" /></city>
            </xsl:if>
            <xsl:if test="address/state">
                <state><xsl:value-of select="address/state" /></state>
            </xsl:if>
            <xsl:if test="address/country">
                <country><xsl:value-of select="address/country" /></country>
            </xsl:if>
            <xsl:if test="status">
                <status><xsl:value-of select="status" /></status>   
            </xsl:if>
            <xsl:if test="organizationType">
                <type><xsl:value-of select="organizationType" /></type>
            </xsl:if>
        </stud:organization>
    </xsl:template>
    <xsl:template match="principalInvestigator">
        <xsl:if test="investigatorStatus/text() = 'Active'">
        </xsl:if>
    </xsl:template>
    <xsl:template match="studyTreatmentAssignments">
        <xsl:if test="count(child::*) &gt; 0">
            <treatmentAssignments>
                <xsl:apply-templates select="studyTreatmentAssignment" />
            </treatmentAssignments>
        </xsl:if>

    </xsl:template>
    <xsl:template match="studyTreatmentAssignment">
        <stud:treatmentAssignment>
            <ctepDbIdentifier><xsl:value-of select="tacDbId" /></ctepDbIdentifier>
            <code><xsl:value-of select="code" /></code>
            <xsl:if test="normalize-space(description) != ''">
                <description><xsl:value-of select="description" /></description>
            </xsl:if>
        </stud:treatmentAssignment>
    </xsl:template>
    <xsl:template match="studyDiseases">
        <ctepStudyDiseases>
            <xsl:apply-templates select="studyDisease" />
            <xsl:if test="not(studyDisease/reportingMedDRA/text() = '10018864')">
                <stud:ctepStudyDisease>
                    <stud:diseaseTerm>
                        <term>Hematopoietic malignancy, NOS</term>
                        <meddraCode>10018864</meddraCode>
                    </stud:diseaseTerm>
                    <leadDisease>false</leadDisease>
                </stud:ctepStudyDisease>
            </xsl:if>
            <xsl:if test="not(//studyDisease/reportingMedDRA/text() = '10029000')">
                <stud:ctepStudyDisease>
                    <stud:diseaseTerm>
                        <term>Solid tumor, NOS</term>
                        <meddraCode>10029000</meddraCode>
                    </stud:diseaseTerm>
                    <leadDisease>false</leadDisease>
                </stud:ctepStudyDisease>
            </xsl:if>
        </ctepStudyDiseases>
    </xsl:template>
    <xsl:template match="studyDisease">
        <stud:ctepStudyDisease>
            <stud:diseaseTerm>
                <term><xsl:value-of select="diseaseName" /></term>
                <meddraCode><xsl:value-of select="reportingMedDRA" /></meddraCode>
            </stud:diseaseTerm>
            <xsl:variable name="_leadDisYesNo" select="@lead" />
            <leadDisease>
                <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//yesnos" /><xsl:with-param name="_code" select="$_leadDisYesNo" /></xsl:call-template>
            </leadDisease>
        </stud:ctepStudyDisease>
    </xsl:template>
    <xsl:template match="studyAgents">
        <studyAgents>
            <xsl:apply-templates select="studyAgent" />
        </studyAgents>
    </xsl:template>
    <xsl:template match="studyAgent">
        <xsl:if test="agent/agentStatus/text() = 'Active'">
            <xsl:apply-templates select="agent"/>
        </xsl:if>
    </xsl:template>
    <xsl:template match="agent" >
        <stud:studyAgent>
            <stud:agent>
                <nscNumber><xsl:value-of select="nscNumber"/></nscNumber>
                <name><xsl:value-of select="agentName"/></name>
            </stud:agent>
            <indType>
                <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//indtypes" /><xsl:with-param name="_code" select="parent::node()/commercialInvestigational" /></xsl:call-template>
            </indType>
            <xsl:variable name="_partOfLead" select="inds//ind/@lead = 'Yes'"  />
            <partOfLeadIND><xsl:value-of select="$_partOfLead" /></partOfLeadIND>
            <xsl:apply-templates select="inds" />

            <!-- special case -->
            <xsl:if test="not(inds/ind) and (parent::node()/commercialInvestigational/text() = 'Investigational') ">
                <studyAgentINDAssociations>
                    <stud:studyAgentINDAssociation>
                    </stud:studyAgentINDAssociation>
                </studyAgentINDAssociations>
            </xsl:if>
        </stud:studyAgent>
    </xsl:template>
    <xsl:template match="inds">
        <xsl:if test="ind">
            <studyAgentINDAssociations>
                <xsl:apply-templates select="ind"  />
            </studyAgentINDAssociations>
        </xsl:if>
    </xsl:template>
    <xsl:template match="ind">
        <stud:studyAgentINDAssociation>
            <stud:investigationalNewDrug>
                <indNumber><xsl:value-of select="indNumber" /></indNumber>
                <holderName><xsl:value-of select="indHolder" /></holderName>
                <xsl:if test="normalize-space(indEndDate) != ''">
                    <endDate><xsl:value-of select="indEndDate" /></endDate>
                </xsl:if>
            </stud:investigationalNewDrug>
        </stud:studyAgentINDAssociation>
    </xsl:template>
    <xsl:template match="studyDevices">
        <studyDevices>
              <xsl:apply-templates select="studyDevice" />
        </studyDevices>
    </xsl:template>
    <xsl:template match="studyDevice">
        <xsl:if test="status/text() = 'Active'">
            <xsl:apply-templates select="device" />
        </xsl:if>
    </xsl:template>
    <xsl:template match="device">
        <stud:studyDevice>
            <stud:device>
                <commonName><xsl:value-of select="commonName" /></commonName>
                <brandName><xsl:value-of select="brandName" /></brandName>
                <type><xsl:value-of select="category" /></type>
                <ctepDbIdentifier><xsl:value-of select="deviceDbId" /></ctepDbIdentifier>
                <xsl:if test="status"><status><xsl:value-of select="status" /></status></xsl:if>
            </stud:device>
            <!-- special case -->
            <xsl:if test="not(ideNumber/text()) and (parent::node()/commercialInvestigational/text() = 'Investigational') ">
                <studyDeviceINDAssociations>
                    <stud:studyDeviceINDAssociation>
                    </stud:studyDeviceINDAssociation>
                </studyDeviceINDAssociations>
            </xsl:if>

                <xsl:if test="ideNumber/text() and ideHolder/text()">
                    <studyDeviceINDAssociations>
                        <stud:studyDeviceINDAssociation>
                            <stud:investigationalNewDrug>
                                <indNumber><xsl:value-of select="ideNumber" /></indNumber>
                                <holderName><xsl:value-of select="ideHolder" /></holderName>
                                <xsl:if test="normalize-space(ideStatus) != ''">
                                    <status><xsl:value-of select="ideStatus" /></status>
                                </xsl:if>
                            </stud:investigationalNewDrug>
                        </stud:studyDeviceINDAssociation>
                    </studyDeviceINDAssociations>
                </xsl:if>

        </stud:studyDevice>
    </xsl:template>
    <xsl:template match="nciDocumentNum" mode="sys">
        <identifiers>
            <stud:systemAssignedIdentifier>
                <type>Other</type>
                <value><xsl:value-of select="." /></value>
                <primaryIndicator>false</primaryIndicator>
                <systemName>CTEP-ESYS</systemName>
            </stud:systemAssignedIdentifier>
        </identifiers>
    </xsl:template>
    <xsl:template match="studyTherapies">
        <otherInterventions>
            <xsl:apply-templates select="therapy" />
        </otherInterventions>
    </xsl:template>

    <xsl:template match="therapy">
        <xsl:if test="preferredTerm/text() = 'Surgery'
                or preferredTerm/text() = 'Radiation Therapy'
                or preferredTerm/text() = 'Gene Transfer'
                or preferredTerm/text() = 'Image Directed Local Therapy'
                or preferredTerm/text() = 'Hematopoietic Stem Cell Transplantation'">
                <stud:otherIntervention>
                    <interventionType><xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//interventions" /><xsl:with-param name="_code" select="preferredTerm" /></xsl:call-template></interventionType>
                    <name><xsl:value-of select="preferredTerm" /></name>
            </stud:otherIntervention>
            </xsl:if>

    </xsl:template>

    <xsl:template name="lookup">
        <xsl:param name="_map" />
        <xsl:param name="_code" />
        <xsl:value-of select="$_map//code[text() = $_code]/parent::node()/value"/>
    </xsl:template>

</xsl:stylesheet>