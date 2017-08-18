/* jshint node: true */
module.exports = function(grunt) {
    'use strict';
    
    grunt.loadNpmTasks('grunt-contrib-connect');
    
    grunt.initConfig({});

    // Simple HTTP server to host the xirr client
    // Runs on port 8000 by default
    grunt.config('connect', {
        options: {
            base: 'www',
            keepalive: true
        },
        'default': {}
    });
};