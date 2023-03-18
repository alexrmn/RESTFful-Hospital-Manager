import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

export default function AppointmentDetails(credentials) {

  const { id } = useParams();
  const [appointment, setAppointment] = useState(null)

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


    fetchAppointment();

  }, [credentials, id]);

  return (
    <div className="container">
      <h1>Appointment details</h1>
      {appointment && (
        <div className="card my-4 col-10 mx-auto">
          <div className="card-body">
            <h3 className="card-title">
              Doctor: {appointment.doctor.firstName} {appointment.doctor.lastName}
            </h3>
            <h4 className="card-subtitle mb-2 text-muted">Date: {appointment.date}</h4>
            <h4 className="card-subtitle mb-2 text-muted">Time: {appointment.timeSlot.name}</h4>


            <table className="col-10 mx-auto">
              <td className="col-6">
                <h4>Diagnoses:</h4>
                <ul className="list-group">
                  {appointment.diagnoses.map((diagnosis) => (
                    <li key={diagnosis.id} className="list-group-item">
                      {diagnosis.name}
                    </li>
                  ))}
                </ul>
              </td>
              <td>
                <h4>Procedures done:</h4>
                <ul className="list-group">
                  {appointment.procedures.map((diagnosis) => (
                    <li key={diagnosis.id} className="list-group-item">
                      {diagnosis.name}
                    </li>
                  ))}
                </ul>
              </td>
            </table>
            <div className="mt-5">
              <h4>Appointment summary</h4>
              {appointment.summary && appointment.summary.split('\n').map((line, index) => (
                <p className="text-start" key={index}>{line}</p>
              ))}
            </div>
          </div>
        </div>
      )}
    </div>
  )
}
