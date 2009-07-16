<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@attribute name="path" description="This path is used by the render decision manager to determinie the visibility" %>
<%@attribute name="title" required="true" description="The title of the accordion."%>
<%@attribute name="id" required="true" description="The ID to be given to the title control of the accordion"%>
<%@attribute name="cssClass" description="Any additional CSS class, that has to be used on the title aswell as the content"%>
<caaers:renderFilter elementID="${empty path ? 'dummyPath' : path}" uiType="DIVISION">
<div id="${id}" class="accordion-toggle ${cssClass}">${title}</div>
  <div id="accordionContent-${id}" class="accordion-content ${cssClass}">
   <jsp:doBody/>
  </div>
</caaers:renderFilter>