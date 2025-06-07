import axios from 'axios';

import { API_BASE_URL } from '../config.js';

export async function loginAttendee(loginData) {
    try {
        const response = await axios.post(`${API_BASE_URL}/attendees/login`, loginData);
        return response.data;
    } catch (error) {
        console.error(error.response?.data.message);
        throw error;
    }
}