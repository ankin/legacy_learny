package com.learny.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.learny.ejb.service.local.CommentLocal;
import com.learny.persistence.entity.RecordComment;
import com.learny.rest.core.AbstractService;

@Path(CommentService.PATH)
@RequestScoped
public class CommentService extends AbstractService {

    public static final String PATH = "/comment";

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    @Inject
    private CommentLocal commentBean;

    @GET
    @Path("record/getall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecordComment> findCommentsByRecordId(@QueryParam("recordUuid") String recordUuid) {
        logger.info("findCommentsByRecordId() was called with param recordUuid: " + recordUuid);
        return commentBean.findRecordCommentsByRecordUuid(recordUuid);
    }

    @POST
    @Path("/record/new/{recordUuid}/")
    @Produces(MediaType.APPLICATION_JSON)
    public void createComment(@PathParam("recordUuid") String recordUuid, @FormParam("commentText") String commentText) {
        logger.info("createComment() was called with params recordUuid: " + recordUuid + ", commentText:" + commentText);
        commentBean.createRecordComment(getCurrentUser().getEmail(), recordUuid, commentText);
    }

}
