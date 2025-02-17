import React, { useState, useEffect } from "react";
import axios from "axios";

function Adminpage() {
  const [allUsers, setAllUsers] = useState([]);

  useEffect(() => {
    const storedToken = localStorage.getItem("AuthToken");

    axios
      .get("http://localhost:8080/api/v1/users", {
        headers: {
          Authorization: `Bearer ${storedToken.trim()}`,
        },
        withCredentials: true,
      })
      .then((response) => {
        if (Array.isArray(response.data)) {
          setAllUsers(response.data);
        } else {
          setAllUsers([]);
        }
      })
      .catch((err) => {
        console.error("Error fetching users:", err.message);
      });
  }, []);

  const handleDeleteUser = (Id) => {
    const storedToken = localStorage.getItem("AuthToken");
    console.log(storedToken);
    axios
      .delete(`http://localhost:8080/api/v1/deleteUser/${Id}`, {
        headers: {
          Authorization: `Bearer ${storedToken.trim()}`,
        },
        withCredentials: true,
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error.response?.data || "An error occurred.");
      });
   
  };

  return (
    <div>
      <h2 className="flex">Admin Page</h2>

      <h3>All Users:</h3>
      {allUsers.length === 0 ? (
        <p>No users found.</p>
      ) : (
        <table border="1" cellPadding="10" cellSpacing="0">
          <thead>
            <tr>
              <th>ID</th>
              <th>Email</th>
              <th>Name</th>
              <th>Role</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {allUsers.map((user, index) => (
              <tr key={user.id || index}>
                <td>{user.id}</td>
                <td>{user.email}</td>
                <td>{user.name}</td>
                <td>{user.role}</td>
                <td>
                  <button onClick={() => handleDeleteUser(user.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default Adminpage;
