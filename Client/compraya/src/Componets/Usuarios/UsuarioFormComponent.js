import React, { useState, useEffect } from 'react';

const UsuarioFormComponent = ({ usuarioSeleccionado, onSave, onCancel }) => {
  const [usuario, setUsuario] = useState({
    nombre: '',
    identificacion: '',
    email: '',
    rol: '',
    celular: ''
  });

  useEffect(() => {
    if (usuarioSeleccionado) {
      setUsuario(usuarioSeleccionado);
    }
  }, [usuarioSeleccionado]);

  const handleChange = (e) => {
    setUsuario({
      ...usuario,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSave(usuario);
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>{usuarioSeleccionado ? "Editar Usuario" : "Crear Usuario"}</h2>
      <input name="nombre" value={usuario.nombre} onChange={handleChange} placeholder="Nombre" required />
      <input name="identificacion" value={usuario.identificacion} onChange={handleChange} placeholder="Identificación" required />
      <input name="email" value={usuario.email} onChange={handleChange} placeholder="Email" required />
      <input name="rol" value={usuario.rol} onChange={handleChange} placeholder="Rol" required />
      <input name="celular" value={usuario.celular} onChange={handleChange} placeholder="Celular" required />
      <button type="submit">Guardar</button>
      <button type="button" onClick={onCancel}>Cancelar</button>
    </form>
  );
};

export default UsuarioFormComponent;
