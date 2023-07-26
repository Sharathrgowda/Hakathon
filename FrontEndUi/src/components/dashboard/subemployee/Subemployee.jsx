import React, { useState, useEffect } from "react";
import { loadSkills } from '../../service/Config'
import "./Subemployee.css";
import Cards from "../cards/Cards";
import SearchBar from "../searchbar/SearchBar"

const Subemployee = props => {
  const [searchValue, setSearchValue] = useState("");
  const [selectedProjectTitle, setSelectedProjectTitle] = useState("");
  const [skills, setskills] = useState([]);
  const [selectedSkills, setSelectedSkills] = useState("");
  
  useEffect(() => {
    loadSkills().then(SkillInfo => {
      setskills(SkillInfo)
    })
  }, [])

  const handleSearchInput = (e) => {
    if (e.target.name === "project") {
      setSelectedProjectTitle(e.target.value);
    }
    else if (e.target.name === 'skill') {
      setSelectedSkills(e.target.value);
    }
  };

  const searchHandler = (searchValue)=>{
    setSearchValue(searchValue);
  };

 const filteredCards = props.data.details && props.data.details
    .filter(card => card.employeeName.toLowerCase().includes(searchValue.toLowerCase()))
    .filter(card => !selectedProjectTitle || (card.projectId && card.projectId.map((inside) => (inside.projectTitle.includes(selectedProjectTitle)))).includes(true))
    .filter(card => !selectedSkills || (card.skillId && card.skillId.map((inside) => (inside.skillName.includes(selectedSkills)))).includes(true));

  return (
    <div className="sub-employee-details">

      <div className="upper-container">

        <div className="searchbar">
           <SearchBar
           data={searchValue}
           searchValue={searchHandler}/>
        </div>

        <div className="skills-dropdown">
          <select
            name="skill"
            value={selectedSkills}
            onChange={handleSearchInput}
          >
          <option value="">All Skills</option>
            {skills && skills.map((item, index) => {
            return (
                  <option key={index} value={item}>
                    {item}
                  </option>
                );
            })}
          </select>
        </div>

        <div className="project-dropdown">
          <select
            name="project"
            value={selectedProjectTitle}
            onChange={handleSearchInput}
          >
          <option value="">All projects</option>
            {props.data.projectDetailes && props.data.projectDetailes.map((item, index) => {
              return (
                  <option key={index} value={item.projectTitle}>
                    {item.projectTitle}
                  </option>
                );
            })}
          </select>
        </div>
      
      </div>

      <div className="lower-container">
        {filteredCards && filteredCards.map((cardDetails, index) => {
          return (
            <div className="Cards" key={index}>
              <Cards
                  employeeCode={cardDetails.employeeCode}
                  employeeName={cardDetails.employeeName}
                  employeeEmail={cardDetails.emailId}
                  mobileNumber={cardDetails.mobileNumber}
                  designation={cardDetails.designation}
                  department={cardDetails.department}
                  employeeGrade={cardDetails.employeeGrade}
                  joinDate={cardDetails.joinDate}
                  reportingManager={cardDetails.reportingEmailId}
                  gender={cardDetails.gender}
                  location={cardDetails.location}
                  project={cardDetails.projectId}
                  skills={cardDetails.skillId}
              />
            </div>
            );
        })}
      </div>
    
    </div>

  );
};

export default Subemployee;
