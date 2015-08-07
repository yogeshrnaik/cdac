class Player
{
	char name[20];
	int score;
	int lives;
	
	public:
	Player ();
	int get_score();
	int get_lives();
//	char& get_name();
	void readName();
	void set_score(int);
	void set_lives(int);
	void set_name(char*);

	void updateScore (int);
	void reduceLives();
         
	int hasLife();
	void displayInfo();
};




