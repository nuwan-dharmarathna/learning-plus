import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  const [scrolled, setScrolled] = useState(false);
  const [menuOpen, setMenuOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    if (localStorage.getItem("userId")) {
      setIsLoggedIn(true);
    }

    const handleScroll = () => {
      setScrolled(window.scrollY > 50);
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  const toggleMenu = () => {
    setMenuOpen(!menuOpen);
  };

  return (
    <nav className={`navbar ${scrolled ? "navbar__scrolled" : ""} font-sans font-semibold text-size-[100px]`}>
      <div className="nav__container">
        <div className="nav__header">
          <div className="nav__logo">
            <img style={{ width: "100px", height: "100px" }} src="./assets/icon.png" alt="" />
            <Link to="/">
              
            </Link>
          </div>

          <div className="nav__search">
            <form>
              <input type="text" placeholder="Search for skills, topics..." style={{ fontSize: "16px", padding: "10px" }} />
              <button type="submit" className="search__button" style={{ fontSize: "16px", padding: "10px" }}>
                <i className="fas fa-search"></i>
              </button>
            </form>
          </div>

          <button className="nav__menu__btn" onClick={toggleMenu}>
            <div className={`menu__icon ${menuOpen ? "open" : ""}`}>
              <span></span>
              <span></span>
              <span></span>
            </div>
          </button>
        </div>

        <ul className={`nav__links ${menuOpen ? "open" : ""}`} style={{ fontSize: "18px" }}>
          <li className="nav__item">
            <Link to="/services" className=" text-gray-900">Projects</Link>
          </li>
          <li className="nav__item">
            <Link to="/specialists" className=" text-gray-900">Mentors</Link>
          </li>
          <li className="nav__item">
            <Link to="/telehealth" className=" text-gray-900">Courses</Link>
          </li>
          <li className="nav__item">
            <Link to="/resources" className=" text-gray-900">Resources</Link>
          </li>

          <li className="nav__item nav__item--cta">
            {isLoggedIn ? (
              <Link to="/patient-portal" className="nav__link nav__link--cta">My Skills</Link>
            ) : (
              <Link to="/get-started" className="nav__link nav__link--cta">Start Skill Journey</Link>
            )}
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;

