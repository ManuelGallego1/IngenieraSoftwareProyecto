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
    <div className="flex min-h-screen bg-gray-100">
      <aside className="w-64 bg-gray-800 text-white">
        {/* Aquí iría el contenido de la slidebar */}
      </aside>
      <main className="flex-1 p-4">
        <div className="max-w-4xl mx-auto bg-white shadow-md rounded-lg p-6">
          <h1 className="text-2xl font-bold mb-4">Usuarios</h1>
          <button
            onClick={() => navigate("/usuarios/crear")}
            className="mb-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Crear Usuario
          </button>
          <ListaUsuariosComponets usuarios={usuarios} onEdit={handleEdit} onDelete={handleDelete} />
        </div>
      </main>
    </div>
  );
};

export default UsuarioPage;