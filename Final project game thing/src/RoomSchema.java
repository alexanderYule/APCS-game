import adsouza.shapes.Rectangle;


public class RoomSchema {
	
	public static final int LEVELS = 5 ;
	public static final int ROOMS = 3 ;
	private static Room[][] rooms= new Room[LEVELS][LEVELS];
	private static int level, room;
	
	private static RoomSchema self = null ;
		
	private RoomSchema() {
		level = 0;
		room = 0;
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
		
		//genWalls();
		//Level 0 - Room 0

			int roomID = 1 ;
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
			};
			
			RangedEnemy[] rangedEnemies = {
				new RangedEnemy(175,175,1,0) ,
				new RangedEnemy(595,175,1,0) ,
				new RangedEnemy(175,615,1,0) ,
				new RangedEnemy(595,615,1,0) 
			} ;
	
			MeleeEnemy[] meleeEnemies = {
				new MeleeEnemy(460,100,2) 
			} ;
		
		rooms[level][room++] = new Room(roomID,walls,rangedEnemies,meleeEnemies);
	}

	private Rectangle[][] genWalls() { //Generate random
		for(int i = 0; i < level; i++) {
			for(int j = 0; i < room; j++) {
				
			}
		}
		
		return null;
		
	}
	
	
}
