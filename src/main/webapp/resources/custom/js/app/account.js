function signUp() {

    var data = {
        username: $("#username").val(),
        password: $("#password").val(),
        email: $("#email").val()
    };

    $.ajax({
        url: '/account/signUp',
        data: data,
        type: 'POST',
        async: false,
        error: function (error) {
            toastr.error('Error!');
        },
        success: function (data) {
            console.log(data);
            window.location.reload();
        }
    });
}