const container = document.querySelector('.container');
const btn1 = document.getElementById('btn-sign-in');
const btn2 = document.getElementById('btn-sign-up');

btn1.addEventListener('click', () => {
    container.classList.remove('toggle');
})

btn2.addEventListener('click', () => {
    container.classList.add('toggle');
})