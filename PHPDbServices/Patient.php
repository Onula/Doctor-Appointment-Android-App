<?php
//Insert the function connectToDatabase()!( Database connection )
require 'dbConnection.php';

// Get the action parameter from the request
$action = $_GET['action'];
//Check the action and perform the corresponding operation
if ($action === 'insertUnConfirmedVisit') {
    insertUnConfirmedVisit();
}elseif ($action === 'getPatientMovement') {
    getPatientMovement();
}else echo "Invalid action";
function insertUnConfirmedVisit(){
    global $connection ;
    
    //Retrieve the Physiotherapy data from the request
    $AMKA = $_GET['PatientAMKA']; 
    $doctorID = $_GET['DoctorID'];
    $date = $_GET['VisitDate'];
    $sTime = $_GET['StartTime'];
    $eTime = $_GET['EndTime'];
    $confirmed = $_GET['VisitConfirmed'];
    $provisonID = $_GET['ProvisionID'];

    // Check for overlapping visits
    $query = "SELECT COUNT(*) FROM visit 
              WHERE StartTime <= '$eTime' AND EndTime >= '$sTime'";
    $result = mysqli_query($connection, $query);
    $count = mysqli_fetch_row($result)[0];

    
    if ($count > 0) {// Overlapping visit found
        echo "Overlapping visit found";
    } else {
        // Perform the insertion into the Physiotherapy table
        $query = "INSERT INTO visit (PatientAMKA, ProvisionID, DoctorID, VisitDate, StartTime, EndTime, VisitConfirmed) 
        VALUES (?, ?, ?, ?, ?, ?, ?)";
        // Prepare the statement
        $stmt = mysqli_prepare($connection, $query);
        // Bind parameters to the statement
        mysqli_stmt_bind_param($stmt, "ssssssi", $AMKA, $provisonID , $doctorID, $date, $sTime, $eTime, $confirmed);
        // Execute the statement
        $result = mysqli_stmt_execute($stmt);
        // Print the insertion result
        echo $result;
        
    }
}
function getPatientMovement(){
    global $connection ;
    
    //Retrieve the Physiotherapy data from the request
    $AMKA = $_GET['PatientAMKA']; 
    // Create an array to store the results
    $data = array();
    // SQL query to fetch visit date and provision name for the specific patient
    $query = "SELECT provision.ProvisionName AND provision.ProvisionPrice
                FROM provision
                JOIN visit ON visit.ProvisionID = provision.provisionID
                WHERE visit.PatientAMKA = ? ";
    $stmt = mysqli_prepare($connection, $query);
    mysqli_stmt_bind_param($stmt, "i", $AMKA);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    // Fetch the results and add them to the array
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }
    header("Content-Type: application/json");
    // Output the array as JSON
    echo json_encode($data);
}

?>