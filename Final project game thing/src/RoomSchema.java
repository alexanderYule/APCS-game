
    
import java.util.ArrayList;

import adsouza.shapes.Rectangle;


public class RoomSchema {
	
	public static final int LEVELS = 5 ;
	public static final int ROOMS = 3 ;
	private static Room[][] rooms= new Room[LEVELS][LEVELS] ;
	
	private static RoomSchema self = null ;
		
	private RoomSchema() {
		RoomSchema.setupRooms();		
	}
	
	public static void create(){
		if(self == null)
			self = new RoomSchema();
	}
	
	public static Room getRoom(int level, int room) {
		return rooms[level][room] ;
	}
	
	private static void setupRooms() {
		int level = 0 ;
		int room = 0 ;
		
		//Level 0 - Room 0
		
		
		
	/*	int roomID = 1 ;
		Rectangle[] walls = {
			new Rectangle(150,150,20,70,211,211,211),
			new Rectangle(150,150,200,20,211,211,211),
			new Rectangle(330,150,20,70,211,211,211),
			new Rectangle(570,150,20,70,211,211,211),
			new Rectangle(570,150,200,20,211,211,211),
			new Rectangle(750,150,20,70,211,211,211),
			new Rectangle(150,600,20,70,211,211,211),
			new Rectangle(150,650,200,20,211,211,211),
			new Rectangle(330,600,20,70,211,211,211),
			new Rectangle(570,600,20,70,211,211,211),
			new Rectangle(570,650,200,20,211,211,211),
			new Rectangle(750,600,20,70,211,211,211)
		};*/
		
		
		
		Rectangle[] walls = {
				new Rectangle(150,40,50,600,211,211,211),
				new Rectangle(150,640,150,50,211,211,211),
				new Rectangle(450,360,50,520,211,211,211),
				new Rectangle(370,310,130,50,211,211,211),
				new Rectangle(670,40,80,350,211,211,211),
				new Rectangle(687,687,200,200,211,211,211),

		};
		
		RangedEnemy[] rangedEnemies = {
			new RangedEnemy(50,80,1,0) ,
			new RangedEnemy(500,175,1,0) ,
			new RangedEnemy(175,615,1,0) ,
			new RangedEnemy(595,615,1,0) 
		} ;

		MeleeEnemy[] meleeEnemies = {
			new MeleeEnemy(800,100,4) 
		} ;
		
		StationEnemy[] stationEnemies = {
				new StationEnemy(380,820,1) 
		} ;
		
		rooms[level][room++] = new Room(1,walls,rangedEnemies,meleeEnemies, stationEnemies);

	}
	
	
}
