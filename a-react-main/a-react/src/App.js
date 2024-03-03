import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./Login"; // Import existing components
import New from "./New"; // Import the new page component

function App() {
  return (
    <BrowserRouter>
      <Routes>
          <Route index element={<Login />} />
          <Route path="/target" element={<New />} />
          <Route path="*" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
