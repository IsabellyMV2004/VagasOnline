import React, { useState, useEffect } from "react";
import vagasServices from "../services/vagasService";
import "../styles/Home.css";
import { useNavigate } from "react-router-dom";

function Home() {
  const [vagas, setVagas] = useState([]);
  const [search, setSearch] = useState("");
  const [menuAberto, setMenuAberto] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    carregarVagas();
  }, []);

  const carregarVagas = async () => {
    try {
      const response = await vagasServices.getVagas();
      setVagas(response);
    } catch (err) {
      console.error("Erro ao buscar vagas:", err);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm("Deseja excluir esta vaga?")) {
      try {
        await vagasServices.deleteVaga(id);
        carregarVagas();
      } catch (err) {
        console.error("Erro ao excluir:", err);
      }
    }
  };

  const handleEdit = (vaga) => {
    navigate(`/form/${vaga.id}`); // modo edição
  };

  const handleSearch = (e) => {
    setSearch(e.target.value);
  };

  const vagasFiltradas = vagas.filter(
    (v) =>
      v.cargo.toLowerCase().includes(search.toLowerCase()) ||
      v.empresa.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="container">
      <h1>Vagas Online</h1>

      {/* 🔍 Barra de Pesquisa + Novo */}
      <div className="search-bar">
        <input
          type="text"
          placeholder="Pesquisar vagas..."
          value={search}
          onChange={handleSearch}
        />
        <button onClick={() => navigate("/form")}>+ Nova Vaga</button>
      </div>

      {/* 📋 Lista de Vagas */}
      <div className="vaga-list">
        {vagasFiltradas.map((vaga) => (
          <div key={vaga.id} className="vaga-card">
            <div>
              <h3>{vaga.cargo}</h3>
              <p>{vaga.empresa}</p>
              <p>
                {vaga.cidade} - {vaga.estado}
              </p>
            </div>

            {/* ⋮ Menu de opções */}
            <div className="menu-container">
              <button
                className="menu-btn"
                onClick={() =>
                  setMenuAberto(menuAberto === vaga.id ? null : vaga.id)
                }
              >
                ⋮
              </button>
              {menuAberto === vaga.id && (
                <div className="menu-opcoes">
                  <button onClick={() => handleEdit(vaga)}>✏️ Editar</button>
                  <button onClick={() => handleDelete(vaga.id)}>🗑️ Excluir</button>
                </div>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;
