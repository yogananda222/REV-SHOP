
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Account - REVSHOP</title>
<link rel="stylesheet" href="css/createAccount.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body onload="hideMessageAfterTimeout()">

	<main>
		<div class="content-container">
			<!-- Left side: Logo -->
			<div class="left-column">
				<img src="/Images/LOGO.png" alt="Logo" class="left-logo">
			</div>

			<!-- Right side: Form Section -->
			<section class="form-section">
				<div class="progress-indicators"
					style="display: flex; justify-content: center; margin-bottom: 20px;">
					<div class="indicator active"
						style="text-align: center; flex: 1; font-size: 24px; color: black; font-weight: bold;">
						<span>Login</span>
					</div>
				</div>

				<!-- Session message placeholder -->
				<div id="message-container"
					style="color: red; font-weight: bold; text-align: center;">
					<c:if test="${not empty sessionScope.message}">
                    ${sessionScope.message}
                </c:if>
				</div>

				<div class="form-container">
					<form action="${pageContext.request.contextPath}/loginSeller"
						method="post">
						<input type="email" id="email" name="email"
							placeholder="Enter your email" onkeyup="validateEmail()" required>
						<span id="emailError" class="error-message"></span> <input
							type="password" id="password" name="password"
							placeholder="Password" required>
						<button type="submit" id="login" class="btn btn-primary btn-block">
							<b>Login</b>
						</button>
					</form>
					<p class="login-link">
						Don't have an account? <a href="createAccount.jsp">Create
							Account</a>
					</p>
				</div>
			</section>
		</div>
	</main>
	<script>
        // Function to hide the message after 5 seconds
        function hideMessageAfterTimeout() {
            const messageContainer = document.getElementById("message-container");
            if (messageContainer && messageContainer.innerText.trim() !== "") {
                // Set a timeout to clear the message after 5 seconds
                setTimeout(() => {
                    messageContainer.style.display = "none";
                }, 5000); // 5000 ms = 5 seconds
            }
        }
    </script>
	<script src="js/createAccount.js"></script>

</body>
</html>
