"use strict";

let validateName = false;
let validateEmail = false;
let validatePass = false;
let validateConfPass = false;

let inpNameWrited = false;
const form_name = document.getElementById("form_name");
form_name.addEventListener("input", () => {
    if (!inpNameWrited) {
        if (form_name.value.length >= 3) {
            validateName = true;
            inpNameWrited = true;
            errorCheck();
        }
    } else {
        if (form_name.value.length >= 3) {
            validateName = true;
            errorCheck();
        } else {
            validateName = false;
            errorCheck();
        }
    }
})

const form_email = document.getElementById("form_email");
form_email.addEventListener("input", () => {
    if (form_email.value.includes("@")) {
        validateEmail = false;
        errorCheck();
    }
    if (validateEmail == false && !form_email.value.includes("@")) {
        validateEmail = true;
        errorCheck();
    }
})

let inpPassWrited = false;
const form_pass = document.getElementById("pass-inp");
form_pass.addEventListener("input", () => {
    if (!inpPassWrited) {
        if (form_pass.value.length >= 6) {
            validatePass = true;
            inpPassWrited = true;
            errorCheck();
        }
    } else {
        if (form_pass.value.length < 6) {
            validatePass = false;
            errorCheck();
        } else {
            validatePass = true;
            errorCheck();
        }
    }
})

const form_confirmPass = document.getElementById("confirmPass-inp");
form_confirmPass.addEventListener("input", () => {
    if (validatePass == true) {
        if (form_pass.value == form_confirmPass.value) {
            validateConfPass = true;
            errorCheck();
        } else {
            validateConfPass = false;
            errorCheck();
        }
    } else {
        validateConfPass = false;
        errorCheck();
    }
})

function errorCheck() {
    let cont_error = document.getElementById("cont-error");
    let error = document.getElementById("form-error");
    if (validateName) {
        cont_error.classList.add("form-register__cont-error--hidden");
        if (validateEmail) {
            cont_error.classList.add("form-register__cont-error--hidden");
            if (validatePass) {
                cont_error.classList.add("form-register__cont-error--hidden");
                if (validatePass && validateConfPass) {
                    cont_error.classList.add("form-register__cont-error--hidden");
                    removeDisabled();
                } else if (!form_confirmPass.value == "") {
                    cont_error.classList.remove("form-register__cont-error--hidden");
                    error.innerHTML = "Las contraseñas deben coincidir."
                    addDisabled();
                }
            } else if (!form_pass.value == "") {
                addDisabled();
                cont_error.classList.remove("form-register__cont-error--hidden");
                error.innerHTML = "La contraseña debe ser de al menos 6 caracteres.";
            }
        } else if (!form_email.value == "") {
            addDisabled();
            cont_error.classList.remove("form-register__cont-error--hidden");
            error.innerHTML = "El mail no puede contener un arroba.";
        }
    } else {
        addDisabled();
        cont_error.classList.remove("form-register__cont-error--hidden");
        error.innerHTML = "El nombre debe ser de al menos 3 caracteres.";
    }
}

function addDisabled() {
    let form_submit = document.getElementById("form_submit");
    form_submit.setAttribute("disabled", "");
    form_submit.classList.remove("form-register__submit");
    form_submit.classList.add("form-register__submit--disabled");
}

function removeDisabled() {
    let form_submit = document.getElementById("form_submit");
    form_submit.removeAttribute("disabled");
    form_submit.classList.add("form-register__submit");
    form_submit.classList.remove("form-register__submit--disabled");
}

document.getElementById("cont-pass-eye").addEventListener("click", () => {
    let eye = document.getElementById("pass-eye");
    if (eye.classList.contains("fa-eye-slash")) {
        eye.classList.replace("fa-eye-slash", "fa-eye");
        document.getElementById("pass-inp").type = "text";
    } else {
        eye.classList.replace("fa-eye", "fa-eye-slash");
        document.getElementById("pass-inp").type = "password";
    }
})

document.getElementById("cont-confirmPass-eye").addEventListener("click", () => {
    let eye = document.getElementById("confirmPass-eye");
    if (eye.classList.contains("fa-eye-slash")) {
        eye.classList.replace("fa-eye-slash", "fa-eye");
        document.getElementById("confirmPass-inp").type = "text";
    } else {
        eye.classList.replace("fa-eye", "fa-eye-slash");
        document.getElementById("confirmPass-inp").type = "password";
    }
})