import { useState, useEffect } from "react";
import { EmployeeCard } from "./components/EmployeeCard";
import "./App.css";
import { ToolWrapper } from "./components/ToolWrapper";

function App() {
  const [search, setSearch] = useState("");
  const [employees, setEmployees] = useState([]);

  const handleSearch = () => {
    console.log("Search");
  };

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

          <ToolWrapper
            search={search}
            setSearch={setSearch}
            handleSearch={handleSearch}
          />

          <div className="card-wrapper">
            {employees.map((employee) => (
              <EmployeeCard
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
