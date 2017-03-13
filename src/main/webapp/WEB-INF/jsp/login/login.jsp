<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/13
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login&signup</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700|Raleway:300,600" rel="stylesheet"/>
    <link rel='stylesheet prefetch' href='../../../resources/css/bootstrap.min.css'/>
    <link rel='stylesheet' href="../../../resources/css/core.css"/>
    <link rel='stylesheet' href="../../../resources/css/login.css"/>
    <script src="../../../resources/js/jquery-3.1.1.min.js"></script>
    <script src='../../../resources/js/bootstrap.min.js'></script>
    <script src="../../../resources/js/login.js"></script>
</head>
<body>

<div class="container">
    <section id="formHolder">

        <div class="row">

            <!-- Brand Box -->
            <div class="col-sm-6 brand">
                <a href="#" class="logo">MR <span>.</span></a>

                <div class="heading">
                    <h2>Marina</h2>
                    <p>Your Right Choice</p>
                </div>

                <div class="success-msg">
                    <p>Great! You are one of our members now</p>
                    <a href="/success" class="profile">Your Profile</a>
                </div>
            </div>


            <!-- Form Box -->
            <div class="col-sm-6 form">

                <!-- Login Form userName,Password -->
                <div class="login form-peice switched">
                    <form class="login-form" action="/login" enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <label for="loginemail">Email Adderss</label>
                            <input type="email" name="loginemail" id="loginemail" required>
                        </div>

                        <div class="form-group">
                            <label for="loginPassword">Password</label>
                            <input type="password" name="Password" id="loginPassword" required>
                        </div>

                        <div class="CTA">
                            <input type="submit" value="Login">
                            <a href="#" class="switch">I'm New</a>
                        </div>
                    </form>
                </div>
                <!-- End Login Form -->


                <!-- Signup Form  userName,Password,salt,mobilePhone,userEmail, -->
                <div class="signup form-peice">
                    <form class="signup-form" action="/signup" enctype="multipart/form-data" method="post">
                        <div class="form-group">
                            <label for="name">Full Name</label>
                            <input type="text" name="userName" id="name" class="name">
                            <span class="error"></span>
                        </div>

                        <div class="form-group">
                            <label for="email">Email Adderss</label>
                            <input type="email" name="userEmail" id="email" class="email">
                            <span class="error"></span>
                        </div>

                        <div class="form-group">
                            <label for="phone">Phone Number - <small>Optional</small>
                            </label>
                            <input type="text" name="mobilePhone" id="phone">
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="Password" id="password" class="pass">
                            <span class="error"></span>
                        </div>

                        <div class="form-group">
                            <label for="passwordCon">Confirm Password</label>
                            <input type="password" name="passwordCon" id="passwordCon" class="passConfirm">
                            <span class="error"></span>
                        </div>

                        <div class="CTA">
                            <input type="submit" value="Signup Now" >
                            <a href="#" class="switch">I have an account</a>
                        </div>
                    </form>
                </div>
                <!-- End Signup Form -->
            </div>
        </div>

    </section>

</div>


</body>
</html>
