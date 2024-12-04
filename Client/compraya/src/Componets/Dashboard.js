import React from 'react';
import { useAuth } from '../context/AuthContext';
import { Redirect } from 'react-router-dom';

const Dashboard = () => {
  const { isAuthenticated, logout } = useAuth();

  if (!isAuthenticated) {
    return <Redirect to="/login" />;
  }

  return (
    <div>
      <h2>Welcome to your Dashboard</h2>
      <button onClick={logout}>Logout</button>
    </div>
  );
};

export default Dashboard;
