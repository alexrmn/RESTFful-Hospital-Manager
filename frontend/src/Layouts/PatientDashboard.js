import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function PatientDashboard(credentials) {
  const [specialties, setSpecialties] = useState([]);
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

  return (
    <div className="container">
      <h1>Welcome</h1>
      <p>
        Welcome to our private clinic, where we are dedicated to providing exceptional healthcare services to our patients.
        Our team of highly experienced doctors and medical professionals are committed to ensuring that you receive
        the highest standard of care possible. We understand that healthcare is a critical aspect of life, and we
        are committed to providing you with the best possible experience and outcomes.
      </p>
      <p>
        We believe that each patient is unique and deserves personalized attention and care.
        We take the time to understand your individual healthcare needs and concerns to provide you
        with tailored treatment options that are specific to your situation.
        Whether you are seeking medical advice or undergoing a medical procedure, our team of healthcare professionals will
        be with you every step of the way to ensure that you receive the best care possible. We are committed to providing you
        with a warm and welcoming environment, where you can feel comfortable and confident in your healthcare decisions.
      </p>
      <h2>Our specialties:</h2>
      <div className="row">
        {specialties.map((specialty) => (
          <div className="col-md-4 mb-3" key={specialty.id}>
            <img
              src={specialty.imageLink} alt={specialty.name} className="img-fluid w-30"
              onClick={() => { navigate(`/specialties/${specialty.id}`) }}
              onMouseOver={(e) => e.currentTarget.style.transform = 'scale(1.1)'}
              onMouseOut={(e) => e.currentTarget.style.transform = 'scale(1)'}
            />
            <p>{specialty.name}</p>
          </div>
        ))}
      </div>
    </div>
  );
}
