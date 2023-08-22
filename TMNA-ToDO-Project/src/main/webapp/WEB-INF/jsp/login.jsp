<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Login Page</title>
    <link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .login-form{
            width: 400px;
            height: 280px;
            position: absolute;
            background-color:darkslategray;
            border-radius: 2%;
            top: 50%;
            left: 50%;
            margin-right: -50%;
            transform: translate(-50%, -50%);
        }

    </style>
</head>

<body>
   
    
    
    <div class="login-form">

        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger" role="alert">${errorMsg}</div>
        </c:if>

        <c:if test="${not empty successMsg}">
            <div class="alert alert-success" role="alert">${successMsg}</div>
        </c:if>

        <div class="container-fluid">
            <form method="post" action="login">

                <div class="mt-3">
                    <input type="text" name="userName" class="form-control mt-3" placeholder="User Name" />
                </div>
               
                
                <div class="mt-3">
                    <input type="password" name="password" class="form-control mt-3" placeholder="Password" /> 
                </div>
                

                <div class="mt-3">
                    <button type="submit" class="btn btn-block btn-primary mt-3">Login</button>
                </div>
                
                <div class="mt-3">
                    <a href="/register" class="btn btn-success btn-block mt-3">Register</a>
                </div>
                
            </form>
        </div>
    </div>
</body>
</html>