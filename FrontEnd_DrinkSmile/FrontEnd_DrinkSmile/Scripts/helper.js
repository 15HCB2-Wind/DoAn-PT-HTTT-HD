function callAjax(type, data, areaId, whichService, url, successCallback, errorCallback){
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
        error: errorCallback
    });
}