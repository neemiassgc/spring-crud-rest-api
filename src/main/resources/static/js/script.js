const DATA_ROWS = document.querySelectorAll(".table-row");
const MAIN_BUTTON = document.getElementById("main-button");

let userId = null;

function clickEditButtonIcon() {
    let editButtonIconList = document.querySelectorAll(".edit-button-icon");
    let len = editButtonIconList.length;

    for(let i = 0; i < len; i++) {
        editButtonIconList[i].onclick = () => {
            let tdElements = DATA_ROWS[i].getElementsByTagName("td");

            for(let j = 0; j < 4; j++) {
                ALL_INPUTS[j].value = tdElements[j + 1].innerHTML;
            }

            userId = tdElements[0].innerHTML;

            MAIN_BUTTON.innerHTML = "EDIT USER";
            MAIN_BUTTON.style.backgroundColor = "orange";
        };
    }
}

function clickDeleteButtonIcon() {
    let deleteButtonIconList = document.querySelectorAll(".delete-button-icon");
    let len = deleteButtonIconList.length;

    for(let i = 0; i < len; i++) {
        deleteButtonIconList[i].onclick = () => {
            // delete user
            deleteUser("id="+DATA_ROWS[i].getElementsByTagName("td")[0].innerHTML);
        };
    }
}


function mainButtonClick() {
    let formContainer = document.getElementById("form-container");

    // action of add user
    const addUserAction = () => {
        if(isValidForm()) {
            let body = {
                id: (
                    parseInt(
                        DATA_ROWS[DATA_ROWS.length - 1]
                        .getElementsByTagName("td")[0].innerHTML
                    ) + 1
                ),
                username: ALL_INPUTS[0].value,
                phone: ALL_INPUTS[1].value,
                email: ALL_INPUTS[2].value,
                country: ALL_INPUTS[3].value
            };

            createUser(JSON.stringify(body));
            return;
        }

        formContainer.style.boxShadow = "1px 1px 15px red";
    };

    // action of edit user
    const editUserAction = () => {
        if(isValidForm()) {
            let body = {
                id: userId,
                username: ALL_INPUTS[0].value,
                phone: ALL_INPUTS[1].value,
                email: ALL_INPUTS[2].value,
                country: ALL_INPUTS[3].value
            };
            
            editUser(JSON.stringify(body));
            return;
        }
        
        formContainer.style.boxShadow = "1px 1px 15px red";
    };

    document.getElementById("main-button").onclick = () => {

        // add user
        if(MAIN_BUTTON.innerHTML === "ADD USER") {
            addUserAction();
            return;
        }
        
        // edit user
       editUserAction();
    };
}

( () => {

    clickEditButtonIcon();

    clickDeleteButtonIcon();

    mainButtonClick();

})();