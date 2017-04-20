/**
 * Created by Administrator on 2017/3/17.
 */


;(function ($) {
    $(function () {
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

function addnotice() {
    //debugger;
    var formData = new FormData();
    formData.append("noticeTitle",document.getElementById("exampleInput1").value);
    formData.append("noticeContent",document.getElementById("exampleInput2").value);
    formData.append("Img",$('#exampleInput3')[0].files[0]);
    console.log(formData);
    $.ajax({
        url: "/addnotice",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            console.log("over..");
            setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}

function deletenotice(node) {
    var r = confirm("确认删除");
    if (r) {
        var formData = new FormData();
        formData.append("noticeId", $(node).attr("noticeId"));
        formData.append("noticeImage", $(node).attr("noticeImage"));
        console.log(formData);
        $.ajax({
            url: "/deletenotice",
            type: "post",
            data: formData,
            //async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
                console.log("over..");
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

function modifynotice() {
    var formData = new FormData();
    formData.append("noticeId",document.getElementById("modifyInput1").value);
    formData.append("noticeTitle",document.getElementById("modifyInput2").value);
    formData.append("noticeContent",document.getElementById("modifyInput3").value);
    formData.append("noticeImage",document.getElementById("modifyInput4").value);
    formData.append("Img",$('#modifyInput5')[0].files[0]);
    console.log(formData);
    $.ajax({
        url: "/modifynotice",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            console.log("over..");
            setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}


