/********************************************************************************/
using System;
using System.Windows.Forms;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for Function.
	/// </summary>
/********************************************************************************/
	[Serializable]
	public class Function
	{
		public string function_name;
		public string created_by;
		public DateTime created_on;
		public string function_sql;
		public bool isCompiled;
/********************************************************************************/
		public Function(string function_name, string created_by, DateTime created_on, 
						string function_sql) 
		{
			this.function_name = function_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.function_sql = function_sql;
			this.isCompiled = false;
		}
/********************************************************************************/
		public Function(string function_name, string created_by, DateTime created_on, 
						string function_sql, bool isCompiled) 
		{
			this.function_name = function_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.function_sql = function_sql;
			this.isCompiled = isCompiled;
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/