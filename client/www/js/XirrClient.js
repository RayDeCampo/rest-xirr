/* global fetch */

/**
 * Client for the xirr REST service.
 */
class XirrClient {
    
    constructor() {
        this.txs = [];
    }

    /**
     * Add a transaction to the client.
     * @param {type} amount  amount of the transaction
     * @param {type} when  date of the transaction as a string "YYYY-MM-DD"
     * @returns {XirrClient} this
     */
    add(amount, when) {
        this.txs.push({amount: amount, when: when});
        return this;
    }

    /**
     * Clear the transactions.
     * @returns {XirrClient} this
     */
    clear() {
        this.txs = [];
        return this;
    }

    /**
     * Invoke the xirr ping service to ensure the server is up and we are
     * connected.
     * @returns {unresolved} promise returning the text of the response
     */
    ping() {
        return fetch('http://localhost:8080/xirr').then(function(response) {
            console.log(response);
            return response.text();
        });
    }

    /**
     * Invoke the xirr service passing the transactions added to the client.
     * @returns {unresolved} promise returning an object with a 'xirr'
     *          property containing the result or a 'message' property
     *          with an error message from the server
     */
    xirr() {
        return fetch('http://localhost:8080/xirr', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'}, 
            body: JSON.stringify(this.txs)
        }).then(function(response) {
            console.log(response);
            return response.json();
        });
    }
}