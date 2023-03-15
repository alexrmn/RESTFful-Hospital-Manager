import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';

export default function (credentials) {
    const { id } = useParams();
    const [specialty, setSpecialty] = useState('');
    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        const fetchSpecialty = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/specialties/${id}`, {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                })
                setSpecialty(response.data)
                console.log(response.data)
            } catch (error) {
                console.error(error)
            }
        }

        if (credentials) {
            fetchSpecialty()
        }
    }, [credentials, id])

    useEffect(() => {
        const fetchDoctors = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/doctors/specialty/${id}`, {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                })
                setDoctors(response.data)
                console.log(response.data)
            } catch (error) {
                console.error(error)
            }
        }

        if (credentials) {
            fetchDoctors()
        }
    }, [credentials, id])

    return (
        <div className="container mt-5">
            <h1>{specialty.name}</h1>
            <br></br>
            <br></br>
            <p>{specialty.description}</p>
            <br></br>
            <br></br>
            <h2>Meet our {specialty.name} team</h2>
            <div className="col-6 mx-auto">
                <ul className="list-group list-group-flush">
                    {doctors.map((doctor) => (
                        <li className='list-group-item' key={doctor.id}>
                            <h4>Dr. {doctor.firstName} {doctor.lastName}</h4>
                        </li>
                    ))}
                </ul>
                <br></br>
                <Link className="btn btn-outline-secondary mt-5" to={`/appointments/new?spId=${specialty.id}`}>Book an Apppointment</Link>
            </div>
        </div>
    )
}
