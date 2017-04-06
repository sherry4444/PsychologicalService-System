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
            var number = $(this).attr("number");
            var collegeid = $(this).attr("collegeid");
            var majorid = $(this).attr("majorid");

            $('#modifyInput1').attr("value",id);
            $('#modifyInput2').attr("value",name);
            $('#modifyInput3').attr("value",number);
            $('#modifyInput4').val(collegeid);
            $('#modifyInput5').val(majorid);
            $('#modifyInput6').attr("value",email);
            $('#modifyInput7').attr("value",mobilePhone);
            $('#modifyInput8').attr("value",userid);

            $("#myModal").modal('show');
        });

        cleanedit=function () {
            $('#modifyInput1').attr("value","");
            $('#modifyInput2').attr("value","");
            $('#modifyInput3').attr("value","");
            $('#modifyInput4').find('option:selected').attr('selected', false);
            $('#modifyInput5').find('option:selected').attr('selected', false);
            $('#modifyInput6').attr("value","");
            $('#modifyInput7').attr("value","");
            $('#modifyInput8').attr("value","");
            //$('#scaleZedit').find('option:selected').attr('selected', false);
        };
    });
})(jQuery);

function addstudent() {
    //debugger;
    var formData = new FormData();
    formData.append("studentName",document.getElementById("exampleInput1").value);
    formData.append("studentNumber",document.getElementById("exampleInput2").value);
    formData.append("collegeId",document.getElementById("exampleInput3").value);
    formData.append("majorId",document.getElementById("exampleInput4").value);
    console.log(formData);
    $.ajax({
        url: "/addstudent",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            console.log("添加学生信息成功");
            //setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}

function modifystudent() {
    //debugger;
    var formData = new FormData();
    formData.append("studentId",document.getElementById("modifyInput1").value);
    formData.append("studentName",document.getElementById("modifyInput2").value);
    formData.append("studentNumber",document.getElementById("modifyInput3").value);
    formData.append("collegeId",document.getElementById("modifyInput4").value);
    formData.append("majorId",document.getElementById("modifyInput5").value);
    formData.append("userInfo.userName",document.getElementById("modifyInput2").value);
    formData.append("userInfo.userEmail",document.getElementById("modifyInput6").value);
    formData.append("userInfo.mobilePhone",document.getElementById("modifyInput7").value);
    formData.append("stu_userId",document.getElementById("modifyInput8").value);
    console.log(formData);
    $.ajax({
        url: "/modifystudent",
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


function deletestudent(user) {
    var r = confirm("确认删除");
    if (r) {
        //debugger;
        var formData = new FormData();
        formData.append("studentId", $(user).attr("studentid"));
        formData.append("stu_userId", $(user).attr("stu_userid"));
        console.log(formData);
        $.ajax({
            url: "/deletestudent",
            type: "post",
            data: formData,
            //async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
                console.log("删除学生信息成功");
                //setTimeout("location.reload()",100);//页面刷新
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