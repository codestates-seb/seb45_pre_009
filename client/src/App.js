
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Header from "./components/Header/Header";
import Footer from './components/Footer/Footer';
import "../src/css/input.css";
import Main from "./pages/main";
import { useState } from 'react';
import SignUpPage from "./pages/SignUpPage";
import Sidebar from './components/Sidebar/Sidebar';

function App() {

  

  

  

  const [isLogin,setIsLogin] = useState(false);

  return (

    <div className="App">
      <Header />
      
      <div id='container' className='w-screen h-screen'>
      <Main></Main>
      <Sidebar />
      </div>
      <SignUpPage/>
      <Footer />
    </div>


  );
}

export default App;

