import React, { useState, createContext, useContext } from 'react';

// 1. Creamos el contexto. Inicialmente está vacío.
const TemaContext = createContext();

const AppContexto = () => {
    const [tema, setTema] = useState('claro');

    const alternarTema = () => {
        setTema((prevTema) => (prevTema === 'claro' ? 'oscuro' : 'claro'));
    };

    return (
        // 2. Envolvemos nuestra aplicación con el Provider.
        // Todo lo que esté dentro de este Provider podrá acceder a 'tema' y 'alternarTema'.
        <TemaContext.Provider value={{ tema, alternarTema }}>
            <div style={{ padding: '20px', border: '1px solid #ccc' }}>
                <h2>Aplicación Principal</h2>
                {/* Fíjate que no le pasamos ninguna prop a BarraDeHerramientas */}
                <BarraDeHerramientas />
            </div>
        </TemaContext.Provider>
    );
};

// Componente intermedio: No necesita saber nada sobre el tema.
const BarraDeHerramientas = () => {
    return (
        <div>
            <p>Soy un componente intermedio.</p>
            <BotonCambiarTema />
        </div>
    );
};

// 3. Componente consumidor: Extrae los datos directamente del contexto.
const BotonCambiarTema = () => {
    // Usamos el hook useContext para acceder a los valores provistos
    const { tema, alternarTema } = useContext(TemaContext);

    const estilos = {
        background: tema === 'claro' ? '#fff' : '#333',
        color: tema === 'claro' ? '#000' : '#fff',
        padding: '10px',
        cursor: 'pointer'
    };

    return (
        <button style={estilos} onClick={alternarTema}>
            Cambiar a tema {tema === 'claro' ? 'Oscuro' : 'Claro'}
        </button>
    );
};

export default AppContexto;