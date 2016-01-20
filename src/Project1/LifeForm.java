package Project1;

import java.io.Serializable;
import java.util.Random;

import javafx.scene.paint.Color;

/**
 * This class contains all the basic attributes of a LifeForm.
 */
public abstract class LifeForm implements Serializable {

	//Encapulated variables
	protected int energy;
	protected int xpos;
	protected int ypos;
	protected int smellrange;
	protected int size_x;
	protected int size_y;
	protected char grid[][];
	private String name;
	private char symbol;
	private int BugID;
	private String herdType;
	private int members;
	private String type;


	public LifeForm(int dx,int dy, String specie, String name, int ID, int xpos, int ypos, int smellRange, int energy) { //Constructor
		this.setXdimension(dx);
		this.setYdimension(dy);
		this.setType(specie);
		this.setName(name);
		this.setSymbol(name.charAt(0));
		this.setBugID(ID);
		this.setXpos(xpos);
		this.setYpos(ypos);
		this.setSmellrange(smellRange);
		this.setEnergy(energy);
	}

	//Getter and Setter methods
	public int getXdimension() {
		return size_x;
	}

	public void setXdimension(int size_x) {
		this.size_x = size_x;
	}

	public int getYdimension() {
		return size_y;
	}

	public void setYdimension(int size_y) {
		this.size_y = size_y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public char[][] getGrid() {
		return grid;
	}

	public void setGrid(char [][] grid) {
		this.grid = grid;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public int getBugID() {
		return BugID;
	}

	public void setBugID(int bugID) {
		BugID = bugID;
	}

	public int getSmellrange() {
		return smellrange;
	}

	public void setSmellrange(int smellrange) {
		this.smellrange = smellrange;
	}

	public void setGridpos() {
		grid[xpos][ypos] = symbol;
	}

	public String getHerdType() {
		return herdType;
	}

	public void setHerdType(String herdType) {
		this.herdType = herdType;
	}

	public int getMembers() {
		return members;
	}

	public void setMembers(int members) {
		this.members = members;
	}

	public String getType(){
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	//Abstract methods
	public abstract Color getFill();

	/**
	 * This method will find the nearest food in a given direction.
	 * @param direction - passes in which direction the method should check
	 * @return - returns if food is found in a given direction.
	 */
		//Main methods
	public boolean smellFood(Direction direction) {
		boolean found;
		switch (direction) {
		case North:
			found = false;
			for (int i = 0; i < this.smellrange; i++) {
				if (this.ypos - i - 1 >= 0) {
					if (Character.isUpperCase(this.grid[this.xpos][this.ypos - i - 1]) || this.grid[this.xpos][this.ypos - i - 1] == '^') {
						break;
					} else if (this.grid[this.xpos][this.ypos - i - 1] != ' ') {
							found = true;
						}
					}
				}
			return found;
		case East:
			found = false;
			for (int i = 0; i < this.smellrange; i++) {
				if (this.xpos + i + 1 < getXdimension()) {
					if (Character.isUpperCase(this.grid[this.xpos + 1 + i][this.ypos]) || this.grid[this.xpos + 1 + i][this.ypos] == '^') {
						break;
					}else if (this.grid[this.xpos + 1 + i][this.ypos] != ' ') {
							found = true;
						}
					}
				}
			return found;
		case South:
			found = false;
			for (int i = 0; i < this.smellrange; i++) {
				if (this.ypos + 1 + i < getYdimension()) {
					if (Character.isUpperCase(this.grid[this.xpos][this.ypos + 1 + i]) || this.grid[this.xpos][this.ypos + 1 + i] == '^') {
						break;
					}else if (this.grid[this.xpos][this.ypos + 1 + i] != ' ') {
							found = true;
						}
					}
				}
			return found;
		case West:
			found = false;
			for (int i = 0; i < smellrange; i++) {
				if (this.xpos - 1 - i >= 0) {
					if (Character.isUpperCase(this.grid[this.xpos - 1 - i][this.ypos]) || this.grid[this.xpos - 1 - i][this.ypos] == '^') {
						break;
					} else if (this.grid[this.xpos - 1 - i][this.ypos] != ' ') {
							found = true;
						}
					}
				}
			return found;
		}
		return false;
	}

	/**
	 * Will generate a random direction the animal should move
	 * @return - in which direction the LifeForm should move.
	 */
	public Direction getRandomDirection(Direction d) { //Picks a random direction and returns it
		Random rand = new Random();
		int temp = rand.nextInt(Direction.values().length);
		d = Direction.values()[temp];
		return d;
	}

	/**
	 * Will determine in which direction the animal should move.
	 * Either in the direction food is found or a random direction.
	 * @return - in which direction the LifeForm should move.
	 */
	public Direction getDirectionOfFood() { //Will return the direction of the food
		Direction direction = Direction.North;
		boolean found = false;

		for (Direction d : Direction.values()) {
			if (smellFood(d)) {
				found = true;
				direction = d;
			}
		}
		if (found) {
			return direction;
		} else {
			return getRandomDirection(direction);
		}
	}

	/**
	 * This method will move the LifeForm in a given direction
	 * @param d - the direction in which the LifeForm should move
	 */
	public void Move(Direction d){//Move the animals towards the food
		this.grid[this.xpos][this.ypos] = ' ';

		switch (d) {
		case North:
			if (this.ypos - 1 >= 0 && this.grid[this.xpos][this.ypos - 1] != 'X' && this.grid[this.xpos][this.ypos - 1] != '^') {
				this.ypos -= 1;
				energy -= 1;
			}
			break;
		case East:
			if (this.xpos + 1 < size_x && this.grid[this.xpos + 1][this.ypos] != 'X' && this.grid[this.xpos + 1][this.ypos] != '^') {
				this.xpos += 1;
				energy -= 1;
			}
			break;
		case South:
			if (this.ypos + 1 < size_y && this.grid[this.xpos][this.ypos + 1] != 'X' && this.grid[this.xpos][this.ypos + 1 ] != '^') {
				this.ypos += 1;
				energy -= 1;
			}
			break;
		case West:
			if (this.xpos - 1 >= 0 && this.grid[this.xpos - 1][this.ypos] != 'X' && this.grid[this.xpos - 1][this.ypos] != '^') {
				this.xpos -= 1;
				energy -= 1;
			}
			break;
		}
	}

	/**
	 * This method will update the LifeForms attributes after each cycle
	 */
	public void update() { //Updates the attributes of the animals
		Move(getDirectionOfFood());//Moves animal towards the food

		if (this.grid[this.xpos][this.ypos] == '^') {
			this.setSymbol('^');
		}

		//If food is found it will assign the food energy value and add it to the animals energy
		if (this.grid[this.xpos][this.ypos] != ' ') {
			this.energy += Character.getNumericValue(this.grid[this.xpos][this.ypos]);
		}
		System.out.println("\tenergy: " + energy);
		System.out.println("\tposition: x = " + xpos + " y = " + ypos);

		setGridpos();
	}

	public enum Direction {
		North, East, South, West
	}

}