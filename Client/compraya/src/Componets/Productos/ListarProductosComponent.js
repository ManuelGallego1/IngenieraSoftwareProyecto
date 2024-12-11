import React, { useState } from 'react';

const ListarProductosComponent = ({ productos = [], onEdit, onDelete }) => {
    const [searchTerm, setSearchTerm] = useState(''); // Estado para la búsqueda

    // Función para manejar cambios en el cuadro de búsqueda
    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value.toLowerCase());
    };

    // Filtrar los productos en función del término de búsqueda
    const filteredProductos = productos.filter((producto) =>
        producto.nombre.toLowerCase().includes(searchTerm) ||
        producto.categoria.nombre.toLowerCase().includes(searchTerm)
    );

    return (
        <div className="productos-container p-4">
            <h2 className="text-2xl font-bold mb-4">Lista de Productos</h2>

            {/* Cuadro de búsqueda */}
            <div className="search-container mb-4">
                <input
                    type="text"
                    placeholder="Buscar por nombre o descripción"
                    value={searchTerm}
                    onChange={handleSearchChange}
                    className="search-input p-2 border border-gray-300 rounded w-full"
                />
            </div>

            {/* Lista de productos */}
            <ul className="productos-list space-y-4">
                {filteredProductos.map((producto) => (
                    <li key={producto.id} className="producto-item bg-white p-4 rounded shadow flex items-center">
                        <img src={producto.imagen} alt={producto.nombre} className="w-16 h-16 object-cover rounded mr-4" />
                        <div className="flex-1">
                            <h3 className="text-xl font-semibold mb-2">{producto.nombre}</h3>
                            <p className="text-gray-700 mb-2">Descripción: {producto.descripcion}</p>
                            <p className="text-gray-700 mb-2">Precio: ${producto.precio}</p>
                            <p className="text-gray-700 mb-4">Categoría: {producto.categoria.nombre}</p>
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