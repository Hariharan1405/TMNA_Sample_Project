<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
<title>Login Page</title>
<link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"
	rel="stylesheet">
	
<style>
.login-form {
	width: 400px;
	height: 230px;
	background-color: #34568B;
	position: absolute;
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


        <div class="container-fluid">
            <form:form method="post" modelAttribute="user">

                <div class="mt-3">
                    <form:input type="text" path="userName" class="form-control mt-3" placeholder="User Name" />
                </div>
               
                
                <div class="mt-3">
                    <form:input type="password" path="password" class="form-control mt-3" placeholder="Password" /> 
                </div>
                

                <div class="mt-3">
                    <form:button type="submit" class="btn btn-success btn-block mt-3">Register</form:button>
                </div>
                
            </form:form>
        </div>
    </div>

</body>

</html>