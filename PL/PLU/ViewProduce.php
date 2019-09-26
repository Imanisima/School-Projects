<html>
    <head>
        <link rel="stylesheet" href="~/lib/Font-Awesome/css/fontawesome.min.css">
        <link rel="stylesheet" href="~/lib/Font-Awesome/css/all.min.css">

        <script src="https://use.fontawesome.com/6e47fdd73a.js"></script>

        <link rel="stylesheet" type="text/css" href="style.css">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>PLU System: View Produce</title>

    </head>
    <body>
        <header>
            <nav class="nav">
                <a href ="Logout.php" title = "Logout"> Logout</a>
                <a href ="WelcomeManager.php"> Go Back</a>
            </nav>
        </header>

        <h1>View Produce</h1>

        <div class="content-wrap">

        <input type="text" id="search" onkeyup="filter()" autofocus placeholder="Enter PLU number or Produce name.." >

            <table id="table">
            <tr class="header">
                <th style="width:60%;">Produce</th>
                <th style="width:40%;">PLU</th>
            </tr>
            <tr>
                <td>Banana</td>
                <td>94011</td>
            </tr>
            <tr>
                <td>Pepper</td>
                <td>4065</td>
            </tr>
            <tr>
                <td>Strawberry</td>
                <td>4246</td>
            </tr>
            <tr>
                <td>Spinach</td>
                <td>3332</td>
            </tr>
            </table>
        </div>

        <?php
            
         ?>

    </body>

    <script type="text/javascript" src="style.js"></script>
</html>