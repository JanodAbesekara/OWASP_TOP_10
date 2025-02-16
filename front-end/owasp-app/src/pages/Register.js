import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function Register() {
  const handlesubmit = async(e) => {
    e.preventDefault();
    try {
    const respond =   await axios.post(`http://localhost:8080/api/v1/Register`,{
            name,
            email,
            role,
            password,
        });
        window.alert(respond.data.name+"You are Regiserd Successfully")
        
    } catch (error) {
      console.log("Give this error" + error);
    }
  };

  const [name, setname] = useState("");
  const [email, setemail] = useState("");
  const [role, setRole] = useState("");
  const [password, setpassword] = useState("");

  return (
    <div>
      <h2>Register Form</h2>
      <form onSubmit={handlesubmit} style={{border:"2px solid black", padding:"20px"}}>
        <label>Name:- </label>
        <input
          type="text"
          placeholder="Jhone Dohn"
          onChange={(e) => setname(e.target.value)}
        />
        <br />
        <br />
        <label>Email:- </label>
        <input
          type="email"
          placeholder="example@gmail.com"
          onChange={(e) => setemail(e.target.value)}
        />
        <br />
        <br />

        <label>Role: </label>
        <br />
        <input
          type="radio"
          name="role"
          value="Customer"
          checked={role === "Customer"}
          onChange={(e) => setRole(e.target.value)}
        />
        <label>Customer</label>
        <br />

        <input
          type="radio"
          name="role"
          value="Seller"
          checked={role === "Seller"}
          onChange={(e) => setRole(e.target.value)}
        />
        <label>Seller</label>
        <br />
        <br />
        <label>Password :-</label>
        <input
          type="password"
          placeholder="password"
          onChange={(e) => setpassword(e.target.value)}
        />
        <br />
        <br />
        <button type="submit" >Register</button>
      </form>
      <button style={{margin:"100px"}}>
        <Link to="/Login">Login</Link>
      </button>
    </div>
  );
}

export default Register;
