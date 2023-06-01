const img_inp = document.getElementById("user-profile__img-inp").addEventListener("change", (event) => {
	var input = event.target;
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function (e) {
			document.getElementById("preview_image").src = e.target.result;
		};
		reader.readAsDataURL(input.files[0]);
	}
})

const fileInput = document.getElementById('user-profile__img-inp');
const fileLabel = document.getElementById('user-profile__img-inp-label');
fileInput.addEventListener('change', function () {
	if (fileInput.files.length > 0) {
		// fileLabel.textContent = fileInput.files[0].name;
		fileLabel.textContent = 'Cambiar imagen';
	} else {
		fileLabel.textContent = 'Seleccione una foto';
	}
});

// Show and Hide email icons
document.getElementById("form-profile__email-edit-icon").addEventListener("click", () => {
	let edit_icon = document.getElementById("form-profile__email-edit-icon");
	let cancel_icon = document.getElementById("form-profile__email-cancel-icon");
	let email_inp = document.getElementById("user-profile__email-inp-cont");
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	email_inp.classList.toggle("form-profile--hidden");
});
document.getElementById("form-profile__email-cancel-icon").addEventListener("click", () => {
	let edit_icon = document.getElementById("form-profile__email-edit-icon");
	let cancel_icon = document.getElementById("form-profile__email-cancel-icon");
	let email_inpCont = document.getElementById("user-profile__email-inp-cont");
	let email_inp = document.getElementById("user-profile__email-inp");
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	email_inpCont.classList.toggle("form-profile--hidden");
	email_inp.value = "";
});
// Show and Hide pass and confpass icons
document.getElementById("form-profile__pass-edit-icon").addEventListener("click", () => {
	let edit_icon = document.getElementById("form-profile__pass-edit-icon");
	let cancel_icon = document.getElementById("form-profile__pass-cancel-icon");
	let pass_inp = document.getElementById("form-profile__pass");
	let confpass_cont = document.getElementById("user-profile__confpass-cont");
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	pass_inp.classList.toggle("form-profile--hidden");
	confpass_cont.classList.toggle("form-profile--hidden");
});
document.getElementById("form-profile__pass-cancel-icon").addEventListener("click", () => {
	let edit_icon = document.getElementById("form-profile__pass-edit-icon");
	let cancel_icon = document.getElementById("form-profile__pass-cancel-icon");
	let pass_inp = document.getElementById("form-profile__pass");
	let confpass_cont = document.getElementById("user-profile__confpass-cont");
	let confpass_inp = document.getElementById("form-profile__confpass");
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	pass_inp.classList.toggle("form-profile--hidden");
	confpass_cont.classList.toggle("form-profile--hidden");
	pass_inp.value = "";
	confpass_inp.value = "";
});
// Show and Hide Insurance icons
document.getElementById("form-profile__insurance-edit-icon").addEventListener("click", () => {
	let edit_icon = document.getElementById("form-profile__insurance-edit-icon");
	let cancel_icon = document.getElementById("form-profile__insurance-cancel-icon");
	let insurance_select = document.getElementById("patient-profile__insurance-select");
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	insurance_select.classList.toggle("form-profile--hidden");
});
window.onload = function () {
	let select = document.getElementById('patient-profile__insurance-select');
	select.dataset.estadoOriginal = select.selectedIndex;
};
function restaurarSelect() {
	let select = document.getElementById('patient-profile__insurance-select');
	select.selectedIndex = select.dataset.estadoOriginal;
}
document.getElementById("form-profile__insurance-cancel-icon").addEventListener("click", () => {
	let edit_icon = document.getElementById("form-profile__insurance-edit-icon");
	let cancel_icon = document.getElementById("form-profile__insurance-cancel-icon");
	let insurance_select = document.getElementById("patient-profile__insurance-select");
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	insurance_select.classList.toggle("form-profile--hidden");
	restaurarSelect();
});
// Show and Hide Contact icons
document.getElementById("form-profile__contact-edit-icon").addEventListener("click", () => {
	let edit_icon = document.getElementById("form-profile__contact-edit-icon");
	let cancel_icon = document.getElementById("form-profile__contact-cancel-icon");
	let contact_inp = document.getElementById("form-profile__contact-inp");
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	contact_inp.classList.toggle("form-profile--hidden");
});
document.getElementById("form-profile__contact-cancel-icon").addEventListener("click", () => {
	let edit_icon = document.getElementById("form-profile__contact-edit-icon");
	let cancel_icon = document.getElementById("form-profile__contact-cancel-icon");
	let contact_inp = document.getElementById("form-profile__contact-inp");
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	contact_inp.classList.toggle("form-profile--hidden");
	contact_inp.value = "";
});

removeActualHI();
function removeActualHI() {
	let html = document.getElementsByClassName("insurance-select__option");
	let arr = Array.from(html);
	let patient_insurance = document.getElementById("patient-profile__insurance-option--actual");
	for (let i = 0; i < arr.length; i++) {
		if (arr[i].getAttribute("value") == patient_insurance.getAttribute("value")) {
			arr[i].classList.add("form-profile--hidden");
		}
	}
}

