<!-- BEGIN decorated-error.jsp -->

<%@page language="java" isErrorPage="true" %>
<%@page isErrorPage="true" %>
<%@page language="java" %>

<%@include file="/WEB-INF/views/taglibs.jsp" %>

<%
    Object statusCode = request.getAttribute("javax.servlet.error.status_code");
    Object exceptionType = request.getAttribute("javax.servlet.error.exception_type");
    Object message = request.getAttribute("javax.servlet.error.message");
%>

<%@page import="java.io.PrintStream" %>
<%@page import="java.io.PrintWriter" %>
 <page:applyDecorator name="standard">
    <html>
    <head>
        <style>
            div.error {
                font-size: 36px;
                text-align: left;
                color: #cc0033;
				margin-left:-2px;
				padding-bottom:16px;
            }

            div.errorMessage {
                font-size: 12px;
                text-align: left;
            }

            table.errortd {
                border: 0px gray dotted;
                font-size: 11px;
            }

            table.errortd td {
                border: 1px gray dotted;
                font-size: 11px;
				background-color:white;
            }
        </style>
        
        <title>Error</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <script type="text/javascript" language="JavaScript">
            function PanelCombo(element) {
                panelDiv = $(element + "-interior");
                imageId = element + '-image';
                imageSource = document.getElementById(imageId).src;
                
                if (panelDiv.style.display == 'none') {
                    new Effect.OpenUp(panelDiv, arguments[1] || {});
                    document.getElementById(imageId).src = imageSource.replace('minimize', 'maximize');
                } else {
                    new Effect.CloseDown(panelDiv, arguments[1] || {});
                    document.getElementById(imageId).src = imageSource.replace('maximize', 'minimize');
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
    <div align="center">

<table width="900" border="0">
<tr>
    <td valign="top" width="1px"><img src="<c:url value="/images/blue/error.png" />"></td>
    <td valign="top">
        <div style="float:left; padding-left:20px; padding-top:12px;">
            <div class="error">ERROR</div>
            <div class="errorMessage">The system encountered an error. Please contact your system administrator.</div>
            <br/>
            <div class="errorMessage"><a href="<c:url value="/"/>">Return Home</a></div>
        </div>
    </td>
</tr>
<tr>
    <td colspan="2" valign="top">
        <table width="900" border="0">
            <tr style="border-bottom: 1px black dotted">
                <td align="left"><h2 style="float:left">Detailed Error</h2> &nbsp;&nbsp;<a href="javascript:PanelCombo('error');"><img id="error-image" src="/caaers/images/chrome/minimize.gif" border="0" alt="Expand"/></a></td>
                <td align="right"></td>
            </tr>
            <tr>
                <td align="left" colspan="2">
                    <div id="error-interior" class="interior" style="display:none;">
                        <TABLE class="errortd" WIDTH="100%" cellspacing="1">
                          <TR>
                            <td width="100px"><B><font color="blue">Status Code</font></B></TD>
                            <TD><%= statusCode %></TD>
                          </TR>
                          <TR>
                            <TD><B><font color="blue">Exception Type</font></B></TD>
                            <TD><%= exceptionType %></TD>
                          </TR>
                          <TR>
                            <TD><B><font color="blue">Message</font></B></TD>
                            <TD><%= message %></TD>
                          </TR>
                        </TABLE>

                        <br/>
                        <b>Header List:</b>
                        <table class="errortd" width="100%" cellspacing="1">
                          <tr>
                            <td width="100px"><b>Name</b></td>
                            <td><b>Value</b></td>
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
                            <td><font color="blue"><%=name%></font></td>
                            <td><%=value%></td>
                          </tr>
                          <%
                                    }
                                %>
                        </table>

                        <br />
                        <b>Attribute List:</b>
                        <table class="errortd" width="100%" cellspacing="1">
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
                            <td><font color="blue"><%=name%></font></td>
                            <td><%=value%></td>
                          </tr>
                          <%
                                    }
                                %>
                          <tr>
                            <td colspan="2"><b>StackTrace :</b><br />
                              <pre>
                                         <%
                                             exception.printStackTrace(new PrintWriter(out));
                                         %>
                              </pre>
                            </td>
                          </tr>
                        </table>

                    </div>
                </td>
            </tr>
        </table>
    </td>
</tr>
</table>

<%--



--%>

    </div>
    </body>
    </html>
</page:applyDecorator>
<!-- END decorated-error.jsp -->