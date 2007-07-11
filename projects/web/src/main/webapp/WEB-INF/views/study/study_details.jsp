
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
    <tags:includeScriptaculous/> <!-- TODO: this should be included by default on all pages -->
</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="singleFields">
        <div class="content">
            <div class="row">
                <div class="label"><tags:requiredIndicator/>Title:</div>
                <div class="value">
                    <form:input path="shortTitle" maxlength="30" size="40" />
                    <tags:inlineHelp path="shortTitle">
                        Enter Protocol title intended for the public. Required field.
                    </tags:inlineHelp>
                </div>
            </div>
           
            <div class="row">
                <div class="label"><tags:requiredIndicator/>Long Title:</div>
                <div class="value">
                    <form:textarea path="longTitle" rows="3" cols="50"/>
                    <tags:inlineHelp path="longTitle">
                        Enter Official name of the protocol provided by the study principal
                        investigator or sponsor. Required Field.
                    </tags:inlineHelp>
                </div>
            </div>
            <div class="row">
                <div class="label">Precis:</div>
                <div class="value">
                    <form:textarea path="precis" rows="3" cols="50"/>
                    <tags:inlineHelp path="precis">
                        Enter short description of the primary purpose of the protocol intended for
                        the lay public. Optional Field.
                    </tags:inlineHelp>
                </div>
            </div>
            <div class="row">
                <div class="label">Description:</div>
                <div class="value">
                    <form:textarea path="description" rows="3" cols="50"/>
                    <tags:inlineHelp path="description">
                        Enter extended description of the protocol, including information not
                        already contained in other fields, such as comparison(s) studied.
                    </tags:inlineHelp>
                </div>
            </div>
            <div class="row">
                <div class="label"><tags:requiredIndicator/>Sponsor:</div>
                <div class="value">
                    <form:select path="primarySponsorCode">
                        <option value="">--please select --</option>
                        <form:options items="${sponsorCodeRefData}" itemLabel="desc"
                            itemValue="desc"/>
                    </form:select>
                    <tags:inlineHelp path="primarySponsorCode">
                        Select name of primary organization that oversees implementation of study
                        and is responsible for data analysis. Required field.
                    </tags:inlineHelp>
                </div>
            </div>
            <div class="row">
                <div class="label"><tags:requiredIndicator/>Phase:</div>
                <div class="value">
                    <form:select path="phaseCode">
                        <option value="">--please select --</option>
                        <form:options items="${phaseCodeRefData}" itemLabel="desc"
                            itemValue="desc"/>
                    </form:select>
                    <tags:inlineHelp path="phaseCode">
                        Select phase of investigation, as defined by the US FDA for trials
                        involving investigational new drugs. Required field.
                    </tags:inlineHelp>
                </div>
            </div>
            <div class="row">
                <div class="label"><tags:requiredIndicator/>CTC Version:</div>
                <div class="value">
                    <form:select path="ctcVersion">
                        <option value="">--please select --</option>
                        <form:options items="${ctcVersion}" itemLabel="name"
                            itemValue="id"/>
                    </form:select>
                    <tags:inlineHelp path="ctcVersion">
                        Select Common Toxicity Criteria Version, caAERS supports CTC v.2.0 and
                        CTCAE v3.0. Required field.
                    </tags:inlineHelp>
                </div>
            </div>
        </div>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
