Backbone.emulateHTTP = true;
Backbone.emulateJSON = true;


// define Models
var BookmarkModel = Backbone.Model.extend({
	defaults: {
		title: "defaultTitle",
		url: "defaultUrl"
	},
	validate: function (attrs) {
        if (!attrs.title) {
            return 'Please fill title field.';
        }
        if (!attrs.url) {
            return 'Please fill url field.';
        }
    },
	initialize: function() {
		console.log('This model has been initialized');
		this.on('invalid', function(model, error) {
			console.log(error);
		});
	}
});

var TagModel = Backbone.Model.extend({
});


// define Collections
var BookmarkListCollection = Backbone.Collection.extend({
	model: BookmarkModel,
  	url: "/backbone_basics/api/BookmarkList.php"
});

var TagListCollection = Backbone.Collection.extend({
  	model: TagModel,
  	url: "/backbone_basics/api/TagList.php"
});

// define Views
var BookmarkView = Backbone.View.extend({
	tagName: "li",
	template: _.template('<%= title %> (<%= url %>)'),
	initialize: function(){
		this.render();
	},
	render: function(){
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	},
	remove: function(){
		this.$el.remove();
	}
});

var BookmarkListView = Backbone.View.extend({
	el: $("#bookmarkList"),


	initialize: function(){
 		this.collection.on('add', this.addOne, this);
 		this.collection.on('reset', this.addAll, this);
 	},
 	addOne: function(bookmarkItem){
 		var view = new BookmarkView({model: bookmarkItem});
 		this.$el.append(view.render().el);
 	},
 	addAll: function(){
 		this.collection.forEach(this.addOne, this);
 	},
	render: function(){
		this.addAll();
	}
});

var BookmarkFormView = Backbone.View.extend({
	el: $("#form"),

	render: function () {
        return this;
    },

	events: {
		"submit #btnSave": "search",
		"click #btnSave": "save",
		"click #btnClear": "reset"
	},
	save: function(e) {
		e.preventDefault();

	    var options = {
	        success: function () {
	            alert('New bookmark was saved!');
	        },
	        error: function () {
	            alert('Opps, bookmark has not been submitted, please try again.');
	        }
	    };

	    var bookmark = new BookmarkModel();
	    bookmark.set({title: this.$el.find('input[id="title"]').val()}, {validate: true});
	    bookmark.set({url: this.$el.find('input[id="url"]').val()}, {validate: true});

	    this.model.save(bookmark, options);
	},
	reset: function(e){
		e.preventDefault();
	}
});




// define Router
var Router = Backbone.Router.extend({

	routes: {
    	
  	}

});

//var bookmarkModel = new BookmarkModel();
var bookmarkList = new BookmarkListCollection();
var bookmarkListView = new BookmarkListView({
	collection: bookmarkList
});
bookmarkList.fetch();
bookmarkListView.render();

var bookmarkFormView = new BookmarkFormView({model: new BookmarkModel()});