var yamsPlayer = {
    init: function() {
        var me = this;
        this.controls.initialUserInput.keypress(function (e) {
            var key = e.which;
            if(key == 13) { me.controls.startButton.trigger('click') }
        }); 
        this.controls.startButton.on('click', function(){me.startPlayback.call(me)});
    },
    
/*    getCookie: function(name) {
        var matches = document.cookie.match(new RegExp("(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    },
    
    setCookie: function(name, value, options) {
        options = options || {};

        var expires = options.expires;

        if (typeof expires == "number" && expires) {
            var d = new Date();
            d.setTime(d.getTime() + expires * 1000);
            expires = options.expires = d;
        }
        if (expires && expires.toUTCString) {
            options.expires = expires.toUTCString();
        }

        value = encodeURIComponent(value);

        var updatedCookie = name + "=" + value;

        for (var propName in options) {
            updatedCookie += "; " + propName;
            var propValue = options[propName];
            if (propValue !== true) {
              updatedCookie += "=" + propValue;
            }
        }
        document.cookie = updatedCookie;
    },*/
    
    getPlayList: function(data) {        
        $.ajax({
            method: "POST",
            contentType: "text/plain",
            data: data,
            crossDomain: true,
            xhrFields: {
                withCredentials: false
            },
            headers: {},
            url: "http://eprupetw0021:8080/getstep",
            success: function(resp) {console.log(resp)}
            });
    },
    
    startPlayback: function() {
        var name = this.controls.initialUserInput.val(),
            data = JSON.stringify([{name: name, inputType: "artist"}]);
        
        this.getPlayList(data);
        this.controls.startScreen.addClass("hidden");
        this.controls.listenScreen.removeClass("hidden");
        this.controls.playerBar.removeClass("hidden");

        console.info('Playback initiated');
    },
    

    
}

