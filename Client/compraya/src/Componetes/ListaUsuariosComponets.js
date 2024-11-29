import React, { useEffect, useState } from 'react'
import UsuarioService from '../Services/UsuarioService'

export const ListaUsuariosComponets  = () => {

    const [usuarios,setUsuarios] = useState([])

  useEffect(() =>{
    UsuarioService.getAllUsuarios().then(response => {
      setUsuarios(response.data);
      console.log(response.data);
    }).catch(error => {
      console.log(error);
    })
  },[])



  return (
    <div className='Container'>
      <h2 className='text-center'> ListaUsuarios</h2>
      <table className=''>
        <thead>
          <th>ID</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Rol</th>
          <th>ID</th>
        </thead>
        <tbody>
          {
            usuarios.map(
              usuarios =>
                <tr key={usuarios.id}>
                  <td>{usuarios.nombre}</td>
                  <td>{usuarios.identificacion}</td>
                  <td>{usuarios.email}</td>
                  <td>{usuarios.rol}</td>
                  <td>{usuarios.celular}</td>
              </tr>
                )
          }
        </tbody>

      </table>
     
      </div>
  )
}



export default ListaUsuariosComponets;
