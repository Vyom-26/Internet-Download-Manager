---
# Internet Download Manager

An **Internet Download Manager (IDM)** built using Java that provides the capability to download multiple files concurrently. This application showcases the power of multithreading by managing multiple downloads simultaneously, offering a comprehensive solution for file downloads, complete with real-time progress tracking, error handling, and a user-friendly graphical interface.

---

# Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Project Structure](#project-structure)
5. [How It Works](#how-it-works)
6. [Setup and Usage](#setup-and-usage)
7. [Workflow](#workflow)
8. [Classes Overview](#classes-overview)
9. [Future Enhancements](#future-enhancements)
10. [Video Demonstration](#video-demonstration)
11. [Contributing](#contributing)
12. [License](#license)

---
# Introduction 

The Internet Download Manager (IDM) is a multithreaded Java application that enables users to download files concurrently from the internet. It offers a simplified GUI that lets users manage file downloads in real-time, track progress, and handle download failures. By leveraging Java’s robust multithreading capabilities, the IDM optimizes resource usage and download speed, improving overall efficiency. Users simply input the file URL, choose a destination folder, and the download begins—complete with live status updates for each file.

---

# Features

1. **Concurrent Downloads:** Download multiple files concurrently using Java multithreading.
2. **Real-time Progress Tracking:** Live status updates showing the progress of each download.
3. **User-Friendly GUI:** Built with JavaFX, the interface is simple and intuitive.
4. **Error Handling:** The application gracefully handles errors like failed downloads, providing appropriate feedback.
5. **Cross-Platform Compatibility:** Written in Java, making the program compatible with multiple operating systems (Windows, MacOS, Linux).

---

# Technologies Used

1. **Java (JDK 8 or later):** The core programming language used for the entire application.
2. **JavaFX:** For building the graphical user interface (GUI) that allows users to manage downloads interactively.
3. **Multithreading:** Java’s Thread class is used to manage multiple file downloads concurrently.
4. **I/O Streams:** For handling file downloads and writing to disk using BufferedInputStream and FileOutputStream.
5. **Networking:** Uses Java's URLConnection to connect to the provided URL and download files.
6. **FXML:** For defining the layout and behavior of the JavaFX components.

---

# Project Structure

```
Internet-Download-Manager/
│
├── src/main/java/org/example/
│   ├── App.java                        # Main entry point for the JavaFX application
│   ├── DownloadManager.java            # Core logic for managing and coordinating downloads
│   ├── DownloadThread.java             # Handles multithreaded file downloads
│   ├── models/
│        └── FileInfo.java              # Stores metadata about each download (e.g., URL, file path)
│
├── src/main/resources/
│   └── downloadManager.fxml            # FXML file defining the GUI layout and controls
│
└── README.md                           # This file
```

---

# How It Works

1. **Input URL:** Users provide a URL for the file they wish to download.
2. **Start Download:** The application starts downloading the file by launching a separate thread for each download request.
3. **Multithreading:** Each file is downloaded concurrently using Java’s multithreading capabilities.
4. **File Download:** A URL connection is opened, data is read in chunks using BufferedInputStream, and it is saved to a local directory using FileOutputStream.
5. **Real-Time Updates:** The GUI updates in real time, showing the download status (e.g., "DOWNLOADING", "DONE", "FAILED") and progress percentage.
6. **Error Handling:** If a download fails, the status is set to "FAILED" and appropriate feedback is provided to the user.

---

# Setup and Usage

## Prerequisites
1. Java Development Kit (JDK) 8 or later installed.
2. Maven for building the project.
3. A suitable IDE (e.g., IntelliJ IDEA, Eclipse) or command line interface

## Installation
1. Clone the Repository:
```
git clone https://github.com/Vyom-26/Internet-Download-Manager.git
cd Internet-Download-Manager
```
2. Build the Project:
```
mvn clean install
```
## Usage
1. **Open the Project:** Open the project in your preferred IDE.
2. **Run the Application:** The entry point of the project is in the App.java file inside the org.example package.

   Navigate to the file and run it:

   ```
   src/main/java/org/example/App.java
   ```
   This will launch the JavaFX application, where the graphical interface will allow you to manage downloads.
3. **Using the GUI:**
   - **Input the File URL:** Paste the URL of the file you want to download.
   - **Choose Download Location:** Specify where you want to save the downloaded file.
   - **Start Downloading:** Click the "Download" button to initiate the process.
   - **Monitor Progress:** The GUI will update in real-time, showing the status and progress of each active download.

---

# Workflow

1. **User Input:** Users provide the URL of the file they want to download.
2. **File Creation:** The DownloadThread class creates a new thread for each file download.
3. **Downloading:**
    - Opens a connection to the provided URL.
    - Reads the file data in chunks.
    - Writes the data to local storage.
4. **Progress Tracking:** Each thread monitors download progress and updates the percentage in real time.
5. **UI Update:** The GUI updates the download status for each file ("DOWNLOADING," "DONE," "FAILED").
6. **Completion:** Upon download completion, the status is updated. If any error occurs, it will be indicated on the GUI.

---

# Classes Overview

## 1. App.java (Main Entry Point)
   - **Responsibilities:** Handles the lifecycle of the JavaFX application and initializes the GUI.
   - **Key Methods:**
       - **start(Stage stage):** Initializes the primary stage and sets up the scene with the downloadManager.fxml layout.
       - **setRoot(String fxml):** Changes the root scene for navigation between different FXML views.
       - **loadFXML(String fxml):** Loads the FXML file for the user interface from the resources.
       - **main():** The main method that launches the JavaFX application.
    
## 2. DownloadManager.java (Main Logic for Managing Downloads)
  - **Responsibilities:** Manages user input, coordinates file downloads, and handles the UI updates in real time.
  - **Key Methods:**
      - **downloadButtonClicked(ActionEvent event):** Handles the click event for the "Download" button, extracting the URL, setting up the file information, and starting a new download thread for each file.
      - **updateUI(fileInfo metaFile):** Updates the UI by refreshing the table view with the real-time status and progress of the downloads.
      - **initialize():** Initializes the table view and binds columns to properties of the fileInfo model.
    
## 3. DownloadThread.java (Multithreaded File Downloads)
  - **Responsibilities:** Manages the downloading process for each file on a separate thread to enable concurrent downloads.
  - **Key Methods:**
     - **run():** Contains the download logic. It opens a connection to the provided URL, reads the file data in chunks, writes the data to the specified path, and updates the UI with the current download progress. It also handles errors, updating the status as "FAILED" if any issue occurs.

## 4. fileInfo.java (Model for Download Metadata)
  - **Responsibilities:** Stores metadata for each file being downloaded, including its index, name, URL, status, action, path, and progress percentage.
  - **Key Methods:**
      - **indexProperty()**, **nameProperty()**, **urlProperty()**, **statusProperty()**, **actionProperty()**, **perProperty()**: These methods provide access to the properties of the download data to allow real-time binding with the table view in the UI.

---

# Future Enhancements

  - **Pause/Resume Functionality:** Add the ability to pause and resume downloads.
  - **Download Queue:** Implement a queue system to limit the number of concurrent downloads.
  - **File Validation:** Validate the URL before starting the download.
  - **Settings Panel:** Allow users to configure settings, such as download speed limits and max concurrent downloads.

--- 

# Video Demonstration

Here's a video demonstration of the Internet Download Manager in action, showcasing its key features, including concurrent downloads, real-time progress tracking, and error handling.

--- 

# Contributing

## Contributions are welcome! Please follow these steps to contribute:
   - **Fork the repository.**
   - **Create a new branch for your feature:**
     ```
     git checkout -b feature/YourFeature.
     ```
   - **Make your changes and commit them:**
     ```
     git commit -m 'Add new feature'.
     ```
   - **Push to the branch:**
     ```
     git push origin feature/YourFeature.
     ```
   - **Open a pull request.**

--- 
# License

 The source code for the site is licensed under the MIT license, which you can find in the MIT-LICENSE.txt file.
 
--- 
