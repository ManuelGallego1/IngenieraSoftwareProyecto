import React, { useState, useEffect } from "react";
import { createDetalleFactura } from "../../Services/Facturas/DetalleFacturaService";

const DetalleFactura = ({ factura, productos, onNext, onBack }) => {
  const [detalles, setDetalles] = useState([]);

  useEffect(() => {
    const generarDetalles = async () => {
      if (!factura || !factura.id) {
        console.error("Factura no disponible o ID de factura no definido");
        return;
      }

      const nuevosDetalles = [];
      for (const producto of productos) {
        try {
          const detalle = {
            producto:{
                id: producto.id,

            },
            cantidad: producto.cantidad,
            precio: producto.precio,
            valorTotal: producto.precio * producto.cantidad,
            descuento: 0,
            factura: {
                id:factura.id
            }
          };
          const nuevoDetalle = await createDetalleFactura(detalle);
          nuevosDetalles.push(nuevoDetalle);
        } catch (error) {
          console.error("Error al agregar el detalle de la factura:", error);
        }
      }
      setDetalles(nuevosDetalles);
    };

    generarDetalles();
  }, [factura, productos]);

  const handleSubmit = () => {
    onNext(detalles);
  };

  return (
    <div className="flex min-h-screen bg-gray-100">
      <aside className="w-64 bg-gray-800 text-white">
        {/* Aquí iría el contenido de la slidebar */}
      </aside>
      <main className="flex-1 p-4">
        <div className="detalle-factura">
          <h2 className="text-2xl font-bold mb-4">Detalle de la Factura</h2>
          <ul className="productos-list space-y-4">
            {productos.map((producto) => (
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
          <button
            onClick={handleSubmit}
            className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Siguiente
          </button>
          <button
            onClick={onBack}
            className="mt-4 px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600"
          >
            Atrás
          </button>
        </div>
      </main>
    </div>
  );
};

export default DetalleFactura;