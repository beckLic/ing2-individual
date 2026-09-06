import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx' // Importacion de  componente principal

// Aquí React DOM linkea nuestra app al div con id="root" del index.html
ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <App />
    </React.StrictMode>,
)
