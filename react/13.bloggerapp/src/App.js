import React from 'react';
import './App.css';
import {books,blogs,courses} from './Data';

function App() {

  const booklist = (
    books.map((book) =>
      <div key={book.id}>
        <h2>{book.bname}</h2>
        <h4>{book.price}</h4>
      </div>
    )
  );

  const bloglist = (
    blogs.map((blog) =>
      <div key={blog.id}>
        <h2>{blog.title}</h2>
        <h4>{blog.author}</h4>
        <p>{blog.content}</p>
      </div>
    )
  );

  const courselist = (
    courses.map((course) =>
      <div key={course.id}>
        <h2>{course.cname}</h2>
        <h4>{course.date}</h4>
      </div>
    )
  );

  return (
    <div>
      <div>
        <div className="myStyle1">
          <h1>Course Details</h1>
          {courselist}
        </div>
        <div className="h2">
          <h1>Book Details</h1>
          {booklist}
        </div>
        <div className="h1">
          <h1>Blog Details</h1>
          {bloglist}
        </div>
      </div>
    </div>
  );
}

export default App;
