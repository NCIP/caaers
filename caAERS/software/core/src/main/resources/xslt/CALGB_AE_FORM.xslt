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
  <xsl:attribute name="font-size">10pt</xsl:attribute>
  <xsl:attribute name="font-weight">normal</xsl:attribute>
</xsl:attribute-set>
<xsl:attribute-set name="cellTextValue">
  <xsl:attribute name="font-family">Courier</xsl:attribute>
  <xsl:attribute name="font-size">10pt</xsl:attribute>
  <xsl:attribute name="font-weight">normal</xsl:attribute>
</xsl:attribute-set>
<xsl:attribute-set name="cellSmallText">
  <xsl:attribute name="font-family">Tahoma</xsl:attribute>
  <xsl:attribute name="font-size">8pt</xsl:attribute>
  <xsl:attribute name="font-weight">normal</xsl:attribute>
</xsl:attribute-set>

    <xsl:template match="/">
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

    <fo:layout-master-set>
        <fo:simple-page-master master-name="A4" margin-left="10mm" margin-top="5mm" margin-right="5mm" margin-bottom="5mm">
            <fo:region-body margin-top="3.4in" margin-bottom="1.7in"/>
            <fo:region-before extent="3.4in"/>
            <fo:region-after extent="1.7in"/>
        </fo:simple-page-master>
    </fo:layout-master-set>

    <fo:page-sequence master-reference="A4">

        <!-- PAGE HEADER -->
        <fo:static-content flow-name="xsl-region-before">

            <fo:table width="100%" border="0px blue dotted">
                <fo:table-column />
                <fo:table-column column-width="140px"/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell border="0px green dotted">
                            <fo:block text-align="center" padding-bottom="15px" padding-left="140px">Adverse Event (AE) Worksheet</fo:block>
                            <xsl:call-template name="CALGB_FORM"></xsl:call-template>
                        </fo:table-cell>
                        <fo:table-cell border="0px blue dotted"><fo:block text-align="right"><fo:external-graphic src="url(https://demo.semanticbits.com/caaers/images/blue/login-logo.png)" content-width="120px"/></fo:block></fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>

            <!--<fo:block><fo:leader leader-length="100%" leader-pattern="rule"/></fo:block>-->
            <fo:block space-after="10px"></fo:block>

        </fo:static-content>

        <!-- PAGE FOOTER -->
        <fo:static-content flow-name="xsl-region-after">

            <fo:block space-after="10px" xsl:use-attribute-sets="cellSmallText "></fo:block>
            <fo:table width="100%" border="0px solid black" >
                <fo:table-column column-width="33%"/>
                <fo:table-column column-width="33%" />
                <fo:table-column column-width="34%" />

                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block xsl:use-attribute-sets="cellSmallText"><fo:footnote><fo:inline baseline-shift="super" font-size="7px" color="black">(1)</fo:inline><fo:footnote-body><fo:block font="8pt Verdana"></fo:block></fo:footnote-body></fo:footnote> AE Grade Codes:</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">Grade -1: Not evaluated</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">Grade  0: Evaluated but not present</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">Grade  1: Mild</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">Grade  2: Moderate</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">Grade  3: Severe</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">Grade  4: Life threatening and/or disabling</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">Grade  5: Death</fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding-right="10px">
                            <fo:block xsl:use-attribute-sets="cellSmallText"><fo:footnote><fo:inline baseline-shift="super" font-size="7px" color="black">(2)</fo:inline><fo:footnote-body><fo:block font="8pt Verdana"></fo:block></fo:footnote-body></fo:footnote> Did the adverse events result in hospitalization or the prolongation of hospitalization ?</fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block xsl:use-attribute-sets="cellSmallText"><fo:footnote><fo:inline baseline-shift="super" font-size="7px" color="black">(3)</fo:inline><fo:footnote-body><fo:block font="8pt Verdana"></fo:block></fo:footnote-body></fo:footnote> Attribution is the relatedness of the adverse event to the investigational intervention.</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">Attribution Codes:</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">1: Unrelated</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">2: Unlikely</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">3: Possibly</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">4: Probably</fo:block>
                            <fo:block xsl:use-attribute-sets="cellSmallText">5: Definitely</fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
            <fo:block space-after="10px"></fo:block>
            
            <fo:table width="100%" border="0px solid black" >
                <fo:table-column column-width="100px"/>
                <fo:table-column />
                <fo:table-column column-width="100px"/>

                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell border="0px black solid"><fo:block xsl:use-attribute-sets="cellText" text-align="center">CONFIDENTIAL</fo:block></fo:table-cell>
                        <fo:table-cell><fo:block font-size="8pt" text-align-last="center" border-width="1mm">Page <fo:page-number/> of <fo:page-number-citation ref-id="c-END"/></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block font-size="8pt" text-align-last="right" border-width="1mm">Printed <xsl:value-of select="java:format(java:java.text.SimpleDateFormat.new('MM/dd/yyyy'), java:java.util.Date.new())"/></fo:block></fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:static-content>

        <!-- PAGE BODY -->
        <fo:flow flow-name="xsl-region-body">

            <!--<fo:block space-after="10px"></fo:block>-->

            <fo:block xsl:use-attribute-sets="cellText"><xsl:value-of select="ns2:studies/ns2:study/evaluationPeriods/ns2:evaluationPeriod/descriptionText"/></fo:block>

            <fo:block space-after="10px"/>

            <!---->
            <fo:block font-weight="bold" xsl:use-attribute-sets="cellText">ADVERSE EVENTS</fo:block>

            <fo:table width="100%" border="0.5px solid black">
                <fo:table-column />
                <fo:table-column column-width="20px" />
                <fo:table-column column-width="16px" />
                <fo:table-column column-width="16px" />
                <fo:table-column column-width="25px" />
                <fo:table-column column-width="16px" />
                <fo:table-column column-width="25px" />
                <fo:table-column column-width="16px" />
                <fo:table-column column-width="35px" />

            <fo:table-header>
                <fo:table-row background-color="rgb(200, 200, 200)" border="1px blue solid">
                    <fo:table-cell border-top="0.5px black solid"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="left" font-weight="bold">AE Term / Verbatim</fo:block></fo:table-cell>
                    <fo:table-cell border-top="0.5px black solid"><fo:block xsl:use-attribute-sets="cellSmallText"></fo:block></fo:table-cell>
                    <fo:table-cell border-top="0.5px black solid" number-columns-spanned="2"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center" font-weight="bold"><fo:footnote><fo:inline baseline-shift="super" font-size="7px" color="black">(1)</fo:inline><fo:footnote-body><fo:block font="8pt Verdana"></fo:block></fo:footnote-body></fo:footnote> Grade</fo:block></fo:table-cell>
                    <fo:table-cell border-top="0.5px black solid" number-columns-spanned="3"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center" font-weight="bold"><fo:footnote><fo:inline baseline-shift="super" font-size="7px" color="black">(2)</fo:inline><fo:footnote-body><fo:block font="8pt Verdana"></fo:block></fo:footnote-body></fo:footnote> Hospitalization (Y/N) ?</fo:block></fo:table-cell>
                    <fo:table-cell border-top="0.5px black solid" number-columns-spanned="2"><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center" font-weight="bold"><fo:footnote><fo:inline baseline-shift="super" font-size="7px" color="black">(3)</fo:inline><fo:footnote-body><fo:block font="8pt Verdana"></fo:block></fo:footnote-body></fo:footnote> Attribution</fo:block></fo:table-cell>
                </fo:table-row>
                <fo:table-row height="1px"><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>
            </fo:table-header>
                
            <fo:table-body>
                <fo:table-row height="1px"><fo:table-cell><fo:block/></fo:table-cell></fo:table-row>

                <xsl:for-each select="ns2:studies/ns2:study/evaluationPeriods/ns2:evaluationPeriod/solicitedAdverseEvents/ns2:solicitedAdverseEvent/name">
                    <fo:table-row height="16px">
                        <fo:table-cell border-bottom="0.5px black solid" padding-left="10px"><fo:block xsl:use-attribute-sets="cellTextValue"><xsl:value-of select="."/></fo:block></fo:table-cell>
                        <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell border="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                    </fo:table-row>
                    <fo:table-row height="1px"><fo:table-cell><fo:block></fo:block></fo:table-cell></fo:table-row>
                </xsl:for-each>

                <xsl:call-template name="loop">
                    <xsl:with-param name="count">15</xsl:with-param>
                </xsl:call-template>

            </fo:table-body>
            </fo:table>

            <!---->

            <fo:block space-after="10px"/>
            <!--<fo:block><fo:leader leader-length="100%" leader-pattern="rule"/></fo:block>-->
            <fo:block space-after="10px"/>

            <fo:table width="100%" border="0px solid black" >
                <fo:table-column column-width="25%"/>
                <fo:table-column column-width="30%" />
                <fo:table-column column-width="10%" />
                <fo:table-column column-width="15%" />
                <fo:table-column column-width="20%" />
                <fo:table-column/>

                <fo:table-body>
                    <fo:table-row height="14.5px">
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Clinical observations made by:</fo:block></fo:table-cell>
                        <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Date</fo:block></fo:table-cell>
                        <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                    </fo:table-row>
                    <fo:table-row height="14.5px">
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Worksheet completed by:</fo:block></fo:table-cell>
                        <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                        <fo:table-cell><fo:block xsl:use-attribute-sets="cellText">Date</fo:block></fo:table-cell>
                        <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>

            <fo:block space-after="10px"/>
            <fo:block><fo:leader leader-length="100%" leader-pattern="rule"/></fo:block>

            <!---->
            
            <fo:block id="c-END"/>
        </fo:flow>

    </fo:page-sequence>

    
</fo:root>
        
</xsl:template>

<!--HEADER RIGHT FORM-->
<xsl:template name="CALGB_FORM">
    <fo:table width="100%" border="0px solid black" >
        <fo:table-column column-width="100px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column column-width="16px" />
        <fo:table-column />
        <fo:table-body>
<!--
            <fo:table-row height="10.5px">
                <fo:table-cell border="0px gray solid"><fo:block xsl:use-attribute-sets="cellText">Form</fo:block></fo:table-cell>
                <fo:table-cell border="0px gray solid" number-columns-spanned="10"><fo:block xsl:use-attribute-sets="cellText" text-align="right">C-1399</fo:block></fo:table-cell>
            </fo:table-row>
-->
            <fo:table-row height="16px">
                <fo:table-cell border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellText">Study ID</fo:block></fo:table-cell>
                <fo:table-cell number-columns-spanned="11" border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellTextValue"><xsl:value-of select="ns2:studies/ns2:study/@id"/></fo:block></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="16px">
                <fo:table-cell border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellText">Study title</fo:block></fo:table-cell>
                <fo:table-cell number-columns-spanned="11" border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellTextValue"><xsl:value-of select="ns2:studies/ns2:study/shortTitle"/></fo:block></fo:table-cell>
            </fo:table-row>

            <fo:table-row height="10px"><fo:table-cell><fo:block></fo:block></fo:table-cell></fo:table-row>
            <fo:table-row>
                <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText">Study site name</fo:block></fo:table-cell>
                <fo:table-cell number-columns-spanned="11" border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            </fo:table-row>

            <fo:table-row height="10px"><fo:table-cell><fo:block></fo:block></fo:table-cell></fo:table-row>
            <fo:table-row>
                <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText">Patient ID</fo:block></fo:table-cell>
                <fo:table-cell number-columns-spanned="11" border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="5px"><fo:table-cell><fo:block></fo:block></fo:table-cell></fo:table-row>
            
            <fo:table-row height="16px">
                <fo:table-cell border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellText">Patient Initials</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted"><fo:block></fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted"><fo:block></fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block></fo:block></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted" number-columns-spanned="6"><fo:block></fo:block></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="6px">
                <fo:table-cell><fo:block></fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">First</fo:block></fo:table-cell>
                <fo:table-cell><fo:block></fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">Middle</fo:block></fo:table-cell>
                <fo:table-cell><fo:block></fo:block></fo:table-cell>
                <fo:table-cell><fo:block xsl:use-attribute-sets="cellSmallText" text-align="center">Last</fo:block></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="16px">
                <fo:table-cell border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellText">Course/Cycle start date</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted"><fo:block text-align="center">/</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted"><fo:block text-align="center">/</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted"><fo:block/></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="1px"><fo:table-cell><fo:block></fo:block></fo:table-cell></fo:table-row>
            <fo:table-row height="16px">
                <fo:table-cell border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellText">Course/Cycle end date</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted"><fo:block text-align="center">/</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted"><fo:block text-align="center">/</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted"><fo:block/></fo:table-cell>
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
            <fo:table-row height="16px">
                <fo:table-cell border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellText">Course/Cycle number</fo:block></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border="0.5px black solid"><fo:block/></fo:table-cell>
                <fo:table-cell border-bottom="0px black dotted" number-columns-spanned="9"><fo:block/></fo:table-cell>
            </fo:table-row>
            <fo:table-row height="16px">
                <fo:table-cell border-bottom="0px black dotted"><fo:block xsl:use-attribute-sets="cellText">Course/Cycle type</fo:block></fo:table-cell>
                <fo:table-cell number-columns-spanned="11"><fo:block xsl:use-attribute-sets="cellTextValue"><xsl:value-of select="ns2:studies/ns2:study/evaluationPeriods/ns2:evaluationPeriod/name"/></fo:block></fo:table-cell>
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

        <fo:table-row height="16px">
            <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border-bottom="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell border="0.5px black solid"><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
            <fo:table-cell><fo:block xsl:use-attribute-sets="cellText"></fo:block></fo:table-cell>
        </fo:table-row>
        <fo:table-row height="1px"><fo:table-cell><fo:block></fo:block></fo:table-cell></fo:table-row>

        <xsl:if test="$iteration &lt; $count">
            <xsl:call-template name="loop">
                <xsl:with-param name="count" select="$count"/>
                <xsl:with-param name="iteration" select="$iteration + 1"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
<!---->

</xsl:stylesheet>