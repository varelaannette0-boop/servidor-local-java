import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Registo from "./pages/Registo";
import Dashboard from "./pages/Dashboard";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />

        <Route path="/registo" element={<Registo />} />

        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;