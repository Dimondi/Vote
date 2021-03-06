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
            $("#content").append('<a class="btn btn-primary" href="/poll/'+ obj.id +'" role="button">' + obj.pollTitle + '</a>\n');
        })
    }







    // $.each(obj.options, function (j, innerObj) {
    //     $("#pollUl"+obj.id).append(
    //         '                       <li class="list-group-item">\n' +
    //         '                            <div class="checkbox">\n' +
    //         '                                <label>\n' +
    //         '                                    <input type="checkbox" required value="' + innerObj.value +'">\n' + innerObj.value +
    //         '                                </label>\n' +
    //         '                            </div>\n' +
    //         '                        </li>'
    //     );
    // })

});