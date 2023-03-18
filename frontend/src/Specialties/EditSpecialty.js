import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function EditSpecialty(credentials) {
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [imageLink, setImageLink] = useState('');
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`http://localhost:8080/specialties/${id}`, {
        headers: {
          Authorization: `Bearer ${credentials.token}`,
        },
      })
      .then((response) => {
        setName(response.data.name);
        setDescription(response.data.description);
        setImageLink(response.data.imageLink)
      })
      .catch((error) => {
        console.log(error);
      });
  }, [id, credentials]);

  const handleNameChange = (event) => {
    setName(event.target.value);
  };

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  };

  const handleImageLinkChange = (event) => {
    setImageLink(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    axios
      .put(
        `http://localhost:8080/specialties/${id}`,
        { name, description, id, imageLink },
        {
          headers: {
            Authorization: `Bearer ${credentials.token}`,
          },
        }
      )
      .then((response) => {
        console.log(response);
        navigate("/specialties")
        toast.success('Specialty edited successfully!', {
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
        toast.error("Error editing specialty. Please try again.", {
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
      <h1>Edit Specialty</h1>
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
        <div className="mb-3">
          <label htmlFor="description" className="form-label">
            Description:
          </label>
          <textarea
            type="text"
            required
            className="form-control"
            id="description"
            rows="5"
            value={description}
            onChange={handleDescriptionChange}
          />
        </div><div className="mb-3">
          <label htmlFor="imageLink" className="form-label">
            Image Link:
          </label>
          <input
            type="text"
            className="form-control"
            id="imageLink"
            value={imageLink}
            onChange={handleImageLinkChange}
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Save
        </button>
      </form>
    </div>
  );
}
