/**
 * Created by Administrator on 2017/3/17.
 */

function addteacher() {
    debugger;
    var formData = new FormData();
    formData.append("teacherName",document.getElementById("exampleInput1").value);
    formData.append("userInfo.userEmail",document.getElementById("exampleInput2").value);
    console.log(formData);
    $.ajax({
        url: "/addteacher",
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