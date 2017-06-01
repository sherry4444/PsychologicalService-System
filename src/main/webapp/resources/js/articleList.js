/**
 * Created by Administrator on 2017/3/17.
 */


;(function ($) {
    $(function () {
        //
        // $(".delete").click(function () {
        //     var label = $(this).next(":hidden").val;
        //     var flag = comfirm("确定要删除" + label + "吗？");
        //     if (flag) {
        //         var url = $(this).attr("href");
        //         $("#_form").attr("action", url);
        //         $("#_form").submit();
        //     }
        //     return false;
        // });



        $(".edit").on("click",function () {
            cleanedit();
            var id= $(this).attr("id");
            var title = $(this).attr("title");
            var content = $(this).attr("content");
            var img = $(this).attr("img");


            $('#modifyInput1').attr("value",id);
            $('#modifyInput2').attr("placeholder",title);
            $('#modifyInput3').attr("placeholder",content);
            $('#modifyInput4').attr("src",img);


            $("#myModal").modal('show');
        });

        cleanedit=function () {
            $('#modifyInput1').attr("value","");
            $('#modifyInput2').attr("placeholder","");
            $('#modifyInput3').attr("placeholder","");
            $('#modifyInput4').attr("src","");
        };

    });
})(jQuery);

function addarticle() {
    //debugger;
    var formData = new FormData();
    formData.append("article_name",document.getElementById("exampleInput1").value);
    formData.append("article_author",document.getElementById("exampleInput2").value);
    formData.append("article_context",document.getElementById("exampleInput3").value);
    console.log(formData);
    $.ajax({
        url: "/addarticle",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}

function deletearticle(node) {
    var r = confirm("确认删除");
    if (r) {
        $.ajax({
            url: "/deletearticle/"+$(node).attr("id"),
            type: "get",
            //data: ,
            //async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
                setTimeout("location.reload()", 100);//页面刷新
            },
            error: function (data) {
                alert("错误！！" + data);
            }
        });
    }
    else {
        return false;
    }
}

function viewComment(node) {
    var commentlist = document.getElementById("commentList").value;
        $.ajax({
            url: "/viewComment/"+$(node).attr("id"),
            type: "get",
            //data: ,
            //async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                console.log(data);
                console.log("json: "+JSON.parse(data));

                //console.log("data.list[0]:"+data.list[0]);
                alert(data.userName);
                //显示出
                for (var i = 0; i < data.list.length; i++) {
                    commentlist.append('<span >'+data.list[i].userName+'</span> <p>'+data.list[i].context+'</p><span th:text="${comment.createTime}" class="pull-right">'+data.list[i].createTime+'</span> <hr>');
                }

            },
            error: function (data) {
                alert("错误！！" + data);
            }
        });
}


function comment(node) {
    //debugger;
    var formData = new FormData();
    formData.append("context",document.getElementById("comment_context").value);
    formData.append("userId",$(node).attr("user_id"));
    formData.append("userName",$(node).attr("user_name"));
    formData.append("articleId",$(node).attr("article_id"));
    console.log(formData);
    $.ajax({
        url: "/addcomment",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}



