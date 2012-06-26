<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<html>
<head>
	<style type="text/css">
        pre {
            overflow-x: auto; /* Use horizontal scroller if needed; for Firefox 2, not needed in Firefox 3 */
            white-space: pre-wrap; /* css-3 */
            white-space: -moz-pre-wrap !important; /* Mozilla, since 1999 */
            white-space: -pre-wrap; /* Opera 4-6 */
            white-space: -o-pre-wrap; /* Opera 7 */
            width: 99%;
            word-wrap: break-word; /* Internet Explorer 5.5+ */
            margin: 0px 0px 0px 0px;
            padding:5px 5px 3px 5px;
        }


    </style>

    <title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE" />
    <script type="text/javascript">
        var _b = "FF";
	    if(Prototype.Browser.IE){
	        document.write('<style type="text/css">pre { white-space : normal; }</style>');
	    }
        function insertRecipient(t){
           var insTemplate = 's-email';
           var insLoc = 'b-email';
           if(t == 'role'){
                insTemplate = 's-role';
                insLoc = 'b-role';
           }

           $(insLoc).insert({before: $(insTemplate).innerHTML});

        }

        //This is the function that will insert the substitutions
        var FREE_MARKER_PREFIX = '$' + '{';
        var FREE_MARKER_SUFFIX = '}';


        var lastElement; //will store the last element on focus
        function insertReplacement(selSub) {
            i = selSub.selectedIndex;
            if (i < 0) return;
            if (!lastElement) {
                selSub.selectedIndex = 0;
                return;
            }

            var txtToInsert = FREE_MARKER_PREFIX + selSub.options[i].value + FREE_MARKER_SUFFIX;

            var msg = lastElement.value;

            if (lastElement.type == 'text') {
                //text (subject)
                msg = msg + txtToInsert;
                lastElement.value = msg;
            } else if (lastElement.type == 'textarea') {
                //textarea (message)
                if (_b == "IE") {
                    insertTextOnCursorPosition(txtToInsert, document.getElementById("notification.content"), th.caretPosition);
                } else {
                    st = lastElement.selectionStart;
                    end = lastElement.selectionEnd;
                    len = lastElement.textLength;
                    msg = msg.substring(0, st) + txtToInsert + msg.substring(end, len);
                    lastElement.value = msg;
                    lastElement.selectionStart = st + txtToInsert.length;
                    lastElement.selectionEnd = lastElement.selectionStart;
                }
            }
            selSub.selectedIndex = 0;
            lastElement.focus();
        }

        function insertTextOnCursorPosition(_text, _textareaElement, _position) {
            var text = jQuery(_textareaElement).val();
            _startText = text.substring(0, _position);
            _endText = text.substring(_position);
            jQuery(_textareaElement).val(_startText + " " + _text + " " +_endText);
        }
    </script>
</head>
<body>

<!--[if IE]>
<script>
    var _b = "IE";
    var th = null;
    jQuery("document").ready(function () {
        th = new ieTextAreaHack(document.getElementById("notification.content"));
        th.caretChange(function () {
            qa(th);
        });
    });

    function qa(th) {
    }
</script>
<![endif]-->

<div id="s-email" style="display: none;">
    <div>
        <input type="text" maxlength="2000" value="" class="validate-NOTEMPTY$$EMAIL required" name="recipientEmails" title="Email address" >
        <input type="image" align="absmiddle" onclick="javascript:{this.parentNode.parentNode.removeChild(this.parentNode)}" style="border: 0px none ;" src="<c:url value="/images/rule/remove_condition.gif" />">
    </div>

</div>
<div id="s-role" style="display: none;">
    <div>
        <select size="1" class="validate-NOTEMPTY required" name="recipientRoles" title="Role option">
            <c:forEach var="i" items="${command.roles}">
                <option value="${i.key}">${i.value}</option>
            </c:forEach>
        </select>
        <input type="image" align="absmiddle" onclick="javascript:{this.parentNode.parentNode.removeChild(this.parentNode)}" style="border: 0px none ;" src="<c:url value="/images/rule/remove_condition.gif" />">
    </div>
</div>

    <tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
        <jsp:attribute name="repeatingFields">
            <p><tags:instructions code="notification.safety_notification.top"/></p>
            <ui:row path="notification.study">
                <jsp:attribute name="label"><ui:label path="notification.study" required="true" text="Study." /></jsp:attribute>
                <jsp:attribute name="value">
                    <ui:autocompleter path="notification.study" required="true" initialDisplayValue="${empty command.notification.study ? 'Begin typing here' : command.notification.study.displayName}" enableClearButton="true" >
                        <jsp:attribute name="populatorJS">
                            function(autocompleter, text) {
                                createAE.matchStudies(text,null, false, function(values) { autocompleter.setChoices(values)} )
                            }
                        </jsp:attribute>
                        <jsp:attribute name="selectorJS">
                            function(s) {
                                return s.displayName;
                            }
                        </jsp:attribute>
                    </ui:autocompleter>
                </jsp:attribute>
            </ui:row>

            <ui:row path="notification.name">
                <jsp:attribute name="label"><ui:label path="notification.name" required="true" text="Name." /></jsp:attribute>
                <jsp:attribute name="value">
                    <ui:text path="notification.name" required="true" size="50" />
                </jsp:attribute>
            </ui:row>

            <ui:row path="notification.emails">
                <jsp:attribute name="label"><ui:label path="notification.emails" required="true" text="Recipients."/></jsp:attribute>
                <jsp:attribute name="value">
                    <table border="0" cellspacing="2" cellpadding="0" width="90%">
                        <tr align="middle">
                            <td width="49%"><tags:button color="blue" type="button" value="Add e-mail" size="small" icon="add" onclick="javascript:insertRecipient('email')"/></td>
                            <td rowspan="2" width="2%" class="divider">&nbsp;</td>
                            <td><tags:button color="blue" type="button" value="Add Role" size="small" icon="add" onclick="javascript:insertRecipient('role')" hoverTitle="Add Role"/></td>
                        </tr>
                        <tr>
                            <td width="49%" >
                                <c:forEach var="e" items="${command.notification.recipientEmails}">
                                    <div>
                                        <input type="text" maxlength="2000" value="${e}" class="validate-NOTEMPTY$$EMAIL valueOK" name="recipientEmails" title="Email address" >
                                        <input type="image" align="absmiddle" onclick="javascript:{this.parentNode.parentNode.removeChild(this.parentNode)}" style="border: 0px none ;" src="<c:url value="/images/rule/remove_condition.gif" />">
                                    </div>

                                </c:forEach>

                                <span id="b-email" />
                            </td>
                            <td>
                                <c:forEach var="r" items="${command.notification.recipientRoles}">

                                    <div>
                                        <select size="1" class="valueOK validate-NOTEMPTY " name="recipientRoles" title="Role option" >
                                            <c:forEach var="i" items="${command.roles}">
                                                <option ${r eq i.key ? 'selected="selected"' : ''} value="${i.key}">${i.value}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="image" align="absmiddle" onclick="javascript:{this.parentNode.parentNode.removeChild(this.parentNode)}" style="border: 0px none ;" src="<c:url value="/images/rule/remove_condition.gif" />">
                                    </div>
                                </c:forEach>

                                <span id="b-role" />
                            </td>
                        </tr>
                    </table>
                </jsp:attribute>

            </ui:row>

            <ui:row path="notification.subject">
                <jsp:attribute name="label"><ui:label path="notification.subject" required="true" text="Subject." /></jsp:attribute>
                <jsp:attribute name="value">
                    <ui:text path="notification.subject" required="true" size="100">
                        <jsp:attribute name="embededJS">
                            $('notification.subject').observe('focus', function(evt) {
                                lastElement = Event.element(evt);
                            });
                        </jsp:attribute>
                    </ui:text>
                </jsp:attribute>
            </ui:row>

            <caaers:message var="subopt1" code="LBL_substitution_firstOption" text="Insert a variable"/>
            <ui:row path="substitution">
                <jsp:attribute name="label"> </jsp:attribute>
                <jsp:attribute name="value">
                    <ui:label path="substitution" text="Insert substitutions." />
                    <select id="sub-sel" onchange="insertReplacement(this);">
                        <option value="">${subopt1}</option>
                        <option value="study.shortTitle">Study short title</option>
                        <option value="study.primaryIdentifier.value">Study identifier</option>
                    </select>
                </jsp:attribute>
            </ui:row>

            <ui:row path="notification.content">
                <jsp:attribute name="label"><ui:label path="notification.content" required="true" text="Mail content." /></jsp:attribute>
                <jsp:attribute name="value">
                    <ui:textarea path="notification.content" required="true" rows="20" cols="100">
                        <jsp:attribute name="embededJS">
                            $('notification.content').observe('focus', function(evt) {
                                lastElement = Event.element(evt);
                            });
                        </jsp:attribute>
                    </ui:textarea>
                </jsp:attribute>
            </ui:row>


            <c:if test="${flow.tabCount eq (tab.number + 1) }">
                <input type="hidden" name="_finish" value="0" />
            </c:if>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>