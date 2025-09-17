<?php
//Insert the function connectToDatabase()!( Database connection )
require 'dbConnection.php';

// Get the action parameter from the request
$action = $_GET['action'];
//Check the action and perform the corresponding operation
if ($action === 'getUser') {
    getUser();
}else echo "Invalid action";

function getUser() {
    global $connection;
    $username = $_GET['UserName'];
    $password = $_GET['Password'];

    $query = "SELECT * 
            FROM user
            WHERE user.Username = ? AND user.Password =?
            LIMIT 1";
    $stmt = mysqli_prepare($connection, $query);
    mysqli_stmt_bind_param($stmt, "ss", $username, $password);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);    
    
    $row = mysqli_fetch_assoc($result);
    header("Content-Type: application/json");
     // Output the user as JSON
    echo json_encode($row);

}




?>