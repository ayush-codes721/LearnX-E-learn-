import { atom } from "recoil";

export const AddCourseOpen = atom({
  default: true,
  key: "course",
});

export const AddvideoOpen = atom({
  default: false,
  key: "vid",
});
export const ViewCourseOpen = atom({
  default: false,
  key: "viewC",
});
