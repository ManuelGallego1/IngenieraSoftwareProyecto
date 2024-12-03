import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ListaUsuariosComponets from "../../Componets/Usuarios/ListaUsuariosComponet";
import { getAllUsuarios, deleteUsuario } from "../../Services/Usuarios/UsuarioService";

const UsuarioPage = () => {
  const [usuarios, setUsuarios] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUsuarios = async () => {
      try {
        const data = await getAllUsuarios();
        setUsuarios(data);
      } catch (error) {
        console.error("Error al cargar los usuarios:", error);
      }
    };
    fetchUsuarios();
  }, []);

  const handleEdit = (id) => {
    navigate(`/usuarios/editar/${id}`);
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm("¿Estás seguro de que quieres eliminar este usuario?");
    if (confirmDelete) {
      try {
        await deleteUsuario(id);
        setUsuarios(usuarios.filter((usuarios) => usuarios.id !== id));
        alert("Usuario eliminado con éxito.");
      } catch (error) {
        console.error("Error al eliminar el usuario:", error);
        alert("Error al eliminar el usuario.");
      }
    }
  };

  return (
    <div>
      <h1>Usuarios</h1>
      <button onClick={() => navigate("/usuarios/crear")}>Crear Usuario</button>
      <ListaUsuariosComponets usuarios={usuarios} onEdit={handleEdit} onDelete={handleDelete} />
    </div>
  );
};

export default UsuarioPage;
