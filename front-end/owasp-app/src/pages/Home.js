import React from "react";
import { Link } from "react-router-dom";

function Home() {
  return (
    <div>
      <nav>
        <h1>Home</h1>
        <ul >
            <li>
            <Link to="/">Home</Link>
            </li>
          <li>
            <Link to="/Register">Register</Link>
          </li>
          <li>
            <Link to="/Login">Login</Link>
          </li>
        </ul>
      </nav>
    </div>
  );
}

export default Home;
