<?php
   session_start();
   ?>

<html>
   <head></head>
   <body>
   <?php
   // Log out of page and reroute to home page
   unset($_SESSION["username"]);
   unset($_SESSION["password"]);
   
   echo 'Reseting password';
   header('Refresh: 2; URL = index.php');
?>
   </body>

</html>