"use strict";

const appointments = document.getElementById("appointments");
appointments.addEventListener("click", () => {
    let getAppointment = document.getElementById("getAppointment");
    let editAppointment = document.getElementById("editAppointment");
    let editContact = document.getElementById("editContact");
    appointments.classList.add("sidebar__link--active");
    getAppointment.classList.remove("sidebar__link--active");
    editAppointment.classList.remove("sidebar__link--active");
    editContact.classList.remove("sidebar__link--active");
    let apps_icon = document.getElementById("apps_icon");
    let getApp_icon = document.getElementById("getApp_icon");
    let editApp_icon = document.getElementById("editApp_icon");
    let editCon_icon = document.getElementById("editCon_icon");
    apps_icon.classList.add("sidebar__icon--active");
    getApp_icon.classList.remove("sidebar__icon--active");
    editApp_icon.classList.remove("sidebar__icon--active");
    editCon_icon.classList.remove("sidebar__icon--active");
})
const getAppointment = document.getElementById("getAppointment");
getAppointment.addEventListener("click", () => {
    let appointments = document.getElementById("appointments");
    let editAppointment = document.getElementById("editAppointment");
    let editContact = document.getElementById("editContact");
    getAppointment.classList.add("sidebar__link--active");
    appointments.classList.remove("sidebar__link--active");
    editAppointment.classList.remove("sidebar__link--active");
    editContact.classList.remove("sidebar__link--active");
    let apps_icon = document.getElementById("apps_icon");
    let getApp_icon = document.getElementById("getApp_icon");
    let editApp_icon = document.getElementById("editApp_icon");
    let editCon_icon = document.getElementById("editCon_icon");
    getApp_icon.classList.add("sidebar__icon--active");
    apps_icon.classList.remove("sidebar__icon--active");
    editApp_icon.classList.remove("sidebar__icon--active");
    editCon_icon.classList.remove("sidebar__icon--active");
})
const editAppointment = document.getElementById("editAppointment");
editAppointment.addEventListener("click", () => {
    let appointments = document.getElementById("appointments");
    let getAppointment = document.getElementById("getAppointment");
    let editContact = document.getElementById("editContact");
    editAppointment.classList.add("sidebar__link--active");
    appointments.classList.remove("sidebar__link--active");
    getAppointment.classList.remove("sidebar__link--active");
    editContact.classList.remove("sidebar__link--active");
    let apps_icon = document.getElementById("apps_icon");
    let getApp_icon = document.getElementById("getApp_icon");
    let editApp_icon = document.getElementById("editApp_icon");
    let editCon_icon = document.getElementById("editCon_icon");
    editApp_icon.classList.add("sidebar__icon--active");
    apps_icon.classList.remove("sidebar__icon--active");
    getApp_icon.classList.remove("sidebar__icon--active");
    editCon_icon.classList.remove("sidebar__icon--active");
})
const editContact = document.getElementById("editContact");
editContact.addEventListener("click", () => {
    let appointments = document.getElementById("appointments");
    let getAppointment = document.getElementById("getAppointment");
    let editAppointment = document.getElementById("editAppointment");
    editContact.classList.add("sidebar__link--active");
    appointments.classList.remove("sidebar__link--active");
    getAppointment.classList.remove("sidebar__link--active");
    editAppointment.classList.remove("sidebar__link--active");
    let apps_icon = document.getElementById("apps_icon");
    let getApp_icon = document.getElementById("getApp_icon");
    let editApp_icon = document.getElementById("editApp_icon");
    let editCon_icon = document.getElementById("editCon_icon");
    editCon_icon.classList.add("sidebar__icon--active");
    apps_icon.classList.remove("sidebar__icon--active");
    getApp_icon.classList.remove("sidebar__icon--active");
    editApp_icon.classList.remove("sidebar__icon--active");
})

// const colHTML_btn = document.getElementsByClassName("table2__tbody-cell-btn");
// const arr_btn = Array.from(colHTML_btn);
// arr_btn.forEach(e => {
//     e.addEventListener("click", (e) => {
//         e.preventDefault();
//         let full_id = e.target.getAttribute("href");
//         let id = full_id.split("/")[3];
//         window.location.href = `/appointment/assignAppointments/${id}`;
//     })
// });
// document.getElementById("getAppointment-btn").addEventListener("click", (e) => {
//     let full_id = e.target.getAttribute("id");
//     console.log(full_id);
// });
// function accederURL() {
//     window.location.href = "/mi-ruta";
//   }