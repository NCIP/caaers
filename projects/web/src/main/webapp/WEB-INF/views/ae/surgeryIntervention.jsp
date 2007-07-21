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
     
     <style type="text/css">
    	div.row div.label { width: 15em; }
    	div.row div.value { margin-left: 16em;}
    	
    	 textarea {
            width: 20em;
            height: 5em;
        }
    	
    </style>
    
    <script type="text/javascript">
    	
         var anatomicAutocompleterProps = {
          basename: "aeReport.surgeryIntervention.anatomicSite",
          populator: function(autocompleter, text) {
              createAE.matchAnatomicSite(text, function(values) {
                  autocompleter.setChoices(values)
              })
          },
          valueSelector: function(obj) {
              return obj.name
          }
      }
      
      function acPostSelect(mode, selectedChoice) {
          //Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
          $(mode.basename).value = selectedChoice.id;
          //$(mode.basename + '-selected').show()
          //new Effect.Highlight(mode.basename + "-selected")
      }

      function updateSelectedDisplay(mode) {
      	 
          if ($(mode.basename).value) {
              	$(mode.basename + '-input').value = '${command.aeReport.surgeryIntervention.anatomicSite.name}'
          }
      }

      function acCreate(mode) {
          new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices",
              mode.populator, {
              valueSelector: mode.valueSelector,
              afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                  acPostSelect(mode, selectedChoice)
              },
              indicator: mode.basename + "-indicator"
          })
         
      }


        Event.observe(window, "load", function() {
            acCreate(anatomicAutocompleterProps)
            updateSelectedDisplay(anatomicAutocompleterProps)
            
        })
    
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="instructions">
            If applicable, enter Surgery Intervention Information for ${command.assignment.participant.fullName}
            on ${command.assignment.studySite.study.shortTitle}.
        </jsp:attribute>
    <jsp:attribute name="singleFields">
        <c:forEach items="${fieldGroups.desc.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>