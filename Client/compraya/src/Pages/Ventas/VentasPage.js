import React, { useState } from 'react';
import SeleccionarProductos from './SeleccionarProductos';
import GenerarFactura from './GenerarFactura';
import DetalleFactura from './DetalleFactura';
import GenerarXML from './GenerarXML';

const VentasPage = () => {
    const [step, setStep] = useState(1);
    const [productosSeleccionados, setProductosSeleccionados] = useState([]);
    const [factura, setFactura] = useState(null);
    const [detallesFactura, setDetallesFactura] = useState([]);

    const nextStep = () => setStep(step + 1);
    const prevStep = () => setStep(step - 1);

    const handleProductosSeleccionados = (productos) => {
        setProductosSeleccionados(productos);
        nextStep();
    };

    const handleFacturaGenerada = (factura) => {
        setFactura(factura);
        nextStep();
    };

    const handleDetallesFactura = (detalles) => {
        setDetallesFactura(detalles);
        nextStep();
    };

    return (
        <div className="ventas-page">
            {step === 1 && <SeleccionarProductos onNext={handleProductosSeleccionados} />}
            {step === 2 && <GenerarFactura productos={productosSeleccionados} onNext={handleFacturaGenerada} onBack={prevStep} />}
            {step === 3 && <DetalleFactura factura={factura} productos={productosSeleccionados} onNext={handleDetallesFactura} onBack={prevStep} />}
            {step === 4 && <GenerarXML factura={factura} detalles={detallesFactura} onBack={prevStep} />}
        </div>
    );
};

export default VentasPage;