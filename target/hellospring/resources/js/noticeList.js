/**
 * Created by Administrator on 2017/3/17.
 */

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
    formData.append("Img",$('#modifyInput4')[0].files[0]);
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
