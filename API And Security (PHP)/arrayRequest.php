<?php
// Get the row JSON from the request
$json = file_get_contents('php://input');

// Decode the JSON input into a PHP accociative array
$jsonArray = json_decode($json, true);

// Access the first element of the decoded array
$data = $jsonArray[0];

// Extract the 'name' and 'pass' values from the first element
$name = $data['name'];
$pass = $data['pass'];

// Initialize an array to hold the main response
$mainArray = array();

// Initialize an array to hold the response object
$object = array();

// Check if the provided password matches the predefined password '1144777'
if ($pass=='1144777') {
	// If the password is correct, set the response status to 'success' and include the name
	$object['status'] = 'success';
	$object['data'] = $name;
} else {
	// If the password is incorrect, set the response status to 'failed' and include an unauthorized message 
	$object['status'] = 'failed';
	$object['data'] = 'Unauthorized';
}

// Add to response object to main array
array_push($mainArray, $object);

// Encode the main array as JSON and output it
echo json_encode ($mainArray);
?>