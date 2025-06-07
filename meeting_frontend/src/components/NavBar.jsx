import { useNavigate, useLocation, Link } from "react-router-dom";

// Navigation Bar component
const NavBar = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("attendeeId");
    navigate("/login");
  };

  // Hide navbar on login page
  const location = useLocation();
  if (location.pathname === "/login") return null;

  return (
    <nav className="bg-white shadow mb-6">
      <div className="container mx-auto px-4 py-3 flex justify-between items-center">
        <Link to="/" className="text-2xl font-bold text-blue-700 hover:text-blue-900">
          Meeting Management
        </Link>
        <button
          onClick={handleLogout}
          className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition-colors"
        >
          Logout
        </button>
      </div>
    </nav>
  );
};

export default NavBar;