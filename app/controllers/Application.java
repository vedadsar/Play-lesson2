package controllers;

import models.*;
import play.*;
import play.data.Form;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	
	static Form<Name> input = new Form<Name>(Name.class);
	static String message = "Welcome";
	
    public static Result index() {
      String name = session("name");
      if(name == null){
    	  name="no name";
    	  return ok(index.render(message, name, input));	  
      }	  
      return redirect("/bookmarks");
    }
    
    public static Result recognize(){
    	String name = input.bindFromRequest().get().name;
    	session("name", name);
    	return redirect("/bookmarks");
    }
    static Form<Bookmark> newBookmark = new Form<Bookmark>(Bookmark.class);
    
    public static Result bookmarks(){
    	String name = session("name");
    	if(name == null)
    		return redirect("/");
    	
    	return ok(bookmark.render(newBookmark, name, Bookmark.all(name)));
    }
    
    public static Result addBookmark(){
    	String url = newBookmark.bindFromRequest().get().url;
    	String username = session("name");
    	Bookmark.create(username, url);
    	return redirect("/bookmarks");
    }

}
