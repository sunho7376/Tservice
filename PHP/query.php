<?php
$mysqli = new mysqli("server", "id", "password", "table_name");

if ($mysqli->connect_errno) {
    die("Could not select database");
}

$type = $_POST['type'];
$query = str_replace("\\", "", $_POST['query']);

if (!$result = $mysqli->query($query)) {
    die("php failed: " . $mysqli->error);
}

if ($type == "insert") {
    echo $result;

} else if ($type == "select") {
    if ($result->num_rows) {
        $user = $result->fetch_array(MYSQL_ASSOC);

        echo "success;";

        foreach ($user as $data) {
            echo $data;
            echo ";";
        }
    } else {
        echo "fail;";
    }

} else if ($type == "update") {
    echo $result;

} else if ($type == "delete") {
    echo $result;

}

mysqli_close($mysqli);
?>
