import React, { useState } from 'react';
import './ListaUsuariosComponet.css';

const ListaUsuariosComponets = ({ usuarios, onEdit, onDelete }) => {
  const [searchTerm, setSearchTerm] = useState(''); // Estado para la búsqueda

  // Función para manejar cambios en el cuadro de búsqueda
  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value.toLowerCase());
  };

  // Filtrar los usuarios en función del término de búsqueda
  const filteredUsuarios = usuarios.filter((usuario) =>
    usuario.nombre.toLowerCase().includes(searchTerm) ||
    usuario.identificacion.toLowerCase().includes(searchTerm) ||
    usuario.email.toLowerCase().includes(searchTerm)
  );

  return (
    <div className="usuarios-container">
      <h2 className="title">Lista de Usuarios</h2>

      {/* Cuadro de búsqueda */}
      <div className="search-container">
        <input
          type="text"
          placeholder="Buscar por nombre, identificación o email"
          value={searchTerm}
          onChange={handleSearchChange}
          className="search-input"
        />
      </div>

      {/* Lista de usuarios */}
      <ul className="usuarios-list">
        {filteredUsuarios.map((usuario) => (
          <li key={usuario.id} className="usuario-item">
            <div className="usuario-info">
              <p className="usuario-name">Nombre: {usuario.nombre}</p>
              <p className="usuario-identificacion">ID: {usuario.identificacion}</p>
              <p className="usuario-email">Email: {usuario.email}</p>
              <p className="usuario-rol">Rol: {usuario.rol}</p>
              <p className="usuario-celular">Celular: {usuario.celular}</p>
            </div>
            <div className="usuario-actions">
              <button onClick={() => onEdit(usuario.id)}>Editar</button>
              <button onClick={() => onDelete(usuario.id)}>Eliminar</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListaUsuariosComponets;
