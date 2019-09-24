<html>
    <head>
        <title>PLU System</title>
    </head>
    <body>

        <h1>View Produce</h1>

        <!-- Create a filter for the produce -->
        <form action="/AddProduce.php">
            <input type="text" id="filterInput" onkeyup="searchFilter()" placeholder="Input PLU or produce name...">
            <input type="submit">
        </form>

        <?php 
            // something goes here
        ?>

        Logout Here <a href = "Logout.php" tite = "Logout"> Sign Out in progress...

    </body>
</html>


<!-- Welcome Message 
    Search for PLU Codes + Search Button
    Create Database of various produce (Name : PLU)
    Add image for each produce listed
    PLU Information:
        PLU code
        Name of produce
        Latin word for produce
-->