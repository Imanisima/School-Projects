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

        <title>Welcome to the PLU System</title>

    </head>

    <body>
        <h2 class="sign" align="center">Please enter your username and password.</h2>
        <div>
                <?php
                $warning_msg = '';

                if(isset($_POST['login']) && !empty($_POST['username']) && !empty($_POST['password'])){
                    if($_POST['username'] == 'manager' && $_POST['password'] == 'managerpw'){

                        echo "Sign In Successfull!";

                        /*
                        * manager login
                        * Should go to view produce page with message "Here is out produce for the day!"
                        * Add button that takes them to Add produce page!
                        */
                        if(!isset($_SESSION['username'])){
                            header("Location:WelcomeManager.php");
                         }
                        
                        /*
                        * employee login
                        * Should go to view produce page with message "Here is out produce for the day!"
                        * 
                        */
                    }else if($_POST['username'] == 'employee' && $_POST['password'] == 'employpw'){
                        if(!isset($_SESSION['username'])){
                            header("Location:WelcomeEmployee.php");
                        }

                    // invalid login
                    }else{
                        $warning_msg = "Sign In Invalid. Please try again.";
                    }
                }

            ?>
        </div>

        <div class="login">
            <form role="form" class="nForm" method = "post">
                <h4 class="invalid"> <?php echo $warning_msg; ?> </h4>

                <input type="text" class="user" align="center" name="username" placeholder="Enter Username" required autofocus><br>
                <input type="password" class="pw" align="center" name="password" placeholder="Enter Password"  required><br>

                <button class="submit" type="submit" align="center" name = "login">Sign In</button>

            </form>

        </div>
    </body>
</html>
