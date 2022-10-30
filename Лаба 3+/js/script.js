
var phoneEmployeer = document.querySelector('#input-phone-employeer');
var nameEmloyeer = document.querySelector('#input-name-employeer');
var checkboxPhoneEmployeer = document.getElementById('phone-checkbox');
var test = 123;

var lastnameEmployeer = document.querySelector('#input-lastname-employeer');
var lastnameEmployeerPlaceholder = document.querySelector('#label-lastname-placeholder');
var lastnameCheckbox = document.querySelector('#lastname-checkbox');

var table = document.querySelector('.main-form-table');

var labelPlaceholder = document.querySelector('.main-form-placeholder');
var namePlaceholder = document.querySelector('#label-name-placeholder');

var checkboxPhoneEmployeer= document.querySelector('#phone-checkbox');

var parsePhoneNumber/* = parseInt(phoneEmployeer.value, 10);*/;

var title = document.title;




	//Улучшенное 1е задание, плавно уходящая подсказка для поля номера
	phoneEmployeer.addEventListener('change', (event) => {
		checkboxPhoneEmployeer.checked = false;
		if (phoneEmployeer.value.length !== 0) {
			labelPlaceholder.style.transform = 'translate(-15px, -30px) scale(0.8)';
		} else {
			labelPlaceholder.style = '';
			labelPlaceholder.style.transition = 'all 1s';
		}
	});


	//Улучшенное 1е задание, плавно уходящая подсказка для поля имени

	nameEmloyeer.addEventListener('change', (event) => {

	  if (nameEmloyeer.value.length !== 0) {
			namePlaceholder.style.transform = 'translate(-15px, -30px) scale(0.8)';
		} else {
			namePlaceholder.style = '';
			namePlaceholder.style.transition = 'all 1s';
		}
	  
	});

	lastnameEmployeer.addEventListener('change', (event) => {
		if (lastnameEmployeer.value.length !== 0) {
			lastnameEmployeerPlaceholder.style.transform = 'translate(-15px, -30px) scale(0.8)';
		} else {
			lastnameEmployeerPlaceholder.style = '';
			lastnameEmployeerPlaceholder.style.transition = 'all 1s';
		}

		/*if (lastnameCheckbox.checked && lastnameEmployeer.value.length !== 0) {
			localStorage.lastname = lastnameEmployeer.value;
		}*/
	});




	//2 задание с использованием parseInt
	checkboxPhoneEmployeer.addEventListener('change', (event) => {
		parsePhoneNumber = parseInt(phoneEmployeer.value, 10);
		if (checkboxPhoneEmployeer.checked && phoneEmployeer.value.length !== 0) {
	  	if (isNaN(parsePhoneNumber)) {
	  		alert('Необходимо ввод числа в поле номера.');
	  		checkboxPhoneEmployeer.checked = false;
	  	}
	  }
	});

lastnameCheckbox.addEventListener('change', (event) => {
	if (lastnameCheckbox.checked && lastnameEmployeer.value.length !== 0) {
			localStorage.lastname = lastnameEmployeer.value;
	}
});

	tableDeleteButtons = document.querySelectorAll('.table-element-button');

	formTableTds = document.querySelectorAll('.form-table-td');


	/*for (var i = 0; i < tableDeleteButtons.length - 1; i++) {
		tableDeleteButtons[i].setAttribute('id', 'table-element-button-' + i);

		tableDeleteButtons[i].addEventListener("click", function(event){
	  	event.preventDefault()
	  	var isDelete = confirm('Вы хотите удалить данную строку?');
	  	if (isDelete) {
	  		formTableTds[i].remove();
	  	}

		});
	}
	*/

	table.onclick = function(event) {
	  let target = event.target; // где был клик?

	  if (target.className != 'table-element-button') return; // не на btn? тогда не интересует

	  var isDelete = confirm('Вы хотите удалить данную строку?');
	  if (isDelete) {
	  		target.offsetParent.remove();
	  	}
	};

