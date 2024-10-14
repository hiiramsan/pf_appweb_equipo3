<?php
    $server = 'localhost';
    $user = 'root';
    $pass = '2608';
    $db = 'usuarios';
    $conectar = new mysqli($server, $user, $pass, $db);

    if($conectar->connect_errno){
            die("Conexion Fallida" . $conectar->connect-errno);
    }else{
        echo "";
    }



?>