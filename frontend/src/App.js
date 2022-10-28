
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LoginPage from './page/LoginPage';
import SignupPage from "./page/SignupPage";
import NavBar from "./component/navbar/NavBar";
import HomePage from './page/HomePage';
import ProfilePage from "./page/ProfilePage";
import ProfileSettingsPage from "./page/ProfileSettingsPage";
import SearchPage from "./page/SearchPage";

import AdminHomePage from "./page/AdminHomePage";
import ChatPage from "./page/ChatPage";
function App() {
    const exclusionArray = [
        '/login',
        '/signup',

    ]
    {localStorage.getItem('login_token')!= null ? exclusionArray.push('/login') : exclusionArray.push('/')}
  return (

      <BrowserRouter>

          {exclusionArray.indexOf(window.location.pathname) < 0 && <NavBar/>}

          <Routes>
              <Route exact path="/login" element={<LoginPage />} />
              <Route exact path="/signup" element={<SignupPage />} />
              <Route exact path="/admin" element={<AdminHomePage />} />
              <Route exact path="/chat" element={<ChatPage />} />
              {localStorage.getItem("authority")==="4" ?
                  <Route exact path="/" element={<AdminHomePage />} />
                  :
                  (localStorage.getItem('login_token')!= null) ?
                      <Route exact path="/" element={<HomePage />} />
                      : <Route exact path="/" element={<LoginPage />} />
              }

              <Route exact path="/profile/:userid" element={<ProfilePage />} />
              <Route exact path="profile/:userid/settings" element={<ProfileSettingsPage />} />
              <Route exact path="/search/:query" element={<SearchPage />} />
          </Routes>
      </BrowserRouter>
  );
}

export default App;
