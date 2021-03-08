$(document).ready(function() {

    $('#update').click(function (){
        var firstname = $('#firstName').val();
        var lastname = $('#lastName').val();
        var email = $('#email').val();
        var gender = $('#gender').val();
        var username = $('#username').val();
        var phone = $('#phone').val();
        // alert(firstname+ " " + lastname + " " + email + " " + gender + " " + username + " " + phone)
        jQuery.ajax({
            url: "http://localhost:8080/profile/edit",
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            data:{"firstname":firstname,"lastname":lastname,"email":email,"gender":gender,"username":username,"phone":phone},
            success: function (resultData) {
                alert(resultData)
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {alert("GG");
            },
            timeout: 120000,
        });
    })

    $('#passwordSettings').click(function (){
        $('#passwordLabel').css('display','block');
    })

    $('#confirm').click(function (){
        var current = $('#current').val();
        var newPassword = $('#new').val();
        var confirm = $('#repeat').val();
        jQuery.ajax({
            url: "http://localhost:8080/editPassword",
            type: "GET",
            contentType: 'application/json; charset=utf-8',
            data:{"current":current,"newPassword":newPassword,"confirm":confirm},
            success: function (resultData) {
                alert(resultData)
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {alert("GG");
            },
            timeout: 120000,
        });
    })

});