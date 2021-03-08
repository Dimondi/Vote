$(document).ready(function() {
    jQuery.ajax({
        url: "http://localhost:8080/polls",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        success: function (resultData) {
            displayEvents(resultData)
        },
        error: function (jqXHR, textStatus, errorThrown) {alert("GG");
        },
        timeout: 120000,
    });

    function displayEvents(resultData){
        $.each(resultData, function (i, obj) {
            $("#content").append('' +
                '  <div class="col-sm-6">\n' +
                '    <div class="card">\n' +
                '       <div class="card-body">\n' +
                '        <h5 class="card-title"><strong>Author:</strong> ' + obj.author.firstname + ' ' + obj.author.lastname + '</h5>\n' +
                '        <p class="card-text"><strong>Title:</strong> ' + obj.pollTitle +
                '         <strong>Voted:</strong> '+ obj.votedUsers.length +'</p>\n' +
                '        <div style="display: none;" class="row edit"><a class="btn btn-success edit" style="width: 50%;text-align: center;" href="/editPage/'+ obj.id +'" role="button">Edit</a>\n' +
                '        <a class="btn btn-danger edit" style="width: 50%;text-align: center;" href="/delete/'+ obj.id +'" role="button">Delete</a></div>\n' +
                '        <div class="row"><a class="btn btn-primary" style="width: 100%;" href="/poll/'+ obj.id +'" role="button">Vote</a></div>\n' +
                '      </div>\n' +
                '    </div>\n' +
                '  </div>');
        })
        var Role = $('#isAdmin').val();
        if(Role == "ADMIN"){
            $('.edit').css('display','flex');
        }
    }




    jQuery.ajax({
        url: "http://localhost:8080/popular",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        success: function (resultData) {
            displayEvents2(resultData)
        },
        timeout: 120000,
    });

    function displayEvents2(resultData){
        $.each(resultData, function (i, obj) {
            $("#popular").append('' +
                '  <div class="col-sm-6">\n' +
                '    <div class="card">\n' +
                '       <div class="card-body">\n' +
                '        <h5 class="card-title"><strong>Author:</strong> ' + obj.author.firstname + ' ' + obj.author.lastname + '</h5>\n' +
                '        <p class="card-text"><strong>Title:</strong> ' + obj.pollTitle +
                '         <strong>Voted:</strong> '+ obj.votedUsers.length +'</p>\n' +
                '        <div style="display: none;" class="row edit"><a class="btn btn-success edit" style="width: 50%;text-align: center;" href="/editPage/'+ obj.id +'" role="button">Edit</a>\n' +
                '        <a class="btn btn-danger edit" style="width: 50%;text-align: center;" href="/delete/'+ obj.id +'" role="button">Delete</a></div>\n' +
                '        <div class="row"><a class="btn btn-primary" style="width: 100%;" href="/poll/'+ obj.id +'" role="button">Vote</a></div>\n' +
                '      </div>\n' +
                '    </div>\n' +
                '  </div>');
        })
    }

    $('#popular_btn').click(
        function(){
            $('#popular').css('display','flex');
            $('#content').css('display','none');
            $('#latest').css('display','none')

        }
    )


    jQuery.ajax({
        url: "http://localhost:8080/latest",
        type: "GET",
        contentType: 'application/json; charset=utf-8',
        success: function (resultData) {
            displayEvents3(resultData)
        },
        timeout: 120000,
    });

    function displayEvents3(resultData){
        $.each(resultData, function (i, obj) {
            $("#latest").append('' +
                '  <div class="col-sm-6">\n' +
                '    <div class="card">\n' +
                '       <div class="card-body">\n' +
                '        <h5 class="card-title"><strong>Author:</strong> ' + obj.author.firstname + ' ' + obj.author.lastname + '</h5>\n' +
                '        <p class="card-text"><strong>Title:</strong> ' + obj.pollTitle +
                '         <strong>Voted:</strong> '+ obj.votedUsers.length +'</p>\n' +
                '        <div style="display: none;" class="row edit"><a class="btn btn-success edit" style="width: 50%;text-align: center;" href="/editPage/'+ obj.id +'" role="button">Edit</a>\n' +
                '        <a class="btn btn-danger edit" style="width: 50%;text-align: center;" href="/delete/'+ obj.id +'" role="button">Delete</a></div>\n' +
                '        <div class="row"><a class="btn btn-primary" style="width: 100%;" href="/poll/'+ obj.id +'" role="button">Vote</a></div>\n' +
                '      </div>\n' +
                '    </div>\n' +
                '  </div>');
        })
    }

    $('#latest_btn').click(
        function(){
            $('#latest').css('display','flex');
            $('#content').css('display','none');
            $('#popular').css('display','none');
        }
    )
});