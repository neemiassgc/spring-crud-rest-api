const URL = "http://"+window.location.host;

function makeRequest(invokation) {
     if(invokation.readyState === XMLHttpRequest.DONE) {
        if(invokation.status === 200) {
            window.location.assign(URL);
            return;
        }
        alert("There was a problem with the response => "+invokation.status);
        return;
    }
}

function createUser(data) {
    let invokation = new XMLHttpRequest();

    if(!invokation) {
        alert("It was not possible to instantiate");
        return;
    }

    invokation.onreadystatechange = () => {
        makeRequest(invokation);
    };

    invokation.open("POST", URL+"/createUser", true);
    invokation.setRequestHeader("Content-Type", "application/json");
    invokation.send(data);
}

function editUser(data) {
    let invokation = new XMLHttpRequest();

    if(!invokation) {
        alert("It was not possible to instantiate");
        return;
    }

    invokation.onreadystatechange = () => {
        makeRequest(invokation);
    };

    invokation.open("PUT", URL+"/editUser", true);
    invokation.setRequestHeader("Content-Type", "application/json");
    invokation.send(data);
}

function deleteUser(data) {
    let invokation = new XMLHttpRequest();

    if(!invokation) {
        alert("It was not possible to instantiate");
        return;
    }

    invokation.onreadystatechange = () => {
        makeRequest(invokation);
    };

    invokation.open("DELETE", URL+"/deleteUser", true);
    invokation.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    invokation.send(data);
}