# 💳 Cloud Bank – Android Mobile Banking App

Cloud Bank is a simple Android mobile application developed in Android Studio as part of the TP3 (ListView) mobile development course.
The project demonstrates how to build a multi-screen Android app with user authentication, interactive lists, and dynamic data transfer between activities.

---

# Features
## Login Screen

* Secure login interface that validates user credentials.

* “Save username” toggle for improved user experience.

* On successful login, the app navigates to the home screen.

## 💰 Home Screen (Transactions List)

* Displays a ListView of recent transactions (internet bills, card payments, withdrawals, etc.).

* Each transaction item includes:

  * Transaction type and description

  * Amount

  * Operation date

## 📄 Transaction Details Screen

* When a user clicks a transaction, a detailed view appears showing:

  * Account number and reference

  * Description and operation type

  * Dates of operation and validation

  * Transaction amount and balance

---
## 🧩 Technical Overview

* Language: Java

* IDE: Android Studio

* Architecture: Multi-Activity navigation using Intent and getExtra()

* UI Components: ListView, TextView, Button, Switch

* Dependency: ButterKnife
 for simplified view binding

---

## 📱 Screenshots
<div align="center">
  
| Login Screen #1                           | Transactions List Screen #2        | Transaction Details screen #3 |
| ----------------------------------------- | ---------------------------------- | ----------------------------- |
| <img width="1080" height="2400" alt="Screenshot_20251020_193527" src="https://github.com/user-attachments/assets/923a6d39-b53a-401b-8fc7-a1f2415186a6" /> | <img width="1080" height="2400" alt="Screenshot_20251020_193734" src="https://github.com/user-attachments/assets/bf7aea58-30bf-4c98-8017-db1576ef2b87" /> | <img width="1080" height="2400" alt="Screenshot_20251020_193806" src="https://github.com/user-attachments/assets/cf92cb94-2bcc-4315-91fe-14ac619b339b" />

 
</div>

---

## 🧠 Learning Objectives

This project was developed as part of the Mobile Development (TP3) coursework to:

* Understand Android activity lifecycle

* Implement ListView and custom adapters

* Handle inter-activity communication using Intents

* Improve UI responsiveness and navigation flow

---

## ⚙️ How to Run

**1. Clone the repository:**

```bash
git clone https://github.com/yourusername/cloud-bank.git
```

**2. Open the project in Android Studio**

**3. Build and run it on an Android Emulator or physical device**

---

## 👨‍💻 Author

### Imad Labrini
Android Developer & Software Engineering Student

---
## Under the supervision of:
### Dr. El Gahi Youssef
