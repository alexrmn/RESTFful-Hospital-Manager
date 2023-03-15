import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import axios from 'axios'
import { toast } from 'react-toastify';

export default function (credentials) {

    const [appointments, setAppointments] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchAppointments = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/appointments/upcoming/${credentials.userId}`, {
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

    const handleCancelAppointment = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/appointments/${id}`, {
                headers: { Authorization: `Bearer ${credentials.token}` },
            });
            setAppointments(appointments.filter((appointment) => appointment.id !== id));
            toast.success('Appointment canceled!', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
        } catch (error) {
            console.error(error);
        }
    };

    const handleEditAppointment = (id) => {
        navigate(`/appointments/${id}/edit`);
    };

  return (
    <div>
        <h1 className='mt-5'>My Uppcoming Appointments</h1>
        <table className="table mt-5">
                <thead>
                    <tr>
                        <th scope="col">Date</th>
                        <th scope="col">Specialty</th>
                        <th scope="col">Doctor </th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {appointments.map((appointment) => (
                        <tr key={appointment.id}>
                            <td>{appointment.date}</td>
                            <td>{appointment.specialty.name}</td>
                            <td>{`${appointment.doctor.firstName} ${appointment.doctor.lastName}`}</td>
                            <td>
                                <button
                                    className="btn btn-primary mx-1"
                                    onClick={() => handleEditAppointment(appointment.id)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="btn btn-danger mx-1"
                                    onClick={() => handleCancelAppointment(appointment.id)}
                                >
                                    Cancel
                                </button>
                            </td>

                        </tr>
                    ))}
                </tbody>
            </table>
    </div>
  )
}
