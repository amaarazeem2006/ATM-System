import React, { useState } from "react";
import Login from "./Login";
import Register from "./Register";
import Dashboard from "./Dashboard";

function App() {
  const [user, setUser] = useState(null);
  const [showRegister, setShowRegister] = useState(false);

  return (
    <div className="App">
      {!user ? (
        showRegister ? (
          <Register setUser={setUser} setShowRegister={setShowRegister} />
        ) : (
          <Login setUser={setUser} setShowRegister={setShowRegister} />
        )
      ) : (
        <Dashboard user={user} />
      )}
    </div>
  );
}
export default App;
