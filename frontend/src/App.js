import React, { useEffect, useState } from 'react';

function App() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('/api/books')
      .then((res) => {
        if (!res.ok) throw new Error('Network error');
        return res.json();
      })
      .then(setBooks)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div style={{ padding: 20 }}>
      <h2>Books List</h2>
      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
          </tr>
        </thead>
        <tbody>
          {books.map((b) => (
            <tr key={b.id}>
              <td>{b.id}</td>
              <td>{b.title}</td>
              <td>{b.author}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
