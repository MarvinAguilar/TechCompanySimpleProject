import { useState } from "react";
import ReactDOM from "react-dom";
import axios from "axios";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./EmployeeSavePopup.css";

export const EmployeeSavePopup = ({ employee, onClose }) => {
  const [editedEmployee, setEditedEmployee] = useState(
    employee
      ? { ...employee }
      : {
          id: "",
          firstName: "",
          lastName: "",
          about: "",
          email: "",
          gender: "",
          location: "",
          jobPosition: "",
        }
  );

  const handleFieldChange = (field, value) => {
    setEditedEmployee((prevEmployee) => ({
      ...prevEmployee,
      [field]: value,
    }));
  };

  const validateForm = () => {
    const { firstName, lastName, jobPosition, about, email, gender, location } =
      editedEmployee;

    if (!employee) {
      return !!(
        editedEmployee.id &&
        firstName &&
        lastName &&
        jobPosition &&
        about &&
        email &&
        gender &&
        location
      );
    }

    return !!(
      firstName &&
      lastName &&
      jobPosition &&
      about &&
      email &&
      gender &&
      location
    );
  };

  const handleUpdate = () => {
    if (validateForm()) {
      const id = editedEmployee.id;

      axios
        .patch(
          `http://localhost:8085/api/v1/techcompany/employee/${id}`,
          editedEmployee
        )
        .then((response) => {
          console.log("Employee updated:", response.data);

          toast.success("Employee has been updated!", {
            position: "top-right",
            autoClose: 2000,
            onClose: () => {
              setTimeout(() => {
                onClose();
              }, 3000);
            },
          });
        })
        .catch(() => {
          toast.error("Error updating employee. Please try again.", {
            position: "top-right",
            autoClose: 2000,
          });
        });
    } else {
      toast.error("Please fill in all required fields.", {
        position: "top-right",
        autoClose: 2000,
      });
    }
  };

  const handleSave = () => {
    if (validateForm()) {
      axios
        .post(
          "http://localhost:8085/api/v1/techcompany/addEmployee",
          editedEmployee
        )
        .then((response) => {
          console.log("Employee saved:", response.data);

          toast.success("Employee has been saved!", {
            position: "top-right",
            autoClose: 2000,
            onClose: () => {
              setTimeout(() => {
                onClose();
              }, 3000);
            },
          });
        })
        .catch((error) => {
          console.error("Error saving employee:", error);
          toast.error("Error saving employee. Please try again.", {
            position: "top-right",
            autoClose: 2000,
          });
        });
    } else {
      toast.error("Please fill in all required fields.", {
        position: "top-right",
        autoClose: 2000,
      });
    }
  };

  return ReactDOM.createPortal(
    <>
      <div className="popup">
        <div className="popup-content">
          <h1>Edit Employee</h1>
          <div className="generalInfo">
            {!employee && (
              <input
                type="text"
                placeholder="Id"
                value={editedEmployee?.id}
                onChange={(e) => handleFieldChange("id", e.target.value)}
              />
            )}
            <input
              type="text"
              placeholder="First Name"
              value={editedEmployee?.firstName}
              onChange={(e) => handleFieldChange("firstName", e.target.value)}
            />
            <input
              type="text"
              value={editedEmployee?.lastName}
              placeholder="Last Name"
              onChange={(e) => handleFieldChange("lastName", e.target.value)}
            />
            <input
              type="text"
              placeholder="Job Position"
              value={editedEmployee?.jobPosition}
              onChange={(e) => handleFieldChange("jobPosition", e.target.value)}
            />
            <textarea
              placeholder="About me"
              value={editedEmployee?.about}
              onChange={(e) => handleFieldChange("about", e.target.value)}
            ></textarea>
            <input
              type="text"
              placeholder="Email"
              value={editedEmployee?.email}
              onChange={(e) => handleFieldChange("email", e.target.value)}
            />
            <input
              type="text"
              placeholder="Gender"
              value={editedEmployee?.gender}
              onChange={(e) => handleFieldChange("gender", e.target.value)}
            />
            <input
              type="text"
              placeholder="Location"
              value={editedEmployee?.location}
              onChange={(e) => handleFieldChange("location", e.target.value)}
            />
          </div>
          <div className="popupbuttons">
            <button
              onClick={employee ? handleUpdate : handleSave}
              className={`btn ${!validateForm() && "disabled"}`}
              disabled={!validateForm()}
            >
              Save
            </button>
            <button onClick={onClose} className="btn">
              Cancel
            </button>
          </div>
        </div>
      </div>

      <ToastContainer position="top-right" autoClose={2000} />
    </>,
    document.body
  );
};
