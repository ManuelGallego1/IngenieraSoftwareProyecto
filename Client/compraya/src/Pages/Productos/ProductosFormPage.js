import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getProductoById, createProducto, updateProducto } from '../../Services/Productos/ProductosService';
import { getAllCategorias } from '../../Services/Categorias/CategoriaService';
import HeaderComponent from '../../Componets/HeaderComponet';

const ProductosFormPage = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [producto, setProducto] = useState({
        nombre: '',
        descripcion: '',
        precio: '',
        categoria: {
            id: '',
            nombre: ''
        },
        imagen: ''
    });
    const [categorias, setCategorias] = useState([]);
    const [mode, setMode] = useState(id ? 'edit' : 'create');

    useEffect(() => {
        const fetchCategorias = async () => {
            try {
                const data = await getAllCategorias();
                setCategorias(data);
            } catch (error) {
                console.error("Error al cargar las categorías:", error);
                alert("Error al cargar las categorías.");
            }
        };

        fetchCategorias();

        if (id) {
            const fetchProducto = async () => {
                try {
                    const data = await getProductoById(id);
                    setProducto(data);
                } catch (error) {
                    console.error("Error al cargar el producto:", error);
                    alert("Error al cargar los datos del producto.");
                }
            };
            fetchProducto();
        }
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name.startsWith('categoria.')) {
            const categoriaField = name.split('.')[1];
            setProducto((prevState) => ({
                ...prevState,
                categoria: {
                    ...prevState.categoria,
                    [categoriaField]: value
                }
            }));
        } else {
            setProducto((prevState) => ({
                ...prevState,
                [name]: value,
            }));
        }
    };

    const isValid = () => {
        const { nombre, descripcion, precio, categoria } = producto;
        return nombre && descripcion && precio && categoria.id;
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!isValid()) {
            alert("Por favor, completa todos los campos obligatorios.");
            return;
        }
        try {
            if (mode === 'edit') {
                await updateProducto(id, producto);
                alert("Producto actualizado con éxito.");
            } else {
                await createProducto(producto);
                alert("Producto creado con éxito.");
            }
            navigate('/productos');
        } catch (error) {
            console.error("Error al guardar el producto:", error);
            alert("Error al guardar el producto.");
        }
    };

    return (
        <div className="flex">
            <HeaderComponent />
            <div className="ml-64 p-8 w-full">
                <div className="productos-form-container p-4">
                    <h2 className="text-2xl font-bold mb-4">
                        {mode === 'edit' ? 'Editar Producto' : 'Crear Producto'}
                    </h2>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-4">
                            <label className="block text-gray-700">Nombre</label>
                            <input
                                type="text"
                                name="nombre"
                                value={producto.nombre}
                                onChange={handleChange}
                                className="p-2 border border-gray-300 rounded w-full"
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700">Descripción</label>
                            <textarea
                                name="descripcion"
                                value={producto.descripcion}
                                onChange={handleChange}
                                className="p-2 border border-gray-300 rounded w-full"
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700">Precio</label>
                            <input
                                type="number"
                                name="precio"
                                value={producto.precio}
                                onChange={handleChange}
                                className="p-2 border border-gray-300 rounded w-full"
                                required
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700">Categoría</label>
                            <select
                                name="categoria.id"
                                value={producto.categoria.id}
                                onChange={handleChange}
                                className="p-2 border border-gray-300 rounded w-full"
                                required
                            >
                                <option value="">Seleccione una categoría</option>
                                {categorias.map((categoria) => (
                                    <option key={categoria.id} value={categoria.id}>
                                        {categoria.nombre}
                                    </option>
                                ))}
                            </select>
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700">Imagen URL</label>
                            <input
                                type="text"
                                name="imagen"
                                value={producto.imagen}
                                onChange={handleChange}
                                className="p-2 border border-gray-300 rounded w-full"
                            />
                        </div>
                        <div className="flex justify-end">
                            <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
                                {mode === 'edit' ? 'Actualizar' : 'Crear'}
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default ProductosFormPage;