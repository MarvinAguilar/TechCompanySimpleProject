import { useState } from "react";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import { EmployeeSavePopup } from "./EmployeeSavePopup";
import { EmployeeInfoPopup } from "./EmployeeInfoPopup";
import CardBackground from "../assets/card-bg.jpg";
import "./EmployeeCard.css";

export const EmployeeCard = ({ employee, setEmployees, fetchEmployees }) => {
  const [isInfoPopupOpen, setInfoPopupOpen] = useState(false);
  const [isEditPopupOpen, setEditPopupOpen] = useState(false);

  const profilePicture = `https://ui-avatars.com/api/?name=${employee?.firstName}+${employee?.lastName}&background=0D8ABC&color=fff&size=128`;

  const handleOpenEditPopup = () => {
    setEditPopupOpen(true);
  };

  const handleCloseEditPopup = () => {
    setEditPopupOpen(false);
  };

  const handleOpenViewMore = () => {
    setInfoPopupOpen(true);
  };

  const handleCloseViewMore = () => {
    setInfoPopupOpen(false);
  };

  const handleDeleteEmployee = async (employeeId) => {
    try {
      const url = `http://localhost:8085/api/v1/techcompany/employee/${employeeId}`;
      const response = await axios.delete(url);

      if (response.status === 200) {
        setEmployees((prevEmployees) =>
          prevEmployees.filter((employee) => employee.id !== employeeId)
        );
        toast.success("Employee has been deleted!", {
          position: "top-right",
          autoClose: 2000,
        });
      } else {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
    } catch (error) {
      console.error(error);
      toast.error("Failed to delete employee. Please try again.", {
        position: "top-right",
        autoClose: 2000,
      });
    }
  };

  return (
    <div className="card">
      <img src={CardBackground} alt="card background" className="card-img" />

      <img src={profilePicture} alt="profile image" className="profile-img" />

      <h1>{`${employee?.firstName} ${employee?.lastName}`}</h1>

      <p className="job-title">{employee?.jobPosition}</p>

      <p className="about">{employee.about}</p>

      <button onClick={handleOpenViewMore} className="btn">
        View More
      </button>

      <ul className="social-media">
        <li>
          <button onClick={handleOpenEditPopup} className="icon-button">
            <i className="fa-solid fa-pen-to-square"></i>
          </button>
        </li>
        <li>
          <button
            onClick={() => handleDeleteEmployee(employee.id)}
            className="icon-button"
          >
            <i className="fa-solid fa-trash"></i>
          </button>
        </li>
      </ul>

      {isInfoPopupOpen && (
        <EmployeeInfoPopup employee={employee} onClose={handleCloseViewMore} />
      )}

      {isEditPopupOpen && (
        <EmployeeSavePopup
          employee={employee}
          onClose={handleCloseEditPopup}
          fetchEmployees={fetchEmployees}
        />
      )}

      <ToastContainer position="top-right" autoClose={2000} />
    </div>
  );
};
