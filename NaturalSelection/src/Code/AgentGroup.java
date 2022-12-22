package Code;

public class AgentGroup extends Thread{
	private Agent[] mAgents = new Agent[25];
	private Agent[] fAgents = new Agent[25];
	private String name;
	
	public void run() {
		
		for(int i=0; i<25; i++) {
			mAgents[i] = new Agent(i*1000, 0);
			fAgents[i] = new Agent(i*1023, 1);
		}
		
		for(int gen=0; gen<5000; gen++) {
			System.out.printf("========================Generation %d============================\n", gen);
			for(int i=0; i<25; i++) {
				Agent ch1 = mAgents[i].reproduce(fAgents[i]);
				Agent ch2 = mAgents[i].reproduce(fAgents[i]);
				System.out.printf(
						"Thread %s:\nfather %d genome: %12s\nmother %d genome: %12s\nchild1 genome: %12s\nchild2 genome: %12s\n\n",
						this.getName(),
						i,
						mAgents[i].getGenome(),
						i,
						fAgents[i].getGenome(),
						ch1.getGenome(),
						ch2.getGenome());
				
			}
		}
		
	}
}
