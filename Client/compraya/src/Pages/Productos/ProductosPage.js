import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ListaProductosComponent from "../../Componets/Productos/ListarProductosComponent";
import {
  getAllProductos,
  deleteProducto,
} from "../../Services/Productos/ProductosService";

const ProductosPage = () => {
  const [productos, setProductos] = useState([]);
  const navigate = useNavigate();

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

  const handleEdit = (id) => {
    navigate(`/productos/editar/${id}`);
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm(
      "¿Estás seguro de que quieres eliminar este producto?"
    );
    if (confirmDelete) {
      try {
        await deleteProducto(id);
        setProductos(productos.filter((producto) => producto.id !== id));
        alert("Producto eliminado con éxito.");
      } catch (error) {
        console.error("Error al eliminar el producto:", error);
        alert("Error al eliminar el producto.");
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
          <h1 className="text-2xl font-bold mb-4">Productos</h1>
          <button
            onClick={() => navigate("/productos/crear")}
            className="mb-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Crear Producto
          </button>
          <ListaProductosComponent
            productos={productos}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
        </div>
      </main>
    </div>
  );
};

export default ProductosPage;
