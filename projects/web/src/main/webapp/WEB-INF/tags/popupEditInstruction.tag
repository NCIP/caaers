<%@taglib prefix="caaers" uri="http://gforge.nci.nih.gov/projects/caaers/tags" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@attribute name="propertyName"%>
<%@attribute name="size"%>
<%@attribute name="validations"%>

  		<div style="display:none;">
			<div id="${propertyName}-div" >
			<table>
			<tr>
			<td colspan="1">
			<form:textarea path="${propertyName}" rows="8" cols="50" />
			</td>
			</tr>
			<tr class="bottom" >
			<td align="center">
			<input id="${propertyName}-div-button" type="button" value="Save" />
			</td>
			</tr>
			</table>
			</div>
		</div>
