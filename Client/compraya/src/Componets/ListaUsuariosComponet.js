
import React from 'react';
import './ListaUsuariosComponet.css'; 

const ListaUsuariosComponets = ({ usuarios }) => {
  return (
    <div className="usuarios-container">
      <h2 className="title">Lista de Usuarios</h2>
      <ul className="usuarios-list">
        {usuarios.map((usuario) => (
          <li key={usuario.id} className="usuario-item">
            <div className="usuario-info">
              <p className="usuario-name">{usuario.nombre}</p>
              <p className="usuario-email">{usuario.identificacion}</p>
              <p className="usuario-email">{usuario.email}</p>
              <p className="usuario-email">{usuario.rol}</p>
              <p className="usuario-email">{usuario.celular}</p>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListaUsuariosComponets;
