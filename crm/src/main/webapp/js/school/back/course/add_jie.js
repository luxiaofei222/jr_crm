var oTimer = null;
  //检查章的名称是否为null
  function check_jie_name(){
	  var zhang=$("#video_name").val();
	  if(zhang!=null&&zhang!=""){
			return true;
		}else{
			layer.msg('请输入章节名称！');
			return false;
		}
  }
  //监听上传进度
  function getProgress() {
		var now = new Date();
	    $.ajax({
	        type: "post",
	        dataType: "json",
	        url: "/back_video/progress.jr",
	        data: now.getTime(),
	        success: function(data) {
	            $("#progress_bar").css("width",data.percent);//上传的百分比
	            $("#has_upload").html("已上传："+data.hasread); 
	          	$("#baifenbi").html(data.percent);
	            $("#upload_speed").html("速度："+data.speed+"/s");//上传速度
	            if(data.percent=="100%"){
	            	clearInterval(oTimer);
	            }
	        },
	        error: function(err) {
	        	$("#progress_percent").html("Error");
	        }
	    });
	}
  /**
   * 提交上传文件
   */
  function fSubmit(video_id) {
	  $("#progress_id").removeClass("hide");
	  var file_up=$("#file_up").val();
	  var extStart=file_up.lastIndexOf(".");
      var ext=file_up.substring(extStart,file_up.length).toUpperCase();
  if(video_add()){
	 	 if(file_up!=null&&file_up!=""){
	   		  if(ext!=".MP4"&&ext!=".mp4"){
	   			  layer.msg('您上传的视频文件类型错误，目前只支持MP4格式');
	   		  }else{
	   			  if (check_jie_name()) {
	   			      oTimer = setInterval("getProgress()", 1000);
	   			      save_jie(video_id);
	   				  }
	   		  }
	   	  }else{
	   		  layer.msg('请选择您要上传的视频');
	   	  }
  }
  }
  //点击加入文件时
  function video_add(){
	  var fileName="";
	  var name = $("#file_up").val();
	  fileName = name.split("\\").pop();  
      //截取文件后缀名 fileName=fileName.substring(0, fileName.lastIndexOf("."));
	  var byteSize  = $("#file_up")[0].files[0].size;
	     if(byteSize<500*1024*1024){
	    	 $("#file_name").html(fileName);
	    	 return true;
	     }else{
	    	 layer.msg('文件大小不能超过500MB'); 
	    	 return false;
	     }
  }
  //保存章节名称
  function save_jie(video_id){
			$("#myform").ajaxSubmit({
				type : 'POST',
				url : "/back_video/save_video_jie.jr?video_id="+video_id,
				success : function(data) {
					if(data==1){
						//添加数据成功，关闭弹出窗之前，刷新列表页面的数据
	                    tanchuang('恭喜您，添加成功');
	                    location.reload();
					}else{
						tanchuang('很遗憾，添加失败');
					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					tanchuang('很遗憾，添加失败');
				}
			});
  }