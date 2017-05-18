var SERVICES = {
    "hcm": {
        "login": "http://localhost:30000/",
        "management": "http://localhost:20454/Service_Management/webresources/",
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