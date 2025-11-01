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

### Displays a ListView of recent transactions (internet bills, card payments, withdrawals, etc.).
### Dynamic Data Fetching: 
The transaction data presented here is dynamically fetched from the local SQLite database. The data access layer is handled by the databaseHelper.java file.

- **SQLite Database:** All transaction records are stored within the SQLite database file named *bank_data.db* via the helper.

- **Data Fetching:** The databaseHelper.java file is responsible for connecting to the SQLite database and executing the necessary SQL queries to store and fetch the data from the designated *table_transactions*.

- **Data Model:** The retrieved data is structured and encapsulated using the *Transaction.java* model class, ensuring a clean Object-Oriented representation of the database records before being passed to the list adapter for display.

### Each transaction item includes: ###

* Transaction type and icon

* Amount (DH)

* Operation date

## 📄 Transaction Details Screen

* When a user clicks a transaction, a detailed view appears showing:

  * Account number and reference

  * Description and operation type

  * Dates of operation and validation

  * Transaction amount and balance
 
---
## 🗺️ Maps Activity (Agencies Locator)

The Maps Activity provides an interactive Google Map interface that allows users to search, locate, and interact with bank agencies in real time.
It integrates multiple communication features (call, SMS, and email) to make it easier for users to contact agencies directly from the app.

🌍 Core Functionalities
### 🏦 Displaying Agencies on the Map

* All registered bank agencies are displayed as markers on the Google Map.

* Each marker represents an agency’s geographical location (latitude and longitude).

* When the user taps a marker, a detailed card appears showing:

  * Agency name

  * Address

  * Contact number and email

  * Options to call, send an SMS, or email the agency.

### 🔍 Search Bar (Agency Lookup)

A SearchView component allows users to quickly find an agency by name.

When the user types the agency name, the app automatically:

* Locates the corresponding marker on the map.

* Animates and centers the map camera on the selected agency.

* Displays the agency’s detailed information.

### 📞 Call Agency

* A “Call” button enables users to directly call the selected agency via the phone dialer.

* The app securely checks and requests the CALL_PHONE permission before initiating the call.

* If permission is denied, the app shows a helpful message guiding the user to enable it.

### 💬 Send SMS

* Users can contact an agency via SMS through the integrated SMS button.

* Automatically opens the device’s default messaging app with the agency’s number pre-filled.

* Ideal for quick inquiries or appointment scheduling.

### 📧 Send Email

The Email button opens the user’s preferred email client with:

* The agency’s email address automatically filled in.

* A predefined subject line such as “Bank Inquiry” to save time.

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

| Login Screen | Transactions | Details |
|:--:|:--:|:--:|
| <img src="https://github.com/user-attachments/assets/23e9c464-b729-4a4a-9b89-33aac3d9d71b" width="300"/> | <img src="https://github.com/user-attachments/assets/bf7aea58-30bf-4c98-8017-db1576ef2b87" width="300"/> | <img src="https://github.com/user-attachments/assets/cf92cb94-2bcc-4315-91fe-14ac619b339b" width="300"/> |


| Maps Screen #4                            | Agency Details #5       | Send SMS #6             |
| ----------------------------------------- | ------------------------| ------------------------|
| <img width="300" src="https://github.com/user-attachments/assets/2903f105-d351-4522-a04d-89bed1294ebc" /> | <img width="300" src="https://github.com/user-attachments/assets/d5b0f9ce-127c-47ca-b7b9-930e1c2ef82f" /> | <img width="300" src="https://github.com/user-attachments/assets/3db83af3-0e9f-4c50-8642-9279658a313c" />



 
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

