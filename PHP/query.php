<?php
$mysqli = new mysqli("server", "id", "pw", "table");

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
        $data_list = array();
        while ($data = $result->fetch_array(MYSQLI_NUM)) {
            $data_list[] = $data;
        }
        echo "success;";
        foreach($data_list as $data) {
            echo "data;";
            foreach($data as $print) {
                echo $print;
                echo ";";
            }
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