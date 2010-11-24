$('#content a').each(function () {
    $('<div class="fader" />').css('opacity', 0).prependTo(this);
}).hover(function () {
    $('img', this).stop().animate({
        marginLeft : 35
    }, 250);

    $('.fader', this).stop().animate({
        opacity : 0.15
    });

}, function () {
    $('img', this).stop().animate({
        marginLeft : 25
    }, 250);

    $('.fader', this).stop().animate({
        opacity : 0
    });
}).find('img').css('marginLeft', 25);

$('.navigation').each(function () {
    var $links = $(this).find('a'),
            panelIds = $links.map(function() {
                return this.hash;
            }).get().join(","),
            $panels = $(panelIds),
            $panelwrapper = $panels.filter(':first').parent(),
            delay = 500,
            heightOffset = 40; // we could add margin-top + margin-bottom + padding-top + padding-bottom of $panelwrapper

    $panels.hide();

    $links.click(function () {
        var link = this,
                $link = $(this);

        // ignore if already visible
        if ($link.is('.selected')) {
            return false;
        }

        $links.removeClass('selected');
        $link.addClass('selected');

        document.title = 'jQuery look: Tim Van Damme - ' + $link.text();

        if ($.support.opacity) {
            $panels.stop().animate({opacity: 0 }, delay);
        }

        $panelwrapper.stop().animate({
            height: 0
        }, delay, function () {
            var height = $panels.hide().filter(link.hash).css('opacity', 1).show().height() + heightOffset;

            $panelwrapper.animate({
                height: height
            }, delay);
        });
    });

    $links.filter(window.location.hash ? '[hash=' + window.location.hash + ']' : ':first').click();
});

