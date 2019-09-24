<?php
   session_start();
?>
<html>
    <head>
        <h1>PLU System: Sign In</h1>
    </head>

    <body>
        <h2>Please enter your username and password.</h2>
        <div>
            <?php
                $warning_msg = '';

                if(isset($_POST['login']) && !empty($_POST['username']) && !empty($_POST['password'])){
                    if($_POST['username'] == 'manager' && $_POST['password'] == 'managerpw'){

                        echo "Sign In Successfull!";

                        // manager login
                        if(!isset($_SESSION['username'])){
                            header("Location:AddProduce.php");
                         }
                        
                        // employee login
                    }else if($_POST['username'] == 'employee' && $_POST['password'] == 'employpw'){
                        if(!isset($_SESSION['username'])){
                            header("Location:ViewProduce.php");
                        }

                    // invalid login
                    }else{
                        $warning_msg = "Sign In Invalid. Please try again.";
                    }
                }

            ?>
        </div>

        <div class="login_container">
            <form role="form" method = "post">
                <h4> <?php echo $warning_msg; ?> </h4>

                <label for="username"><b>Username</b></label>
                <input type="text" name="username" placeholder="Enter Username" required autofocus><br>

                <label for="password"><b>Password</b></label>
                <input type="password" name="password" placeholder="Enter Password"  required><br>

                <button type = "submit" name = "login">Sign In</button>

            </form>

        </div>
    </body>

</html>