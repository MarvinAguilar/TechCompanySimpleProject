import { useState } from "react";
import { EmployeeSavePopup } from "./EmployeeSavePopup";

export const ToolWrapper = ({ search, setSearch, handleSearch }) => {
  const [isSavePopupOpen, setSavePopupOpen] = useState(false);

  const handleOpenSavePopup = () => {
    setSavePopupOpen(true);
  };

  const handleCloseSavePopup = () => {
    setSavePopupOpen(false);
  };

  return (
    <>
      <div className="tool-wrapper">
        <input
          type="text"
          placeholder="Search Employees"
          className="search-bar"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
        <button onClick={handleSearch} className="search-button">
          <i className="fa-solid fa-magnifying-glass"></i> Search
        </button>
        <button onClick={handleOpenSavePopup} className="add-button">
          <i className="fa-solid fa-plus"></i> Add Employee
        </button>
      </div>

      {isSavePopupOpen && <EmployeeSavePopup onClose={handleCloseSavePopup} />}
    </>
  );
};
