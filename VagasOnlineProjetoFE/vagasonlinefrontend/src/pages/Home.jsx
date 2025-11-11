import React, { useState, useEffect } from "react";
import { getVagas, deleteVaga, updateVaga } from "../services/vagasService";
import "../styles/Home.css";
import { useNavigate } from "react-router-dom";

export default function Home() {
  const [vagas, setVagas] = useState([]);
  const [search, setSearch] = useState("");
  const [menuAberto, setMenuAberto] = useState(null);
  const navigate = useNavigate();

  // Carrega as vagas ao iniciar
  useEffect(() => {
    carregarVagas();
  }, []);

  // Função para carregar as vagas da API
  const carregarVagas = async () => {
    try {
      const response = await getVagas();
      setVagas(response);
    } catch (err) {
      console.error("Erro ao buscar vagas:", err);
    }
  };

  // Função para excluir uma vaga
  const handleDelete = async (vaga) => {
    if (window.confirm("Deseja excluir esta vaga?")) {
      try {
        // Usamos a combinação de 'nome_fantasia' e 'cargo' para identificar a vaga
        await deleteVaga(vaga);  // Passando a vaga inteira para excluir
        setVagas(vagas.filter((v) => v.empresa.nome_fantasia !== vaga.empresa.nome_fantasia || v.cargo !== vaga.cargo)); // Remove a vaga do estado diretamente
      } catch (err) {
        console.error("Erro ao excluir:", err);
      }
    }
  };

  // Função para editar uma vaga
  const handleEdit = (vaga) => {
    navigate(`/form/${vaga.id}`); // Usando a combinação para editar
  };

  // Função para lidar com a pesquisa
  const handleSearch = (e) => {
    setSearch(e.target.value);
  };

  // Filtra as vagas com base na pesquisa
  const vagasFiltradas = vagas.filter(
    (v) =>
      v.cargo.toLowerCase().includes(search.toLowerCase()) ||
      v.empresa.nome_fantasia.toLowerCase().includes(search.toLowerCase())
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
          <div key={`${vaga.empresa.nome_fantasia}-${vaga.cargo}`} className="vaga-card">
            <div>
              <h3>{vaga.cargo}</h3>
              {/* Renderizando o nome_fantasia de vaga.empresa, que é uma string */}
              <p>{vaga.empresa.nome_fantasia}</p>
              <p>
                {vaga.cidade} - {vaga.estado}
              </p>
            </div>

            {/* ⋮ Menu de opções */}
            <div className="menu-container">
              <button
                className="menu-btn"
                onClick={() =>
                  setMenuAberto(menuAberto === `${vaga.empresa.nome_fantasia}-${vaga.cargo}` ? null : `${vaga.empresa.nome_fantasia}-${vaga.cargo}`)
                }
              >
                ⋮
              </button>
              {menuAberto === `${vaga.empresa.nome_fantasia}-${vaga.cargo}` && (
                <div className="menu-opcoes">
                  <button onClick={() => handleEdit(vaga)}>✏️ Editar</button>
                  <button onClick={() => handleDelete(vaga)}>🗑️ Excluir</button>
                </div>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
