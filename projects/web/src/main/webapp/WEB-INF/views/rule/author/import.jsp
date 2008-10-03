<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="commons" uri="http://bioinformatics.northwestern.edu/taglibs/commons"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Import Rules</title>
    <style type="text/css">
        div.row {
            padding: 5px 3px;
             width: 70%;
        }
        .row .value {
            margin-left: 22%;
        }

        p.description {
            margin: 0.25em 0 0 1em;
        }
        div.submit {
            text-align: right;
        }
        .value input[type=text] {
            width: 80%;
        }

        form {
            margin-top: 1em;
        }

        .updated {
            border: #494 solid;
            border-width: 1px 0;
            background-color: #8C8;
            padding: 1em 2em;
            text-align: center;
            margin: 1em 30%;
            color: #fff;
            font-weight: bold;
            font-size: 1.1em;
        }
    </style>
</head>
<body>
<chrome:box title="Import XML File" autopad="true">
<p>
<tags:instructions code="importxmlrules" />
</p>
	<form:form action="${action}" enctype="multipart/form-data" cssClass="standard">
            <div class="row">
                <div class="label">
                    Rule set xml file : 
                </div>
                <div class="value">
                
                	<input type="file" name="ruleSetFile1" size="50"/>
                	<!-- 
                	<input id="importDir" type="text" name="importDir" size="100" value="${param.importDir}"/>
                	<p class="description">Directory location on the server </p>
                	-->
                	
                	<!-- <p class="description">Please reset your rules database and repositiry before performing import. </p> -->
                </div>
            </div>    
        <div class="row submit">
            <input type="submit" value="Import"/>
        </div>
    </form:form>
    <c:if test="${command.updated}">
		<p class="updated">${command.message}</p>
	</c:if>
</chrome:box>
</body>
</html>