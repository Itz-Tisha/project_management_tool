# 📋 Project Management Tool

A collaborative Project Management System developed using Java, Spring Boot, and Hibernate, enabling teams to manage tasks, communicate, and track project progress efficiently. It supports user-group assignments, file-based chat, notifications, and task delegation.

## 🚀 Features

### 👥 User Management
- User registration and login
- Users can create or join project groups

### 🧑‍💻 Project Groups
- Each group is managed by a user
- Projects contain descriptions, team members, and assigned tasks

### 📌 Task Management
- Admin can define group-level task descriptions
- Tasks can be assigned to users within a project group

### 💬 Chat System
- Group-based real-time chat feature
- Supports file sharing (stored as `byte[]`)

### 🔔 Notifications
- Project-specific notifications are delivered to group members

## 🛠️ Tech Stack

- Backend: Java, Spring Boot, Spring MVC
- ORM: Hibernate (JPA)
- Database: MySQL 

## ⚙️ Entity Relationships

| Entity              | Relationships                                                                 |
|---------------------|------------------------------------------------------------------------------|
| `webuser`           | - One-to-many with `projectgroup`, `task`, `chat`, `notifications`, `teammember` |
| `projectgroup`      | - Many-to-one with `webuser` (creator) <br> - One-to-many with `task`, `group_task_desc`, `chat`, `notifications`, `teammember` |
| `group_task_desc`   | - Many-to-one with `projectgroup` <br> - One-to-many with `task`              |
| `task`              | - Many-to-one with `webuser`, `projectgroup`, `group_task_desc`              |
| `chat`              | - Many-to-one with `webuser`, `projectgroup`                                 |
| `notifications`     | - Many-to-one with `webuser`, `projectgroup`                                 |
| `teammember`        | - Many-to-one with `webuser`, `projectgroup`                                 |

