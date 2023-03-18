import React, { useEffect, useState } from 'react'
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios'

export default function PatientHistory(credentials) {

    const { id } = useParams();
    const [appointments, setAppointments] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchAppointments = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/appointments/history/${id}`, {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                });
                setAppointments(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (credentials) {
            fetchAppointments();
        }
    }, [credentials]);

    const handleViewDetails = (id) => {
        navigate(`/appointments/${id}/details`);
    };

  return (
    <div>
        <h1>Patient History</h1>
        <table className="table mt-5">
                <thead>
                    <tr>
                        <th scope="col">Date</th>
                        <th scope="col">Specialty</th>
                        <th scope="col">Doctor </th>
                        <th scope="col">Diagnoses </th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {appointments.map((appointment) => (
                        <tr key={appointment.id}>
                            <td>{appointment.date}</td>
                            <td>{appointment.specialty && appointment.specialty.name}</td>
                            <td>{`${appointment.doctor.firstName} ${appointment.doctor.lastName}`}</td>
                            <td></td>
                            <td>
                                <button
                                    className="btn btn-primary mx-1"
                                    onClick={handleViewDetails(appointment.id)}
                                >
                                    View Details
                                </button> 
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
    </div>
  )
}
