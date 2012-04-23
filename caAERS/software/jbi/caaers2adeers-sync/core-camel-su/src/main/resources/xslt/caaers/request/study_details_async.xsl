<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:stud="http://schema.integration.caaers.cabig.nci.nih.gov/study"
        version='1.0'>
    <xsl:include href="caaers_request_common.xsl" />
    <xsl:output method="xml" indent="yes" />
    <xsl:variable name="map" select="document('lookup.xml')"/>

    <xsl:template match="data">
        <stud:updateStudy>
            <xsl:apply-templates select="StudyDetails" />
        </stud:updateStudy>
    </xsl:template>

    <xsl:template match="StudyDetails">
        <stud:studies>
            <stud:study>
               <shortTitle><xsl:value-of select="title" /></shortTitle>
                <xsl:variable name="_phase" select="phase"></xsl:variable>
                <phaseCode>
                    <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//phases" /><xsl:with-param name="_code" select="$_phase" /></xsl:call-template>
                </phaseCode>
                <xsl:variable name="_status" select="currentStatus"></xsl:variable>
                <status>
                    <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//statuses" /><xsl:with-param name="_code" select="$_status" /></xsl:call-template>
                </status>
                <adeersReporting>true</adeersReporting>
                <studyPurpose>
                    <xsl:variable name="_purpose" select="primaryStudyType"></xsl:variable>
                    <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//studypurposes" /><xsl:with-param name="_code" select="$_purpose" /></xsl:call-template>
                </studyPurpose>
                <aeTermUnique>true</aeTermUnique>
                <stud:aeTerminology>
                    <stud:ctcVersion>
                        <xsl:variable name="_ctcVer" select="ctcVersion" />
                        <name>
                            <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//ctcversions" /><xsl:with-param name="_code" select="$_ctcVer" /></xsl:call-template>
                        </name>
                    </stud:ctcVersion>
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
                            <name>Cancer Therapy Evaluation Program</name>
                            <nciInstituteCode><xsl:value-of select="sponsorOrganization" /></nciInstituteCode>
                        </stud:organization>
                    </stud:studyFundingSponsor>
                </fundingSponsor>
                <xsl:apply-templates select="studyOrganizations/studyOrganization[@role = 'Document to Lead Organization']" mode="cc" />
                <xsl:apply-templates select="studyTreatmentAssignments" />
                <xsl:apply-templates select="studyAgents" />
                <xsl:apply-templates select="studyDiseases" />
                <xsl:apply-templates select="studyDevices" />
            </stud:study>
        </stud:studies>
    </xsl:template>
    <xsl:template match="studyOrganization" mode="cc">
        <xsl:if test="status/text() = 'Active'">
            <coordinatingCenter>
                <organizationAssignedIdentifier>
                    <value><xsl:value-of select="localDocumentNumber"/></value>
                </organizationAssignedIdentifier>
                <stud:studyCoordinatingCenter>
                    <xsl:apply-templates select="organization" />
                    <xsl:apply-templates select="principalInvestigator" />
                    <studyPersonnels />
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
            <studyInvestigators>
                <stud:studyInvestigator>
                    <roleCode>PI</roleCode>
                    <stud:siteInvestigator>
                        <stud:investigator>
                            <firstName><xsl:value-of select="firstname"/></firstName>
                            <lastName><xsl:value-of select="lastname" /></lastName>
                            <nciIdentifier><xsl:value-of select="@ctepInvestigatorId"/></nciIdentifier>
                        </stud:investigator>
                    </stud:siteInvestigator>
                </stud:studyInvestigator>
            </studyInvestigators>
        </xsl:if>
    </xsl:template>
    <xsl:template match="studyTreatmentAssignments">
        <treatmentAssignments>
            <xsl:apply-templates select="studyTreatmentAssignment" />
        </treatmentAssignments>
    </xsl:template>
    <xsl:template match="studyTreatmentAssignment">
        <xsl:if test="status/text() = 'Active'">
            <stud:treatmentAssignment>
                <code><xsl:value-of select="code" /></code>
                <description><xsl:value-of select="description" /></description>
            </stud:treatmentAssignment>
        </xsl:if>
    </xsl:template>
    <xsl:template match="studyDiseases">
        <ctepStudyDiseases>
            <xsl:apply-templates select="studyDisease" />
        </ctepStudyDiseases>
    </xsl:template>
    <xsl:template match="studyDisease">
        <stud:ctepStudyDisease>
            <stud:diseaseTerm>
                <!--<term><xsl:value-of select="diseaseName" /></term>-->
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
            <stud:studyAgent>
                <xsl:apply-templates select="agent"/>
                <xsl:variable name="_indType" select="commercialInvestigational" />
                <indType> 
                    <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//indtypes" /><xsl:with-param name="_code" select="$_indType" /></xsl:call-template>
                </indType>
                <xsl:variable name="_leadIndYesNo" select="@lead" />
                <partOfLeadIND>
                    <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//yesnos" /><xsl:with-param name="_code" select="$_leadIndYesNo" /></xsl:call-template>
                </partOfLeadIND>
                <xsl:apply-templates select="inds" />
            </stud:studyAgent>
        </xsl:if>
    </xsl:template>
    <xsl:template match="agent" >
        <stud:agent>
            <!--<name><xsl:value-of select="agentName"/></name>-->
            <nscNumber><xsl:value-of select="nscNumber"/></nscNumber>
        </stud:agent>
    </xsl:template>
    <xsl:template match="inds">
        <studyAgentINDAssociations>
            <xsl:apply-templates select="ind"  />
        </studyAgentINDAssociations>
    </xsl:template>
    <xsl:template match="ind">
        <stud:studyAgentINDAssociation>
            <stud:investigationalNewDrug>
                <indNumber><xsl:value-of select="indNumber" /></indNumber>
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
                <type><xsl:value-of select="parent::node()/commercialInvestigational" /></type>
                <status><xsl:value-of select="status" /></status>
            </stud:device>
        </stud:studyDevice>
    </xsl:template>
    
    <xsl:template name="lookup">
        <xsl:param name="_map" />
        <xsl:param name="_code" />
        <xsl:value-of select="$_map//code[text() = $_code]/parent::node()/value"/>
    </xsl:template>

</xsl:stylesheet>