const img_inp = document.getElementById("profile_picture").addEventListener("change", (event) => {
	var input = event.target;
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function (e) {
			document.getElementById("preview_image").src = e.target.result;
		};
		reader.readAsDataURL(input.files[0]);
	}
})