import React, { useEffect, useState } from "react";
import axios from "axios";
import ReactPlayer from "react-player";

export default function ViewCourse() {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedVideo, setSelectedVideo] = useState(null);
  const [selectedCourse, setSelectedCourse] = useState(null);

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.get("http://localhost:5000/api/courses");
        setCourses(response.data);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchCourses();
  }, []);

  const handleDeleteCourse = async (courseId) => {
    try {
      await axios.delete(`http://localhost:5000/api/courses/${courseId}`);
      setCourses((prevCourses) =>
        prevCourses.filter((course) => course.id !== courseId)
      );
    } catch (error) {
      setError(error);
    }
  };

  if (loading)
    return <div className="text-white text-center text-lg">Loading...</div>;
  if (error)
    return (
      <div className="text-red-400 text-center text-lg mt-6">
        Error fetching courses: {error.message}
      </div>
    );

  const handleVideoClick = (video) => {
    setSelectedVideo(video);
  };

  const handleViewLectures = (course) => {
    setSelectedCourse(course);
  };

  const handleCloseModal = () => {
    setSelectedVideo(null);
    setSelectedCourse(null);
  };

  return (
    <div className="bg-gray-900 min-h-screen p-6">
      <h1 className="text-4xl font-bold text-white mb-8 text-center">
        Courses
      </h1>
      <div className="grid gap-6 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3">
        {courses.map((course) => (
          <CourseCard
            key={course.id}
            course={course}
            onViewLectures={() => handleViewLectures(course)}
            onVideoClick={handleVideoClick}
            onDelete={handleDeleteCourse}
          />
        ))}
      </div>
      <VideoModal
        course={selectedCourse}
        video={selectedVideo}
        onClose={handleCloseModal}
        onVideoClick={handleVideoClick}
      />
    </div>
  );
}
function CourseCard({ course, onViewLectures, onVideoClick, onDelete }) {
  return (
    <div className="bg-gray-800 rounded-lg shadow-lg overflow-hidden border border-gray-700">
      <div className="p-4">
        <h2 className="text-xl font-semibold text-white mb-3">
          {course.title}
        </h2>
        <p className="text-gray-400 mb-4">{course.description}</p>
        <div className="flex space-x-2">
          <button
            onClick={onViewLectures}
            className="bg-blue-600 text-white py-2 px-4 rounded-md shadow-sm hover:bg-blue-500 transition duration-200"
          >
            View Lectures
          </button>
          <button
            onClick={() => onDelete(course.id)}
            className="bg-red-600 text-white py-2 px-4 rounded-md shadow-sm hover:bg-red-500 transition duration-200"
          >
            Delete Course
          </button>
        </div>
      </div>
    </div>
  );
}

function VideoModal({ course, video, onClose, onVideoClick }) {
  if (!course && !video) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-70 z-50">
      <div className="bg-gray-900 p-4 rounded-lg shadow-lg w-full max-w-2xl relative">
        <button
          onClick={onClose}
          className="absolute top-3 right-3 text-white bg-red-600 hover:bg-red-500 rounded-full p-1"
        >
          &times;
        </button>
        <h2 className="text-xl font-bold text-white mb-3">
          {video ? video.title : "Lectures"}
        </h2>
        {video ? (
          <ReactPlayer
            url={video.url}
            controls
            width="100%"
            height="auto"
            className="rounded-md"
          />
        ) : (
          <div>
            {course?.videos.length > 0 ? (
              course.videos.map((video) => (
                <div
                  key={video.id}
                  className="bg-gray-700 p-4 rounded-lg shadow-md mb-4 hover:bg-gray-600 transition duration-200"
                >
                  <h3 className="text-lg font-semibold text-white mb-2">
                    {video.title}
                  </h3>
                  <button
                    onClick={() => onVideoClick(video)}
                    className="bg-blue-600 text-white py-2 px-4 rounded-md shadow-sm hover:bg-blue-500 transition duration-200"
                  >
                    Play Video
                  </button>
                </div>
              ))
            ) : (
              <p className="text-gray-400 text-sm">No lectures available</p>
            )}
          </div>
        )}
      </div>
    </div>
  );
}
