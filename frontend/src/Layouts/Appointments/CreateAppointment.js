import axios from 'axios';
import React, { useEffect, useState } from 'react';


export default function (credentials) {

    const [specialties, setSpecialties] = useState([]);
    const [specialtyId, setSpecialtyId] = useState('');
    const [doctors, setDoctors] = useState([]);
    const [doctorId, setDoctorId] = useState('');
    const [timeslots, setTimeslots] = useState([]);
    const [timeslotId, setTimeslotId] = useState('');
    const [date, setDate] = useState('');
    const [initializedDate, setInitializedDate] = useState(false);



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

    useEffect(() => {
        const fetchDoctors = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/doctors/specialty/${specialtyId}`, {
                    headers: { Authorization: `Bearer ${credentials.token}` },
                });
                setDoctors(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (specialtyId) {
            fetchDoctors();
        }
    }, [specialtyId]);

    useEffect(() => {
        if (!initializedDate) {
            setInitializedDate(true);
            return;
        }

        const fetchTimeslots = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/timeslots/${date}/${doctorId}`, {
                    headers: { Authorization: `Bearer ${credentials.token}` }
                });
                setTimeslots(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        fetchTimeslots();
    }, [date, doctorId, credentials, initializedDate]);


    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/appointments', {
                specialtyId: specialtyId,
                doctorId: doctorId,
                patientId: credentials.userId,
                timeslotId: timeslotId,
                date: date
            },
                { headers: { Authorization: `Bearer ${credentials.token}` } }
            );
            console.log(response);
            

        } catch (error) {
            console.error(error);
        }
    };


    return (
        <div className='col-3 mx-auto mt-5'>

            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="specialtyId" className="form-label">
                        Specialty
                    </label>
                    <select
                        className="form-select"
                        id="specialtyId"
                        required
                        value={specialtyId}
                        onChange={(e) => setSpecialtyId(e.target.value)}
                    >
                        <option value="">Select a specialty</option>
                        {specialties.map((specialty) => (
                            <option key={specialty.id} value={specialty.id}>
                                {specialty.name}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="doctorId" className="form-label">
                        Doctor
                    </label>
                    <select
                        className="form-select"
                        id="doctorId"
                        required
                        value={doctorId}
                        onChange={(e) => setDoctorId(e.target.value)}
                    >
                        <option value="">Select a doctor</option>
                        {doctors.map((doctor) => (
                            <option key={doctor.id} value={doctor.id}>
                                {doctor.firstName} {doctor.lastName}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="date" className="form-label">
                        Date
                    </label>
                    <input
                        className="form-control"
                        type="date"
                        onChange={e => setDate(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="timeslotId" className="form-label">
                        Time
                    </label>
                    <select
                        className="form-select"
                        id="timeslotId"
                        required
                        value={timeslotId}
                        onChange={(e) => setTimeslotId(e.target.value)}
                    >
                        <option value="">Select a time slot</option>
                        {timeslots.map((timeSlot) => (
                            <option key={timeSlot.id} value={timeSlot.id}>
                                {timeSlot.name}
                            </option>
                        ))}
                    </select>
                </div>
                <button
                    type="submit"
                    className="btn btn-outline-secondary mt-3"
                    
                >Create Appointment
                </button>
            </form>
            
        </div>
    )
}
