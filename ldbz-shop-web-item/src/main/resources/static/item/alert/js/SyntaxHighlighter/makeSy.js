
    $(function () {
// Line wrap back
        var shLineWrap = function () {
            $('.syntaxhighlighter').each(function () {
// Fetch
                var $sh = $(this),
                    $gutter = $sh.find('td.gutter'),
                    $code = $sh.find('td.code');
// Cycle through lines
                $gutter.children('.line').each(function (i) {
// Fetch
                    var $gutterLine = $(this),
                        $codeLine = $code.find('.line:nth-child(' + (i + 1) + ')');
//alert($gutterLine);
// Fetch height
                    var height = $codeLine.height() || 0;
                    if (!height) {
                        height = 'auto';
                    } else {
                        height = height += 'px';
//alert(height);
                    }
// Copy height over
                    $gutterLine.attr('style', 'height: ' + height + ' !important'); // fix by Edi, for JQuery 1.7+ under Firefox 15.0
                   // console.debug($gutterLine.height(), height, $gutterLine.text(), $codeLine);
                });
            });
        };


// Line wrap back when syntax highlighter has done it's stuff
        var shLineWrapWhenReady = function () {
            if ($('.syntaxhighlighter').length === 0) {
                setTimeout(shLineWrapWhenReady, 10);
            } else {
                shLineWrap();
            }
        };  // Fire
        shLineWrapWhenReady();
    });
