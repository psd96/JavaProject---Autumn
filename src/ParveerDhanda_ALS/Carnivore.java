package ParveerDhanda_ALS;


/**
 * This class sets the attributes of a Carnivore. It extends from LifeForm class and inherits all of its methods.
 */
public abstract class Carnivore extends LifeForm {


	/**
	 * This is the carnivore constructor. Within this it sets the map diemnsions and the specie of the LifeForm
	 * @param dx - X dimensions of the map
	 * @param dy - X dimensions of the map
	 * @param name - the name of the bug
	 * @param xpos -  the X-position of the bug
	 * @param ypos - the Y-position of the bug
	 * @param energy -the energy level of the bug
	 * @param ID - the ID of the bug
	 * @param smellRange - the smellrange of the bug
	 */
	public Carnivore(int dx,int dy, String specie, String name, int ID, int xpos, int ypos, int smellRange, int energy) {
		super(dx, dy, specie,name,ID,xpos,ypos,smellRange,energy);
	}

	/**
	 * This smellFood function overrides the one in LifeForm class.
	 * This is because a carnivore can eat other animals as well as other animals.
	 * This method will return the in which direction an eatable object is found.
	 * @param direction - passes in which direction the method should check
	 * @return - returns if food is found in a given direction.
	 */
	public boolean smellFood(Direction direction) {
		boolean found;
		switch (direction) {
			//In each case the method will for loop the grid at a distance of the smell range.
			//If it finds a food object or another bug to eat, it will return that it has found and in
			//which direction.
		case North:
			found = false;
			for (int i = 0; i < this.smellrange; i++) {
				//Checks if the next position is at the end of the grid
				if (this.ypos - i - 1 >= 0) {
					//Checks if the position is a obstacle or shelter
					if (this.grid[this.xpos][this.ypos - i - 1] == 'X' || this.grid[this.xpos][this.ypos - i - 1] == '^') {
						break;
						//If position is not free then it is another animal or food
					} else if (this.grid[this.xpos][this.ypos - i - 1] != ' ') {
							found = true;
						}
					}
				}
			return found;
		case East:
			found = false;
			//Checks if the next position is at the end of the grid
			for (int i = 0; i < this.smellrange; i++) {
				if (this.xpos + i + 1 < size_x) {
					//Checks if the position is a obstacle or shelter
					if (this.grid[this.xpos + 1 + i][this.ypos] == 'X' || this.grid[this.xpos + 1 + i][this.ypos] == '^') {
						break;
						//If position is not free then it is another animal or food
					}else if (this.grid[this.xpos + 1 + i][this.ypos] != ' ') {
							found = true;
						}
					}
				}
			return found;
		case South:
			found = false;
			//Checks if the next position is at the end of the grid
			for (int i = 0; i < this.smellrange; i++) {
				if (this.ypos + 1 + i < size_y) {
					//Checks if the position is a obstacle or shelter
					if (this.grid[this.xpos][this.ypos + 1 + i] == 'X' || this.grid[this.xpos][this.ypos + 1 + i] == '^') {
						break;
						//If position is not free then it is another animal or food
					}else if (this.grid[this.xpos][this.ypos + 1 + i] != ' ') {
							found = true;
						}
					}
				}
			return found;
		case West:
			found = false;
			//Checks if the next position is at the end of the grid
			for (int i = 0; i < smellrange; i++) {
				if (this.xpos - 1 - i >= 0) {
					//Checks if the position is a obstacle or shelter
					if (this.grid[this.xpos - 1 - i][this.ypos] == 'X' || this.grid[this.xpos - 1 - i][this.ypos] == '^') {
						break;
					//If position is not free then it is another animal or food
					} else if (this.grid[this.xpos - 1 - i][this.ypos] != ' ') {
							found = true;
						}
					}
				}
			return found;
		}
		return false;
	}
	
}
