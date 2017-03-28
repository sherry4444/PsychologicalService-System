/**
 * Created by Administrator on 2017/3/17.
 */

;(function ($) {
    $(function () {
        $(".edit").on("click",function () {
            cleanedit();
            var id= $(this).attr("id");
            var userid = $(this).attr("userid");
            var name = $(this).attr("name");
            var email = $(this).attr("email");
            var mobilePhone = $(this).attr("mobilePhone");

            $('#modifyInput1').attr("value",id);
            $('#modifyInput5').attr("value",userid);
            $('#modifyInput2').attr("value",name);
            $('#modifyInput3').attr("value",email);
            $('#modifyInput4').attr("value",mobilePhone);

            $("#myModal").modal('show');
        });

        cleanedit=function () {
            $('#modifyInput1').attr("value","");
            $('#modifyInput5').attr("value","");
            $('#modifyInput2').attr("value","");
            $('#modifyInput3').attr("value","");
            $('#modifyInput4').attr("value","");
            //$('#scaleZedit').find('option:selected').attr('selected', false);
        };
    });
})(jQuery);

function addmanager() {
    //debugger;
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
            setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });

}


function modifymanager() {
    //debugger;
    var formData = new FormData();
    formData.append("managerId",document.getElementById("modifyInput1").value);
    formData.append("managerName",document.getElementById("modifyInput2").value);
    formData.append("userInfo.userName",document.getElementById("modifyInput2").value);
    formData.append("userInfo.userEmail",document.getElementById("modifyInput3").value);
    formData.append("userInfo.mobilePhone",document.getElementById("modifyInput4").value);
    formData.append("mg_userId",document.getElementById("modifyInput5").value);
    console.log(formData);
    $.ajax({
        url: "/modifymanager",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            console.log("修改学生信息成功");
            //setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}


function deletemanager(node) {
    //debugger;
    var formData = new FormData();
    formData.append("managerId", $(node).attr("managerid"));
    formData.append("mg_userId", $(node).attr("mg_userid"));
    console.log(formData);
    $.ajax({
        url: "/deletemanager",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            console.log("删除学生信息成功");
            //setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}