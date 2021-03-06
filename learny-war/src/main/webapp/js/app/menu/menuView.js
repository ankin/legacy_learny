define([ 'jquery', 'backbone', 'text!menu/menu.html' ], function($, Backbone, menuTpl) {

    var menuView = Backbone.View.extend({
        tagName : 'ul',
        className : 'nav nav-pills margin-right-0 span2',
      
        _template : _.template(menuTpl),

        render : function() {
            this.$el.html(this._template());
            return this;
        },

    });
    return menuView;
});