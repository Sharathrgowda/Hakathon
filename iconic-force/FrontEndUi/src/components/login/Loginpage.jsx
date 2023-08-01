import React, { useState } from "react";
import { getAuthenticate, getEmployee } from "../service/Config";
import "./LoginPage.css";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState();
  const [employee, setEmployee] = useState("");
  const navigate = useNavigate();

  const validation = (email, password) => {
    setEmployee(localStorage.getItem("jwtToken"));
    setError(null);
    if (email === "" && password === "") {
      setError("Enter the credentials!");
      return;
    } else if (email === null || email === "") {
      setError("Email cannot be empty!");
      return;
    } else if (!/^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
      setError("Invalid email format.");
      return;
    } else if (password === null || password === "") {
      setError("Password cannot be empty!");
      return;
    } else if (employee.length === 0) {
      setError("");
      return;
    } else {
      setError("Invalid Credentials!");
    }
  };

  const loadEmployee = async event => {
    event.preventDefault();
    try {
      getAuthenticate(email, password)
        .then(result => {
          localStorage.setItem("jwtToken", result.token);
          validation(email, password);
        })
        .then(() => {
          getEmployee().then(() => {
            navigate("/dashboard");
          });
        });
    } catch (error) {
      setError("Error");
      
    }
  };

  const clearError = () => {
    setError("");
  };

  return (
    <>
      <div>
          <div className="container">
          <div className="img">
            <img src="/assets/images/bg.png" alt="bg"></img>
          </div>

           <form onSubmit={loadEmployee}>
          
            <h2 className="title">Welcome</h2>
            
            <div>

            <div className="form-group">
                  <span className="icon w3-xxlarge">
                  <ion-icon name="mail"></ion-icon>
                </span>
                <input type="text" required 
                    onChange={event => {
                    setEmail(event.target.value);
                    clearError();
                  }} />
                <label style={{ color: "black" }}>Email</label>
              </div>
              
              <div className="form-group">
                  <span className="icon">
                  <ion-icon name="lock-closed"></ion-icon>
                </span>

                <input type="password" required   
                onChange={event => {
                    setPassword(event.target.value);
                    clearError();
                  }}/>
                <label style={{ color: "black" }}>Password</label>
              </div>
            <div className="error-message">
              <p style={{ color: "red" }}>{error}</p>
            </div>
            
            <button className="login-button">Login</button>
            </div>
          </form> 
          </div>

      </div>
    </>
  );
};

export default LoginPage;