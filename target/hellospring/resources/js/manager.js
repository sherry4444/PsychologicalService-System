var token = $("meta[name='_csrf']").attr("content");

;(function ($) {
    $(function () {
        $('.dropdown-toggle').dropdown('toggle');

        $(window).scroll(function(){
            if($(window).scrollTop()>400){    //垂直滚动条钓offset 大于90时。
                $("#BacktoTop").css({
                    "visibility":"visible",       //修改相关div样式
                });
            }else{
                $("#BacktoTop").css({
                    "visibility":"hidden",     //修改相关div样式
                });
            }
        });

        window.onload=function(){
            function adsorption(){
                var headerWrap=document.getElementsByClassName('header-wrap');
                var scrollTop=0;
                window.onscroll=function(){
                    scrollTop=document.body.scrollTop||document.documentElement.scrollTop;
                    if(scrollTop>100){
                        headerWrap.className='fixed';
                        $("#look1").css({
                            "width":"100%",       //修改相关div样式
                        });

                    }else{
                        headerWrap.className='header-wrap';
                        $("#look1").css({
                            "width":"400px",       //修改相关div样式

                        });
                    }
                }
            }
            adsorption();
        }

        $(".showimg").on("click",function () {
                var showimg = $('#showimg');
                showimg.attr("src","");
                var img = $(this).attr("img");
                showimg.attr("src",img);
                $("#myModalimg").modal('show');
            }
        );

        $(".numchange").on("change",function () {
            var url = $(this).attr("url");
            var num = $(this).val();
            location.href = encodeURI(url+"?num=" + num);
            $(this).val(num);
        }
        );

    });
})(jQuery);


$(function(){
    var _nava= $('.navigation li a');
    var _url = window.location.href;

    for(i=0;i<_nava.length;i++)
    {
        var _astr = _nava.eq(i).attr('href');
       if(_url.indexOf(_astr) != -1){
            _nava.eq(i).parent().addClass('active');
        }
        //console.log(_nava+""+_url+"==="+"==="+_astr);
    }

});



//每页条数选择
function numsearch(node,num) {
    var flag = $(node).attr("flag");
    location.href = hostUrl+num+"&flag="+flag;
}

//模糊搜索
 function titlesearch(node) {
 var title = document.getElementById("title").value;
 var flag = $(node).attr("flag");
 var num = $(node).attr("num");
 var url = $(node).attr("url");
 location.href = encodeURI(hostUrl+url+"?flag=" + flag + "&title=" + title + "&num=" + num);
/* var data1={
     title:title,
     flag:flag,
     num:num
 }
 $.post(hostUrl+"/"+url+"?_csrf="+token,data1,function(data){document.body.parentElement.innerHTML=data});*/
 }


/*
function change(node) {
    var r=confirm("确认操作？");
    if (r)
    {
        var formData = new FormData();
        var url =  $(node).attr("url");
        var id = $(node).attr("id");
        formData.append(id, $(node).attr("certificationId"));
        formData.append("certificationStatus",$(node).attr("status"));
        //alert(certificationId);
        $.ajax({
            url: hostUrl+"/"+url+"/change?_csrf="+token,
            type: "post",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                alert(returndata);
                setTimeout("location.reload()",100);//页面刷新
            },
            error: function (returndata) {
                alert("错误！！"+returndata);
            }
        });
        return true;
    }
    else
    {
        return false;
    }
}
*/


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
