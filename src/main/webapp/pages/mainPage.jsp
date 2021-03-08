<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login & Register</title>
    <link rel="stylesheet" href="../css/mainPage_css.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<input style="display: none" type="text" id="isAdmin" value="${roles[0]}">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-md-auto gap-2">
                <li class="nav-item rounded">
                    <a class="nav-link active" aria-current="page" href="/mainPage"><i class="bi bi-house-fill me-2"></i>Home</a>
                </li>
                <li class="nav-item rounded">
                    <a class="nav-link" href="/writePoll"><i class="bi bi-people-fill me-2"></i>Write post</a>
                </li>
                <li class="nav-item dropdown rounded" >
                    <a id="popular_btn" class="nav-link" href="#"><i class="bi bi-telephone-fill me-2"></i>Popular Polls</a>
                </li>
                <li class="nav-item dropdown rounded" >
                    <a id="latest_btn" class="nav-link" href="#"><i class="bi bi-telephone-fill me-2"></i>Latest Polls</a>
                </li>
                <li class="nav-item dropdown rounded">
                    <a class="nav-link" href="/logout"><i class="bi bi-telephone-fill me-2"></i>Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="vh-100 d-flex justify-content-center align-items-center">
    <div id="latest" class="container" style="display:none;"></div>
    <div id="popular" class="container" style="display:none;" ></div>
    <div id="content" class="container"></div>
</div>
    <script src="../js/mainPage_js.js"></script>
</body>

</html>
