import React, { useState, useEffect } from "react";

function Dashboard({ user }) {
  const [amount, setAmount] = useState("");
  const [transactions, setTransactions] = useState([]);
  const [balance, setBalance] = useState(user.balance);

  const handleAction = async (type) => {
    const res = await fetch(`/api/${type}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ userId: user.id, amount })
    });
    if (res.ok) {
      const updated = await res.json();
      setBalance(updated.balance);
      loadTransactions();
    }
  };

  const loadTransactions = async () => {
    const res = await fetch(`/api/transactions/${user.id}`);
    setTransactions(await res.json());
  };

  useEffect(() => { loadTransactions(); }, []);

  return (
    <div>
      <h2>Welcome, {user.username}</h2>
      <p>Balance: ${balance}</p>
      <input value={amount} onChange={e=>setAmount(e.target.value)} />
      <button onClick={()=>handleAction("deposit")}>Deposit</button>
      <button onClick={()=>handleAction("withdraw")}>Withdraw</button>
    </div>
  );
}
export default Dashboard;
