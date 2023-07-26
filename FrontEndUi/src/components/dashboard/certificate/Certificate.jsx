import React ,{useState} from 'react'
import './Certificate.css'
import Modal from 'react-modal';
import { getCertificate, getEmployee, removeCertificate} from '../../service/Config'
import { FaEye, FaTrash } from 'react-icons/fa';
import { UilTimes } from '@iconscout/react-unicons'


const Certificate = (props) => {
  const [imagePath, setImagePath] = useState(null)
  const [modalIsOpen, setModalIsOpen] = useState(false);

  const loadCertificate = async event => {
    event.preventDefault();
    try {
    const result = await getEmployee();
    const employeeData = result.employeeCode;
    getCertificate(employeeData,props.certificateName).then(result=>{
      setImagePath(result)
      setModalIsOpen(true);
    })
    props.updateCertificate();
    } catch (error) {
    console.log(error);
    }
  };

  const deleteCertificate = async event => {
    event.preventDefault();
    try {
    const result = await getEmployee();
    const employeeData = result.employeeCode;
    removeCertificate(employeeData,props.certificateName).then(()=>{
    alert("Do you want to delete?")
    })
    props.updateCertificate();
    } catch (error) {
    console.log(error);
    }
  };

  const closeModal = () => {
    setImagePath(null);
    setModalIsOpen(false);
  }


return (
  
    <div className="certificatedata">

      <div className="certificate-view">
        <span>{props.certificateName}</span>
        <div className="certificate-function" >
        <FaEye className="eye" onClick={loadCertificate}/>
        <FaTrash style={{cursor:"pointer"}} onClick={deleteCertificate}  /></div>
      </div>

      <Modal className="model-image-view" isOpen={modalIsOpen} onRequestClose={closeModal} ariaHideApp={false} >
        <div className="modelheader">
        <UilTimes style={{cursor:"pointer",  position: "sticky", left: "97%" }} onClick={closeModal}  />
        </div>
        <div className="modelbody">
        {imagePath && <img src={`http://localhost:8080/${imagePath}`} alt="" />}
        </div>
      </Modal>

    </div>
  
  )
}

export default Certificate
