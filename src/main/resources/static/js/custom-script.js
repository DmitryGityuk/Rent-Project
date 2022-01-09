(function($) {
	
	"use strict";
	
	//Hide Loading Box (Preloader)
	function handlePreloader() {
		if($('.preloader').length){
			$('body').addClass('page-loaded');
			$('.preloader').delay(1000).fadeOut(0);
		}
	}

	//Update Header Style and Scroll to Top
	function headerStyle() {
		if($('.main-header').length){
			var windowpos = $(window).scrollTop();
			var siteHeader = $('.main-header');
			var scrollLink = $('.scroll-to-top');
			var sticky_header = $('.main-header .sticky-header');
			if (windowpos > 120) {
				siteHeader.addClass('fixed-header');
				sticky_header.addClass("animated slideInDown");
				scrollLink.fadeIn(300);
			} else {
				siteHeader.removeClass('fixed-header');
				sticky_header.removeClass("animated slideInDown");
				scrollLink.fadeOut(300);
			}
		}
	}
	
	headerStyle();

	//Submenu Dropdown Toggle
	if($('.main-header li.dropdown ul').length){
		$('.main-header .navigation li.dropdown').append('<div class="dropdown-btn"><span class="fa fa-angle-right"></span></div>');
		
	}

	//Mobile Nav Hide Show
	if($('.mobile-menu').length){
		
		$('.mobile-menu .menu-box').mCustomScrollbar();
		
		var mobileMenuContent = $('.main-header .nav-outer .main-menu').html();
		$('.mobile-menu .menu-box .menu-outer').append(mobileMenuContent);
		$('.sticky-header .main-menu').append(mobileMenuContent);
		
		//Dropdown Button
		$('.mobile-menu .navigation li.dropdown > a').on('click', function(e) {
			e.preventDefault();
			//$(this).parent('li').toggleClass('current');
			$(this).next('ul').slideToggle(500);
		});
		//Menu Toggle Btn
		$('.mobile-nav-toggler').on('click', function() {
			$('body').addClass('mobile-menu-visible');
		});

		//Menu Toggle Btn
		$('.mobile-menu .menu-backdrop,.mobile-menu .close-btn').on('click', function() {
			$('body').removeClass('mobile-menu-visible');
		});

		$(document).keydown(function(e){
	        if(e.keyCode === 27) {
	            $('body').removeClass('mobile-menu-visible');
	        }
	    });
	}
	
	//Main Slider / Banner Carousel
	if ($('.banner-carousel').length) {
		$('.banner-carousel').owlCarousel({
			loop:true,
			animateOut: 'fadeOut',
            animateIn: 'fadeIn',
			margin:0,
			nav:true,
			smartSpeed: 500,
			autoplay: 6000,
			autoplayTimeout:7000,
			navText: [ '<span class="icon flaticon-left-arrow-1"></span>', '<span class="icon flaticon-arrows-10"></span>' ],
			responsive:{
				0:{
					items:1
				},
				600:{
					items:1
				},
				800:{
					items:1
				},
				1024:{
					items:1
				}
			}
		});
	}

	//Testimonial Carousel
	if ($('.testimonial-carousel').length) {
		$('.testimonial-carousel').owlCarousel({
			loop:true,
			margin:0,
			nav:true,
			smartSpeed: 700,
			autoplay: 5000,
			autoplayTimeout:5000,
			navText: [ '<span class="icon flaticon-left-arrow-1"></span>', '<span class="icon flaticon-arrows-10"></span>' ],
			responsive:{
				0:{
					items:1
				},
				600:{
					items:1
				},
				768:{
					items:2
				},
				992:{
					items:2
				},
				1200:{
					items:3
				}
			}
		});
	}

	//Rooms Carousel
	if ($('.rooms-carousel').length) {
		$('.rooms-carousel').owlCarousel({
			loop:true,
			margin:0,
			nav:true,
			smartSpeed: 700,
			autoplay: 5000,
			autoplayTimeout:5000,
			navText: [ '<span class="icon flaticon-left-arrow-1"></span>', '<span class="icon flaticon-arrows-10"></span>' ],
			responsive:{
				0:{
					items:1
				},
				600:{
					items:1
				},
				768:{
					items:2
				},
				992:{
					items:2
				},
				1200:{
					items:3
				}
			}
		});
	}

	//Gallery Carousel
	if ($('.gallery-carousel').length) {
		$('.gallery-carousel').owlCarousel({
			loop:true,
			margin:0,
			nav:true,
			smartSpeed: 700,
			autoplay: 5000,
			autoplayTimeout:5000,
			navText: [ '<span class="icon flaticon-left-arrow-1"></span>', '<span class="icon flaticon-arrows-10"></span>' ],
			responsive:{
				0:{
					items:1
				},
				600:{
					items:2
				},
				768:{
					items:2
				},
				992:{
					items:3
				},
				1200:{
					items:4
				}
			}
		});
	}

	//Default Carousel
	if ($('.default-carousel').length) {
		$('.default-carousel').owlCarousel({
			loop:true,
			animateOut: 'fadeOut',
            animateIn: 'fadeIn',
			margin:0,
			nav:true,
			smartSpeed: 500,
			autoplay: 5000,
			autoplayTimeout:5000,
			navText: [ '<span class="icon flaticon-left-arrow-1"></span>', '<span class="icon flaticon-arrows-10"></span>' ],
			responsive:{
				0:{
					items:1
				},
				600:{
					items:1
				},
				800:{
					items:1
				},
				1024:{
					items:1
				}
			}
		});
	}

	//Single Item Carousel
	if ($('.single-item-carousel').length) {
		$('.single-item-carousel').owlCarousel({
			loop:true,
			margin:30,
			nav:true,
			smartSpeed: 500,
			autoplay: 5000,
			autoplayTimeout:5000,
			navText: [ '<span class="icon flaticon-left-arrow-1"></span>', '<span class="icon flaticon-arrows-10"></span>' ],
			responsive:{
				0:{
					items:1
				},
				600:{
					items:1
				},
				800:{
					items:1
				},
				1024:{
					items:1
				}
			}
		});
	}
	
	//Fact Counter + Text Count
	if($('.count-box').length){
		$('.count-box').appear(function(){
	
			var $t = $(this),
				n = $t.find(".count-text").attr("data-stop"),
				r = parseInt($t.find(".count-text").attr("data-speed"), 10);
				
			if (!$t.hasClass("counted")) {
				$t.addClass("counted");
				$({
					countNum: $t.find(".count-text").text()
				}).animate({
					countNum: n
				}, {
					duration: r,
					easing: "linear",
					step: function() {
						$t.find(".count-text").text(Math.floor(this.countNum));
					},
					complete: function() {
						$t.find(".count-text").text(this.countNum);
					}
				});
			}
			
		},{accY: 0});
	}

	//Custom Checkbox List
	if($('.check-sel-box').length){
		var thisBox = $('.check-sel-box');
		var thisBoxSelBtn = $(thisBox).find('.check-sel-btn');
		var thisBoxSelList = $(thisBox).find('.check-sel-droplist');
		var SelBtnAdultBox = $(thisBoxSelBtn).find('.adult-info');
		var SelBtnChildBox = $(thisBoxSelBtn).find('.child-info');

		//Menu Toggle Btn
		$(thisBoxSelBtn).on('click', function() {
			$(thisBox).find(thisBoxSelList).fadeToggle(300);
		});

		$(thisBox).find('.adult-block').on('click', function() {
			var CheckVal = $(this).find('input[type="radio"]').attr('value');
			$(SelBtnAdultBox).html(CheckVal);
		});

		$(thisBox).find('.child-block').on('click', function() {
			var CheckVal = $(this).find('input[type="radio"]').attr('value');
			$(SelBtnChildBox).html(CheckVal);
		});

		$(document).keydown(function(e){
	        if(e.keyCode === 27) {
	            $('.check-sel-droplist').fadeOut(300);
	        }
	    });
	}
	 
	
	//Tabs Box
	if($('.tabs-box').length){
		$('.tabs-box .tab-buttons .tab-btn').on('click', function(e) {
			e.preventDefault();
			var target = $($(this).attr('data-tab'));
			
			if ($(target).is(':visible')){
				return false;
			}else{
				target.parents('.tabs-box').find('.tab-buttons').find('.tab-btn').removeClass('active-btn');
				$(this).addClass('active-btn');
				target.parents('.tabs-box').find('.tabs-content').find('.tab').fadeOut(0);
				target.parents('.tabs-box').find('.tabs-content').find('.tab').removeClass('active-tab');
				$(target).fadeIn(300);
				$(target).addClass('active-tab');
			}
		});
	}
	
	//Accordion Box
	if($('.accordion-box').length){
		$(".accordion-box").on('click', '.acc-btn', function() {
			
			var outerBox = $(this).parents('.accordion-box');
			var target = $(this).parents('.accordion');
			
			if ($(this).next('.acc-content').is(':visible')){
				//return false;
				$(this).removeClass('active');
				$(this).next('.acc-content').slideUp(300);
				$(outerBox).children('.accordion').removeClass('active-block');
			}else{
				$(outerBox).find('.accordion .acc-btn').removeClass('active');
				$(this).addClass('active');
				$(outerBox).children('.accordion').removeClass('active-block');
				$(outerBox).find('.accordion').children('.acc-content').slideUp(300);
				target.addClass('active-block');
				$(this).next('.acc-content').slideDown(300);	
			}
		});	
	}
	
	//Custom Seclect Box
	if($('.custom-select-box').length){
		$('.custom-select-box').selectmenu().selectmenu('menuWidget').addClass('overflow');
	}

	//Datepicker
	if($('.date-picker').length){
		$('.date-picker').datepicker();
	}

	//LightBox / Fancybox
	if($('.lightbox-image').length) {
		$('.lightbox-image').fancybox({
			openEffect  : 'fade',
			closeEffect : 'fade',
			helpers : {
				media : {}
			}
		});
	}

	//MixitUp Gallery Filters
	 if($('.filter-list').length){
	 	 $('.filter-list').mixItUp({});
	 }

	//Default Masonry
	function enableDefaultMasonry() {
		if($('.masonry-container').length){

			var winDow = $(window);
			// Needed variables
			var $container=$('.masonry-container');

			$container.isotope({
				itemSelector: '.masonry-item',
				 masonry: {
					columnWidth : '.column-width'
				 },
				animationOptions:{
					duration:500,
					easing:'linear'
				}
			});
		}
	}
	enableDefaultMasonry();

	//Sortable Masonary with Filters
	function sortableMasonry() {
		if($('.sortable-masonry').length){
	
			var winDow = $(window);
			// Needed variables
			var $container=$('.sortable-masonry .items-container');
			var $filter=$('.filter-btns');
	
			$container.isotope({
				filter:'*',
				 masonry: {
					columnWidth : '.masonry-item'
				 },
				animationOptions:{
					duration:500,
					easing:'linear'
				}
			});
			
	
			// Isotope Filter 
			$filter.find('li').on('click', function(){
				var selector = $(this).attr('data-filter');
	
				try {
					$container.isotope({ 
						filter	: selector,
						animationOptions: {
							duration: 500,
							easing	: 'linear',
							queue	: false
						}
					});
				} catch(err) {
	
				}
				return false;
			});
	
	
			winDow.on('resize', function(){
				var selector = $filter.find('li.active').attr('data-filter');

				$container.isotope({ 
					filter	: selector,
					animationOptions: {
						duration: 500,
						easing	: 'linear',
						queue	: false
					}
				});
			});
	
	
			var filterItemA	= $('.filter-btns li');
	
			filterItemA.on('click', function(){
				var $this = $(this);
				if ( !$this.hasClass('active')) {
					filterItemA.removeClass('active');
					$this.addClass('active');
				}
			});
		}
	}
	sortableMasonry();
	
	//Contact Form Validation
	if($('#contact-form').length){
		$('#contact-form').validate({
			rules: {
				username: {
					required: true
				},
				email: {
					required: true,
					email: true
				},
				phone: {
					required: true
				},
				message: {
					required: true
				}
			}
		});
	}
	
	// Scroll to a Specific Div
	if($('.scroll-to-target').length){
		$(".scroll-to-target").on('click', function(e) {
			e.preventDefault();
			var target = $(this).attr('data-target');
		   // animate
		   $('html, body').animate({
			   scrollTop: $(target).offset().top
			 }, 1500);
	
		});
	}
	
	// Elements Animation
	if($('.wow').length){
		var wow = new WOW(
		  {
			boxClass:     'wow',      // animated element css class (default is wow)
			animateClass: 'animated', // animation css class (default is animated)
			offset:       0,          // distance to the element when triggering the animation (default is 0)
			mobile:       false,       // trigger animations on mobile devices (default is true)
			live:         true       // act on asynchronously loaded content (default is true)
		  }
		);
		wow.init();
	}


/* ==========================================================================
   When document is Scrollig, do
   ========================================================================== */
	
	$(window).on('scroll', function() {
		headerStyle();
	});

/* ==========================================================================
   When document is Resized, do
   ========================================================================== */
	
	$(window).on('resize', function() {
		
	});	

/* ==========================================================================
   When document is loading, do
   ========================================================================== */
	
	$(window).on('load', function() {
		handlePreloader();
		enableDefaultMasonry();
		sortableMasonry();
	});	

})(window.jQuery);