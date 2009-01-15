<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
     <tags:dwrJavascriptLink objects="createAE"/>
     <tags:slider>
   		<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			Here is the comments's DIV
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>
    <link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <script type="text/javascript">
    
    	var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        Element.observe(window, "load", function() {
        
			if ( $('radiationIntervention-0')){
				$('add-radiationIntervention-button').hide();
			}
			
			
            new ListEditor("radiationIntervention", createAE, "RadiationIntervention", {
                addFirstAfter: "single-fields",
                addParameters: [aeReportId],
                addCallback: function(index) {
                	AE.registerCalendarPopups("radiationIntervention-" + index)
                	$('add-radiationIntervention-button').hide();
                	
                },
                removeCallback: function(index) {
                	$('add-radiationIntervention-button').show();
                	
                },
                deletable: true
            }, 'aeReport.radiationInterventions')
        })
    
    </script>
    <style type="text/css">
    	div.row div.label { width: 16em; }
    	div.row div.value { margin-left: 17em;}

        textarea {
            width: 20em;
            height: 5em;
        }
    </style>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section12radiation">
    <jsp:attribute name="instructions">
    	<tags:instructions code="instruction_ae_radiation" />
    </jsp:attribute>
   <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.radiationInterventions}" varStatus="status">
            <ae:oneRadiationIntervention index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="radiationIntervention" label="Add a radiation" buttonCssClass="ae-list-editor-button"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>