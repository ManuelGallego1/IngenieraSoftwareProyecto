import React from 'react';
import 'tailwindcss/tailwind.css';

const FooterComponent = () => {
    return (
        <footer className="bg-gray-800 text-white py-4 mt-auto w-full fixed bottom-0">
            <div className="container mx-auto text-center">
                <p>© 2024 COMPRAYA - Todos los derechos reservados</p>
            </div>
        </footer>
    );
};

export default FooterComponent;
