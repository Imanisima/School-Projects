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
            <!-- Show logout button at top of page -->
            <nav class="nav">
                <a href ="Logout.php" title = "Logout"> Logout</a>
            </nav>
        </header>

    <form class="nForm">
        <!-- Show buttons to reroute to view produce and add produce pages -->
        <button class="submit" type="submit" align="center" formaction="/ViewProduce.php"> View Produce </button>
        <button class="submit" type="submit" align="center" formaction="/AddProduce.php"> Add Produce </button>
    </form>

    </body>
</html>