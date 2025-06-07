import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from "react-router-dom";
import HomePage from "./pages/HomePage";
import MeetingDetailPage from "./pages/MeetingDetailPage";
import Login from "./pages/Login";
import NavBar from "./components/NavBar";

// ProtectedRoute component
const ProtectedRoute = ({ children }) => {
  const attendeeId = localStorage.getItem("attendeeId");
  const location = useLocation();
  if (!attendeeId) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }
  return children;
};

const App = () => (
  <Router>
    <NavBar />
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route
        path="/"
        element={
          <ProtectedRoute>
            <HomePage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/meetings/:id"
        element={
          <ProtectedRoute>
            <MeetingDetailPage />
          </ProtectedRoute>
        }
      />
    </Routes>
  </Router>
);

export default App;