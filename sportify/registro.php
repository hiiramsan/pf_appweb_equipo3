<?php

include("conexion.php");

if(isset($_POST['usecrear-cuentars'])){
    if(
    strlen($_POST['username']) >= 1 &&
    strlen($_POST['password']) >= 1 
    ){
        $name = trim($_POST['username']);
        $paswword = trim($_POST['password']);

        $consulta = "INSERT INTO users(usuario,password)
        VALUES('$name', '$paswword'";
        
        $resultado = mysqli_query($conectar, $consulta);
        if($resultado){
          ?>
          <h3 class="sucess" >Tu registro se a completado</h3>
          <?php      
        }else{
            ?>
            <h3 class="error">Ocurrio un error</h3>
            <?php
        }

    }else{
        ?>
        <h3 class="error">Llena todos los campos</h3>
        <?php
    }
}

?>