import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function (credentials) {
    const [specialties, setSpecialties] = useState([]);

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
        <div>
            {specialties.map((specialty) => (
                <div key={specialty.id}>
                    <img src={specialty.imageLink} alt={specialty.name} />
                    <p>{specialty.name}</p>
                </div>
            ))}
        </div>
    );

}
