import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

export default function SpecialtiesList(credentials) {

    const [specialties, setSpecialties] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [newSpecialtyName, setNewSpecialtyName] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchSpecialties = async () => {
            try {
                const response = await axios.get('http://localhost:8080/specialties', {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                });
                setSpecialties(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (credentials) {
            fetchSpecialties();
        }
    }, [credentials]);

    const handleAddSpecialty = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post(
                'http://localhost:8080/specialties',
                { name: newSpecialtyName },
                { headers: { Authorization: `Bearer ${credentials.token}` } }
            );

            setSpecialties([...specialties, response.data]);
            setShowForm(false);
            setNewSpecialtyName('');
        } catch (error) {
            console.error(error);
        }
    };

    

    const handleEditSpecialty = (id) => {
        navigate(`/specialties/${id}/edit`);
      };

    const handleDeleteSpecialty = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/specialties/${id}`, {
                headers: { Authorization: `Bearer ${credentials.token}` },
            });
            setSpecialties(specialties.filter((specialty) => specialty.id !== id));
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div>
            <h1 className='mt-5'>Specialties</h1>
            <button className='btn btn-outline-secondary mt-5' onClick={() => setShowForm(!showForm)}>
                {showForm ? 'Cancel' : 'Add New Specialty'}
            </button>
            {showForm && (
                <form onSubmit={handleAddSpecialty} className="form mx-auto col-4 mt-3">
                    <div className="form-group">
                        <label htmlFor="name">Name:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            value={newSpecialtyName}
                            onChange={(event) => setNewSpecialtyName(event.target.value)}
                        />
                    </div>
                    <button type="submit" className='btn btn-outline-secondary mt-5'>Add</button>
                </form>
            )}
            <table className="table mt-5">
                <thead>
                    <tr>
                        <th scope="col-2">Id</th>
                        <th scope="col-8">Name</th>
                        <th scope="col-2"></th>
                    </tr>
                </thead>
                <tbody>
                    {specialties.map((specialty) => (
                        <tr key={specialty.id}>
                            <td>{specialty.id}</td>
                            <td>{specialty.name}</td>
                            <td>
                                <button
                                    className="btn btn-primary mx-1"
                                    onClick={() => handleEditSpecialty(specialty.id)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="btn btn-danger mx-1"
                                    onClick={() => handleDeleteSpecialty(specialty.id)}
                                >
                                    Delete
                                </button>
                            </td>

                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}
