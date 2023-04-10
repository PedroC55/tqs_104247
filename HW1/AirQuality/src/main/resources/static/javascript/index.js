

$(document).ready(function(){



    $.ajax({
        url: "http://localhost:8080/api/cache",
        headers:{
            "Access-Control-Allow-Origin":"http://localhost",
        },
        statusCode: {
            500: function(xhr){
                console.log("erro 500");
                return;
            },
            403: function(xhr){
                console.log("erro 403");
                return;
            }
        }

    }).then(function(data) {
        console.log(data);
        $('#hits').text(data.numHits);
        $('#misses').text(data.numMisses);
        $('#requests').text(data.numRequests);
        
        
       
    });
    
});


$(function() {

    $('#searchButton').click(function(){
        $.ajax({
            url: "http://localhost:8080/api/city/" + getVal() ,
            headers:{
                "Access-Control-Allow-Origin":"http://localhost",
            },
            statusCode: {
                500: function(xhr){
                    console.log("erro 500");
                    return;
                },
                403: function(xhr){
                    console.log("erro 403");
                    return;
                }
            }

        }).then(function(data) {
            console.log(data);
            console.log(data.city_name);
            $('#city').text(data.city_name);
            $('#aqi').text(data.data.aqi);
            $('#surfaceo3').text(data.data.o3);
            $('#surfaceso2').text(data.data.so2);
            $('#surfaceno2').text(data.data.no2);
            $('#monoxide').text(data.data.co);
            $('#under2_5').text(data.data.pm25);
            $('#under10').text(data.data.pm10);


        
        });
    });
});

$(function() {
    $('#updateButton').click(function(){
        $.ajax({
            url: "http://localhost:8080/api/cache",
            headers:{
                "Access-Control-Allow-Origin":"http://localhost",
            },
            statusCode: {
                500: function(xhr){
                    console.log("erro 500");
                    return;
                },
                403: function(xhr){
                    console.log("erro 403");
                    return;
                }
            }

        }).then(function(data) {
            console.log(data);
            $('#hits').text(data.numHits);
            $('#misses').text(data.numMisses);
            $('#requests').text(data.numRequests);


        
        });
    });
});

function getVal() {
    const val = document.querySelector('input').value;
    return val;
  }