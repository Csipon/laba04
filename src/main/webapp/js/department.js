var request;

function initRequest() {
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }
}


function deleteDepartment(idDepartment, confirm) {
    var url = '/admin/deleteDepartment?id=' + idDepartment + '&confirm=' + confirm;
    initRequest();

    request.onreadystatechange = updateAfterDelete;
    request.open("POST", url, true);
    request.send();
}

function updateAfterDelete() {
    if (request.readyState == 4 && this.status == 200){
        location.reload();
    }
}