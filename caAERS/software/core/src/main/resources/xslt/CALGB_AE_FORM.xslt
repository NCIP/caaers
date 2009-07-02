<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet
     version="1.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
     xmlns:fo="http://www.w3.org/1999/XSL/Format"
     xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">

  <xsl:output method="xml"/>

<xsl:attribute-set name="table.cell.padding">
  <xsl:attribute name="padding-left">20pt</xsl:attribute>
  <xsl:attribute name="padding-right">20pt</xsl:attribute>
  <xsl:attribute name="padding-top">20pt</xsl:attribute>
  <xsl:attribute name="padding-bottom">20pt</xsl:attribute>
</xsl:attribute-set>

<xsl:attribute-set name="cellText">
  <xsl:attribute name="font-family">Tahoma</xsl:attribute>
  <xsl:attribute name="font-size">8pt</xsl:attribute>
  <xsl:attribute name="font-weight">normal</xsl:attribute>
</xsl:attribute-set>
<xsl:attribute-set name="cellSmallText">
  <xsl:attribute name="font-family">Tahoma</xsl:attribute>
  <xsl:attribute name="font-size">6pt</xsl:attribute>
  <xsl:attribute name="font-weight">normal</xsl:attribute>
</xsl:attribute-set>

    <xsl:template match="/">
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <fo:layout-master-set>
        <fo:simple-page-master master-name="A4" margin-left="10mm" margin-top="10mm" margin-right="10mm" margin-bottom="10mm">
            <fo:region-body margin-top="5mm" margin-bottom="5mm"/>
            <fo:region-before extent="5mm"/>
            <fo:region-after extent="5mm"/>
        </fo:simple-page-master>
    </fo:layout-master-set>

    <fo:page-sequence master-reference="A4">

        <!-- PAGE HEADER -->
        <fo:static-content flow-name="xsl-region-before">
            <fo:block space-after="20mm" border-width="2mm" text-align="center">
                CALGB: 80405 ADVERSE EVENT (AE) FORM
            </fo:block>
        </fo:static-content>

        <!-- PAGE FOOTER -->
        <fo:static-content flow-name="xsl-region-after">
            <fo:block font-size="8pt" text-align-last="right" border-width="1mm">
                Page
                <fo:page-number/>
                of
                <fo:page-number-citation ref-id="c-END"/>
            </fo:block>
        </fo:static-content>

        <!-- PAGE BODY -->
        <fo:flow flow-name="xsl-region-body">

            <!---->
                <fo:table width="100%" border="0px solid black" >
                    <fo:table-column column-width="45%" />
                    <fo:table-column column-width="55%" />

                    <fo:table-header>
                        <fo:table-row>
                            <fo:table-cell border="0.5px gray solid"><fo:block font-weight="bold" xsl:use-attribute-sets="cellText"><xsl:value-of select="ns2:studies/ns2:study/evaluationPeriods/ns2:evaluationPeriod/descriptionText"/></fo:block></fo:table-cell>
                            <fo:table-cell border="0px gray solid"><fo:block font-weight="bold"><xsl:call-template name="CALGB_FORM"></xsl:call-template></fo:block></fo:table-cell>
                        </fo:table-row>
                    </fo:table-header>

                    <fo:table-body>
                        <fo:table-row>
                            <fo:table-cell><fo:block></fo:block></fo:table-cell>
                            <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell><fo:block></fo:block></fo:table-cell>
                            <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        </fo:table-row>
                    </fo:table-body>

                </fo:table>

                <fo:block space-after="10px"/>
                <fo:block><fo:leader leader-length="100%" leader-pattern="rule"/></fo:block>
                <fo:block space-after="10px"/>
            <!---->

            <!-- PATIENT DETAILS-->
            <!---->
            <fo:table width="100%" border="0px solid black" >
                <fo:table-column column-width="90px"/>
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="100px" />
                <fo:table-column column-width="80px"/>
                <fo:table-column />

                <fo:table-body>
                    <fo:table-row height="10.5px">
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Patient Initials</fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                    </fo:table-row>
                    <fo:table-row height="6px">
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">Last</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">First</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">Middle</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Participating Group</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block><fo:leader leader-length="100%" leader-pattern="dots"/></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                    </fo:table-row>
                    <fo:table-row height="10.5px">
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Patient Hospital Number</fo:block></fo:table-cell>
                        <fo:table-cell number-columns-spanned="6"><fo:block><fo:leader leader-length="100%" leader-pattern="dots"/></fo:block></fo:table-cell>
                    </fo:table-row>
                    <fo:table-row height="10.5px">
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Institution/Affiliate</fo:block></fo:table-cell>
                        <fo:table-cell number-columns-spanned="6"><fo:block><fo:leader leader-length="100%" leader-pattern="dots"/></fo:block></fo:table-cell>
                    </fo:table-row>
                </fo:table-body>

            </fo:table>

            <!---->

            <fo:block space-after="10px"/>
            <fo:block><fo:leader leader-length="100%" leader-pattern="rule"/></fo:block>
            <fo:block space-after="10px"/>

            <!---->

            <fo:table width="100%" border="0px solid black" >
                <fo:table-column column-width="40%"/>
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="40px" />
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="13px" />
                <fo:table-column column-width="40px" />
                <fo:table-column column-width="80px"/>
                <fo:table-column column-width="13px"/>
                <fo:table-column column-width="13px"/>
                <fo:table-column/>

                <fo:table-body>
                    <fo:table-row height="10.5px">
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Has an ADEERS' been submited to Central Office based on</fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">No</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Yes</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Cycle nuber</fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                    </fo:table-row>
                    <fo:table-row height="10.5px">
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">an event reported below ?</fo:block></fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>

            <!---->

            <fo:block space-after="10px"/>
            <fo:block><fo:leader leader-length="100%" leader-pattern="rule"/></fo:block>
            <fo:block space-after="10px"/>

            <!---->

            <fo:table width="100%" border="0px solid black" >
                <fo:table-column column-width="45%" />
                <fo:table-column column-width="55%" />

                <fo:table-header>
                    <fo:table-row>
                        <fo:table-cell border="0.5px gray solid">
                            <fo:block font-weight="bold" xsl:use-attribute-sets="cellText">SOLICITED ADVERSE EVENTS</fo:block>

                            <fo:table width="100%" border="0px solid black" >
                                <fo:table-column />
                                <fo:table-column column-width="20px" />
                                <fo:table-column column-width="11px" />
                                <fo:table-column column-width="11px" />
                                <fo:table-column column-width="20px" />
                                <fo:table-column column-width="20px" />
                                <fo:table-column column-width="11px" />
                                <fo:table-column column-width="20px" />

                            <fo:table-body>
                                <fo:table-row height="20px">
                                    <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center"></fo:block></fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border="0px black solid"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="left" font-weight="bold">AE Term</fo:block></fo:table-cell>
                                    <fo:table-cell border="0px black solid"><fo:block xsl:use-attribute-sets="cellSmallText"></fo:block></fo:table-cell>
                                    <fo:table-cell  border="0px black solid" number-columns-spanned="2"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center" font-weight="bold">AE Grade</fo:block></fo:table-cell>
                                    <fo:table-cell border="0px green solid"><fo:block xsl:use-attribute-sets="cellSmallText"></fo:block></fo:table-cell>
                                    <fo:table-cell  border="0px red solid" number-columns-spanned="3"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center" font-weight="bold">AE Attr. Code</fo:block></fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="ns2:studies/ns2:study/evaluationPeriods/ns2:evaluationPeriod/solicitedAdverseEvents/ns2:solicitedAdverseEvent/name">
                                    <fo:table-row>
                                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"><xsl:value-of select="."/></fo:block></fo:table-cell>
                                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                                        <fo:table-cell border="1px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                                        <fo:table-cell border="1px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                                        <fo:table-cell border="1px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                            </fo:table>

                        </fo:table-cell>
                        <fo:table-cell border="0.5px gray solid">
                            <fo:block font-weight="bold" xsl:use-attribute-sets="cellText ">
                                Specify any other events that occured during this time period. Report all events grade 2 and above. Report secondary malignancies including AML/MDS here and on Form C-1001.
                            </fo:block>

                            <fo:table width="100%" border="0px solid black" >
                                <fo:table-column />
                                <fo:table-column column-width="20px" />
                                <fo:table-column column-width="11px" />
                                <fo:table-column column-width="11px" />
                                <fo:table-column column-width="20px" />
                                <fo:table-column column-width="20px" />
                                <fo:table-column column-width="11px" />
                                <fo:table-column column-width="20px" />

                            <fo:table-body>
                                <fo:table-row height="20px">
                                    <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center"></fo:block></fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border="0px black solid"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="left" font-weight="bold">AE Term</fo:block></fo:table-cell>
                                    <fo:table-cell border="0px black solid"><fo:block xsl:use-attribute-sets="cellSmallText"></fo:block></fo:table-cell>
                                    <fo:table-cell  border="0px black solid" number-columns-spanned="2"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center" font-weight="bold">AE Grade</fo:block></fo:table-cell>
                                    <fo:table-cell border="0px green solid"><fo:block xsl:use-attribute-sets="cellSmallText"></fo:block></fo:table-cell>
                                    <fo:table-cell  border="0px red solid" number-columns-spanned="3"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center" font-weight="bold">AE Attr. Code</fo:block></fo:table-cell>
                                </fo:table-row>

                                <xsl:call-template name="loop">
                                    <xsl:with-param name="count">15</xsl:with-param>
                                </xsl:call-template>

                            </fo:table-body>
                            </fo:table>
                            
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-header>

                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block></fo:block></fo:table-cell>
                    </fo:table-row>
                </fo:table-body>

            </fo:table>

            <!---->

            <fo:block space-after="10px"/>
            <fo:block><fo:leader leader-length="100%" leader-pattern="rule"/></fo:block>
            <fo:block space-after="10px"/>

            <!---->

            <fo:block id="c-END"/>
        </fo:flow>

    </fo:page-sequence>

    
</fo:root>
        
</xsl:template>

<!--HEADER RIGHT FORM-->
<xsl:template name="CALGB_FORM">
    <fo:table width="100%" border="0px solid black" >
        <fo:table-column column-width="50%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-column column-width="5%" />
        <fo:table-body>
            <fo:table-row height="10.5px">
                <fo:table-cell border="0px gray solid"><fo:block xsl:use-attribute-sets="cellText">CALGB Form</fo:block></fo:table-cell>
                <fo:table-cell border="0px gray solid" number-columns-spanned="10"><fo:block xsl:use-attribute-sets="cellText" text-align="right">C-1399</fo:block></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="10.5px">
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">CALGB Study No.</fo:block></fo:table-cell>
                <fo:table-cell number-columns-spanned="4"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="10.5px">
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">CALGB Patient ID</fo:block></fo:table-cell>
                <fo:table-cell number-columns-spanned="4"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="10.5px">
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Reporting period start date</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell><fo:block text-align="center">/</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell><fo:block text-align="center">/</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="10.5px">
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Reporting period end date</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell><fo:block text-align="center">/</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell><fo:block text-align="center">/</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="6px">
                <fo:table-cell><fo:block></fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">M</fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">M</fo:block></fo:table-cell>
                <fo:table-cell><fo:block/></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">D</fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">D</fo:block></fo:table-cell>
                <fo:table-cell><fo:block/></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">Y</fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">Y</fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">Y</fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">Y</fo:block></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="10.5px">
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Are data amended ?</fo:block></fo:table-cell>
                <fo:table-cell number-columns-spanned="8"><fo:block text-align="right" xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                <fo:table-cell><fo:block text-align="right" xsl:use-attribute-sets="cellText">Yes</fo:block></fo:table-cell>
            </fo:table-row>
        </fo:table-body>

    </fo:table>
</xsl:template>
<!---->
    <xsl:template name="B-CELL">
        <xsl:param name="value" />
        <xsl:param name="span" />
        <fo:table-cell border="0.5px gray solid" number-columns-spanned="$span"><fo:block xsl:use-attribute-sets="cellText"><xsl:value-of select="$value"/></fo:block></fo:table-cell>
    </xsl:template>
<!---->

    <xsl:template name="loop">
        <xsl:param name="count"/>
        <xsl:param name="iteration">1</xsl:param>

        <fo:table-row height="9px">
            <fo:table-cell border="1px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border="1px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border="1px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border="1px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
        </fo:table-row>

        <xsl:if test="$iteration &lt; $count">
            <xsl:call-template name="loop">
                <xsl:with-param name="count" select="$count"/>
                <xsl:with-param name="iteration" select="$iteration + 1"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
<!---->

</xsl:stylesheet>