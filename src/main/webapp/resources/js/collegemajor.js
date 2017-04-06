/**
 * Created by Administrator on 2017/3/17.
 */

function addcollege() {
    //debugger;
    var formData = new FormData();
    formData.append("collegeName",document.getElementById("collegeInput").value);
    console.log(formData);
    $.ajax({
        url: "/addcollege",
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

function deletecollege(node) {
    var r = confirm("确认删除");
    if (r) {
    var formData = new FormData();
    formData.append("collegeId",$(node).attr("collegeid"));
    console.log(formData);
    $.ajax({
        url: "/deletecollege",
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
    else {
            return false;
        }
}


function addmajor() {
    //debugger;
    var formData = new FormData();
    formData.append("majorName",document.getElementById("majorInput").value);
    console.log(formData);
    $.ajax({
        url: "/addmajor",
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

function deletemajor(node) {
    var r = confirm("确认删除");
    if (r) {
        var formData = new FormData();
        formData.append("majorId", $(node).attr("majorid"));
        console.log(formData);
        $.ajax({
            url: "/deletemajor",
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


