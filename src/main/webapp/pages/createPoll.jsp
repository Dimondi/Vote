<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="../css/createPoll_css.css" />
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script></head>
<body>
<div style="display: flex;flex-direction: row; justify-content: center;">
    <div style="width: 50%;" class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">
                <input class="form-control transparent-input" id="question" type='text' name='name' placeholder="Poll title..."  required>
            </h3>
        </div>
        <div class="panel-body">
            <ul id="content" class="list-group">
                <li class="list-group-item">
                    <div class="form-group">
                        <label for="optionNumber1">Option</label>
                        <input class="form-control" id="optionNumber1" placeholder="answer">
                    </div>
                </li>
                <li class="list-group-item">
                    <div class="form-group">
                        <label for="optionNumber2">Option</label>
                        <input class="form-control" id="optionNumber2" placeholder="answer">
                    </div>
                </li>
            </ul>
            <button style="width: 100%;" id="addOption" type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-plus"></span> option
            </button>
        </div>
        <div class="panel-footer text-center">
            <button type="button" id="createPoll" class="btn btn-success btn-block btn-sm">
                Create poll</button>
            <a href="#" id="clear" class="small">Clear</a></div>
    </div>
</div>
<script src="../js/createPoll_js.js"></script>
</body>
</html>