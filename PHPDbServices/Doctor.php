<?php
//Insert the function connectToDatabase()!( Database connection )
require 'dbConnection.php';

// Get the action parameter from the request
$action = $_GET['action'];

//Check the action and perform the corresponding operation
if ($action === 'insertPatient') {
    insertPatient();
}elseif ($action === 'getPatientHistory') {
    getPatientHistory();
}elseif ($action === 'getPatientWithAMKA') {
    getPatientWithAMKA();
}elseif ($action === 'getWeeklyConfirmedVisits') {
    getWeeklyConfirmedVisits();
}elseif ($action === 'getAllUnConfirmedVisits') {
    getAllUnConfirmedVisits();
}elseif ($action === 'insertVisit') {
    insertVisit();
}elseif ($action === 'setVisitConfirmed') {
    setVisitConfirmed();
}elseif ($action === 'getDoctorActivity') {
    getDoctorActivity();
}elseif ($action === 'getAllPatients') {
    getAllPatients();
}else echo "Invalid action";

//R3////////////////////////////////////////////////////////////////
function insertPatient(){
    global $connection ;
    
    //Retrieve the Physiotherapy data from the request
    $firstName = $_GET['PatientFirstname'];
    $lastName = $_GET['PatientLastname'];
    $address = $_GET['PatientAddress'];
    $AMKA = $_GET['PatientAMKA']; 
    $DoctorID = $_GET['DoctorID'];
    
    
    // Perform the insertion into the Physiotherapy table
    $query = "INSERT INTO patient (PatientFirstname,PatientLastname, PatientAddress, PatientAMKA) 
              VALUES (?, ?, ?, ?)";
              //Insert automatically the also the username and password for login
    
    // Prepare the statement
    $stmt = mysqli_prepare($connection, $query);
    // Bind parameters to the statement
    mysqli_stmt_bind_param($stmt, "sssi", $firstName, $lastName, $address, $AMKA );
    // Execute the statement
    $result = mysqli_stmt_execute($stmt);
    //Adding the insertion to the doctor activity table
    if ($result){   
    addDoctorActivity($DoctorID, "ΠΡΟΣΘΗΚΗ ΑΣΘΕΝΗ: ".$firstName.$lastName. "\r\n AMKA: " .$AMKA);
        
    }
    // Print the insertion result
    printInsertResult($result);

}
//R8
function insertVisit(){
    global $connection ;
    
    //Retrieve the Physiotherapy data from the request
    $AMKA = $_GET['PatientAMKA']; 
    $doctorID = $_GET['DoctorID'];
    $date = $_GET['VisitDate'];
    
    $sTime = $_GET['StartTime'];
    $eTime = $_GET['EndTime'];
    $confirmed = 1;
    $provisonID = $_GET['ProvisionID'];

    // Check for overlapping visits
    $query = "SELECT COUNT(*) 
              FROM visit 
              WHERE VisitDate ='$date' AND((visit.StartTime > '$sTime' AND visit.StartTime < '$eTime') OR (visit.EndTime > '$sTime' AND visit.EndTime < '$eTime'))";
    $result = mysqli_query($connection, $query);
    $count = mysqli_fetch_row($result)[0];

    
    if ($count > 0) {// Overlapping visit found
        echo "In the same time visit found";
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
        printInsertResult($result);
        //Adding the insertion to the doctor activity table
        if ($result){
            addDoctorActivity($doctorID, "ΠΡΟΣΘΗΚΗ ΕΠΙΣΚΕΨΗΣ ME \r\nAMKA: ".$AMKA );
            
        }
    }
    
}
function printInsertResult($result) {
    if ($result) {
        echo $result;
    } else {
        echo "Failed to insert into table";
    }
}
//R8////////////////////////////////////////////////////////////////
function getPatientHistory(){
    global $connection;
    $AMKA = $_GET['PatientAMKA'];
    // Create an array to store the results
    $data = array();
    // SQL query to fetch visit date and provision name for the specific patient
    $query = "SELECT visit.VisitDate, provision.ProvisionName
                FROM visit
                JOIN provision ON visit.ProvisionID = provision.provisionID
                WHERE visit.PatientAMKA = ? AND visit.VisitConfirmed = 1";
    $stmt = mysqli_prepare($connection, $query);
    mysqli_stmt_bind_param($stmt, "s", $AMKA);
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
//R5////////////////////////////////////////////////////////////////
function getPatientWithAMKA(){
    global $connection;

    $AMKA = $_GET['PatientAMKA'];

    $query = "SELECT * FROM patient WHERE PatientAMKA = ?";
    $stmt = mysqli_prepare($connection, $query);
    mysqli_stmt_bind_param($stmt, "s", $AMKA);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);
    
    $row = mysqli_fetch_assoc($result);

    header("Content-Type: application/json");
    echo json_encode($row);
   
}

function getAllPatients(){
    global $connection;
    // Create an array to store the results
    $data = array();
    // SQL query to fetch visit date and provision name for the specific patient
    $query = "SELECT *
                FROM patient";
    $stmt = mysqli_prepare($connection, $query);
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
//R6////////////////////////////////////////////////////////////////
function getWeeklyConfirmedVisits(){
    global $connection;
    $doctorID = $_GET['DoctorID'];
    $startOfWeek = $_GET['StartOfWeek'];
    $EndOfWeek = $_GET['EndOfWeek'];
    
    // Create an array to store the results
    $data = array();
    // Calculate the start and end dates of the specified week
    $query = "SELECT *
          FROM visit
          WHERE visit.DoctorID = ? AND visit.VisitConfirmed = 1 AND visit.VisitDate BETWEEN ? AND ? ";
    $stmt = mysqli_prepare($connection, $query);
    mysqli_stmt_bind_param($stmt, "sss", $doctorID,  $startOfWeek, $EndOfWeek);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    $data = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }

    header("Content-Type: application/json");
     // Output the array as JSON
    echo json_encode($data); 
    
}
function setVisitConfirmed(){
    global $connection;
    $visitID = $_GET['VisitID'];

    $query = "UPDATE visit
              SET VisitConfirmed = 1
              WHERE VisitID = ?";

    $stmt = mysqli_prepare($connection, $query);
    // Bind parameters to the statement
    mysqli_stmt_bind_param($stmt, "s", $visitID );
    // Execute the statement
    $result = mysqli_stmt_execute($stmt);
    //Adding to the doctor activity
    if($result) {
        $query2 = "SELECT patient.PatientAMKA, patient.PatientFirstName, patient.PatientLastName, visit.DoctorID
                    FROM patient
                    JOIN visit ON patient.PatientAMKA = visit.PatientAMKA
                    WHERE visit.VisitID = ?";
        $result = mysqli_query($connection, $query2);
        $data = mysqli_fetch_assoc($result);
        
        addDoctorActivity($data['DoctorID'], "ΑΠΟΔΟΧΗ ΕΠΙΣΚΕΨΗΣ ME: "+ $data['PatientFirstName']+" "+$data['PatientLastName']+" \r\nAMKA:" + $data['PatientAMKA']);
    }
    // Print the insertion result
    echo $result;

}
//R7////////////////////////////////////////////////////////////////
function getAllUnConfirmedVisits(){
    global $connection;
    
    $query = "SELECT *
                FROM visit
                WHERE visit.DoctorID = ? AND visit.VisitConfirmed = 0";

    $stmt = mysqli_prepare($connection, $query);
    mysqli_stmt_bind_param($stmt, "i", $doctorID);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    // Create an array to store the results
    $data = array();
    // Fetch the results and add them to the array
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }
    header("Content-Type: application/json");
    // Output the array as JSON
    echo json_encode($data);
}
////////////////////////////////////////////////////////////////////
function getDoctorActivity(){
    global $connection;
    $doctorID = $_GET['DoctorID'];
    $query = "SELECT *
                FROM doctor_activity
                WHERE doctor_activity.ActivityDoctorID = ? 
                ORDER BY doctor_activity.ActivityDate DESC";
    $stmt = mysqli_prepare($connection, $query);
    mysqli_stmt_bind_param($stmt, "s", $doctorID);
    mysqli_stmt_execute($stmt);
    $result = mysqli_stmt_get_result($stmt);

    $data = array();
    while ($row = mysqli_fetch_assoc($result)) {
        $data[] = $row;
    }

    header("Content-Type: application/json");
     // Output the array as JSON
    echo json_encode($data);            
}

function addDoctorActivity($doctorID, $activityName){
    global $connection;
    // Perform the insertion into the Physiotherapy table
        $query = "INSERT INTO doctor_activity (ActivityDoctorID, ActivityName) 
                    VALUES (?, ?)";
        // Prepare the statement
        $stmt = mysqli_prepare($connection, $query);
        // Bind parameters to the statement
        mysqli_stmt_bind_param($stmt, "ss", $doctorID, $activityName );
        // Execute the statement
        $result = mysqli_stmt_execute($stmt);
}
