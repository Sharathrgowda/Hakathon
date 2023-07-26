import { useState } from "react";
import { getEmployee, loadSkills, saveSkill, addSkill,deleteSkill } from '../../service/Config'
import Multiselect from "multiselect-react-dropdown";
import { Modal, ModalHeader, ModalBody } from "reactstrap";
import { FaSync } from 'react-icons/fa';
import './Skills.css'

const SkillFunction = (props) => {
  const [modal, setModal] = useState(false);
  const [options, setOptions] = useState([]);
  const [selectedSkills, setSelectedSkills] = useState([]);
  const [newSkill, setNewSkill] = useState("");
  const [deleteSkills,setDeleteSkills] = useState("");
  const [message, setMessage] = useState("");
  const [refersh, setrefersh] = useState(false);
  const rotate = refersh ? "rotate(360deg)" : "rotate(0deg)"
  let employeeCode=""; 

  const loadEmployee = async event => {
    event.preventDefault();
    try {
      const result = await getEmployee();
      employeeCode = result.employeeCode;
      if (!newSkill && selectedSkills.length === 0 && deleteSkills.length===0) { 
        setMessage({ text: "Please select or add a Skill!", type: "warning" });
        return;
      }
      if (!newSkill && !deleteSkills) {
        handleSaveSkills(employeeCode);
      }
      if (newSkill) {
        handleAddSkill(employeeCode);
      }
        if(deleteSkills){
        handleDeleteSkill(employeeCode);
      }
    
    } catch (error) {
      console.log(error);
    }
  };

  const handleLoadSkill = async () => {
    handleRefresh();
    try {
      loadSkills().then(response => {
        let skillOptions = [];
        if (Array.isArray(response)) {
          skillOptions = response.map(skill => ({ Skill: skill }));
        } else if (response && typeof response === "object") {
          skillOptions = Object.keys(response).map(skill => ({
            Skill: skill
          }));
        }
        setOptions(skillOptions);
      })
    } catch (error) {
      console.log(error);
      setMessage({text: "Error loading Skills!", type: "error"});        
    }
  };

  const validation = (response) => {
    if (newSkill) {
      if (!response.data) {
        setMessage({ text: "Error saving new Skill!", type: "error" });
        setNewSkill("");
      } else if (response.data.statusCode === "200") {
        setMessage({ text: "New Skill saved successfully!", type: "success" });
        setNewSkill("");
      } else {
        setMessage({ text: response.data.discription, type: "error" });
        setNewSkill("");
      }
    }
    if (!newSkill) {
      if (!response.data) {
        setMessage({ text: "Error saving new Skill!", type: "error" });
      } else if (response.data.statusCode === "200") {
        setMessage({ text: "New Skill saved successfully!", type: "success" });
      } else if (response.data.statusCode === "400") {
        setMessage({ text: "Skill already present!", type: "warning" });
      } else {
        setMessage({ text: response.data.discription, type: "error" });
      }
    }
    if (deleteSkills) {
      if (!response.data) {
        setMessage({ text: "Error deleting Skill!", type: "error" });
        setDeleteSkills("");
      } else if (response.data.statusCode === "200") {
        setMessage({ text: "Skill deleted successfully!", type: "success" });
        setDeleteSkills("");
      } else if (response.data.statusCode === "400") {
        setMessage({ text: "Skill not present for the employee", type: "warning" });
        setDeleteSkills("");
      } else {
        setMessage({ text: response.data.discription, type: "error" });
        setDeleteSkills("");
      }
    }
  };

  const handleSaveSkills = async (employeeCode) => {
        try {
    const dataFetch = selectedSkills.map(skill => skill.Skill);
    if (dataFetch.length === 0) {
      setMessage("Please select the Skill!");
      return;
    }
    const skillNameJSON = JSON.stringify(dataFetch)
    const skillsToSave = {
    employeeCode: employeeCode,
    skillName: skillNameJSON.replace(/["[\]"]/g, '')
    };
    saveSkill(skillsToSave).then(result => validation(result));
    props.updateskill();
    } catch (error) {
    console.log(error);
    setMessage("Error saving Skills!");
    }
  };
    
  const handleAddSkill = async (employeeCode) => {
      try {
        const skillExists = options.some(option => option.Skill === newSkill);
        if (skillExists) {
          setMessage({ text:"Skill already present!", type: "warning"});
          return;
        }
        const newskillsToSave = {
          employeeCode: employeeCode,
          skillName: newSkill
        };
        addSkill(newskillsToSave).then(result => validation(result));
      } catch (error) {
        console.log(error);
        setMessage({text:"Error saving Skills!",type:"error"});
      }
  };
    
  const handleDeleteSkill = async (employeeCode) => {
      try {
        const skillsToDelete= {
          employeeCode: employeeCode,
          skillName: deleteSkills
        };
        deleteSkill(skillsToDelete).then(result=>validation(result))
        props.updateskill();
      } catch (error) {
          console.log(error);
          setMessage({text:"Error deleting Skill!",type:"error"});
      }
  };

  const handleRefresh = () => {
    setMessage("");
    setrefersh(!refersh)
  };

  const removeMessage = () => {
    setMessage("");
  }

  const clearField = () =>{
    setNewSkill("");
    setDeleteSkills("");
  }

  return (
    <div className="p-4">

      <form onSubmit={loadEmployee}>

        <Modal size="lg" isOpen={modal} toggle={() => setModal(!modal)}>
        
          <ModalHeader toggle={() => setModal(!modal)}>
            <p className="text mb-0 text-center" style={{ color: "rgb(28 19 86 / 77%)" }}>Select Skill 
            <FaSync style={{ transform: rotate, transition: "all 0.2s ",top: "-2px",position: "relative",left:"6px"  }}
            onClick={()=>{
              handleLoadSkill();
              handleRefresh();
              clearField();
            }}
            />
            </p>
          </ModalHeader>
          
          <ModalBody>
            <div className="d-flex flex-column">

               <p className="text mb-3" style={{ color: "rgb(28 19 86 / 77%)" }}>Note: If the skill is not present, please add the skill</p>
              
               {message && (
               <p style={{ color: message.type === "error" ? "red" : message.type === "success" ? "green" : "orange" }}>
                {message.text}
               </p>)}
              
            <div className="d-flex justify-content-left align-items-left mb-3">
                <Multiselect
                  options={options}
                  displayValue="Skill"
                  onSelect={setSelectedSkills}
                  onRemove={setSelectedSkills} />
              </div>
              
            <div className="d-flex justify-content-left align-items-left mb-3">

              <button
                  className="btn mt-3 ml-3"
                  style={{ backgroundColor: "rgb(28 19 86 / 77%)", color: "white" }}
                  onClick={loadEmployee}>
                  Save Skills
            </button>

            </div>

            <div className="d-flex flex-column align-items-start">
                <label><p style={{ color: "purple" }}>Enter the Skill Name to be added :</p></label>
                <input type="text" value={newSkill} onChange={(event) => {setNewSkill(event.target.value);
                                                                          removeMessage();}} />
                <button className="btn mt-3 ml-3"
                  style={{ backgroundColor: "rgb(28 19 86 / 77%)", color: "white" }} onClick={loadEmployee} >Add Skill</button>
              </div>

            <div className="d-flex flex-column align-items-start">
                <label><p style={{ color: "purple" }}>Enter the Skill Name to deleted :</p></label>
                <input type="text" value={deleteSkills} onChange={(event) => {setDeleteSkills(event.target.value);
                                                                              removeMessage();}} />
                <button className="btn mt-3 ml-3"
                  style={{ backgroundColor: "rgb(28 19 86 / 77%)", color: "white" }} onClick={loadEmployee} >Delete Skill</button>
              </div>
           
            </div>
          </ModalBody>
        
        </Modal>

        <div className="skills">
          <span>Skills</span>
          <img src="/assets/icons/plus.svg" alt="addSkill" onClick={() => {
            setModal(true);
            clearField();
            handleLoadSkill();
          }} />
        </div>
      
      </form>

    </div>
  )
}
export default SkillFunction;