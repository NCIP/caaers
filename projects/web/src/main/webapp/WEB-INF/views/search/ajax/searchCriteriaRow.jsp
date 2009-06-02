<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<tr>
	<td align="center">
		<input id="selected-id-${lastIndex }" type="checkbox" name="criteriaDTO.criteriaObjects[${lastIndex }].selected"></input>
	</td>
	<td align="center">
		<SELECT id="object-id-${lastIndex }" name="criteriaDTO.criteriaObjects[${lastIndex }].object" onChange="javascript:updateAttributes(${lastIndex });">
			<OPTION selected value="none">Please select</OPTION>
			<c:forEach items="${command.searchTargetObject.dependentObject }" var="dependentObject" varStatus="dependentObjectStatus">
				<OPTION value="${dependentObject.className }">${dependentObject.displayName }</OPTION>
			</c:forEach>
		</SELECT>
	</td>
	<td align="center">
		<SELECT id="attribute-id-${lastIndex }" name="criteriaDTO.criteriaObjects[${lastIndex }].attribute"  onChange="javascript:updateConditions(${lastIndex});">
			<OPTION selected value="none">Please select</OPTION>
		</SELECT>
	</td>
	<td align="center">
		<SELECT id="condition-id-${lastIndex }" name="criteriaDTO.criteriaObjects[${lastIndex }].condition">
			<OPTION selected value="none">Please select</OPTION>
		</SELECT>
	</td>
	<td align="center">
		<input type="text" id="value-id-${lastIndex }" name="criteriaDTO.criteriaObjects[${lastIndex }].condition"></input>
	</td>
	<td align="center">
		<SELECT if="operator-id-${lastIndex }" name="criteriaDTO.criteriaObjects[${lastIndex }].operator">
			<Option selected value="AND">AND</Option>
			<Option value="OR">OR</Option>
		</SELECT>
	</td>
</tr>