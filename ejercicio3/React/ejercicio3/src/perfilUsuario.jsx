import React from 'react';

function PerfilUsuario() {
  const nombre = "María";
  const edad = 25;
  const esEstudiante = true;

  return (
    // Se utiliza un fragmento <> para agrupar los elementos sin crear un nodo extra en el DOM
    <>
      <h1 className="titulo">Perfil de {nombre}</h1>
      <p>Edad: {edad} años</p>
      {/* Podemos usar lógica de JS dentro de las llaves */}
      <p>Estado: {esEstudiante ? 'Estudiando' : 'Graduada'}</p>
    </>
  );
}