import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import HeaderComponent from "../Componets/HeaderComponet";
import FooterComponent from "../Componets/FooterComponet";
import UsuarioPage from "../Pages/Usuarios/UsuarioPage";
import UsuarioFormPage from "../Pages/Usuarios/UsuarioFormPage";
import PuntosPage from "../Pages/Puntos/PuntosPage";
import PuntosFormPage from "../Pages/Puntos/PuntosFormPage";
import ProductosPage from "../Pages/Productos/ProductosPage"
import ProductosFormPage from "../Pages/Productos/ProductosFormPage";

const AppRoutes = () => {
  return (
    <Router>
      <HeaderComponent />
      <Routes>
        <Route path="/" element={<Navigate to="/usuarios" />} />
        <Route path="/usuarios" element={<UsuarioPage />} />
        <Route
          path="/usuarios/crear"
          element={<UsuarioFormPage mode="create" />}
        />
        <Route
          path="/usuarios/editar/:id"
          element={<UsuarioFormPage mode="edit" />}
        />
        <Route path="*" element={<h1>404 - Página no encontrada</h1>} />
        <Route path="/puntos" element={<PuntosPage />} />
        <Route
          path="/puntos/crear"
          element={<PuntosFormPage mode="create" />}
        />
        <Route
          path="/puntos/editar/:id"
          element={<PuntosFormPage mode="edit" />}
        />
        <Route
          path="/productos"
          element={<ProductosPage/>}
        />
        <Route
          path="/productos/crear"
          element={<ProductosFormPage mode="create" />}
        />
        <Route
          path="/productos/editar/:id"
          element={<ProductosPage mode="edit" />}
        />
      </Routes>
      <FooterComponent />
    </Router>
  );
};

export default AppRoutes;
