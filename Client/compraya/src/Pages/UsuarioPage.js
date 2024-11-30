
import React, { useEffect, useState } from 'react';
import { getAllUsuarios } from '../Services/UsuarioService';
import ListaUsuariosComponet from '../Componets/ListaUsuariosComponet';


const UsuarioPage = () => {
    const [usuarios, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        getAllUsuarios()
            .then(data => {
                setUsers(data);
                setLoading(false);
            })
            .catch(err => {
                setError("No se pudo cargar la lista de usuarios");
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Cargando usuarios...</p>;
    if (error) return <p>{error}</p>;

    return (
        <div>
            <h1>Usuarios</h1>
            <ListaUsuariosComponet usuarios={usuarios} />
        </div>
    );
};

export default UsuarioPage;
