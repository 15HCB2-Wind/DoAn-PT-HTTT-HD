

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
    { normal: "#2E2E2E", hover: "#4E4E4E" },
    { normal: "#4B515D", hover: "#6B717D" },
    { normal: "#3F729B", hover: "#5F92BB" },
    { normal: "#37474F", hover: "#57676F" },
    { normal: "#b71c1c", hover: "#D73c3c" },
    { normal: "#880e4f", hover: "#A82e6f" },
    { normal: "#4a148c", hover: "#6a34Ac" },
    { normal: "#311b92", hover: "#513bB2" },
    { normal: "#006064", hover: "#208084" },
    { normal: "#01579b", hover: "#2177Bb" },
    { normal: "#004d40", hover: "#206d60" },
    { normal: "#33691e", hover: "#53893e" },
    { normal: "#827717", hover: "#A29737" },
    { normal: "#64dd17", hover: "#84Fd37" },
    { normal: "#e65100", hover: "#FF7120" },
    { normal: "#bf360c", hover: "#Df562c" },
    { normal: "#3e2723", hover: "#5e4743" }
]