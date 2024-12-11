import React from "react";
import { generateXML } from "../../Services/Facturas/FacturaService";

const GenerarXML = ({ factura, detalles, onBack }) => {
  const handleGenerateXML = async () => {
    try {
      const xml = await generateXML(factura.id);
      // Aquí puedes manejar el XML generado, por ejemplo, descargarlo
      console.log(xml);
    } catch (error) {
      console.error("Error al generar el XML:", error);
    }
  };

  return (
    <div className="flex min-h-screen bg-gray-100">
      <aside className="w-64 bg-gray-800 text-white">
        {/* Aquí iría el contenido de la slidebar */}
      </aside>
      <main className="flex-1 p-4">
        <div className="generar-xml">
          <h2 className="text-2xl font-bold mb-4">Generar XML de la Factura</h2>
          <button
            onClick={handleGenerateXML}
            className="mt-4 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Generar XML
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

export default GenerarXML;
