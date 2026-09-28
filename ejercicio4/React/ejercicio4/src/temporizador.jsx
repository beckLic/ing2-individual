import React, { useState, useEffect } from 'react';

function Temporizador() {
  const [segundos, setSegundos] = useState(0);

  // useEffect se usa aquí para manejar un efecto secundario (el intervalo de tiempo)
  useEffect(() => {
    // Iniciamos un temporizador cuando el componente se monta
    const intervalo = setInterval(() => {
      setSegundos(estadoAnterior => estadoAnterior + 1);
    }, 1000);

    // Función de limpieza: se ejecuta cuando el componente se desmonta
    return () => clearInterval(intervalo);
  }, []); // El array vacío indica que este efecto solo se ejecuta una vez al montar

  return <h1>Tiempo en pantalla: {segundos} segundos</h1>;
}