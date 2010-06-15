<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Configure caAERS</title>
    <style type="text/css">
        div.row { padding: 5px 3px; }
        .row .value { margin-left: 22%; }
        .row .label { width: 20%; margin-left: 1em; text-align: right; }
        p.description { margin: 0.25em 0 0 1em; }
        div.submit { text-align: right; }
        .value input[type=text] { width: 80%; }
        form { margin-top: 1em; }
        .updated { border: #494 solid; border-width: 1px 0; background-color: #8C8; padding: 1em 2em; text-align: center; margin: 1em 30%; color: #fff; font-weight: bold; font-size: 1.1em; }
    </style>
</head>
<body>
	<div class="tabpane">
	    <div class="workflow-tabs2">
    	    <ul id="" class="tabs autoclear">
        	    <li id="thirdlevelnav" class="tab selected">
           	    	<div>
                    	<a href="configure"><caaers:message code="configure.menu.general"/></a>
                	</div>
            	</li>
            	<li id="thirdlevelnav" class="tab">
                	<div>
                   		<a href="passwordPolicyConfigure"><caaers:message code="configure.menu.passwordPolicy"/></a>
                	</div>
            	</li>
            	<li id="thirdlevelnav" class="tab">
                	<div><a href="mandatoryFields"><caaers:message code="configure.menu.mandatoryFields"/></a></div>
            	</li>
        	</ul>
    	</div>

<script>
    function reloadLabels() {
        jQuery.ajax({
            url: '<c:url value="/pages/admin/reload" />',
            success: function(data) {
                jQuery('#reloadedLabels').html("<a style='color:green; font-weight:bold;'>Labels reloaded successfully.</a>");
            }
        });
    }
</script>
        
    <form:form action="${action}" cssClass="standard">
        <chrome:box title="Configure caAERS" autopad="true">
            <p><tags:instructions code="configurecaares" /></p>

        <div class="row">
            <div class="label"><caaers:message code="reloadLabels" text="Reload labels"/></div>
            <div class="value" id="reloadedLabels"><input type="button" onclick="reloadLabels()" value="<caaers:message code="reloadLabels" text="Reload labels"/>"></div>
        </div>
            <c:forEach items="${command.conf}" var="entry" varStatus="status">
                    <div class="row">
                        <div class="label"><form:label path="conf[${entry.key}].value">${entry.value.property.name}</form:label></div>
                        <div class="value">
                            <c:set var="beanPath">conf[${entry.key}].value</c:set>
                            <c:choose>
                                <c:when test="${entry.value.property.controlType == 'boolean'}">
                                    <div>
                                        <label><form:radiobutton path="${beanPath}" value="true"/> Yes</label>
                                        <label><form:radiobutton path="${beanPath}" value="false"/> No</label>
                                    </div>
                                </c:when>
                                <c:when test="${entry.value.property.controlType == 'text'}">
                                    <div><form:input path="${beanPath}"/></div>
                                </c:when>
                                <c:otherwise>
                                    <div>Unimplemented control type ${entry.value.controlType} for ${beanPath}</div>
                                </c:otherwise>
                            </c:choose>
                            <p class="description">${entry.value.property.description}</p>
                            <c:if test="${not empty entry.value.default}"><p class="description">(Default: ${entry.value.default})</p></c:if>
                        </div>
                    </div>
            </c:forEach>

        <c:if test="${param.updated}"><p class="updated">Settings saved</p></c:if>
        </chrome:box>

        <div class="row submit"><tags:button type="submit" value="Save" color="green" icon="save" /></div>
    </form:form>
    </div>
</body>
</html>