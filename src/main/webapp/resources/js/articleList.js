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

Date.prototype.toLocaleString = function() {
    return this.getFullYear() + "年" + (this.getMonth() + 1) + "月" + this.getDate() + "日 ";
};

function viewComment(node) {

    var commentList = $("#commentList");
        $.ajax({
            url: "/viewComment/"+$(node).attr("article_id"),
            type: "get",
            //data: ,
            //async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                //console.log(data);
                //清空
                //document.getElementById('commentList').innerHTML = "";
                $('#comment_context').attr("value","");
                commentList.empty();
                //显示出
                for (var i = 0; i < data.list.length; i++) {
                    var unixTimestamp = new Date(data.list[i].createTime).toLocaleString();
                    commentList.append('<span >'+data.list[i].userName+'</span> <p>'+data.list[i].context+'</p><span  class="pull-right">'+unixTimestamp+'</span> <hr>');
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
            viewComment(node);
            //setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}



