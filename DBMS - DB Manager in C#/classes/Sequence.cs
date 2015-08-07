/********************************************************************************/
using System;
using System.Windows.Forms;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for Sequence.
	/// </summary>
/********************************************************************************/
	[Serializable]
	public class Sequence 
	{
		public string sequence_name;
		public string created_by;
		public DateTime created_on;
		public string min_value;
		public string max_value;
		public string start_with;
		public string increment_by;
		public string cache;
		public bool cycle;
		public bool order;
		public string sequence_sql;
/********************************************************************************/
		public Sequence(string sequence_name, string created_by, DateTime created_on,
						string min_value, string max_value, string start_with, string increment_by,
						string cache, bool cycle, bool order) 
		{
			this.sequence_name = sequence_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.min_value = min_value;
			this.max_value = max_value;
			this.start_with = start_with;
			this.increment_by = increment_by;
			this.cache = cache;
			this.cycle = cycle;
			this.order = order;
			this.sequence_sql = createSequenceSQL();
		}
/********************************************************************************/
		public string createSequenceSQL()
		{
			sequence_sql = "";
			sequence_sql = "create " + sequence_name + "\n" + 
							"minvalue " + min_value + "\n" + 
							"maxvalue " + max_value + "\n" + 
							"start with " + start_with + "\n" + 
							"increment by " + increment_by + "\n" + 
							"cache " + cache + "\n";
			if (cycle)
				sequence_sql += "cycle " + "\n";
			if (order)
				sequence_sql += "order " + "\n";
			return sequence_sql;
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/