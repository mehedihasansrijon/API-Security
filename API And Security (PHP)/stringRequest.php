<?php
// Retrieve the 'name' and 'pass' fields from the POST request
$name = $_POST['name'];
$pass = $_POST['pass'];

// Check if the provided password matches the predefiend password '5522333'
if ($pass=='552233') {
	// If the password is correct, output the user's name
	echo "Your name is " . $name;
} else {
	// If the password is incorrect, display and unauthorized message
	echo "Unauthorized";
}
?>