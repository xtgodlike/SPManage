jQuery(document).ready(function(){
	
	/*jQuery(".navBG").hover(function(e){
		jQuery(this).children(".nav-content").show(300);
		e.stopPropagation();
	});
	
	
	jQuery(".navBG").mouseout(function(e){
		jQuery(this).children(".nav-content").hide();
	});*/
	
	//设置子菜单
	jQuery(".navBG").hover(
		function(){
			jQuery(this).children(".nav-content").show();
			//jQuery(this).children(".nav-content").slideDown(300);	
		},
		function(){
			jQuery(this).children(".nav-content").hide();
			//jQuery(this).children(".nav-content").slideUp();
		}
	);
	
	/*jQuery(".navBG-li").live('click', function(event){
		var obj = jQuery(this).closest(".navBG");
		obj.find(".nav-title").eq(0).addClass("navBG-current");
	});*/
	
});