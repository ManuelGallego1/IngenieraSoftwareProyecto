import React, { useState } from 'react';
import { registrarEntrada } from '../../Services/Inventario/InventarioService';

const ModalEntradaInventario = ({ isOpen, onClose, producto }) => {
    const [entrada, setEntrada] = useState('');
    const [referenciaCompra, setReferenciaCompra] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await registrarEntrada(producto.id, referenciaCompra, entrada);
            alert('Entrada registrada con éxito.');
            onClose();
        } catch (error) {
            console.error('Error al registrar la entrada:', error);
            alert('Error al registrar la entrada.');
        }
    };

    if (!isOpen) return null;

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center">
            <div className="bg-white p-6 rounded shadow-md w-96">
                <h2 className="text-xl font-bold mb-4">Registrar Entrada para {producto.nombre}</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-4">
                        <label className="block text-gray-700">Referencia de Compra</label>
                        <input
                            type="text"
                            value={referenciaCompra}
                            onChange={(e) => setReferenciaCompra(e.target.value)}
                            className="p-2 border border-gray-300 rounded w-full"
                            required
                        />
                    </div>
                    <div className="mb-4">
                        <label className="block text-gray-700">Cantidad de Entrada</label>
                        <input
                            type="number"
                            value={entrada}
                            onChange={(e) => setEntrada(e.target.value)}
                            className="p-2 border border-gray-300 rounded w-full"
                            required
                        />
                    </div>
                    <div className="flex justify-end">
                        <button type="button" onClick={onClose} className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600 mr-2">
                            Cancelar
                        </button>
                        <button type="submit" className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
                            Registrar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default ModalEntradaInventario;