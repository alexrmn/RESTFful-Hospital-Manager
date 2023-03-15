import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function EditDoctorForm(credentials) {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [specialties, setSpecialties] = useState([]);
    const [selectedSpecialty, setSelectedSpecialty] = useState('');
    const navigate = useNavigate();
    const { id } = useParams();

    const getDoctor = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/doctors/${id}`, {
                headers: {
                    Authorization: `Bearer ${credentials.token}`
                }
            });
            const doctor = response.data;
            setFirstName(doctor.firstName);
            setLastName(doctor.lastName);
            setEmail(doctor.email);
            setSelectedSpecialty(doctor.specialty);
            
        } catch (error) {
            console.error(error);
        }
    };

    const getSpecialties = async () => {
        try {
            const response = await axios.get('http://localhost:8080/specialties', {
                headers: {
                    Authorization: `Bearer ${credentials.token}`
                }
            });
            setSpecialties(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {
        getDoctor();
        getSpecialties();
    }, []);

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.put(
                `http://localhost:8080/doctors/${id}`,
                {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    specialtyName: selectedSpecialty
                },
                { headers: { Authorization: `Bearer ${credentials.token}` } }
            );
            toast.success('Doctor account edited successfully!', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
            navigate('/doctors');
        } catch (error) {
            console.error(error);
            toast.error("Error editing doctor acount. Please try again.");
        }
    };

    return (
        <div>
            <h1>Edit Doctor Account</h1>
                <form onSubmit={handleSubmit} className="form mx-auto col-4 mt-3">
                    <div className="mb-3">
                        <label htmlFor="firstName" className="form-label">
                            First Name
                        </label>
                        <input
                            type="text"
                            className="form-control"
                            id="firstName"
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="lastName" className="form-label">
                            Last Name
                        </label>
                        <input
                            type="text"
                            className="form-control"
                            id="lastName"
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="email" className="form-label">
                            Email
                        </label>
                        <input
                            type="email"
                            className="form-control"
                            id="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <div className="mb-3">
                        <label htmlFor="specialty" className="form-label">
                            Specialty
                        </label>
                        <select
                            className="form-control"
                            id="specialty"
                            value={selectedSpecialty}
                            onChange={(e) => setSelectedSpecialty(e.target.value)}
                        >
                            <option value="">-- Select Specialty --</option>
                            {specialties.map((specialty) => (
                                <option key={specialty.id} value={specialty.name}>
                                    {specialty.name}
                                </option>
                            ))}
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary">
                        Update Account
                    </button>
                </form>
        </div>
    );
}
