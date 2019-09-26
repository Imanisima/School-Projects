<html>
    <head>
        <script src="https://use.fontawesome.com/6e47fdd73a.js"></script>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="style.css">
        
        <title>Welcome</title>
    </head>
    <body>
    <header>
            <nav class="nav">
                <a href ="Logout.php" title = "Logout"> Logout</a>
            </nav>
        </header>

        <?php 
        // send employee straight to view current produce page
           include('ViewProduce.php');
        ?>

    </body>
</html>