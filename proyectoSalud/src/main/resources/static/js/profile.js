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

const colHTML_edit = document.getElementsByClassName("form-profile__edit-icon");
const colHTML_cancel = document.getElementsByClassName("form-profile__cancel-icon");
const arr_edit = Array.from(colHTML_edit);
const arr_cancel = Array.from(colHTML_cancel);

arr_edit.forEach(e => {
	e.addEventListener("click", (e) => {
		buttonAction(e.target);
	});
});
arr_cancel.forEach(e => {
	e.addEventListener("click", (e) => {
		buttonAction(e.target);
	});
});

function buttonAction(e) {
	let id = e.getAttribute("id");
	let arr_id = id.split("-");
	let edit_icon = document.getElementById(`${arr_id[0]}-edit-icon`);
	let cancel_icon = document.getElementById(`${arr_id[0]}-cancel-icon`);
	edit_icon.classList.toggle("form-profile--hidden");
	cancel_icon.classList.toggle("form-profile--hidden");
	switch (arr_id[0]) {
		case "email":
			let email_inpCont = document.getElementById("user-profile__email-inp-cont");
			let email_inp = document.getElementById("user-profile__email-inp");
			email_inpCont.classList.toggle("form-profile--hidden");
			if (arr_id[1] == "cancel") {
				email_inp.value = "";
			}
			break;
		case "pass":
			let pass_inp = document.getElementById("form-profile__pass");
			let confpass_cont = document.getElementById("user-profile__confpass-cont");
			let confpass_inp = document.getElementById("form-profile__confpass");
			pass_inp.classList.toggle("form-profile--hidden");
			confpass_cont.classList.toggle("form-profile--hidden");
			if (arr_id[1] == "cancel") {
				pass_inp.value = "";
				confpass_inp.value = "";
			}
			break;
		case "insurance":
			let insurance_select = document.getElementById("patient-profile__insurance-select");
			insurance_select.classList.toggle("form-profile--hidden");
			if (arr_id[1] == "cancel") {
				restaurarSelect();
			}
			break;
		case "contact":
			let contact_inp = document.getElementById("form-profile__contact-inp");
			contact_inp.classList.toggle("form-profile--hidden");
			if (arr_id[1] == "cancel") {
				contact_inp.value = "";
			}
			break;
		case "entry":
			case "exit":
				case "charge":
			let span = document.getElementById(`${arr_id[0]}-span`);
			let txt_cont = document.getElementById(`${arr_id[0]}-txt-cont`);
			let inp_cont = document.getElementById(`${arr_id[0]}-inp-cont`);
			span.classList.toggle("form-profile--hidden");
			txt_cont.classList.toggle("justify-content-between");
			inp_cont.classList.toggle("form-profile--hidden");
			if (arr_id[1] == "cancel") {
				if (arr_id[0] == "entry" || arr_id[0] == "exit") {
					let icon = document.getElementById(`${arr_id[0]}Time`);
					icon.value = "";
				} else if (arr_id[0] == "charge") {
					let icon = document.getElementById(`${arr_id[0]}`);
					icon.value = "";
				}
			}
			break;
	}
}

window.onload = function () {
	let select = document.getElementById('patient-profile__insurance-select');
	select.dataset.estadoOriginal = select.selectedIndex;
};
function restaurarSelect() {
	let select = document.getElementById('patient-profile__insurance-select');
	select.selectedIndex = select.dataset.estadoOriginal;
};

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

