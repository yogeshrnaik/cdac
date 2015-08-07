/********************************************************************************/
using System;
using System.Windows.Forms;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for View.
	/// </summary>
/********************************************************************************/
	[Serializable]
	public class View 
	{
		public string view_name;
		public string created_by;
		public DateTime created_on;
		public string view_sql;
		public bool isCompiled;
/********************************************************************************/
		public View(string view_name, string created_by, DateTime created_on, 
						string view_sql) 
		{
			this.view_name = view_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.view_sql = view_sql;
			this.isCompiled = false;
		}
/********************************************************************************/
		public View(string view_name, string created_by, DateTime created_on, 
			string view_sql, bool isCompiled) 
		{
			this.view_name = view_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.view_sql = view_sql;
			this.isCompiled = isCompiled;
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/