import axios from "axios";
import React, { useState } from "react";
import toast from "react-hot-toast";

export default function AddCourseForm() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const { data } = await axios.post("http://localhost:5000/api/courses", {
        title,
        description,
      });
      setTitle("");
      setDescription("");

      if (data.id) {
        toast.success("course addedd");
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="flex flex-col items-center min-h-screen bg-gray-900 text-white py-10 mt-14">
      <div className="max-w-md w-full p-8 bg-gray-900 rounded-lg shadow-xl border border-gray-800">
        <h2 className="text-3xl font-bold text-white mb-8 text-center">
          Add New Course
        </h2>
        <form onSubmit={handleSubmit} className="space-y-8">
          <div>
            <label
              htmlFor="title"
              className="block text-sm font-medium text-gray-300 mb-2"
            >
              Course Title
            </label>
            <input
              id="title"
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              className="block w-full p-4 bg-gray-800 border border-gray-700 rounded-lg text-white placeholder-gray-500 focus:border-blue-400 focus:ring-1 focus:ring-blue-400 transition duration-300"
              placeholder="Enter course title"
              required
            />
          </div>
          <div>
            <label
              htmlFor="description"
              className="block text-sm font-medium text-gray-300 mb-2"
            >
              Course Description
            </label>
            <textarea
              id="description"
              rows="6"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              className="block w-full p-4 bg-gray-800 border border-gray-700 rounded-lg text-white placeholder-gray-500 focus:border-blue-400 focus:ring-1 focus:ring-blue-400 transition duration-300"
              placeholder="Enter course description"
              required
            />
          </div>
          <div className="text-center">
            <button
              type="submit"
              className="bg-blue-700 text-white py-3 px-6 rounded-lg shadow-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 transition duration-300 ease-in-out transform hover:scale-105"
            >
              Add Course
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
