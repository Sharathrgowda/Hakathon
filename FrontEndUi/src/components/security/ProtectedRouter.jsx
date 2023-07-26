import React from 'react'
import { Navigate, Outlet } from 'react-router';

const useAuth = () => {
    let tokens = localStorage.getItem("jwtToken");
    return tokens;
}

const isTokenExpired = (token) => {
    if (!token) return true;
    const decodedToken = JSON.parse(atob(token.split('.')[1]));
    return decodedToken.exp * 1000 < Date.now();
}

const ProtectedRouter = () => {
    const token = useAuth();
    const isAuth = !isTokenExpired(token) && token !== '';

    return isAuth ? <Outlet /> : <Navigate to="/" />;
}

export default ProtectedRouter;