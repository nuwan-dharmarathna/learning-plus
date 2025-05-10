# cooking-share

# gym

# 📚 Learning Plus - Skill-Sharing & Learning Platform

Learning Plus is a full-stack web application that encourages people to **share and learn different skills** such as coding, cooking, photography, and DIY crafts. It's designed to help users grow and stay motivated through a collaborative learning experience.

This project is built using:

- **Spring Boot** (Java) for the backend
- **React.js** for the frontend

Both frontend and backend are located in the same repository under separate folders.

---

## 🌟 Features

### 🔄 Skill Sharing Posts

- Users can upload up to **3 photos or 30-second videos** per post
- Add rich **descriptions** to each post

### 📈 Learning Progress Updates

- Share your progress on your personal learning journey
- Use **predefined templates** for easy updates (e.g., completed tutorials, new skills learned)

### 📝 Learning Plan Sharing

- Create structured **learning plans** with topics, resources, and timelines
- Update plans as you grow and complete goals

### 💬 Interactivity & Engagement

- **Like** and **comment** on others' posts
- Edit or delete your own comments
- Post owners can delete comments on their posts

### 👥 User Profiles & Social Features

- Public **profile pages** show user posts and activities
- **Follow** other users to stay connected with their journey

### 🔔 Notifications

- Real-time notifications for **likes** and **comments** on your posts

### 🔐 Authentication

- Secure **OAuth 2.0** login using existing social media accounts

---

## 📁 Project Structure

root/
│
├── backend/ # Spring Boot backend API
│ └── ...
│
├── frontend/ # React frontend app
│ └── ...
│
└── README.md

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 17
- Node.js & npm
- Maven

---

### 🔧 Backend Setup (Spring Boot)

1. Navigate to the backend folder:
   ```bash
   cd backend
   ```
   mvn clean install
   mvn spring-boot:run
