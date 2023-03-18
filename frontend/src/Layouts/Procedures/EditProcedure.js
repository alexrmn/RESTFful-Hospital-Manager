import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function EditProcedure(credentials) {
    const [name, setName] = useState('');
    const { id } = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        axios
            .get(`http://localhost:8080/procedures/${id}`, {
                headers: {
                    Authorization: `Bearer ${credentials.token}`,
                },
            })
            .then((response) => {
                setName(response.data.name);
            })
            .catch((error) => {
                console.log(error);
            });
    }, [id, credentials]);

    const handleNameChange = (event) => {
        setName(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        axios
          .put(
            `http://localhost:8080/procedures/${id}`,
            { name, id },
            {
              headers: {
                Authorization: `Bearer ${credentials.token}`,
              },
            }
          )
          .then((response) => {
            console.log(response);
            navigate("/procedures")
            toast.success('Procedure updated successfully!', {
              position: "top-center",
              autoClose: 1500,
              hideProgressBar: true,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
              theme: "light",
          });
          })
          .catch((error) => {
            console.log(error);
            toast.error("Error updating procedure. Please try again.", {
                position: "top-center",
                autoClose: 1500,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            })
          });
      };

    return (
        <div className="container col-4 mt-5">
            <h1>Edit Procedure</h1>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">
                        Name:
                    </label>
                    <input
                        type="text"
                        required
                        className="form-control"
                        id="name"
                        value={name}
                        onChange={handleNameChange}
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    Save
                </button>
            </form>
        </div>
    );
}
