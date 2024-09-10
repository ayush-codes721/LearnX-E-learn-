import React from "react";
import ReactPlayer from "react-player";
import NavBar from "./components/NavBar";
import AddCourseForm from "./components/AddCourseForm";
import AddVideos from "./components/AddVideos";
import { useRecoilValue } from "recoil";
import { AddCourseOpen, AddvideoOpen, ViewCourseOpen } from "./atoms/AppAtoms";
import ViewCourse from "./components/ViewCourse";

export default function App() {
  const isAddCourseOpen = useRecoilValue(AddCourseOpen);
  const isAddVideoOpen = useRecoilValue(AddvideoOpen);
  const isViewCourseOpen = useRecoilValue(ViewCourseOpen);
  return (
    <div>
      <NavBar />
      {isAddCourseOpen && <AddCourseForm />}
      {isAddVideoOpen && <AddVideos />}
      {isViewCourseOpen && <ViewCourse />}{" "}
    </div>
  );
}
