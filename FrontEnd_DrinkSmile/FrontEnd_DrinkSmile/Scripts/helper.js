function callAjax(type, data, areaId, whichService, url, successCallback) {
    $.ajax({
        type: type,
        dataType: 'json',
        data: data,
        url: getAPI(areaId, whichService, url),
        beforeSend: function () {
            $('#loader').show();
            $("#error").hide();
            $("fieldset").attr("disabled", "true");
        },
        complete: function () {
            $('#loader').hide();
            $("fieldset").removeAttr("disabled");
        },
        success: function (data) {
            if (data.IsTokenTimeout) {
                Cookies.remove("token");
                Cookies.remove("area");
                Cookies.remove("name");
                Cookies.remove("role");
                location.href = "/Account/Login?ReturnUrl=" + location.pathname;
            }
            else {
                successCallback(data);
            }
        },
        error: function () {
            toastr["error"]("Không thể kết nối tới máy chủ !");
            $("#error").show();
            $("#error").text("Không thể kết nối tới máy chủ");
        }
    });
}

//setTimeout(function () {
//    location.href = "/Home/Index";
//}, 2000);