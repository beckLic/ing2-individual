import React, { useState, useMemo, useCallback } from 'react';

// Subcomponente que recibe una función como prop
const Boton = React.memo(({ onClick, children }) => {
    console.log(`Renderizando botón: ${children}`);
    return <button onClick={onClick}>{children}</button>;
});

const OptimizacionApp = () => {
    const [contador, setContador] = useState(0);
    const [texto, setTexto] = useState('');

    // 1. Uso de useMemo:
    // Este cálculo simulado es "pesado". Solo queremos que se ejecute
    // si el 'contador' cambia, NO cuando el usuario escribe en el 'texto'.
    const calculoPesado = useMemo(() => {
        console.log('Realizando cálculo pesado...');
        let resultado = 0;
        for (let i = 0; i < 100000000; i++) {
            resultado += contador;
        }
        return resultado;
    }, [contador]); // <- Dependencia: solo recalcula si 'contador' cambia.

    // 2. Uso de useCallback:
    // Memorizamos la función para que 'Boton' no se re-renderice innecesariamente
    // cuando el usuario escribe en el input de texto.
    const incrementar = useCallback(() => {
        setContador((prev) => prev + 1);
    }, []); // <- Sin dependencias, la función nunca se recrea.

    return (
        <div>
            <h3>Hooks de Rendimiento</h3>

            {/* Al escribir aquí, cambia el estado 'texto', causando un re-render del componente. */}
            <input
                type="text"
                value={texto}
                onChange={(e) => setTexto(e.target.value)}
                placeholder="Escribe algo..."
            />

            <p>Texto ingresado: {texto}</p>
            <p>Resultado del cálculo: {calculoPesado}</p>

            {/* Gracias a useCallback y React.memo, este botón no se renderiza al escribir en el input */}
            <Boton onClick={incrementar}>Incrementar Contador</Boton>
        </div>
    );
};

export default OptimizacionApp;