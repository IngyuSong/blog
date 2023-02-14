$(function(){
    $("#more").click(function() {
        console.log("clicked");
        var last_page = parseInt($(this).attr("last-page")) + 2;

        $.ajax({
            method:"GET",
            url: "/api/post",
            data : {"page":last_page}
        })
        .done(function(response){
            console.log("done");
            console.log(response);
            if(response.length > 0) {
                for(var blog of response) {
                    $("#more-posts").append(
                    "<div class=\"post-preview\">" +
                        "<a href=\"/blog/" + blog.idx + "\">" +
                            "<h2 class=\"post-title\">" + blog.title + "</h2>\n" +
                            "<h3 class=\"post-subtitle\">" + blog.content + "</h3>" +
                        "</a>" +
                        "<p class=\"post-meta\">" +
                            "Posted by " + blog.username +
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
            console.log("always");
            console.log(response);
        });
        $(this).attr("last-page", last_page);
    });


    $("#create_button").click(function() {
        var title = $("#post-title").val();
        var username = $("#post-username").val();
        var content = $("#post-content").val();
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
            method:"POST",
            url: "/post",
            data : JSON.stringify({
                "title": title,
                "username": username,
                "content": content
            }),
            contentType: "application/json"
        })
        .done(function(response) {
            console.log("Post creation success!");
            window.location.href="/";
        });
    });

    $("#edit_button").click(function() {
            var idx = $("#edit-post-id").val();
            var title = $("#edit-post-title").val();
            var content = $("#edit-post-content").val();
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
                method:"PUT",
                url: "/post",
                data : JSON.stringify({
                    "idx": idx,
                    "title": title,
                    "content": content
                }),
                contentType: "application/json"
            })
            .done(function(response) {
                console.log("Post update success!");
                window.location.href="/post/" + idx;
            });
    });

    $("#delete_button").click(function(){
        var idx = $("#idx").val();

        $.ajax({
            method:"DELETE",
            url:"/post",
            data: {"idx": idx}
        })
        .done(function(response) {
            console.log("Post delete success!");
            window.location.href="/";
        });
    });

    $("#comment-save-button").click(function() {
            var username = $("#comment-username").val();
            var content = $("#comment-content").val();
            var post_idx = $("#idx").val();

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
                method:"POST",
                url: "/comment",
                data : JSON.stringify({
                    "username": username,
                    "content": content,
                    "post_idx": post_idx
                }),
                contentType: "application/json"
            })
            .done(function(response) {
                console.log("Comment creation success!");
                window.location.href="/post/" + idx;
            });
    });
});
