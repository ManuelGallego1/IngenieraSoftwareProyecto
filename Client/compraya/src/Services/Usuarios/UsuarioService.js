import axios from "axios";
const API_URL = "http://localhost:4000/usuarios";

export const getAllUsuarios = async () => {
    try {
        const response = await axios.get(`${API_URL}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching users:", error);
        throw error;
    }
};

export const getUsuarioById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error("Error al obtener el usuario:", error);
        throw error;
    }
};

export const createUsuario = async (usuario) => {
    try {
        const response = await axios.post(API_URL, usuario);
        return response.data;
    } catch (error) {
        console.error("Error al crear el usuario:", error);
        throw error;
    }
};

export const updateUsuario = async (id, usuario) => {
    try {
        const response = await axios.put(`${API_URL}/${id}`, usuario);
        return response.data;
    } catch (error) {
        console.error("Error al actualizar el usuario:", error);
        throw error;
    }
};

export const deleteUsuario = async (id) => {
    try {
        const response = await axios.delete(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error("Error al eliminar el usuario:", error);
        throw error;
    }
};

export const getClientes = async () => {
    try {
        const response = await axios.get(`${API_URL}?rol=cliente`);
        return response.data;
    } catch (error) {
        console.error("Error al obtener los clientes:", error);
        throw error;
    }
};