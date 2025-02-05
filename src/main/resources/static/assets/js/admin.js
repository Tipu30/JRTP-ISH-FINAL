var form = document.getElementById("myForm"),
    planId = document.getElementById("planId"),
    planCategory = document.getElementById("planCategory"),
    planName = document.getElementById("planName"),
    startDate = document.getElementById("startDate"),
    endDate = document.getElementById("endDate"),
    submitBtn = document.querySelector(".submit"),
    userInfo = document.getElementById("data"),
    modal = document.getElementById("userForm"),
    modalTitle = document.querySelector("#userForm .modal-title"),
    newPlanBtn = document.querySelector(".newUser");

let getData = localStorage.getItem('insurancePlans') ? JSON.parse(localStorage.getItem('insurancePlans')) : [
    // Pre-defined data
    {
        planId: '001',
        planCategory: 'Basic',
        planName: 'SNAP',
        startDate: '2025-01-01',
        endDate: '2026-01-01'
    },
    {
        planId: '002',
        planCategory: 'Premium',
        planName: 'CCAP',
        startDate: '2025-02-01',
        endDate: '2026-02-01'
    }
];

let isEdit = false, editId;
showInfo();

newPlanBtn.addEventListener('click', () => {
    submitBtn.innerText = 'Submit';
    modalTitle.innerText = "Fill the Form";
    isEdit = false;
    form.reset();
});

function showInfo() {
    userInfo.innerHTML = '';
    getData.forEach((element, index) => {
        let createElement = `<tr class="planDetails">
            <td>${element.planId}</td>
            <td>${element.planCategory}</td>
            <td>${element.planName}</td>
            <td>${element.startDate}</td>
            <td>${element.endDate}</td>
            <td>
                <button class="btn btn-warning" onclick="editInfo(${index}, '${element.planId}', '${element.planCategory}', '${element.planName}', '${element.startDate}', '${element.endDate}')" data-bs-toggle="modal" data-bs-target="#userForm">Update</button>
                <button class="btn btn-danger" onclick="deleteInfo(${index})">Delete</button>
            </td>
        </tr>`;
        userInfo.innerHTML += createElement;
    });
}

function editInfo(index, planId, planCategory, planName, startDate, endDate) {
    isEdit = true;
    editId = index;
    document.getElementById("planId").value = planId;
    document.getElementById("planCategory").value = planCategory;
    document.getElementById("planName").value = planName;
    document.getElementById("startDate").value = startDate;
    document.getElementById("endDate").value = endDate;

    submitBtn.innerText = "Update";
    modalTitle.innerText = "Update The Form";
}

function deleteInfo(index) {
    if (confirm("Are you sure you want to delete this plan?")) {
        getData.splice(index, 1);
        localStorage.setItem("insurancePlans", JSON.stringify(getData));
        showInfo();
    }
}

form.addEventListener('submit', (e) => {
    e.preventDefault();

    const startDateValue = new Date(startDate.value);
    const endDateValue = new Date(endDate.value);

    if (endDateValue < startDateValue) {
        alert("End date cannot be earlier than start date.");
        return;
    }

    const information = {
        planId: document.getElementById("planId").value,
        planCategory: document.getElementById("planCategory").value,
        planName: document.getElementById("planName").value,
        startDate: document.getElementById("startDate").value,
        endDate: document.getElementById("endDate").value
    };

    if (!isEdit) {
        getData.push(information);
    } else {
        isEdit = false;
        getData[editId] = information;
    }

    localStorage.setItem('insurancePlans', JSON.stringify(getData));

    submitBtn.innerText = "Submit";
    modalTitle.innerText = "Fill The Form";

    showInfo();

    form.reset();

    // Close the modal
    var modalEl = document.querySelector("#userForm");
    var modalInstance = bootstrap.Modal.getOrCreateInstance(modalEl);
    modalInstance.hide();
});
