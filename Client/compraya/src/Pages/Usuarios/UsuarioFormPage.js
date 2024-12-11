import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { createUsuario, updateUsuario, getUsuarioById } from "../../Services/Usuarios/UsuarioService";
import HeaderComponent from "../../Componets/HeaderComponet"; // Adjust the path as necessary

const UsuarioFormPage = ({ mode }) => {
    const { id } = useParams();
    const navigate = useNavigate();

    const [usuario, setUsuario] = useState({
        nombre: "",
        identificacion: "",
        email: "",
        contrasena: "",
        celular: "",
        rol: "",
    });

    useEffect(() => {
        if (mode === "edit" && id) {
            const fetchUsuario = async () => {
                try {
                    const data = await getUsuarioById(id);
                    setUsuario(data);
                } catch (error) {
                    console.error("Error al cargar el usuario:", error);
                    alert("Error al cargar los datos del usuario.");
                }
            };
            fetchUsuario();
        }
    }, [id, mode]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUsuario((prevState) => ({
            ...prevState,
            [name]: value,
        }));
    };

    const isValid = () => {
        const { nombre, identificacion, email, contrasena, celular, rol } = usuario;
        return nombre && identificacion && email && contrasena && celular && rol;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!isValid()) {
            alert("Por favor, completa todos los campos obligatorios.");
            return;
        }
        try {
            if (id) {
                await updateUsuario(id, usuario);
                alert("Usuario actualizado con éxito.");
            } else {
                await createUsuario(usuario);
                alert("Usuario creado con éxito.");
            }
            navigate("/usuarios");
        } catch (error) {
            console.error("Error al guardar el usuario:", error);
            alert("Ocurrió un error al guardar el usuario.");
        }
    };

    return (
        <div className="flex">
            <HeaderComponent />
            <div className="ml-64 p-8 w-full">
                <h1 className="text-2xl font-bold mb-4">{mode === "edit" ? "Editar Usuario" : "Crear Usuario"}</h1>
                <form onSubmit={handleSubmit} className="space-y-4">
                    <input
                        type="text"
                        name="nombre"
                        placeholder="Nombre"
                        value={usuario.nombre}
                        onChange={handleChange}
                        className="w-full p-2 border border-gray-300 rounded"
                    />
                    <input
                        type="text"
                        name="identificacion"
                        placeholder="Identificación"
                        value={usuario.identificacion}
                        onChange={handleChange}
                        className="w-full p-2 border border-gray-300 rounded"
                    />
                    <input
                        type="email"
                        name="email"
                        placeholder="Email"
                        value={usuario.email}
                        onChange={handleChange}
                        className="w-full p-2 border border-gray-300 rounded"
                    />
                    <input
                        type="password"
                        name="contrasena"
                        placeholder="Contraseña"
                        value={usuario.contrasena}
                        onChange={handleChange}
                        className="w-full p-2 border border-gray-300 rounded"
                    />
                    <input
                        type="text"
                        name="celular"
                        placeholder="Celular"
                        value={usuario.celular}
                        onChange={handleChange}
                        className="w-full p-2 border border-gray-300 rounded"
                    />
                    <select
                        name="rol"
                        value={usuario.rol}
                        onChange={handleChange}
                        className="w-full p-2 border border-gray-300 rounded"
                    >
                        <option value="cliente">Cliente</option>
                        <option value="admin">Admin</option>
                    </select>
                    <button
                        type="submit"
                        className="w-full p-2 bg-blue-500 text-white rounded hover:bg-blue-600"
                    >
                        {mode === "edit" ? "Actualizar" : "Crear"}
                    </button>
                </form>
            </div>
        </div>
    );
};

export default UsuarioFormPage;
