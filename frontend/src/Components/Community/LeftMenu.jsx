import React from "react";
import { useSnapshot } from "valtio";
import state from "../../Utils/Store";

// Import icons
import { FaRegNewspaper, FaTasks, FaChartLine, FaUserFriends, FaBell } from "react-icons/fa";

const LeftMenu = () => {
  const snap = useSnapshot(state);

  const handleClick = (index) => {
    state.activeIndex = index;
  };

  const menuIcons = [
    <FaRegNewspaper />,
    <FaTasks />,
    <FaChartLine />,
    <FaUserFriends />,
    <FaBell />,
  ];

  return (
    <div className="left-menu">
      <div className="left-menu-header">
        <img src="./assets/icon.png" alt="Logo" style={{ width: "100px" }} />
        
      </div>
      <ul className="left-menu-list">
        {menuIcons.map((icon, index) => (
          <li
            key={index}
            onClick={() => handleClick(index + 1)}
            className={`left-menu-item ${snap.activeIndex === index + 1 ? "active" : ""}`}
          >
            <a href="#" className="left-menu-link" style={{ fontSize: "20px" }}>
              {icon}
            </a>
            {snap.activeIndex === index + 1 && (
              <div className="left-menu-active-indicator" />
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default LeftMenu;
