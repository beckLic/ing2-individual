document.addEventListener('DOMContentLoaded', () =>{
    const formulario = document.querySelector('#formulario');
    const parrafo = document.querySelector('#mensaje');
    parrafo.style.display = 'none';

    formulario.addEventListener('submit', (e) =>{
        e.preventDefault();
        const nombre = document.querySelector('#nombre').value;  
        const contraseña = document.querySelector('#contraseña').value;  
        
        if(nombre === '' || contraseña === ''){
            parrafo.style.display = 'block';
            parrafo.textContent = 'Por favor complete todos los campos';
            parrafo.style.backgroundColor = 'red';
        }else{
            parrafo.style.display = 'block';
            parrafo.textContent = 'Formulario enviado correctamente';
            parrafo.style.backgroundColor = 'green';
        }

    })
    })