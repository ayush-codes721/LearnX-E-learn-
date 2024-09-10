import React from "react";
import ReactPlayer from "react-player";

export default function VideoModal({ video, onClose }) {
  if (!video) return null;

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-75 z-50">
      <div className="bg-gray-900 p-4 rounded-lg shadow-lg w-full max-w-3xl relative">
        <button
          onClick={onClose}
          className="absolute top-4 right-4 text-white bg-red-600 hover:bg-red-500 rounded-full p-2"
        >
          &times;
        </button>
        <h2 className="text-2xl font-bold text-white mb-4">{video.title}</h2>
        <ReactPlayer
          url={video.url}
          controls
          width="100%"
          height="auto"
          className="rounded-lg"
        />
      </div>
    </div>
  );
}
