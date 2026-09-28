import React, { useState } from 'react';

function Contador() {
  // Declaramos una variable de estado llamada "cuenta" y su función para actualizarla "setCuenta"
  // El valor inicial del estado es 0
  const [cuenta, setCuenta] = useState(0);

  return (
    <div>
      <p>Has hecho clic {cuenta} veces</p>
      {/* Al hacer clic, llamamos a setCuenta para actualizar el estado */}
      <button onClick={() => setCuenta(cuenta + 1)}>
        Haz clic aquí
      </button>
    </div>
  );
}