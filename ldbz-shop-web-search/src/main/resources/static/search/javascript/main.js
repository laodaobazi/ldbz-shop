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

        var searchButton = function() {
            var showsearch = $('.show-search button');
            showsearch.on('click',function() {
                $('.show-search').parent('div').children('.top-search.style1').toggleClass('active');
                showsearch.toggleClass('active');
            });
        }; // Show Search

        var searchFilterbox = function(){
			var buttonFilter=$('.filter');
				buttonFilter.on('click',function(){
					$('.box-filter').toggleClass('active');
				});
			};

        //商品滚动
        var slideProduct = function() {
            $(".owl-carousel-1").owlCarousel({
                autoplay: true,
                nav: true,
                dots: false,
                responsive: true,
                margin:0,
                loop:true,
                items:1,
            });
        };// slide Product

        //商品推荐
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
        
        //页头广告
        var slideProduct_s6 = function() {
            $(".owl-carousel-16").owlCarousel({
                autoplay:true,
                nav: false,
                dots: true,
                responsive: true,
                margin:0,
                loop:true,
                items:1
            });
        };// slide Product s6


        var tabImagebox = function() {
            var speed = 1000;
            $('.flat-imagebox').each(function() {
                $(this).find('.tab-list').children().first().addClass('active'),
                $(this).find('.box-product').children('.row').first().show().siblings().hide(),
                $(this).find('.tab-list').children('li').on('click', function(e){
                    var liActive = $(this).index();
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).addClass('active').closest('.flat-imagebox').find('.box-product').children('.row').eq(liActive).fadeIn(1000).show().siblings().hide();
                    e.preventDefault();
                });
            });
        }; // Tab Imagebox

        var tabImagebox_s2 = function() {
            var speed = 1000;
            $('.flat-imagebox').each(function() {
                $(this).find('.tab-list').children().first().addClass('active'),
                $(this).find('.tab-item').children('.row').first().show().siblings().hide(),
                $(this).find('.tab-list').children('li').on('click', function(e){
                    var liActive = $(this).index();
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).addClass('active').closest('.flat-imagebox').find('.tab-item').children('.row').eq(liActive).fadeIn(1000).show().siblings().hide();
                    e.preventDefault();
                });
            });
        }; // Tab Imagebox

        //切换商品显示方式
        var tabSortproduct = function() {
            $('.wrap-imagebox').each(function() {
                $(this).find('ul.icons').children('li').first().addClass('active');
                $(this).find('.tab-product').children('.sort-box').first().show().siblings().hide();
                $(this).find('ul.icons').children('li').on('click', function(e) {
                    var liActive = $(this).index();
                    $(this).addClass('active').siblings().removeClass('active');
                     $(this).closest('.wrap-imagebox').find('.tab-product').children('.sort-box').eq(liActive).fadeIn(1000).show().siblings().hide();
                    e.preventDefault();
                });
            });
        };


        //筛选价格
        var FilterPrice = function() {
            if( $().slider ) {
                $( function() {
                    $( "#slider-range" ).slider({
                      range: true,
                      min: 0,
                      max: 9999,
                      values: [ 0, 9999 ],
                      slide: function( event, ui ) {
                        $( "#amount" ).val( ui.values[ 0 ] + "￥" + " - " + ui.values[ 1 ] + "￥" );
                        $("#priceFrom").val(ui.values[0]);
                        $("#priceTo").val(ui.values[1]);
                        getItemsBySolr();
                      }
                    });
                    $( "#amount" ).val( $( "#slider-range" ).slider( "values", 0 ) + "￥" + " - " + $( "#slider-range" ).slider( "values", 1 ) + "￥" );
                    $("#priceFrom").val($( "#slider-range" ).slider( "values", 0 ));
                    $("#priceTo").val($( "#slider-range" ).slider( "values", 1 ));
                });
            }
        }; // Filter Price


        var toggleWidget = function() {
            $( ".widget .widget-title h3 span" ).on('click', function() {
                $(this).toggleClass('active');
              $(this).closest('.widget').children('.widget-content').slideToggle(300);
            });
        }; // Toggle Widget

        var toggleCatlist = function() {
            $('.cat-list.style1').each(function() {
                $(this).children('li').children('ul.cat-child').hide();
                // $(this).children('li').children('ul.cat-child').first().show()
                $( ".cat-list.style1 li span" ).on('click', function() {
                    $(this).parent('li').toggleClass('active');
                    $(this).toggleClass('active');
                    $(this).parent('li').children('ul.cat-child').slideToggle(300);
                    // var liActive = $(this).index(),
                    // contentActive = $(this).parent('li').siblings().removeClass('active').children('ul-cat-child').eq(liActive);
                    // contentActive.addClass('active').parent('li').fadeIn("slow");
                    // contentActive.parent('li').siblings().removeClass('active');
                    // $(this).parent('li').addClass('active').children('.ul-cat-child').eq(liActive).siblings().hide();
                });
            })
        }; // Toggle Cat List

        var toggleDropdown = function() {
            $( ".form-box .form-box-content .dropdown-title" ).on('click', function() {
                $(this).toggleClass('active');
                $(this).parent('.form-box-content').children('ul').slideToggle(300);
            });
        }; // Toggle Dropdown

        var toggleLocation = function() {
            $( ".location .location-content .select-location select" ).on('click', function() {
                $(this).toggleClass('active');
                $(this).closest('.location-content').children('ul.location-list').slideToggle(300);
            });
        }; // Toggle Location

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

        var showAllcat = function() {
    		//显示购物车中的商品数量
        	var arr = [] ;
        	var _cart = getCookie("_cart");
        	if(_cart){
        		_cart = Base64.decode(_cart);
    			arr = $.parseJSON(_cart);
    			var item_count = 0 , item_price = 0 ;
    			$(arr).each(function(i , o){
    				item_count += (o.count*1) ;
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
        

        //显示购物车中商品条目
        var showCartItems = function(){
        	var _cart = getCookie("_cart");
			_cart = Base64.decode(_cart);
			var arr = $.parseJSON(_cart);
			
        	$("#ul_items").empty();
        	var item_count = 0 , item_price = 0 ;
			$(arr).each(function(i , o){
				var item = '<li><div class="img-product"><img src="' + nginxImage + o.item_image + '" alt="" style="width:64px;height:64px;"></div>' +
								'<div class="info-product">' +
								'	<div class="name" style="width: 95%;">' + o.item_title + '</div>' +
								'	<div class="price"><span>' + o.count + ' x</span><span>' + o.item_price + '</span></div>' +
								'</div>' +
								'<div class="clearfix"></div><span item_code="' + o.item_code + '" class="delete" name="span_delete_item">x</span></li>';
				$("#ul_items").append(item);
				item_count = item_count*1 + o.count ;
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

        //置顶
        var goTop = function(){
            var gotop = $('.btn-scroll');
            gotop.on('click', function() {
                $('html, body').animate({ scrollTop: 0}, 800);
                return false;
            });
        }; // Go Top

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

        var removePreloader = function() { 
            $(window).on('load', function() {
            	$('.preloader').hide();
            });  
        }; //remove Preloader

    // Dom Ready
    $(function() {
        responsiveMenu();
        responsiveMenuMega_S2();
        responsiveMenuMega();
        searchButton();
        searchFilterbox();
        slideProduct();
        slideMostViewer();
        tabImagebox();
        tabImagebox_s2();
        tabSortproduct();
        overlay();
        FilterPrice();        
        toggleWidget();
        toggleCatlist();
        toggleDropdown();
        toggleLocation();
        showSuggestions();
        showAllcat();
        goTop();
        removePreloader();
        slideProduct_s6();
    });

})(jQuery);

function setCookie(name,value)
{
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString() + ";domain=" + document.domain + ";path=/";
	document.cookie = name + "_timestamp="+ new Date().getTime() + ";expires=" + exp.toGMTString() + ";domain=" + document.domain + ";path=/";;
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
		document.cookie= name + "="+cval+";expires="+exp.toGMTString() + ";domain=" + document.domain + ";path=/";
}