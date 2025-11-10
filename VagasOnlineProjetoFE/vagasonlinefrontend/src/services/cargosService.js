import api from "./api";

export async function getCargos() {
  const response = await api.get("/cargos");
  return response.data;
}
