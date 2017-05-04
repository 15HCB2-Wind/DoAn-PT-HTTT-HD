function callAjax(type, data, areaId, whichService, url, successCallback){
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