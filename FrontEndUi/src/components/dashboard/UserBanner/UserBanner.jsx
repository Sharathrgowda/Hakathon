import React from "react";
import "./UserBanner.css";
import Skills from '../skills/Skills'
import Aboutme from "../aboutme/AboutMe";
import CertificateFunction from "../certificate/CertificateFunction";
import Project from "../projects/Project";
import SkillFunction from "../skills/SkillsFunction";
import Certificate from "../certificate/Certificate";
import { FaEnvelope } from 'react-icons/fa';
import { FaUserAlt } from 'react-icons/fa';

const UserBanner = props => {
  return (
    
    <div className="classwrap">

      <div className="left-section">

        <div className="profile-image">
        {props.data.gender === "M" ?
          <img src="/assets/images/male.png" alt="icon" /> :
          <img src="/assets/images/female.png" alt="icon" />
        }
        </div>
        
        <div className="employee-info">
        <h3>{props.data.employeeName}</h3>
        <h6>{<FaEnvelope />} &nbsp; {props.data.emailId}</h6>
        <h6 id="empdesignation"> {<FaUserAlt />} &nbsp; {props.data.designation}</h6>
        </div>

        <div className="section-divider"></div>

        <div className="skill-section">
          <div className="addSkills">
            <SkillFunction 
             updateskill = {props.updateData}
            />
          </div>
          <div className="skillList">
            {props.data.skills && props.data.skills.map((skillsdata, index) => {
              return (
                      <div className="skills" key={index}>
                       <Skills skillName={skillsdata.skillName}/>
                      </div>
                );
                })}
          </div>
        </div>

      </div>

      <div className="right-section">

        <div className="aboutme-section">
          <h4>About Me : </h4>
          <div className="underline"></div>
          <div className="aboutme">
            <Aboutme
              employeeCode={props.data.employeeCode}
              gender={props.data.gender}
              department={props.data.department}
              location={props.data.location}
              mobileNumber={props.data.mobileNumber}
              joinDate={props.data.joinDate}
              reportingEmailId={props.data.reportingEmailId}
              employeeGrade={props.data.employeeGrade}
            />
          </div>
        </div>

        <div className="row">

          <div className="certificate-section col-sm-6">
            <div className="addcertificate">
              <CertificateFunction 
               updateCertificate = {props.updateData}
              />
            </div>
            <div className="underline"></div>
            <div className="certificateList">
              {props.data.certification && props.data.certification.map((certificate, index) => {
                return (
                        <div className="Certificate" key={index}>
                        <Certificate 
                        certificateName={certificate.certificatName}
                        updateCertificate={props.updateData}
                        />
                    </div>
                  );
                  })}
            </div>
          </div>

          <div className="project-section col-sm-5">
            <h4>Projects </h4>
            <div className="underline"></div>
            <div className="project-list">
              {props.data.projectDetailes && props.data.projectDetailes.map((projectdata, index) => {
                return (
                        <div className="Project" key={index}>
                        <Project projectname={projectdata.projectTitle} />
                        </div>
                      );
                      })}
            </div>
          </div>
        
        </div>

      </div>

    </div>
  );
};

export default UserBanner;
