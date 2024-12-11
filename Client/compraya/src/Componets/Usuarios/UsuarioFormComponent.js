import React, { useState, useEffect } from 'react';
import { getAllUsuarios, getUsuarioById, createUsuario, updateUsuario, deleteUsuario } from './UsuarioService';

const UsuarioComponent = () => {
    const [usuarios, setUsuarios] = useState([]);
    const [usuario, setUsuario] = useState({ nombre: '', email: '' });
    const [id, setId] = useState(null);

    useEffect(() => {
        fetchUsuarios();
    }, []);

    const fetchUsuarios = async () => {
        try {
            const data = await getAllUsuarios();
            setUsuarios(data);
        } catch (error) {
            console.error("Error fetching users:", error);
        }
    };

    const handleCreateUsuario = async () => {
        try {
            const newUsuario = await createUsuario(usuario);
            setUsuarios([...usuarios, newUsuario]);
        } catch (error) {
            console.error("Error creating user:", error);
        }
    };

    const handleUpdateUsuario = async () => {
        try {
            const updatedUsuario = await updateUsuario(id, usuario);
            setUsuarios(usuarios.map(u => (u.id === id ? updatedUsuario : u)));
        } catch (error) {
            console.error("Error updating user:", error);
        }
    };

    const handleDeleteUsuario = async (id) => {
        try {
            await deleteUsuario(id);
            setUsuarios(usuarios.filter(u => u.id !== id));
        } catch (error) {
            console.error("Error deleting user:", error);
        }
    };

    return (
        <div>
            {/* Renderizar usuarios y formularios para crear/actualizar */}
        </div>
    );
};

export default UsuarioComponent;