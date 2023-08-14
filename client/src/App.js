
import { BrowserRouter, Route, Routes } from 'react-router-dom';


import './App.css';
import Header from "./components/Header/Header";
import Footer from './components/Footer/Footer';

import { useState } from 'react';

import SignUpPage from "./pages/SignUpPage";


function App() {

  

  

  

  const [isLogin,setIsLogin] = useState(false);

  return (
    <div className="App">
      <Header />
      <SignUpPage/>
      <Footer />
    </div>

  );
}

export default App;

