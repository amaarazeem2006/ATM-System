import React, { useState } from "react";

function Login({ setUser, setShowRegister }) {
  const [username, setUsername] = useState("");
  const [pin, setPin] = useState("");

  const handleLogin = async () => {
    const res = await fetch("/api/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, pin })
    });
    if (res.ok) setUser(await res.json());
    else alert("Login failed");
  };

  return (
    <div>
      <h2>Login</h2>
      <input placeholder="Username" onChange={e=>setUsername(e.target.value)} />
      <input type="password" placeholder="PIN" onChange={e=>setPin(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
      <p>Don't have an account? <button onClick={()=>setShowRegister(true)}>Register</button></p>
    </div>
  );
}
export default Login;
