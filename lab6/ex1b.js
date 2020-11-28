const async = require('async');

n = 4;

async.waterfall([
        function(callback){
            callback(null, '1\n');
        },
        function(arg1,  callback){
            callback(null, arg1+'2\n');
        },
        function(str, callback){
            callback(null, str+'3');
        }
    ],
    function (err, str){
        for(var i=0; i<n; i++) console.log(str);
        console.log('done');
    }
);