package Code;

public class Genome {
	private char[] gene = new char[12];
	
	public Genome() {
		for(int i=0; i<12; i++) {
			this.setGene(i, (char)(97 + (int)(Math.random() * ((122 - 97) + 1))));
		}	
	}
	
	public Genome(String genome) {
		setGenome(genome.toCharArray());
	}

	public String getGenome() {
		return String.valueOf(gene);
	}
	
	public String getGene(int index) {
		char[] ret_gene = {gene[index], gene[index+1], gene[index+2]};
		return String.valueOf(ret_gene); 
	}

	public void setGene(int index, char value) {
		this.gene[index] = value;
	}
	
	private void setGenome(char[] genome) {
		this.gene = genome;
	}	
}
