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
    registro: "",
    empresa: "",
    cargo: "",
    cidade: "",
    estado: "",
    pre_requisitos: "",
    formacao: "",
    conhecimentos_requeridos: "",
    regime: "",
    jornada_trabalho: "",
    remuneracao: ""
  });

  const { id } = useParams();
  const navigate = useNavigate();
  const modoEdicao = Boolean(id);

  // Carregar empresas e cargos
  useEffect(() => {
    async function carregarEmpresasECargos() {
      try {
        const [empresasData, cargosData] = await Promise.all([
          getEmpresas(),
          getCargos(),
        ]);

        setEmpresas(
          empresasData.map((e) => ({
            id: e.id || e._id,
            nome_fantasia: e.nome_fantasia,
          }))
        );

        setCargos(
          cargosData.map((c) => ({
            id: c.id || c._id,
            nome: c.nome,
          }))
        );
      } catch (error) {
        console.error("Erro ao carregar empresas ou cargos:", error);
      }
    }

    carregarEmpresasECargos();
  }, []);

  // Carregar vaga somente DEPOIS que empresas e cargos estiverem carregados
  useEffect(() => {
    if (!modoEdicao) return;
    if (empresas.length === 0 || cargos.length === 0) return;

    async function carregarVaga() {
      try {
        const dados = await getVagaById(id);

        setVaga({
          registro: dados.registro || "",
          empresa: dados.empresa?.id || dados.empresa?._id || "",
          cargo: dados.cargo?.id || dados.cargo?._id || "",
          cidade: dados.cidade || "",
          estado: dados.estado || "",
          pre_requisitos: dados.pre_requisitos || "",
          formacao: dados.formacao || "",
          conhecimentos_requeridos: dados.conhecimentos_requeridos || "",
          regime: dados.regime || "",
          jornada_trabalho: dados.jornada_trabalho || "",
          remuneracao: dados.remuneracao || ""
        });
      } catch (error) {
        console.error("Erro ao buscar vaga:", error);
      }
    }

    carregarVaga();
  }, [modoEdicao, empresas, cargos, id]);

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

          <label>Registro:</label>
          <input
            type="text"
            name="registro"
            value={vaga.registro}
            onChange={handleChange}
            placeholder="Informe o registro..."
            required
          />

          <label>Empresa:</label>
          <select
            name="empresa"
            value={vaga.empresa}
            onChange={handleChange}
            required
          >
          {!modoEdicao && (
            <option value="">
              Selecione a empresa
            </option>
          )}
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
           {!modoEdicao && (
            <option value="">
              Selecione o cargo
            </option>
          )}

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

          <label>Pré-Requisitos:</label>
          <input
            type="text"
            name="pre_requisitos"
            value={vaga.pre_requisitos}
            onChange={handleChange}
            placeholder="Informe os pré-requisitos..."
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

          <label>Conhecimentos Requeridos:</label>
          <input
            type="text"
            name="conhecimentos_requeridos"
            value={vaga.conhecimentos_requeridos}
            onChange={handleChange}
            placeholder="Informe os conhecimentos requeridos..."
          />

          <label>Regime:</label>
          <input
            type="text"
            name="regime"
            value={vaga.regime}
            onChange={handleChange}
            placeholder="Informe o regime (CLT, PJ, etc)"
          />

          <label>Jornada de Trabalho:</label>
          <input
            type="text"
            name="jornada_trabalho"
            value={vaga.jornada_trabalho}
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
    registro: "",
    empresa: "",
    cargo: "",
    cidade: "",
    estado: "",
    pre_requisitos: "",
    formacao: "",
    conhecimentos_requeridos: "",
    regime: "",
    jornada_trabalho: "",
    remuneracao: ""
  });

  const { id } = useParams();
  const navigate = useNavigate();
  const modoEdicao = Boolean(id);

  // Carregar empresas e cargos
  useEffect(() => {
    async function carregarEmpresasECargos() {
      try {
        const [empresasData, cargosData] = await Promise.all([
          getEmpresas(),
          getCargos(),
        ]);

        setEmpresas(
          empresasData.map((e) => ({
            id: e.id || e._id,
            nome_fantasia: e.nome_fantasia,
          }))
        );

        setCargos(
          cargosData.map((c) => ({
            id: c.id || c._id,
            nome: c.nome,
          }))
        );
      } catch (error) {
        console.error("Erro ao carregar empresas ou cargos:", error);
      }
    }

    carregarEmpresasECargos();
  }, []);

  useEffect(() => {
    if (!modoEdicao) return;
    if (empresas.length === 0 || cargos.length === 0) return;

    async function carregarVaga() {
      try {
        const dados = await getVagaById(id);

        setVaga({
          registro: dados.registro || "",
          empresa: dados.empresa || "",
          cargo: cargos.find(c => c.nome === dados.cargo)?.id || "",
          cidade: dados.cidade || "",
          estado: dados.estado || "",
          pre_requisitos: dados.pre_requisitos || "",
          formacao: dados.formacao || "",
          conhecimentos_requeridos: dados.conhecimentos_requeridos || "",
          regime: dados.regime || "",
          jornada_trabalho: dados.jornada_trabalho || "",
          remuneracao: dados.remuneracao || ""
        });
      } catch (error) {
        console.error("Erro ao buscar vaga:", error);
      }
    }

    carregarVaga();
  }, [modoEdicao, empresas, cargos, id]);

  const handleChange = (e) => {
    setVaga({ ...vaga, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const empresaSelecionada = empresas.find((e) => e.id === vaga.empresa);
      const cargoSelecionado = cargos.find((c) => c.id === vaga.cargo);

      const cargoNome = cargoSelecionado ? cargoSelecionado.nome : vaga.cargo;
      const vagaData = {
        ...vaga,
        empresa: empresaSelecionada ? empresaSelecionada.id : vaga.empresa,
        cargo: cargoSelecionado ? cargoSelecionado.nome : vaga.cargo
      };



      if (modoEdicao) {
        console.log("Vaga enviada no PUT:", JSON.stringify(vagaData, null, 2));
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

          <label>Registro:</label>
          <input
            type="text"
            name="registro"
            value={vaga.registro}
            onChange={handleChange}
            placeholder="Informe o registro..."
            required
          />

          <label>Empresa:</label>
          <select
            name="empresa"
            value={vaga.empresa}
            onChange={handleChange}
            required
          >
            {!modoEdicao && <option value="">Selecione a empresa</option>}
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
            {!modoEdicao && <option value="">Selecione o cargo</option>}
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
            required
            placeholder="Informe a cidade..."
          />

          <label>Estado:</label>
          <input
            type="text"
            name="estado"
            value={vaga.estado}
            onChange={handleChange}
            required
            placeholder="Informe o estado..."
          />

          <label>Pré-Requisitos:</label>
          <input
            type="text"
            name="pre_requisitos"
            value={vaga.pre_requisitos}
            onChange={handleChange}
            required
            placeholder="Informe os pré-requisitos..."
          />

          <label>Formação:</label>
          <input
            type="text"
            name="formacao"
            value={vaga.formacao}
            onChange={handleChange}
            placeholder="Informe a formação..."
          />

          <label>Conhecimentos Requeridos:</label>
          <input
            type="text"
            name="conhecimentos_requeridos"
            value={vaga.conhecimentos_requeridos}
            onChange={handleChange}
            placeholder="Informe os conhecimentos requeridos..."
          />

          <label>Regime:</label>
          <input
            type="text"
            name="regime"
            value={vaga.regime}
            onChange={handleChange}
            placeholder="Informe o regime (CLT, PJ, etc)"
          />

          <label>Jornada de Trabalho:</label>
          <input
            type="text"
            name="jornada_trabalho"
            value={vaga.jornada_trabalho}
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
