import React, { useState, useEffect } from 'react';
import axios from 'axios';

export default function Welcome( credentials ) {
  const [doctor, setDoctor] = useState('');

  useEffect(() => {
    const fetchDoctor = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/doctors/${credentials.userId}`, {
          headers: { Authorization: `Bearer ${credentials.token}` },
        });
        setDoctor(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchDoctor();
  }, [credentials]);

  return (
    <div>
      <br></br>
      <br></br>
      <div className="container h-100 d-flex justify-content-center align-items-center mt-5">
        <div className="jumbotron text-center">
          <h1 className="display-4">Welcome to Hospital Manager</h1>
          <h2 className="display-4">Doctor {`${doctor.firstName} ${doctor.lastName}`}</h2>
        </div>
      </div>
    </div>
  );
}
