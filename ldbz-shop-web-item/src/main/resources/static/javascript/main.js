;(function($) {

   'use strict'
        var isMobile = {
            Android: function() {
                return navigator.userAgent.match(/Android/i);
            },
            BlackBerry: function() {
                return navigator.userAgent.match(/BlackBerry/i);
            },
            iOS: function() {
                return navigator.userAgent.match(/iPhone|iPad|iPod/i);
            },
            Opera: function() {
                return navigator.userAgent.match(/Opera Mini/i);
            },
            Windows: function() {
                return navigator.userAgent.match(/IEMobile/i);
            },
            any: function() {
                return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
            }
        }; // is Mobile

        var responsiveMenu = function() {
            var menuType = 'desktop';

            $(window).on('load resize', function() {
                var currMenuType = 'desktop';

                if ( matchMedia( 'only screen and (max-width: 991px)' ).matches ) {
                    currMenuType = 'mobile';
                }

                if ( currMenuType !== menuType ) {
                    menuType = currMenuType;

                    if ( currMenuType === 'mobile' ) {
                        var $mobileMenu = $('#mainnav').attr('id', 'mainnav-mobi').hide();
                        var hasChildMenu = $('#mainnav-mobi').find('li:has(ul)');
                        var hasChildMenuMega = $('#mainnav-mobi').find('li:has(div.submenu)');

                        $('#header').after($mobileMenu);
                        hasChildMenu.children('ul').hide();
                        hasChildMenu.children('a').after('<span class="btn-submenu"></span>');
                        hasChildMenuMega.children('div.submenu').hide();
                        $('ul.submenu-child').hide();
                        hasChildMenuMega.find('h3').append('<span class="btn-submenu-child"></span>');
                        $('.btn-menu').removeClass('active');

                    } else {
                        var $desktopMenu = $('#mainnav-mobi').attr('id', 'mainnav').removeAttr('style');
                        $desktopMenu.find('.submenu').removeAttr('style');
                        $('#header').find('.nav-wrap').append($desktopMenu);
                        $('.btn-submenu').remove();
                        $('ul.submenu-child').show();
                        $('h3').children('span').removeClass('btn-submenu-child');
                    }
                }
            });

            $('.btn-menu').on('click', function() {         
                $('#mainnav-mobi').slideToggle(300);
                $(this).toggleClass('active');
                return false;
            });

            $(document).on('click', '#mainnav-mobi li.has-mega-menu .row .btn-submenu-child', function(e) {
                $(this).toggleClass('active').parent('h3.cat-title').next('.submenu-child').slideToggle(400);
                e.stopImmediatePropagation();
                return false;
            });

            $(document).on('click', '#mainnav-mobi li .btn-submenu', function(e) {
                $(this).toggleClass('active').next('.submenu').slideToggle(400);
                e.stopImmediatePropagation();
                return false;
            });

        }; // Responsive Menu       

        var responsiveMenuMega_S2 = function() {
            var menuType = 'desktop';

            $(window).on('load resize', function() {
                var currMenuType = 'desktop';

                if ( matchMedia( 'only screen and (max-width: 991px)' ).matches ) {
                    currMenuType = 'mobile';
                }

                if ( currMenuType !== menuType ) {
                    menuType = currMenuType;

                    if ( $('body').hasClass('grid') ) {
                        if (currMenuType === 'mobile') {
                            var $mobileMenuMegaV2 = $('#mega-menu').attr('id', 'mega-mobile').hide();
                            var ChildMenuMegaV2 = $('.header-bottom').find('.grid-right');
                            var ChildDropmenuV2 = $('.drop-menu').children('.one-third');

                            $('.btn-mega').hide();
                            $('#header').after($mobileMenuMegaV2);
                            ChildMenuMegaV2.append('<div class="btn-menu-mega"><span></span></div>');

                            $('.drop-menu').hide();
                            $mobileMenuMegaV2.find('.dropdown').append('<span class="btn-dropdown"></span>');

                            ChildDropmenuV2.children('ul').hide();
                            $('.drop-menu').find('.cat-title').append('<span class="btn-dropdown-child"></span>');

                        } else {
                            var $desktopMenuMegaV2 = $('#mega-mobile').attr('id', 'mega-menu').removeAttr('style');

                            $desktopMenuMegaV2.find('.drop-menu').removeAttr('style');
                            $('.header-bottom.style1').find('.grid-left').append($desktopMenuMegaV2);
                        }
                    };

                };
                
            });

            $(document).on('click', '#mega-mobile ul.menu li a .btn-dropdown', function(e) {
                $(this).toggleClass('active').closest('li').children('.drop-menu').slideToggle(400);
                e.stopImmediatePropagation();
                return false;
            });

            $(document).on('click', '#mega-mobile .btn-dropdown-child', function(e) {
                $(this).toggleClass('active').closest('.one-third').children('ul').slideToggle(400);
                e.stopImmediatePropagation();
                return false;
            });

        }; // Responsive Menu Mega S2

        var responsiveMenuMega = function() {
            var menuType = 'desktop';

            $(window).on('load resize', function() {
                var currMenuType = 'desktop';

                if ( matchMedia( 'only screen and (max-width: 991px)' ).matches ) {
                    currMenuType = 'mobile';
                }

                if ( currMenuType !== menuType ) {
                    menuType = currMenuType;

                    if ( currMenuType === 'mobile' ) {
                        var $mobileMenuMega = $('#mega-menu').attr('id', 'mega-mobile').hide();
                        var ChildMenuMega = $('.header-bottom').find('.col-2');
                        var ChildDropmenu = $('.drop-menu').children('.one-third');

                        $('.btn-mega').hide();
                        $('#header').after($mobileMenuMega);
                        ChildMenuMega.append('<div class="btn-menu-mega"><span></span></div>');

                        $('.drop-menu').hide();
                        $mobileMenuMega.find('.dropdown').append('<span class="btn-dropdown"></span>');

                        ChildDropmenu.children('ul').hide();
                        $('.drop-menu').find('.cat-title').append('<span class="btn-dropdown-child"></span>');

                    } else {
                        var $desktopMenuMega = $('#mega-mobile').attr('id', 'mega-menu').removeAttr('style');

                        $('.btn-mega').show();
                        $desktopMenuMega.find('.drop-menu').removeAttr('style');
                        $('.header-bottom').find('.col-2').append($desktopMenuMega);
                        $('.btn-menu-mega').remove();
                        $('.btn-dropdown-child').remove();
                        $('.drop-menu').children('.one-third').children('ul').show();
                    }
                }
            });

            $(document).on('click', '.btn-menu-mega', function() {       
                $('#mega-mobile').slideToggle(300);
                $(this).toggleClass('active');
                return false;
            });

            $(document).on('click', '#mega-mobile ul.menu li a .btn-dropdown', function(e) {
                $(this).toggleClass('active').closest('li').children('.drop-menu').slideToggle(400);
                e.stopImmediatePropagation();
                return false;
            });

            $(document).on('click', '#mega-mobile .btn-dropdown-child', function(e) {
                $(this).toggleClass('active').closest('.one-third').children('ul').slideToggle(400);
                e.stopImmediatePropagation();
                return false;
            });

        }; // Responsive Menu Mega


		//最受欢迎商品滚动
        var slideMostViewer = function() {
            $(".owl-carousel-3").owlCarousel({
                autoplay:true,
                nav: true,
                dots: true,
                responsive: true,
                margin:30,
                loop:true,
                items:5,
                responsive:{
                    0:{
                        items: 2,
                        dots: false,
                        margin:10,
                    },
                    479:{
                        items: 2,
                        dots: false
                    },
                    600:{
                        items: 3,
                        dots: false
                    },
                    768:{
                        items: 4,
                         margin:20,
                    },
                    991:{
                        items: 4
                    },
                    1200: {
                        items: 5
                    }
                }
            });
        };// slide Most Viewer
        
        //商品明细页面，图片预览
        var flexProduct = function() {
            $('.flexslider').flexslider({
                animation: "slide",
                controlNav: "thumbnails"
            });
        }; // Flex Product


        //页头检索框遮罩
        var showSuggestions = function() {
            $( ".top-search form.form-search .box-search" ).each(function() {
                $( "form.form-search .box-search input" ).on('focus', (function() {
                    $(this).closest('.boxed').children('.overlay').css({
                        opacity: '1',
                        display: 'block'
                    });
                    $(this).parent('.box-search').children('.search-suggestions').css({
                        opacity: '1',
                        visibility: 'visible',
                        top: '77px'
                    });
                }));
                $( "form.form-search .box-search input" ).on('blur', (function() {
                    $(this).closest('.boxed').children('.overlay').css({
                        opacity: '0',
                        display: 'none'
                    });
                    $(this).parent('.box-search').children('.search-suggestions').css({
                        opacity: '0',
                        visibility: 'hidden',
                        top: '100px'
                    });
                }));
            });

            $( ".top-search.style1 form.form-search .box-search" ).each(function() {
                $( "form.form-search .box-search input" ).on('focus', (function() {
                    $(this).closest('.boxed').children('.overlay').css({
                        opacity: '1',
                        display: 'block'
                    });
                    $(this).parent('.box-search').children('.search-suggestions').css({
                        opacity: '1',
                        visibility: 'visible',
                        top: '50px'
                    });
                }));
            });
        }; // Toggle Location

        //显示页头购物车
        var showAllcat = function() {
    		//显示购物车中的商品数量
        	var arr = [] ;
        	var _cart = getCookie("_cart");
        	if(_cart){
        		_cart = Base64.decode(_cart);
    			arr = $.parseJSON(_cart);
    			var item_count = 0 , item_price = 0 ;
    			$(arr).each(function(i , o){
    				item_count += o.count ;
    				item_price += (o.count * o.item_price);
    			});
    			$("#span_cart_itemcount").text(item_count).show();
    			$("#span_cart_itemprice").text("￥"+item_price).show();
        	}else{
        		$("#span_cart_itemcount").text('').hide();
        	}

        	//页头下拉框检索
            $('.cat-wrap').each(function() {
                $(this).on('click', function() {
                    $(this).children('.all-categories').toggleClass('show');
                });
            });
            
            function showCartItems(){
            	var _cart = getCookie("_cart");
    			_cart = Base64.decode(_cart);
    			var arr = $.parseJSON(_cart);
    			
            	$("#ul_items").empty();
            	var item_count = 0 , item_price = 0 ;
    			$(arr).each(function(i , o){
    				var item = '<li><div class="img-product"><img src="' + o.item_image + '" alt="" style="width:64px;height:64px;"></div>' +
									'<div class="info-product">' +
									'	<div class="name" style="width: 95%;">' + o.item_title + '</div>' +
									'	<div class="price"><span>' + o.count + ' x</span><span>' + o.item_price + '</span></div>' +
									'</div>' +
									'<div class="clearfix"></div><span item_code="' + o.item_code + '" class="delete" name="span_delete_item">x</span></li>';
    				$("#ul_items").append(item);
    				item_count = item_count + o.count ;
    				item_price += (o.item_price*o.count) ;
    			});
    			$("#span_cart_itemcount").text(item_count).show();
    			$("#span_cart_itemprice").text("￥"+item_price).show();
    			
    			
    			//将商品从购物车删除
    			$("#ul_items").find('span[name="span_delete_item"]').click(function(){
    				var item_code = $(this).attr('item_code');
    				$(arr).each(function(i , o){
    					if(o.item_code == item_code){
    						if(o.count == 1){
    							arr.splice(i , 1);
    						}else{
    							o.count = o.count-1;
    						}
    					}
    				});
        			setCookie("_cart" , Base64.encode(JSON.stringify(arr)));
        			showCartItems();
    			});
    			
            }

            //显示购物车
            $("div[name='div_show_cart']").hover(function(){
        		var _cart = getCookie("_cart");
        		if(_cart){
        			showCartItems();
        			$(this).children("div:last-child").fadeIn(600);
        		}
            } , function(){
            	$(this).children("div:last-child").fadeOut(10);
            });
            
        };

        //商品明细页面下方Tab切换
        var tabProductDetail = function() {
            $('.flat-product-content').each(function() {
                $(this).find('ul.product-detail-bar').children().first().addClass('active');
                $(this).find('.container').children('.row').first().show().siblings().hide();
                $(this).find('ul.product-detail-bar').children('li').on('click', function(e) {
                    var liActive = $(this).index();
                    $(this).addClass('active').siblings().removeClass('active');
                     $(this).closest('.flat-product-content').find('.container').children('.row').eq(liActive).fadeIn(1000).show().siblings().hide();
                    e.preventDefault();
                });
            });
            $('.flat-product-content.style2').each(function() {
                $(this).find('ul.product-detail-bar').children().first().addClass('active');
                $(this).find('.col-md-12').children('div.row').first().show().siblings().hide();
                $(this).find('ul.product-detail-bar').children('li').on('click', function(e) {
                    var liActive = $(this).index();
                    $(this).addClass('active').siblings().removeClass('active');
                     $(this).closest('.flat-product-content.style2').find('.col-md-12').children('div.row').eq(liActive).fadeIn(1000).show().siblings().hide();
                    e.preventDefault();
                });
            });
        };

        //首页弹出框
        var popup = function() {
            $('.popup-newsletter').each( function() {
                $(this).closest('.boxed').children('.overlay').css({
                    opacity: '1',
                    display: 'block',
                    zIndex: '89999'
                });
                $(".popup span" ).on('click', function() {
                    $(this).closest('.popup-newsletter').hide(400);
                    $(this).closest('.boxed').children('.overlay').css({
                        opacity: '0',
                        display: 'none',
                         zIndex: '909'
                    });
                });
            });
        };

        //置顶
        var goTop = function(){
            var gotop = $('.btn-scroll');
            gotop.on('click', function() {
                $('html, body').animate({ scrollTop: 0}, 800, 'easeInOutExpo');
                return false;
            });
        };

        var overlay = function(){
            var megaMenu = $('ul.menu li');
            megaMenu.on('mouseover', function() {
                $(this).closest('.boxed').children('.overlay').css({
                    opacity: '1',
                    display: 'block',
                });
            });
            megaMenu.on('mouseleave', function() {
                $(this).closest('.boxed').children('.overlay').css({
                    opacity: '0',
                    display: 'none',
                });
            });
        }; // Overlay

        //隐藏加载状态
        var removePreloader = function() { 
            $(window).on('load', function() {
                setTimeout(function() {
                    $('.preloader').hide(); }, 300           
                ); 
            });  
        };

    // Dom Ready
    $(function() {
        responsiveMenu();
        tabProductDetail();
        responsiveMenuMega_S2();
        responsiveMenuMega();
        slideMostViewer();
        flexProduct();
        overlay();
        showSuggestions();
        showAllcat();
        goTop();//置顶
        popup();//首页弹出框
        removePreloader();//隐藏加载状态
    });

})(jQuery);

function setCookie(name,value)
{
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}


function getCookie(name)
{
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

function delCookie(name)
{
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
