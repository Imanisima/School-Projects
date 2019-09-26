function insertNewEntry() { 
            
    var table =  document.getElementById("table");

    var insertNewRow = table.insertRow(1); 
    var createCell0 = insertNewRow.insertCell(0); 
    var createCell1 = insertNewRow.insertCell(1); 
    
    createCell0.innerHTML = "Apples"
    createCell1.innerHTML = "9087";
}

function filter() {
    // Declare variables 
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("table");
    tr = table.getElementsByTagName("tr");


    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function sort(){
    var table = document.getElementById("table");
    var Arr = [];
    for(var i=1, ln=table.rows.length; i<ln; i++){
        var row = table.rows[i];
        var firstCell = row.cells[0].textContent;
        Arr.push([firstCell, row]);  //temporary array
    }
    //sort by first column of inner arrays
        Arr = Arr.sort(function(a,b) {
    return a[0] > b[0];
    });
    for(var i=0, ln=Arr.length; i<ln; i++){
        table.appendChild(Arr[i][1]);
    }
    Arr = null;
}

sort();