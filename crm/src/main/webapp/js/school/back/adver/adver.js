//添加广告信息
function to_add_adver(){
	 layer.open({
		  type: 2,
		  title: ['添加广告'],
		  area: ['800px', '550px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_adver/to_save_adver.jr",
		  end: function () {
			  adver_jump_page(1);
          }
		  });
}
//广告列表翻页-上一页 下一页
function adver_jump_page(page){
	jiazaidonghua();
	$.post("/back_adver/get_advertising_main.jr",{
		'pageNumber' : page,
		'limit' : 8
	},function(data){
		$("#conten_list").html(data);
	})
}
//查询关键字
function seacher_adver(){
	var drop_menu=$('#select_val').val();//获取查询下拉菜单的值
	if(drop_menu!=0){
		jiazaidonghua();
		$.post("/back_adver/get_advertising_main.jr", {
			'adver_type':drop_menu,
			'pageNumber' : 1,
			'limit' : 8
		}, function(data) {
			if (data != null) {
				$('#conten_list').html(data);
			}
		});
	}else{
			jiazaidonghua();
			$.post("/back_adver/get_advertising_main.jr",{
				'pageNumber' : 1,
				'limit' : 8
			},function(data){
				$("#conten_list").html(data);
			})
	}
}
//修改广告信息
function to_update_adver(adver_id){
	 layer.open({
		  type: 2,
		  title: ['添加广告'],
		  area: ['800px', '550px'],
		  shadeClose: false, //点击遮罩关闭
		  content: "/back_adver/to_update_adver.jr?adver_id="+adver_id,
		  end: function () {
			  adver_jump_page(1);
         }
		  });
}
//删除广告信息
function delete_adver(adver_id){
	layer.confirm("提示：您好，确定要删除吗？",function(){
	layer.closeAll('dialog');
	jiazaidonghua();
	$.post("/back_adver/delete_adver.jr",{
		'adver_id':adver_id
	},function(data){
		if(data==1){
			tanchuang("删除成功！");
			adver_jump_page(1);
		}else{
			tanchuang("删除失败！");
		}
	});
	})
}
//设置广告上下架
function update_isshow(adver_id,type){
	$.post("/back_adver/update_isshow.jr",{
		'adver_id':adver_id,
		'is_show':type
	},function(data){
		if(data==1){
			tanchuang("设置成功！");
			adver_jump_page(1);
		}else{
			tanchuang("设置失败！");
		}
	});
}
//信息弹窗
function tanchuang(content){
	layer.alert(content, {
		  icon: 1,
		  skin: 'layer-ext-moon' 
		})
}