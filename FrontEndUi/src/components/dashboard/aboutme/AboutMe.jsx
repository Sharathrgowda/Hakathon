import React from 'react'
import './AboutMe.css'

const Aboutme = (props) => {
  
  return (
    <div className="aboutme">

      <div className="employee-info-1">
        <h5><span className="bold-text">EmployeeId : </span> {props.employeeCode}</h5>
        <h5><span className="bold-text">Gender : </span> {props.gender}</h5>
        <h5><span className="bold-text">Department : </span> {props.department}</h5>
        <h5><span className="bold-text">Location : </span> {props.location} </h5>
      </div>

      <div className="employee-info-2">
        <h5><span className="bold-text">Phone : </span> {props.mobileNumber} </h5>
        <h5><span className="bold-text">Date Of Join : </span> {props.joinDate}</h5>
        <h5><span className="bold-text">Reporting Manager : </span> {props.reportingEmailId} </h5>
        <h5><span className="bold-text">Employee Grade : </span> {props.employeeGrade} </h5>
      </div>

    </div>
  )
}

export default Aboutme
