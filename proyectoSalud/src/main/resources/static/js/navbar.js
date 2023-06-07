"use strict";

const dropdown_icon = document.getElementById("navbar__useradmin-icon");
dropdown_icon.addEventListener("click", () => {
    let nav_dropdown = document.getElementById("navbar__dropdown");
    nav_dropdown.classList.toggle("navbar__dropdown--d-none");
    nav_dropdown.classList.toggle("navbar__dropdown--active");
    dropdown_icon.classList.toggle("fa-circle-chevron-down");
    dropdown_icon.classList.toggle("fa-circle-chevron-up");
})

const navbar__icon = document.getElementById("navbar__icon");
navbar__icon.addEventListener("click", () => {
    navbar__icon.classList.toggle("fa-bars");
    navbar__icon.classList.toggle("fa-bars-staggered");

    let nav_links = document.getElementById("navbar__links");
    let nav_contDropdown = document.getElementById("navbar__cont-dropdown");
    nav_links.classList.toggle("navbar--active");
    nav_contDropdown.classList.toggle("navbar--active");

    let responsiveDropdown = document.getElementById("navbar__cont-sd");
    responsiveDropdown.classList.toggle("navbar__cont-sd--border");
})

addHoverLastChild();
function addHoverLastChild() {
    const nav_dropdown = document.getElementById("navbar__dropdown");
    nav_dropdown.lastElementChild.children[0].classList.add("navbar__dropdown-link--hover");
}