var request;
var v;
function validLogin() {
    v = document.register.login.value;
    var url = "/admin/valid?login=" + v;

    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
    }

    try {
        request.onreadystatechange = getInfo;
        request.open("GET", url, true);
        request.send();
    }
    catch (e) {
        alert("Unable to connect to server");
    }
}

function getInfo() {
    if (request.readyState == 4) {
        var val = request.responseText;
        if(val == 'true' && v.length != 0) {
            document.getElementById('result').innerHTML = 'This login is available';
            document.getElementById('result').style.color = 'green';
            document.getElementById('submit').disabled = false;
        }else if (v.length == 0){
            document.getElementById('result').innerHTML = '';
            document.getElementById('submit').disabled = true;
        }else {
            document.getElementById('result').innerHTML = 'This login is not available';
            document.getElementById('result').style.color = 'red';
            document.getElementById('submit').disabled = true;
        }
    }
}