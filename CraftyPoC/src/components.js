/**@
 * #Astroway
 * @category Input
 * Used to bind keys to directions and have the entity move accordingly
 * @trigger NewDirection - triggered when direction changes - { x:Number, y:Number } - New direction
 * @trigger Moved - triggered on movement on either x or y axis. If the entity has moved on both axes for diagonal movement the event is triggered twice - { x:Number, y:Number } - Old position
 */
Crafty.c("Astroway", {
    _speed: 3,
    _enableDirection: true,

    /*_keydown: function (e) {
      if (this._keys[e.key] && this._enableDirection)  {
      this._movement.x = this._keys[e.key].x;
      this._movement.y = this._keys[e.key].y;
      this._enableDirection = false;
      this.trigger('NewDirection', this._movement);
      }
      },

      _keyup: function (e) {
      if (this._keys[e.key]) {
      this._movement.x = 0;
      this._movement.y = 0;
      this.trigger('NewDirection', this._movement);
      }
      },*/

    // *new*
    _mouseclick: function (e) {
        if (this._enableDirection) {
            console.log("mousedown");
            var pos = Crafty.DOM.translate(e.clientX, e.clientY);

	    // ignore invalid 0 0 position - strange problem on ipad
	    if (pos.x == 0 || pos.y == 0) {
	        return false;
	    }

            var dx = pos.x - this._x;
            var dy = pos.y - this._y;
            var dd = Math.sqrt(dx*dx + dy*dy);
            this._movement.x = Math.round(1000 * dx / dd)/1000 * this._speed.x;
            this._movement.y = Math.round(1000 * dy / dd)/1000 * this._speed.y;
            this._enableDirection = false;
	    this.trigger('NewDirection', this._movement);
        }
    },
    // *wen*

    
    _enterframe: function () {
	if (this.disableControls) return;

	if (this._movement.x !== 0) {
	    this.x += this._movement.x;
	    this.trigger('Moved', { x: this.x - this._movement.x, y: this.y });
	}
	if (this._movement.y !== 0) {
	    this.y += this._movement.y;
	    this.trigger('Moved', { x: this.x, y: this.y - this._movement.y });
	}
    },

    _stopMovement: function () {
        this.x -= this._movement.x;
        this.y -= this._movement.y;        
        this._movement.x = 0;
        this._movement.y = 0;
        this._enableDirection = true;
    },

    /**@
     * #.multiway
     * @comp Multiway
     * @sign public this .multiway([Number speed,] Object keyBindings )
     * @param speed - Amount of pixels to move the entity whilst a key is down
     * @param keyBindings - What keys should make the entity go in which direction. Direction is specified in degrees
     * Constructor to initialize the speed and keyBindings. Component will listen to key events and move the entity appropriately.
     *
     * When direction changes a NewDirection event is triggered with an object detailing the new direction: {x: x_movement, y: y_movement}
     * When entity has moved on either x- or y-axis a Moved event is triggered with an object specifying the old position {x: old_x, y: old_y}
     * 
     * @example
     * ~~~
     * this.multiway(3, {UP_ARROW: -90, DOWN_ARROW: 90, RIGHT_ARROW: 0, LEFT_ARROW: 180});
     * this.multiway({x:3,y:1.5}, {UP_ARROW: -90, DOWN_ARROW: 90, RIGHT_ARROW: 0, LEFT_ARROW: 180});
     * this.multiway({W: -90, S: 90, D: 0, A: 180});
     * ~~~
     */
    astroway: function (speed) {
	this._movement = { x: 0, y: 0 };
	this._speed = speed ? { x: speed, y: speed} : { x: 3, y: 3 };
	this.disableControl();
	this.enableControl();

	return this;
    },

    /**@
     * #.enableControl
     * @comp Multiway
     * @sign public this .enableControl()
     * 
     * Enable the component to listen to key events.
     *
     * @example
     * ~~~
     * this.enableControl();
     * ~~~
     */
    enableControl: function() {
	this.bind("GlobalMouseDown", this._mouseclick) // *new*
            .bind("KeyDown", this._keydown)
        //	    .bind("KeyUp", this._keyup)
	    .bind("EnterFrame", this._enterframe);
	return this;
    },

    /**@
     * #.disableControl
     * @comp Multiway
     * @sign public this .disableControl()
     * 
     * Disable the component to listen to key events.
     *
     * @example
     * ~~~
     * this.disableControl();
     * ~~~
     */

    disableControl: function() {
	this.unbind("GlobalMouseDown", this._mouseclick) // *new*
            .unbind("KeyDown", this._keydown)
        //	    .unbind("KeyUp", this._keyup)
	    .unbind("EnterFrame", this._enterframe);
	return this;
    },

});



// The Grid component allows an element to be located
//  on a grid of tiles
Crafty.c('Grid', {
    init: function() {
	this.attr({
	    w: Game.map_grid.tile.width,
	    h: Game.map_grid.tile.height
	})
    },

    // Locate this entity at the given position on the grid
    at: function(x, y) {
	if (x === undefined && y === undefined) {
	    return { x: this.x/Game.map_grid.tile.width, y: this.y/Game.map_grid.tile.height }
	} else {
	    this.attr({ x: x * Game.map_grid.tile.width, y: y * Game.map_grid.tile.height });
	    return this;
	}
    }
});

// An "Actor" is an entity that is drawn in 2D on canvas
//  via our logical coordinate grid
Crafty.c('Actor', {
    init: function() {
	this.requires('2D, Canvas, Grid');
    },
});

// A Tree is just an Actor with a certain sprite
Crafty.c('Tree', {
    init: function() {
	this.requires('Actor, Solid, spr_tree');
    },
});

// A Bush is just an Actor with a certain sprite
Crafty.c('Bush', {
    init: function() {
	this.requires('Actor, Solid, spr_bush');
    },
});

// A Rock is just an Actor with a certain sprite
Crafty.c('Rock', {
    init: function() {
        this.requires('Actor, Solid, spr_rock');
    },
});

// This is the player-controlled character
Crafty.c('PlayerCharacter', {
    init: function() {
	this.requires('Actor, Astroway, Collision, spr_player, SpriteAnimation')
	    .astroway(6)
	    .stopOnSolids()
	    .onHit('Village', this.visitVillage)
    },

    // Registers a stop-movement function to be called when
    //  this entity hits an entity with the "Solid" component
    stopOnSolids: function() {
	this.onHit('Solid', this.stopMovement);
	return this;
    },

    // Stops the movement
    stopMovement: function() {
	//this._speed = 0;
        this.silenceControl = false;
	if (this._movement) {
            this._stopMovement();
	}
    },

    // Respond to this player visiting a village
    visitVillage: function(data) {
	villlage = data[0].obj;
	villlage.visit();
    }

});

// A village is a tile on the grid that the PC must visit in order to win the game
Crafty.c('Village', {
    init: function() {
	this.requires('Actor, spr_village');
    },

    // Process a visitation with this village
    visit: function() {
	this.destroy();
	Crafty.audio.play('knock');
	Crafty.trigger('VillageVisited', this);
    }
});
