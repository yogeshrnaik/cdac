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
	public class DBLink 
	{
		public string dblink_name;
		public string created_by;
		public DateTime created_on;
		public string uname;
		public string passwd;
		public string database;
		public string authby_uname;
		public string authby_passwd;
		public string dblink_sql;
/********************************************************************************/
		public DBLink(string dblink_name, string created_by, DateTime created_on, 
					string uname, string passwd, string database, string authby_uname, string authby_passwd) 
		{
			this.dblink_name = dblink_name;
			this.created_by = created_by;
			this.created_on = created_on;
			this.uname = uname;
			this.passwd = passwd;
			this.database = database;
			this.authby_uname = authby_uname;
			this.authby_passwd = authby_passwd;
			this.dblink_sql = createDBLinkSQL();
		}
/********************************************************************************/
		public string createDBLinkSQL()
		{
			dblink_sql = "";
			dblink_sql = "create database link " + dblink_name + "\n" + 
				"connect to " + uname + "\n" + 
				"identified by " + passwd + "\n";
			if (authby_uname != "")
			{
				dblink_sql += "authenticated by " + authby_uname + "\n" + 
								"identified by " + authby_passwd + "\n";
			}
			dblink_sql += "using " + database;
			return dblink_sql;
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/