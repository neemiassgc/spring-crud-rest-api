const url = "https://"+window.location.host;

function createUser(data) {
    let invokation = new XMLHttpRequest();

    if(!invokation) {
        alert("It was not possible to instantiate");
        return;
    }

    invokation.onreadystatechange = () => {
        if(invokation.readyState === XMLHttpRequest.DONE) {
            if(invokation.status === 200) {
                window.location.assign(url);
                return;
            }
            alert("There was a problem with the response => "+invokation.status);
            return;
        }

    };

    invokation.open("POST", url+"/createUser", true);
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
        if(invokation.readyState === XMLHttpRequest.DONE) {
            if(invokation.status === 200) {
                window.location.assign(url);
                return;
            }
            alert("There was a problem with the response => "+invokation.status);
            return;
        }
    };

    invokation.open("PUT", url+"/editUser", true);
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
        if(invokation.readyState === XMLHttpRequest.DONE) {
            if(invokation.status === 200) {
                window.location.assign(url);
                return;
            }
            alert("There was a problem with the response => "+invokation.status);
            return;
        }
    };

    invokation.open("DELETE", url+"/deleteUser", true);
    invokation.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    invokation.send(data);
}