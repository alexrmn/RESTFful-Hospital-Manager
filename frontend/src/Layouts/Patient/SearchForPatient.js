import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function SearchForPatient(credentials) {
    
    const [patients, setPatients] = useState([]);
    const [searchQuery, setSearchQuery] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchPatients = async () => {
            try {
                const response = await axios.get('http://localhost:8080/patients', {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                });
                setPatients(response.data);
            } catch (error) {
                console.error(error);
            }
        };
        fetchPatients();
    }, []);

    const filteredPatients = patients.filter(
        (patient) =>
            `${patient.firstName} ${patient.lastName}`
                .toLowerCase()
                .includes(searchQuery.toLowerCase())
    );

    const handleSearchQueryChange = (event) => {
        setSearchQuery(event.target.value);
    };

    const handleViewHistory = (id) => {
        navigate(`/patients/${id}/history`)
    };

    return (
        <div >
            <h1>Search for a patient</h1>
            <div className='col-6 mx-auto'>
                <div className="mb-3">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search by patient name"
                        value={searchQuery}
                        onChange={handleSearchQueryChange}
                    />
                </div>
                <table className="table">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        {filteredPatients.map((patient) => (
                            <tr key={patient.id}>
                                <td>
                                    {`${patient.firstName} ${patient.lastName}`}
                                </td>
                                <td>
                                    <button
                                        className="btn btn-secondary"
                                        onClick={() => handleViewHistory(patient.id)}
                                    >
                                        View History
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
