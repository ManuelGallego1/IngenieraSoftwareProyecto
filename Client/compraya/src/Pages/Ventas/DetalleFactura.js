import React, { useState } from "react";
import { createDetalleFactura } from "../../Services/Facturas/DetalleFacturaService";

const DetalleFactura = ({ factura, onNext, onBack }) => {
  const [detalles, setDetalles] = useState([]);

  const handleAddDetalle = async (detalle) => {
    try {
      const nuevoDetalle = await createDetalleFactura({
        ...detalle,
        facturaId: factura.id,
      });
      setDetalles([...detalles, nuevoDetalle]);
    } catch (error) {
      console.error("Error al agregar el detalle de la factura:", error);
    }
  };

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
          {/* Aquí puedes agregar un formulario para agregar detalles */}
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
