
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import HeaderComponent from '../Componets/HeaderComponet';
import FooterComponent from '../Componets/FooterComponet';
import UsuarioPage from '../Pages/Usuarios/UsuarioPage';
import UsuarioFormPage from "../Pages/Usuarios/UsuarioFormPage";


const AppRoutes = () => {
    return (
        <Router>
            <HeaderComponent />
            <Routes>
                <Route path="/" element={<Navigate to="/usuarios" />} />
                <Route path="/usuarios" element={<UsuarioPage />} />
                <Route path="/usuarios/crear" element={<UsuarioFormPage mode="create" />} />
                <Route path="/usuarios/editar/:id" element={<UsuarioFormPage mode="edit" />} />
                <Route path="*" element={<h1>404 - Página no encontrada</h1>} />
            </Routes>
            <FooterComponent />
        </Router>
    );
};

export default AppRoutes;
