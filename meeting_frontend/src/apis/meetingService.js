import axios from 'axios';

import { API_BASE_URL } from '../config.js';

export async function getMeetings() {
    try {
        const response = await axios.get(`${API_BASE_URL}/meetings`);
        return response.data;
    } catch (error) {
        console.error(error.response?.data.message);
        throw error;
    }
}

export async function getMeetingById(meetingId) {
    try {
        const response = await axios.get(`${API_BASE_URL}/meetings/${meetingId}`);
        return response.data;
    } catch (error) {
        console.error(error.response?.data.message);
        throw error;
    }
}
