// src/pages/FormVaga.jsx
import React, { useState, useEffect } from "react";
import { createVaga, updateVaga, getVagaById } from "../services/vagasService";
import { getEmpresas } from "../services/empresasService";
import { getCargos } from "../services/cargosService";
import "../styles/FormVaga.css";





export default function FormVaga({ vagaId }) {
  const [empresas, setEmpresas] = useState([]);
  const [cargos, setCargos] = useState([]);

  const [vaga, setVaga] = useState({
    empresa: "",
    cargo: "",
    cidade: "",
    estado: "",
    requisitos: "",
    formacao: "",
    conhecimentos: "",
    regime: "",
    jornada: "",
    remuneracao: "",
  });

  // 🔹 Carrega empresas e cargos ao iniciar
  useEffect(() => {
    async function carregarEmpresasECargos() {
      try {
        const [empresasData, cargosData] = await Promise.all([
          getEmpresas(),
          getCargos(),
        ]);
        setEmpresas(empresasData);
        setCargos(cargosData);
      } catch (error) {
        console.error("Erro ao carregar empresas ou cargos:", error);
      }
    }

    carregarEmpresasECargos();
  }, []);

  // 🔹 Se for edição, carrega os dados da vaga
  useEffect(() => {
    if (vagaId) {
      getVagaById(vagaId)
        .then((dados) => setVaga(dados))
        .catch((erro) => console.error("Erro ao buscar vaga:", erro));
    }
  }, [vagaId]);

  const handleChange = (e) => {
    setVaga({ ...vaga, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const vagaData = {
        ...vaga,
        empresa: { id: vaga.empresa },
        cargo: { id: vaga.cargo },
      };

      if (vagaId) {
        await updateVaga(vagaId, vagaData);
        alert("Vaga atualizada com sucesso!");
      } else {
        await createVaga(vagaData);
        alert("Vaga criada com sucesso!");
      }
    } catch (error) {
      alert("Erro ao salvar vaga!");
      console.error(error);
    }
  };

  return (
    <div style={{ maxWidth: "700px", margin: "0 auto" }}>
      <h2>{vagaId ? "Editar Vaga" : "Cadastrar Nova Vaga"}</h2>
      <form onSubmit={handleSubmit}>
        {/* Empresa */}
        <label>Empresa:</label>
        <select
          name="empresa"
          value={vaga.empresa}
          onChange={handleChange}
          required
        >
          <option value="">Selecione a empresa</option>
          {empresas.map((e) => (
            <option key={e.id} value={e.id}>
              {e.nome}
            </option>
          ))}
        </select>

        {/* Cargo */}
        <label>Cargo:</label>
        <select
          name="cargo"
          value={vaga.cargo}
          onChange={handleChange}
          required
        >
          <option value="">Selecione o cargo</option>
          {cargos.map((c) => (
            <option key={c.id} value={c.id}>
              {c.nome}
            </option>
          ))}
        </select>

        {/* Demais campos */}
        <input
          type="text"
          name="cidade"
          value={vaga.cidade}
          onChange={handleChange}
          placeholder="Cidade"
          required
        />
        <input
          type="text"
          name="estado"
          value={vaga.estado}
          onChange={handleChange}
          placeholder="Estado"
          required
        />
        <input
          type="text"
          name="requisitos"
          value={vaga.requisitos}
          onChange={handleChange}
          placeholder="Requisitos"
          required
        />
        <input
          type="text"
          name="formacao"
          value={vaga.formacao}
          onChange={handleChange}
          placeholder="Formação"
        />
        <input
          type="text"
          name="conhecimentos"
          value={vaga.conhecimentos}
          onChange={handleChange}
          placeholder="Conhecimentos"
        />
        <input
          type="text"
          name="regime"
          value={vaga.regime}
          onChange={handleChange}
          placeholder="Regime (CLT, PJ, etc)"
        />
        <input
          type="text"
          name="jornada"
          value={vaga.jornada}
          onChange={handleChange}
          placeholder="Jornada (ex: 40h semanais)"
        />
        <input
          type="number"
          name="remuneracao"
          value={vaga.remuneracao}
          onChange={handleChange}
          placeholder="Remuneração"
        />

        <button type="submit">{vagaId ? "Atualizar" : "Cadastrar"}</button>
      </form>
    </div>
  );
}
