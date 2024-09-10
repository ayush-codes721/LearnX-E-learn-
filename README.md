# Learn X

## Overview

This application provides a video management system with backend services using Spring Boot and integrates with Amazon S3 for video storage and streaming. The frontend is built with React, Axios, Recoil, and React Hot Toast for an interactive user experience. The system allows for video upload, retrieval, deletion, and streaming.

## Features

- **Upload Video**: Upload videos to a specific course and store them in Amazon S3.
- **Retrieve Video**: Fetch details of a video by its ID.
- **Get Videos by Course**: Retrieve all videos associated with a specific course.
- **Delete Video**: Remove a video from the database and Amazon S3.
- **Stream Video**: Stream video content directly from Amazon S3.

## Technology Stack

### Backend

- **Framework**: Spring Boot
- **Storage**: Amazon S3
- **Database**: (Specify if using any, e.g., PostgreSQL)

### Frontend

- **Library**: React
- **HTTP Client**: Axios
- **State Management**: Recoil
- **Notifications**: React Hot Toast

## API Endpoints

### Video Management

#### Upload Video

- **URL**: `/api/videos/upload`
- **Method**: `POST`
- **Parameters**:
  - `file` (multipart/form-data): The video file to be uploaded.
  - `courseId` (query parameter): The ID of the course to which the video belongs.
  - `title` (query parameter): The title of the video.
- **Response**:
  - **200 OK**: Returns the uploaded video details.

#### Get Video by ID

- **URL**: `/api/videos/{id}`
- **Method**: `GET`
- **Response**:
  - **200 OK**: Returns video details for the given ID.

#### Get Videos by Course ID

- **URL**: `/api/videos/course/{courseId}`
- **Method**: `GET`
- **Response**:
  - **200 OK**: Returns a list of videos associated with the specified course ID.

#### Delete Video

- **URL**: `/api/videos/{id}`
- **Method**: `DELETE`
- **Response**:
  - **200 OK**: Confirmation message that the video was deleted.

#### Stream Video

- **URL**: `/api/videos/stream/{filename}`
- **Method**: `GET`
- **Response**:
  - **200 OK**: Streams the video file from Amazon S3.
  - **Headers**:
    - `Content-Type`: `video/mp4` (or the appropriate MIME type for the video)

## Frontend Setup

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-repository-url.git
   cd your-repository-name
