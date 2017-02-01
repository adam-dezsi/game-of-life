var express = require('express');

var app = express();

app.use(express.static(__dirname + '/app/views'));
app.get('/', function(req, res){
    res.sendfile('app.html', { root: __dirname + "/app/views" } );
});

app.listen(8082);