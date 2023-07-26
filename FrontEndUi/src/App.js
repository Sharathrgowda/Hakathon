import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from './components/dashboard/Dashboard';
import LoginPage from './/components/login/Loginpage';
import ProtectedRouter from './components/security/ProtectedRouter'
import {useState} from 'react';
import './App.css'
import './Variable.css'

function App() {


  const [theme , setTheme] = useState("light");

  const toggleTheme = (themeFunction) =>{
    setTheme((themeFunction) => (themeFunction === "light" ? "dark" : "light"));
  }


  return (
    <div className="App" id={theme}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route element={<ProtectedRouter />}>
            <Route path="/dashboard" element={<Dashboard 
              themeData={theme}
              themeFunction={toggleTheme}
            />}></Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;