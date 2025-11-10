const API_URL = "http://localhost:8080/empresas";

export async function getEmpresas() {
  const response = await fetch(API_URL);
  if (!response.ok) throw new Error("Erro ao buscar empresas");
  return await response.json();
}
