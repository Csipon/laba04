var request;
function initRequest() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function loadMessage(paramName, id) {
    var url = '/maker/getMessage?paramName=' + paramName + '&id=' + id;
    try {
        initRequest();
        request.onreadystatechange = getInformation;
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}

function getInformation() {
    if (request.readyState == 4) {
        document.getElementById('taskMessages').innerHTML = parseJSON();
    }
}

function parseJSON() {
    var map = JSON.parse(request.responseText);
    var result = '<br/><div class="containerMessage">';

    for (var key in map) {
        var temp = map[key];
        for(var prop in temp){
            result += '<div class="messageblock">';
            result += '<b>' + prop + '</b>' +
                '<p>' + temp[prop] + '</p>' +
                '<p align="right" class="date">' + formatDate(new Date(Number(key))) + '</p>';
            result += '</div>';
        }
    }
    result += '</div>';
    return result;
}

function formatDate(date) {
    var monthNames = [
        "January", "February", "March",
        "April", "May", "June", "July",
        "August", "September", "October",
        "November", "December"
    ];

    var day = date.getDate();
    var monthIndex = date.getMonth();
    var year = date.getFullYear();

    return day + ' ' + monthNames[monthIndex] + ' ' + year + ' ' + date.getHours() + ":" + date.getMinutes();
}

