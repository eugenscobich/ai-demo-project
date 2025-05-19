import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    fetch('/api/books')
      .then(response => response.json())
      .then(data => setBooks(data));
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>Book Store</h1>
        <ul>
          {books.map(book => (
            <li key={book.id}>{book.name}</li>
          ))}
        </ul>
      </header>
    </div>
  );
}

export default App;
