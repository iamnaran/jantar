# Jetpack Compose App for Medicine Detection Using Google Gemini API

This project is a Jetpack Compose-based Android app that detects medicine information from images using Google Gemini API and Flask API deployed on Render. The app allows users to scan medicine packaging via the device's camera, upload the captured image to a Flask API, and retrieve relevant data such as dosage, usage, and other details.

## Features

- **Camera Integration**: Uses CameraX in Jetpack Compose to capture images of medicine packaging.
- **Image Upload**: Captures the image and sends it to a Flask API hosted on Render.
- **Google Gemini API**: Uses Google Gemini API to analyze the image and return relevant details about the medicine.
- **Jetpack Compose UI**: Provides a clean and modern UI built with Jetpack Compose for a seamless experience.

## Project Structure

### Android App

- **Jetpack Compose UI**: Used to build the user interface, including the camera preview and image capture functionality.
- **Ktor HTTP Client**: Used to send HTTP requests to the Flask API, which processes the image and interacts with the Google Gemini API.
- **CameraX**: Integrated to capture images using the device camera.
- **Google Gemini API**: The API used for processing and analyzing the medicine images.

### Flask API (Backend)

- **Flask**: A lightweight Python web framework used to build the backend that handles requests from the mobile app.
- **Google Gemini API**: Integrated in the Flask API to process image data and return detailed information about the medicine detected in the image.
- **Render Deployment**: Flask app is deployed on Render to be accessible over the internet.