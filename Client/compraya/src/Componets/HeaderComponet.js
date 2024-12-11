import React from 'react';
import { Link } from 'react-router-dom';
import 'tailwindcss/tailwind.css';

const HeaderComponent = () => {
    return (
        <div className="fixed h-full w-64 bg-gray-800 text-white">
            <nav className="mt-10">
                <ul>
                    <li className="mb-6">
                        <Link to="/" className="text-lg font-semibold hover:text-gray-400">Inicio</Link>
                    </li>
                    <li className="mb-6">
                        <Link to="/usuarios" className="text-lg font-semibold hover:text-gray-400">Usuarios</Link>
                    </li>
                    <li className="mb-6">
                        <Link to="/productos" className="text-lg font-semibold hover:text-gray-400">Productos</Link>
                    </li>
                </ul>
            </nav>
        </div>
    );
};

export default HeaderComponent;
