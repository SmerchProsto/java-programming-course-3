var lastnameInput = document.querySelector('#input-lastname-employeer');
var lastnamePlaceholder = document.querySelector('#label-name-placeholder');
lastnameInput.value = localStorage.lastname;
lastnamePlaceholder.style.transform = 'translate(-15px, -30px) scale(0.8)';

lastnameInput.addEventListener('change', (event) => {
	if (lastnameInput.value.length !== 0) {
		lastnamePlaceholder.style.transform = 'translate(-15px, -30px) scale(0.8)';
	} else {
		lastnamePlaceholder.style = '';
		lastnamePlaceholder.style.transition = 'all 1s';
	}
});