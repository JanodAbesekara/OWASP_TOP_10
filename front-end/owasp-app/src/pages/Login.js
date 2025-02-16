import React, { useState } from "react";
import axios from "axios";

function Login() {
  const [username, setusername] = useState("");
  const [password, setpassword] = useState("");

  const handleSubmit = async(e) => {
    e.preventDefault();

    const respond = await axios.post(`http://localhost:8080/api/v1/login`,{
        email:username,
        password:password,
    });

    console.log(respond.data);

  };

  return (
    <div>
      Login
      <br />
      <br />
      <form onSubmit={handleSubmit}>
        <label>UserName:- </label>
        <input
          type="email"
          placeholder="exaplme@gmail.com"
          onChange={(e) => setusername(e.target.value)}
        />
        <br />
        <br />
        <label>Password:- </label>
        <input
          type="password"
          placeholder="password"
          onChange={(e) => setpassword(e.target.value)}
        />
        <br />
        <br />
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
