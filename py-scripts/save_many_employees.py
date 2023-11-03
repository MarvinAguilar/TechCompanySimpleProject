import json
import requests

# run "pip install requests" to install the request module

# URL of the endpoint to add many employees
url = "http://localhost:8085/api/v1/techcompany/addEmployees"

# JSON file containing an array of employees
json_file = "employees.json"


def add_employees_from_json(file_path):
    try:
        with open(file_path, "r") as json_file:
            employees = json.load(json_file)

            response = requests.post(url, json=employees)

            if response.status_code == 201:
                print(f"Employees added successfully. IDs: {response.json()}")
            else:
                print(f"Error adding employees: {response.text}")

    except FileNotFoundError:
        print(f"The JSON file '{file_path}' was not found.")
    except Exception as e:
        print(f"Error: {e}")


if __name__ == "__main__":
    add_employees_from_json(json_file)
