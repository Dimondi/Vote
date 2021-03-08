<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/poll_css.css" />
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script></head>
<body>
<div style="display: flex;flex-direction: row; justify-content: center;">
    <div class="col-md-3">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <span class="glyphicon glyphicon-hand-right"></span>${poll.pollTitle}</h3>
                <input type="text" id="pollId" style="display: none;" value="${poll.id}">
            </div>
            <div class="panel-body">
                <ul class="list-group">
                    <c:forEach items="${poll.options}" var="option">
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    <input type="radio" name="option" value="${option.id}">
                                        ${option.value}
                                </label>
                                <div style="display: none;" class="rate" class="progress">
                                    <div class="progress-bar bg-success" role="progressbar" style="width: ${option.rate}%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100">${option.rate}%</div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="panel-footer text-center">
                <button type="button" id="vote" class="btn btn-success btn-block btn-sm">
                    Vote</button>
                <a href="#" id="view" class="small">View Result</a></div>
        </div>
    </div>
</div>
<div class='ui' style="display: none">

    <div class='title'>
        <h1>
            Statistic for poll
        </h1>
        <br><br>

    </div>

    <div class="container" style="display: flex;">

        <div class='ui_box'>

            <div class='ui_box__inner'>
                <h2>
                    Vote Rate
                </h2>

                <div class='stat'>
                    <span>${statistics}%</span>
                </div>
                <div class='progress' >
                    <div class='progress_bar' style="width:${statistics}%"></div>
                </div>
                <p>Percentage of users who voted in relation to all users</p>
            </div>

        </div>

        <div class='ui_box'>

            <div class='ui_box__inner'>
                <h2>
                    Male Rate
                </h2>

                <div class='stat'>
                    <span>${male}%</span>
                </div>
                <div class='progress' >
                    <div class='progress_bar' style="width:${male}%"></div>
                </div>
                <p>Percentage of voted males</p>
            </div>

        </div>

        <div class='ui_box'>

            <div class='ui_box__inner'>
                <h2>
                    Female Rate
                </h2>

                <div class='stat'>
                    <span>${female}%</span>
                </div>
                <div class='progress' >
                    <div class='progress_bar' style="width:${female}%"></div>
                </div>
                <p>Percentage of voted females</p>
            </div>

        </div>

    </div>


</div>
<script src="../js/poll_js.js"></script>
</body>
</html>