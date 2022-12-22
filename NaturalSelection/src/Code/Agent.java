package Code;

import java.util.Random;
import java.lang.Math;

public class Agent {
	private int sex; //0 for male - 1 for female
	private int id;
	private double xPos;
	private double yPos;
	private int foodBank;
	private Genome genome;
	private double speed;
	private double size;
	private double vision;
	private double energy;
	private double energyConsumptionRate;
	private Random rnd = new Random();
	
	public Agent(int ID) {
		setId(ID);
		setGenome(new Genome());
		setFoodBank(0);
		setSex((0 + (int)(Math.random() * ((10 - 0) + 10))) % 2);
		char[] sizeGene = this.genome.getGene(0).toCharArray();
		setSize(((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]) / 100 * 2);
		char[] speedGene = this.genome.getGene(3).toCharArray();
		setSpeed(((double)speedGene[0] + (double)speedGene[1] + (double)speedGene[2]) / 100);
		setSpeed(getSpeed() - (getSpeed() * getSize() / 100.0));
		char[] visionGene = this.genome.getGene(6).toCharArray();
		setVision(((double)visionGene[0] + (double)visionGene[1] + (double)visionGene[2]) / 100 + (getSize() / 100.0));
		char[] energyGene = this.genome.getGene(9).toCharArray();
		setEnergy(1000 - ((double)energyGene[0] + (double)energyGene[1] + (double)energyGene[2]));
		setEnergyConsumptionRate((getEnergy() - (getEnergy() * (getSize() / 100.0)) - (getEnergy() * (getSpeed() / 100))) / 10);
	}
	
	public Agent(int ID, String GENOME) {
		setId(ID);
		setGenome(new Genome(GENOME));
		setFoodBank(0);
		setSex((0 + (int)(Math.random() * ((1 - 0) + 1))));
		char[] sizeGene = this.genome.getGene(0).toCharArray();
		setSize(((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]) / 100 * 2);
		char[] speedGene = this.genome.getGene(3).toCharArray();
		setSpeed(((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]) / 100);
		setSpeed(getSpeed() - (getSpeed() * getSize() / 100.0));
		char[] visionGene = this.genome.getGene(6).toCharArray();
		setVision(((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]) / 100 + (getSize() / 100.0));
		char[] energyGene = this.genome.getGene(9).toCharArray();
		setEnergy(1000 - ((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]));
		setEnergyConsumptionRate((getEnergy() - (getEnergy() * (getSize() / 100.0)) - (getEnergy() * (getSpeed() / 100))) / 10);
	}
	
	public Agent(int ID, int SEX) {
		setId(ID);
		setGenome(new Genome());
		setSex(SEX);
		setFoodBank(0);
		char[] sizeGene = this.genome.getGene(0).toCharArray();
		setSize(((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]) / 100 * 2);
		char[] speedGene = this.genome.getGene(3).toCharArray();
		setSpeed(((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]) / 100);
		setSpeed(getSpeed() - (getSpeed() * getSize() / 100.0));
		char[] visionGene = this.genome.getGene(6).toCharArray();
		setVision(((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]) / 100 + (getSize() / 100.0));
		char[] energyGene = this.genome.getGene(9).toCharArray();
		setEnergy(1000 - ((double)sizeGene[0] + (double)sizeGene[1] + (double)sizeGene[2]));
		setEnergyConsumptionRate((getEnergy() - (getEnergy() * (getSize() / 100.0)) - (getEnergy() * (getSpeed() / 100))) / 10);
	}
	
	public void printStats() {
		System.out.printf("Agent id: %d\nsex: %d\nsize: %.2f\nspeed: %.2f\nenergy: %.2f\nConsuption Rate: %.2f\nvision: %.2f\ngenome: %s\n\n",
				getId(),
				getSex(),
				getSize(),
				getSpeed(),
				getEnergy(),
				getEnergyConsumptionRate(),
				getVision(),
				getGenome());
	}
	
	public Agent reproduce(Agent partner) {
		int[] randNums = {(1 + (int)(Math.random() * ((1000 - 1) + 1000))),
				(1 + (int)(Math.random() * ((1000 - 1) + 1000))),
				(1 + (int)(Math.random() * ((1000 - 1) + 1000))),
				(1 + (int)(Math.random() * ((1000 - 1) + 1000)))};
		String newGenome="";
		String newId = String.format("%d%d%d%d",getId() % 10 / 1000 , getId() / 100, partner.getId() % 100 / 10, partner.getId() % 10);
		
		for(int i=0; i<randNums.length; i++) {
			if(randNums[i] > 500) newGenome += partner.getGenomeObj().getGene(i*3);
			else newGenome += this.getGenomeObj().getGene(i*3);
		}
		char[] gSeq = newGenome.toCharArray();
		for(int i=0; i<newGenome.length(); i++) {
			int chance = (1 + (int)(Math.random() * ((1000 - 1) + 1000)));
			if(chance <= 10) {
				if(gSeq[i] == 'a') gSeq[i] = (char)((int)'a' + 1);
				else if(gSeq[i] == 'z') gSeq[i] = (char)((int)'z' - 1);
				else gSeq[i] = (char)('a' + rnd.nextInt(26));
				//else gSeq[i] = (char)((int)gSeq[i] + 1);
				System.out.println("Mutation");
			}
		}
		
		newGenome = String.valueOf(gSeq);
		
		return new Agent(Integer.parseInt(newId), newGenome);
	}

	public String getGenome() {
		return genome.getGenome();
	}
	
	public Genome getGenomeObj() {
		return this.genome;
	}

	private void setGenome(Genome genome) {
		this.genome = genome;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int i) {
		this.sex = i;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getVision() {
		return vision;
	}

	public void setVision(double vision) {
		this.vision = vision;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}

	public void setEnergyConsumptionRate(double energyConsumptionRate) {
		this.energyConsumptionRate = energyConsumptionRate;
	}

	public double getXPos() {
		return xPos;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	
	public void move() {
		//boolean clearOfBorders = (Math.abs()) && () && () && ()
		//if()
			double direction = rnd.nextDouble(361.00); // set random direction for agent movement in degrees
			
			if(direction == 360.00) direction = 0.00;
			
			if((direction >= 337.5) && (direction <= 22.4)) {//forward movement
				setYPos(getYPos() + 2);
			}
			else if((direction >= 22.5) && (direction <= 67.4)) {//forward right movement
				setYPos(getYPos() + 1);
				setXPos(getXPos() + 1);
			}
			else if((direction >= 67.5) && (direction <= 112.4)) {//right movement
				setXPos(getXPos() + 2);
			}
			else if((direction >= 112.5) && (direction <= 157.4)) {//right backward movement
				setYPos(getYPos() - 1);
				setXPos(getXPos() + 1);
			}
			else if((direction >= 157.5) && (direction <= 202.4)) {//backward movement
				setYPos(getYPos() - 2);
			}
			else if((direction >= 202.5) && (direction <= 247.4)) {//backward left movement
				setYPos(getYPos() - 1);
				setXPos(getXPos() - 1);
			}
			else if((direction >= 247.5) && (direction <= 292.4)) {//left movement
				setXPos(getXPos() - 2);
			}
			else if((direction >= 292.5) && (direction <= 337.4)) {//left forward  movement
				setYPos(getYPos() + 1);
				setXPos(getXPos() - 1);
			}
		
	}

	public int getFoodBank() {
		return foodBank;
	}

	public void setFoodBank(int foodBank) {
		this.foodBank = foodBank;
	}
}
