/* global Intl */

window.addEventListener('load', function() {

    let errorHandler = function(error) {
        console.log(error);
        document.getElementById('errors').innerHTML += error + '\n';
    };

    formatter = new Intl.NumberFormat('en-US', {
        style: 'percent',
        minimumFractionDigits: 2
    });

    let client = new XirrClient();

    // Invoke the ping method of the xirr service
    client.ping().then(function(result) {
        console.log(result);
        document.getElementById('ping').innerHTML = result;
    }).catch(errorHandler);

    // Invoke the xirr service and get a result back
    client.clear()
      .add(-1000, "2017-01-01")
      .add( 1100, "2018-01-01")
      .xirr().then(function(result) {
        console.log(result);
        document.getElementById('output').innerHTML =
            result.xirr ? formatter.format(result.xirr) : result.message;
    }).catch(errorHandler);
    
    // Invoke the xirr service and get an error message back
    client.clear()
      .add( 1000, "2017-01-01")
      .add( 1100, "2018-01-01")
      .xirr().then(function(result) {
        console.log(result);
        document.getElementById('bad').innerHTML =
            result.xirr ? formatter.format(result.xirr) : result.message;
    }).catch(errorHandler);

});
