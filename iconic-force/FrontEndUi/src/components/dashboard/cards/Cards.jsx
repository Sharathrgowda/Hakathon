import React, { useState } from 'react'
import { motion, AnimateSharedLayout } from 'framer-motion'
import { UilTimes } from '@iconscout/react-unicons'
import '../cards/Cards.css'

const Cards = (props) => {

const [expanded, setExpanded] = useState(false);

    return (
        <div className="Card">
            <AnimateSharedLayout>
                {
                expanded ?
                <ExpandedCard param={props} setExpanded={() => setExpanded(false)} /> :
                <CompactCard param={props} setExpanded={() => setExpanded(true)} />
                }
            </AnimateSharedLayout>
        </div>
       )
}

function CompactCard({ param, setExpanded }) {
    
    return (

        <motion.div className="compactcard"
            onClick={setExpanded}
            layoutId='expandableCard'>

            <div className="profile">
            {param.gender === "M" ?
                    <img src="/assets/images/male.png" alt="icon" /> :
                    <img src="/assets/images/female.png" alt="icon" />
            }
                <div className="details">
                    <h6 id="name">{param.employeeName}</h6>
                    <h6 id="designation">{param.designation}</h6>
                    <h6 id="mobilenumber">{param.mobileNumber}</h6>
                    <h6 id="email">{param.employeeEmail}</h6>
                </div>
           
            </div>
        
        </motion.div>
    );
}

function ExpandedCard({ param, setExpanded }) {
    return (

        <motion.div className="ExpandedCard" layoutId='expandableCard'>

            <div className="close-btn">
                <UilTimes onClick={setExpanded} />
            </div>

            <div className="Complete_Employee_details">

                <div className="profile-image">
                {param.gender === "M" ?
                    <img src="/assets/images/male.png" alt="icon" /> :
                    <img src="/assets/images/female.png" alt="icon" />
                }
                </div>
                
                <div className="employee-details">
                    <h2>{param.employeeName}</h2>
                    <h6 id="employeeid">EmployeeId <span>: {param.employeeCode}</span></h6>
                    <h6 id="email">Email <span>: {param.employeeEmail}</span></h6>
                    <h6 id="mobilenumber">Contact Number <span>: {param.mobileNumber}</span></h6>
                    <h6 id="designation"> Designation <span>: {param.designation}</span></h6>
                    <h6 id="department">Department <span>: {param.department}</span></h6>
                    <h6 id="reportingManager">Reporting Manager <span> : {param.reportingManager}</span></h6>
                    <h6 id="grade">Employee Grade <span>: {param.employeeGrade}</span></h6>
                    <h6 id="joinDate">Join-Date <span>: {param.joinDate}</span></h6>
                    <h6 id="gender">Gender <span>: {param.gender}</span></h6>
                    <h6 id="location">Location <span>: {param.location}</span></h6>
                    <h6 id="skills">Skills : {param.skills && param.skills.map((skillsdata,index) => {
                        return (
                             <span key={index}>{skillsdata.skillName}</span>  
                        )
                    })}</h6>
                    <h6 id="project">Projects : {param.project && param.project.map((projectdata,index) => {
                        
                        return (
                             <span key={index}>{projectdata.projectTitle}</span> 
                               )
                    })}</h6>
                    <br></br>
                    <br></br>

                </div>
            
            </div>

        </motion.div>
    );
}

export default Cards
