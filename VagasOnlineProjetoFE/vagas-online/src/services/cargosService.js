const API_URL = "http://localhost:8080/cargos";

export async function getCargos() {
  const response = await fetch(API_URL);
  if (!response.ok) throw new Error("Erro ao buscar cargos");
  return await response.json();
}
