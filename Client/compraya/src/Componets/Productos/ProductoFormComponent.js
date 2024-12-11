import React, { useState, useEffect } from 'react';
import { getAllProductos, getProductoById, createProducto, updateProducto, deleteProducto } from './ProductoService';

const ProductoFormComponent = () => {
    const [productos, setProductos] = useState([]);
    const [producto, setProducto] = useState({ nombre: '', descripcion: '', precio: 0, imagen: '', categoria: null });
    const [id, setId] = useState(null);

    useEffect(() => {
        fetchProductos();
    }, []);

    const fetchProductos = async () => {
        try {
            const data = await getAllProductos();
            setProductos(data);
        } catch (error) {
            console.error("Error fetching products:", error);
        }
    };

    const handleCreateProducto = async () => {
        try {
            const newProducto = await createProducto(producto);
            setProductos([...productos, newProducto]);
        } catch (error) {
            console.error("Error creating product:", error);
        }
    };

    const handleUpdateProducto = async () => {
        try {
            const updatedProducto = await updateProducto(id, producto);
            setProductos(productos.map(p => (p.id === id ? updatedProducto : p)));
        } catch (error) {
            console.error("Error updating product:", error);
        }
    };

    const handleDeleteProducto = async (id) => {
        try {
            await deleteProducto(id);
            setProductos(productos.filter(p => p.id !== id));
        } catch (error) {
            console.error("Error deleting product:", error);
        }
    };

    return (
        <div>
            {/* Renderizar productos y formularios para crear/actualizar */}
        </div>
    );
};

export default ProductoFormComponent;