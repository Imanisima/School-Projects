<?php
   session_start();
?>
<html>
    <head>
        <script src="https://use.fontawesome.com/6e47fdd73a.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="style.css">

        <title>Add To PLU System</title>

    </head>

    <body>
        <h2 class="sign" align="center">Please enter the product and PLU.</h2>

        <div class="login">
            <form role="form" class="nForm" method = "post">
                <input type="text" class="user" id="produce" align="center" name="newProduce" placeholder="Enter Produce Name" value="" required autofocus><br>
                <input type="text" class="pw" id="plu" align="center" name="newPLU" placeholder="Enter PLU #" value="" required><br>

                <button class="submit" align="center" name ="addProduce" onclick="insertNewEntry()">Add</button>

                <p class="invalid">Double-click to show the updated table!</p>

            </form>

        </div>

    </body>

    <script type="text/javascript" src="style.js"></script>
</html>