/**
 * Created by Administrator on 2017/3/22.
 */

$(function(){
    //绑定全局ajaxStart事件
//  $("#divMsg").ajaxStart(function(){
    //  $(this).show()
    //})
    //绑定全局ajaxStop事件
//  $("#divMsg").ajaxStop(function(){
    //  $(this).hide()
//  })
    initFace();//初始化表情
    getMessageList();//取得聊天信息
    getOnlineList();//取得在线人员
    /**
     * 点击发送按钮
     */
    $("#btnSend").bind("click",function(){
        var $content = $("#txtContent")
        if($content.val()!=""){
            sendContent($content.val())
        }else{
            alert("发送内容不能为空")
            $content.foucus()
            return false
        }
    })
    /**
     * 点击表情图标
     */
    $("table tr td img").bind("click",function(){
        var strContent = $("#txtContent").val()+"<:"+this.id+":>"
        $("#txtContent").val(strContent)
    })
});

/**
 * 发送聊天内容函数
 * @param content 聊天内容
 */
function sendContent(content){
    $.ajax({
        type:'get',
        data:{"content":content},
        url:'sentContent',
        dataType:'text',
        success:function(data){
            if(data=="success"){
                getMessageList();
                $("#txtContent").val("")
            }else{
                alert("发送失败");
                return false
            }
        }
    })
}

/**
 * 取得聊天内容数据
 */
function getMessageList(){
    $.ajax({
        type:"get",
        url:"getMassageList",
        dataType:"text",
        success:function(data){
            $("#divContent").html(data);
        }
    })
    autoUpdContent(); //定时获取聊天信息
}

/**
 * 获取在线用户
 */
function getOnlineList(){
    $.ajax({
        type:"get",
        url:"getOnlineList",
        dataType:"text",
        success:function(data){
            $("#divOnLine").html(data)
        }
    })
}

/**
 * 设置表情图标函数
 */
function initFace(){
    var strHTML = "";
    for(var i=1;i<=10;i++){
        strHTML += "<img src='/chatroom/static/pic/"+i+".gif' id='"+i+"'/>"
    }
    $("#divFace").html(strHTML)
}

/**
 * 定时返回聊天内容和在线人数
 */
function autoUpdContent(){
    setTimeout(getMessageList,5000)
    setTimeout(getOnlineList,6000)
}