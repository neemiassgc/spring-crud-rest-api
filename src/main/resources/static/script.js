( () => {
    const dataRows = document.querySelectorAll(".table-row");
    const mainButton = document.getElementById("main-button");
    const inputAllEntries = document.querySelectorAll("input");
    const url = "https://"+window.location.host;

    let userId = null;

    const validadeEntries = {
        isUsernameValid: () => {
            let usernameValue = inputAllEntries[0].value;
            let checkPattern = (/[A-Za-z_]{3,}/i).exec(usernameValue);
            if(checkPattern) {
                return (usernameValue.length <= 30 && checkPattern.input === usernameValue);
            }
            return false;
        },

        isPhoneValid: () => {
            let phoneValue = inputAllEntries[1].value;
            let checkPattern = (/\+\d{13,}/i).exec(phoneValue);
            if(checkPattern) {
                return (phoneValue.length <= 20 && checkPattern.input === phoneValue);
            }
            return false;
        },

        isEmailValid: () => {
            let emailValue = inputAllEntries[2].value;
            let checkPattern = (/\w+@{1}\w+\.\w+/i).exec(emailValue);
            if(checkPattern) {
                return (emailValue.length <= 50 && checkPattern.input === emailValue);
            }
            return false;
        },

        isCountryValid: () => {
            let countryValue = inputAllEntries[3].value;
            let checkPattern = (/[A-Za-z_]{5,}/i).exec(countryValue);
            if(checkPattern) {
                return (countryValue.length <= 20 && checkPattern.input === countryValue);
            }
            return false;
        }
    };

    function validationAnimation() {
        let entryNames = ["isUsernameValid", "isPhoneValid", "isEmailValid", "isCountryValid"];

        for(let i = 0; i < inputAllEntries.length; i++) {
            inputAllEntries[i].onkeyup = () => {
                document.getElementById("form-container").style.boxShadow = "1px 1px 15px black";

                if(validadeEntries[entryNames[i]]()) {
                    inputAllEntries[i].style.borderBottomColor = "green";
                    return;
                }
                inputAllEntries[i].style.borderBottomColor = "red";
            }
        }
    }

    function isValidForm() {
        let entryNames = ["isUsernameValid", "isPhoneValid", "isEmailValid", "isCountryValid"];
        for(let entry of entryNames) {
            if(!validadeEntries[entry]()) {
                return false;
            }
        }
        return true;
    }

    function editButtonIconClick() {
        let editButtonList = document.querySelectorAll(".edit-button-icon");

        for(let i = 0; i < editButtonList.length; i++) {
            editButtonList[i].onclick = () => {
                let tdElements = dataRows[i].getElementsByTagName("td");

                for(let j = 0; j < 4; j++) {
                    inputAllEntries[j].value = tdElements[j + 1].innerHTML;
                }

                userId = tdElements[0].innerHTML;

                mainButton.innerHTML = "EDIT USER";
                mainButton.style.backgroundColor = "orange";
            };
        }
    }

    function deleteButtonIconClick() {
        let deleteButtonList = document.querySelectorAll(".delete-button-icon");

        for(let i = 0; i < deleteButtonList.length; i++) {
            deleteButtonList[i].onclick = () => {
                // delete user
                deleteUser("id="+dataRows[i].getElementsByTagName("td")[0].innerHTML);
            };
        }
    }


    function mainButtonClick() {
        document.getElementById("main-button").onclick = () => {
            let body = {
                username: inputAllEntries[0].value,
                phone: inputAllEntries[1].value,
                email: inputAllEntries[2].value,
                country: inputAllEntries[3].value
            };

            const addUserAction = () => {
                if(!isValidForm()) {
                    document.getElementById("form-container").style.boxShadow = "1px 1px 15px red";
                    return;
                }
                
                body.id = (dataRows.length + 1);
                createUser(JSON.stringify(body));
            };

            const editUserAction = () => {
                if(!isValidForm()) {
                    document.getElementById("form-container").style.boxShadow = "1px 1px 15px red";
                    return;
                }

                body.id = userId;
                editUser(JSON.stringify(body));
            };

            // add user
            if(mainButton.innerHTML === "ADD USER") {
                addUserAction();
                return;
            }
            
            // edit user
           editUserAction();
        };
    }

    validationAnimation();

    editButtonIconClick();

    deleteButtonIconClick();

    mainButtonClick();

})();