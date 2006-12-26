<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Account login</title>
    <style type="text/css">
        body {
            background-color: #666;
        }
        div#body {
            width: 24em;
            border: 2px outset #000;
            background-color: #fff;
            padding: 0;
            margin: 3em auto;
        }
        h1 {
            background-color: #000;
            color: #fff;
            padding: 0.5em 2em;
            text-align: center;
        }
        form#login {
            margin: 1em 2em;
        }
    </style>
</head>
<body>
<h1>Please log in</h1>
<tags:loginForm/>
</body>
</html>
