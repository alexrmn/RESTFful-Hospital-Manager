import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Summary from "./Summary";


export default function DoctorAppointmentDetails(credentials) {
  const { id } = useParams();
  const [appointment, setAppointment] = useState(null);
  const [diagnoses, setDiagnoses] = useState([]);
  const [showDiagnosesList, setShowDiagnosesList] = useState(false);
  const [procedures, setProcedures] = useState([]);
  const [showProceduresList, setShowProceduresList] = useState(false);

  const [searchDiagnosisQuery, setSearchDiagnosisQuery] = useState('');
  const [searchProcedureQuery, setSearchProcedureQuery] = useState('');

  const fetchAppointment = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/appointments/${id}`, {
        headers: { Authorization: `Bearer ${credentials.token}` },
      });
      setAppointment(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {

    const fetchDiagnoses = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/diagnoses`, {
          headers: { Authorization: `Bearer ${credentials.token}` },
        });
        setDiagnoses(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    const fetchProcedures = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/procedures`, {
          headers: { Authorization: `Bearer ${credentials.token}` },
        });
        setProcedures(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchAppointment();
    fetchDiagnoses();
    fetchProcedures();
  }, [credentials, id]);


  const filteredDiagnoses = diagnoses.filter(
    (diagnosis) =>
      `${diagnosis.name}`
        .toLowerCase()
        .includes(searchDiagnosisQuery.toLowerCase())
  );

  const filteredProcedures = procedures.filter(
    (procedure) =>
      `${procedure.name}`
        .toLowerCase()
        .includes(searchProcedureQuery.toLowerCase())
  );

  const handleAddDiagnosesClick = () => {
    setShowDiagnosesList(!showDiagnosesList);
  };

  const handleAddProceduresClick = () => {
    setShowProceduresList(!showProceduresList);
  };

  const handleDiagnosisSearchQueryChange = (event) => {
    setSearchDiagnosisQuery(event.target.value);
  };

  const handleProcedureSearchQueryChange = (event) => {
    setSearchProcedureQuery(event.target.value);
  };

  const handleAddDiagnosisClick = async (diagnosis) => {
    try {
      const response = await axios.post(
        `http://localhost:8080/appointments/${id}/diagnoses/${diagnosis.id}`,
        null,
        {
          headers: { Authorization: `Bearer ${credentials.token}` },
        }
      );
      console.log("Diagnosis added successfully");
      //refresh Appointment
      fetchAppointment();

    } catch (error) {
      console.error(error);
    }
  };

  const handleAddProcedureClick = async (procedure) => {
    try {
      const response = await axios.post(
        `http://localhost:8080/appointments/${id}/procedures/${procedure.id}`,
        null,
        {
          headers: { Authorization: `Bearer ${credentials.token}` },
        }
      );
      console.log("Procedure added successfully");
      // refresh Appointment
      fetchAppointment();
    } catch (error) {
      console.error(error);
    }
  };

  const handleRemoveDiagnosisClick = async (diagnosis) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/appointments/${id}/diagnoses/${diagnosis.id}`,
        {
          headers: { Authorization: `Bearer ${credentials.token}` },
        }
      );
      console.log("Diagnosis removed successfully");
      //refresh Appointment
      fetchAppointment();

    } catch (error) {
      console.error(error);
    }
  };

  const handleRemoveProcedureClick = async (procedure) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/appointments/${id}/procedures/${procedure.id}`,
        {
          headers: { Authorization: `Bearer ${credentials.token}` },
        }
      );
      console.log("Procedure removed successfully");
      // Refresh appointment
      fetchAppointment();
    } catch (error) {
      console.error(error);
    }
  };



  return (
    <div className="container">
      <h1 className="my-4">Doctor Appointment Details</h1>
      {appointment && (
        <div className="card my-4">
          <div className="card-body">
            <h3 className="card-title">
              Patient Name: {appointment.patient.firstName} {appointment.patient.lastName}
            </h3>
            <h4 className="card-subtitle mb-2 text-muted">Date: {appointment.date}</h4>
            <h4 className="card-subtitle mb-2 text-muted">Time: {appointment.timeSlot.name}</h4>
            <table className="table">
              <tbody>
                <tr>
                  <td className="col-6">
                    <div className="text-left mt-3">
                      <h4>Diagnoses:</h4>
                      <table className="table table-hover" style={{ maxHeight: "150px", overflowY: "scroll" }}>
                        <tbody>
                          {appointment.diagnoses.map((diagnosis) => (
                            <tr key={diagnosis.id}>
                              <td className="col-10">{diagnosis.name}</td>
                              <td className="col-2">
                                <button className='btn btn-outline-secondary btn-sm' onClick={() => handleRemoveDiagnosisClick(diagnosis)}>Remove</button>
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>
                    <div className="text-left mb-5">
                      <h4>Procedures:</h4>
                      <table className="table table-hover" style={{ maxHeight: "150px", overflowY: "scroll" }}>
                        <tbody>
                          {appointment.procedures.map((procedure) => (
                            <tr key={procedure.id}>
                              <td className="col-10">{procedure.name}</td>
                              <td className="col-2">
                                <button className='btn btn-outline-secondary btn-sm' onClick={() => handleRemoveProcedureClick(procedure)}>Remove</button>
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>

                  </td>
                  <td className="col-6">

                    <button className="btn btn-primary my-4" onClick={handleAddDiagnosesClick}>
                      Add Diagnoses
                    </button>
                    {showDiagnosesList && (
                      <>
                        <input
                          type="text"
                          className="form-control"
                          placeholder="Search:"
                          value={searchDiagnosisQuery}
                          onChange={handleDiagnosisSearchQueryChange}
                        />
                        <ul className="list-group" style={{ maxHeight: "150px", overflowY: "scroll" }}>
                          {filteredDiagnoses.map((diagnosis) => (
                            <li key={diagnosis.id} className="list-group-item d-flex justify-content-between align-items-center">
                              {diagnosis.name}
                              <button className='btn btn-outline-secondary btn-sm' onClick={() => handleAddDiagnosisClick(diagnosis)}>Add</button>
                            </li>
                          ))}
                        </ul>
                      </>
                    )}
                    <br></br>
                    <button className="btn btn-primary my-4" onClick={(handleAddProceduresClick)}>Add Procedures</button>
                    {showProceduresList && (
                      <>
                        <input
                          type="text"
                          className="form-control"
                          placeholder="Search:"
                          value={searchProcedureQuery}
                          onChange={handleProcedureSearchQueryChange}
                        />
                        <ul className="list-group" style={{ maxHeight: "150px", overflowY: "scroll" }}>
                          {filteredProcedures.map((procedure) => (
                            <li key={procedure.id} className="list-group-item d-flex justify-content-between align-items-center">
                              {procedure.name}
                              <button className="btn btn-outline-secondary btn-sm" onClick={() => handleAddProcedureClick(procedure)}>Add</button>
                            </li>
                          ))}
                        </ul>
                      </>
                    )}
                  </td>
                </tr>
                <tr>
                  <td colSpan={2} >
                    <div className="form-floating">
                      <h3>Summary</h3>
                      <Summary credentials={credentials} appointmentId={id} initialSummary={appointment.summary} />
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
}
