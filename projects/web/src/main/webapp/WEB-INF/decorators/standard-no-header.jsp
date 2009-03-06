<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <standard:head/>
    <decorator:head/>

    <style>
        #main {
            padding:0;
            top:0;
            margin-top:0;
        }
    </style>
</head>
<body>

<chrome:body title="${__decorator_title}">
    <decorator:body/>
</chrome:body>

</body>
</html>
