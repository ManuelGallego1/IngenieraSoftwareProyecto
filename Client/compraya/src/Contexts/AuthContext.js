import React, { createContext, useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [userId, setUserId] = useState(null);
  const history = useHistory();

  useEffect(() => {
    const user = localStorage.getItem('user');
    if (user) {
      const { id, admin } = JSON.parse(user);
      setUserId(id);
      setIsAuthenticated(true);
      setIsAdmin(admin);
    }
  }, []);

  const login = async (email, password) => {
    const response = await fetch('http://localhost:8080/auth/login', {
      method: 'POST',
      body: new URLSearchParams({
        email,
        password,
      }),
    });

    const data = await response.json();
    if (response.status === 200) {
      const { id, rol } = data;
      localStorage.setItem('user', JSON.stringify({ id, admin: rol === 'admin' }));
      setUserId(id);
      setIsAuthenticated(true);
      setIsAdmin(rol === 'admin');
      history.push(rol === 'admin' ? '/admin' : '/dashboard');
    } else {
      alert(data.error);
    }
  };

  const logout = () => {
    localStorage.removeItem('user');
    setIsAuthenticated(false);
    setIsAdmin(false);
    setUserId(null);
    history.push('/login');
  };

  return (
    <AuthContext.Provider value={{ isAuthenticated, isAdmin, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => React.useContext(AuthContext);
