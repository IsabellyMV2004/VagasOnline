import React from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import FormVaga from "./pages/FormVaga";

function AppRoutes() {
  return (
    <Routes>
      {/* Página inicial */}
      <Route path="/" element={<Home />} />

      {/* Formulário para cadastrar nova vaga */}
      <Route path="/form" element={<FormVaga />} />

      {/* Formulário no modo de edição (quando vem com id na URL) */}
      <Route path="/:id" element={<FormVaga />} />
    </Routes>
  );
}

export default AppRoutes;
