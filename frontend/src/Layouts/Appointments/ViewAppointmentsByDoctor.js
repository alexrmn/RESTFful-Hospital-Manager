import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios'

export default function ViewAppointmentsByDoctor(credentials) {

    const [appointments, setAppointments] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchAppointments = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/appointments/scheduled/${credentials.userId}`, {
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

    return (
        <div className='mt-5'>
            <h1>My Scheduled Appointments</h1>

            <table className="table mt-5">
                <thead>
                    <tr>
                        <th scope="col">Date</th>
                        <th scope="col" className='col-4'>Patient</th>
                        <th scope="col">Diagnoses </th>
                        <th scope="col"> </th>
                    </tr>
                </thead>
                <tbody>
                    {appointments.map((appointment) => (
                        <tr key={appointment.id}>
                            <td>{appointment.date}</td>
                            <td>{`${appointment.patient.firstName} ${appointment.patient.lastName}`}</td>
                            {/* <td>
                                <ol>{appointment.diagnoses.map((diagnosis) => (
                                    <li key={diagnosis.id}> {diagnosis.name}</li>
                                ))}
                                </ol>
                            </td> */}


                            <td>
                                <button
                                    className="btn btn-primary mx-1"
                                    onClick={() => navigate(`/appointments/${appointment.id}`)}
                                >
                                    View Appointment
                                </button>

                            </td>

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}
