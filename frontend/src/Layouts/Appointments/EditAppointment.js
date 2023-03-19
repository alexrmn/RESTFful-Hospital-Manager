import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';

export default function EditAppointment(credentials) {

    const { id } = useParams();
    const navigate = useNavigate();

    const [appointmentData, setAppointmentData] = useState({
        specialtyId: '',
        doctorId: '',
        timeslotId: '',
        date: '',
      });
      const [specialties, setSpecialties] = useState([]);
      const [specialtyId, setSpecialtyId] = useState(appointmentData.specialtyId);
      const [doctors, setDoctors] = useState([]);
      const [doctorId, setDoctorId] = useState('');
      const [timeslots, setTimeslots] = useState([]);
      const [timeslotId, setTimeslotId] = useState('');
      const [date, setDate] = useState('');


      useEffect(() => {
        const fetchAppointmentData = async () => {
          try {
            const response = await axios.get(`http://localhost:8080/appointments/${id}`, {
              headers: { Authorization: `Bearer ${credentials.token}` },
            });
            setAppointmentData(response.data);
          } catch (error) {
            console.error(error);
          }
        };

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
            fetchAppointmentData();
            fetchSpecialties();
          }
        }, [credentials, id]);
      
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
        }, [specialtyId, credentials]);
      
        useEffect(() => {
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
        }, [date, doctorId]);

        const handleSubmit = async (e) => {
            e.preventDefault();
        
            try {
              await axios.put(`http://localhost:8080/appointments/${id}`, {
                specialtyId: specialtyId,
                doctorId: doctorId,
                patientId: credentials.userId,
                timeslotId: timeslotId,
                date: date
            },
                {headers: { Authorization: `Bearer ${credentials.token}` } },
              );
              toast.success('Appointment updated successfully!', {
                position: "top-center",
                autoClose: 1500,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
              });
              navigate("/")
        
            } catch (error) {
              console.error(error);
              toast.error('Error updating appointment. Please try again.', {
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
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
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
                <button type="submit" className="btn btn-primary">
                  Save
                </button>
              </form>
             
            </div>
          );
}
