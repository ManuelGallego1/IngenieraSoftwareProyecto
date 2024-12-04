import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import HeaderComponent from '../Components/HeaderComponent';
import FooterComponent from '../Components/FooterComponent';
import UsuarioPage from '../Pages/UsuarioPage';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import AdminDashboard from './components/AdminDashboard';
import { useAuth } from '../context/AuthContext'; // Importa el contexto de autenticación

const AppRoutes = () => {
    const { isAuthenticated, isAdmin } = useAuth();

    return (
        <Router>
            <HeaderComponent />
            <Routes>
                {/* Ruta principal redirige a /usuarios */}
                <Route path="/" element={<Navigate to="/usuarios" />} />
                
                {/* Ruta para usuarios */}
                <Route path="/usuarios" element={<UsuarioPage />} />
                
                {/* Ruta de login */}
                <Route path="/login" element={isAuthenticated ? <Navigate to={isAdmin ? "/admin" : "/dashboard"} /> : <Login />} />

                {/* Ruta para el dashboard del usuario */}
                <Route 
                    path="/dashboard" 
                    element={isAuthenticated ? <Dashboard /> : <Navigate to="/login" />} 
                />
                
                {/* Ruta para el dashboard del administrador */}
                <Route 
                    path="/admin" 
                    element={isAuthenticated && isAdmin ? <AdminDashboard /> : <Navigate to="/login" />} 
                />
                
                {/* Ruta para manejar rutas no encontradas */}
                <Route path="*" element={<h1>404 - Página no encontrada</h1>} />
            </Routes>
            <FooterComponent />
        </Router>
    );
};

export default AppRoutes;





 /*


import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, Switch } from 'react-router-dom';
import HeaderComponent from '../Componets/HeaderComponet';
import FooterComponent from '../Componets/FooterComponet';
import UsuarioPage from '../Pages/UsuarioPage';

import { Route, Switch } from 'react-router-dom';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import AdminDashboard from './components/AdminDashboard';


const AppRoutes = () => {
    return (
        <Router>
            <HeaderComponent />
            <Routes>
                <Route path="/" element={<Navigate to="/usuarios" />} />
                <Route path="/usuarios" element={<UsuarioPage />} />
                <Route path="*" element={<h1>404 - Página no encontrada</h1>} />
            </Routes>
            <FooterComponent />
        </Router>
        
    );
};
export default AppRoutes;
 */

