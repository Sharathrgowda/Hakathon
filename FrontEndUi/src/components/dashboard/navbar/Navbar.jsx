import React, { useRef,useState } from "react";
import "../navbar/Navbar.css";
import {getAllUser, getEmployeeDetails, getProjectDetails} from '../../service/Config'
import { useNavigate } from "react-router-dom";
import { FaSync, FaMoon, FaSun, FaSignOutAlt } from 'react-icons/fa';

const Navbar = (props) => {

  localStorage.getItem("jwtToken");
  const navigate = useNavigate();
  const inputEle = useRef('');
  const [loading, setloading] = useState(false)

  const logout = () => {
    localStorage.removeItem("jwtToken");
    navigate("/");
  };

  const getThemedata = () =>{
    props.themeFunction("dark");
  }
  
  const handleSaveSync = async () => {
    try { 
      debugger
      setloading( true );
      await getAllUser();
      await getEmployeeDetails();
      getProjectDetails().then(()=>{setloading(false)});
     } catch (error) {
      alert('Error saving Sync!',error);
    }
  };

   return (
    <div className="navbar-menu">

      <div className="rigth-section">
        <div className="logo">
          <img src="/assets/images/logo.png" alt="excelsoft-logo" />
          <span>ExcelSoft</span>
        </div>
      </div>
      
      <div className="left-section">
      
      <div>

        <button type="submit" className="theme"
            ref={inputEle}
            onClick={getThemedata}
            checked={props.themeData === "dark"}>
            {props.themeData === "dark" ?
              <FaSun style={{top: "-2px", position: "relative",}}/> :
              <FaMoon style={{top: "-2px", position: "relative",}}/>
            }
        </button>

        <button type="submit" className="sync-icon" disabled={loading}  onClick= {handleSaveSync}>
        {loading && (
            <i
              className="fa fa-refresh fa-spin"
              style={{ position: "relative",left:"-6px" ,fontSize:"18px" }}
            />
          )}
           {loading && <span>Syncing</span> } 

         {!loading && <FaSync style={{top: "-2px",position: "relative",left:"-6px"}}/> } 
         {!loading && <span>Sync</span> } 
        </button>

        <button type="submit" className="signout-icon" onClick={logout}>
          <FaSignOutAlt style={{top: "-2px", position: "relative",}} /> &nbsp;
            <span>Sign Out</span>
        </button>
    
      </div>

      </div>

    </div>
  );
};

export default Navbar;