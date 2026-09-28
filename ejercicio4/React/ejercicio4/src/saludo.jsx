import React, { useState } from 'react';

function SaludoInteractivo() {
  const [nombre, setNombre] = useState('');

  // Función manejadora del evento change
  const manejarCambio = (evento) => {
    setNombre(evento.target.value); // Capturamos lo que el usuario escribe
  };

  return (
    <div>
      {/* Evento onChange capta cada vez que se teclea algo */}
      <input 
        type="text" 
        placeholder="Escribe tu nombre" 
        onChange={manejarCambio} 
      />
      <p>Hola, {nombre ? nombre : 'invitado'}</p>
    </div>
  );
}