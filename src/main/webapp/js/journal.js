var request;
function initRequest() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function loadJournals(paramName, id, idEmployee) {
    var url = 'loadJournals?paramName=' + paramName + '&id=' + id + '&idEmployee=' + idEmployee;

    initRequest();

    try {
        request.onreadystatechange = getView;
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}

function loadSprintJournals(paramName, id) {
    var url = 'loadSprintJournals?paramName=' + paramName + '&id=' + id;

    initRequest();
    try {
        request.onreadystatechange = getView;
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}


function getView() {
    if (request.readyState == 4) {
        document.getElementById('fieldJournal').innerHTML = parseJournals(request.responseText);
    }
}


function parseJournals(line) {
    var journals = JSON.parse(line);

    var result = '<div class="containerJournal">';
    for (var i = 0; i < journals.length; i++) {
        var journal = journals[i];
        result +='<div class="journal">';
        result += '<p>' + journal.task.name +'</p>';
        if (journal.startTask != null) {
            result += '<p>Start make this task :' + formatDate(new Date(journal.startTask)) + '</p>';
        } else {
            result += '<p>This task is not started</p>';
        }
        if (journal.finishTask != null) {
            result += '<p>Task is finished : ' + formatDate(new Date(journal.finishTask)) + '</p>';
        }

        result +='<p>Description : </p>'
            + '<p>' + journal.description + '</p>';
        result += '<button onclick="showMakers(' + journal.id + ')">Show makers</button>';
        result +='</div>';
    }
    result += '</div>';
    return result;
}

var map;
function showMakers(idJournal) {
    var url = 'showMakers?idJournal=' + idJournal;

    initRequest();

    try {
        request.onreadystatechange = getMakers;
        request.open("GET", url, true);
        request.send();
    } catch (e) {
        alert("Unable to connect to server");
    }
}


function getMakers() {
    if (request.readyState == 4) {
        document.getElementById('fieldMakers').innerHTML = parseMakers(request.responseText);
    }
}

function parseMakers(line) {
    var list = JSON.parse(line);

    var result = '';
    for (var i = 0 ; i < list.length; i++){
        var emp = list[i];
        result += '<p>' +
            '<a href="/idEmp?id=' + emp.id + '">' + emp.name + ' ' + emp.surname + '</a>' +
            ', level qualification ' + emp.levelQualification +
        '</p>';
    };
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