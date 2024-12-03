import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { createUsuario, updateUsuario, getUsuarioById } from "../../Services/Usuarios/UsuarioService";

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
        <div>
            <h1>{mode === "edit" ? "Editar Usuario" : "Crear Usuario"}</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" name="nombre" placeholder="Nombre" value={usuario.nombre} onChange={handleChange} />
                <input type="text" name="identificacion" placeholder="Identificación" value={usuario.identificacion} onChange={handleChange} />
                <input type="email" name="email" placeholder="Email" value={usuario.email} onChange={handleChange} />
                <input type="password" name="contrasena" placeholder="Contraseña" value={usuario.contrasena} onChange={handleChange} />
                <input type="text" name="celular" placeholder="Celular" value={usuario.celular} onChange={handleChange} />
                <select name="rol" value={usuario.rol} onChange={handleChange}>
                    <option value="cliente">Cliente</option>
                    <option value="admin">Admin</option>
                </select>
                <button type="submit">{mode === "edit" ? "Actualizar" : "Crear"}</button>
            </form>
        </div>
    );
};

export default UsuarioFormPage;
