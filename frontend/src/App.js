import './App.css';
import SignInForm from './Layouts/Auth/SignInForm';
import React, { useState, useEffect } from 'react';
import Navbar from './Layouts/Components/Navbar';
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
import ViewSpecialty from './Specialties/ViewSpecialty';
import CreateAppointment from './Layouts/Appointments/CreateAppointment';

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import UpcomingAppointments from './Layouts/Appointments/UpcomingAppointments';
import AppointmentsHistory from './Layouts/Appointments/AppointmentsHistory';
import EditAppointment from './Layouts/Appointments/EditAppointment';
import ViewAppointmentsByDoctor from './Layouts/Appointments/ViewAppointmentsByDoctor';
import SearchForPatient from './Layouts/Patient/SearchForPatient';
import PatientHistory from './Layouts/Patient/PatientHistory';
import DiagnosesList from './Layouts/Diagnoses/DiagnosesList';
import EditDiagnosis from './Layouts/Diagnoses/EditDiagnosis';
import ProceduresList from './Layouts/Procedures/ProceduresList';
import EditProcedure from './Layouts/Procedures/EditProcedure';
import DoctorAppointmentDetails from './Layouts/Appointments/DoctorAppointmentDetails';
import AppointmentDetails from './Layouts/Appointments/AppointmentDetails';



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
        <Navbar credentials={credentials} setCredentials={setCredentials} />
        <Routes>
          {/* Render different components based on user role */}
          {isAdmin() && (
            <>
              <Route path="/" element={<AdminDashboard />} />
              <Route path="/specialties" element={<SpecialtiesList {...credentials} />} />
              <Route path="/specialties/:id/edit" element={<EditSpecialty {...credentials} />} />
              <Route path="/doctors" element={<DoctorList {...credentials} />} />
              <Route path="/doctors/new" element={<CreateNewDoctorForm {...credentials} />} />
              <Route path="/doctors/:id/edit" element={<EditDoctorForm {...credentials} />} />
              <Route path="/diagnoses" element={<DiagnosesList {...credentials} />} />
              <Route path="/diagnoses/:id/edit" element={<EditDiagnosis {...credentials} />} />
              <Route path="/procedures" element={<ProceduresList {...credentials} />} />
              <Route path="/procedures/:id/edit" element={<EditProcedure {...credentials} />} />
            </>
          )}
          {isDoctor() && (
            <>
              <Route path="/" element={<DoctorDashboard {...credentials}/>} />
              <Route path="/appointments" element={<ViewAppointmentsByDoctor {...credentials} />} />
              <Route path="/appointments/:id" element={<DoctorAppointmentDetails {...credentials} />} />
              <Route path="/appointments/:id/details" element={<AppointmentDetails {...credentials} />} />
              <Route path="/patients/search" element={<SearchForPatient {...credentials} />} />
              <Route path="/patients/:id/history" element={<PatientHistory {...credentials} />} />

            </>
            )}
          {isPatient() && (
            <>
              <Route path="/" element={<PatientDashboard {...credentials} />} />
              <Route path="/specialties/:id" element={<ViewSpecialty {...credentials} />} />
              <Route path="/appointments/new" element={<CreateAppointment {...credentials} />} />
              <Route path="/appointments/upcoming" element={<UpcomingAppointments {...credentials} />} />
              <Route path="/appointments/history" element={<AppointmentsHistory {...credentials} />} />
              <Route path="/appointments/:id/edit" element={<EditAppointment {...credentials} />} />
              <Route path="/appointments/:id/details" element={<AppointmentDetails {...credentials} />} />

            </>
          )}
          {!credentials && <Route path="/" element={<SignInForm setCredentials={setCredentials} />} />}
          <Route path="/register" element={<RegistrationForm />} />
        </Routes>

      </div>
      <ToastContainer position='toast.POSITION.TOP_CENTER' />
    </>

  );
}

export default App;