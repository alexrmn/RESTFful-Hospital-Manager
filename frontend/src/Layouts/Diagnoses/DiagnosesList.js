import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function DiagnosesList(credentials) {

    const [diagnoses, setDiagnoses] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [newDiagnosisName, setNewDiagnosisName] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchDiagnoses = async () => {
            try {
                const response = await axios.get('http://localhost:8080/diagnoses', {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                });
                setDiagnoses(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (credentials) {
            fetchDiagnoses();
        }
    }, [credentials]);

    const handleDeleteDiagnosis = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/diagnoses/${id}`, {
                headers: { Authorization: `Bearer ${credentials.token}` },
            });
            setDiagnoses(diagnoses.filter((diagnosis) => diagnosis.id !== id));
        } catch (error) {
            console.error(error);
        }
    };

    const handleAddDiagnosis = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post(
                'http://localhost:8080/diagnoses',
                { name: newDiagnosisName},
                { headers: { Authorization: `Bearer ${credentials.token}` } }
            );

            setDiagnoses([...diagnoses, response.data]);
            setShowForm(false);
            setNewDiagnosisName('');
            toast.success('Diagnosis created successfully!', {
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
            toast.error('Error creating diagnosis. Please try again.');
        }
    };

    
    const handleEditDiagnosis = (id) => {
        navigate(`/diagnoses/${id}/edit`);
    };


  return (
    <div>
        <h1 className='mt-5'>Diagnoses</h1>
        <button className='btn btn-outline-secondary mt-5' onClick={() => setShowForm(!showForm)}>
                {showForm ? 'Cancel' : 'Add New Diagnosis'}
            </button>
            {showForm && (
                <form onSubmit={handleAddDiagnosis} className="form mx-auto col-4 mt-3">
                    <div className="form-group">
                        <label htmlFor="name">Name:</label>
                        <input
                            type="text"
                            className="form-control"
                            id="name"
                            value={newDiagnosisName}
                            onChange={(event) => setNewDiagnosisName(event.target.value)}
                        />
                    </div>
                    <button type="submit" className='btn btn-outline-secondary mt-5'>Add</button>
                </form>
            )}
        
        <table className="table mt-5">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col" className='col-2'>Name</th>
                    </tr>
                </thead>
                <tbody>
                    {diagnoses.map((diagnosis) => (
                        <tr key={diagnosis.id}>
                            <td>{diagnosis.id}</td>
                            <td>{diagnosis.name}</td>
                            <td>
                            <button
                                    className="btn btn-primary mx-1"
                                    onClick={() => handleEditDiagnosis(diagnosis.id)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="btn btn-danger mx-1"
                                    onClick={() => handleDeleteDiagnosis(diagnosis.id)}
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
