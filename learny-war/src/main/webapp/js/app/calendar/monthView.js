define([ 'jquery', 'backbone', 'calendar/weekView' ], function($, Backbone, WeekView) {

    var monthView = Backbone.View.extend({
        tagName : 'tbody',

        initialize : function() {
            this.rendered = $.Deferred();
            this.render();
        },

        render : function() {
            var self = this; 

            var currentDate = new Date(this.options.date.getTime());
            var currentMonth = this.options.date.getMonth();

            // render first week
            currentDate.setDate(1);
            var weekView = new WeekView({
                date : currentDate,
                history : self.options.history,
                dateClickHandler : this.options.dateClickHandler
            });
            self.$el.append(weekView.el);

            // in case if first day is Sunday
            currentDate.setDate(currentDate.getDate() + 1);

            while (currentMonth == currentDate.getMonth()) { // until next month is reached
                while (currentDate.getDay() != 0) {
                    currentDate.setDate(currentDate.getDate() + 1);
                }
                if (currentMonth != currentDate.getMonth()) {
                    break; // next month is reached
                }
                self.$el.append(new WeekView({
                    date : currentDate,
                    history : self.options.history,
                    dateClickHandler : this.options.dateClickHandler
                }).el);
                currentDate.setDate(currentDate.getDate() + 1);
            }

            return this;
        }
    });

    return monthView;
});