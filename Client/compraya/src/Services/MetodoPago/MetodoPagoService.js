import axios from 'axios';
const API_URL = 'http://localhost:4000/metodo-pagos';

export const getAllMetodosPago = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error("Error al obtener los métodos de pago:", error);
        throw error;
    }
};