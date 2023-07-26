
import React, { useState } from 'react';
import { Modal, ModalHeader, ModalBody } from "reactstrap";
import { getEmployee, saveCertificate} from '../../service/Config';
import './Certificate.css';

const CertificateFunction = (props) => {
  const [modal, setModal] = useState(false);
  const [newCertificate, setNewCertificate] = useState("");
  const [message, setMessage] = useState("");
  const [file, setFile] = useState(null);
  
  const loadEmployee = async event => {
    event.preventDefault();
    try {
    const result = await getEmployee();
    const employeeData = result.employeeCode;
    if (newCertificate.length === 0) {
      setMessage({ text:"Please Enter the Certificate Name", type: "warning"});
      return;
    }
    if (newCertificate) {
      saveAddCertificate(employeeData);
    }
    } catch (error) {
    console.log(error);
    }
  };

  const validation = (response) => {
    if (newCertificate) {
      if (!response.data) {
        setMessage({ text: "Error saving new Certificate!", type: "error" });
        setNewCertificate("");
      } else if (response.data.statusCode === "200") {
        setMessage({ text: "Certificate saved successfully!", type: "success" });
        setNewCertificate("");
      } else if (response.data.statusCode === "400") {
        if (response.data.discription.toLowerCase() === "certificate already exists") {
        setMessage({ text: "Certificate already exists", type: "warning" });
        setNewCertificate("")
      } else {
        setMessage({ text: response.data.discription, type: "error" });
        setNewCertificate("");
      }
    }
  }
  };

  const saveAddCertificate = async (employeeCode) => {
    try {
      const formData = new FormData();
      formData.append('file', file);
      formData.append('employeeCode', employeeCode);
      formData.append('certificatName', newCertificate);
      saveCertificate(formData).then(result => validation(result));
      props.updateCertificate();
    } catch (error) {
      console.log(error);
      setMessage({text:"Error saving Certificate!", type: "error"});
    }
  };

  const clearField = ()=>{
   setNewCertificate("");
  }

  const handleFileChange = (event) => {
    setFile(event.target.files[0]);
  };

  const handleRefresh = () => {
    setMessage("");
    
  }




  return (
    <div className="p-4">

    <form onSubmit={loadEmployee}>
    
        <Modal size="lg" isOpen={modal} toggle={() => setModal(!modal)}>

          <ModalHeader toggle={() => setModal(!modal)}>
            <p style={{ color: "rgb(28 19 86 / 77%)" }}> Add Certificate </p>
          </ModalHeader>

          <ModalBody>
            <div className="d-flex flex-column align-items-start">
            {message && (
            <p style={{ color: message.type === "error" ? "red" : message.type === "success" ? "green" : "orange" }}>
            {message.text}</p>)}

            <label><p style={{ color: "purple" }}>Enter the Certificate Name to be added :</p></label>
            <input type="text" value={newCertificate} onChange={(event) => {setNewCertificate(event.target.value);
                                                                            handleRefresh();}} />

              <input type="file" className="upload-file" onChange={handleFileChange}  />    

              <button className="btn mt-3 ml-3"
              style={{ backgroundColor: "rgb(28 19 86 / 77%)", color: "white" }} onClick={loadEmployee}>Save Certificate</button>
            </div>
          </ModalBody>

        </Modal>

        <div className="Certificate">
          <span>Certificate</span>
          <img src="/assets/icons/plus1.svg" alt="addCertificate" onClick={() => {
            setModal(true);
            handleRefresh();
            clearField();
          }} />
        </div>
      
      </form>

    </div>
  )
}
export default CertificateFunction;