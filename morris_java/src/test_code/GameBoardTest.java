package test_code;

import static org.junit.Assert.*;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import morris_java.Coord;
import morris_java.GameBoard;
import morris_java.Player;
import morris_java.Position;

import org.junit.Assert;
import org.junit.Test;

public class GameBoardTest {
   GameBoard gameboard=new GameBoard();
	@Test
	public void isNeutralPositionstest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Coord cord= new Coord(2,2);
		Position pos = new Position(cord,null);
		Method method = GameBoard.class.getDeclaredMethod("isNeutralPosition", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0", true,output);
	
	}
	@Test
	public void isNeutralPositionstest1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(2,2);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isNeutralPosition", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	
	}

	@Test
	public void isSamePlayerstest1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(2,2);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isSamePlayer", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	
	}
	@Test
	public void isSamePlayerstest2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(2,2);
		Player play=new Player("player_1","red");
		Player play1=new Player("player_1","red");
		Position pos = new Position(cord,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play1);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isSamePlayer", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	}
	@Test
	public void isAdjacentToSelectedtestRight() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Coord cord1= new Coord(0,3);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Method method = GameBoard.class.getDeclaredMethod("isAdjacentToSelected", Position.class,Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos,pos1);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void isAdjacentToSelectedtestLeft() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,3);
		Coord cord1= new Coord(0,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Method method = GameBoard.class.getDeclaredMethod("isAdjacentToSelected", Position.class,Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos1,pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void isAdjacentToSelectedtestUp() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Coord cord1= new Coord(3,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Method method = GameBoard.class.getDeclaredMethod("isAdjacentToSelected", Position.class,Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos1,pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void isAdjacentToSelectedtestDown() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(3,0);
		Coord cord1= new Coord(0,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Method method = GameBoard.class.getDeclaredMethod("isAdjacentToSelected", Position.class,Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos1,pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void isAdjacentToSelectedtestFail() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Coord cord1= new Coord(6,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Method method = GameBoard.class.getDeclaredMethod("isAdjacentToSelected", Position.class,Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos1,pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	}
	@Test
	public void isAdjacentToSelectedtestFail1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Coord cord1= new Coord(6,6);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Method method = GameBoard.class.getDeclaredMethod("isAdjacentToSelected", Position.class,Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos1,pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	}
	
	@Test
	public void hasVacantNeibourPosUptest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(3,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Method method = GameBoard.class.getDeclaredMethod("hasVacantNeibourPos", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void hasVacantNeibourPosDowntest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Method method = GameBoard.class.getDeclaredMethod("hasVacantNeibourPos", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void hasVacantNeibourPosRighttest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Method method = GameBoard.class.getDeclaredMethod("hasVacantNeibourPos", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void hasVacantNeibourPosLefttest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,3);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Method method = GameBoard.class.getDeclaredMethod("hasVacantNeibourPos", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void hasVacantNeibourPostestFail() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Coord cord1= new Coord(0,3);
		Coord cord2= new Coord(3,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("hasVacantNeibourPos", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	}
	@Test
	public void hasVacantNeibourPostestFail1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(6,6);
		Coord cord1= new Coord(6,3);
		Coord cord2= new Coord(3,6);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("hasVacantNeibourPos", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	}
	
	@Test
	public void isVerticalMillTest1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Coord cord1= new Coord(3,0);
		Coord cord2= new Coord(6,0);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isVerticalMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	
	@Test
	public void isVerticalMillTest2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(1,1);
		Coord cord1= new Coord(3,1);
		Coord cord2= new Coord(5,1);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isVerticalMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	
	@Test
	public void isVerticalMillTestFail() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Coord cord1= new Coord(0,3);
		Coord cord2= new Coord(0,6);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isVerticalMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	}
	@Test
	public void isHorizontalMillTest1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,0);
		Coord cord1= new Coord(0,3);
		Coord cord2= new Coord(0,6);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isHorizontalMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void isHorizontalMillTest2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(3,4);
		Coord cord1= new Coord(3,5);
		Coord cord2= new Coord(3,6);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isHorizontalMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void isHorizontalMillTestFail() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,6);
		Coord cord1= new Coord(3,6);
		Coord cord2= new Coord(6,6);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isHorizontalMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	}
	@Test
	public void isMillTest1() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,3);
		Coord cord1= new Coord(1,3);
		Coord cord2= new Coord(2,3);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void isMillTest2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(4,2);
		Coord cord1= new Coord(4,3);
		Coord cord2= new Coord(4,4);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",true,output);
	}
	@Test
	public void isMillTestFail() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Coord cord= new Coord(0,6);
		Coord cord1= new Coord(3,6);
		Coord cord2= new Coord(5,5);
		Player play=new Player("player_1","red");
		Position pos = new Position(cord,play);
		Position pos1 = new Position(cord1,play);
		Position pos2 = new Position(cord2,play);
		HashMap<Coord, Position> positions = new HashMap<Coord, Position>();
		positions=gameboard.getPositions();
		positions.get(pos.getCoord()).setPlayer(play);
		positions.get(pos1.getCoord()).setPlayer(play);
		positions.get(pos2.getCoord()).setPlayer(play);
		gameboard.setPositions(positions);
		Method method = GameBoard.class.getDeclaredMethod("isMill", Position.class);
        method.setAccessible(true);
        boolean output = (boolean) method.invoke(gameboard, pos);
        Assert.assertEquals("Total should be 0.0",false,output);
	}
}
