<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>caAERS Template</title>
    <style type="text/css">
        #patient-summary {
            width: 29%;
            float: left;
        }

        #details {
            width: 69%;
            float: right;
        }

        #patient-summary .division-content, #details .division-content {
            height: 28em;
        }

        #registration-history {
            clear: both;
        }
    </style>
</head>
<body>

<chrome:search title="Participant Management">
    <form method="post" action="" name="searchMeth" class="search">
        <table border="0" cellspacing="0" cellpadding="0" class="search">
            <tr>
                <td class="labels"></td>
                <td align="left" class="labels"><span class="notation"><span
                    class="red">*</span></span>Last Name:</td>
                <td align="left" class="labels">First Name:</td>
                <td class="labels"></td>
            </tr>
            <tr>
                <td class="searchType">
                    Search
                    <select name="select"
                        class="field1">
                        <option selected>Participant</option>
                        <option>Protocol</option>
                    </select> by <select name="select" class="field1">
                        <option selected>Participant Name</option>
                        <option>Participant Registration#</option>
                    </select>
                </td>
                <td><input name="textfield2" type="text" class="field1" size="25"></td>
                <td><input name="textfield3" type="text" class="field1" size="25"></td>
                <td><input name="imageField" type="image" class="image button"
                    src="<chrome:imageUrl name="b-go.gif"/>" alt="GO" align="middle" width="22" height="10"></td>
            </tr>
            <tr>
                <td></td>
                <td colspan="4" class="notation">
                    <span class="labels">(<span class="red">*</span><em>Required Information</em>)</span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    ^ Minimum two characters for Last Name search.
                </td>
            </tr>
        </table>
    </form>
</chrome:search>

<chrome:body title="John Smith">
    <chrome:division title="Patient Summary" id="patient-summary">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table1">
        <tr valign="top">
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                    class="heightControl"></td>
                <td width="75%"><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                    height="1" class="heightControl"></td>
            </tr>
            <tr align="center" valign="top">
                <td colspan="2"><strong>First:</strong> John
                &nbsp;&nbsp;&nbsp;<strong>MI:</strong> -&nbsp;&nbsp;&nbsp;<strong>Last:</strong>
                Smith</td>
            </tr>
            <tr valign="top">
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                    class="heightControl"></td>
                <td width="75%"><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                    height="1" class="heightControl"></td>
            </tr>
            <tr valign="top">
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                    class="heightControl"></td>
                <td width="75%"><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                    height="1" class="heightControl"></td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>Gender:</td>
                <td>Not Reported</td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>Primary Id:</td>
                <td>1234567XYZ</td>
            </tr>
            <tr valign="top">
                <td class="label"><span class="red">*</span>Primary Id Source:</td>
                <td>NCI-Frederick / MD066NCI-Frederick /</td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>SSN:</td>
                <td>111-22-3333</td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>Birth
                Date:</td>
                <td valign="top">01/01/1981</td>
            </tr>
            <tr>
                <td class="label">First Visit Date:</td>
                <td valign="top">01/01/2001</td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>Ethnicity:</td>
                <td>Not Hispanic or Latino</td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>Race(s):</td>
                <td>Not Reported</td>
            </tr>
            <tr>
                <td class="label">Address:</td>
                <td>xyc, route 23, yyy</td>
            </tr>
            <tr>
                <td class="label">City:</td>
                <td>XXYYZZ</td>
            </tr>
            <tr>
                <td class="label">State:</td>
                <td>NY<strong>&nbsp;&nbsp;&nbsp;Zip:</strong>12345</td>
            </tr>
            <tr>
                <td class="label">County:</td>
                <td>USA</td>
            </tr>
        </table>
    </chrome:division>

    <chrome:division title="Details" id="details">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="split-pane">
    <tr>
    <td width="50%" valign="top" class="contentAreaL">
        <strong>Patient Details </strong>(<span class="red">*</span><em>Required Information</em>)<br>
        <br>
        <form:form method="post" cssClass="standard">
            <table width="308" border="0" cellspacing="0" cellpadding="0" class="table1">
                <tr>
                    <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                             class="heightControl"></td>
                    <td width="65%"><img src="<chrome:imageUrl name="spacer.gif"/>"
                                         width="1"
                                         height="1" class="heightControl"></td>
                </tr>
                <tr>
                    <td class="label"><span class="red">*</span>First Name:</td>
                    <td><input name="driverfield1" type="text" size="34"></td>
                </tr>
                <tr>
                    <td class="label"><span class="red">*</span>Last Name:</td>
                    <td><input name="driverfield1" type="text" size="34"></td>
                </tr>
                <tr>
                    <td class="label"><span class="red">*</span>Middle Name:</td>
                    <td><input name="driverfield1" type="text" size="15">
                        &nbsp;&nbsp;Suffix <select name="select" class="field1">
                        <option selected>--</option>
                        <option>I</option>
                        <option>II</option>
                        <option>III</option>
                        <option>Jr</option>
                        <option>Sr</option>
                    </select></td>
                </tr>
                <tr>
                    <td class="label"><span class="red">*</span>Gender:
                    </td>
                    <td><select name="select" class="field1">
                        <option selected>--</option>
                        <option>Male</option>
                        <option>Female</option>
                        <option>Not Reported</option>
                        <option>Unknown</option>
                    </select></td>
                </tr>
                <tr>
                    <td width="46%" class="label"><span class="red">*</span>Social
                        Security Number:
                    </td>
                    <td width="54%"><input name="driverfield1" type="text"
                                           size="11"></td>
                </tr>
                <tr>
                    <td class="label"><span class="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</span>Birth
                        Date:
                    </td>
                    <td valign="top"><input name="driverfield3" type="text"
                                            size="10">&nbsp;<a href="#"
                                                               onClick="parent.OpenWins('calendar.htm','calendar',200,236);return false;"><img
                        src="<chrome:imageUrl name="b-calendar.gif"/>" alt="Calendar"
                        width="17"
                        height="16" border="0" align="absmiddle"></a></td>
                </tr>
                <tr>
                    <td class="label"><span class="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*</span>First
                        Visit Date:
                    </td>
                    <td valign="top"><input name="driverfield3" type="text"
                                            size="10">&nbsp;<a href="#"
                                                               onClick="parent.OpenWins('calendar.htm','calendar',200,236);return false;"><img
                        src="<chrome:imageUrl name="b-calendar.gif"/>" alt="Calendar"
                        width="17"
                        height="16" border="0" align="absmiddle"></a></td>
                </tr>
                <tr>
                    <td class="label"><span class="red">*</span>Ethnicity
                    </td>
                    <td><select name="select" class="field1">
                        <option selected>--</option>
                        <option>Hispanic or Latino</option>
                        <option>Not Hispanic or Latino</option>
                        <option>Not Reported</option>
                        <option>Unknown</option>
                    </select></td>
                    <tr>
                        <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                                 height="1"
                                 class="heightControl  style1"></td>
                        <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                                 height="1"
                                 class="heightControl"></td>
                    </tr>
                    <tr>
                        <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                                 height="1"
                                 class="heightControl  style1"></td>
                        <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1"
                                 height="1"
                                 class="heightControl"></td>
                    </tr>
            </table>
        </form:form>
    </td>
    <td valign="top" class="contentAreaR">
        <strong>Current Registration</strong><br>
        <br>
        <table border="0" cellspacing="0" cellpadding="0"
               class="table1">
            <tr>
                <td class="label"><span class="red">*</span>Short Title:</td>
                <td width="75%" valign="top">CALGB_XYZ</td>
            </tr>
            <tr>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>Status:</td>
                <td>active</td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span><strong>Disease Code:</strong>
                <td valign="top">ABC</td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>NCI Identifier:</td>
                <td>NCI_IDENTIFIER</td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>Navy NCI Idententifier:</td>
                <td>NAVY_NCI_IDENTIFIER</td>
            </tr>
            <tr>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl "></td>
                <td><img src="<chrome:imageUrl name="spacer.gif"/>" width="1" height="1"
                         class="heightControl"></td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>Long Title:</td>
                <td>CALGB_Long_Title</td>
            </tr>
            <tr>
                <td class="label"><span class="red">*</span>Phase code:</td>
                <td>Phase_Code</td>
            </tr>
            <tr>
                <td class="label">Amendment Date:</td>
                <td>02/05/2006</td>
            </tr>
        </table>
    </td>
    </tr>
    </table>
    </chrome:division>

    <chrome:division title="Registration History" id="registration-history">
        <div style="margin: auto; border: 1px dotted orange; color: darkorange; width: 98%; height: 4em; vertical-align: middle; text-align: center; line-height: 4em;">
            TABLE GOES HERE
        </div>
    </chrome:division>
</chrome:body>
</body>
</html>