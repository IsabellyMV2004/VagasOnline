import api from "./api";

export async function getVagas() {
  const response = await api.get("/vagas");
  return response.data;
}

export async function getVagaById(id) {
  const response = await api.get(`/vagas/${id}`);
  return response.data;
}

export async function createVaga(vaga) {
  const response = await api.post("/vagas", vaga);
  return response.data;
}

export async function updateVaga(id, vaga) {
  const response = await api.put(`/vagas/${id}`, vaga);
  return response.data;
}

export async function deleteVaga(id) {
  const response = await api.delete(`/vagas/${id}`);
  return response.data;
}

export async function registrarInteresse(interesse) {
  const response = await api.post("/vagas/interesse", interesse);
  return response.data;
}
