import ReactDOM from "react-dom";
import "./EmployeeInfoPopup.css";

export const EmployeeInfoPopup = ({ employee, onClose }) => {
  const profilePicture = `https://ui-avatars.com/api/?name=${employee?.firstName}+${employee?.lastName}&background=0D8ABC&color=fff&size=128`;

  return ReactDOM.createPortal(
    <div className="popup">
      <div className="popup-content">
        <img src={profilePicture} alt="profile image" className="profile-img" />

        <h1>{`${employee.firstName} ${employee.lastName}`}</h1>

        <p className="job-title">{employee.jobPosition}</p>

        <p className="about">
          <b>About me:</b> {employee.about}
        </p>

        <div className="generalInfo">
          <p>
            <b>Id: </b> {employee.id}
          </p>

          <p>
            <b>Email: </b> {employee.email}
          </p>

          <p>
            <b>Gender: </b> {employee.gender}
          </p>

          <p>
            <b>Location: </b> {employee.location}
          </p>
        </div>

        <div className="popupbuttons">
          <button onClick={onClose} className="btn">
            Close
          </button>
        </div>
      </div>
    </div>,
    document.body
  );
};
