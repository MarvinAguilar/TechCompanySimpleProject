import { useState, useEffect } from "react";
import axios from "axios";
import { EmployeeCard } from "./components/EmployeeCard";
import { ToolWrapper } from "./components/ToolWrapper";
import "./App.css";

function App() {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const url = "http://localhost:8085/api/v1/techcompany/employees";
        const response = await axios.get(url);

        if (response.status === 200) {
          setEmployees(response.data);
        } else {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, []);

  return (
    <>
      <section>
        <div className="container">
          <h1 className="heading">OUR EXCEPTIONAL TEAM</h1>

          <ToolWrapper setEmployees={setEmployees} />

          {employees.length > 0 ? (
            <div className="card-wrapper">
              {employees.map((employee) => (
                <EmployeeCard
                  key={employee.id}
                  employee={employee}
                  setEmployees={setEmployees}
                />
              ))}
            </div>
          ) : (
            <div className="not-found">
              <h1>No employees were found.</h1>
            </div>
          )}
        </div>
      </section>
    </>
  );
}

export default App;
