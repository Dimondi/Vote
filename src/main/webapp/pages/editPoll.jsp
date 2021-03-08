<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/editPoll_css.css" />
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script></head>
<body>
<div style="display: flex;flex-direction: row; justify-content: center;">
    <div class="col-md-3">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">
                <span class="glyphicon glyphicon-hand-right"></span>
                <input type="text" class="custom-file-input"  id="pollTitle" value="${poll.pollTitle}">
                    <input type="text" style="display: none;"  id="pollId" value="${poll.id}">
                </h3>
            </div>
            <div class="panel-body">
                <ul class="list-group">
                    <c:forEach items="${poll.options}" var="option">
                        <li class="list-group-item">
                            <div class="checkbox">
                                <label>
                                    <input class="form-control option_value" placeholder="answer" value="${option.value}">
                                </label>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="panel-footer text-center">
                <button type="button" id="edit" class="btn btn-primary btn-block btn-sm">
                    Edit</button>
        </div>
    </div>
</div>
<script src="../js/editPoll_js.js"></script>
</body>
</html>