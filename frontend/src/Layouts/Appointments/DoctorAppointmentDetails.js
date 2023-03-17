import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

export default function DoctorAppointmentDetails (credentials) {
  const { id } = useParams();
  const [appointment, setAppointment] = useState(null);
  const [diagnoses, setDiagnoses] = useState([]);
  const [showDiagnosesList, setShowDiagnosesList] = useState(false);
  const [procedures, setProcedures] = useState([]);
  const [showProceduresList, setShowProceduresList] = useState(false);
  

  useEffect(() => {
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

  const handleDiagnosesClick = () => {
    setShowDiagnosesList(!showDiagnosesList);
  };

  const handleProceduresClick = () => {
    setShowProceduresList(!showProceduresList);
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
              <td className="col-6">
                <div className="text-left mt-3">
                  <h4>Diagnoses:</h4>
                  <ul className="list-group">
                    {appointment.diagnoses.map((diagnosis) => (
                      <li key={diagnosis.id} className="list-group-item">
                        {diagnosis.name}
                      </li>
                    ))}
                  </ul>
                </div>
                <div className="text-left mb-5">
                  <h4>Procedures:</h4>
                  <ul className="list-group">
                    {appointment.procedures.map((procedure) => (
                      <li key={procedure.id} className="list-group-item">
                        {procedure.name}
                      </li>
                    ))}
                  </ul>
                </div>

              </td>
              <td className="col-6">
                <button className="btn btn-primary my-4" onClick={handleDiagnosesClick}>
                  Add Diagnoses
                </button>
                {showDiagnosesList && (
                  <ul className="list-group">
                    {diagnoses.map((diagnosis) => (
                      <li key={diagnosis.id} className="list-group-item">
                        {diagnosis.name}
                      </li>
                    ))}
                  </ul>
                )}
                <br></br>
                <button className="btn btn-primary my-4" onClick={handleProceduresClick}>Add Procedures</button>
                {showProceduresList && (
                  <ul className="list-group">
                    {procedures.map((procedure) => (
                      <li key={procedure.id} className="list-group-item">
                        {procedure.name}
                      </li>
                    ))}
                  </ul>
                )}
              </td>
            </table>

          </div>
        </div>
      )}
    </div>
  );
}
