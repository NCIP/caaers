<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="index" required="true" type="java.lang.Integer" %>
<%@attribute name="style"%>
<%@attribute name="title"%>
<%@attribute name="sectionClass" required="true" %>
<%@attribute name="removeButtonAction"%>
<%@attribute name="enableDelete" type="java.lang.Boolean" %>
<%@attribute name="localButtons" fragment="true" %>
<c:set var="deleteParams">'${removeButtonAction}',${index}</c:set>
<c:set var="mainGroup">main${index}</c:set>
<chrome:division title="${title}" id="${sectionClass}-${index}" 
cssClass="${sectionClass}" style="${style}" enableDelete="${enableDelete}" deleteParams="${deleteParams}">

<tags:renderRow field="${fieldGroups[mainGroup].fields[0]}">
        <jsp:attribute name="label">
            <label>
                <input id="select-agent-${index}" name="priorTherapyOrOther${index}" type="radio"/>
                ${fieldGroups[mainGroup].fields[0].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
    
    <tags:renderRow field="${fieldGroups[mainGroup].fields[1]}">
        <jsp:attribute name="label">
            <label>
                <input id="select-other-${index}" name="priorTherapyOrOther${index}" type="radio"/>
                ${fieldGroups[mainGroup].fields[1].displayName}
            </label>
        </jsp:attribute>
    </tags:renderRow>
    

<tags:renderRow field="${fieldGroups[mainGroup].fields[2]}"></tags:renderRow>


 <div id="local-buttons-${index}" class="local-buttons">
    <jsp:invoke fragment="localButtons"/>
 </div>
 <br />
</chrome:division>