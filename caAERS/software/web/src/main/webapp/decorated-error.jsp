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
                font-size: 26px;
                text-align: left;
                color: #333366;
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
        
        <title>Oops! You found a bug</title>

        <script type="text/javascript" language="JavaScript">
            function PanelCombo(element) {
                panelDiv = $(element + "-interior");
                
                if (panelDiv.style.display == 'none') {
                    new Effect.OpenUp(panelDiv, arguments[1] || {});
					$('errorlink').innerHTML = "Hide error code";
                } else {
                    new Effect.CloseDown(panelDiv, arguments[1] || {});
					$('errorlink').innerHTML = "View error code";
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
    <div style="overflow:auto; margin-bottom:10px;">
        <img src="<c:url value="/images/blue/error.png" />" style="float:left; margin:10px;">
        <div style="float:left; padding-left:20px; padding-top:12px;">
            <div class="error">
                Oops! We thought we squashed that bug.
            </div>
            <div class="errorMessage">
                Try refreshing the page, if that doesn't work you may need to start over.
            </div>
            <br/>
            <div class="errorMessage">
                <!--[if IE]>
                    &nbsp;&nbsp;
                <![endif]--><tags:button color="blue" onclick="javascript:location.reload(true)" value="Refresh" />
                <c:set var="homeHref">
                    <c:url value='/'/>
                </c:set>
                <!--[if IE]>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <![endif]--><tags:button color="blue" onclick="javascript:location.href='${homeHref}'" value="Go Home" />
                <br/>
                <br/>
                <a id="errorlink" href="javascript:PanelCombo('error');">View error code</a>
            </div>
			<br style="clear:both;"/>
        </div>
    </div>
		<div>
            <div id="error-interior" class="interior" style="display:none;">
                <TABLE class="errortd" WIDTH="100%" cellspacing="1">
                    <TR>
                    <td width="100px">
                    <B>
                        <font color="blue">
                            Status Code
                        </font>
                    </B>
                    </TD>
                    <TD>
                        <%= String.valueOf(statusCode) %>
                    </TD>
                </TR>
                <TR>
                    <TD>
                        <B>
                            <font color="blue">
                                Exception Type
                            </font>
                        </B>
                    </TD>
                    <TD>
                        <%=String.valueOf(exceptionType) %>
                    </TD>
                </TR>
                <TR>
                    <TD>
                        <B>
                            <font color="blue">
                                Message
                            </font>
                        </B>
                    </TD>
                    <TD>
                        <%= String.valueOf(message) %>
                    </TD>
                </TR>
                </TABLE>
                <br/>
                <b>Header List:</b>
                <table class="errortd" width="100%" cellspacing="1">
                    <tr>
                        <td width="100px">
                            <b>Name</b>
                        </td>
                        <td>
                            <b>Value</b>
                        </td>
                    </tr>
                    <%                String name  = "";
                    String value = "";
                    java.util.Enumeration headers = request.getHeaderNames();
                    while(headers.hasMoreElements())
                    {
                    name  = (String) headers.nextElement();
                    value = request.getHeader(name); %>
                    <tr>
                        <td>
                            <font color="blue">
                                <%=String.valueOf(name) %>
                            </font>
                        </td>
                        <td>
                            <%=String.valueOf(value)%>
                        </td>
                    </tr>
                    <%                } %>
                </table>
                <br/>
                <b>Attribute List:</b>
                <table class="errortd" width="100%" cellspacing="1">
                    <%                java.util.Enumeration attributes = request.getAttributeNames();
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
                    } %>
                    <tr>
                        <td>
                            <font color="blue">
                                <%=String.valueOf(name) %>
                            </font>
                        </td>
                        <td>
                            <%=String.valueOf(value)%>
                        </td>
                    </tr>
                    <%                } %>
                    <tr>
                        <td colspan="2">
                            <b>StackTrace :</b>
                            <br/>
                            <pre>
                                             <%
                                             	if(exception != null){
                                                 	exception.printStackTrace(new PrintWriter(out));
                                             	}
                                             %>
                                  </pre>
                        </td>
                    </tr>
                </table>
            </div>
            </td>
        </tr>
        </table>
		</div>

    </body>
    </html>
    </page:applyDecorator>
<!-- END decorated-error.jsp -->