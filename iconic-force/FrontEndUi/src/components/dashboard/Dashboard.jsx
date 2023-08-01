import React, { useState, useEffect, useReducer } from 'react'
import { getEmployee } from '../service/Config'
import Navbar from "../dashboard/navbar/Navbar"
import UserBanner from "../dashboard/UserBanner/UserBanner"
import Subemployee from '../dashboard/subemployee/Subemployee'
import '../../../src/App.css'

function Dashboard(props) {

  const [employeeData, setEmployeeData] = useState([]);
  const [showSubEmployee, setShowSubemployee] = useState(true)
  const [reducerValue, forceUpdate] = useReducer(x => x + 1, 0);

  useEffect(() => {
    getEmployee().then(EmployeeInfo => {
      setEmployeeData(EmployeeInfo)
      if (EmployeeInfo.details.length === 0) {
        setShowSubemployee(false)
      }
    })
  },[reducerValue])

  return (
    <>
      <Navbar 
       themeData={props.themeData}
       themeFunction={props.themeFunction}
      />

      <UserBanner
        data={employeeData}
        updateData={forceUpdate} />

      {showSubEmployee ?
      <Subemployee data={employeeData} />
      : null}
    </>
  );
}

export default Dashboard