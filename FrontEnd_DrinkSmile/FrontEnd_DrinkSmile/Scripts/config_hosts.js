var SERVICES = {
    "hcm": {
        "login": "http://172.20.10.8:30000/",
        "management": "",
        "import_export": "",
        "animal_care": "",
        "report": ""
    },
    "hn": {
        "login": "",
        "management": "",
        "import_export": "",
        "animal_care": "",
        "report": ""
    }
}

function getAPI(areaId, whichService, urlAPI) {
    return SERVICES[areaId][whichService] + urlAPI;
}