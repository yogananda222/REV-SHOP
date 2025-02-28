// Document Ready Event
document.addEventListener("DOMContentLoaded", function () {
    initializeForm();
});

// Initialize Form on Load
function initializeForm() {
    generateCaptcha();  // Generate CAPTCHA
    attachEventListeners();  // Attach validation to fields
    console.log("Session Message: ", sessionMessage);  // Debugging session message
    if (sessionMessage) showPopup(sessionMessage);  // Show session message if exists
    initializeModal();
}

// Generate CAPTCHA
function generateCaptcha() {
    const captcha = Math.random().toString(36).substring(2, 8).toUpperCase();
    document.getElementById("capt").value = captcha;
}

// Refresh CAPTCHA
function refreshCaptcha() {
    generateCaptcha();
}

// Open Terms and Conditions Modal
function openTermsModal() {
    document.getElementById("termsModal").style.display = "block";
}

// Close Modal when clicking outside of it
window.onclick = function(event) {
    const modal = document.getElementById("termsModal");
    if (event.target === modal) modal.style.display = "none";
};

// Initialize Modal Functions
function initializeModal() {
    const closeBtn = document.querySelector(".close");
    closeBtn.onclick = () => document.getElementById("termsModal").style.display = "none";

    const agreeButton = document.getElementById("agreeButton");
    agreeButton.onclick = () => {
        document.getElementById("termsModal").style.display = "none";
        document.getElementById("checkbox").checked = true;
    };
}

// Validation Functions

// Validate CAPTCHA
function validateCaptcha() {
    const captchaInput = document.getElementById("captcha-input").value;
    const captchaValue = document.getElementById("capt").value;
    const captchaError = document.getElementById("captchaError"); // Assuming you have a captcha error element
    if (captchaInput !== captchaValue) {
        captchaError.innerText = "Invalid CAPTCHA entered.";
        return false;
    } else {
        captchaError.innerText = "";
        return true;
    }
}

// Username Validation
function validateUsername() {
    const username = document.getElementById("username").value;
    const usernameError = document.getElementById("usernameError");

    if (username.length < 4) {
        usernameError.innerText = "Username must be at least 4 characters long.";
        return false;
    } else {
        usernameError.innerText = "";
        return true;
    }
}

// Email Validation
function validateEmail() {
    const email = document.getElementById("email").value;
    const emailError = document.getElementById("emailError");
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

    if (!emailPattern.test(email)) {
        emailError.innerText = "Please enter a valid email address.";
        return false;
    } else {
        emailError.innerText = "";
        return true;
    }
}

// Password Validation
function validatePassword() {
    const password = document.getElementById("password").value;
    const passwordError = document.getElementById("passwordError");
    const passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$/;

    if (!passwordPattern.test(password)) {
        passwordError.innerText = "Password must include a number, uppercase letter, and special character.";
        return false;
    } else {
        passwordError.innerText = "";
        return true;
    }
}

// Contact Number Validation
function validateContactNo() {
    const contactNo = document.getElementById("contactNo").value;
    const contactNoError = document.getElementById("contactNoError");
    const contactNoPattern = /^\d{10}$/;

    if (!contactNoPattern.test(contactNo)) {
        contactNoError.innerText = "Please enter a valid 10-digit mobile number.";
        return false;
    } else {
        contactNoError.innerText = "";
        return true;
    }
}

// Business Name Validation
function validateBusinessName() {
    const businessName = document.getElementById("businessName").value;
    const businessNameError = document.getElementById("businessNameError");

    if (businessName.length < 3) {
        businessNameError.innerText = "Business name must be at least 3 characters long.";
        return false;
    } else {
        businessNameError.innerText = "";
        return true;
    }
}

// GSTIN Validation
function validateGSTIN() {
    const gstin = document.getElementById("GSTIN").value;
    const gstinError = document.getElementById("gstinError");
    const gstinPattern = /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$/;

    if (gstin.length > 0 && !gstinPattern.test(gstin)) {
        gstinError.innerText = "Please enter a valid GSTIN.";
        return false;
    } else {
        gstinError.innerText = "";
        return true;
    }
}

// Pincode Validation
function validatePincode() {
    const pincode = document.getElementById("pincode").value;
    const pincodeError = document.getElementById("pincodeError");
    const pincodePattern = /^\d{6}$/;

    if (!pincodePattern.test(pincode)) {
        pincodeError.innerText = "Please enter a valid 6-digit pincode.";
        return false;
    } else {
        pincodeError.innerText = "";
        return true;
    }
}

// Handle Form Submission
function handleSubmit(event) {
    event.preventDefault();

    // Validate all fields
    const usernameValid = validateUsername();
    const emailValid = validateEmail();
    const passwordValid = validatePassword();
    const contactNoValid = validateContactNo();
    const businessNameValid = validateBusinessName();
    const gstinValid = validateGSTIN();
    const pincodeValid = validatePincode();
    const captchaValid = validateCaptcha();

    // Check if all validations passed
    if (usernameValid && emailValid && passwordValid && contactNoValid && businessNameValid && gstinValid && pincodeValid && captchaValid) {
        document.getElementById("registrationForm").submit();
    } else {
        showPopup("Please fill in all fields correctly.");
    }
}

// Assuming you have a showPopup function defined somewhere to display messages.

// Show Popup for Error Messages
function showPopup(message) {
    const popup = document.createElement('div');
    popup.classList.add('popup-message');
    popup.style = `
        position: fixed;
        top: 20px;
        right: 20px;
        background-color: #ffdddd;
        border: 1px solid red;
        padding: 10px;
        z-index: 1000;
        color: red;
        font-weight: bold;
    `;
    popup.innerText = message;
    document.body.appendChild(popup);
    setTimeout(() => popup.remove(), 5000);
}

// Attach Validation Functions on Field Events
function attachEventListeners() {
    document.getElementById("email").addEventListener("keyup", validateEmail);
    document.get
}