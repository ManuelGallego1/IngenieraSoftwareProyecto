import React, { useState, useEffect } from 'react';
import { getInventario } from '../../Services/Inventario/InventarioService';

const ListarProductosComponent = ({ productos = [], onEdit, onDelete }) => {
    const [inventarios, setInventarios] = useState({});

    useEffect(() => {
        const fetchInventarios = async () => {
            const inventariosTemp = {};
            for (const producto of productos) {
                try {
                    const data = await getInventario(producto.id);
                    inventariosTemp[producto.id] = data.inventario;
                } catch (error) {
                    console.error(`Error al obtener el inventario para el producto ${producto.id}:`, error);
                }
            }
            setInventarios(inventariosTemp);
        };

        fetchInventarios();
    }, [productos]);

    return (
        <div className="productos-container p-4">
            <h2 className="text-2xl font-bold mb-4">Lista de Productos</h2>

            {/* Lista de productos */}
            <ul className="productos-list space-y-4">
                {productos.map((producto) => (
                    <li key={producto.id} className="producto-item bg-white p-4 rounded shadow flex items-center">
                        <img src={producto.imagen} alt={producto.nombre} className="w-16 h-16 object-cover rounded mr-4" />
                        <div className="flex-1">
                            <h3 className="text-xl font-semibold mb-2">{producto.nombre}</h3>
                            <p className="text-gray-700 mb-2">Descripción: {producto.descripcion}</p>
                            <p className="text-gray-700 mb-2">Precio: ${producto.precio}</p>
                            <p className="text-gray-700 mb-2">Categoría: {producto.categoria.nombre}</p>
                            <p className="text-gray-700 mb-4">Inventario: {inventarios[producto.id] !== undefined ? inventarios[producto.id] : 'Cargando...'}</p>
                        </div>
                        <div className="flex space-x-2">
                            <button onClick={() => onEdit(producto.id)} className="bg-blue-500 text-white px-4 py-2 rounded">Editar</button>
                            <button onClick={() => onDelete(producto.id)} className="bg-red-500 text-white px-4 py-2 rounded">Eliminar</button>
                        </div>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ListarProductosComponent;