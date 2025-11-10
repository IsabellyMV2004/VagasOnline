// src/services/api.js
import axios from "axios";

// Base URL do backend
const api = axios.create({
  baseURL: "http://localhost:8080/apis",
});

export default api;
