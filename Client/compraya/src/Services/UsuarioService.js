import axios from "axios";
const USUARIO_BASE_REST_API_URL = "http://localhost:4000"


export const getAllUsuarios = async () => {
    try {
        const response = await axios.get(`${USUARIO_BASE_REST_API_URL}/usuarios`);
        return response.data;
    } catch (error) {
        console.error("Error fetching users:", error);
        throw error;
    }
};
