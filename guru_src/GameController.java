
public class GameController {

	Player player_1;
	Player player_2;
	Player current_player;
	Position last_pos;
	String state;
	GameBoard board;
	/**
	 * 
	 */
	public GameController()
	{
		this.player_1 = new Player("player_1", "X");
		this.player_2 = new Player("player_2", "O");
		this.last_pos = null;
		this.current_player = this.player_1;
		this.state = GameState.drop;
		this.board = new GameBoard();
	}


	public boolean move(Position pos){

		if(!this.board.isLegal(this.state, this.current_player, this.last_pos, pos))
		{
			return false;
		}
		//apply change to board
		this.board.change(this.state, this.last_pos, pos);

		//state transition
		if (this.state == GameState.move_1)
		{
			this.last_pos = pos;
			this.state = GameState.move_2;
			return true;
		}
		else if (this.state == GameState.fly_1)
		{
			this.last_pos = pos;
			this.state = GameState.fly_2;
			return true;
		}
		else if (this.state == GameState.drop || this.state == GameState.move_2 || this.state == GameState.fly_2)
		{
			if(this.board.isMill(pos))
			{
				this.state = GameState.new_mill;
				return true;
			}
			// the case did not form a mill
			this.change_player();
			this.decide_new_player_state();
			return true;
		}
		else
		{
			//the case state == new_mill
			this.change_player();
			this.decide_new_player_state();
			return true;
		}
	}

	public void change_player() {
		if (this.current_player == this.player_1)
		{
			this.current_player = this.player_2;
		}
		else
		{
			this.current_player = this.player_1;        	
		}

	}
	public void decide_new_player_state() {
		if (this.current_player.getTokensPut() < 9)
		{
			this.state = GameState.drop;
		}
		else if (this.current_player.getTokensLeft() < 3)
		{
			this.set_opponent_win();
		}
		else if (this.current_player.getTokensLeft() == 3)
		{
			this.state = GameState.fly_1;
		}
		else
		{
			this.state = GameState.move_1;
		}
	}


	private void set_opponent_win() 
	{
		if (this.current_player == this.player_1)
		{
			this.state = GameState.player_2_win;
		}
		else{
			this.state = GameState.player_1_win;
		}
	}




}
