import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ListaProductosComponent from '../../Componets/Productos/ListarProductosComponent';
import ModalEntradaInventario from '../../Componets/Productos/ModalEntradaInventario';
import { getAllProductos, deleteProducto } from '../../Services/Productos/ProductosService'; // Asegúrate de importar getAllProductos

const ProductosPage = () => {
    const [productos, setProductos] = useState([]);
    const [selectedProducto, setSelectedProducto] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProductos = async () => {
            try {
                const data = await getAllProductos(); // Usa getAllProductos en lugar de getProductos
                setProductos(data);
            } catch (error) {
                console.error('Error al obtener los productos:', error);
            }
        };

        fetchProductos();
    }, []);

    const handleEdit = (id) => {
        navigate(`/productos/editar/${id}`);
    };

    const handleDelete = async (id) => {
        if (window.confirm('¿Estás seguro de que deseas eliminar este producto?')) {
            try {
                await deleteProducto(id);
                setProductos(productos.filter((producto) => producto.id !== id));
                alert('Producto eliminado con éxito.');
            } catch (error) {
                console.error('Error al eliminar el producto:', error);
                alert('Error al eliminar el producto.');
            }
        }
    };

    const handleEntrada = (producto) => {
        setSelectedProducto(producto);
        setIsModalOpen(true);
    };

    const closeModal = () => {
        setIsModalOpen(false);
        setSelectedProducto(null);
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
                        onClick={() => navigate('/productos/crear')}
                        className="mb-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
                    >
                        Crear Producto
                    </button>
                    <ListaProductosComponent
                        productos={productos}
                        onEdit={handleEdit}
                        onDelete={handleDelete}
                        onEntrada={handleEntrada}
                    />
                </div>
            </main>
            {selectedProducto && (
                <ModalEntradaInventario
                    isOpen={isModalOpen}
                    onClose={closeModal}
                    producto={selectedProducto}
                />
            )}
        </div>
    );
};

export default ProductosPage;