import java.util.ArrayList;

import adsouza.shapes.Rectangle;


public class RoomSchema {
	
	public static final int LEVELS = 5 ;
	public static final int ROOMS = 3 ;
	public static int wallColorR = 153 ;
	public static int wallColorG = 153 ;
	public static int wallColorB = 102 ;

	private static Room[][] rooms= new Room[LEVELS][ROOMS] ;
	
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
		int roomID = 1 ;
		Rectangle[] walls00 = {
			new Rectangle(150,150,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,150,200,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(330,150,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(570,150,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(570,150,200,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(750,150,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,600,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,650,200,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(330,600,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(570,600,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(570,650,200,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(750,600,20,70,wallColorR,wallColorG,wallColorB)
		};
		
		RangedEnemy[] rangedEnemies00 = {
			new RangedEnemy(175 + 70 * Math.random(),175,1,0) ,
			new RangedEnemy(595 + 70 * Math.random(),175,1,0) ,
			new RangedEnemy(175 + 70 * Math.random(),607,1,0) ,
			new RangedEnemy(595 + 70 * Math.random(),607,1,0) 
		} ;

		MeleeEnemy[] meleeEnemies00 = {
			new MeleeEnemy(460,100,2) 
		} ;
		
		StationEnemy[] stationEnemies00 = {
		} ;

		rooms[level][room++] = new Room(roomID,walls00,rangedEnemies00,meleeEnemies00,stationEnemies00);

		//Level 0 - Room 1
		roomID = 2 ;
		Rectangle[] walls01 = {
			new Rectangle(150,150,20,240,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,390,620,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(750,150,20,240,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,530,20,240,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,530,620,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(750,530,20,240,wallColorR,wallColorG,wallColorB)
		};
		
		RangedEnemy[] rangedEnemies01 = {
			new RangedEnemy(175,175,1,0) ,
			new RangedEnemy(525,250,1,0) ,
			new RangedEnemy(175,650,1,0) ,
			new RangedEnemy(525,720,1,0) 
		} ;

		MeleeEnemy[] meleeEnemies01 = {
			new MeleeEnemy(460,460,2) 
		} ;
		
		StationEnemy[] stationEnemies01 = {
		} ;

		rooms[level][room++] = new Room(roomID,walls01,rangedEnemies01,meleeEnemies01,stationEnemies01);
		
		//Level 0 - Room 2
		roomID = 3 ;
		Rectangle[] walls02 = {
			new Rectangle(150,150,120,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(250,150,20,500,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,650,120,20,wallColorR,wallColorG,wallColorB),

			new Rectangle(250,400,400,20,wallColorR,wallColorG,wallColorB),

			new Rectangle(650,150,120,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(650,150,20,500,wallColorR,wallColorG,wallColorB),
			new Rectangle(650,650,120,20,wallColorR,wallColorG,wallColorB),
		};
		
		RangedEnemy[] rangedEnemies02 = {
			new RangedEnemy(155,175,0,2) ,
			new RangedEnemy(210,600,0,-2) ,
			new RangedEnemy(300,175,1,0) ,
			new RangedEnemy(625,250,-1,0) ,
			new RangedEnemy(300,350,1,0) ,
			new RangedEnemy(625,450,1,0) ,
			new RangedEnemy(300,525,-1,0) ,
			new RangedEnemy(625,625,1,0) ,
		} ;

		MeleeEnemy[] meleeEnemies02 = {
			new MeleeEnemy(460,100,2),
			new MeleeEnemy(460,800,2),
			new MeleeEnemy(700,200,1),
			new MeleeEnemy(700,600,1)
		} ;
		
		StationEnemy[] stationEnemies02 = {
		} ;

		rooms[level++][room] = new Room(roomID,walls02,rangedEnemies02,meleeEnemies02,stationEnemies02);
		room = 0 ;
		
		Rectangle[] walls10 = {
				new Rectangle(150,40,50,600,211,211,211),
				new Rectangle(150,640,150,50,211,211,211),
				new Rectangle(450,360,50,520,211,211,211),
				new Rectangle(370,310,130,50,211,211,211),
				new Rectangle(670,40,80,350,211,211,211),
				new Rectangle(687,687,200,200,211,211,211),

		};
		
		RangedEnemy[] rangedEnemies10 = {
			new RangedEnemy(50,80,1,0) ,
			new RangedEnemy(500,175,1,0) ,
			new RangedEnemy(175,615,1,0) ,
			new RangedEnemy(595,615,1,0) 
		} ;

		MeleeEnemy[] meleeEnemies10 = {
			new MeleeEnemy(800,100,4) 
		} ;
		
		StationEnemy[] stationEnemies10 = {
				new StationEnemy(380,820,1) 
		} ;
		
		rooms[level][room++] = new Room(1,walls10,rangedEnemies10,meleeEnemies10, stationEnemies10);
		
	}
}