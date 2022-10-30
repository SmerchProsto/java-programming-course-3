var phoneEmloyeer = document.querySelector('#input-phone-employeer');
var nameEmloyeer = document.querySelector('#input-name-employeer');
var checkboxPhoneEmployeer = document.getElementById('phone-checkbox');

var labelPlaceholder = document.querySelector('.main-form-placeholder');
var namePlaceholder = document.querySelector('#label-name-placeholder');

var checkboxPhoneEmployeerChecked = document.querySelector('.input-phone-employeer:checked');


//Улучшенное 1е задание, плавно уходящая подсказка для поля номера
phoneEmloyeer.addEventListener('change', (event) => {
	if (phoneEmloyeer.value.length !== 0) {
		labelPlaceholder.style.transform = 'translate(-15px, -30px) scale(0.8)';
	} else {
		labelPlaceholder.style = '';
		labelPlaceholder.style.transition = 'all 1s';

	}
  
  if (checkboxPhoneEmployeerChecked) {

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