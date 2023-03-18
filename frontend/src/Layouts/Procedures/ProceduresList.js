import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function ProceduresList(credentials) {

    const [procedures, setProcedures] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [newProcedureName, setNewProcedureName] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        const fetchProcedures = async () => {
            try {
                const response = await axios.get('http://localhost:8080/procedures', {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                });
                setProcedures(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (credentials) {
            fetchProcedures();
        }
    }, [credentials]);

    const handleDeleteProcedure = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/procedures/${id}`, {
                headers: { Authorization: `Bearer ${credentials.token}` },
            });
            setProcedures(procedures.filter((procedure) => procedure.id !== id));
        } catch (error) {
            console.error(error);
        }
    };

    const handleAddProcedure = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post(
                'http://localhost:8080/procedures',
                { name: newProcedureName},
                { headers: { Authorization: `Bearer ${credentials.token}` } }
            );

            setProcedures([...procedures, response.data]);
            setShowForm(false);
            setNewProcedureName('');
            toast.success('Procedure created successfully!', {
                position: "top-center",
                autoClose: 1500,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
        } catch (error) {
            console.error(error);
            toast.error('Error creating procedure. Please try again.', {
                position: "top-center",
                autoClose: 1500,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
        }
    };

    const handleEditProcedure = (id) => {
        navigate(`/procedures/${id}/edit`);
    };


    return (
        <div>
            <h1 className='mt-5'>Procedures</h1>
            <button className='btn btn-outline-secondary mt-5' onClick={() => setShowForm(!showForm)}>
                {showForm ? 'Cancel' : 'Add New Procedure'}
            </button>
            {showForm && (
                <form onSubmit={handleAddProcedure} className="form mx-auto col-4 mt-3">
                    <div className="form-group">
                        <label htmlFor="name">Name:</label>
                        <input
                            type="text"
                            required
                            className="form-control"
                            id="name"
                            value={newProcedureName}
                            onChange={(event) => setNewProcedureName(event.target.value)}
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
                    {procedures.map((procedure) => (
                        <tr key={procedure.id}>
                            <td>{procedure.id}</td>
                            <td>{procedure.name}</td>
                            <td>
                            <button
                                    className="btn btn-primary mx-1"
                                    onClick={() => handleEditProcedure(procedure.id)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="btn btn-danger mx-1"
                                    onClick={() => handleDeleteProcedure(procedure.id)}
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