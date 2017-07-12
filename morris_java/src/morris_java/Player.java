package morris_java;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player  implements Serializable{
	private int tokens_put;
	private int tokens_left;
	private String name;
	private String color;
	private String state;

//	private static Player player_1 = null;
//	private static Player player_2 = null;
	private int totalMill;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
//	public static Player getPlayer_1() {
//		if(Player.player_1 == null) {
//			Player.player_1 = new Player("player_1", "X");
//		}
//		return Player.player_1;
//	}
//	
//	public static Player getPlayer_2() {
//		if(Player.player_2 == null) {
//			Player.player_2 = new Player("player_2", "O");
//		}
//		return Player.player_2;
//	}
	
	public Player(String name, String color) {
		this.tokens_left = 0;
		this.tokens_put = 0;
		this.name = name;
		this.color = color;
		this.state = GameState.drop;
		this.totalMill = 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int getTokensPut( ){
		return tokens_put;
	}
	
	public int getTokensLeft( ){
		return tokens_left;
	}
	
	public String toString() {
		String temp = String.format("%s %s", this.name, this.color);
		return temp;
		
	}
	
	public void putToken() {
		this.tokens_put += 1;
		this.tokens_left += 1;
	}
	
	public void removeToken() {
		this.tokens_left -= 1;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void formedNewMill() {
		this.totalMill++;
	}
	
	public int getTotalMill() {
		return this.totalMill;
	}
	public int getTokensRemoved( ){
		return this.tokens_put - this.tokens_left;
	}
	
}
