import axios from 'axios';
const API_URL = 'http://localhost:4000/inventario';

export const registrarSalida = async (id, referenciaCompra, salida) => {
    try {
        const formData = new FormData();
        formData.append('referenciaCompra', referenciaCompra);
        formData.append('salida', salida);

        const response = await axios.post(`${API_URL}/${id}/salida`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        console.log(response.data);
    } catch (error) {
        console.error("Error al registrar la salida:", error);
        throw error;
    }
};

export const registrarEntrada = async (id, referenciaCompra, entrada) => {
    try {
        const formData = new FormData();
        formData.append('referenciaCompra', referenciaCompra);
        formData.append('entrada', entrada);

        const response = await axios.post(`${API_URL}/${id}/entrada`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        console.log(response.data);
    } catch (error) {
        console.error("Error al registrar la entrada:", error);
        throw error;
    }
};

export const getInventario = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}/total`);
        return response.data;
    } catch (error) {
        console.error("Error al obtener el inventario:", error);
        throw error;
    }
};