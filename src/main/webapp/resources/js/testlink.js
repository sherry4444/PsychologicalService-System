/**
 * Created by Administrator on 2017/3/17.
 */

function addlink() {
    //debugger;
    var formData = new FormData();
    formData.append("linkTitle",document.getElementById("exampleInput1").value);
    formData.append("linkPath",document.getElementById("exampleInput2").value);
    formData.append("Img",$('#exampleInput3')[0].files[0]);
    console.log(formData);
    $.ajax({
        url: "/addlink",
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

function deletelink(node) {
    var r = confirm("确认删除");
    if (r) {
        var formData = new FormData();
        formData.append("linkId", $(node).attr("linkId"));
        formData.append("linkImg", $(node).attr("linkImg"));
        console.log(formData);
        $.ajax({
            url: "/deletelink",
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
