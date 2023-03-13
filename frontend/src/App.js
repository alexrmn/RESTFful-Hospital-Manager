import './App.css';
import SignInForm from './Layouts/Auth/SignInForm';
import React, { useState,useEffect } from 'react';
import Navbar from './Layouts/Navbar';
import { Route, Routes } from 'react-router-dom';
import RegistrationForm from './Layouts/Auth/RegistrationForm';
import AdminDashboard from './Layouts/AdminDashboard';
import DoctorDashboard from './Layouts/DoctorDashboard';
import PatientDashboard from './Layouts/PatientDashboard';
import SpecialtiesList from './Specialties/SpecialtiesList';
import EditSpecialty from './Specialties/EditSpecialty';
import DoctorList from './Layouts/Doctors/DoctorList';
import CreateNewDoctorForm from './Layouts/Doctors/CreateNewDoctorForm';
import EditDoctorForm from './Layouts/Doctors/EditDoctorForm';



function App() {
  const [credentials, setCredentials] = useState(null); // state to store credentials (JWT token and roles)

  //storing credentials in local storage
  useEffect(() => {
    const storedCredentials = localStorage.getItem('credentials');
    if (storedCredentials) {
      setCredentials(JSON.parse(storedCredentials));
    }
  }, []);

  useEffect(() => {
    if (credentials) {
      localStorage.setItem('credentials', JSON.stringify(credentials));
    } else {
      localStorage.removeItem('credentials');
    }
  }, [credentials]);

  // Define a function to check if the user is an admin
  const isAdmin = () => {
    return credentials && credentials.roles && credentials.roles.includes("ROLE_ADMIN");
  };

  // Define a function to check if the user is a doctor
  const isDoctor = () => {
    return credentials && credentials.roles && credentials.roles.includes("ROLE_DOCTOR");
  };

  // Define a function to check if the user is a patient
  const isPatient = () => {
    return credentials && credentials.roles && credentials.roles.includes("ROLE_PATIENT");
  };

  return (
    <>
      <div className="App">
        <Navbar credentials={credentials} setCredentials={setCredentials}/>
        <Routes>
          {/* Render different components based on user role */}
          {isAdmin() && (
            <>
              <Route path="/" element={<AdminDashboard/>} />
              <Route path="/specialties" element={<SpecialtiesList {...credentials} />} />
              <Route path="/specialties/:id/edit" element={<EditSpecialty {...credentials} />} />
              <Route path="/doctors" element={<DoctorList {...credentials} />} />
              <Route path="/doctors/new" element={<CreateNewDoctorForm {...credentials} />} />
              <Route path="/doctors/:id/edit" element={<EditDoctorForm {...credentials} />} />

            </>
          )}
          {isDoctor() && <Route path="/" element={<DoctorDashboard/>} />}
          {isPatient() && <Route path="/" element={<PatientDashboard />} />}
          {!credentials && <Route path="/" element={<SignInForm setCredentials={setCredentials} />} />}
          <Route path="/register" element={<RegistrationForm />} />
        </Routes>

      </div>
    </>
  );
}

export default App;