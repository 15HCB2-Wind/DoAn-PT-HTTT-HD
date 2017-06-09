var SERVICES = {
    "hcm": {
        "login": "http://localhost:30000/",
        "management": "http://localhost:8080/Service_Management/webresources/",
        "import_export": "http://localhost:8080/Service_ImportExport/webresources/",
        "animal_care": "http://localhost:28289/"
    },
    "hn": {
        "login": "",
        "management": "",
        "import_export": "",
        "animal_care": ""
    }
}

function getAPI(areaId, whichService, urlAPI) {
    return SERVICES[areaId][whichService] + urlAPI;
}