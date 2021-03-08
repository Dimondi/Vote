$(document).ready(function() {

    $("#edit").click(function (){
        poll_id = $("#pollId").val();
        title = $("#pollTitle").val();
        optionList = []
        var inputs = $(".option_value");
        for(var i = 0; i < inputs.length; i++) {
            optionList.push($(inputs[i]).val())
        }
        jQuery.ajax({
            url: "http://localhost:8080/edit/" + poll_id,
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            data:{"title":title,"optionList":optionList.toString()},
            success: function (resultData) {
                alert(resultData)
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {alert("GG");
            },
            timeout: 120000,
        });
    });


});