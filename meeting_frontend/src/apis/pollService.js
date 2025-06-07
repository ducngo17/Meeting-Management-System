import axios from "axios";

import { API_BASE_URL } from "../config.js";

export async function findAllPollsOfAMeeting(meetingId) {
  try {
    const response = await axios.get(`${API_BASE_URL}/polls/meetings/${meetingId}`);
    return response.data;
  } catch (error) {
    console.error(error.response?.data.message);
    throw error;
  }
}

export async function findAllPollOptionsOfAPoll(pollId) {
  try {
    const response = await axios.get(`${API_BASE_URL}/polls/${pollId}/poll-options`);
    return response.data;
  } catch (error) {
    console.error(error.response?.data.message);
    throw error;
  }
}

export async function voteForPollOption(attendeeId, pollOptionId) {
  try {
    const response = await axios.post(
      `${API_BASE_URL}/polls/vote`,
      { 
        attendeeId, 
        pollOptionId
      },
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    return response.data;
  } catch (error) {
    console.error(error.response?.data.message);
    throw error;
  }
}

export async function isVoted(attendeeId, pollId) {
  try {
    const response = await axios.get(`${API_BASE_URL}/polls/is-voted`, {
      params: {
        attendeeId: attendeeId,
        pollId: pollId,
      },
    });
    return response.data;
  } catch (error) {
    console.error(error.response?.data.message);
    throw error;
  }
}