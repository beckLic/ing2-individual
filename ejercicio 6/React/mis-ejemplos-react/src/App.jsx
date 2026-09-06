import React from 'react';
import OptimizacionApp from './OptimizacionApp.jsx';
import AppContexto from './AppContexto.jsx';

function App() {
    return (
        <div style={{ fontFamily: 'system-ui, sans-serif', padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
            <h1>Mis Ejemplos de React</h1>

            <section style={{ marginBottom: '40px', padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>
                <OptimizacionApp />
            </section>

            <section style={{ padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>
                <AppContexto />
            </section>
        </div>
    );
}

export default App;