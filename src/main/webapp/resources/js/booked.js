/**
 * Created by Administrator on 2017/3/17.
 */


;(function ($) {
    $(function () {
        $(".audit").on("click",function () {
            cleanedit();
            var id= $(this).attr("id");
            $('#modifyInput1').attr("value",id);
            $("#myModal").modal('show');
        });

        cleanedit=function () {
            $('#modifyInput1').attr("value","");
        };


        $(".finish").on("click",function () {
            var formData = new FormData();
            formData.append("bookId", $(this).attr("id"));
            formData.append("bookState", 4);
            $.ajax({
                url: "/finishbook",
                type: "post",
                data: formData,
                //async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    alert(data);
                    //console.log("over..");
                    //setTimeout("location.reload()",100);//页面刷新
                },
                error: function (data) {
                    alert("错误！！" + data);
                }
            });
        });

    });
})(jQuery);

function modifystate() {

    var status = $('input:radio[name="modifyInput2"]:checked').val();
    if(status == null){
        alert("请选择");
        return false;
    }
    else {
        var formData = new FormData();
        formData.append("bookId", document.getElementById("modifyInput1").value);
        formData.append("bookState", status);
        formData.append("feedback", document.getElementById("modifyInput3").value);
        console.log(formData);
        $.ajax({
            url: "/modifystate",
            type: "post",
            data: formData,
            //async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
                //console.log("over..");
                //setTimeout("location.reload()",100);//页面刷新
            },
            error: function (data) {
                alert("错误！！" + data);
            }
        });
    }
}

function book() {
    var formData = new FormData();
    formData.append("bookUserId",document.getElementById("exampleInput1").value);
    formData.append("bookState", 1);
    formData.append("bebookedUserId",document.getElementById("exampleInput2").value);
    formData.append("bookTime",document.getElementById("exampleInput3").value);
    formData.append("bookReason",document.getElementById("exampleInput4").value);
    console.log(formData);
    $.ajax({
        url: "/addbooked",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            //console.log("over..");
            //setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}

function addbooked() {
    //debugger;
    var formData = new FormData();
    formData.append("bookedTitle",document.getElementById("exampleInput1").value);
    formData.append("bookedPath",document.getElementById("exampleInput2").value);
    formData.append("Img",$('#exampleInput3')[0].files[0]);
    console.log(formData);
    $.ajax({
        url: "/addbooked",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            console.log("over..");
            //setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}

function deletebooked(node) {
    var r = confirm("确认删除");
    if (r) {
        var formData = new FormData();
        formData.append("bookedId", $(node).attr("bookedId"));
        formData.append("bookedImg", $(node).attr("bookedImg"));
        console.log(formData);
        $.ajax({
            url: "/deletebooked",
            type: "post",
            data: formData,
            //async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
                console.log("over..");
                //setTimeout("location.reload()", 100);//页面刷新
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


function AutoResizeImage(maxWidth,maxHeight,objImg){
    //alert("autoresizeimage");
    //debugger;
    var img = new Image();
    img.src = objImg.src;
    var hRatio;
    var wRatio;
    var Ratio = 1;
    var w = img.width;
    var h = img.height;
    wRatio = maxWidth / w;
    hRatio = maxHeight / h;
    if (maxWidth ==0 && maxHeight==0){
        Ratio = 1;
    }else if (maxWidth==0){//
        if (hRatio<1) Ratio = hRatio;
    }else if (maxHeight==0){
        if (wRatio<1) Ratio = wRatio;
    }else if (wRatio<1 || hRatio<1){
        Ratio = (wRatio<=hRatio?wRatio:hRatio);
    }
    if (Ratio<1){
        w = w * Ratio;
        h = h * Ratio;
    }
    objImg.height = h;
    objImg.width = w;
}
