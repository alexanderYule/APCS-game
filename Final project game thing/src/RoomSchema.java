import java.util.ArrayList;

import adsouza.shapes.Rectangle;


public class RoomSchema {
	
	public static final int LEVELS = 3 ;
	public static final int ROOMS = 4 ;
	public static int wallColorR = 153 ;
	public static int wallColorG = 153 ;
	public static int wallColorB = 102 ;

	private static Room[][] rooms= new Room[LEVELS][ROOMS];
	
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
			
			new Rectangle(150,500,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,550,200,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(330,500,20,70,wallColorR,wallColorG,wallColorB),
			
			new Rectangle(570,500,20,70,wallColorR,wallColorG,wallColorB),
			new Rectangle(570,550,200,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(750,500,20,70,wallColorR,wallColorG,wallColorB)
		};
		
		RangedEnemy[] rangedEnemies00 = {
			new RangedEnemy(175,175,1,0) ,
			new RangedEnemy(595,175,1,0) ,
			new RangedEnemy(175,500,1,0) ,
			new RangedEnemy(595,500,1,0) 
		} ;

		MeleeEnemy[] meleeEnemies00 = {
			new MeleeEnemy(460,100,2) 
		} ;
				
		StationEnemy[] stationEnemies00 = {
		} ;

		HealthBooster[] boosters00 = {
		} ;

		rooms[level][room++] = new Room(roomID++,walls00,rangedEnemies00,meleeEnemies00,stationEnemies00,boosters00);

		//Level 0 - Room 1
		Rectangle[] walls01 = {
			new Rectangle(150,150,20,140,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,290,620,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(750,150,20,140,wallColorR,wallColorG,wallColorB),
			
			new Rectangle(150,430,20,140,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,430,620,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(750,430,20,140,wallColorR,wallColorG,wallColorB)
		};
		
		RangedEnemy[] rangedEnemies01 = {
			new RangedEnemy(175,175,1,0) ,
			new RangedEnemy(525,200,1,0) ,
			new RangedEnemy(175,475,1,0) ,
			new RangedEnemy(525,520,1,0) 
		} ;

		MeleeEnemy[] meleeEnemies01 = {
			new MeleeEnemy(460,360,2) 
		} ;
		
		StationEnemy[] stationEnemies01 = {
		} ;

		HealthBooster[] boosters01 = {
		} ;

		rooms[level][room++] = new Room(roomID++,walls01,rangedEnemies01,meleeEnemies01,stationEnemies01,boosters01);
				
		//Level 0 - Room 2
		roomID++ ;
		Rectangle[] walls02 = {
			new Rectangle(150,150,120,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(250,150,20,400,wallColorR,wallColorG,wallColorB),
			new Rectangle(150,550,120,20,wallColorR,wallColorG,wallColorB),

			new Rectangle(250,350,400,20,wallColorR,wallColorG,wallColorB),

			new Rectangle(650,150,120,20,wallColorR,wallColorG,wallColorB),
			new Rectangle(650,150,20,400,wallColorR,wallColorG,wallColorB),
			new Rectangle(650,550,120,20,wallColorR,wallColorG,wallColorB),
		};
		
		RangedEnemy[] rangedEnemies02 = {
			new RangedEnemy(155,175,0,2) ,
			new RangedEnemy(210,500,0,-2) ,
			new RangedEnemy(300,150,1,0) ,
			new RangedEnemy(625,200,-1,0) ,
			new RangedEnemy(300,300,1,0) ,
			new RangedEnemy(625,400,1,0) ,
			new RangedEnemy(300,425,-1,0) ,
			new RangedEnemy(625,500,1,0) ,
		} ;

		MeleeEnemy[] meleeEnemies02 = {
			new MeleeEnemy(460,100,2),
			new MeleeEnemy(460,600,2),
			new MeleeEnemy(700,200,1),
			new MeleeEnemy(700,500,1)
		} ;
		
		StationEnemy[] stationEnemies02 = {
		} ;

		HealthBooster[] boosters02 = {
			new HealthBooster(675,175,true)
		} ;

		rooms[level][room++] = new Room(roomID++,walls02,rangedEnemies02,meleeEnemies02,stationEnemies02,boosters02);

		//Level 0 - Room 3 - Rest Room
		roomID = 3 ;
		Rectangle[] walls03 = {
			new Rectangle(40,40,240,280,wallColorR,wallColorG,wallColorB),
			new Rectangle(280,40,100,210,wallColorR,wallColorG,wallColorB),
			new Rectangle(380,40,240,280,wallColorR,wallColorG,wallColorB),
			new Rectangle(620,40,100,210,wallColorR,wallColorG,wallColorB),
			new Rectangle(720,40,240,280,wallColorR,wallColorG,wallColorB),

			new Rectangle(40,450,920,320,wallColorR,wallColorG,wallColorB)
		};
		
		RangedEnemy[] rangedEnemies03 = {
		} ;

		MeleeEnemy[] meleeEnemies03 = {
		} ;
		
		StationEnemy[] stationEnemies03 = {
		} ;

		HealthBooster[] boosters03 = {
			new HealthBooster(285,265,false),
			new HealthBooster(320,260,false),
			new HealthBooster(630,265,false),
			new HealthBooster(675,260,false),
			new HealthBooster(650,270,false),
		} ;

		rooms[level++][room] = new Room(roomID++,walls03,rangedEnemies03,meleeEnemies03,stationEnemies03,boosters03);
				
		room = 0 ;
		
		//Level 2 , room 0
		Rectangle[] walls10 = {
			new Rectangle(40,150,250,50,211,211,211),
			new Rectangle(40,500,250,50,211,211,211),
			new Rectangle(400,150,20,400,211,211,211),
			new Rectangle(500,150,250,50,211,211,211),
			new Rectangle(500,500,250,50,211,211,211)
		};
		
		RangedEnemy[] rangedEnemies10 = {
			new RangedEnemy(600,50,0,1) ,
			new RangedEnemy(600,650,0,1) ,
			new RangedEnemy(575,225,1,2) ,
			new RangedEnemy(675,225,-1,2) ,
		} ;

		MeleeEnemy[] meleeEnemies10 = {
			new MeleeEnemy(450,350,4) 
		} ;
		
		StationEnemy[] stationEnemies10 = {
			new StationEnemy(150,75,1) ,
			new StationEnemy(150,600,1),
			new StationEnemy(250,350,1),
		} ;
		
		HealthBooster[] boosters10 = {
			new HealthBooster(50,75,false),
			new HealthBooster(50,600,false)
		} ;

		rooms[level][room++] = new Room(roomID++,walls10,rangedEnemies10,meleeEnemies10, stationEnemies10,boosters10);

		//Level 2 , room 1
		Rectangle[] walls11 = {
				new Rectangle(40,40,250,250,211,211,211),
				new Rectangle(400,200,50,250,211,211,211),
				new Rectangle(400,200,250,50,211,211,211),
				new Rectangle(600,400,50,50,211,211,211),
				new Rectangle(200,550,50,50,211,211,211),
				new Rectangle(400,550,50,50,211,211,211),
				new Rectangle(600,550,50,50,211,211,211),
				new Rectangle(800,600,100,100,211,211,211)
		};
		
		RangedEnemy[] rangedEnemies11 = {
			new RangedEnemy(260,550,1,0) ,
			new RangedEnemy(460,550,1,0) ,
		} ;

		MeleeEnemy[] meleeEnemies11 = {
			new MeleeEnemy(550,350,4),
			new MeleeEnemy(300,60,4),
		} ;
		
		StationEnemy[] stationEnemies11 = {
			new StationEnemy(800,350,1) 
		} ;
		
		HealthBooster[] boosters11 = {
			new HealthBooster(475,275,true)
		} ;

		rooms[level][room++] = new Room(roomID++,walls11,rangedEnemies11,meleeEnemies11, stationEnemies11,boosters11);

		//Level 2 , room 2
		Rectangle[] walls12 = {
			new Rectangle(40,40,400,275,211,211,211),
			new Rectangle(40,40,900,100,211,211,211),
			new Rectangle(40,600,900,100,211,211,211),
			new Rectangle(120,315,320,200,211,211,211),
			new Rectangle(550,300,100,300,211,211,211),
			new Rectangle(550,225,10,75,211,211,211),
			new Rectangle(640,225,10,75,211,211,211),
			new Rectangle(750,125,200,200,211,211,211),
			new Rectangle(750,440,200,75,211,211,211),
		};
		
		RangedEnemy[] rangedEnemies12 = {
			new RangedEnemy(300,550,0,1) ,
			new RangedEnemy(450,150,0,3) ,
			new RangedEnemy(775,350,0,2) ,
		} ;

		MeleeEnemy[] meleeEnemies12 = {
			new MeleeEnemy(585,250,4) 
		} ;
		
		StationEnemy[] stationEnemies12 = {
			new StationEnemy(45,550,1), 
			new StationEnemy(660,550,1), 
		} ;
		
		HealthBooster[] boosters12 = {
			new HealthBooster(825,550,true)
		} ;

		rooms[level][room++] = new Room(roomID++,walls12,rangedEnemies12,meleeEnemies12, stationEnemies12,boosters12);

		//Level 2 - Room 3 - Rest Room
		roomID = 3 ;
		Rectangle[] walls13 = {
			new Rectangle(40,40,900,280,wallColorR,wallColorG,wallColorB),

			new Rectangle(40,440,920,320,wallColorR,wallColorG,wallColorB)
		};
		
		RangedEnemy[] rangedEnemies13 = {
		} ;

		MeleeEnemy[] meleeEnemies13 = {
		} ;
		
		StationEnemy[] stationEnemies13 = {
		} ;

		HealthBooster[] boosters13 = {
			new HealthBooster(700,400,true),
		} ;

		rooms[level++][room] = new Room(roomID++,walls13,rangedEnemies13,meleeEnemies13,stationEnemies13,boosters13);
		
		room = 0 ;
				
		//Level 3 , room 0
		Rectangle[] walls20 = {
			new Rectangle(150,40,50,400,211,211,211),
			new Rectangle(150,440,150,50,211,211,211),
			new Rectangle(450,360,50,520,211,211,211),
			new Rectangle(370,310,130,50,211,211,211),
			new Rectangle(670,40,80,350,211,211,211),
			new Rectangle(687,637,200,200,211,211,211),
		};
		
		RangedEnemy[] rangedEnemies20 = {
			new RangedEnemy(50,80,1,0) ,
			new RangedEnemy(500,175,1,0) ,
			new RangedEnemy(175,615,1,0) ,
			new RangedEnemy(595,615,1,0) 
		} ;

		MeleeEnemy[] meleeEnemies20 = {
			new MeleeEnemy(800,100,4) 
		} ;

		StationEnemy[] stationEnemies20 = {
			new StationEnemy(380,620,1) 
		};

		HealthBooster[] boosters20 = {
		} ;

		rooms[level][room++] = new Room(roomID++,walls20,rangedEnemies20,meleeEnemies20, stationEnemies20,boosters20);
		
		//Level 3 - Room 1
		Rectangle[] walls21 = {
			new Rectangle(150,40,50,400,211,211,211),
			new Rectangle(150,440,150,50,211,211,211),
			new Rectangle(450,360,50,520,211,211,211),
			new Rectangle(370,310,130,50,211,211,211),
			new Rectangle(670,40,80,350,211,211,211),
			new Rectangle(687,637,200,200,211,211,211),
		};
		
		RangedEnemy[] rangedEnemies21 = {
			new RangedEnemy(50,80,1,0),
			new RangedEnemy(500,175,1,0),
			new RangedEnemy(595,615,1,0) 
		} ;
		rangedEnemies21[0].getGun().setDamage(3);
		rangedEnemies21[1].getGun().setDamage(3);
		rangedEnemies21[2].getGun().setDamage(3);


		MeleeEnemy[] meleeEnemies21 = {
			new MeleeEnemy(600,100,4),
		} ;
		
		StationEnemy[] stationEnemies21 = {
				new StationEnemy(380,620,1),
				new StationEnemy(260,45,1) 
		} ;
		stationEnemies21[0].getGun().setDamage(3);
		stationEnemies21[1].getGun().setDamage(3);
		
		HealthBooster[] boosters21 = {
		} ;

		rooms[level][room++] = new Room(roomID++,walls21,rangedEnemies21,meleeEnemies21, stationEnemies21,boosters21);
		
		
		//Level 3 - Room 2
		Rectangle[] walls22 = {
				new Rectangle(200,40,50,250,211,211,211),
				new Rectangle(200,450,50,350,211,211,211),
				new Rectangle(110,360,40,40,211,211,211),

				new Rectangle(430,210,50,350,211,211,211),

				new Rectangle(670,40,215,275,211,211,211),
				new Rectangle(670,450,215,350,211,211,211),


		};
		
		RangedEnemy[] rangedEnemies22 = {
			new RangedEnemy(50,80,1,0),
			new RangedEnemy(100,510,1,0),

		} ;
		rangedEnemies22[0].getGun().setAttackSpeed(1);
		rangedEnemies22[1].getGun().setAttackSpeed(1);
		rangedEnemies22[0].getGun().setDamage(2);
		rangedEnemies22[1].getGun().setDamage(2);


		MeleeEnemy[] meleeEnemies22 = {
			new MeleeEnemy(480,400,4),
		} ;
		
		StationEnemy[] stationEnemies22 = {
				new StationEnemy(360,620,1),
				new StationEnemy(260,45,1),
				new StationEnemy(700,390,1) 
		} ;
		stationEnemies22[0].getGun().setDamage(5);
		stationEnemies22[1].getGun().setDamage(5);
		stationEnemies22[0].getGun().setAttackSpeed(1);
		stationEnemies22[1].getGun().setAttackSpeed(1);
		
		HealthBooster[] boosters22 = {
		} ;

		rooms[level][room++] = new Room(roomID++,walls22,rangedEnemies22,meleeEnemies22, stationEnemies22,boosters22);
		
		//Winner's room - Todo
		Rectangle[] walls23 = {
		};
		
		RangedEnemy[] rangedEnemies23 = {
		} ;


		MeleeEnemy[] meleeEnemies23 = {
		} ;
		
		StationEnemy[] stationEnemies23 = {
		} ;
		
		HealthBooster[] boosters23 = {
		} ;

		rooms[level][room] = new Room(roomID++,walls23,rangedEnemies23,meleeEnemies23, stationEnemies23,boosters23);
		
	}
}
