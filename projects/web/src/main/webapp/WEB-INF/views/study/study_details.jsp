
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>${tab.longTitle}</title>
<script src="/caaers/js/Spry/SpryEffects.js" type="text/javascript"></script>
<style type="text/css">
.hideInitially{
	display: none;
}
</style> </head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="singleFields">
        <div class="content">
            <div class="row">
                <div class="label"><span class="red">*</span>Short Title:</div>
                <div class="value"><form:input path="shortTitle" maxlength="30" size="40" /> <a onclick="shorthelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div> <div class="value"><div class="hideInitially" id="Short Help">
                  <h3> Enter Protocol title intended for the public. Required field.</h3>
                </div></div></div>
           
            <div class="row">
                <div class="label"><span class="red">*</span>Long Title:</div>
                <div class="value"><form:textarea path="longTitle" rows="3" cols="50"/> <a onclick="longhelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div> 
                <div class="value"><div class="hideInitially" id="Long Help">
                  <h3> Enter Official name of the protocol provided by the study principal investigator or sponsor. Required Field.</h3>
                </div></div></div>
            <div class="row">
                <div class="label">Precis:</div>
                <div class="value"><form:textarea path="precis" rows="3" cols="50"/> <a onclick="precishelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div> 
                <div class="value"><div class="hideInitially" id="Precis Help">
                  <h3> Enter short description of the primary purpose of the protocol intended for the lay public. Optional Field. </h3>
                </div></div></div>
            <div class="row">
                <div class="label">Description:</div>
                <div class="value"><form:textarea path="description" rows="3" cols="50"/> <a onclick="descriptionhelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div> 
                <div class="value"><div class="hideInitially" id="Description Help">
                  <h3>Enter extended description of the protocol, including information not already contained in other fields, such as comparison(s) studied.</h3>
                </div></div></div>
            <div class="row">
                <div class="label"><span class="red">*</span><em></em>Sponsor:</div>
                <div class="value"><form:select path="primarySponsorCode">
                    <option value="">--please select --</option>
                    <form:options items="${sponsorCodeRefData}" itemLabel="desc"
                        itemValue="desc"/>
                    </form:select> <a onclick="sponsorhelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div>
                <div class="value"><div class="hideInitially" id="Sponsor Help">
                  <h3> Select name of primary organization that oversees implementation of study and is responsible for data analysis. Required field.</h3>
                </div></div></div>
            <div class="row">
                <div class="label"><span class="red">*</span><em></em>Phase:</div>
                <div class="value"><form:select path="phaseCode">
                    <option value="">--please select --</option>
                    <form:options items="${phaseCodeRefData}" itemLabel="desc"
                        itemValue="desc"/>
                    </form:select> <a onclick="phasehelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div> 
                <div class="value"><div class="hideInitially" id="Phase Help">
                  <h3> Select phase of investigation, as defined by the US FDA for trials involving investigational new drugs. Required field. </h3>
                </div></div></div>
             <div class="row">
                <div class="label"><span class="red">*</span><em></em>Ctc Version:</div>
                <div class="value"><form:select path="ctcVersion">
                    <option value="">--please select --</option>
                    <form:options items="${ctcVersion}" itemLabel="name"
                        itemValue="id"/>
                    </form:select>
                <a onclick="ctchelp.start(); return false;" href="#"><img src="/caaers/images/q.gif" border="0" alt="Help" title="Help"></a></div> 
                <div class="value"><div class="hideInitially" id="CTC Help">
                  <h3> Select Common Toxicity Criteria Version, caAERS supports CTC v.2.0 and CTCAE v3.0. Required field.</h3>
                </div></div></div>
        </div>
		<script type="text/javascript">
var shorthelp = new Spry.Effect.Slide('Short Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var longhelp = new Spry.Effect.Slide('Long Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var precishelp = new Spry.Effect.Slide('Precis Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var descriptionhelp = new Spry.Effect.Slide('Description Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var sponsorhelp = new Spry.Effect.Slide('Sponsor Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var phasehelp = new Spry.Effect.Slide('Phase Help', {duration: 500, from: '0%', to:'100%', toggle:true});
var ctchelp = new Spry.Effect.Slide('CTC Help', {duration: 500, from: '0%', to:'100%', toggle:true});
</script>
	</jsp:attribute>
</tags:tabForm>
</body>
</html>
