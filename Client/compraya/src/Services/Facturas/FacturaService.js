import axios from 'axios';
const API_URL = 'http://localhost:4000/facturas';

export const getFacturas = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error("Error al obtener las facturas:", error);
        throw error;
    }
};

export const getFacturaById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error("Error al obtener la factura:", error);
        throw error;
    }
};

export const createFactura = async (factura) => {
    try {
        const response = await axios.post(API_URL, factura);
        return response.data;
    } catch (error) {
        console.error("Error al crear la factura:", error);
        throw error;
    }
};

export const updateFactura = async (id, factura) => {
    try {
        const response = await axios.put(`${API_URL}/${id}`, factura);
        return response.data;
    } catch (error) {
        console.error("Error al actualizar la factura:", error);
        throw error;
    }
};

export const deleteFactura = async (id) => {
    try {
        const response = await axios.delete(`${API_URL}/${id}`);
        return response.data;
    } catch (error) {
        console.error("Error al eliminar la factura:", error);
        throw error;
    }
};

export const generateXML = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}/xml`, { responseType: 'blob' });
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', `factura_${id}.xml`);
        document.body.appendChild(link);
        link.click();
        link.parentNode.removeChild(link);
    } catch (error) {
        console.error("Error al generar el XML:", error);
        throw error;
    }
};