/********************************************************************************/
using System;
using System.Windows.Forms;
/********************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// Summary description for Procedure.
	/// </summary>
/********************************************************************************/
	[Serializable]
	public class Procedure 
	{
		public string procedure_name;
		public string created_by;
		public DateTime created_on;
		public string procedure_sql;
		public bool isCompiled;
/********************************************************************************/
		public Procedure(string procedure_name, string created_by, DateTime created_on, 
							string procedure_sql) 
		{
			this.procedure_name = procedure_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.procedure_sql = procedure_sql;
			this.isCompiled = false;
		}
/********************************************************************************/
		public Procedure(string procedure_name, string created_by, DateTime created_on, 
							string procedure_sql, bool isCompiled) 
		{
			this.procedure_name = procedure_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.procedure_sql = procedure_sql;
			this.isCompiled = isCompiled;
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/