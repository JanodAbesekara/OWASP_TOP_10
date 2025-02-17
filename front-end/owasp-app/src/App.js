import './App.css';
import { Route,Routes } from 'react-router-dom';
import Register from './pages/Register';
import Home from './pages/Home';
import Login from './pages/Login';
import Adminpage from './pages/Adminpage';

function App() {
  return (
    <div className="App">
     <Routes>
      <Route path="/" element ={<Home/>}/>
      <Route path="/Register" element={<Register/>}/>
      <Route path="/Login" element={<Login/>}/>
      <Route path="/Adminpage" element={<Adminpage/>}/>
     </Routes>
    </div>
  );
}

export default App;
