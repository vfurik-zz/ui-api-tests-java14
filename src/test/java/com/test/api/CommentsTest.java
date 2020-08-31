package com.test.api;

import com.test.api.actions.CommentsActions;
import com.test.api.clients.CommentsClient;
import com.test.api.models.Comment;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.test.api.actions.CommentsActions.QueryParams.getParamsInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@Epic("Comments")
public class CommentsTest {

    private final CommentsActions commentsActions = new CommentsActions();

    @Story("[API] Get all comments")
    @Tag("api")
    @Test
    void getAllComments() {
        final List<Comment> comments = commentsActions.getComments();
        assertThat(comments).isNotEmpty();
    }

    // test should fail, emails don't contains .biz123
    @Story("[API] Filter comments by postId and cmail")
    @Tag("api")
    @Test
    void getCommentsWithParams() {
        var postId = 1;
        var emailPart = ".biz123";

        List<Comment> comments = commentsActions.getComments(getParamsInstance().withPostId(1).withEmail(emailPart));

        assertSoftly(softly -> {
            softly.assertThat(comments)
                    .as("Response array should not be empty")
                    .isNotEmpty();

            softly.assertThat(comments).extracting(Comment::postId)
                    .as(String.format("Comments should has postId=%s", postId))
                    .containsOnly(postId);

            softly.assertThat(comments).extracting(Comment::email)
                    .as(String.format("Comments should has email which contains:%s", emailPart))
                    .allMatch(e -> e.contains(emailPart));
        });
    }

    // test should fail, 404 instead of 405
    @Story("[API] Get comment by incorrect id")
    @Tag("api")
    @Test
    void getCommentByIncorrectId() {
        Response resp = new CommentsClient().getComment(100500);
        resp.then().assertThat().statusCode(405);
    }

}