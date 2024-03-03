import React, { useState } from "react";
import { Navigate } from "react-router-dom";

function New() {
  // Check if there is a session (e.g., username) stored in session storage or cookies
  const username = sessionStorage.getItem("sessionUser"); // Assuming username is stored in session storage

  // State to manage redirection
  const [redirect, setRedirect] = useState(false);

  // Function to clear session and redirect to login page
  const clearSession = () => {
    // Clear session storage
    sessionStorage.removeItem("sessionUser");
    // Set redirect to true
    setRedirect(true);
  };

  // If redirect is true, navigate to "/error"
  if (redirect) {
    return <Navigate to="/" />;
  }

  // If there is no session (username), redirect the user to the login page
  if (!username) {
    return <Navigate to="/" />;
  }

  // If there is a session (username), render the content of the target page
  return (
    <div>
      <h1>Welcome, {username}!</h1>
      <p>Click Below to Clear Session and Redirect to Login Page</p>{" "}
      <button onClick={clearSession}>Clear Session</button>
    </div>
  );
}

export default New;
