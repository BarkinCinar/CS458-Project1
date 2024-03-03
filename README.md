# CS458-Project1
CS 458 Project 1

This repository contains a full-stack application designed to manage user authentication for a website. It features a backend service built to handle authentication processes and a React-based frontend that provides a user interface for login operations. Additionally, the project includes a suite of tests that cover various authentication scenarios.

## Directory Structure

- `a-authentication-main`: Backend authentication service.
  - `server.js`: Node.js server script.

- `a-react-main/a-react`: Frontend React application.
  - `public`: Contains static assets like HTML file, favicon, and images.
  - `src`: Contains React component files, styling, and configuration.
    - `App.js`: Main React component that renders the login page.
    - `GoogleOAuth.js`: Component handling Google OAuth logic.
    - `Login.js`: Login component.

- `test`: Contains Java-based test scripts and resources.
  - `lib`: Libraries needed for running tests.
  - `Test.java`: Contains the test cases for the login system.

## How To Run The Code:

Website is live at: https://a-react-alpha.vercel.app/

But if you want to run the code
1. Clone the repository:

git clone https://github.com/BarkinCinar/CS458-Project1.git

2. Navigate to the backend directory and install dependencies:
cd CS458-PROJECT-1/a-authentication-main
npm install

3. Start the backend server:
npm start

4. Navigate to the frontend directory and install dependencies:
cd ../a-react-main/a-react
npm install

5. Start the React development server:
npm start

This should launch the web application on `localhost:3000` in your default web browser.

## Running the Tests

To run the test cases for the login system:

1. Ensure that you have Java installed on your system, as the test cases are written in Java.
2. Navigate to the test directory
3. Follow the instructions in the `README.md` file within the test directory to set up your testing environment and execute the tests.

