$(document).ready(function() {
    id = 3;

    function displayOption() {
        if(id < 10) {
            $("#content").append('<li class="list-group-item">\n' +
                '                    <div class="form-group">\n' +
                '                        <label for="optionNumber' + id + '">Option</label>\n' +
                '                        <input class="form-control" id="optionNumber'+ id +'"placeholder="answer">\n' +
                '                    </div>\n' +
                '                </li>');

            id = id + 1;
        }
        else{
            alert("Max 10 options")
        }
    }

    $('#addOption').click(function() {
        displayOption();
    })

    $('#createPoll').click(function () {
        title = $('#question').val();
        optionList = [];
        for (j = 1; j <= id; j++) {
            optionList.push($("#optionNumber"+j).val())
        }
        optionList.pop()
            jQuery.ajax({
                url: "http://localhost:8080/createPoll",
                type: "GET",
                contentType: 'application/json; charset=utf-8',
                data:{"title":title,"optionList":optionList.toString()},
                success: function (resultData) {
                    alert("Poll created")
                    location.reload()
                },
                error: function () {
                    alert("Invalid Input");
                },
                timeout: 120000,
            });
    })

    $('#clear').click(function (){
        location.reload()
    })
});