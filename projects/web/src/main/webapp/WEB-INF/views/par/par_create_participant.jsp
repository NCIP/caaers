<%--
    TODO: this entire flow's views need to be refactored.
--%>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
<tags:stylesheetLink name="participant"/>
<script src="/caaers/js/Spry/SpryEffects.js" type="text/javascript"></script>
<style type="text/css">
.hideInitially{
	display: none;
}
</style>
</head>
<body>
    <p class="instructions">
        You are creating a new Participant
    </p>
    
    <tags:tabForm tab="${tab}" flow="${flow}">
        <jsp:attribute name="singleFields">
        
        <div class="leftpane">
	        <div class="row">
	            <div class="label"><span class="red">*</span>First Name:</div>
	            <div class="value"><form:input path="firstName" /><a onClick="firsthelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div><div class="hideInitially" id="First Help">
                  <h3> Enter participant’s first name. Required field. </h3></div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Last Name:</div>
	            <div class="value"><form:input path="lastName" /><a onClick="lasthelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div> <div class="hideInitially" id="Last Help">
                  <h3> Enter the participant’s surname.  Required field.  </h3></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Maiden Name:</div>
	            <div class="value"><form:input path="maidenName" /><a onClick="maidenhelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div><div class="hideInitially" id="Maiden Help">
                  <h3>Enter the participant’s pre-marital name.  Optional field. </h3></div>
	        </div>
	        
	        <div class="row">
	            <div class="label">Middle Name:</div>
	            <div class="value"><form:input path="middleName" /><a onClick="middlehelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div><div class="hideInitially" id="Middle Help">
                  <h3> Enter participant’s middle name.  Optional field. </h3></div>
	        </div>
        </div>
       </div>
        <div class="leftpane">
	        <div class="row">
	            <div class="label"><span class="red">*</span>Date of Birth:</div>
	            <div class="value"><tags:dateInput path="dateOfBirth"/></div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Ethnicity:</div>
	            <div class="value">
	            		<form:select path="ethnicity">
							<form:options items="${ethnicity}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Race:</div>
	            <div class="value">
	            		<form:select path="race">
						<form:options items="${race}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
	        
	        <div class="row">
	            <div class="label"><span class="red">*</span>Gender:</div>
	            <div class="value">
	            		<form:select path="gender">
						<form:options items="${genders}" itemLabel="desc" itemValue="code" />
					    </form:select>
				</div>
	        </div>
        </div>
        
        <div class="endpanes">&nbsp;</div>

	<table width="700" border="0" cellspacing="0" cellpadding="0"
		id="table1">
		<tr>
			<td align="center"><B>
			Type:</td>
			<td align="center"><em></em><B>
			Value:</td>
			<td align="center"><em></em><B>
			Assigning Authority:</td>
			<td align="center"><B>Is Primary:</td>
		</tr>

		<c:forEach var="index" begin="0" end="4">
			<tr>
				<td align="center"><form:input
					path="identifiers[${index}].type" /></td>
				<td align="center"><form:input
					path="identifiers[${index}].value" /></td>
				<td align="center">
				<form:select path="identifiers[${index}].source">
					  						<form:options items="${sources}" itemLabel="desc" itemValue="code" />
				    					</form:select></td>

				<td align="center"><form:radiobutton
					path="identifiers[${index}].primaryIndicator" /></td>
			</tr>

		</c:forEach>

	</table>		
	<script type="text/javascript">
var firsthelp = new Spry.Effect.Slide('First Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var lasthelp = new Spry.Effect.Slide('Last Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var maidenhelp = new Spry.Effect.Slide('Maiden Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var middlehelp = new Spry.Effect.Slide('Middle Help', {duration: 500, from: '0%', to:'100%', toggle:true});
</script>
        </jsp:attribute>
    </tags:tabForm>    
</body>
</html>
