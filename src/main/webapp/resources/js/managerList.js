/**
 * Created by Administrator on 2017/3/17.
 */

function addmanager() {
    debugger;
    var formData = new FormData();
    formData.append("managerName",document.getElementById("exampleInput1").value);
    formData.append("userInfo.userEmail",document.getElementById("exampleInput2").value);
    formData.append("userInfo.mobilePhone",document.getElementById("exampleInput3").value);
    console.log(formData);
    $.ajax({
        url: "/addmanager",
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