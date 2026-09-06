Los Hooks de rendimiento son herramientas que proporciona React para optimizar la velocidad y eficiencia de una aplicación. Su objetivo principal es evitar que los componentes realicen cálculos pesados o se vuelvan a renderizar (dibujar en pantalla) cuando no es estrictamente necesario.

Los dos hooks de rendimiento más utilizados son useMemo y useCallback.

useMemo: Sirve para memorizar un valor calculado. Si tienes una función matemática compleja o un filtrado de datos muy grande, useMemo guarda el resultado. Si el componente se vuelve a renderizar por otra razón, React reutiliza el resultado guardado en lugar de volver a procesarlo, a menos que sus dependencias hayan cambiado.

useCallback: Sirve para memorizar una función. En React, cada vez que un componente se renderiza, sus funciones internas se vuelven a crear en memoria. Si pasas esas funciones como props a componentes hijos, los hijos creerán que la prop cambió y se re-renderizarán. useCallback mantiene la misma referencia de la función entre renders.

Ejemplo de OptimizacionApp.jsx:

Si no se usara useMemo, cada vez que el usuario escribiera una letra en el input, el componente se actualizaría y el calculoPesado trabaría la pantalla volviendo a contar hasta 100 millones. Gracias a useMemo, React nota que el contador no cambió y devuelve el valor almacenado inmediatamente. Lo mismo ocurre con useCallback; evita que el componente <Boton/> se vuelva a dibujar en la pantalla solo porque el usuario está escribiendo un texto.

Ejemplo de AppContexto.jsx:

En este código, el estado tema vive en AppContexto. Sin embargo, quien lo utiliza y modifica es BotonCambiarTema. Entre ellos existe BarraDeHerramientas. Gracias al Contexto, no se tiene que pasar el tema como prop a BarraDeHerramientas para que esta se lo pase al botón. El botón simplemente "salta" los niveles intermedios y consume la información directamente desde el TemaContext.Provider utilizando el hook useContext.