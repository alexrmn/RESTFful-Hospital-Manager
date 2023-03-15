import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function CreateNewDoctorForm(credentials) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [specialties, setSpecialties] = useState([]);
    const [selectedSpecialty, setSelectedSpecialty] = useState('');
    const navigate = useNavigate();

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
        getSpecialties();
    }, []);

    const handleSubmit = async(event) => {
        event.preventDefault();

        try {
            const response = await axios.post(
                'http://localhost:8080/doctors',
                {
                    firstName: firstName,
                    lastName: lastName,
                    username: username,
                    password: password,
                    email: email,
                    specialtyName: selectedSpecialty
                },
                { headers: { Authorization: `Bearer ${credentials.token}` } }
            );
            toast.success('Doctor account created successfully!', {
                position: "top-center",
                autoClose: 5000,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
            });
            navigate("/doctors")
        } catch (error) {
            console.error(error);
            toast.error("Error creating doctor acount. Please try again.");
        }
    };

    return (
        <div>
            <h1>Create New Doctor Account</h1>
            <form onSubmit={handleSubmit} className="form mx-auto col-4 mt-3">
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">Username</label>
                    <input type="text" className="form-control" id="username" value={username} onChange={(e) => setUsername(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input type="password" className="form-control" id="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label htmlFor="firstName" className="form-label">First Name</label>
                    <input type="text" className="form-control" id="firstName" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label htmlFor="lastName" className="form-label">Last Name</label>
                    <input type="text" className="form-control" id="lastName" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email</label>
                    <input type="email" className="form-control" id="email" value={email} onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div className="mb-3">
                    <label htmlFor="specialty" className="form-label">Specialty</label>
                    <select className="form-control" id="specialty" value={selectedSpecialty} onChange={(e) => setSelectedSpecialty(e.target.value)}>
                        <option value="">-- Select Specialty --</option>
                        {specialties.map((specialty) => (
                            <option key={specialty.id} value={specialty.name}>{specialty.name}</option>
                        ))}
                    </select>
                </div>
                <button type="submit" className="btn btn-primary">Create Account</button>
            </form>
        </div>
    );
}
