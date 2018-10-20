<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>多文件拖动上传</title>
<script type="text/javascript" src="js/jquery3.0.0.js"></script>
<style>
        *{
            margin:0;
            padding:0;
        }
        #box{
            width: 338px;
            height: 300px;
            background: #ccc;
            background-image: url("img/upload.png");
            background-size: 330px;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
	<div id="box"></div>
    <form>
        <input type="button" value="提交">
    </form>
</body>
<script>
    var d = new FormData();
    var box=document.getElementById('box');
    box.ondragover=function (e){//图片被拖动到box时触发
            e.preventDefault();//阻止浏览器直接打开图片
    }
    box.ondrop=function (e){//图片被放置到box上时触发
        e.preventDefault();
        // alert(e.dataTransfer.files.length)
        for (var i = 0; i < e.dataTransfer.files.length; i++) {
            var f=e.dataTransfer.files[i];//获取到每一个上传的文件对象
            d.append("file",f);
            var fr=new FileReader();//实例FileReader对象
            fr.readAsDataURL(f);//把上传的文件对象转换成url
            fr.onload=function (e){
                // console.log(e);//输出到控制台
                // var Url=e.target.result;//上传文件的URL
                var Url=this.result;//上传文件的URL
                box.innerHTML+='<img class="preview" src="'+Url+'" alt="" style="width: 97px;height: 99px">';
            }
        }
    }
    $("input:button").click(function () {
    	alert("454");
        $.ajax({
            url:'${pageContext.request.contextPath}/new/upload.do',
            data:d,
            type:'post',
            contentType: false,
            processData: false,
            dataType:'json',
            success:function (result) {
                if(result.flag=="success") {
                    alert("上传成功！");
                }else {
                    alert("上传失败！");
                }
                $(".preview").remove();
                d = new FormData();//重置formdata对象
            }
        })
    })
</script>
</html>