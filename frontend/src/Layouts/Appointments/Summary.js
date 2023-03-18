import axios from 'axios';
import { useState } from 'react';

export default function Summary({ appointmentId, credentials, initialSummary }) {
    const [summary, setSummary] = useState(initialSummary);
  
    const handleSaveSummary = async () => {
  
    try {
      const response = await axios.post(
        `http://localhost:8080/appointments/${appointmentId}/summary`,
        { summary },
        { headers: { Authorization: `Bearer ${credentials.token}` } }
      );
      console.log('Summary saved successfully');
    } catch (error) {
      console.error(error);
    }
  };

  const handleSummaryChange = (e) => {
    setSummary(e.target.value);
  };


  return (
    <>
       <textarea
        className="form-control col-md-8"
        placeholder="Appointment summary"
        id="summary"
        style={{ height: "200px", overflowY: "scroll" }}
        value={summary}
        onChange={handleSummaryChange}
      ></textarea>
      <button onClick={handleSaveSummary} className="btn btn-outline-secondary mt-3">Save Summary</button>
    </>
  );
}
