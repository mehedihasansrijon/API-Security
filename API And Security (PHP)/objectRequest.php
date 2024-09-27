<?php
// Get the row JSON input from the request
$json = file_get_contents('php://input');

// Decode the JSON input into a PHP associative array
$data = json_decode($json, true);

// Extract the 'name' and 'pass' values from the decodeed data
$name = $data['name'];
$pass = $data['pass'];

// Initialize an array to hold the response data
$temp = array();

// Check if provided password matches the predefined password '578456'
if ($pass==578456){
	// If the password is correct, set the response to 'success' and include the name
	$temp['status'] = 'success';
	$temp['data'] = $name;
} else {
	// If the password is incorrect, set response to 'failed' and include an unauthorized message
	$temp['status'] = 'failed';
	$temp['data'] = 'Unauthorized';
}

// Encode the response array to JSON and output it
echo json_encode($temp);

?>