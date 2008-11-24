<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <standard:head/>
    <decorator:head/>

    <style>
        #main {
            padding : 12px 18px 20px;
            position: relative;
            top: 10px;
        }
    </style>
</head>
<body>

<chrome:body title="${__decorator_title}">
    <decorator:body/>
</chrome:body>

</body>
</html>
