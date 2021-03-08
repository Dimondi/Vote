<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html >
<head>
    <meta charset="UTF-8">
    <title>Login & Register</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../css/auth_css.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>

<div class="form">

    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Register</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">
        <div id="signup">
            <h1>Register</h1>
            <h1 class="error">${error}</h1>
            <h1>${success}</h1>
            <form action="/registration" method="post">
                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            First Name<span class="req">*</span>
                        </label>
                        <input type="text" name="firstname" required autocomplete="off" />
                    </div>

                <div class="field-wrap">
                        <label>
                            Last Name<span class="req">*</span>
                        </label>
                        <input type="text" name="lastname" required autocomplete="off" />
                    </div>
                </div>

                <div class="field-wrap">
                    <label>
                        Username<span class="req">*</span>
                    </label>
                    <input type="text" name="username" required autocomplete="off" />
                </div>

                <div class="field-wrap">
                    <label>
                        Email<span class="req">*</span>
                    </label>
                    <input type="email" name="email" required autocomplete="off" />
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req">*Minimum 6 characters!</span>
                    </label>
                    <input type="password" name="password" required autocomplete="off" />
                </div>

                <div class="field-wrap">
                    <label>
                        Phone<span class="req">*</span>
                    </label>
                    <input type="text" name="phone" required autocomplete="off" />
                </div>
                <button type="submit" class="button button-block" />Register</button>

            </form>

        </div>

        <div id="login">
            <h1>Welcome!</h1>
            <h1 class="error">${SPRING_SECURITY_LAST_EXCEPTION.message}</h1>
            <form action="login" method="post">

                <div class="field-wrap">
                    <label>
                        Username<span class="req"></span>
                    </label>
                    <input type="username" name="username" required autocomplete="off" />
                </div>

                <div class="field-wrap">
                    <label>
                        Password<span class="req"></span>
                    </label>
                    <input type="password" name="password" required autocomplete="off" />
                </div>

                <button class="button button-block" />Log In</button>

            </form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src="../js/auth_js.js"></script>

</body>
</html>
