import axios from 'axios';
const API_URL = 'http://localhost:4000/categorias';

export const getAllCategorias = async () => {
    try {
        const response = await axios.get(API_URL);
        return response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
        throw error;
    }
};