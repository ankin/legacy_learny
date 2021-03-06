requirejs.config({

    paths : {
        jquery : '../lib/jquery-2.0.3',
        lodash : '../lib/lodash-1.2.1',
        backbone : '../lib/backbone-1.0.0',
        jqueryDateFormat : '../lib/jquery.dateFormat-1.0',
        jqueryForm : '../lib/jquery.form-3.25.0',
        jqueryi18n : '../lib/jquery.i18n.properties-min-1.0.9',
        typeahead : '../lib/typeahead-0.9.2',
        text : '../lib/text-2.0.6',

        // learny
        util : 'util/util'
    },

    shim : {
        'jqueryDateFormat' : [ 'jquery' ],
        'jqueryForm' : [ 'jquery' ],
        'jqueryi18n' : [ 'jquery' ],
        'typeahead' : ['jquery'],
        'backbone' : {
            deps : [ 'lodash', 'jquery' ],
            exports : 'Backbone'
        },
    }
});

requirejs([ 'jquery', 'backbone', 'util', 'menu/menuRouter' ], function($, Backbone, util, MenuRouter) {

    $('#spinner-container').on('showSpinner', function() {
        if (!$('#spinner-container').hasClass('spinner')) {
            $('#spinner-container').addClass('spinner');
            $('#spinner-container').css('display', '');

            $('#disabler-container').addClass('disable');

        }
    });
    $('#spinner-container').on('hideSpinner', function() {
        $('#spinner-container').removeClass('spinner');
        $('#spinner-container').css('display', 'none');

        $('#disabler-container').removeClass('disable');
    });

    util.initLanguage('en_EN');

    this.router = new MenuRouter();
    Backbone.history.start();

});