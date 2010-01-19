<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Mandatory Fields</title>
</head>
<body>
	<div class="tabpane">
	    <div class="workflow-tabs2">
    	    <ul id="" class="tabs autoclear">
        	    <li id="thirdlevelnav" class="tab">
           	    	<div>
                    	<a href="configure"><caaers:message code="configure.menu.general"/></a>
                	</div>
            	</li>
            	<li id="thirdlevelnav" class="tab">
                	<div>
                   		<a href="passwordPolicyConfigure"><caaers:message code="configure.menu.passwordPolicy"/></a>
                	</div>
            	</li>
            	<li id="thirdlevelnav" class="tab selected">
                	<div>
                   		<a href="passwordPolicyConfigure"><caaers:message code="configure.menu.mandatoryFields"/></a>
                	</div>
            	</li>
        	</ul>
    	</div>
    </div>
    This is mandatory fields page.
</body>
</html>