/**
 * Created by Administrator on 2017/3/17.
 */
// $(document).ready(function() {
//
//     // $('.addstudent').click(function () {
//
//     $('form.addstudent').submit(function(event) {
//         Console.log("addstudent");
//         $.ajax({
//             url: "/addstudent",
//             type: "post",
//             data: $('#add').serialize(),
//             // async: true,
//             // cache: false,
//             // contentType: false,
//             // processData: false,
//             success: function (data) {
//                 alert(data);
//                 //setTimeout("location.reload()",100);//页面刷新
//             },
//             error: function (data) {
//                 alert("错误！！"+data);
//             }
//         });
//         }
//     );
//
// });


function addUser() {
    //debugger;
    var formData = new FormData();
    formData.append("userName",document.getElementById("exampleInput1").value);
    formData.append("Password",document.getElementById("exampleInput2").value);
    formData.append("userEmail",document.getElementById("exampleInput3").value);
    formData.append("mobilePhone",document.getElementById("exampleInput4").value);
    formData.append("role",document.getElementById("exampleInput5").value);
    console.log(formData);
    $.ajax({
        url: "/addUser",
        type: "post",
        data: formData,
        //async: false,
        cache:false,
        contentType: false,
        processData: false,
        success: function (data) {
            alert(data);
            console.log("添加用户信息成功");
            //setTimeout("location.reload()",100);//页面刷新
        },
        error: function (data) {
            alert("错误！！"+data);
        }
    });
}

function modifyUser() {
    //debugger;
    var formData = new FormData();
    formData.append("userId",document.getElementById("modifyInput1").value);
    formData.append("userName",document.getElementById("modifyInput2").value);
    formData.append("Password",document.getElementById("modifyInput3").value);
    formData.append("mobilePhone",document.getElementById("modifyInput4").value);
    formData.append("role",document.getElementById("modifyInput5").value);
    console.log(formData);
    $.ajax({
        url: "/modifyUser",
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


function deleteUser(user) {
    var r = confirm("确认删除");
    if (r) {
        //debugger;
        var formData = new FormData();
        formData.append("userId", $(user).attr("userid"));
        formData.append("role", $(user).attr("role"));
        console.log(formData);
        $.ajax({
            url: "/deleteUser",
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