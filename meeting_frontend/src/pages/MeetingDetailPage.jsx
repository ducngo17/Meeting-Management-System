import React, { useEffect, useState, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";
import PollChart from "../components/PollChart";
import { getMeetingById } from "../apis/meetingService";
import { findAllPollsOfAMeeting, findAllPollOptionsOfAPoll, voteForPollOption, isVoted } from "../apis/pollService";
import { createSpeakingRequest, getAllSpeakingRequests, isRequested } from "../apis/speakingRequestService";
import SockJS from "sockjs-client/dist/sockjs";
import { Client } from "@stomp/stompjs";
import { API_BASE_URL } from "../config";
import { toast, ToastContainer } from "react-toastify";

const MeetingDetailPage = () => {
  const { id } = useParams();
  const attendeeId = localStorage.getItem("attendeeId");
  const navigate = useNavigate();
  const [meeting, setMeeting] = useState(null);
  const [speakingRequests, setSpeakingRequests] = useState([]);
  const [polls, setPolls] = useState([]);
  const [loading, setLoading] = useState(true);
  const [isRequesting, setIsRequesting] = useState(false);
  const [votedPolls, setVotedPolls] = useState({}); // { [pollId]: true/false }

  // Check if attendee has already requested
  useEffect(() => {
    if (attendeeId && id) {
      isRequested(attendeeId, id)
        .then((result) => setIsRequesting(result === true))
        .catch(() => setIsRequesting(false));
    }
  }, [attendeeId, id]);

  // Check voted state for each poll
  useEffect(() => {
    if (attendeeId && polls.length > 0) {
      const checkVotes = async () => {
        const results = {};
        for (const poll of polls) {
          try {
            const voted = await isVoted(attendeeId, poll.pollId);
            results[poll.pollId] = voted === true;
          } catch {
            results[poll.pollId] = false;
          }
        }
        setVotedPolls(results);
      };
      checkVotes();
    }
  }, [attendeeId, polls]);

  useEffect(() => {
    setLoading(true);
    Promise.all([
      getMeetingById(id),
      findAllPollsOfAMeeting(id),
      getAllSpeakingRequests(id) 
    ])
      .then(async ([meetingData, pollsData, speakingRequestsData]) => {
        setMeeting(meetingData);
        setSpeakingRequests(speakingRequestsData);

        // Fetch poll options for each poll
        const pollsWithOptions = await Promise.all(
          pollsData.map(async (poll) => {
            const options = await findAllPollOptionsOfAPoll(poll.pollId);
            return { ...poll, options };
          })
        );
        setPolls(pollsWithOptions);
      })
      .catch((err) => {
        console.error("Error fetching data:", err);
      })
      .finally(() => setLoading(false));
  }, [id]);

  useEffect(() => {
    // Tạo SockJS connection
    const socket = new SockJS(`${API_BASE_URL}/ws`);
    const stompClient = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 5000,
      onConnect: () => {
        // Subscribe topic
        stompClient.subscribe(`/topic/meeting/${id}`, async (message) => {
          const [messageType, meetingId] = message.body.split("/");
          if (meetingId !== id) return;

          if (messageType === "request-speaking") {
            const speakingRequestsData = await getAllSpeakingRequests(id);
            setSpeakingRequests(speakingRequestsData);
            // Re-check request state after update
            isRequested(attendeeId, id)
              .then((result) => setIsRequesting(result === true))
              .catch(() => setIsRequesting(false));
          } else if (messageType === "poll-vote") {
            const pollsData = await findAllPollsOfAMeeting(id);
            const pollsWithOptions = await Promise.all(
              pollsData.map(async (poll) => {
                const options = await findAllPollOptionsOfAPoll(poll.pollId);
                return { ...poll, options };
              })
            );
            setPolls(pollsWithOptions);
          }
        });
      },
      onStompError: (frame) => {
        console.error("Broker reported error: " + frame.headers["message"]);
        console.error("Additional details: " + frame.body);
      },
    });

    stompClient.activate();

    return () => {
      stompClient.deactivate();
    };
  }, [id, attendeeId]);

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <i className="fas fa-spinner fa-spin text-4xl text-blue-600 mb-4"></i>
          <p className="text-gray-600">Loading meeting details...</p>
        </div>
      </div>
    );
  }

  const handleRequestSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await createSpeakingRequest(attendeeId, id);
      toast.success(res); // <-- show success message
      setIsRequesting(true); // Disable button after request
    } catch (err) {
      const message = err.response?.data?.message || "Failed to send request.";
      toast.error(message); // <-- show error message
    }
  };

  const handleVote = async (attendeeId, optionId) => {
    try {
      const res = await voteForPollOption(attendeeId, optionId);
      toast.success(res);
    } catch (err) {
      const message = err.response?.data?.message || "Error voting for poll option.";
      toast.error(message); // <-- show error message
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <div className="container mx-auto px-4 py-8">
        {/* Header */}
        <div className="mb-8">
          <button
            onClick={() => navigate("/")}
            className="text-blue-600 hover:text-blue-800 mb-4 transition-colors"
          >
            <i className="fas fa-arrow-left mr-2"></i>
            Back to meetings
          </button>
          <h1 className="text-4xl font-bold text-gray-800">
            {meeting.meetingName}
          </h1>
        </div>

        {/* Top Part - meeting Details and Speaking Requests */}
        <div className="grid grid-cols-1 lg:grid-cols-10 gap-6 mb-8">
          {/* meeting Details - 70% */}
          <div className="lg:col-span-7">
            <div className="bg-white rounded-lg shadow-lg p-6">
              <h2 className="text-2xl font-semibold mb-4 text-gray-800">
                <i className="fas fa-info-circle text-blue-600 mr-2"></i>
                Meeting Details
              </h2>
              <div className="space-y-4">
                <p className="text-gray-600 leading-relaxed">
                  {meeting.description}
                </p>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                  <div>
                    <h3 className="font-semibold text-gray-800 mb-2">
                      <i className="fas fa-calendar text-blue-500 mr-2"></i>
                      Event Information
                    </h3>
                    <ul className="space-y-1 text-gray-600">
                      <li>
                        <strong>Date:</strong> {meeting.startTime.split("T")[0]}
                      </li>
                      <li>
                        <strong>Time:</strong> {meeting.startTime.split("T")[1].slice(0, 5)}
                      </li>
                      <li>
                        <strong>Location:</strong> {meeting.location}
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>

          {/* Speaking Requests - 30% */}
          <div className="lg:col-span-3">
            <div className="bg-white rounded-lg shadow-lg p-6">
              <div className="flex justify-between items-center mb-4">
                <h2 className="text-xl font-semibold mb-4 text-gray-800">
                  <i className="fas fa-microphone text-purple-600 mr-2"></i>
                  Speaking Requests
                </h2>
                <form onSubmit={handleRequestSubmit}>
                  <button
                    className="ml-2 px-4 py-2 bg-indigo-600 text-white rounded hover:bg-indigo-700 transition-colors text-sm font-medium disabled:opacity-50 disabled:cursor-not-allowed"
                    type="submit"
                    disabled={isRequesting}
                    title={isRequesting ? "You have already requested" : ""}
                  >
                    <i className="fas fa-plus mr-1"></i>Request
                  </button>
                </form>
              </div>
              <div className="space-y-3">
                {speakingRequests.map((request) => (
                  <div
                    key={request.id}
                    className="border border-gray-200 rounded-lg p-3"
                  >
                    <div className="flex justify-between items-start mb-2">
                      <h4 className="font-medium text-gray-800">
                        {request.attendeeName}
                      </h4>
                      {/* <span
                        className={`px-2 py-1 rounded-full text-xs font-medium ${getStatusColor(
                          request.status
                        )}`}
                      >
                        {request.status}
                      </span> */}
                    </div>
                    {/* <p className="text-sm text-gray-600 mb-2">
                      {request.topic}
                    </p> */}
                    <p className="text-xs text-gray-500">
                      <i className="fas fa-clock mr-1"></i>
                      {request.requestTime.split("T")[1].slice(0, 5)}
                    </p>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </div>

        {/* Bottom Part - Polls */}
        <div className="bg-white rounded-lg shadow-lg p-6">
          <h2 className="text-2xl font-semibold mb-6 text-gray-800">
            <i className="fas fa-poll text-indigo-600 mr-2"></i>
            Live Polls
          </h2>
          <div className="space-y-8">
            {polls.map((poll) => (
              <div
                key={poll.pollId}
                className="border border-gray-200 rounded-lg p-6"
              >
                <h3 className="text-lg font-semibold mb-4 text-gray-800">
                  {poll.title}
                </h3>
                <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
                  {/* Chart */}
                  <div className="lg:col-span-1">
                    <div className="h-48">
                      <PollChart poll={poll} />
                    </div>
                  </div>

                  {/* Options and Results */}
                  {/* {console.log("Option:", poll.options)} */}
                  <div className="lg:col-span-2">
                    <div className="space-y-3">
                      {poll.options.map((option, index) => {
                        const percentage = poll.totalVotes > 0
                          ? ((option.numOfVotes / poll.totalVotes) * 100).toFixed(1)
                          : "0.0";
                        const colors = [
                          "bg-blue-500",
                          "bg-green-500",
                          "bg-yellow-500",
                          "bg-red-500",
                          "bg-purple-500",
                          "bg-orange-500",
                        ];
                        console.log(option.pollOptionId, option.text, option.numOfVotes, percentage);
                        return (
                          <div key={index} className="flex items-center space-x-6 mb-2">
                            <div className="flex-1">
                              <div className="flex items-center space-x-3 mb-1">
                                <div className={`w-4 h-4 rounded ${colors[index % colors.length]}`}></div>
                                <span className="text-gray-700 font-medium">{option.text}</span>
                                <div className="flex-1"></div>
                                <span className="text-lg font-bold text-gray-800">{option.numOfVotes}</span>
                                <span className="text-sm text-gray-500">({percentage}%)</span>
                              </div>
                              <div className="w-full bg-gray-200 rounded-full h-2">
                                <div
                                  className={`h-2 rounded-full ${colors[index % colors.length]}`}
                                  style={{ width: `${percentage}%` }}
                                ></div>
                              </div>
                            </div>
                            <button
                              className="ml-6 px-4 py-1 bg-indigo-600 text-white rounded hover:bg-indigo-700 transition-colors text-sm font-medium disabled:opacity-50 disabled:cursor-not-allowed"
                              onClick={() => handleVote(attendeeId, option.pollOptionId)}
                              disabled={!!votedPolls[poll.pollId]}
                              title={votedPolls[poll.pollId] ? "You have already voted" : ""}
                            >
                              Vote
                            </button>
                          </div>
                        );
                      })}
                    </div>
                    <div className="mt-4 pt-4 border-t border-gray-200">
                      <p className="text-sm text-gray-600">
                        <i className="fas fa-users mr-1"></i>
                        Total votes: <strong>{poll.totalVotes}</strong>
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
      <ToastContainer/>
    </div>
  );
};

export default MeetingDetailPage;