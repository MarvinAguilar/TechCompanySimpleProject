import { useEffect, useState } from "react";
import axios from "axios";
import { EmployeeSavePopup } from "./EmployeeSavePopup";

export const ToolWrapper = ({ setEmployees, fetchEmployees }) => {
  const [searchQuery, setSearchQuery] = useState("");
  const [isSavePopupOpen, setSavePopupOpen] = useState(false);

  const handleOpenSavePopup = () => {
    setSavePopupOpen(true);
  };

  const handleCloseSavePopup = () => {
    setSavePopupOpen(false);
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const encodedSearchQuery = encodeURIComponent(searchQuery);

        const url = `http://localhost:8085/api/v1/techcompany/employee/search?query=${encodedSearchQuery}`;
        console.log(url);
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

    if (searchQuery) {
      fetchData();
    } else {
      /* setEmployees([]); */
    }
  }, [searchQuery, setEmployees]);

  const handleSearchInputChange = (event) => {
    setSearchQuery(event.target.value);
  };

  return (
    <>
      <div className="tool-wrapper">
        <input
          type="text"
          placeholder="Search Employees | Find by { id, name, email, position, gender, location }"
          className="search-bar"
          value={searchQuery}
          onChange={handleSearchInputChange}
          onKeyDown={(e) => {
            if (e.key === "Enter") {
              setSearchQuery(searchQuery);
            }
          }}
        />
        <button
          onClick={() => setSearchQuery(searchQuery)}
          className="search-button"
        >
          <i className="fa-solid fa-magnifying-glass"></i> Search
        </button>
        <button onClick={handleOpenSavePopup} className="add-button">
          <i className="fa-solid fa-plus"></i> Add Employee
        </button>
      </div>

      {isSavePopupOpen && (
        <EmployeeSavePopup
          onClose={handleCloseSavePopup}
          fetchEmployees={fetchEmployees}
        />
      )}
    </>
  );
};
