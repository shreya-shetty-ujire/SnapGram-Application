import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './Components/Profile/Register';
import Login from './Components/Profile/Login';
import './App.css';
import './styles/tailwind.css';

const App = () => {
  return (
      <Router>
        <div className="content">
          <Routes>
          <Route path="/" element={<Register />} /> {/* This will render Register on initial load */}
            <Route path="/register" element={<Register/>}/>
            <Route path="/login" element={<Login/>}/>
          </Routes>
        </div>
      </Router>
  );
};

export default App;
