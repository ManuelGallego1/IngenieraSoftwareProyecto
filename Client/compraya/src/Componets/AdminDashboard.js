import React from 'react';
import { useAuth } from '../context/AuthContext';
import { Redirect } from 'react-router-dom';

const AdminDashboard = () => {
  const { isAuthenticated, isAdmin, logout } = useAuth();

  if (!isAuthenticated || !isAdmin) {
    return <Redirect to="/login" />;
  }

  return (
    <div>
      <h2>Admin Dashboard</h2>
      <button onClick={logout}>Logout</button>
    </div>
  );
};

export default AdminDashboard;
