searchServiceUrl="http://localhost:8081/jokesearch/api/"
$(document).ready(function () {
            $.ajax({
                url: window.searchServiceUrl+"getCategories",
                datatype: "JSON",
                type: "GET",
                success: function (data) {
                    debugger;
                    for(var i=0;i<data.length;i++)
                    {
                        var opt = new Option(data[i]);
                        $("#op1").append(opt);
                    }
                }
            });
        });

function UserAction() {
console.log("UserAction Called")
    var key=document.getElementById("keysearch").value;
    updateDiv();
    $.ajax({
            url: window.searchServiceUrl+"getJokesByTerm",
            type : "GET",
            dataType: 'json',
            data: {
            term : key
            }
    }).then(function(data) {

       for (var x in data) {
           document.getElementById('jokesByTerm').innerHTML += '<li>' + data[x].setup +" "+ data[x].punchline + '</li>'+ "<br>";
           console.log(data[x].punchline);
           }
    });
}


function UserActionAdvSearch() {
console.log("UserActionAdvSearch Called")
            updateDiv();

    var key=document.getElementById("advsearch").value;
    var type=document.getElementById("op1").value;

    $.ajax({
            url: window.searchServiceUrl+"getJokesByTerm",
            type : "GET",
            dataType: 'json',
            data: {
            term : key
            }
    }).then(function(data) {

       for (var x in data) {
           document.getElementById('jokesByTerm').innerHTML += '<li>' + data[x].setup +" "+ data[x].punchline + '</li>'+ "<br>";
           console.log(data[x].punchline);
           }
    });
}
function updateDiv()
{
    $( "#jokesByTerm" ).load(window.location.href + " #jokesByTerm" );
    $( "#searchedTerms" ).load(window.location.href + " #searchedTerms" );
    termNavigation();
}

function termNavigation() {
console.log("termNavigation Called")
    $.ajax({
            url: window.searchServiceUrl+"getRecentSearchedTerms",
            type : "GET",
            dataType: 'json',
    }).then(function(data) {

       for (var x in data) {
           document.getElementById('searchedTerms').innerHTML += '<li>' + data[x].term +" "+ data[x].termCount + '</li>'+ "<br>";
           console.log(data[x].term +" "+ data[x].termcount );
           }
    });
}
