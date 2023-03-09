import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function DoctorList() {

    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/doctors')
            .then(response => setDoctors(response.data))
            .catch(error => console.error(error));
    }, []);

    return (
        <div>
            <h2>Doctors</h2>
            <ul>
                {doctors.map(doctor => (
                    <li key={doctor.id}>{doctor.id} - {doctor.name}</li>
                ))}
            </ul>
        </div>
    )
}
