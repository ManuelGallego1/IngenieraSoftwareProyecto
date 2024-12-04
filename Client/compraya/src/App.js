import logo from './logo.svg';
import { BrowserRouter as Router, Routes, Route,Navigate } from "react-router-dom";
import './App.css';
import ListaUsuariosComponets from './Componets/ListaUsuariosComponet';
import { AuthProvider } from './context/AuthContext';
import HeaderComponet from './Componets/HeaderComponet';
import FooterComponet from './Componets/FooterComponet';
import Routes from './Routes/Routes';

const App = () => {
    return (
      <AuthProvider>
        <Router>
          <Routes />
        </Router>
      </AuthProvider>
    );
  };

export default App;



