/*
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job4_Driver /user/2020st39/Final_out/Job3_out /user/2020st39/Final_out/Job4_out 30
*/
public class Job4_Driver
{
	public static int InitValue = 100;

	public static void main(String[] args) throws Exception
	{
		if (args.length != 3)
		{
			System.err.println("Job4: <job3_out> <job4_out> <times>");
			System.exit(2);
		}

		int times = Integer.parseInt(args[2]);

		String[] GraphBuilder_args = { args[0], args[1] + "/Data_000" };
		Job4_GraphBuilder_Driver.main(GraphBuilder_args);

		String[] PageRankIter_args = { "", "", "" };
		for (int i = 0; i < times; i++)
		{
			PageRankIter_args[0] = args[1] + "/Data_" + String.format("%03d", i);
			PageRankIter_args[1] = args[1] + "/Data_" + String.format("%03d", i + 1);
			PageRankIter_args[2] = Integer.toString(i + 1);
			Job4_PageRankIter_Driver.main(PageRankIter_args);
		}

		String[] PageRankViewer_args = { args[1] + "/Data_" + String.format("%03d", times), args[1] + "/FinalRank" };
		Job4_PageRankViewer_Driver.main(PageRankViewer_args);
	}
}
