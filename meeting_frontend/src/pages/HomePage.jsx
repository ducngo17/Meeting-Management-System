import React, { useEffect, useState } from "react";
import MeetingList from "../components/MeetingList";
import { getMeetings } from "../apis/meetingService";

const HomePage = () => {
  const [meetings, setmeetings] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getMeetings().then((data) => {
      setmeetings(data);
      setLoading(false);
    });
  }, []);

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <i className="fas fa-spinner fa-spin text-4xl text-blue-600 mb-4"></i>
          <p className="text-gray-600">Loading meetings...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="container mx-auto px-4 py-8">
        <div className="text-center mb-12">
          <h1 className="text-5xl font-bold text-gray-800 mb-4">
            <i className="fas fa-calendar-alt text-blue-600 mr-4"></i>
            Meeting Management System
          </h1>
          <p className="text-xl text-gray-600">
            Discover and join amazing meeting 
          </p>
        </div>
        <MeetingList meetings={meetings} />
      </div>
    </div>
  );
};

export default HomePage;