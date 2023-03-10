import React from 'react'
import { Link } from 'react-router-dom'

export default function 
() {
  return (
    <div>
        <h1>Admin Dashboard</h1>
        <Link to="/specialties" className='btn btn-primary'>Specialties</Link>
    </div>
  )
}
