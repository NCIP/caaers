<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="par" tagdir="/WEB-INF/tags/par" %>
<%@attribute name="index" required="true"%>
<%@attribute name="preExistingCondition" type="gov.nih.nci.cabig.caaers.domain.PreExistingCondition"%>

<div class="${(index % 2 ) gt 0 ? 'odd' : 'even' }">
		<table width="100%">
 			<tr>
  				<td width="99%">
                      <ui:select options="${preExistingConditionOptions}" path="assignment.preExistingConditions[${index}].preExistingCondition"></ui:select>
                      <span id="other_${index}" style="display:${(empty preExistingCondition) ? "inline" : "none"};">
                                Other, specify <ui:text path="assignment.preExistingConditions[${index}].other"/>
                      </span>
                  </td>
  				<td>
					<a href="#anchorPreExistingCondition" onClick="mHistory.removeDetails('preExistingCondition', ${index}, 'anchorPreExistingCondition')">
  					<img src="<chrome:imageUrl name="../checkno.gif" />" />
					</a>
				</td>
 			</tr>
		</table>

    <script language="JavaScript">
        Event.observe("assignment.preExistingConditions[${index}].preExistingCondition", "change", function() {
            if (($('assignment.preExistingConditions[${index}].preExistingCondition').options[$('assignment.preExistingConditions[${index}].preExistingCondition').selectedIndex].value) == "")
                $('other_${index}').show();
            else
                $('other_${index}').hide();
        })
    </script>

</div>