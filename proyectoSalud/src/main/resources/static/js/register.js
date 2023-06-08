const name = document.getElementById("form_name");
const surname = document.getElementById("form_surname");
const email = document.getElementById("form_email");
const password = document.getElementById("form_password");
const confPassword = document.getElementById("form_confpassword");

const formState = {
  name: false,
  surname: false,
  email: false,
  emailDomain: false,
  password: false,
  confPassword: false,
};

const errorMessage = {
  name: "El nombre debe tener entre 3 y 13 letras.",
  surname: "El apellido debe tener entre 3 y 13 letras.",
  email: "Solo letras, números, ., _ , - y entre 6 a 30 caracteres.",
  emailDomain: "Solo se permiten correos de gmail, outlook, hotmail y yahoo.",
  password:
    "Requisitos de contraseña: Minúscula, mayúscula, carácter especial, número, 6-10 caracteres.",
  confPassword: "Las contraseñas deben coincidir.",
};

const regex = {
  name: /^[a-zA-Z]{3,13}$/,
  email: /^[a-zA-Z0-9._-]{6,30}@[^@]*$/,
  password: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&+=]).{6,10}$/,
};

const validEmailDomain = [
  "gmail.com",
  "gmail.com.ar",
  "outlook.com",
  "outlook.com.ar",
  "hotmail.com",
  "yahoo.com",
  "yahoo.com.ar",
];

name.addEventListener("input", () => {
  formState.name = regex.name.test(name.value);
  errorCheck();
});

surname.addEventListener("input", () => {
  formState.surname = regex.name.test(surname.value);
  errorCheck();
});

email.addEventListener("input", () => {
  let domain = email.value.split("@").pop();
  formState.email = regex.email.test(email.value);
  formState.emailDomain = validEmailDomain.includes(domain);
  errorCheck();
});

password.addEventListener("input", () => {
  formState.password = regex.password.test(password.value);
  formState.confPassword = password.value === confPassword.value;
  errorCheck();
});

confPassword.addEventListener("input", () => {
  formState.confPassword = password.value === confPassword.value;
  errorCheck();
});

const toggleDisabledClass = () => {
  const isDisable = Object.values(formState).some((valid) => !valid);
  let form_submit = document.getElementById("form_submit");

  if (isDisable) {
    form_submit.setAttribute("class", "disabled");
  } else {
    form_submit.removeAttribute("disabled");
  }

  form_submit.classList.add(
    `${
      isDisable !== false
        ? "form-register__submit--disabled"
        : "form-register__submit"
    }`
  );

  form_submit.classList.remove(
    `${
      isDisable !== false
        ? "form-register__submit"
        : "form-register__submit--disabled"
    }`
  );
};

const errorCheck = () => {
  const cont_error = document.getElementById("cont-error");
  const error = document.getElementById("form-error");

  if (!formState.name && name.value !== "") {
    cont_error.classList.remove("form-register__cont-error--hidden");
    error.innerHTML = errorMessage.name;
    addDisabled();
  } else if (!formState.surname && surname.value !== "") {
    cont_error.classList.remove("form-register__cont-error--hidden");
    error.innerHTML = errorMessage.surname;
    addDisabled();
  } else if (!formState.email && email.value !== "") {
    cont_error.classList.remove("form-register__cont-error--hidden");
    error.innerHTML = errorMessage.email;
    addDisabled();
  } else if (!formState.emailDomain && email.value !== "") {
    cont_error.classList.remove("form-register__cont-error--hidden");
    error.innerHTML = errorMessage.emailDomain;
    addDisabled();
  } else if (!formState.password && password.value !== "") {
    cont_error.classList.remove("form-register__cont-error--hidden");
    error.innerHTML = errorMessage.password;
    addDisabled();
  } else if (!formState.confPassword && confPassword.value !== "") {
    cont_error.classList.remove("form-register__cont-error--hidden");
    error.innerHTML = errorMessage.confPassword;
    addDisabled();
  } else {
    cont_error.classList.add("form-register__cont-error--hidden");
    error.innerHTML = "";
    toggleDisabledClass();
  }
};

const addDisabled = () => {
  const form_submit = document.getElementById("form_submit");
  form_submit.classList.add("disabled");
  form_submit.disabled = true;
};

document.getElementById("cont-pass-eye").addEventListener("click", () => {
  let eye = document.getElementById("pass-eye");
  if (eye.classList.contains("fa-eye-slash")) {
    eye.classList.replace("fa-eye-slash", "fa-eye");
    document.getElementById("form_password").type = "text";
  } else {
    eye.classList.replace("fa-eye", "fa-eye-slash");
    document.getElementById("form_password").type = "password";
  }
});

document
  .getElementById("cont-confirmPass-eye")
  .addEventListener("click", () => {
    let eye = document.getElementById("confirmPass-eye");
    if (eye.classList.contains("fa-eye-slash")) {
      eye.classList.replace("fa-eye-slash", "fa-eye");
      document.getElementById("form_confpassword").type = "text";
    } else {
      eye.classList.replace("fa-eye", "fa-eye-slash");
      document.getElementById("form_confpassword").type = "password";
    }
  });
