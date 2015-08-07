/********************************************************************************/
using System;
using System.Windows.Forms;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for Trigger.
	/// </summary>
/********************************************************************************/
	[Serializable]
	public class Trigger
	{
		public string table_name;
		public string fires;		//before / after / instead of 
		public string fire_on;		//insert / update / delete
		public string trigger_name;
		public string created_by;
		public DateTime created_on;
		public string trigger_sql;
		public bool isCompiled;
/********************************************************************************/
		public Trigger(string table_name, string fires, string fires_on, 
						string trigger_name, string created_by, DateTime created_on, 
						string trigger_sql) 
		{
			this.table_name = table_name;
			this.fires = fires;
			this.fire_on = fire_on;	
			this.trigger_name = trigger_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.trigger_sql = trigger_sql;
			this.isCompiled = false;
		}
/********************************************************************************/
		public Trigger(string table_name, string fires, string fires_on, 
						string trigger_name, string created_by, DateTime created_on, 
						string trigger_sql, bool isCompiled)
		{
			this.table_name = table_name;
			this.fires = fires;
			this.fire_on = fire_on;	
			this.trigger_name = trigger_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.trigger_sql = trigger_sql;
			this.isCompiled = isCompiled;
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/