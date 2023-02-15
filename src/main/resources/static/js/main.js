$(function(){
    $("#more").click(function() {
        var next_page = parseInt($(this).attr("current-page")) + 1;

        $.ajax({
            method: "GET",
            url: "/post",
            data: {"page": next_page}
        })
        .done(function (response){
            if(response.length > 0){
                for (var post of response) {
                    $("#more-posts").append(
                    "<div class=\"post-preview\">" +
                        "<a href=\"/post/" + post.id + "\">" +
                            "<h2 class=\"post-title\">" + post.title + "</h2>\n" +
                            "<h3 class=\"post-subtitle\">" + post.content + "</h3>" +
                        "</a>" +
                        "<p class=\"post-meta\">" +
                            "Posted by " + post.username +
                        "</p>" +
                    "</div>" +
                    "<hr class=\"my-4\" />"
                    );
                }
            }
            else {
                alert('마지막 게시물입니다.');
            }
        })
        .always(function(response){
            console.log(response);
        });
        $(this).attr("current-page", next_page);
    });

    $("#create_button").click(function (){
        let title = $("#post-title").val();
        let username = $("#post-username").val();
        let content = $("#post-content").val();

        if(title == ''){
            alert('글 제목을 입력해주세요');
            $("#post-title").focus();
            return false;
        }
        if(username == ''){
            alert('사용자 이름을 입력해주세요');
            $("#post-username").focus();
            return false;
        }
        if(content == ''){
            alert('글 본문을 입력해주세요');
            $("#post-content").focus();
            return false;
        }

        $.ajax({
            method: "POST",
            url: "/post",
            data: JSON.stringify({
                "title": title,
                "username": username,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function (response) {
            console.log("Post creation success!");
            window.location.href = "/";
        });
    });

    $("#edit_button").click(function () {
        let title = $("#edit-post-title").val();
        let content = $("#edit-post-content").val();
        let id = $("#edit-post-id").val();

        if(title == ''){
            alert('글 제목을 입력해주세요');
            $("#edit-post-title").focus();
            return false;
        }
        if(content == ''){
            alert('글 본문을 입력해주세요');
            $("#edit-post-content").focus();
            return false;
        }

        $.ajax({
            method: "PUT",
            url: "/post",
            data: JSON.stringify({
                "title": title,
                "content": content,
                "id": id
            }),
            contentType: "application/json"
        })
        .done(function (response) {
            console.log("Post update success!");
            window.location.href = "/post/"+id;
        })
    })

    $("#post-delete-button").click(function () {
        let post_id = parseInt($("#post-id").val());

        $.ajax({
            method: "DELETE",
            url: "/post",
            data: {
                "postId": post_id
            }
        })
        .done(function (response){
            console.log("Post delete success!");
            window.location.href = "/";
        })
    })

    $("#more-comment-button").click(function(){
        var next_page = parseInt($(this).attr("current-comment-page")) + 1;
        var post_id = parseInt($("#post-id").val());

        $.ajax({
            method: "GET",
            url: "/comment",
            data: {
                "page": next_page,
                "postId": post_id
            }
        })
        .done(function(response) {
            if(response.length > 0){
                for (let comment of response) {
                    $("#more-comment").append(
                        "<div class=\"comment_text\">" +
                            "<div class=\"etc\">" +
                                "<div class=\"name\">" +
                                    comment.username +
                                "</div>" +
                            "</div>" +
                            "<p>" +
                                comment.content +
                            "</p>" +
                            "<div class=\"edit_btns\">" +
                                "<button class=\"comment-edit-form-button\">수정</button>" +
                                "<button id=\"comment-delete-button\">삭제</button>" +
                            "</div>" +
                                <!--수정 시-->
                                "<textarea class=\"edit comment-edit\" name=\"\" id=\"edit2\" cols=\"30\" rows=\"10\" placeholder=\"댓글을 입력해주세요\"></textarea>" +
                                "<div class=\"save_btns comment-edit\">" +
                                    "<button class=\"comment-edit-cancel-button\">취소</button>" +
                                    "<button class=\"save\" id=\"comment-edit-button\">저장하기</button>" +
                                "</div>" +
                        "</div>");
                }
                $(".comment-edit").hide();
            }
            else {
                alert('마지막 댓글입니다.');
            }
        })
        .always(function(response){
            console.log(response);

        });
        $(this).attr("current-comment-page", next_page);
    });

    $("#comment-save-button").click(function(){
        var username = $("#comment-username").val();
        var content = $("#comment-content").val();
        var post_id = $("#post-id").val();

        if(username == ''){
            alert('사용자 이름을 입력해주세요');
            $("#comment-username").focus();
            return false;
        }
        if(content == ''){
            alert('댓글을 입력해주세요');
            $("#comment-content").focus();
            return false;
        }

        $.ajax({
            method: "POST",
            url: "/comment",
            data: JSON.stringify({
                "username": username,
                "content": content,
                "postId": post_id
            }),
            contentType: "application/json"
        })
        .done(function (response) {
            console.log("Comment creation success!");
            window.location.href = "/post/"+post_id;
        });
    });

    $(document).on("click","#comment-edit-button",function(){
        let post_id = $("#post-id").val();
        let comment_id = $(this).parent().parent().children(".comment-id").val();
        let comment_content = $("#comment-edit-content").val();

        if(comment_content == ''){
            alert('댓글을 입력해주세요');
            $("#comment-edit-content").focus();
            return false;
        }

        $.ajax({
            method: "PUT",
            url: "/comment",
            data: JSON.stringify({
                "content": comment_content,
                "id": comment_id
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            console.log("Comment edit success!");
            window.location.href = "/post/"+post_id;
        });
    });

    $(".comment-edit").hide();

    $(document).on("click",".comment-edit-form-button",function(){
        $(this).closest(".comment_text").find(".comment-edit").show();
    });

    $(document).on("click",".comment-edit-cancel-button",function(){
        $(this).closest(".comment_text").find(".comment-edit").hide();
    });

    $(document).on("click","#comment-delete-button",function(){
        let post_id = $("#post-id").val();
        let comment_id = $(this).parent().parent().children(".comment-id").val();

        $.ajax({
            method: "DELETE",
            url: "/comment",
            data: {"commentId" : comment_id}
        })
        .done(function(response) {
            console.log("Comment deletion success!");
            window.location.href = "/post/"+ post_id;
        });
    });
});