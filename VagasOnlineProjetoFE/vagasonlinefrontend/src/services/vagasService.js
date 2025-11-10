// src/services/vagasService.js
const API_URL = "http://localhost:8080/vagas";

export async function getVagas() {
  const response = await fetch(API_URL);
  if (!response.ok) throw new Error("Erro ao buscar vagas");
  return await response.json();
}

export async function getVagaById(id) {
  const response = await fetch(`${API_URL}/${id}`);
  if (!response.ok) throw new Error("Erro ao buscar vaga");
  return await response.json();
}

export async function createVaga(vaga) {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vaga),
  });
  if (!response.ok) throw new Error("Erro ao criar vaga");
  return await response.json();
}

export async function updateVaga(id, vaga) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vaga),
  });
  if (!response.ok) throw new Error("Erro ao atualizar vaga");
  return await response.json();
}

export async function deleteVaga(id) {
  const response = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  if (!response.ok) throw new Error("Erro ao excluir vaga");
}
