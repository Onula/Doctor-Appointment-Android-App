<?php
// Database connection details
$host = "localhost";
$user = "root";
$password = "";
$database = "physiotherapy";
// Establishing connection to the database
$connection = mysqli_connect($host, $user, $password, $database) or die("Connection failed: " . mysqli_connect_error());



?>