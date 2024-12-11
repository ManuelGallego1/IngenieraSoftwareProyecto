import React, { useState, useEffect } from "react";
import { getAllProductos } from "../../Services/Productos/ProductosService";

const SeleccionarProductos = ({ onNext }) => {
  const [productos, setProductos] = useState([]);
  const [seleccionados, setSeleccionados] = useState({});

  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const response = await getAllProductos();
        setProductos(response);
      } catch (error) {
        console.error("Error al cargar los productos:", error);
      }
    };
    fetchProductos();
  }, []);

  const handleCantidadChange = (id, cantidad) => {
    setSeleccionados({
      ...seleccionados,
      [id]: cantidad,
    });
  };

  const handleSubmit = () => {
    const productosSeleccionados = productos.filter(
      (producto) => seleccionados[producto.id]
    );
    onNext(
      productosSeleccionados.map((producto) => ({
        ...producto,
        cantidad: seleccionados[producto.id],
      }))
    );
  };

  return (
    <div className="flex min-h-screen bg-gray-100">
      <aside className="w-64 bg-gray-800 text-white">
        {/* Aquí iría el contenido de la slidebar */}
      </aside>
      <main className="flex-1 p-4">
        <div className="seleccionar-productos">
          <h2 className="text-2xl font-bold mb-4">Seleccionar Productos</h2>
          <ul className="productos-list space-y-4">
            {productos.map((producto) => (
              <li
                key={producto.id}
                className="producto-item bg-white p-4 rounded shadow flex items-center"
              >
                <img
                  src={producto.imagen}
                  alt={producto.nombre}
                  className="w-16 h-16 object-cover rounded mr-4"
                />
                <div className="flex-1">
                  <h3 className="text-xl font-semibold mb-2">
                    {producto.nombre}
                  </h3>
                  <p className="text-gray-700 mb-2">
                    Descripción: {producto.descripcion}
                  </p>
                  <p className="text-gray-700 mb-2">
                    Precio: ${producto.precio}
                  </p>
                  <p className="text-gray-700 mb-2">
                    Categoría: {producto.categoria.nombre}
                  </p>
                  <input
                    type="number"
                    min="1"
                    placeholder="Cantidad"
                    value={seleccionados[producto.id] || ""}
                    onChange={(e) =>
                      handleCantidadChange(producto.id, e.target.value)
                    }
                    className="p-2 border border-gray-300 rounded w-full"
                  />
                </div>
              </li>
            ))}
          </ul>
          <button
            onClick={handleSubmit}
            className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Siguiente
          </button>
        </div>
      </main>
    </div>
  );
};

export default SeleccionarProductos;
