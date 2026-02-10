const BASE_URL = "http://localhost:8080/employee";

// Load all employees when page loads
window.onload = function () {
    getAllEmployees();
};

// ---------------- Add Employee ----------------
function addEmployee() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const salary = document.getElementById("salary").value;

    if (!name || !email || !salary) {
        alert("Please fill all fields");
        return;
    }

    const employee = { name, email, salary };

    fetch(`${BASE_URL}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(employee)
    })
    .then(response => response.json())
    .then(data => {
        alert("Employee added successfully");
        clearForm();
        getAllEmployees();
    })
    .catch(error => console.error(error));
}

// Clear input form after adding
function clearForm() {
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("salary").value = "";
}

// ---------------- Get All Employees ----------------
function getAllEmployees() {
    fetch(`${BASE_URL}`)
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("employeeTableBody");
            tableBody.innerHTML = "";

            const employees = data.t; // important: your backend array is 't'

            if (!employees || employees.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="5">No employees found</td></tr>`;
                return;
            }

            employees.forEach(emp => {
                const row = `
                    <tr>
                        <td>${emp.id}</td>
                        <td>${emp.name}</td>
                        <td>${emp.email}</td>
                        <td>${emp.salary}</td>
                        <td>
                            <button onclick="deleteEmployee(${emp.id})">Delete</button>
                            <button onclick="updateEmployee(${emp.id}, '${emp.name}', '${emp.email}', ${emp.salary})">Update</button>
                        </td>
                    </tr>
                `;
                tableBody.innerHTML += row;
            });
        })
        .catch(error => console.error(error));
}

// ---------------- Delete Employee ----------------
function deleteEmployee(id) {
    if (!confirm("Are you sure you want to delete this employee?")) return;

    fetch(`${BASE_URL}/${id}`, { method: "DELETE" })
        .then(() => getAllEmployees())
        .catch(error => console.error(error));
}

// ---------------- Update Employee ----------------
function updateEmployee(id, currentName, currentEmail, currentSalary) {
    const name = prompt("Enter new name:", currentName);
    const email = prompt("Enter new email:", currentEmail);
    const salary = prompt("Enter new salary:", currentSalary);

    if (!name || !email || !salary) {
        alert("Update cancelled or invalid values");
        return;
    }

    const updatedEmployee = { name, email, salary };

    fetch(`${BASE_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedEmployee)
    })
    .then(response => response.json())
    .then(() => getAllEmployees())
    .catch(error => console.error(error));
}
