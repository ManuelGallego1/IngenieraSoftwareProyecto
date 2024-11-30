
import React from 'react';
import { Link } from 'react-router-dom';

const HeaderComponent = () => {
    return (
        <header>
            <nav>
                <ul>
                    <li><Link to="/">Inicio</Link></li>
                    <li><Link to="/usuarios">Usuarios</Link></li>
                </ul>
            </nav>
        </header>
    );
};

export default HeaderComponent;
