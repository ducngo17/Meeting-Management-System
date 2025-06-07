import React from "react";
import { Link } from "react-router-dom";

const MeetingList = ({ meetings }) => (
  <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
    {meetings.map((meeting) => (
      <Link
        key={meeting.meetingId}
        to={`/meetings/${meeting.meetingId}`}
        className="bg-white rounded-lg shadow-lg hover:shadow-xl transition-shadow duration-300 cursor-pointer transform hover:scale-105"
      >
        <div className="p-6">
          <h3 className="text-xl font-semibold text-gray-800 mb-3">
            {meeting.meetingName}
          </h3>
          <div className="space-y-2 text-gray-600">
            <div className="flex items-center">
              <i className="fas fa-calendar text-blue-500 mr-2"></i>
              <span>{meeting.startTime.split('T')[0]}</span>
            </div>
            <div className="flex items-center">
              <i className="fas fa-map-marker-alt text-red-500 mr-2"></i>
              <span>{meeting.location}</span>
            </div>
          </div>
          <div className="mt-4 pt-4 border-t border-gray-200">
            <span className="text-blue-600 font-medium hover:text-blue-800 transition-colors">
              View Details <i className="fas fa-arrow-right ml-1"></i>
            </span>
          </div>
        </div>
      </Link>
    ))}
  </div>
);

export default MeetingList;