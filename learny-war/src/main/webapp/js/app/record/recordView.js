define([ 'jquery', 'backbone', 'util/util', 'text!record/record.html', 'record/recordModel', 'comment/commentsColView',
        'calendar/calendarView' ], function($, Backbone, util, recordTpl, RecordModel, CommentColView, CalendarView) {

    var recordView = Backbone.View.extend({
        className : 'record',
        _template : _.template(recordTpl),
        model : new RecordModel(),

        events : {
            'click #calendar-open-link' : 'toggleCalendar'
        },

        initialize : function() {
            this.rendered = $.Deferred();
        },

        render : function() {
            self = this;
            self.model.fetch({
                success : function() {
                    var recordJson = self.model.toJSON();
                    self.$el.html(self._template({
                        record : recordJson,
                        util : util
                    }));
                    self.$el.find('#record-calendar').append(new CalendarView().el);
                    // add comments
                    var commentColView = new CommentColView({
                        objectType : recordJson.objectType,
                        objectId : recordJson.uuid
                    });
                    $.when(commentColView.rendered).done(function() {
                        self.$el.find('.id_comments').html(commentColView.el);
                        self.rendered.resolve('rendered');
                    });
                    commentColView.render();
                }
            });
            return this;
        },

        toggleCalendar : function() {
            if ($('#record-calendar').hasClass('open')) {
                $('#record-calendar').removeClass('open');
                $('#calendar-open-link').html($.i18n.prop('OpenCalendar'));
            } else {
                $('#record-calendar').addClass('open');
                $('#calendar-open-link').html($.i18n.prop('CloseCalendar'));
            }
        }
    });
    return recordView;
});