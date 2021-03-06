define([ 'jquery', 'backbone', 'jqueryForm', 'util', 'text!comment/comments.html', 'comment/commentsCol' ],
        function($, Backbone, jqueryForm, util, commentsTpl, CommentCol) {

            var commentCollectionView = Backbone.View.extend({
                _template : _.template(commentsTpl),
                model : new CommentCol(),
                initialize : function() {
                    this.rendered = $.Deferred();
                },
                events : {
                    'submit form.id_new_comment_form' : 'newComment'
                },
                render : function() {
                    var self = this;
                    this.model.fetch({
                        data : $.param({
                            recordUuid : self.options.recordUuid
                        }),
                        success : function() {
                            self.$el.html(self._template({
                                comments : self.model.toJSON(),
                                util : util
                            }));
                            self.rendered.resolve('rendered');
                        }
                    });
                    return this;
                },

                newComment : function() {
                    var self = this;
                    var formSelector = 'form.id_new_comment_form';
                    $(formSelector).ajaxSubmit({
                        url : 'services/comment/record/new/' + self.options.recordUuid + '/',
                        type : 'post',
                        dataType : 'json',
                        success : function(data) {
                            $(formSelector).clearForm();
                            self.render();
                        }
                    });
                    return false;
                }
            });
            return commentCollectionView;
        });