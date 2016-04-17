var yamsPlayer = {
    init: function() {
        var me = this;
        
        this.controls.initialUserInput.keypress(function (e) {
            var key = e.which;
            if(key == 13) { me.controls.startButton.trigger('click') }
        }); 
        this.controls.startButton.on('click', function(){me.startPlayback.call(me)});
        this.controls.nextUserInput.keypress(function (e) {
            var key = e.which;
            if(key == 13) { me.controls.addButton.trigger('click') }
        });         
        this.controls.addButton.on('click', function(){me.nextStep.call(me)})
        
        $('#artists').on('click', '.artist-add', function(){
            me.controls.nextUserInput.val($(this).text());
            me.controls.addButton.trigger('click');
        })
        
        $('#tags').on('click', '.tag-add', function(){
            me.controls.nextUserInput.val($(this).text());
            me.controls.addButton.trigger('click');
        })
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
        var me = this;
        
        $.ajax({
            method: "GET",
            contentType: "text/plain",
            data: data,
            crossDomain: true,
            xhrFields: {
                withCredentials: false
            },
            headers: {},
            url: "http://eprupetw0021:8080/magic",
            success: function(resp) {
                    me.builtRecomendationList(resp.artists, resp.tags)
                    me.builtPlaylist(resp.playlist);
                }
            });
    },
    
    builtRecomendationList: function(artists, tags) {
        this.controls.artists.empty();
        this.controls.tags.empty();
        
        for(var i = 0; i < artists.length; i++) {
            this.controls.artists.append('<span class="artist-add">' + artists[i]  + '</span>, ')
        }
        
        for(var i = 0; i < tags.length; i++) {
            this.controls.tags.append('<span class="tag-add">' + tags[i]  + '</span>, ')
        }
    },
    
    builtPlaylist: function(playlist) {
        this.controls.currentSong.attr('src', playlist[0].cover);
        this.controls.songTitle.text(playlist[0].title);
        this.controls.songAuthor.text(playlist[0].artist);
        
        for(var i = 0; i < playlist.length; i++) {
            $(this.controls.playlist[i]).attr('src', playlist[i].cover)
        }
        
        this.switchView();
    },
    
    switchView: function() {
        this.controls.startScreen.addClass("hidden");
        this.controls.listenScreen.removeClass("hidden");
        this.controls.playerBar.removeClass("hidden");
    },
    
    startPlayback: function() {
        var name = this.controls.initialUserInput.val(),
            data = {name: name, type: "artist", reset: "all"};
        
        this.getPlayList(data);
    },
    

    nextStep: function() {
        var name = this.controls.nextUserInput.val(),
            data = {name: name, type: "artist"};
        
        this.getPlayList(data);        
    }
}

