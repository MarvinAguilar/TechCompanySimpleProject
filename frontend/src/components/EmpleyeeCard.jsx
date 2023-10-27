import CardBackground from "../assets/card-bg.jpg";

export const EmpleyeeCard = ({ employee, setEmployees }) => {
  const profilePicture = `https://ui-avatars.com/api/?name=${employee?.firstName}+${employee?.lastName}&background=0D8ABC&color=fff&size=128`;

  const handleDeleteEmployee = async (employeeId) => {
    try {
      // Make a DELETE request to the server to delete the employee
      const url = `http://localhost:8085/api/v1/techcompany/employee/${employeeId}`;
      const response = await fetch(url, {
        method: "DELETE",
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      // Remove the deleted employee from the local state
      setEmployees((prevEmployees) =>
        prevEmployees.filter((employee) => employee.id !== employeeId)
      );
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="card">
      <img src={CardBackground} alt="card background" className="card-img" />

      <img src={profilePicture} alt="profile image" className="profile-img" />

      <h1>{`${employee?.firstName} ${employee?.lastName}`}</h1>

      <p className="job-title">{employee?.jobPosition}</p>

      <p className="about">{employee.about}</p>

      <a href="#" className="btn">
        View More
      </a>

      <ul className="social-media">
        <li>
          <a href="#">
            <i className="fa-solid fa-pen-to-square"></i>
          </a>
        </li>
        <li>
          <a href="#" onClick={() => handleDeleteEmployee(employee.id)}>
            <i className="fa-solid fa-trash"></i>
          </a>
        </li>
      </ul>
    </div>
  );
};
