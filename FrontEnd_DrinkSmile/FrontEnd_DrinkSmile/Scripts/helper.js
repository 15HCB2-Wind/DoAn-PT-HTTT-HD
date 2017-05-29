﻿var AjaxCounter = 0;
var AjaxTempData = {};
function callAjax(type, data, areaId, whichService, url, successCallback, tempData) {
    var u = getAPI(areaId, whichService, url)
    $.ajax({
        headers: { 'Content-Type': 'application/json' },
        type: type,
        dataType: 'json',
        data: JSON.stringify(data),
        url: getAPI(areaId, whichService, url),
        beforeSend: function (jqXHR) {
            jqXHR['key-temp-data'] = AjaxCounter++;
            AjaxTempData[jqXHR['key-temp-data']] = tempData;

            $('#loader').show();
            $("#error").hide();
            $("fieldset").attr("disabled", "true");
        },
        complete: function (jqXHR) {
            AjaxTempData[jqXHR['key-temp-data']] = null;

            $('#loader').hide();
            $("fieldset").removeAttr("disabled");
        },
        success: function (data, textStatus, jqXHR) {
            if (data.IsTokenTimeout) {
                Cookies.remove("token");
                Cookies.remove("area");
                Cookies.remove("name");
                Cookies.remove("role");
                location.href = "/Account/Login?ReturnUrl=" + location.pathname;
            }
            else {
                successCallback(data, AjaxTempData[jqXHR['key-temp-data']]);
            }
        },
        error: function () {
            toastr["error"]("Không thể kết nối tới máy chủ !");
            $("#error").show();
            $("#error").text("Không thể kết nối tới máy chủ");
        }
    });
}

var QueryString = function () {
    // This function is anonymous, is executed immediately and 
    // the return value is assigned to QueryString!
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [query_string[pair[0]], decodeURIComponent(pair[1])];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
}();