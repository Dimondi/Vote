$(document).ready(function() {

    $("#vote").click(function (){
        poll_id = $("#pollId").val();
        var selectedVal = "";
        var selected = $("input[type='radio'][name='option']:checked");
        if (selected.length > 0) {
            selectedVal = selected.val();
            jQuery.ajax({
                url: "http://localhost:8080/vote/" + poll_id + "/" + selectedVal,
                type: "GET",
                contentType: 'application/json; charset=utf-8',
                success: function (resultData) {
                    alert(resultData)
                    location.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {;
                },
                timeout: 120000,
            });
        }
    });

    $("#view").click(function (){
        $('.rate').css('display','flex');
    })

});