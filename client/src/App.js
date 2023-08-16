
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


import './App.css';

import Header from "./components/Header/Header";
import Footer from './components/Footer/Footer';

import "../src/css/input.css";
import Main from "./pages/main";

import { useState } from 'react';

import SignUpPage from "./pages/SignUpPage";
import Questions from "./pages/questions";
import Ask from "./pages/ask";


function App() {


  const [isLogin,setIsLogin] = useState(false);

  return (
    <Router>
      <div className="App">
        <Routes>
            <Route path="/" element={<Main></Main>}>
            </Route>
            <Route path="/signup" element={<SignUpPage/>}>
            </Route>
            <Route path="/questions" element={<Questions/>}>
            </Route>
            <Route path="/questions/ask" element={<Ask/>}>
            </Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App;

