import java.util.Arrays;
import java.util.List;
import java.util.*;

public class GameBoard {
	List<String> coordList = Arrays.asList("(0,0)", "(0,3)", "(0,6)", "(1,1)",
			"(1,3)", "(1,5)", "(2,2)", "(2,3)", "(2,4)", "(3,0)", "(3,1)",
			"(3,2)", "(3,4)", "(3,5)", "(3,6)", "(4,2)", "(4,3)", "(4,4)",
			"(5,1)", "(5,3)", "(5,5)", "(6,0)", "(6,3)", "(6,6)");
	HashMap<String, Position> positions = new HashMap<String, Position>();

	// Constructor
	public GameBoard() {

		for (String coord : coordList) {

			positions.put(coord, new Position(coord, null));

		}
	}

	boolean isNeutralPosition(Position pos) {
		return positions.get(pos.getCoord()).getPlayer() == null;
	}

	boolean isSamePlayer(Position pos) {
		return positions.get(pos.getCoord()).getPlayer() == pos.getPlayer();

	}
	
     boolean isOpponentPos(Position pos) {
		return !isSamePlayer(pos) && !isNeutralPosition(pos);
	}   
        
    boolean isAdjacentToSelected(Position lastpos,Position pos){
    	return true;
    }
               
     boolean isLegal(String state,Player player,Position lastPos,Position pos){
        	
        	if(!positions.containsKey(pos.getCoord())){
        		return false;
        	}
        	if(state.equals(GameState.drop) || state.equals(GameState.fly_2))
        		return isNeutralPosition(pos);
        	if(state.equals(GameState.move_1) || state.equals(GameState.fly_1))
        		return isSamePlayer(pos);
        	if(state.equals(GameState.move_2))
        		return isNeutralPosition(pos) && isAdjacentToSelected(lastPos, pos);
        	if(state.equals(GameState.new_mill))
        		return isOpponentPos(pos);
			return false;		
        }
     
     void removeToken(Position pos){
    	 positions.get(pos.getCoord()).getPlayer().removeToken();
    	 positions.get(pos.getCoord()).setPlayer(null);
     }
     
     void putToken(Position pos){
    	 positions.get(pos.getCoord()).setPlayer(pos.getPlayer());
    	 pos.getPlayer().putToken();
     }
     
     boolean isMill(Position pos){
    	 return isHorizontalMill(pos) || isVerticalMill(pos);
     }

     
     boolean isVerticalMill(Position pos){
    	 boolean ret = false;
    	 Position corresPos=positions.get(pos.getCoord());
    	 Player player=pos.getPlayer();
    	 if(corresPos.getUp()==null)
    		 ret = corresPos.getDown().getPlayer()==player && corresPos.getDown().getDown().getPlayer()==player;
     
     else if(corresPos.getDown()==null)
    	 ret = corresPos.getUp().getPlayer()==player && corresPos.getUp().getUp().getPlayer()==player;
     else
    	 ret = corresPos.getDown().getPlayer()==player && corresPos.getUp().getPlayer()==player;
    	 
    	 return ret;
     }

     boolean isHorizontalMill(Position pos){
    	 boolean ret = false;
    	 Position corresPos=positions.get(pos.getCoord());
    	 Player player=pos.getPlayer();
    	 if(corresPos.getLeft()==null)
    		 ret = corresPos.getRight().getPlayer()==player && corresPos.getRight().getRight().getPlayer()==player;
     
     else if(corresPos.getRight()==null)
    	 ret = corresPos.getLeft().getPlayer()==player && corresPos.getLeft().getLeft().getPlayer()==player;
     else
    	 ret = corresPos.getRight().getPlayer()==player && corresPos.getLeft().getPlayer()==player;
    	 
    	 return ret;
     }
 
     void change(String state, Position lastPos,Position pos){
    	 if(state.equals(GameState.drop))
    		 putToken(pos);
    	 else if(state.equals(GameState.move_2) || state.equals(GameState.fly_2)){
    		 removeToken(positions.get(lastPos.getCoord()));
    		 putToken(pos);
    	 }
    	 else if(state.equals(GameState.new_mill))
    		 removeToken(pos);	 
     }
     
 //     def __str__(self, *args, **kwargs):
//         #node_list = [pos.player.color if pos.player is not None else "*" for coord in sorted(self.positions, key=self.positions.get)]
//         node_list = []
//         for coord in self.__coord_list:
//             if(self.positions[coord].player == None):
//                 node_list.append("*")
//             else:
//                 node_list.append(self.positions[coord].player.color)
//         
//         board_repr = """
//           0 1 2 3 4 5 6 
//         0 {}-----{}-----{}
//         1 | {}---{}---{} |
//         2 | | {}-{}-{} | |
//         3 {}-{}-{}   {}-{}-{}
//         4 | | {}-{}-{} | |
//         5 | {}---{}---{}-|
//         6 {}-----{}-----{}""".format(*node_list)
//         return board_repr
     
     public String toString(){
    	 List<String> nodeList= new ArrayList<String>();
    	 for(String coord:coordList){
    		 if(positions.get(coord).getPlayer()==null)
    			 nodeList.add("*");
    		 else
    			 nodeList.add(positions.get(coord).getPlayer().getColor());
    	 }
         String board_repr = 
             "0 1 2 3 4 5 6\n" +
           "0 %s-----%s-----%s\n" + 
           "1 | %s---%s---%s |\n" +
           "2 | | %s-%s-%s | |\n" +
           "3 %s-%s-%s   %s-%s-%s\n" +
           "4 | | %s-%s-%s | |\n" +
           "5 | %s---%s---%s-|\n" +
           "6 %s-----%s-----%s\n";
           		//"+
           		//".format(node_list)";
           return board_repr;

    	 
     }
     public void init() {
 		Position temp;
 		temp = this.positions.get("(0, 0)");
 		temp.setDown(this.positions.get("(3, 0)"));
 		temp.setRight(this.positions.get("(0, 3)"));

 		temp = this.positions.get("(0, 3)");
 		temp.setDown(this.positions.get("(1, 3)"));
 		temp.setLeft(this.positions.get("(0, 0)"));
 		temp.setRight(this.positions.get("(0, 6)"));

 		temp = this.positions.get("(0, 6)");
 		temp.setDown(this.positions.get("(3, 6)"));
 		temp.setLeft(this.positions.get("(0, 3)"));

 		temp = this.positions.get("(1, 1)");
 		temp.setDown(this.positions.get("(3, 1)"));
 		temp.setRight(this.positions.get("(1, 3)"));

 		temp = this.positions.get("(1, 3)");
 		temp.setUp(this.positions.get("(0, 3)"));
 		temp.setDown(this.positions.get("(2, 3)"));
 		temp.setLeft(this.positions.get("(1, 1)"));
 		temp.setRight(this.positions.get("(1, 5)"));

 		temp = this.positions.get("(1, 5)");
 		temp.setDown(this.positions.get("(3, 5)"));
 		temp.setLeft(this.positions.get("(1, 3)"));

 		temp = this.positions.get("(2, 2)");
 		temp.setDown(this.positions.get("(3, 2)"));
 		temp.setRight(this.positions.get("(2, 3)"));

 		temp = this.positions.get("(2, 3)");
 		temp.setUp(this.positions.get("(1, 3)"));
 		temp.setLeft(this.positions.get("(2, 2)"));
 		temp.setRight(this.positions.get("(2, 4)"));
 		
 		temp = this.positions.get("(2, 4)");
 		temp.setDown(this.positions.get("(3, 4)"));
 		temp.setLeft(this.positions.get("(2, 3)"));

 		temp = this.positions.get("(3, 0)");
 		temp.setUp(this.positions.get("(0, 0)"));
 		temp.setDown(this.positions.get("(6, 0)"));
 		temp.setRight(this.positions.get("(3, 1)"));

 		temp = this.positions.get("(3, 1)");
 		temp.setUp(this.positions.get("(1, 1)"));
 		temp.setDown(this.positions.get("(5, 1)"));
 		temp.setLeft(this.positions.get("(3, 0)"));
 		temp.setRight(this.positions.get("(3, 2)"));

 		temp = this.positions.get("(3, 2)");
 		temp.setUp(this.positions.get("(2, 2)"));
 		temp.setDown(this.positions.get("(4, 2)"));
 		temp.setLeft(this.positions.get("(3, 1)"));

 		temp = this.positions.get("(3, 4)");
 		temp.setUp(this.positions.get("(2, 4)"));
 		temp.setDown(this.positions.get("(4, 4)"));
 		temp.setRight(this.positions.get("(3, 5)"));

 		temp = this.positions.get("(3, 5)");
 		temp.setUp(this.positions.get("(1, 5)"));
 		temp.setDown(this.positions.get("(5, 5)"));
 		temp.setLeft(this.positions.get("(3, 4)"));
 		temp.setRight(this.positions.get("(3, 6)"));

 		temp = this.positions.get("(3, 6)");
 		temp.setUp(this.positions.get("(0, 6)"));
 		temp.setDown(this.positions.get("(6, 6)"));
 		temp.setLeft(this.positions.get("(3, 5)"));

 		temp = this.positions.get("(4, 2)");
 		temp.setUp(this.positions.get("(3, 2)"));
 		temp.setRight(this.positions.get("(4, 3)"));

 		temp = this.positions.get("(4, 3)");
 		temp.setDown(this.positions.get("(5, 3)"));
 		temp.setLeft(this.positions.get("(4, 2)"));
 		temp.setRight(this.positions.get("(4, 4)"));

 		temp = this.positions.get("(4, 4)");
 		temp.setUp(this.positions.get("(3, 4)"));
 		temp.setLeft(this.positions.get("(4, 3)"));
 		
 		temp = this.positions.get("(5, 1)");
 		temp.setUp(this.positions.get("(3, 1)"));
 		temp.setRight(this.positions.get("(5, 3)"));

 		temp = this.positions.get("(5, 3)");
 		temp.setUp(this.positions.get("(4, 3)"));
 		temp.setDown(this.positions.get("(6, 3)"));
 		temp.setLeft(this.positions.get("(5, 1)"));
 		temp.setRight(this.positions.get("(5, 5)"));

 		temp = this.positions.get("(5, 5)");
 		temp.setUp(this.positions.get("(3, 5)"));
 		temp.setLeft(this.positions.get("(5, 3)"));
 		
 		temp = this.positions.get("(6, 0)");
 		temp.setUp(this.positions.get("(3, 0)"));
 		temp.setRight(this.positions.get("(6, 3)"));

 		temp = this.positions.get("(6, 3)");
 		temp.setUp(this.positions.get("(5, 3)"));
 		temp.setLeft(this.positions.get("(0, 0)"));
 		temp.setRight(this.positions.get("(6, 6)"));

 		temp = this.positions.get("(6, 6)");
 		temp.setUp(this.positions.get("(3, 6)"));
 		temp.setLeft(this.positions.get("(6, 3)"));
 	}

}