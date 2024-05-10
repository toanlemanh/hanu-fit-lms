document.addEventListener("DOMContentLoaded", function () {
	var customSelects = document.querySelectorAll(".custom-select");
	customSelects.forEach(function (customSelect) {
		customSelect.classList.remove("open");
	});
	// Assuming your form has the id "edit-course-form"
	const form = document.getElementById("edit-course-form");

	form.addEventListener("submit", function (event) {
		// Prevent the default form submission
		// event.preventDefault();

		// Get the selected lecturers from the custom select
		const selectedLecturersInput = document.querySelector(
			".custom-select .tags_input"
		);

		// Retrieve the value of the input field
		const selectedLecturers = selectedLecturersInput.value;

		// Now you can use the selected lecturers data as needed
		console.log("Selected lecturers:", selectedLecturers);

		// You can continue with form submission or other actions here if needed
		// For example, you can submit the form programmatically:
		// form.submit();
	});
});
