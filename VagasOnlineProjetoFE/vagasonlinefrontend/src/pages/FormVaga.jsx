// src/pages/FormVaga.jsx
/*import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createVaga, updateVaga, getVagaById } from "../services/vagasService";
import { getEmpresas } from "../services/empresasService";
import { getCargos } from "../services/cargosService";
import "../styles/FormVaga.css";

export default function FormVaga() {
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

  const { id } = useParams(); // pega o :id da URL (ou undefined se for criação)
  const navigate = useNavigate();
  const modoEdicao = Boolean(id);

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
  if (modoEdicao) {
    getVagaById(id)
      .then((dados) => setVaga({
          empresa: dados.empresa?._id || "",
          cargo: dados.cargo?._id || "",
          cidade: dados.cidade || "",
          estado: dados.estado || "",
          requisitos: dados.requisitos || "",
          formacao: dados.formacao || "",
          conhecimentos: dados.conhecimentos || "",
          regime: dados.regime || "",
          jornada: dados.jornada || "",
          remuneracao: dados.remuneracao || "",
        })
      )
      .catch((erro) => console.error("Erro ao buscar vaga:", erro));
  }
}, [id, modoEdicao]);

  const handleChange = (e) => {
    setVaga({ ...vaga, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const empresaSelecionada = empresas.find((e) => e.id === vaga.empresa);
      const cargoSelecionado = cargos.find((c) => c.id === vaga.cargo);
      
      const vagaData = {
        ...vaga,
        empresa: empresaSelecionada, // encontra a empresa completa com base no nome_fantasia
        cargo: cargoSelecionado, // manda o cargo como string
      };


      if (modoEdicao) {
        await updateVaga(id, vagaData);
        alert("Vaga atualizada com sucesso!");
      } else {
        await createVaga(vagaData);
        alert("Vaga criada com sucesso!");
      }

      navigate("/"); // volta para Home após salvar
    } catch (error) {
      alert("Erro ao salvar vaga!");
      console.error(error);
    }
  };

  return (
    <div className="form-container">
      <div className="form-card">
        <h2>{modoEdicao ? "Editar Vaga" : "Cadastrar Nova Vaga"}</h2>
        <form onSubmit={handleSubmit}>
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
                {e.nome_fantasia}
              </option>
            ))}
          </select>

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

          <label>Cidade:</label>
          <input
            type="text"
            name="cidade"
            value={vaga.cidade}
            onChange={handleChange}
            placeholder="Informe a cidade..."
            required
          />
          <label>Estado:</label>
          <input
            type="text"
            name="estado"
            value={vaga.estado}
            onChange={handleChange}
            placeholder="Informe o estado..."
            required
          />
          <label>Requisitos:</label>
          <input
            type="text"
            name="requisitos"
            value={vaga.requisitos}
            onChange={handleChange}
            placeholder="Informe os requisitos..."
            required
          />
          <label>Formação:</label>
          <input
            type="text"
            name="formacao"
            value={vaga.formacao}
            onChange={handleChange}
            placeholder="Informe a formação..."
          />
          <label>Conhecimentos:</label>
          <input
            type="text"
            name="conhecimentos"
            value={vaga.conhecimentos}
            onChange={handleChange}
            placeholder="Informe os conhecimentos..."
          />
          <label>Regime:</label>
          <input
            type="text"
            name="regime"
            value={vaga.regime}
            onChange={handleChange}
            placeholder="Informe o regime (CLT, PJ, etc)"
          />
          <label>Jornada:</label>
          <input
            type="text"
            name="jornada"
            value={vaga.jornada}
            onChange={handleChange}
            placeholder="informe a jornada (ex: 40h semanais)"
          />
          <label>Remuneração:</label>
          <input
            type="text"
            name="remuneracao"
            value={vaga.remuneracao}
            onChange={handleChange}
            placeholder="Informe a remuneração..."
          />

          <div style={{ marginTop: "15px" }}>
            <button type="submit">
              {modoEdicao ? "Atualizar" : "Cadastrar"}
            </button>
            <button type="button" onClick={() => navigate("/")}>
              Voltar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}*/


import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { createVaga, updateVaga, getVagaById } from "../services/vagasService";
import { getEmpresas } from "../services/empresasService";
import { getCargos } from "../services/cargosService";
import "../styles/FormVaga.css";

export default function FormVaga() {
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

  const { id } = useParams();
  const navigate = useNavigate();
  const modoEdicao = Boolean(id);

  // Carrega empresas e cargos ao iniciar
  useEffect(() => {
    async function carregarEmpresasECargos() {
      try {
        const [empresasData, cargosData] = await Promise.all([
          getEmpresas(),
          getCargos(),
        ]);

        const empresasCorrigidas = empresasData.map((e) => ({
          id: e.id || e._id,
          nome_fantasia: e.nome_fantasia,
        }));
        const cargosCorrigidos = cargosData.map((c) => ({
          id: c.id || c._id,
          nome: c.nome,
        }));

        setEmpresas(empresasCorrigidas);
        setCargos(cargosCorrigidos);
      } catch (error) {
        console.error("Erro ao carregar empresas ou cargos:", error);
      }
    }

    carregarEmpresasECargos();
  }, []);

  // Se for edição carrega os dados da vaga
  useEffect(() => {
  if (!modoEdicao || empresas.length === 0 || cargos.length === 0) 
    return;

  async function carregarVaga() {
    try {
      const dados = await getVagaById(id);
      setVaga({
        empresa: dados.empresa?._id || dados.empresa?.id || "",
        cargo: dados.cargo?._id || dados.cargo?.id || "",
        cidade: dados.cidade || "",
        estado: dados.estado || "",
        requisitos: dados.requisitos || "",
        formacao: dados.formacao || "",
        conhecimentos: dados.conhecimentos || "",
        regime: dados.regime || "",
        jornada: dados.jornada || "",
        remuneracao: dados.remuneracao || "",
      });
    } catch (error) {
      console.error("Erro ao buscar vaga:", error);
    }
  }

  carregarVaga();
}, [id, modoEdicao, empresas, cargos]);


  const handleChange = (e) => {
    setVaga({ ...vaga, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const empresaSelecionada = empresas.find((e) => e.id === vaga.empresa);
      const cargoSelecionado = cargos.find((c) => c.id === vaga.cargo);

      const vagaData = {
        ...vaga,
        empresa: empresaSelecionada,
        cargo: cargoSelecionado,
      };

      if (modoEdicao) {
        await updateVaga(id, vagaData);
        alert("Vaga atualizada com sucesso!");
      } else {
        await createVaga(vagaData);
        alert("Vaga criada com sucesso!");
      }

      navigate("/");
    } catch (error) {
      alert("Erro ao salvar vaga!");
      console.error(error);
    }
  };

  return (
    <div className="form-container">
      <div className="form-card">
        <h2>{modoEdicao ? "Editar Vaga" : "Cadastrar Nova Vaga"}</h2>
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
                {e.nome_fantasia}
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

          {/* Campos de texto */}
          <label>Cidade:</label>
          <input
            type="text"
            name="cidade"
            value={vaga.cidade}
            onChange={handleChange}
            placeholder="Informe a cidade..."
            required
          />

          <label>Estado:</label>
          <input
            type="text"
            name="estado"
            value={vaga.estado}
            onChange={handleChange}
            placeholder="Informe o estado..."
            required
          />

          <label>Requisitos:</label>
          <input
            type="text"
            name="requisitos"
            value={vaga.requisitos}
            onChange={handleChange}
            placeholder="Informe os requisitos..."
            required
          />

          <label>Formação:</label>
          <input
            type="text"
            name="formacao"
            value={vaga.formacao}
            onChange={handleChange}
            placeholder="Informe a formação..."
          />

          <label>Conhecimentos:</label>
          <input
            type="text"
            name="conhecimentos"
            value={vaga.conhecimentos}
            onChange={handleChange}
            placeholder="Informe os conhecimentos..."
          />

          <label>Regime:</label>
          <input
            type="text"
            name="regime"
            value={vaga.regime}
            onChange={handleChange}
            placeholder="Informe o regime (CLT, PJ, etc)"
          />

          <label>Jornada:</label>
          <input
            type="text"
            name="jornada"
            value={vaga.jornada}
            onChange={handleChange}
            placeholder="Informe a jornada (ex: 40h semanais)"
          />

          <label>Remuneração:</label>
          <input
            type="text"
            name="remuneracao"
            value={vaga.remuneracao}
            onChange={handleChange}
            placeholder="Informe a remuneração..."
          />

          <div style={{ marginTop: "15px" }}>
            <button type="submit">
              {modoEdicao ? "Atualizar" : "Cadastrar"}
            </button>
            <button type="button" onClick={() => navigate("/")}>
              Voltar
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

