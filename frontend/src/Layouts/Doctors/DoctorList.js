import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function DoctorList(credentials) {
    const [doctors, setDoctors] = useState([]);
    const navigate = useNavigate();
    
    
    useEffect(() => {
        const fetchDoctors = async () => {
            try {
                const response = await axios.get('http://localhost:8080/doctors', {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                });
                setDoctors(response.data);
            } catch (error) {
                console.error(error);
            }
        };
        

        if (credentials) {
            fetchDoctors();
        }
    }, [credentials]);

    const handleEditDoctor = (id) => {
        navigate(`/doctors/${id}/edit`);
    };


    const handleDeleteDoctor= async (id) => {
        try {
            await axios.delete(`http://localhost:8080/doctors/${id}`, {
                headers: { Authorization: `Bearer ${credentials.token}` },
            });
            setDoctors(doctors.filter((doctor) => doctor.id !== id));
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div>
            <h1 className='mt-5'>Doctors</h1>
            <Link to="/doctors/new" className="btn btn-outline-secondary mt-5" >Add new doctor</Link>
            
            <table className="table mt-5">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Specialty</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    {doctors.map((doctor) => (
                        <tr key={doctor.id}>
                            <td>{doctor.id}</td>
                            <td>{doctor.firstName}</td>
                            <td>{doctor.lastName}</td>
                            <td>{doctor.email}</td>
                            <td>
                                {/* {doctor.specialty ? doctor.specialty.name : ''} */}
                                {doctor.specialtyName}
                            </td>
                            <td>
                                <button
                                    className="btn btn-primary mx-1"
                                    onClick={() => handleEditDoctor(doctor.id)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="btn btn-danger mx-1"
                                    onClick={() => handleDeleteDoctor(doctor.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}
