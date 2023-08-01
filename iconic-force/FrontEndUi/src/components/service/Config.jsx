import axios from 'axios'

const BASE_URL = 'http://localhost:8090/api/v1';

export const getAuthenticate = async (email, password) => {
    const response = await axios.post(`${BASE_URL}/auth/authenticate`, {
        email,
        password
    });
    return response.data
}

export const getAllUser = async () => {
    debugger
    const response = await axios.get(`${BASE_URL}/employee-controller/external-user`, {
        mode: "cors",
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem('jwtToken'),
            'Content-Type': 'application/json',  "Access-Control-Allow-Origin": "*"
        }
    }
    )
    return response.data
}

export const getEmployeeDetails = async () => {
    const response = await axios.get(`${BASE_URL}/employee-controller/external-data`, {
        mode: "cors",
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem('jwtToken'),
            'Content-Type': 'application/json',  "Access-Control-Allow-Origin": "*"
        }
    }
    )
    return response.data
}

export const getProjectDetails = async () => {
    const response = await axios.get(`${BASE_URL}/employee-controller/external-api`, {
        mode: "cors",
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem('jwtToken'),
            'Content-Type': 'application/json',  "Access-Control-Allow-Origin": "*"
        }
    }
    )
    return response.data
}

export const getEmployee = async () => {
    const response = await axios.get(`${BASE_URL}/employee-controller/getEmployeeDetailes`, {
        mode: "cors",
        headers: {
            Authorization: "Bearer " + localStorage.getItem("jwtToken"),
            "Access-Control-Allow-Origin": "*"
        }
    }
    )
    return response.data
}

export const loadSkills = async () => {
    const response = await axios.get(`${BASE_URL}/employee-controller/skills`, {
        mode: "cors",
        method: "GET",
        headers: {
            Authorization: "Bearer " + localStorage.getItem("jwtToken"),
            "Access-Control-Allow-Origin": "*"
        }
    })
    return response.data
}

export const saveSkill = async (skillsToSave) => {
    const response = await axios.post(`${BASE_URL}/employee-controller/AddingnewSkills`,
        skillsToSave,
        {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("jwtToken"),
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        })
    return response;
}

export const addSkill = async (newskillsToSave) => {
    const response = await axios.post(`${BASE_URL}/employee-controller/AddingnewSkills`,
        newskillsToSave,
        {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("jwtToken"),
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "application/json"
            }
        })
    return response;
}

export const deleteSkill = async (skillsToDelete) =>{
    const response = await axios.post(`${BASE_URL}/employee-controller/DeleteSkills`,
    skillsToDelete,
    {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("jwtToken"),
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
    }
    })
    return response;
}

export const saveCertificate = async (formData) => {
    const response = await axios.post(`${BASE_URL}/file-controller`, formData,{
            headers: {
                Authorization: "Bearer " + localStorage.getItem("jwtToken"),
                "Access-Control-Allow-Origin": "*",
                "Content-Type": "multipart/form-data"
            }
        })
    return response;
}

export const getCertificate = async (employeeCode,certificatName) => {
   
    const response = await axios.get(`${BASE_URL}/file-controller/path/${employeeCode}/${certificatName}`,{
        headers: {
            Authorization: "Bearer " + localStorage.getItem("jwtToken"),
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
        }
    });
    return response.data
}

export const removeCertificate = async (employeeCode,certificatName) => {
     const response = await axios.delete(`${BASE_URL}/file-controller/path/${employeeCode}/${certificatName}`,{
         headers: {
             Authorization: "Bearer " + localStorage.getItem("jwtToken"),
             "Access-Control-Allow-Origin": "*",
             "Content-Type": "application/json"
         }
     });
     return response.data
}

 