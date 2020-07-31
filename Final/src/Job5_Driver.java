/*
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Job5_Driver /user/2020st39/Final_out/Job3_out /user/2020st39/Final_out/Job5_out /user/2020st39/Labels.txt 15
*/
public class Job5_Driver
{
	public static int label_num = 13;

	public static void main(String[] args) throws Exception
	{
		if (args.length != 4)
		{
			System.err.println("Job4: <job3_out> <job5_out> <labelled_data> <times>");
			System.exit(2);
		}

		int times = Integer.parseInt(args[3]);

		String[] GraphBuilder_args = { args[0], args[2], args[1] + "/Data_000" };
		Job5_GraphBuilder_Driver.main(GraphBuilder_args);

		String[] LabelPropagationIter_args = { "", "", args[0] + "/part-r-00000", args[2], "" };
		for (int i = 0; i < times; i++)
		{
			LabelPropagationIter_args[0] = args[1] + "/Data_" + String.format("%03d", i);
			LabelPropagationIter_args[1] = args[1] + "/Data_" + String.format("%03d", i + 1);
			LabelPropagationIter_args[4] = Integer.toString(i + 1);
			Job5_LabelPropagationIter_Driver.main(LabelPropagationIter_args);
		}

		String[] LabelViewer_args = { args[1] + "/Data_" + String.format("%03d", times), args[1] + "/FinalLabel" };
		Job5_LabelViewer_Driver.main(LabelViewer_args);
	}
}
