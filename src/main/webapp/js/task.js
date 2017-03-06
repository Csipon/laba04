function deleteTask(idTask, idSprint, idProject) {
    console.log('In delete' + idTask);
    if (confirm("Are you really want delete this task?")) {
        document.location.href = '/deleteTask?idTask=' + idTask + '&idSprint=' + idSprint + '&idProject=' + idProject;
    }
}


var idUser;
var taskId;
var sprintId;
var projectId;
var desc;
function makeMessage(idSender, messageTask, messageSprint, messageProject, description) {
    idUser = idSender;
    taskId = messageTask;
    sprintId = messageSprint;
    projectId = messageProject;
    desc = description;
    document.getElementById('sentMessage').disabled = true;
    document.getElementById("fieldMessage").innerHTML =
        '<td><p>Text :</td></br>' +
        '<td><textarea type="text" id="text" name="text" placeholder="Set here text..." rows="5" cols="45"></textarea></td> ' +
        '<input type="button" value="Send" onclick="message(idUser, taskId, sprintId, projectId, desc)">';
}


var request;
function initRequest() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}


function message(idSender, messageTask, messageSprint, messageProject, description) {
    var text = document.getElementById('text').value;
    var url = "sendMessage?text=" + text + "&idSender=" + idSender + "&messageTask=" + messageTask
        + "&messageSprint=" + messageSprint + "&messageProject=" + messageProject + "&description=" + description;

    initRequest();

    try {
        request.onreadystatechange = getInfo;
        request.open("POST", url, true);
        request.send();
    }
    catch (e) {
        alert("Unable to connect to server");
    }
}

function getInfo() {
    if (request.readyState == 4 && this.status == 200) {
        document.getElementById('fieldMessage').innerHTML = request.responseText;
        document.getElementById('sentMessage').disabled = false;
    }
}
