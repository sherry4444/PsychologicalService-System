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


function addstudent() {
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
        //async: true,
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