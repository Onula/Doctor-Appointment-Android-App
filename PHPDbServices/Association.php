<?php
//Insert the function connectToDatabase()!( Database connection )
require 'dbConnection.php';

// Get the action parameter from the request
$action = $_GET['action'];
//Check the action and perform the corresponding operation
if ($action === 'insertPhysiotherapy') {
    insertPhysiotherapy();
}elseif ($action === 'insertProvision') {
    insertProvision();
}else echo "Invalid action";
function insertPhysiotherapy(){
    global $connection ;
    
    //Retrieve the Physiotherapy data from the request
    $AFM = $_GET['PhysiotherapyAFM'];
    $name = $_GET['PhysiotherapyName'];
    $adress = $_GET['PhysiotherapyAdress']; 
    // Perform the insertion into the Physiotherapy table
    $query = "INSERT INTO physiotherapy (PhysiotherapyAFM, PhysiotherapyName, PhysiotherapyAdress) 
              VALUES (?, ?, ?)";
    // Prepare the statement
    $stmt = mysqli_prepare($connection, $query);
    // Bind parameters to the statement
    mysqli_stmt_bind_param($stmt, "sss", $AFM, $name, $adress);
    // Execute the statement
    $result = mysqli_stmt_execute($stmt);
    // Print the insertion result
    echo $result;
}

function insertProvision(){
    global $connection ;
    // Retrieve the Provision data from the request
    $code = $_GET['ProvisionID'] ;
    $name = $_GET['ProvisionName'] ;
    $price = $_GET['ProvisionPrice']; 
    $description =$_GET['ProvisionDescription'] ;
    // Prepare the SQL statement with placeholders
    $query = "INSERT INTO provision (ProvisionID, ProvisionName, ProvisionPrice, ProvisionDescription)
             VALUES (?, ?, ?, ?)";
    // Prepare the statement
    $stmt = mysqli_prepare($connection, $query);
    // Bind parameters to the statement
    mysqli_stmt_bind_param($stmt, "ssis", $code, $name, $price, $description);
    // Execute the statement
    $result = mysqli_stmt_execute($stmt);
    // Print the insertion result
    echo $result;
}
// Print the insertion result

?>