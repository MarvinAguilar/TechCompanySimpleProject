import { useState, useEffect } from "react";
import { EmpleyeeCard } from "./components/EmpleyeeCard";
import "./App.css";

function App() {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const url = "http://localhost:8085/api/v1/techcompany/employees";
        const response = await fetch(url);
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json();
        setEmployees(data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchData();
  }, [employees]);

  return (
    <>
      <section>
        <div className="container">
          <h1 className="heading">OUR EXCEPTIONAL TEAM</h1>

          <div className="card-wrapper">
            {employees.map((employee) => (
              <EmpleyeeCard
                key={employee.id}
                employee={employee}
                setEmployees={setEmployees}
              />
            ))}
          </div>
        </div>
      </section>
    </>
  );
}

export default App;
