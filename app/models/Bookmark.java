package models;

import java.util.List;

import javax.persistence.*;

import play.db.ebean.Model;

@Entity
public class Bookmark extends Model{

	@Id
	public int id;
	
	public String username;
	public String url;
	
	static Finder<Integer, Bookmark> find = new Finder<Integer,Bookmark>(Integer.class, Bookmark.class);
	
	public Bookmark(String username, String url){
		this.username = username;
		this.url  = url;
	}
	
	public static void create(Bookmark b){
		b.save();
	}
	
	public static void create(String username, String url){
		new Bookmark(username, url).save();
	}
	
	public static List<Bookmark> all(String username){
		return find.where().eq("username", username).findList();
	}
	public static void delete(int id){
		find.byId(id).delete();
	}
}
