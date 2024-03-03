// Import required modules
const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors"); // Import the CORS middleware

// Create an instance of Express app
const app = express();

// Enable trust proxy - X-Forwarded-For Required Setting
app.set("trust proxy", "loopback"); // Nginx sets IP of the request X-Forwarded-For header thus to read it Express needs to be told to trust the proxy

// Configure middleware to parse JSON in request body
app.use(bodyParser.json());

// Enable CORS
app.use(cors());

var whitelist = ["http://localhost:3000", "https://a-react-alpha.vercel.app"];
var corsOptionsDelegate = function (req, callback) {
  var corsOptions;
  console.log("Incoming Request: " + req.ip);
  console.log("\t- Origin: " + req.header("Origin"));
  if (whitelist.indexOf(req.header("Origin")) !== -1) {
    console.log("\t- ALLOWED");
    corsOptions = { origin: true }; // reflect (enable) the requested origin in the CORS response
  } else {
    console.log("\t- REJECTED");
    corsOptions = { origin: false }; // disable CORS for this request
    if (!blacklist.includes(req.ip)) {
      blacklist.push(req.ip);
    }
  }
  callback(null, corsOptions); // callback expects two parameters: error and options
};

// Dummy user data (you can replace this with a database)
const users = [
  { username: "user@intros.com", phone: "5172172729", password: "password1" },
  { username: "user2@intros.com", phone: "5119724212", password: "password2" },
];

// Route to handle user sign-in
app.post("/signin", cors(corsOptionsDelegate), (req, res) => {
  const { username, password } = req.body;
  console.log("Incoming Request: " + req.body);
  console.log("Request Username: " + username);
  console.log("Request Password: " + password);

  // Check if username and password are provided
  if (!username || !password) {
    console.log("Username and password are required");
    return res.status(400).json({ message: "Username and password are required" });
  }

  // Find user in the dummy user data
  const user = users.find((u) => (u.username === username || u.phone === username) && u.password === password);

  // Check if user exists
  if (!user) {
    console.log("Invalid username or password");
    return res.status(401).json({ message: "Invalid username or password" });
  }

  // If user exists, return success message
  console.log("Sign in successful");
  return res.json({ message: "Sign in successful" });
});

// Start the server
const port = 3033;
app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
