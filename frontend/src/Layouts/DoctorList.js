import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function DoctorList({ token }) {
    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/doctors', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                console.log(response);
                setDoctors(response.data);
            })
            .catch(error => {
                console.error(error);
                console.log(error.response);
                if (error.response.status === 401) {
                    console.log('Unauthorized access. Please login again.');
                }
                setDoctors([]);
            });
        console.log('Request headers:', axios.defaults.headers);
    }, [token]);

    return (
        <div>
            <h2>Doctors</h2>
            <p>{token}</p>
            <ul>
                {doctors.map(doctor => (
                    <li key={doctor.id}>
                        {doctor.id} - {doctor.firstName} {doctor.lastName} ({doctor.email}) - {doctor.specialtyName}
                    </li>
                ))}
            </ul>
        </div>
    );
}