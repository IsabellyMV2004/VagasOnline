import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import FormVaga from "./pages/FormVaga";
import Home from "./pages/Home";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/form" element={<FormVaga />} />           {/* Criar nova vaga */}
        <Route path="/form/:id" element={<FormVaga />} />       {/* Editar vaga */}
      </Routes>
    </Router>
  );
}

export default App;
