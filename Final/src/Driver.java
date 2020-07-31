/*
hadoop jar /home/2020st39/Final/Final.jar /MP_Data/task2/wuxia_novels /MP_Data/task2/People_List_unique.txt /user/2020st39/Final_out 30 /user/2020st39/Labels.txt 15
hadoop jar /home/2020st39/Final/Final_NoMainClass.jar Driver /MP_Data/task2/wuxia_novels /MP_Data/task2/People_List_unique.txt /user/2020st39/Final_out 30 /user/2020st39/Labels.txt 15
*/

public class Driver
{
	public static void main(String[] args) throws Exception
	{
		if (args.length != 6)
		{
			System.err.println(
					"Usage: JAR <novels> <people_list> <out_path> <pagerank_times> <labelled_data> <labelpropagation_times>");
			System.exit(2);
		}
		String[] job1_args = { args[0], args[1], args[2] + "/Job1_out" };
		Job1_Driver.main(job1_args);

		String[] job2_args = { args[2] + "/Job1_out", args[2] + "/Job2_out" };
		Job2_Driver.main(job2_args);

		String[] job3_args = { args[2] + "/Job2_out", args[2] + "/Job3_out" };
		Job3_Driver.main(job3_args);

		String[] job4_args = { args[2] + "/Job3_out", args[2] + "/Job4_out", args[3] };
		Job4_Driver.main(job4_args);

		String[] job5_args = { args[2] + "/Job3_out", args[2] + "/Job5_out", args[4], args[5] };
		Job5_Driver.main(job5_args);
	}
}
