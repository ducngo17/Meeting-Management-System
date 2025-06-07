import axios from "axios";

import { API_BASE_URL } from "../config";

export const getAllSpeakingRequests = async (meetingId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/speaking-requests/${meetingId}`);
        return response.data;
    } catch (error) {
        console.error(error.response?.data.message);
        throw error;
    }
}

export const createSpeakingRequest = async (attendeeId, meetingId) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/speaking-requests`, 
            {
                "attendeeId": attendeeId,
                "meetingId": meetingId,
            },
            {
                headers: {
                    'Content-Type': 'application/json'
                }
            }
        );
        return response.data;
    } catch (error) {
        console.error(error.response?.data.message);
        throw error;
    }
}

export const isRequested = async (attendeeId, meetingId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/speaking-requests/is-requested`, {
            params: {
                attendeeId: attendeeId,
                meetingId: meetingId
            }
        });
        return response.data;
    } catch (error) {
        console.error(error.response?.data.message);
        throw error;
    }
}