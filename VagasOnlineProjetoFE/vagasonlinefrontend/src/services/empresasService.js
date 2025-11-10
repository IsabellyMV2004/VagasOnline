import api from "./api";

export async function getEmpresas() {
  const response = await api.get("/empresas");
  return response.data;
}
