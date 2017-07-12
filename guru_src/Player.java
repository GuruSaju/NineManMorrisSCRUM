
class Player {
	private int tokens_put;
	private int tokens_left;
	private String name;
	public int getTokensPut() {
		return tokens_put;
	}

	public void setTokens_put(int tokens_put) {
		this.tokens_put = tokens_put;
	}

	public int getTokensLeft() {
		return tokens_left;
	}

	public void setTokens_left(int tokens_left) {
		this.tokens_left = tokens_left;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	private String color;
	
	public Player(String name, String color) {
		this.tokens_left = 0;
		this.tokens_put = 0;
		this.name = name;
		this.color = color;
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
}
