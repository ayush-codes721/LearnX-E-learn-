import React from "react";
import { useRecoilState } from "recoil";
import { AddCourseOpen, AddvideoOpen, ViewCourseOpen } from "../atoms/AppAtoms";

export default function NavBar() {
  const [isAddCourseOpen, setIsAddCourseOpen] = useRecoilState(AddCourseOpen);
  const [isAddVideoOpen, setIsAddVideoOpen] = useRecoilState(AddvideoOpen);
  const [isViewCourseOpen, setIsViewCourseOpen] =
    useRecoilState(ViewCourseOpen);

  return (
    <nav className="bg-gray-800 p-4 shadow-lg rounded-xl mx-auto mt-10 w-3/4 md:w-2/4 lg:w-1/3 border border-gray-700">
      <div className="flex justify-between items-center">
        <div className="text-white text-2xl font-extrabold tracking-wide">
          LearnX
        </div>
        <div className="flex space-x-4">
          <button
            onClick={() => {
              setIsAddCourseOpen(true);
              setIsAddVideoOpen(false);
              setIsViewCourseOpen(false);
            }}
            className="text-gray-300 hover:text-white bg-gray-700 hover:bg-gray-600 py-2 px-4 rounded-lg shadow-md transition duration-300 ease-in-out transform hover:scale-105"
          >
            Add Course
          </button>
          <button
            onClick={() => {
              setIsAddCourseOpen(false);
              setIsAddVideoOpen(false);
              setIsViewCourseOpen(true);
            }}
            className="text-gray-300 hover:text-white bg-gray-700 hover:bg-gray-600 py-2 px-4 rounded-lg shadow-md transition duration-300 ease-in-out transform hover:scale-105"
          >
            View Courses
          </button>
          <button
            onClick={() => {
              setIsAddCourseOpen(false);
              setIsAddVideoOpen(true);
              setIsViewCourseOpen(false);
            }}
            className="text-gray-300 hover:text-white bg-gray-700 hover:bg-gray-600 py-2 px-4 rounded-lg shadow-md transition duration-300 ease-in-out transform hover:scale-105"
          >
            Add Lectures
          </button>
        </div>
      </div>
    </nav>
  );
}
