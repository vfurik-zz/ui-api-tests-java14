package com.test.api.actions;

import com.test.api.clients.CommentsClient;
import com.test.api.models.Comment;
import io.qameta.allure.Step;

import java.util.*;

public class CommentsActions {

    private CommentsClient commentClient = new CommentsClient();

    @Step
    public List<Comment> getComments() {
        Comment[] comments = commentClient.getComments()
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .as(Comment[].class);
        return new ArrayList<>(Arrays.asList(comments));
    }

    @Step
    public List<Comment> getComments(QueryParams queryParams) {
        Comment[] comments = commentClient.getComments(queryParams.params).then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .as(Comment[].class);
        return new ArrayList<>(Arrays.asList(comments));

    }


    public static class QueryParams {

        private QueryParams() {

        }

        public static QueryParams getParamsInstance() {
            return new QueryParams();
        }

        private Map<String, Object> params = new HashMap<>();

        public QueryParams withPostId(int postId) {
            this.params.put("postId", postId);
            return this;
        }

        public QueryParams withEmail(String email) {
            this.params.put("email_like", email);
            return this;
        }

    }

}
