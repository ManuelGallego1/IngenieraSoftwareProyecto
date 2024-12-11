import React, { useState, useEffect } from 'react';
import { createFactura } from '../../Services/Facturas/FacturaService';
import { getAllMetodosPago } from '../../Services/MetodoPago/MetodoPagoService';
import { getClientes } from '../../Services/Usuarios/UsuarioService';

const GenerarFactura = ({ productos, onNext, onBack }) => {
    const [metodosPago, setMetodosPago] = useState([]);
    const [clientes, setClientes] = useState([]);
    const [selectedCliente, setSelectedCliente] = useState('');
    const [selectedMetodoPago, setSelectedMetodoPago] = useState('');

    useEffect(() => {
        const fetchMetodosPago = async () => {
            try {
                const data = await getAllMetodosPago();
                setMetodosPago(data);
            } catch (error) {
                console.error("Error al obtener los métodos de pago:", error);
            }
        };

        const fetchClientes = async () => {
            try {
                const data = await getClientes();
                setClientes(data);
            } catch (error) {
                console.error("Error al obtener los clientes:", error);
            }
        };

        fetchMetodosPago();
        fetchClientes();
    }, []);

    const generateUniqueCode = () => {
        return 'FAC' + Date.now();
    };

    const handleSubmit = async () => {
        const subtotal = productos.reduce((acc, producto) => acc + producto.precio * producto.cantidad, 0);
        const totalImpuestos = subtotal * 0.19; // Suponiendo un 19% de impuestos
        const total = subtotal + totalImpuestos;

        const facturaData = {
            codigo: generateUniqueCode(),
            fecha: new Date().toISOString(),
            subtotal,
            totalImpuestos,
            total,
            estado: "PAGADA",
            cliente: {
                id: selectedCliente
            },
            metodoPago: {
                id: selectedMetodoPago
            }
        };

        try {
            const factura = await createFactura(facturaData);
            onNext(factura);
        } catch (error) {
            console.error("Error al crear la factura:", error);
        }
    };

    return (
        <div className="flex min-h-screen bg-gray-100">
            <aside className="w-64 bg-gray-800 text-white">
                {/* Aquí iría el contenido de la slidebar */}
            </aside>
            <main className="flex-1 p-4">
                <div className="generar-factura">
                    <h2 className="text-2xl font-bold mb-4">Generar Factura</h2>
                    <ul className="productos-list space-y-4">
                        {productos.map(producto => (
                            <li key={producto.id} className="producto-item bg-white p-4 rounded shadow flex items-center">
                                <div className="flex-1">
                                    <h3 className="text-xl font-semibold mb-2">{producto.nombre}</h3>
                                    <p className="text-gray-700 mb-2">Cantidad: {producto.cantidad}</p>
                                    <p className="text-gray-700 mb-2">Precio: ${producto.precio}</p>
                                    <p className="text-gray-700 mb-2">Subtotal: ${producto.precio * producto.cantidad}</p>
                                </div>
                            </li>
                        ))}
                    </ul>
                    <div className="mb-4">
                        <label className="block text-gray-700">Cliente</label>
                        <select
                            value={selectedCliente}
                            onChange={(e) => setSelectedCliente(e.target.value)}
                            className="p-2 border border-gray-300 rounded w-full"
                            required
                        >
                            <option value="">Seleccione un cliente</option>
                            {clientes.map(cliente => (
                                <option key={cliente.id} value={cliente.id}>
                                    {cliente.nombre}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700">Método de Pago</label>
                        <select
                            value={selectedMetodoPago}
                            onChange={(e) => setSelectedMetodoPago(e.target.value)}
                            className="p-2 border border-gray-300 rounded w-full"
                            required
                        >
                            <option value="">Seleccione un método de pago</option>
                            {metodosPago.map(metodo => (
                                <option key={metodo.id} value={metodo.id}>
                                    {metodo.metodo}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className="flex justify-between mt-4">
                        <button onClick={onBack} className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600">
                            Atrás
                        </button>
                        <button onClick={handleSubmit} className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
                            Generar Factura
                        </button>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default GenerarFactura;