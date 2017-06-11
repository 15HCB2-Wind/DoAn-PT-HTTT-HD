

//Ajax async but tempData sync
var AjaxCounter = 0;
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
            toastr["error"]("Không thể kết nối tới máy chủ!");
            $("#error").show();
            $("#error").text("Không thể kết nối tới máy chủ!");
        }
    });
}


//get params from url
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


//download content to file
function download(data, filename, type) {
    var a = document.createElement("a");
    var file = new Blob([data], { type: type });
    a.href = URL.createObjectURL(file);
    a.download = filename;
    a.click();
}


//read file
function readFileContent(file, callback, opt_encoding) {
    var encoding = opt_encoding || "UTF-8";
    var reader = new FileReader();

    // If we use onloadend, we need to check the readyState.
    reader.onloadend = function (e) {
        if (e.target.readyState == FileReader.DONE) { // DONE == 2
            callback(e.target.result);
        }
    };
    
    reader.readAsText(file, encoding);
}


//print json
JSON.printToElement = function (elementId, json) {
    $('#' + elementId).empty();
    $('head').append(
        "<style>" +
            ".JsonView {outline: 1px solid #ccc; padding: 5px; margin: 5px; }" +
            ".JV-string { color: green; }" +
            ".JV-number { color: darkorange; }" +
            ".JV-boolean { color: blue; }" +
            ".JV-null { color: magenta; }" +
            ".JV-key { color: red; }" +
        "</style>");
    var pre = document.createElement('pre');
    pre.setAttribute("class", "JsonView");
    document.getElementById(elementId).appendChild(pre).innerHTML = JSON.syntaxHighlight(json);
}


//format json
JSON.syntaxHighlight = function (json) {
    json = JSON.stringify(json, undefined, 4);
    json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'JV-number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'JV-key';
            } else {
                cls = 'JV-string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'JV-boolean';
        } else if (/null/.test(match)) {
            cls = 'JV-null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}


//some colors
var colors = [
    { normal_hex: "#2E2E2E", hover_hex: "#4E4E4E", normal: "rgba(46, 46, 46, 1)",   hover: "rgba(78, 78, 78, 1)" },
    { normal_hex: "#4B515D", hover_hex: "#6B717D", normal: "rgba(75, 81, 93, 1)",   hover: "rgba(107, 113, 125, 1)" },
    { normal_hex: "#3F729B", hover_hex: "#5F92BB", normal: "rgba(63, 114, 155, 1)", hover: "rgba(95, 146, 187, 1)" },
    { normal_hex: "#37474F", hover_hex: "#57676F", normal: "rgba(55, 71, 79, 1)", hover: "rgba(87, 103, 111, 1)" },
    { normal_hex: "#b71c1c", hover_hex: "#D73c3c", normal: "rgba(183, 28, 28, 1)", hover: "rgba(215, 60, 60, 1)" },
    { normal_hex: "#880e4f", hover_hex: "#A82e6f", normal: "rgba(136, 14, 79, 1)", hover: "rgba(168, 46, 111, 1)" },
    { normal_hex: "#4a148c", hover_hex: "#6a34Ac", normal: "rgba(74, 20, 140, 1)", hover: "rgba(106, 52, 172, 1)" },
    { normal_hex: "#311b92", hover_hex: "#513bB2", normal: "rgba(49, 27, 146, 1)", hover: "rgba(81, 59, 178, 1)" },
    { normal_hex: "#006064", hover_hex: "#208084", normal: "rgba(0, 96, 100, 1)", hover: "rgba(32, 128, 132, 1)" },
    { normal_hex: "#01579b", hover_hex: "#2177Bb", normal: "rgba(1, 87, 155, 1)", hover: "rgba(32, 109, 96, 1)" },
    { normal_hex: "#004d40", hover_hex: "#206d60", normal: "rgba(0, 77, 64, 1)", hover: "rgba(32, 109, 96, 1)" },
    { normal_hex: "#33691e", hover_hex: "#53893e", normal: "rgba(51, 105, 30, 1)", hover: "rgba(83, 137, 62, 1)" },
    { normal_hex: "#827717", hover_hex: "#A29737", normal: "rgba(130, 119, 23, 1)", hover: "rgba(162, 151, 55, 1)" },
    { normal_hex: "#64dd17", hover_hex: "#84Fd37", normal: "rgba(100, 221, 23, 1)", hover: "rgba(132, 253, 55, 1)" },
    { normal_hex: "#e65100", hover_hex: "#FF7120", normal: "rgba(230, 81, 0, 1)", hover: "rgba(255, 113, 32, 1)" },
    { normal_hex: "#bf360c", hover_hex: "#Df562c", normal: "rgba(191, 54, 12, 1)",  hover: "rgba(223, 86, 44, 1)" },
    { normal_hex: "#3e2723", hover_hex: "#5e4743", normal: "rgba(62, 39, 35, 1)",   hover: "rgba(94, 71, 67, 1)" }
]


//date diff
var DateDiff = {

    inDays: function (d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();

        return parseInt((t2 - t1) / (24 * 3600 * 1000));
    },

    inWeeks: function (d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();

        return parseInt((t2 - t1) / (24 * 3600 * 1000 * 7));
    },

    inMonths: function (d1, d2) {
        var d1Y = d1.getFullYear();
        var d2Y = d2.getFullYear();
        var d1M = d1.getMonth();
        var d2M = d2.getMonth();

        return (d2M + 12 * d2Y) - (d1M + 12 * d1Y);
    },

    inYears: function (d1, d2) {
        return d2.getFullYear() - d1.getFullYear();
    }

}