/*import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
*/

/*
import React from "react";
import FormVaga from "./pages/FormVaga";
import Home from "./pages/Home";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/form" element={<FormVaga />} />
        <Route path="/form/:id" element={<FormVaga />} />
      </Routes>
    </Router>
  );
}

export default App;
*/


/*
export default function App() {
  return <h1>Home OK</h1>;
}*/


import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import FormVaga from "./pages/FormVaga";

function Home() {
  return (
    <div className="container mt-4">
      <h1>🏠 Página Inicial</h1>
      <p>Bem-vindo ao sistema de vagas online!</p>
      <Link to="/form" className="btn btn-primary">
        Nova Vaga
      </Link>
    </div>
  );
}

/*function FormVaga() {
  return (
    <div className="container mt-4">
      <h1>📝 Cadastro de Vaga</h1>
      <p>Aqui será o formulário de cadastro/edição de vaga.</p>
      <Link to="/" className="btn btn-secondary">
        Voltar
      </Link>
    </div>
  );
}
*/

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/form" element={<FormVaga />} />
      </Routes>
    </Router>
  );
}

export default App;

