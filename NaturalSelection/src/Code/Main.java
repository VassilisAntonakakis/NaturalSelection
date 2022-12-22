package Code;

public class Main {

	public static void main(String[] args) {
		
		
		AgentGroup[] agList = new AgentGroup[10];
		
		for(int i=0; i<10; i++) {
			agList[i] = new AgentGroup();
			agList[i].setName("ag" + Integer.toString(i));
			agList[i].start();
		}
		
		
	}

}
