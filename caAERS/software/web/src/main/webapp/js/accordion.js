// accordion.js v2.0
//
// Copyright (c) 2007 stickmanlabs
// Author: Kevin P Miller | http://www.stickmanlabs.com
// 
// Accordion is freely distributable under the terms of an MIT-style license.
//
// I don't care what you think about the file size...
//   Be a pro: 
//	    http://www.thinkvitamin.com/features/webapps/serving-javascript-fast
//      http://rakaz.nl/item/make_your_pages_load_faster_by_combining_and_compressing_javascript_and_css_files
//
//################################################################################################################
// BJ : Note - MODIFIED this script
//################################################################################################################

if (typeof Effect == 'undefined')
  throw("You must have the script.aculo.us library to use this accordion");

var Accordion = Class.create({

    initialize: function(id, defaultExpandedCount) {
        if(!$(id)) throw("Attempted to initalize accordion with id: "+ id + " which was not found.");
        this.accordion = $(id);
        this.options = {
            toggleClass: "accordion-toggle",
            toggleActive: "accordion-toggle-active",
            contentClass: "accordion-content"
        }
        this.contents = this.accordion.select('div.'+this.options.contentClass);
        this.isAnimating = false;
        this.maxHeight = 0;
        this.current = defaultExpandedCount ? this.contents[defaultExpandedCount-1] : this.contents[0];
        this.toExpand = null;

        this.checkMaxHeight();
        this.initialHide();
        this.attachInitialMaxHeight();

        var clickHandler =  this.clickHandler.bindAsEventListener(this);
        //this.accordion.observe('click', clickHandler);
        this.contents.each(function(el){
        	el.previous(0).observe('click', clickHandler);
        });
    },

    expand: function(el) {
        this.toExpand = el.next('div.'+this.options.contentClass);
        if(this.current != this.toExpand){
			this.toExpand.show();
            this.animate();
        }
    },

    checkMaxHeight: function() {
        for(var i=0; i<this.contents.length; i++) {
            if(this.contents[i].getHeight() > this.maxHeight) {
                this.maxHeight = 'auto';
            }
        }
    },

    attachInitialMaxHeight: function() {
		this.current.previous('div.'+this.options.toggleClass).addClassName(this.options.toggleActive);
        if(this.current.getHeight() != this.maxHeight) this.current.setStyle({height: this.maxHeight});
    },

    clickHandler: function(e) {
        var el = e.element();
        if(el.hasClassName(this.options.toggleClass) && !this.isAnimating) {
            this.expand(el);
        }
    },

    initialHide: function(){
        for(var i=0; i<this.contents.length; i++){
            if(this.contents[i] != this.current) {
                this.contents[i].hide();
                this.contents[i].setStyle({height: 0});
            }
        }
    },

    animate: function() {
        var effects = new Array();
        var options = {
            sync: true,
            scaleFrom: 0,
            scaleContent: false,
            transition: Effect.Transitions.sinoidal,
            scaleMode: {
                originalHeight: this.maxHeight,
                originalWidth: this.accordion.getWidth()
            },
            scaleX: false,
            scaleY: true
        };

        effects.push(new Effect.Scale(this.toExpand, 100, options));

        options = {
            sync: true,
            scaleContent: false,
            transition: Effect.Transitions.sinoidal,
            scaleX: false,
            scaleY: true
        };

        effects.push(new Effect.Scale(this.current, 0, options));

        var myDuration = 0.75;

        new Effect.Parallel(effects, {
            duration: myDuration,
            fps: 35,
            queue: {
                position: 'end',
                scope: 'accordion'
            },
            beforeStart: function() {
                this.isAnimating = true;
                this.current.previous('div.'+this.options.toggleClass).removeClassName(this.options.toggleActive);
                this.toExpand.previous('div.'+this.options.toggleClass).addClassName(this.options.toggleActive);
            }.bind(this),
            afterFinish: function() {
                this.current.hide();
                this.toExpand.setStyle({ height: 'auto' });
                this.current = this.toExpand;
                this.isAnimating = false;
            }.bind(this)
        });
    }

});
