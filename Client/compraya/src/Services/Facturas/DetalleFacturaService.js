import axios from 'axios';
const API_URL = 'http://localhost:4000/detalles-factura';

export const getDetallesFactura = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error("Error al obtener los detalles de la factura:", error);
        throw error;
    }
};

export const getDetalleFacturaById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error("Error al obtener el detalle de la factura:", error);
        throw error;
    }
};

export const createDetalleFactura = async (detalleFactura) => {
    try {
        const response = await axios.post(API_URL, detalleFactura);
        return response.data;
    } catch (error) {
        console.error("Error al crear el detalle de la factura:", error);
        throw error;
    }
};

export const updateDetalleFactura = async (id, detalleFactura) => {
    try {
        const response = await axios.put(`${API_URL}/${id}`, detalleFactura);
        return response.data;
    } catch (error) {
        console.error("Error al actualizar el detalle de la factura:", error);
        throw error;
    }
};

export const deleteDetalleFactura = async (id) => {
    try {
        const response = await axios.delete(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error("Error al eliminar el detalle de la factura:", error);
        throw error;
    }
};