const ALL_INPUTS = document.querySelectorAll("input");

function isUsernameValid() {
    let usernameValue = ALL_INPUTS[0].value;
    let checkPattern = (/[A-Za-z_]{3,}/i).exec(usernameValue);
    if(checkPattern) {
        return (usernameValue.length <= 30 && checkPattern.input === usernameValue);
    }
    return false;
}

function isPhoneValid() {
    let phoneValue = ALL_INPUTS[1].value;
    let checkPattern = (/\+\d{13,}/i).exec(phoneValue);
    if(checkPattern) {
        return (phoneValue.length <= 20 && checkPattern.input === phoneValue);
    }
    return false;
}

function isEmailValid() {
    let emailValue = ALL_INPUTS[2].value;
    let checkPattern = (/\w+@{1}\w+\.\w+/i).exec(emailValue);
    if(checkPattern) {
        return (emailValue.length <= 50 && checkPattern.input === emailValue);
    }
    return false;
}

function isCountryValid() {
    let countryValue = ALL_INPUTS[3].value;
    let checkPattern = (/[A-Za-z_]{5,}/i).exec(countryValue);
    if(checkPattern) {
        return (countryValue.length <= 20 && checkPattern.input === countryValue);
    }
    return false;
}

function validationAnimation() {
    let len = ALL_INPUTS.length;
    let formContainer = document.querySelector("#form-container");
    let validadeInputs = [isUsernameValid, isPhoneValid, isEmailValid, isCountryValid];

    for(let i = 0; i < len; i++) {
        ALL_INPUTS[i].onkeyup = () => {
            formContainer.style.boxShadow = "1px 1px 15px black";

            if(validadeInputs[i]()) {
                ALL_INPUTS[i].style.borderBottomColor = "green";
                return;
            }
            ALL_INPUTS[i].style.borderBottomColor = "red";
        }
    }
}

function isValidForm() {
    return (
        isUsernameValid() && isPhoneValid() &&
        isEmailValid() && isCountryValid()
    );
}

( () => {
    validationAnimation();
})();