<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>


<%@page language="java" isErrorPage="true"%>

<%@ page isErrorPage="true" %>
<%@ page language="java" %>

<%
    Object statusCode = request.getAttribute("javax.servlet.error.status_code");
    Object exceptionType = request.getAttribute("javax.servlet.error.exception_type");
    Object message = request.getAttribute("javax.servlet.error.message");
%>

<%@page import="java.io.PrintStream"%>
<%@page import="java.io.PrintWriter"%>
<page:applyDecorator  name="standard">

<html>
<head>
	<tags:includeScriptaculous />
    <title>Error Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <script type="text/javascript" language="JavaScript">
        function PanelCombo(element) {
            panelDiv = $(element+"-interior");
            imageId= element+'-image';
            imageSource=document.getElementById(imageId).src;
            if (panelDiv.style.display == 'none') {
                new Effect.OpenUp(panelDiv, arguments[1] || {});
                document.getElementById(imageId).src=imageSource.replace('minimize','maximize');
            } else {
                new Effect.CloseDown(panelDiv, arguments[1] || {});
                document.getElementById(imageId).src=imageSource.replace('maximize','minimize');
            }
        }
        Effect.OpenUp = function(element) {
            element = $(element);
            new Effect.BlindDown(element, arguments[1] || {});
        }

        Effect.CloseDown = function(element) {
            element = $(element);
            new Effect.BlindUp(element, arguments[1] || {});
        }

        Effect.Combo = function(element) {
            element = $(element);
            if (element.style.display == 'none') {
                new Effect.OpenUp(element, arguments[1] || {});
            } else {
                new Effect.CloseDown(element, arguments[1] || {});
            }
        }
    </script>
</head>

<body>

<chrome:box title="Error" autopad="true">

    <div class="row">
        <div class="error">
            <div class="label">
                ERROR:
            </div>
        </div>
        <div class="value">
            The system encountered an error. Please contact your system administrator
        </div>
    </div>

    <div class="row">
        <div class="value">
            <a href="<c:url value="/"/>">Return Home</a>
        </div>
    </div>
</chrome:box>


<div class="box">
<!-- header -->
<div class="header"><div class="background-L"><div class="background-R">
    <table width="100%"><tr>
        <td>
            <h2>Detailed Error</h2>
        </td>
        <td align="right">
            <div id="error-image-div">
                <a href="javascript:
			PanelCombo('error');
				">
                    <img id="error-image" src="/caaers/images/chrome/minimize.gif"/>
                         </a>
            </div>
        </td>
    </tr>
    </table>
</div></div></div>
<!-- end header --><!-- end header -->

<!-- inner border -->
<div class="border-T"><div class="border-L"><div class="border-R"><div class="border-B"><div class="border-TL"><div class="border-TR"><div class="border-BL"><div class="border-BR">
    <div id="error-interior" class="interior" style="display:none;">
        <div class="content">

            <TABLE CELLPADDING="2" CELLSPACING="2" BORDER="1" WIDTH="50%">
                <TR>
                    <TD WIDTH="20%"><B>Status Code</B></TD>
                    <TD WIDTH="80%"><%= statusCode %></TD>
                </TR>
                <TR>
                    <TD WIDTH="20%"><B>Exception Type</B></TD>
                    <TD WIDTH="80%"><%= exceptionType %></TD>
                </TR>
                <TR>
                    <TD WIDTH="20%"><B>Message</B></TD>
                    <TD WIDTH="80%"><%= message %></TD>
                </TR>
            </TABLE>

            <hr>
            <b>  Header List </b>
            <table border=3>
                <tr>
                    <td>Name</td>
                    <td>Value</td>
                </tr>
                <%
                    String name  = "";
                    String value = "";

                    java.util.Enumeration headers = request.getHeaderNames();
                    while(headers.hasMoreElements())
                    {
                        name  = (String) headers.nextElement();
                        value = request.getHeader(name);
                %>
                <tr>
                    <td><%=name%></td>
                    <td><%=value%></td>
                </tr>
                <%
                    }
                %>
            </table>

            <hr>
            <b>Attribute List:</b>

            <table border=3>
                <%
                    java.util.Enumeration attributes = request.getAttributeNames();
                    while(attributes.hasMoreElements())
                    {
                        name  = (String) attributes.nextElement();

                        if (request.getAttribute(name) == null)
                        {
                            value = "null";
                        }
                        else
                        {
                            value = request.getAttribute(name).toString();
                        }
                %>
                <tr>
                    <td><%=name%></td>
                    <td><%=value%></td>
                </tr>
                <%
                    }
                %>
                <tr>
                  <td colspan="2">
                  	   StackTrace :<br />
                  	   <% 
                  	   	exception.printStackTrace(new PrintWriter(out)); 
                  	   %>
                  	   
                  </td>
                </tr>
            </table>

        </div>
    </div></div></div></div></div></div></div></div>
    <!-- end inner border -->
</div>




</div>
</body>
</html>
</page:applyDecorator>