import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

// 1. Componentes de vista (Páginas)
function PaginaInicio() {
  return <h2>Estás en la página de Inicio 🏠</h2>;
}

function PaginaPerfil() {
  return <h2>Estás en la página de Perfil 👤</h2>;
}

// 2. Componente Principal con las Rutas
function App() {
  return (
      <BrowserRouter>
        <nav style={{ marginBottom: '20px', padding: '10px', background: '#333', color: 'white' }}>
          {/* Los enlaces para navegar */}
          <Link to="/" style={{ marginRight: '15px', color: 'lightblue' }}>Ir a Inicio</Link>
          <Link to="/perfil" style={{ color: 'lightblue' }}>Ir a Perfil</Link>
        </nav>

        <div style={{ padding: '20px' }}>
          {/* El "interruptor" que cambia las vistas */}
          <Routes>
            <Route path="/" element={<PaginaInicio />} />
            <Route path="/perfil" element={<PaginaPerfil />} />
          </Routes>
        </div>
      </BrowserRouter>
  );
}

export default App;
