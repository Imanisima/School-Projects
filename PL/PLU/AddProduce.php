<html>
    <head>
        <link rel="stylesheet" href="~/lib/Font-Awesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="~/lib/Font-Awesome/css/all.min.css">
        <script src="https://use.fontawesome.com/6e47fdd73a.js"></script>
        <link rel="stylesheet" type="text/js" href="style.css">
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>View Produce</title>
    
    </head>
    <body>
        <header>
            <nav class="nav">
                <a href ="Logout.php" title = "Logout"> Logout</a>
                <a href ="WelcomeManager.php"> Go Back</a>
            </nav>
        </header>

        <div class="content-wrap">

        <?php
           include('ViewProduce.php');
           include('UpdateProduce.php');
        ?>
        
    </body>

    <script type="text/javascript" src="style.js"></script>

</html>