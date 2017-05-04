function callAjax(type, data, areaId, whichService, url, successCallback) {
    $.ajax({
        type: type,
        dataType: 'json',
        data: data,
        url: getAPI(areaId, whichService, url),
        beforeSend: function () {
            $('#loader').show();
        },
        complete: function () {
            $('#loader').hide();
        },
        success: successCallback,
        error: function () {
            $("#error").show();
            $("#error").text("Không thể kết nối tới máy chủ");
        }
    });
}

function callAjaxToken(type, data, whichService, url, successCallback) {
    $.ajax({
        type: 'post',
        dataType: 'json',
        data: { token: Cookies.get("token"), value: Cookies.get("role") },
        url: getAPI(Cookies.get("area"), "login", "account/checktoken"),
        beforeSend: function () {
            $('#loader').show();
        },
        complete: function () {
            $('#loader').hide();
        },
        success: function (data) {
            if (data) {
                callAjax(type, data, Cookies.get("area"), whichService, url, successCallback);
            }
            else {
                Cookies.remove("token");
                Cookies.remove("area");
                Cookies.remove("role");
                location.href = "/Account/Login";
            }
        },
        error: function () {
            $("#error").show();
            $("#error").text("Không thể kết nối tới máy chủ");
        }
    });
}