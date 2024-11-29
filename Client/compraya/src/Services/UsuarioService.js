import axios from "axios";
const USUARIO_BASE_REST_API_URL = "http://localhost:4000/usuarios"

class UsuarioService {

    getAllUsuarios(){
        return axios.get(USUARIO_BASE_REST_API_URL);
    }
    
}

export default new UsuarioService();