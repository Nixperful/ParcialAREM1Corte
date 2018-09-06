(function($){

    "use strict";

    $(function() {

        var $html     = UIkit.$html,
            $win      = UIkit.$win,
            $doc      = UIkit.$doc,
            $body     = $('body'),
            scrollpos = {x: window.pageXOffset, y: window.pageYOffset};

        /**
	     * Share Single Post
	     */

        $win.on('scroll resize', (function(share, sharetop, top, offset, rtl){

            share = $('.bh-isblog-single .bh-article-share-top'),
            top   = 100;
            rtl   = (UIkit.langdirection == 'right');

            if(share.length) {

                offset  = share.offset(),
                sharetop = offset.top;

                var fn = function(){

                    if (offset.top - $win.scrollTop() <= top) {

                        share.css({
                            'position' : 'fixed',
                            'top'      : top
                        });

                        share.css(( rtl ? 'right' : 'left' ), rtl ? $win.width() - (share.parent().offset().left + share.parent().outerWidth()) - 90 : share.parent().offset().left - 90 );

                        if (share.offset().top - sharetop + share.outerHeight(true) >= share.parent().height()) {
                            share.css({
                                'top' : share.parent().height() - (share.offset().top - sharetop + share.outerHeight(true) - top),
                            });
                        }

                    } else {
                        share.css({
                            'position' : '',
                            'top'      : ''
                        });

                        share.css(( rtl ? 'right' : 'left' ), '' );
                    }

                    return fn;
                };

                return fn();

            }

        })());

        /**
	     * Post Reading Progress Bar
	     */

        var progressBar = $('.bh-navbar-reading-progress');

        if(progressBar.length) {

            var content       = $('#bh-main'),
                contentTop    = content.offset().top,
                winHeight     = $win.outerHeight(),
                contentHeight = content.outerHeight() - $('.bh-article-comments').outerHeight() - $('.bh-article-comments-form').outerHeight(),
                max, value;

            max = contentHeight - winHeight + contentTop;
            progressBar.attr('max', max);

            $doc.on('scroll', function(){

                value = $win.scrollTop();

                if(value > contentTop) {
                    progressBar.attr('value', value - contentTop);
                }

            });

        }

        /**
	     * Main Height
	     */

        $win.on('resize', (function(navbar, main, footer){

            navbar = $('.bh-navbar'),
            main   = $('.bh-main'),
    	    footer = $('.bh-footer');

            var fn = function(){
                setTimeout(function(){
                    main.css({ 'min-height' : $win.height() - navbar.outerHeight(true) - footer.outerHeight(true) + (main.outerHeight(true) - main.height() ) + ($body.innerWidth() - $body.width()) });
                }, 100);
                return fn;

            };

            return fn();

        })());

        /**
	     * Footer
	     */

        $win.on('resize', (function(main, footer){

            main   = $('.bh-main'),
    	    footer = $('.bh-footer');

            var fn = function(){
                setTimeout(function(){
                    main.css({ 'margin-bottom' : footer.outerHeight(true) });
                }, 100);
                return fn;

            };

            return fn();

        })());

	    /**
	     * To Top Scroller
	     */

	    var scroller = $('.bh-topscroller'),
	        offset   = 600,
	        state    = false;

	    $win.on('scroll', function(){

	        var scrolltop = $doc.scrollTop();

	        if (scrolltop > offset && !state) {
	            state = true;
	            scroller.toggleClass('uk-active');
	        }

	        if (scrolltop < offset && state) {
	            state = false;
	            scroller.toggleClass('uk-active');
	        }

	    });

        /*
         * Menu
         */

        var menu = $("#menu-main-menu");

        if (menu.length) {
            var lists = menu.find('[data-uk-dropdown]').attr('data-uk-dropdown', '{pos:\'bottom-center\'}').find('.uk-dropdown > .uk-nav-navbar');
        }

    });

})(jQuery);
